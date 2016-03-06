package org.sqlproc.meta.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.validation.IResourceValidator;
import org.sqlproc.meta.processorMeta.Artifacts;
import org.sqlproc.meta.processorMeta.MetaStatement;
import org.sqlproc.meta.property.ModelPropertyBean;
import org.sqlproc.plugin.lib.property.ModelProperty;
import org.sqlproc.plugin.lib.resolver.DbResolver;
import org.sqlproc.plugin.lib.resolver.PojoResolverFactory;
import org.sqlproc.plugin.lib.util.MainUtils;
import org.sqlproc.plugin.lib.util.Stats;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

public class Main {

    @Inject
    private PojoResolverFactory pojoResolverFactory;
    @Inject
    private Provider<ResourceSet> resourceSetProvider;
    @Inject
    private IResourceValidator validator;
    @Inject
    private JavaIoFileSystemAccess fileAccess;
    @Inject
    IScopeProvider scopeProvider;
    @Inject
    private IResourceFactory resourceFactory;
    @Inject
    private ModelProperty modelProperty;
    @Inject
    private DbResolver dbResolver;

    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {

        Options options = new Options();
        options.addOption("target", true, "target directory (eg. src-gen)");
        options.addOption("source", true, "source directory (eg. src/main/resources)");
        options.addOption("control", true, "control directives file name (eg. definitions.meta)");
        options.addOption("sql", true, "META SQLs file name to generate (eg. statements.meta)");
        options.addOption("metas", true, "META SQLs file names to verify (eg. statements.meta,custom-statements.meta)");
        options.addOption("ddl", true, "DDLs file name (eg. hsqldb.ddl)");
        options.addOption("nomerge", false, "do not merge generated artefacts with existing ones");
        options.addOption("verify", false, "do not generate META SQLs, only verify existing ones");
        options.addOption("skipdb", false, "in the verification process skip database control");
        options.addOption("debug", false, "DEBUG output");
        CommandLineParser parser = new PosixParser();
        CommandLine cmd = parser.parse(options, args);

        String target = cmd.getOptionValue("target");
        String source = cmd.getOptionValue("source");
        String control = cmd.getOptionValue("control");
        String sql = cmd.getOptionValue("sql");
        String metas = cmd.getOptionValue("metas");
        String ddl = cmd.getOptionValue("ddl");
        boolean nomerge = cmd.hasOption("nomerge");
        boolean verify = cmd.hasOption("verify");
        boolean skipdb = cmd.hasOption("skipdb");
        boolean debug = cmd.hasOption("debug");
        if (debug) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; i++)
                sb.append(args[i]).append(" ");
            System.out.println(sb);
        }

        if (verify) {
            if (control == null || metas == null)
                usage(options);
        } else if (control == null || sql == null) {
            usage(options);
        }

        Injector injector = new org.sqlproc.meta.ProcessorMetaStandaloneSetup().createInjectorAndDoEMFRegistration();
        Main main = injector.getInstance(Main.class);

        if (target == null)
            target = "./";
        else if (!target.endsWith("/"))
            target = target + "/";
        if (source == null)
            source = "";
        else if (!source.endsWith("/"))
            source = source + "/";

        if (verify)
            main.verify(control, metas, source, skipdb, ddl);
        else
            main.generate(control, sql, ddl, source, target, !nomerge);
    }

    private static void usage(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        String header = "META SQL models generation using control directives";
        String footer = "";
        formatter.printHelp("java -jar sqlmeta.jar", header, options, footer);
        System.exit(1);
    }

    protected void verify(String control, String metas, String source, boolean skipdb, String ddl)
            throws IOException, ClassNotFoundException {

        System.out.println(pojoResolverFactory);

        ResourceSet resourceSet = resourceSetProvider.get();
        Resource controlResource = resourceSet.getResource(URI.createURI(MainUtils.getFile(source, control)), true);
        resourceSet.getResources().add(controlResource);

        String[] sqls = metas.split(",");
        List<Resource> sqlResources = new ArrayList<Resource>();
        for (String sql : sqls) {
            Resource sqlResource = resourceSet.getResource(URI.createURI(MainUtils.getFile(source, sql)), true);
            resourceSet.getResources().add(sqlResource);
            sqlResources.add(sqlResource);
        }

        System.out.println("Going to validate " + controlResource);
        Set<String> failedReferences = new HashSet<String>();
        int controlResourceIsOk = MainUtils.isValid(controlResource, failedReferences, validator);
        if (controlResourceIsOk != MainUtils.OK) {
            System.exit(2);
        }
        System.out.println("Validated " + controlResource);

        if (!skipdb) {
            Artifacts definitions = (Artifacts) controlResource.getContents().get(0);
            if (definitions.getProperties().isEmpty()) {
                System.err.println("No control directive.");
                System.exit(3);
            }
            ModelProperty.ModelValues modelValues = ModelPropertyBean.loadModel(null, definitions);
            modelValues.doResolveDb = true;
            modelProperty.init(modelValues);
            // pojoResolverFactory.setPojoResolver(new StandalonePojoResolverImpl(source));
            String dbSqlsBefore = MainUtils.loadDdl(source, ddl);
            dbResolver.init(modelProperty, null, dbSqlsBefore, null);
        }

        for (Resource sqlResource : sqlResources) {
            System.out.println("Going to validate " + sqlResource);
            int sqlResourceIsOk = MainUtils.isValid(sqlResource, null, validator);
            if (sqlResourceIsOk != MainUtils.OK) {
                System.exit(2);
            }
            System.out.println("Validated " + sqlResource);
        }
    }

    protected void generate(String control, String sql, String ddl, String source, String target, boolean merge)
            throws IOException, ClassNotFoundException {

        ResourceSet resourceSet = resourceSetProvider.get();
        Resource controlResource = resourceSet.getResource(URI.createURI(MainUtils.getFile(source, control)), true);
        resourceSet.getResources().add(controlResource);
        System.out.println("Going to validate " + controlResource);
        Set<String> failedReferences = new HashSet<String>();
        int controlResourceIsOk = MainUtils.isValid(controlResource, failedReferences, validator);
        if (controlResourceIsOk == MainUtils.ERROR) {
            System.exit(2);
        } else if (controlResourceIsOk == MainUtils.REFERENCE_ERROR) {
            String controlResourceContent = MainUtils.handleResourceReferences(controlResource, failedReferences);
            System.out.println(controlResourceContent);
            controlResource = MainUtils.reloadResourceFromString(controlResourceContent, resourceSet, resourceFactory,
                    "meta");
            controlResourceIsOk = MainUtils.isValid(controlResource, failedReferences, validator);
            if (controlResourceIsOk != MainUtils.OK) {
                System.exit(2);
            }
        }
        System.out.println("Validated " + controlResource);

        Artifacts definitions = (Artifacts) controlResource.getContents().get(0);
        if (definitions.getProperties().isEmpty()) {
            System.err.println("No control directive.");
            System.exit(3);
        }
        ModelProperty.ModelValues modelValues = ModelPropertyBean.loadModel(null, definitions);
        modelValues.doResolveDb = true;
        modelProperty.init(modelValues);
        // pojoResolverFactory.setPojoResolver(new StandalonePojoResolverImpl(source));
        String dbSqlsBefore = MainUtils.loadDdl(source, ddl);
        dbResolver.init(modelProperty, null, dbSqlsBefore, null);

        Resource sqlResource = null;
        if (merge) {
            try {
                sqlResource = resourceSet.getResource(URI.createURI(MainUtils.getFile(source, sql)), true);
                resourceSet.getResources().add(sqlResource);
            } catch (Exception ex) {
                System.out.println("Can't read " + MainUtils.getFile(source, sql));
            }
        }
        if (merge && sqlResource != null) {
            System.out.println("Going to validate " + sqlResource);
            int sqlResourceIsOk = MainUtils.isValid(sqlResource, null, validator);
            if (sqlResourceIsOk != MainUtils.OK) {
                System.exit(2);
            }
            System.out.println("Validated " + sqlResource);
        }

        Artifacts sqls = null;
        List<MetaStatement> statements = null;
        if (!merge) {
            statements = new ArrayList<MetaStatement>();
        } else {
            if (sqlResource != null) {
                sqls = (Artifacts) sqlResource.getContents().get(0);
                statements = sqls.getStatements();
            }
        }

        fileAccess.setOutputPath(target);
        System.out.println("Going to generate " + sql);
        Stats stats = new Stats();
        String metaDefinitions = TableMetaGenerator.generateMeta(definitions, statements,
                ((XtextResource) controlResource).getSerializer(), dbResolver, scopeProvider, modelProperty, stats);
        fileAccess.generateFile(sql, metaDefinitions);
        System.out.println(sql + " generation finished.");
        System.out.println(stats.toString());
    }
}
