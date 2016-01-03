package org.sqlproc.dsl.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.sqlproc.dsl.processorDsl.AbstractPojoEntity;
import org.sqlproc.dsl.processorDsl.AnnotatedEntity;
import org.sqlproc.dsl.processorDsl.Artifacts;
import org.sqlproc.dsl.processorDsl.EnumEntity;
import org.sqlproc.dsl.processorDsl.EnumEntityModifier1;
import org.sqlproc.dsl.processorDsl.MetaStatement;
import org.sqlproc.dsl.processorDsl.Package;
import org.sqlproc.dsl.processorDsl.PojoDao;
import org.sqlproc.dsl.processorDsl.PojoEntity;
import org.sqlproc.dsl.processorDsl.PojoEntityModifier1;
import org.sqlproc.dsl.property.ModelProperty;
import org.sqlproc.dsl.property.ModelPropertyBean;
import org.sqlproc.dsl.property.ModelPropertyBean.ModelValues;
import org.sqlproc.dsl.resolver.DbResolver;
import org.sqlproc.dsl.resolver.DbResolver.DbType;
import org.sqlproc.dsl.resolver.PojoResolverFactory;
import org.sqlproc.dsl.resolver.StandalonePojoResolverImpl;
import org.sqlproc.dsl.util.Annotations;
import org.sqlproc.dsl.util.MainUtils;
import org.sqlproc.dsl.util.Utils;

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
    private IGenerator2 generator;
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
        options.addOption("control", true, "control directives file name (eg. definitions.model)");
        options.addOption("pojo", true, "POJO models file name (eg. pojo.model)");
        options.addOption("dao", true, "DAO models file name (eg. dao.model)");
        options.addOption("sql", true, "META SQLs file name to generate (eg. statements.meta)");
        options.addOption("models", true, "comma separated list of model files names (eg. pojo.model,dao.model)");
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
        String pojo = cmd.getOptionValue("pojo");
        String dao = cmd.getOptionValue("dao");
        String sql = cmd.getOptionValue("sql");
        String models = cmd.getOptionValue("models");
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

        if (models == null && (control == null || pojo == null || dao == null || sql == null)) {
            usage(options);
            return;
        }

        Injector injector = new org.sqlproc.dsl.ProcessorDslStandaloneSetup().createInjectorAndDoEMFRegistration();
        Main main = injector.getInstance(Main.class);

        if (target == null)
            target = (models != null) ? "src-gen/" : "./";
        else if (!target.endsWith("/"))
            target = target + "/";
        if (source == null)
            source = "";
        else if (!source.endsWith("/"))
            source = source + "/";

        if (models != null) {
            main.generate(control, models, source, target, !verify, skipdb, ddl);
        } else if (control != null) {
            main.generate(control, pojo, dao, sql, source, target, !nomerge, ddl);
        }
    }

    private static void usage(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        String header = "POJO, DAO & META SQL models generation using control directives";
        String footer = "";
        formatter.printHelp("java -jar sqlmodel.jar", header, options, footer);
        System.exit(1);
    }

    protected void generate(String control, String models, String source, String target, boolean generate,
            boolean skipdb, String ddl) throws IOException, ClassNotFoundException {

        String[] models4ver = models.split(",");
        ResourceSet resourceSet = resourceSetProvider.get();
        Resource controlResource = resourceSet.getResource(URI.createURI(MainUtils.getFile(source, control)), true);
        resourceSet.getResources().add(controlResource);
        List<Resource> modelsResources = new ArrayList<Resource>();
        for (String model : models4ver) {
            Resource resource = resourceSet.getResource(URI.createURI(MainUtils.getFile(source, model)), true);
            resourceSet.getResources().add(resource);
            modelsResources.add(resource);
        }

        System.out.println("Going to validate " + controlResource);
        Set<String> failedReferences = new HashSet<String>();
        int controlResourceIsOk = MainUtils.isValid(controlResource, failedReferences, validator);
        if (controlResourceIsOk != MainUtils.OK) {
            System.exit(2);
        }
        System.out.println("Validated " + controlResource);

        if (!generate && !skipdb) {
            Artifacts definitions = (Artifacts) controlResource.getContents().get(0);
            if (definitions.getProperties().isEmpty()) {
                System.err.println("No control directive.");
                System.exit(3);
            }
            ModelValues modelValues = ModelPropertyBean.loadModel(null, definitions);
            modelValues.doResolveDb = true;
            modelProperty.init(modelValues);
            pojoResolverFactory.setPojoResolver(new StandalonePojoResolverImpl(modelProperty, source));
            String dbSqlsBefore = MainUtils.loadDdl(source, ddl);
            dbResolver.init(modelProperty, null, dbSqlsBefore, null);
        }

        for (Resource resource : modelsResources) {
            System.out.println("Going to validate " + resource);
            int resourceIsOk = MainUtils.isValid(resource, null, validator);
            if ((!generate && resourceIsOk != MainUtils.OK) || resourceIsOk == MainUtils.ERROR) {
                System.exit(2);
            } else if (resourceIsOk == MainUtils.REFERENCE_ERROR) {
                System.out.println("Trying to ignore this error!");
            }
            System.out.println("Validated " + resource);
        }
        System.out.println("Resource(s) validation finished.");

        if (generate) {
            fileAccess.setOutputPath(target);
            for (Resource resource : modelsResources) {
                System.out.println("Going to generate " + resource);
                generator.doGenerate(resource, fileAccess);
                System.out.println("Generated " + resource + " into " + target);
            }
            System.out.println("Java code generation finished.");
        }
    }

    protected void generate(String control, String pojo, String dao, String sql, String source, String target,
            boolean merge, String ddl) throws IOException, ClassNotFoundException {

        ResourceSet resourceSet = resourceSetProvider.get();
        Resource controlResource = resourceSet.getResource(URI.createURI(getFile(source, control)), true);
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
                    "model");
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
        ModelValues modelValues = ModelPropertyBean.loadModel(null, definitions);
        modelValues.doResolveDb = true;
        modelProperty.init(modelValues);
        pojoResolverFactory.setPojoResolver(new StandalonePojoResolverImpl(modelProperty, source));
        String dbSqlsBefore = MainUtils.loadDdl(source, ddl);
        dbResolver.init(modelProperty, null, dbSqlsBefore, null);

        Resource pojoResource = null;
        Resource daoResource = null;
        if (merge) {
            try {
                pojoResource = resourceSet.getResource(URI.createURI(getFile(source, pojo)), true);
                resourceSet.getResources().add(pojoResource);
            } catch (Exception ex) {
                System.out.println("Can't read " + getFile(source, pojo));
            }
            if (pojoResource != null && dao != null) {
                try {
                    daoResource = resourceSet.getResource(URI.createURI(getFile(source, dao)), true);
                    resourceSet.getResources().add(daoResource);
                } catch (Exception ex) {
                    System.out.println("Can't read " + getFile(source, dao));
                }
            }
        }
        Resource sqlResource = null;
        if (merge) {
            try {
                sqlResource = resourceSet.getResource(URI.createURI(getFile(source, sql)), true);
                resourceSet.getResources().add(sqlResource);
            } catch (Exception ex) {
                System.out.println("Can't read " + getFile(source, sql));
            }
        }

        if (merge && pojoResource != null) {
            System.out.println("Going to validate " + pojoResource);
            int pojoResourceIsOk = MainUtils.isValid(pojoResource, null, validator);
            if (pojoResourceIsOk != MainUtils.OK) {
                System.exit(2);
            }
            System.out.println("Validated " + pojoResource);
        }
        if (merge && daoResource != null) {
            System.out.println("Going to validate " + daoResource);
            int daoResourceIsOk = MainUtils.isValid(daoResource, null, validator);
            if (daoResourceIsOk != MainUtils.OK) {
                System.exit(2);
            }
            System.out.println("Validated " + daoResource);
        }
        if (merge && sqlResource != null) {
            System.out.println("Going to validate " + sqlResource);
            int sqlResourceIsOk = MainUtils.isValid(sqlResource, null, validator);
            if (sqlResourceIsOk != MainUtils.OK) {
                System.exit(2);
            }
            System.out.println("Validated " + sqlResource);
        }

        Artifacts pojos = null;
        Package pojoPackage = null;
        String pojoPackageName = null;
        if (!merge) {
            pojoPackageName = modelProperty.getPackage(null);
        } else {
            if (pojoResource != null) {
                pojos = (Artifacts) pojoResource.getContents().get(0);
                if (!pojos.getPackages().isEmpty()) {
                    pojoPackage = pojos.getPackages().get(0);
                    pojoPackageName = pojoPackage.getName();
                }
            } else {
                pojoPackageName = modelProperty.getPackage(null);
            }
        }
        if (pojoPackage == null && pojoPackageName == null) {
            System.err.println("Missing POJO package.");
            System.exit(3);
        }

        fileAccess.setOutputPath(target);
        Artifacts daos = null;
        Package daoPackage = null;
        String daoPackageName = null;
        if (!merge) {
            daoPackageName = modelProperty.getDaoPackage(null);
        } else {
            if (daoResource != null) {
                daos = (Artifacts) daoResource.getContents().get(0);
                if (!daos.getPackages().isEmpty()) {
                    daoPackage = daos.getPackages().get(0);
                    daoPackageName = daoPackage.getName();
                }
            } else {
                daoPackageName = modelProperty.getDaoPackage(null);
            }
        }
        if (daoPackage == null && daoPackageName == null) {
            System.err.println("Missing DAO package.");
            System.exit(3);
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

        System.out.println("Going to generate " + pojo);
        String pojoDefinitions = getPojoDefinitions(modelProperty, dbResolver, definitions, pojoPackage,
                ((XtextResource) controlResource).getSerializer());
        fileAccess.generateFile(pojo, "package " + pojoPackageName + " {\n" + pojoDefinitions + "}");
        System.out.println(pojo + " generation finished.");

        System.out.println("Going to generate " + dao);
        String daoDefinitions = getDaoDefinitions(modelProperty, dbResolver, definitions, daoPackage,
                ((XtextResource) controlResource).getSerializer());
        fileAccess.generateFile(dao, "package " + daoPackageName + " {\n" + daoDefinitions + "}");
        System.out.println(dao + " generation finished.");

        System.out.println("Going to generate " + sql);
        String metaDefinitions = getMetaDefinitions(modelProperty, dbResolver, definitions, statements,
                ((XtextResource) controlResource).getSerializer());
        fileAccess.generateFile(sql, metaDefinitions);
        System.out.println(sql + " generation finished.");
    }

    protected String getFile(String source, String file) {
        if (file.startsWith("/"))
            return file;
        return source + file;
    }

    protected boolean isValid(Resource resource) throws IOException {
        resource.load(null);
        List<Issue> list = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        if (!list.isEmpty()) {
            for (Issue issue : list) {
                System.err.println(issue);
            }
            return false;
        }
        return true;
    }

    protected String getPojoDefinitions(ModelProperty modelProperty, DbResolver dbResolver, Artifacts artifacts,
            Package packagex, ISerializer serializer) {

        if (artifacts != null && dbResolver.isResolveDb(artifacts)) {
            Map<String, String> finalEntities = new HashMap<String, String>();
            Annotations annotations = new Annotations();
            String suffix = null;
            if (packagex != null) {
                suffix = Utils.getSuffix(packagex);
                for (AbstractPojoEntity ape : packagex.getElements()) {
                    if (ape instanceof AnnotatedEntity) {
                        AnnotatedEntity apojo = (AnnotatedEntity) ape;
                        if (apojo.getEntity() != null && apojo.getEntity() instanceof PojoEntity) {
                            PojoEntity pojo = (PojoEntity) apojo.getEntity();
                            Annotations.grabAnnotations(apojo, pojo, annotations);
                            if (isFinal(pojo)) {
                                // if (suffix != null && pojo.getName().endsWith(suffix))
                                // finalEntities.put(
                                // pojo.getName().substring(0, pojo.getName().length() - suffix.length()),
                                // serializer.serialize(pojo));
                                // else
                                finalEntities.put(pojo.getName(), serializer.serialize(pojo));
                            }
                        } else if (apojo.getEntity() != null && apojo.getEntity() instanceof EnumEntity) {
                            EnumEntity pojo = (EnumEntity) apojo.getEntity();
                            if (isFinal(pojo)) {
                                // if (suffix != null && pojo.getName().endsWith(suffix))
                                // finalEntities.put(
                                // pojo.getName().substring(0, pojo.getName().length() - suffix.length()),
                                // serializer.serialize(pojo));
                                // else
                                finalEntities.put(pojo.getName(), serializer.serialize(pojo));
                            }
                        }
                    }
                }
            }
            // List<String> tables = dbResolver.getTables(artifacts);
            List<String> dbSequences = dbResolver.getSequences(artifacts);
            DbType dbType = Utils.getDbType(dbResolver, artifacts);
            TablePojoGenerator generator = new TablePojoGenerator(modelProperty, artifacts, suffix, finalEntities,
                    annotations, dbSequences, dbType);
            if (TablePojoGenerator.addDefinitions(scopeProvider, dbResolver, generator, artifacts))
                return generator.getPojoDefinitions(modelProperty, artifacts);
        }
        return null;
    }

    protected String getDaoDefinitions(ModelProperty modelProperty, DbResolver dbResolver, Artifacts artifacts,
            Package packagex, ISerializer serializer) {

        if (artifacts != null && dbResolver.isResolveDb(artifacts)) {
            Map<String, String> finalDaos = new HashMap<String, String>();
            String suffix = null;
            if (packagex != null) {
                suffix = Utils.getSuffix(packagex);
                for (AbstractPojoEntity ape : packagex.getElements()) {
                    if (ape instanceof PojoDao) {
                        PojoDao dao = (PojoDao) ape;
                        if (isFinal(dao)) {
                            // if (suffix != null && dao.getName().endsWith(suffix))
                            // finalDaos.put(dao.getName().substring(0, dao.getName().length() - suffix.length()),
                            // serializer.serialize(dao));
                            // else
                            finalDaos.put(dao.getName(), serializer.serialize(dao));
                        }
                    }
                }
            }
            // List<String> tables = dbResolver.getTables(artifacts);
            List<String> dbSequences = dbResolver.getSequences(artifacts);
            DbType dbType = Utils.getDbType(dbResolver, artifacts);
            TableDaoGenerator generator = new TableDaoGenerator(modelProperty, artifacts, suffix, scopeProvider,
                    finalDaos, dbSequences, dbType);
            if (TablePojoGenerator.addDefinitions(scopeProvider, dbResolver, generator, artifacts)) {
                return generator.getDaoDefinitions(modelProperty, artifacts);
            }
        }
        return null;
    }

    protected String getMetaDefinitions(ModelProperty modelProperty, DbResolver dbResolver, Artifacts artifacts,
            List<MetaStatement> statements, ISerializer serializer) {

        if (artifacts != null && dbResolver.isResolveDb(artifacts)) {
            Map<String, String> finalMetas = new HashMap<String, String>();
            for (MetaStatement meta : statements) {
                if (Utils.isFinal(meta)) {
                    finalMetas.put(meta.getName(), serializer.serialize(meta));
                }
            }
            // List<String> tables = dbResolver.getTables(artifacts);
            List<String> dbSequences = dbResolver.getSequences(artifacts);
            DbType dbType = Utils.getDbType(dbResolver, artifacts);
            TableMetaGenerator generator = new TableMetaGenerator(modelProperty, artifacts, scopeProvider, finalMetas,
                    dbSequences, dbType);
            if (TablePojoGenerator.addDefinitions(scopeProvider, dbResolver, generator, artifacts))
                return generator.getMetaDefinitions(modelProperty, artifacts);
        }
        return null;
    }

    protected boolean isFinal(PojoEntity e) {
        if (e.getModifiers1() == null || e.getModifiers1().isEmpty())
            return false;
        for (PojoEntityModifier1 modifier : e.getModifiers1()) {
            if (modifier.isFinal())
                return true;
        }
        return false;
    }

    protected boolean isFinal(EnumEntity e) {
        if (e.getModifiers1() == null || e.getModifiers1().isEmpty())
            return false;
        for (EnumEntityModifier1 modifier : e.getModifiers1()) {
            if (modifier.isFinal())
                return true;
        }
        return false;
    }

    protected boolean isFinal(PojoDao d) {
        if (d.getModifiers1() == null || d.getModifiers1().isEmpty())
            return false;
        for (PojoEntityModifier1 modifier : d.getModifiers1()) {
            if (modifier.isFinal())
                return true;
        }
        return false;
    }
}
