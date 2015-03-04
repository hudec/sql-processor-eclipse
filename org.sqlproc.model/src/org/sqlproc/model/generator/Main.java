/*
 * generated by Xtext
 */
package org.sqlproc.model.generator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.sqlproc.model.processorModel.Artifacts;
import org.sqlproc.model.processorModel.Package;
import org.sqlproc.model.property.ModelPropertyBean;
import org.sqlproc.model.property.ModelPropertyBean.ModelValues;
import org.sqlproc.model.resolver.DbResolver;
import org.sqlproc.model.resolver.DbResolverBean;

import com.google.common.io.Files;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

public class Main {

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

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String models = null;
        String target = null;
        String source = null;
        String control = null;
        String pojo = null;
        String dao = null;
        String ddl = null;
        boolean merge = true;
        boolean generate = true;
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if ("-models".equals(arg) && i < args.length - 1)
                models = args[++i];
            else if ("-target".equals(arg) && i < args.length - 1)
                target = args[++i];
            else if ("-source".equals(arg) && i < args.length - 1)
                source = args[++i];
            else if ("-control".equals(arg) && i < args.length - 1)
                control = args[++i];
            else if ("-pojo".equals(arg) && i < args.length - 1)
                pojo = args[++i];
            else if ("-dao".equals(arg) && i < args.length - 1)
                dao = args[++i];
            else if ("-ddl".equals(arg) && i < args.length - 1)
                ddl = args[++i];
            else if ("-nomerge".equals(arg))
                merge = false;
            else if ("-verify".equals(arg))
                generate = false;
            else {
                usage(arg);
                return;
            }
        }
        System.out.println("models " + models);
        System.out.println("control " + control);
        System.out.println("pojo " + pojo);
        System.out.println("dao " + dao);
        if (models == null && (control == null || pojo == null || dao == null)) {
            usage(null);
            return;
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
            main.runGenerator(models, source, target, generate);
        } else if (control != null) {
            main.runGenerator(control, pojo, dao, ddl, source, target, merge);
        }
    }

    private static void usage(String arg) {
        System.out.println();
        if (arg != null)
            System.out.println("Incorrect argument '" + arg + "'. Two modes are supported.");
        else
            System.out.println("Incorrect usage. Two modes are supported.");
        System.out.println("Mode 1: POJO & DAO Java source files generation using model files:");
        System.out
                .println("  java -jar sqlmodel.jar -models modelsFile1,modelsFile2... [-source sourceDir] [-target targetDir] [-verify]");
        System.out.println("For example:");
        System.out.println("  java -jar sqlmodel.jar -models pojo.model,dao.model -target src-gen");
        System.out.println("Mode 2: POJO and DAO models generation using control directives:");
        System.out
                .println("  java -jar sqlmodel.jar -control controlDirectivesFile -pojo pojoModelsFile -dao daoModelsFile -sql metaSqlsFile [-ddl ddlsFile] [-source sourceDir] [-target targetDir] [-nomerge]");
        System.out.println("For example:");
        System.out
                .println("  java -jar sqlmodel.jar -control definitions.model -pojo pojo.model -dao dao.model -sql statements.model");
        System.out.println();
        System.out.println("Arguments:");
        System.out
                .println("  -models filename[.filename] - comma separated list of model files names (eg. pojo.model,dao.model)");
        System.out.println("  -target dirname - a target directory (eg. src-gen)");
        System.out.println("  -source dirname - a source directory (eg. src/main/resources)");
        System.out.println("  -control filename - a control directives file name");
        System.out.println("  -pojo filename - a POJO models file name");
        System.out.println("  -dao filename - a DAO models file name");
        System.out.println("  -ddl filename - a DDLs file name");
        System.out.println("  -nomerge - do not merge generated artefacts with existing ones");
        System.out.println("  -verify - do not generate Java source files, only verify models files");
        System.out.println();
    }

    protected void runGenerator(String models, String source, String target, boolean generate) throws IOException,
            ClassNotFoundException {

        String[] sResources = models.split(",");
        ResourceSet set = resourceSetProvider.get();
        List<Resource> set2 = new ArrayList<Resource>();
        for (String sResource : sResources) {
            Resource resource = set.getResource(URI.createURI(getFile(source, sResource)), true);
            set.getResources().add(resource);
            set2.add(resource);
        }

        for (Resource resource : set2) {
            System.out.println("Going to validate " + resource);
            if (!isValid(resource))
                return;
            System.out.println("Validated " + resource);
        }
        System.out.println("Resource(s) validation finished.");

        if (generate) {
            fileAccess.setOutputPath(target);
            for (Resource resource : set2) {
                System.out.println("Going to generate " + resource);
                generator.doGenerate(resource, fileAccess);
                System.out.println("Generated " + resource + " into " + target);
            }
            System.out.println("Java code generation finished.");
        }
    }

    protected void runGenerator(String control, String pojo, String dao, String ddl, String source, String target,
            boolean merge) throws IOException, ClassNotFoundException {

        ResourceSet set = resourceSetProvider.get();
        Resource controlResource = set.getResource(URI.createURI(getFile(source, control)), true);
        set.getResources().add(controlResource);
        Resource pojoResource = null;
        Resource daoResource = null;
        if (merge) {
            try {
                pojoResource = set.getResource(URI.createURI(getFile(source, pojo)), true);
                set.getResources().add(pojoResource);
            } catch (Exception ex) {
                System.out.println("Can't read " + getFile(source, pojo));
            }
            if (pojoResource != null) {
                try {
                    daoResource = set.getResource(URI.createURI(getFile(source, dao)), true);
                    set.getResources().add(daoResource);
                } catch (Exception ex) {
                    System.out.println("Can't read " + getFile(source, dao));
                }
            }
        }

        System.out.println("Going to validate " + controlResource);
        boolean controlResourceIsOk = isValid(controlResource);
        if (!controlResourceIsOk)
            return;
        System.out.println("Validated " + controlResource);
        if (merge && pojoResource != null) {
            System.out.println("Going to validate " + pojoResource);
            boolean pojoResourceIsOk = isValid(pojoResource);
            if (!pojoResourceIsOk)
                return;
            System.out.println("Validated " + pojoResource);
        }
        if (merge && daoResource != null) {
            System.out.println("Going to validate " + daoResource);
            boolean daoResourceIsOk = isValid(daoResource);
            if (!daoResourceIsOk)
                return;
            System.out.println("Validated " + daoResource);
        }

        Artifacts definitions = (Artifacts) controlResource.getContents().get(0);
        if (definitions.getProperties().isEmpty()) {
            System.err.println("No control directive.");
            return;
        }
        fileAccess.setOutputPath(target);
        ModelValues modelValues = ModelPropertyBean.loadModel(null, definitions);
        modelValues.doResolveDb = true;
        ModelPropertyBean modelProperty = new ModelPropertyBean(modelValues);
        String sDbDriver = modelProperty.getModelValues(null).dbDriver;
        Class<?> driverClass = this.getClass().getClassLoader().loadClass(sDbDriver);
        String dbSqlsBefore = null;
        if (ddl != null) {
            File file = new File(getFile(source, ddl));
            dbSqlsBefore = new String(Files.toByteArray(file));
        }
        DbResolver dbResolver = new DbResolverBean(modelProperty, driverClass, dbSqlsBefore, null);

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
            return;
        }

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
            return;
        }

        System.out.println("Going to generate " + pojo);
        String pojoDefinitions = TablePojoGenerator.generatePojo(definitions, pojoPackage,
                ((XtextResource) controlResource).getSerializer(), dbResolver, scopeProvider, modelProperty);
        fileAccess.generateFile(pojo, "package " + pojoPackageName + " {\n" + pojoDefinitions + "}");
        System.out.println(pojo + " generation finished.");

        System.out.println("Going to generate " + dao);
        String daoDefinitions = TableDaoGenerator.generateDao(definitions, daoPackage,
                ((XtextResource) controlResource).getSerializer(), dbResolver, scopeProvider, modelProperty);
        fileAccess.generateFile(dao, "package " + daoPackageName + " {\n" + daoDefinitions + "}");
        System.out.println(dao + " generation finished.");
    }

    protected String getFile(String source, String file) {
        if (file.startsWith("/"))
            return file;
        return source + file;
    }

    protected boolean isValid(Resource resource) throws IOException {
        boolean isError = false;
        resource.load(null);
        List<Issue> list = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        if (!list.isEmpty()) {
            for (Issue issue : list) {
                System.err.println(issue);
                if (issue.getSeverity() == Severity.ERROR)
                    isError = true;
            }
        }
        return !isError;
    }
}
