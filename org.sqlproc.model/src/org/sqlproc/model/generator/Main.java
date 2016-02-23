package org.sqlproc.model.generator;

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
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.validation.IResourceValidator;
import org.sqlproc.model.processorModel.Artifacts;
import org.sqlproc.model.processorModel.Package;
import org.sqlproc.model.processorModel.PackageDirective;
import org.sqlproc.model.processorModel.PackageDirectiveImplementation;
import org.sqlproc.model.property.ModelPropertyBean;
import org.sqlproc.plugin.lib.property.ModelProperty;
import org.sqlproc.plugin.lib.resolver.DbResolver;
import org.sqlproc.plugin.lib.resolver.PojoResolverFactory;
import org.sqlproc.plugin.lib.resolver.StandalonePojoResolverImpl;
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
    private IGenerator generator;
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

        if (verify) {
            if (control == null || models == null)
                usage(options);
        } else if (control == null || (pojo == null && models == null)) {
            usage(options);
        }

        Injector injector = new org.sqlproc.model.ProcessorModelStandaloneSetup().createInjectorAndDoEMFRegistration();
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
            main.generate(control, pojo, dao, source, target, !nomerge, ddl);
        }
    }

    private static void usage(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        String header = "POJO & DAO models generation using control directives";
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
            ModelProperty.ModelValues modelValues = ModelPropertyBean.loadModel(null, definitions);
            modelValues.doResolveDb = true;
            modelProperty.init(modelValues);
            pojoResolverFactory.setPojoResolver(new StandalonePojoResolverImpl(source));
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

    protected void generate(String control, String pojo, String dao, String source, String target, boolean merge,
            String ddl) throws IOException, ClassNotFoundException {

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
        ModelProperty.ModelValues modelValues = ModelPropertyBean.loadModel(null, definitions);
        modelValues.doResolveDb = true;
        modelProperty.init(modelValues);
        pojoResolverFactory.setPojoResolver(new StandalonePojoResolverImpl(source));
        String dbSqlsBefore = MainUtils.loadDdl(source, ddl);
        dbResolver.init(modelProperty, null, dbSqlsBefore, null);

        Resource pojoResource = null;
        Resource daoResource = null;
        if (merge) {
            try {
                pojoResource = resourceSet.getResource(URI.createURI(MainUtils.getFile(source, pojo)), true);
                resourceSet.getResources().add(pojoResource);
            } catch (Exception ex) {
                System.out.println("Can't read " + MainUtils.getFile(source, pojo));
            }
            if (pojoResource != null && dao != null) {
                try {
                    daoResource = resourceSet.getResource(URI.createURI(MainUtils.getFile(source, dao)), true);
                    resourceSet.getResources().add(daoResource);
                } catch (Exception ex) {
                    System.out.println("Can't read " + MainUtils.getFile(source, dao));
                }
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

        Artifacts pojos = null;
        List<Package> pojoPackages = null;
        Package pojoPackage = null;
        String pojoPackageName = null;
        if (!merge) {
            pojoPackageName = modelProperty.getPackage(null);
        } else {
            if (pojoResource != null) {
                pojos = (Artifacts) pojoResource.getContents().get(0);
                if (!pojos.getPackages().isEmpty()) {
                    pojoPackages = pojos.getPackages();
                    pojoPackage = pojoPackages.get(0);
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

        Artifacts daos = null;
        List<Package> daoPackages = null;
        Package daoPackage = null;
        String daoPackageName = null;
        String daoImplPackageName = null;
        if (dao != null) {
            if (!merge) {
                daoPackageName = modelProperty.getDaoPackage(null);
                daoImplPackageName = modelProperty.getDaoImplPackage(null);
            } else {
                if (daoResource != null) {
                    daos = (Artifacts) daoResource.getContents().get(0);
                    if (!daos.getPackages().isEmpty()) {
                        daoPackages = daos.getPackages();
                        daoPackage = daoPackages.get(0);
                        daoPackageName = daoPackage.getName();
                        if (daoPackage.getDirectives() != null && !daoPackage.getDirectives().isEmpty()) {
                            for (PackageDirective dir : daoPackage.getDirectives()) {
                                if (dir instanceof PackageDirectiveImplementation)
                                    daoImplPackageName = ((PackageDirectiveImplementation) dir).getImplementation();
                            }
                        }
                    }
                } else {
                    daoPackageName = modelProperty.getDaoPackage(null);
                    daoImplPackageName = modelProperty.getDaoImplPackage(null);
                }
            }
            if (daoPackage == null && daoPackageName == null) {
                System.err.println("Missing DAO package.");
                System.exit(3);
            }
        }

        ISerializer serializer = ((XtextResource) controlResource).getSerializer();
        fileAccess.setOutputPath(target);

        {
            System.out.println("Going to generate " + pojo);
            Stats stats = new Stats();
            String pojoDefinitions = TablePojoGenerator.generatePojo(definitions, pojoPackage, serializer, dbResolver,
                    scopeProvider, modelProperty, stats);
            StringBuilder sb = new StringBuilder();
            sb.append("package ").append(pojoPackageName).append(" {\n").append(pojoDefinitions).append("}");
            if (pojoPackages != null && !pojoPackages.isEmpty()) {
                boolean first = true;
                for (Package pkg : pojoPackages) {
                    if (first) {
                        first = false;
                        continue;
                    }
                    sb.append("\n").append(serializer.serialize(pkg));
                }
            }
            fileAccess.generateFile(pojo, "package " + pojoPackageName + " {\n" + pojoDefinitions + "}");
            System.out.println(pojo + " generation finished.");
            System.out.println(stats.toString());
        }

        if (dao != null) {
            System.out.println("Going to generate " + dao);
            Stats stats = new Stats();
            String daoDefinitions = TableDaoGenerator.generateDao(definitions, daoPackage, serializer, dbResolver,
                    scopeProvider, modelProperty, stats);
            StringBuilder sb = new StringBuilder();
            if (daoImplPackageName != null)
                sb.append("#Implementation(").append(daoImplPackageName).append(")\n");
            sb.append("package ").append(daoPackageName).append(" {\n").append(daoDefinitions).append("}");
            if (daoPackages != null && !daoPackages.isEmpty()) {
                boolean first = true;
                for (Package pkg : daoPackages) {
                    if (first) {
                        first = false;
                        continue;
                    }
                    sb.append("\n").append(serializer.serialize(pkg));
                }
            }
            fileAccess.generateFile(dao, sb);
            System.out.println(dao + " generation finished.");
            System.out.println(stats.toString());
        }
    }
}
