package org.sqlproc.meta.ui.templates;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.templates.SimpleTemplateVariableResolver;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContext;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContextType;
import org.eclipse.xtext.util.Strings;
import org.sqlproc.meta.generator.TableMetaGenerator;
import org.sqlproc.meta.processorMeta.Artifacts;
import org.sqlproc.meta.processorMeta.MetaStatement;
import org.sqlproc.meta.util.Utils;
import org.sqlproc.plugin.lib.property.FunctionDefinition;
import org.sqlproc.plugin.lib.property.ModelProperty;
import org.sqlproc.plugin.lib.property.ProcedureDefinition;
import org.sqlproc.plugin.lib.property.TableDefinition;
import org.sqlproc.plugin.lib.resolver.DbResolver;
import org.sqlproc.plugin.lib.resolver.PojoResolver;
import org.sqlproc.plugin.lib.util.CommonUtils;
import org.sqlproc.plugin.lib.util.Constants;
import org.sqlproc.plugin.lib.util.Pair;
import org.sqlproc.plugin.lib.util.Stats;

import com.google.inject.Inject;

public class ProcessorMetaTemplateContextType extends XtextTemplateContextType {

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
        super.addResolver(new DbTableResolver());
        super.addResolver(new PojoColumnResolver());
        super.addResolver(new DbSelectColumnResolver());
        super.addResolver(new DbInsertColumnResolver());
        super.addResolver(new DbUpdateColumnResolver());
        super.addResolver(new DbCondColumnResolver());
        super.addResolver(new DbVerUpdateColumnResolver());
        super.addResolver(new DbOptUpdateColumnResolver());
        super.addResolver(new DbOptCondColumnResolver());
        super.addResolver(new PojoDefinitionsResolver());
        super.addResolver(new TablesDefinitionsResolver());
        super.addResolver(new ProceduresDefinitionsResolver());
        super.addResolver(new FunctionsDefinitionsResolver());
        super.addResolver(new MetaGeneratorResolver());
    }

    protected Artifacts getArtifacts(XtextTemplateContext xtextTemplateContext) {
        if (xtextTemplateContext == null)
            return null;
        EObject object = xtextTemplateContext.getContentAssistContext().getCurrentModel();
        if (object == null)
            return null;
        return EcoreUtil2.getContainerOfType(object, Artifacts.class);
    }

    protected MetaStatement getMetaStatement(XtextTemplateContext xtextTemplateContext) {
        if (xtextTemplateContext == null)
            return null;
        EObject object = xtextTemplateContext.getContentAssistContext().getCurrentModel();
        MetaStatement statement = EcoreUtil2.getContainerOfType(object, MetaStatement.class);
        return statement;
    }

    protected Pair<Artifacts, TableDefinition> getTableDefinition(MetaStatement statement) {
        if (statement == null)
            return null;
        Artifacts artifacts = EcoreUtil2.getContainerOfType(statement, Artifacts.class);

        List<String> vals = Utils.getTokensFromModifier(statement, Constants.TABLE_USAGE);
        for (String val : vals) {
            TableDefinition tableDefinition = modelProperty.getModelTables(artifacts).get(val);
            if (tableDefinition != null)
                return new Pair(artifacts, tableDefinition);
        }

        return null;
    }

    protected String toCamelCase(String value) {
        if (value == null)
            return null;
        String[] parts = value.split("_");
        String camelCaseString = "";
        for (String part : parts) {
            if (camelCaseString.length() == 0)
                camelCaseString = camelCaseString + part.toLowerCase();
            else if (part.length() == 1)
                camelCaseString = camelCaseString + part.toUpperCase();
            else if (part.length() > 1)
                camelCaseString = camelCaseString + part.substring(0, 1).toUpperCase()
                        + part.substring(1).toLowerCase();
        }
        return camelCaseString;
    }

    protected String toCamelCase(Class<?> clazz) {
        if (clazz == null)
            return null;
        String camelCaseString = clazz.getSimpleName();
        if (camelCaseString.length() == 1)
            camelCaseString = camelCaseString.toLowerCase();
        else if (camelCaseString.length() > 1)
            camelCaseString = camelCaseString.substring(0, 1).toLowerCase() + camelCaseString.substring(1);
        return camelCaseString;
    }

    protected String getPojoColumns(List<String> columns) {
        if (columns == null)
            return null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            builder.append(':').append(toCamelCase(columns.get(i)));
            if (i < columns.size() - 1)
                builder.append(", ");
        }
        return builder.toString();
    }

    protected String getSelectColumns(List<String> columns) {
        if (columns == null)
            return null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            builder.append("%" + columns.get(i)).append(" @").append(toCamelCase(columns.get(i)));
            if (i < columns.size() - 1)
                builder.append(", ");
        }
        return builder.toString();
    }

    protected String getInsertColumns(List<String> columns) {
        if (columns == null)
            return null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            builder.append("%" + columns.get(i));
            if (i < columns.size() - 1)
                builder.append(", ");
        }
        return builder.toString();
    }

    protected String getUpdateColumns(List<String> columns) {
        if (columns == null)
            return null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            builder.append("%" + columns.get(i)).append(" = :").append(toCamelCase(columns.get(i)));
            if (i < columns.size() - 1)
                builder.append(", ");
        }
        return builder.toString();
    }

    protected String getCondColumns(List<String> columns) {
        if (columns == null)
            return null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            builder.append("\n  {& ").append("%" + columns.get(i)).append(" = :").append(toCamelCase(columns.get(i)))
                    .append(" }");
        }
        return builder.toString();
    }

    protected String getVerUpdateColumns(List<String> columns) {
        if (columns == null)
            return null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).toUpperCase().indexOf("VER") == 0) {
                builder.append("%" + columns.get(i)).append(" = :").append(toCamelCase(columns.get(i)))
                        .append(" + 1, ");
                break;
            }
        }
        return builder.toString();
    }

    protected String getOptUpdateColumns(List<String> columns) {
        if (columns == null)
            return null;
        boolean idFind = false;
        boolean verFind = false;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            if (!idFind && (columns.get(i).toUpperCase().equals("ID")
                    || columns.get(i).toUpperCase().indexOf("ID_") == 0)) {
                idFind = true;
                continue;
            }
            if (!verFind && columns.get(i).toUpperCase().indexOf("VER") == 0) {
                verFind = true;
                continue;
            }
            builder.append("%" + columns.get(i)).append(" = :").append(toCamelCase(columns.get(i)));
            if (i < columns.size() - 1)
                builder.append(", ");
        }
        return builder.toString();
    }

    protected String getOptCondColumns(List<String> columns) {
        if (columns == null)
            return null;
        boolean idFind = false;
        boolean verFind = false;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            if (!idFind && (columns.get(i).toUpperCase().equals("ID")
                    || columns.get(i).toUpperCase().indexOf("ID_") == 0)) {
                idFind = true;
                builder.append("\n  {& ").append("%" + columns.get(i)).append(" = :")
                        .append(toCamelCase(columns.get(i))).append(" }");
                continue;
            }
            if (!verFind && columns.get(i).toUpperCase().indexOf("VER") == 0) {
                verFind = true;
                builder.append("\n  {& ").append("%" + columns.get(i)).append(" = :")
                        .append(toCamelCase(columns.get(i))).append(" }");
                continue;
            }
        }
        return builder.toString();
    }

    protected String getPojoDefinitions(List<Class<?>> pojoClasses) {
        if (pojoClasses == null)
            return null;
        TreeMap<String, String> map = new TreeMap<String, String>();
        for (Class<?> clazz : pojoClasses) {
            map.put(toCamelCase(clazz), clazz.getName());
        }

        StringBuilder builder = new StringBuilder();
        for (Entry<String, String> pojo : map.entrySet()) {
            builder.append("pojo ").append(Strings.toFirstUpper(pojo.getKey())).append(' ').append(pojo.getValue())
                    .append(";\n");
        }
        return builder.toString();
    }

    /*
     * Template variable resolvers
     */
    public class DbTableResolver extends SimpleTemplateVariableResolver {

        public static final String NAME = "dbTable";

        public DbTableResolver() {
            super(NAME, "DbTable");
        }

        @Override
        protected String resolve(TemplateContext context) {
            Pair<Artifacts, TableDefinition> pair = getTableDefinition(
                    getMetaStatement((XtextTemplateContext) context));
            if (pair != null && dbResolver.isResolveDb(pair.getFirst())) {
                return "%%" + pair.getSecond().getTable();
            }
            return super.resolve(context);
        }

        @Override
        protected boolean isUnambiguous(TemplateContext context) {
            return true;
        }
    }

    public class DbSelectColumnResolver extends SimpleTemplateVariableResolver {

        public static final String NAME = "dbSelectColumn";

        public DbSelectColumnResolver() {
            super(NAME, "DbSelectColumn");
        }

        @Override
        protected String resolve(TemplateContext context) {
            Pair<Artifacts, TableDefinition> pair = getTableDefinition(
                    getMetaStatement((XtextTemplateContext) context));
            if (pair != null && dbResolver.isResolveDb(pair.getFirst())) {
                List<String> dbColumns = dbResolver.getColumns(pair.getFirst(), pair.getSecond().getTable());
                return getSelectColumns(dbColumns);
            }
            return super.resolve(context);
        }

        @Override
        protected boolean isUnambiguous(TemplateContext context) {
            return true;
        }
    }

    public class PojoColumnResolver extends SimpleTemplateVariableResolver {

        public static final String NAME = "pojoColumn";

        public PojoColumnResolver() {
            super(NAME, "PojoColumn");
        }

        @Override
        protected String resolve(TemplateContext context) {
            Pair<Artifacts, TableDefinition> pair = getTableDefinition(
                    getMetaStatement((XtextTemplateContext) context));
            if (pair != null && dbResolver.isResolveDb(pair.getFirst())) {
                List<String> dbColumns = dbResolver.getColumns(pair.getFirst(), pair.getSecond().getTable());
                return getPojoColumns(dbColumns);
            }
            return super.resolve(context);
        }

        @Override
        protected boolean isUnambiguous(TemplateContext context) {
            return true;
        }
    }

    public class DbInsertColumnResolver extends SimpleTemplateVariableResolver {

        public static final String NAME = "dbInsertColumn";

        public DbInsertColumnResolver() {
            super(NAME, "DbInsertColumn");
        }

        @Override
        protected String resolve(TemplateContext context) {
            Pair<Artifacts, TableDefinition> pair = getTableDefinition(
                    getMetaStatement((XtextTemplateContext) context));
            if (pair != null && dbResolver.isResolveDb(pair.getFirst())) {
                List<String> dbColumns = dbResolver.getColumns(pair.getFirst(), pair.getSecond().getTable());
                return getInsertColumns(dbColumns);
            }
            return super.resolve(context);
        }

        @Override
        protected boolean isUnambiguous(TemplateContext context) {
            return true;
        }
    }

    public class DbUpdateColumnResolver extends SimpleTemplateVariableResolver {

        public static final String NAME = "dbUpdateColumn";

        public DbUpdateColumnResolver() {
            super(NAME, "DbUpdateColumn");
        }

        @Override
        protected String resolve(TemplateContext context) {
            Pair<Artifacts, TableDefinition> pair = getTableDefinition(
                    getMetaStatement((XtextTemplateContext) context));
            if (pair != null && dbResolver.isResolveDb(pair.getFirst())) {
                List<String> dbColumns = dbResolver.getColumns(pair.getFirst(), pair.getSecond().getTable());
                return getUpdateColumns(dbColumns);
            }
            return super.resolve(context);
        }

        @Override
        protected boolean isUnambiguous(TemplateContext context) {
            return true;
        }
    }

    public class DbCondColumnResolver extends SimpleTemplateVariableResolver {

        public static final String NAME = "dbCondColumn";

        public DbCondColumnResolver() {
            super(NAME, "DbCondColumn");
        }

        @Override
        protected String resolve(TemplateContext context) {
            Pair<Artifacts, TableDefinition> pair = getTableDefinition(
                    getMetaStatement((XtextTemplateContext) context));
            if (pair != null && dbResolver.isResolveDb(pair.getFirst())) {
                List<String> dbColumns = dbResolver.getColumns(pair.getFirst(), pair.getSecond().getTable());
                return getCondColumns(dbColumns);
            }
            return super.resolve(context);
        }

        @Override
        protected boolean isUnambiguous(TemplateContext context) {
            return true;
        }
    }

    public class DbVerUpdateColumnResolver extends SimpleTemplateVariableResolver {

        public static final String NAME = "dbVerUpdateColumn";

        public DbVerUpdateColumnResolver() {
            super(NAME, "DbVerUpdateColumn");
        }

        @Override
        protected String resolve(TemplateContext context) {
            Pair<Artifacts, TableDefinition> pair = getTableDefinition(
                    getMetaStatement((XtextTemplateContext) context));
            if (pair != null && dbResolver.isResolveDb(pair.getFirst())) {
                List<String> dbColumns = dbResolver.getColumns(pair.getFirst(), pair.getSecond().getTable());
                return getVerUpdateColumns(dbColumns);
            }
            return super.resolve(context);
        }

        @Override
        protected boolean isUnambiguous(TemplateContext context) {
            return true;
        }
    }

    public class DbOptUpdateColumnResolver extends SimpleTemplateVariableResolver {

        public static final String NAME = "dbOptUpdateColumn";

        public DbOptUpdateColumnResolver() {
            super(NAME, "DbOptUpdateColumn");
        }

        @Override
        protected String resolve(TemplateContext context) {
            Pair<Artifacts, TableDefinition> pair = getTableDefinition(
                    getMetaStatement((XtextTemplateContext) context));
            if (pair != null && dbResolver.isResolveDb(pair.getFirst())) {
                List<String> dbColumns = dbResolver.getColumns(pair.getFirst(), pair.getSecond().getTable());
                return getOptUpdateColumns(dbColumns);
            }
            return super.resolve(context);
        }

        @Override
        protected boolean isUnambiguous(TemplateContext context) {
            return true;
        }
    }

    public class DbOptCondColumnResolver extends SimpleTemplateVariableResolver {

        public static final String NAME = "dbOptCondColumn";

        public DbOptCondColumnResolver() {
            super(NAME, "DbOptCondColumn");
        }

        @Override
        protected String resolve(TemplateContext context) {
            Pair<Artifacts, TableDefinition> pair = getTableDefinition(
                    getMetaStatement((XtextTemplateContext) context));
            if (pair != null && dbResolver.isResolveDb(pair.getFirst())) {
                List<String> dbColumns = dbResolver.getColumns(pair.getFirst(), pair.getSecond().getTable());
                return getOptCondColumns(dbColumns);
            }
            return super.resolve(context);
        }

        @Override
        protected boolean isUnambiguous(TemplateContext context) {
            return true;
        }
    }

    public class PojoDefinitionsResolver extends SimpleTemplateVariableResolver {

        public static final String NAME = "pojoDefinitions";

        public PojoDefinitionsResolver() {
            super(NAME, "PojoDefinitions");
        }

        @Override
        protected String resolve(TemplateContext context) {
            Artifacts artifacts = getArtifacts((XtextTemplateContext) context);
            if (artifacts != null && CommonUtils.isResolvePojo(modelProperty, artifacts)) {
                URI uri = (artifacts.eResource() != null) ? artifacts.eResource().getURI() : null;
                List<Class<?>> pojoClasses = pojoResolver.getPojoClasses(uri);
                return getPojoDefinitions(pojoClasses);
            }
            return super.resolve(context);
        }

        @Override
        protected boolean isUnambiguous(TemplateContext context) {
            return true;
        }
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

    public class MetaGeneratorResolver extends SimpleTemplateVariableResolver {

        public static final String NAME = "metaGenerator";

        public MetaGeneratorResolver() {
            super(NAME, "MetaGenerator");
        }

        @Override
        protected String resolve(TemplateContext context) {
            Artifacts artifacts = getArtifacts((XtextTemplateContext) context);
            Stats stats = new Stats();
            String metas = TableMetaGenerator.generateMeta(artifacts, artifacts.getStatements(), null, dbResolver,
                    scopeProvider, modelProperty, stats);
            if (metas != null) {
                return metas + stats.toString();
            }
            return super.resolve(context);
        }

        @Override
        protected boolean isUnambiguous(TemplateContext context) {
            return true;
        }
    }
}
