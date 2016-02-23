package org.sqlproc.model.ui.templates;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.templates.SimpleTemplateVariableResolver;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContext;
import org.eclipse.xtext.xbase.ui.templates.XbaseTemplateContextType;
import org.sqlproc.model.generator.TableDaoGenerator;
import org.sqlproc.model.generator.TablePojoGenerator;
import org.sqlproc.model.processorModel.Artifacts;
import org.sqlproc.model.processorModel.Package;
import org.sqlproc.plugin.lib.property.FunctionDefinition;
import org.sqlproc.plugin.lib.property.ModelProperty;
import org.sqlproc.plugin.lib.property.ProcedureDefinition;
import org.sqlproc.plugin.lib.property.TableDefinition;
import org.sqlproc.plugin.lib.resolver.DbResolver;
import org.sqlproc.plugin.lib.resolver.PojoResolver;
import org.sqlproc.plugin.lib.util.CommonUtils;
import org.sqlproc.plugin.lib.util.Stats;

import com.google.inject.Inject;

public class ProcessorModelTemplateContextType extends XbaseTemplateContextType {

    @Inject
    PojoResolver pojoResolver;

    @Inject
    DbResolver dbResolver;

    @Inject
    IScopeProvider scopeProvider;

    @Inject
    ModelProperty modelProperty;

    // @Inject
    // TableDaoGenerator tableDaoGenerator;
    //
    // @Inject
    // TableMetaGenerator tableMetaGenerator;
    //
    // @Inject
    // TablePojoGenerator tablePojoGenerator;

    @Override
    protected void addDefaultTemplateVariables() {
        super.addDefaultTemplateVariables();
        super.addResolver(new TablesDefinitionsResolver());
        super.addResolver(new ProceduresDefinitionsResolver());
        super.addResolver(new FunctionsDefinitionsResolver());
        super.addResolver(new PojoGeneratorResolver());
        super.addResolver(new DaoGeneratorResolver());
    }

    protected Artifacts getArtifacts(XtextTemplateContext xtextTemplateContext) {
        if (xtextTemplateContext == null)
            return null;
        EObject object = xtextTemplateContext.getContentAssistContext().getCurrentModel();
        if (object == null)
            return null;
        return EcoreUtil2.getContainerOfType(object, Artifacts.class);
    }

    protected Package getPackage(XtextTemplateContext xtextTemplateContext) {
        if (xtextTemplateContext == null)
            return null;
        EObject object = xtextTemplateContext.getContentAssistContext().getCurrentModel();
        Package packagex = EcoreUtil2.getContainerOfType(object, Package.class);
        return packagex;
    }

    public class TablesDefinitionsResolver extends SimpleTemplateVariableResolver {

        public static final String NAME = "tableDefinitions";

        public TablesDefinitionsResolver() {
            super(NAME, "TableDefinitions");
        }

        @Override
        protected String resolve(TemplateContext context) {
            Artifacts artifacts = getArtifacts((XtextTemplateContext) context);
            if (artifacts != null && dbResolver.isResolveDb(artifacts)) {
                Map<String, TableDefinition> tablesPresented = modelProperty.getModelTables(artifacts);
                List<String> tables = dbResolver.getTables(artifacts);
                return CommonUtils.getTablesDefinitions(tables, tablesPresented);
            }
            return super.resolve(context);
        }

        @Override
        protected boolean isUnambiguous(TemplateContext context) {
            return true;
        }
    }

    public class ProceduresDefinitionsResolver extends SimpleTemplateVariableResolver {

        public static final String NAME = "procedureDefinitions";

        public ProceduresDefinitionsResolver() {
            super(NAME, "ProcedureDefinitions");
        }

        @Override
        protected String resolve(TemplateContext context) {
            Artifacts artifacts = getArtifacts((XtextTemplateContext) context);
            if (artifacts != null && dbResolver.isResolveDb(artifacts)) {
                Map<String, ProcedureDefinition> proceduresPresented = modelProperty.getModelProcedures(artifacts);
                List<String> procedures = dbResolver.getProcedures(artifacts);
                return CommonUtils.getProceduresDefinitions(procedures, proceduresPresented);
            }
            return super.resolve(context);
        }

        @Override
        protected boolean isUnambiguous(TemplateContext context) {
            return true;
        }
    }

    public class FunctionsDefinitionsResolver extends SimpleTemplateVariableResolver {

        public static final String NAME = "functionDefinitions";

        public FunctionsDefinitionsResolver() {
            super(NAME, "FunctionDefinitions");
        }

        @Override
        protected String resolve(TemplateContext context) {
            Artifacts artifacts = getArtifacts((XtextTemplateContext) context);
            if (artifacts != null && dbResolver.isResolveDb(artifacts)) {
                Map<String, FunctionDefinition> functionsPresented = modelProperty.getModelFunctions(artifacts);
                List<String> functions = dbResolver.getFunctions(artifacts);
                return CommonUtils.getFunctionsDefinitions(functions, functionsPresented);
            }
            return super.resolve(context);
        }

        @Override
        protected boolean isUnambiguous(TemplateContext context) {
            return true;
        }
    }

    public class PojoGeneratorResolver extends SimpleTemplateVariableResolver {

        public static final String NAME = "pojoGenerator";

        public PojoGeneratorResolver() {
            super(NAME, "PojoGenerator");
        }

        @Override
        protected String resolve(TemplateContext context) {
            Artifacts artifacts = getArtifacts((XtextTemplateContext) context);
            Package packagex = getPackage((XtextTemplateContext) context);
            Stats stats = new Stats();
            String pojos = TablePojoGenerator.generatePojo(artifacts, packagex, null, dbResolver, scopeProvider,
                    modelProperty, stats);
            if (pojos != null) {
                return pojos + stats.toString();
            }
            return super.resolve(context);
        }

        @Override
        protected boolean isUnambiguous(TemplateContext context) {
            return true;
        }
    }

    public class DaoGeneratorResolver extends SimpleTemplateVariableResolver {

        public static final String NAME = "daoGenerator";

        public DaoGeneratorResolver() {
            super(NAME, "DaoGenerator");
        }

        @Override
        protected String resolve(TemplateContext context) {
            Artifacts artifacts = getArtifacts((XtextTemplateContext) context);
            Package packagex = getPackage((XtextTemplateContext) context);
            Stats stats = new Stats();
            String daos = TableDaoGenerator.generateDao(artifacts, packagex, null, dbResolver, scopeProvider,
                    modelProperty, stats);
            if (daos != null) {
                return daos + stats.toString();
            }
            return super.resolve(context);
        }

        @Override
        protected boolean isUnambiguous(TemplateContext context) {
            return true;
        }
    }
}
