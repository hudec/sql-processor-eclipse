package org.sqlproc.model.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.sqlproc.model.processorModel.Artifacts;
import org.sqlproc.model.processorModel.DaogenProperty;
import org.sqlproc.model.processorModel.DatabaseProperty;
import org.sqlproc.model.processorModel.ExportAssignement;
import org.sqlproc.model.processorModel.FunctionDefinitionModel;
import org.sqlproc.model.processorModel.ImportAssignement;
import org.sqlproc.model.processorModel.InheritanceAssignement;
import org.sqlproc.model.processorModel.JoinTableAssignement;
import org.sqlproc.model.processorModel.ManyToManyAssignement;
import org.sqlproc.model.processorModel.MetaSqlTypeAssignement;
import org.sqlproc.model.processorModel.MetaTypeAssignement;
import org.sqlproc.model.processorModel.MetagenProperty;
import org.sqlproc.model.processorModel.PojoDefinitionModel;
import org.sqlproc.model.processorModel.PojogenProperty;
import org.sqlproc.model.processorModel.ProcedureDefinitionModel;
import org.sqlproc.model.processorModel.Property;
import org.sqlproc.model.processorModel.PropertyCondition;
import org.sqlproc.model.processorModel.TableDefinitionModel;
import org.sqlproc.model.util.Utils;
import org.sqlproc.plugin.lib.property.ModelProperty;
import org.sqlproc.plugin.lib.property.PairValues;
import org.sqlproc.plugin.lib.property.PojoAttrType;
import org.sqlproc.plugin.lib.util.CommonUtils;

import com.google.inject.Singleton;

@Singleton
public class ModelPropertyBean extends ModelProperty {

    public ModelPropertyBean() {
        super();
    }

    public ModelPropertyBean(ModelValues modelValues) {
        super(modelValues);
    }

    public void notifyChanged(Notification msg) {
        if (msg.getNotifier() == null || msg.getFeatureID(Resource.class) == Notification.NO_FEATURE_ID)
            return;

        if (msg.getNotifier() instanceof XtextResource) {
            int featureID = msg.getFeatureID(Resource.class);

            if (featureID == Resource.RESOURCE__IS_LOADED) {
                XtextResource resource = (XtextResource) msg.getNotifier();

                if (!msg.getNewBooleanValue()) {
                    LOGGER.debug("UNLOADED RESOURCE " + resource);
                    return;
                }

                IParseResult parseResult = resource.getParseResult();
                EObject rootASTElement = (parseResult != null) ? parseResult.getRootASTElement() : null;
                LOGGER.debug("LOADED RESOURCE " + resource + " for " + rootASTElement);
                if (parseResult == null || rootASTElement == null || !(rootASTElement instanceof Artifacts)
                        || resource.getURI() == null) {
                    LOGGER.error("LOADED RESOURCE IS NOT VALID: for parseResult " + parseResult + " and rootASTElement "
                            + rootASTElement + " and msg " + msg);
                    return;
                }
                String dir = CommonUtils.resourceDir(resource);
                if (dir == null) {
                    LOGGER.error("LOADED RESOURCE URI IS NOT VALID " + resource.getURI());
                    return;
                }

                ModelValues modelValues = null;
                if (dirs2models.containsKey(dir)) {
                    modelValues = dirs2models.get(dir);
                } else {
                    modelValues = new ModelValues();
                    dirs2models.put(dir, modelValues);
                    modelValues.dir = dir;
                }

                Artifacts artifacts = (Artifacts) rootASTElement;
                if ((modelValues = loadModel(modelValues, artifacts)) == null)
                    return;

                LOGGER.debug("defaultAttrs = " + modelValues.defaultAttrs);
                LOGGER.debug("conditionalAttrs = " + modelValues.conditionalAttrs);
                LOGGER.debug("systemEnvAttrs = " + modelValues.systemEnvAttrs);

                LOGGER.debug("MODEL " + modelValues.toString());
            }
            // This is obsolete, just to document the possibilities
            // } else if (msg.getNotifier() instanceof Artifacts) {
            // if (msg.getFeature() instanceof EReference
            // && ((EReference) msg.getFeature()).getName().equals("properties")) {
            //
            // Property oldValue = (Property) msg.getOldValue();
            // Property newValue = (Property) msg.getNewValue();
            // Artifacts artifacts = (Artifacts) newValue.eContainer();
            //
            // if (msg.getEventType() == Notification.ADD) {
            // addValue(artifacts, newValue);
            // } else if (msg.getEventType() == Notification.REMOVE) {
            // resetValue(artifacts, newValue);
            // } else if (msg.getEventType() == Notification.SET) {
            // setValue(artifacts, newValue);
            // } else {
            // LOGGER.warn("UNNOWN PROPERTY ACTION " + msg);
            // }
            // // LOGGER.debug("PROPERTY " + ((newValue != null) ? newValue.getName() : "null"));
            // return;
            // }
        }
    }

    public static boolean isNull(PropertyCondition condition) {
        if (condition == null || condition.getName() == null || condition.getValue() == null)
            return true;
        return false;
    }

    public static boolean isValid(PropertyCondition condition) {
        if (condition == null || condition.getName() == null || condition.getValue() == null)
            return false;
        String envValue = System.getenv(condition.getName());
        String propValue = Utils.getPropertyValue(condition.getValue());
        if (envValue == null || propValue == null || !envValue.equals(propValue))
            return false;
        return true;
    }

    public static ModelValues loadModel(ModelValues modelValues, Artifacts artifacts) {

        if (artifacts.getProperties().isEmpty())
            return null;
        if (modelValues == null)
            modelValues = new ModelValues();

        modelValues.initModel();

        boolean firstDatabase = true;
        boolean firstPojogen = true;
        boolean firstMetagen = true;
        boolean firstDaogen = true;
        boolean firstModel = true;
        try {
            for (Property property : artifacts.getProperties()) {
                PropertyCondition condition = property.getCondition();

                if (property.getName().startsWith(DATABASE)) {
                    if (firstDatabase) {
                        firstDatabase = false;
                        modelValues.initDatabaseModel();
                    }
                    if (!isNull(condition))
                        continue;
                    setValue(modelValues, property.getDatabase());
                    modelValues.defaultAttrs.get(DATABASE).add(property.getDatabase().getName());
                } else if (property.getName().startsWith(POJOGEN)) {
                    if (firstPojogen) {
                        firstPojogen = false;
                        modelValues.initPojogenModel();
                    }
                    if (!isNull(condition))
                        continue;
                    setValue(modelValues, property.getPojogen());
                    modelValues.defaultAttrs.get(POJOGEN).add(property.getPojogen().getName());
                } else if (property.getName().startsWith(METAGEN)) {
                    if (firstMetagen) {
                        firstMetagen = false;
                        modelValues.initMetagenModel();
                    }
                    if (!isNull(condition))
                        continue;
                    setValue(modelValues, property.getMetagen());
                    modelValues.defaultAttrs.get(METAGEN).add(property.getMetagen().getName());
                } else if (property.getName().startsWith(DAOGEN)) {
                    if (firstDaogen) {
                        firstDaogen = false;
                        modelValues.initDaogenModel();
                    }
                    if (!isNull(condition))
                        continue;
                    setValue(modelValues, property.getDaogen());
                    modelValues.defaultAttrs.get(DAOGEN).add(property.getDaogen().getName());
                } else {
                    if (!isNull(condition))
                        continue;
                    setValue(modelValues, property);
                    modelValues.defaultAttrs.get(STANDARD).add(property.getName());
                }
            }

            for (PojoDefinitionModel pojo : artifacts.getPojos()) {
                if (firstModel) {
                    firstModel = false;
                    modelValues.initModelModel();
                }
                modelValues.modelPojos.put(pojo.getName(),
                        new PojoDefinitionImpl(pojo.getName(), pojo.getClass_(), pojo.getClassx()));
            }
            for (TableDefinitionModel table : artifacts.getTables()) {
                if (firstModel) {
                    firstModel = false;
                    modelValues.initModelModel();
                }
                modelValues.modelTables.put(table.getName(),
                        new TableDefinitionImpl(table.getName(), table.getTable()));
            }
            for (ProcedureDefinitionModel procedure : artifacts.getProcedures()) {
                if (firstModel) {
                    firstModel = false;
                    modelValues.initModelModel();
                }
                modelValues.modelProcedures.put(procedure.getName(),
                        new ProcedureDefinitionImpl(procedure.getName(), procedure.getTable()));
            }
            for (FunctionDefinitionModel function : artifacts.getFunctions()) {
                if (firstModel) {
                    firstModel = false;
                    modelValues.initModelModel();
                }
                modelValues.modelFunctions.put(function.getName(),
                        new FunctionDefinitionImpl(function.getName(), function.getTable()));
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        try {
            modelValues.setStandardValuesFromEnv();
            modelValues.setDatabaseValuesFromEnv();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        try {
            for (Property property : artifacts.getProperties()) {
                PropertyCondition condition = property.getCondition();
                boolean condIsValid = isValid(condition);

                if (property.getName().startsWith(DATABASE)) {
                    if (condIsValid) {
                        setValue(modelValues, property.getDatabase());
                        modelValues.conditionalAttrs.get(DATABASE).add(property.getDatabase().getName());
                    }
                } else if (property.getName().startsWith(POJOGEN)) {
                    if (condIsValid) {
                        setValue(modelValues, property.getPojogen());
                        modelValues.conditionalAttrs.get(POJOGEN).add(property.getPojogen().getName());
                    }
                } else if (property.getName().startsWith(METAGEN)) {
                    if (condIsValid) {
                        setValue(modelValues, property.getMetagen());
                        modelValues.conditionalAttrs.get(METAGEN).add(property.getMetagen().getName());
                    }
                } else if (property.getName().startsWith(DAOGEN)) {
                    if (condIsValid) {
                        setValue(modelValues, property.getDaogen());
                        modelValues.conditionalAttrs.get(DAOGEN).add(property.getDaogen().getName());
                    }
                } else {
                    if (condIsValid) {
                        setValue(modelValues, property);
                        modelValues.conditionalAttrs.get(STANDARD).add(property.getName());
                    }
                }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return modelValues;
    }

    public static void setValue(ModelValues modelValues, Property property) {
        if (property == null)
            return;
        if (RESOLVE_POJO_ON.equals(property.getName())) {
            modelValues.doResolvePojo = true;
        } else if (RESOLVE_POJO_OFF.equals(property.getName())) {
            modelValues.doResolvePojo = false;
        } else if (REPLACE_ALL_REGEX.equals(property.getName())) {
            if (property.getRegex() != null && property.getReplaceId() != null) {
                modelValues.replaceAllRegex.put(property.getReplaceId(),
                        Utils.getPropertyValueRegex(property.getRegex()));
            }
        } else if (REPLACE_ALL_REPLACEMENT.equals(property.getName())) {
            if (property.getReplacement() != null && property.getReplaceId() != null) {
                modelValues.replaceAllReplacement.put(property.getReplaceId(),
                        Utils.getPropertyValueRegex(property.getReplacement()));
            }
        } else if (REPLACE_TEXT.equals(property.getName())) {
            if (property.getReplacement() != null && property.getRegex() != null) {
                String uuid = UUID.randomUUID().toString();
                modelValues.replaceAllRegex.put(uuid, Utils.getPropertyValueRegex(property.getRegex()));
                modelValues.replaceAllReplacement.put(uuid, Utils.getPropertyValueRegex(property.getReplacement()));
            }
        } else if (COMPRESS_META_DIRECTIVES.equals(property.getName())) {
            modelValues.doCompressMetaDirectives = true;
        } else if (GENERATE_FROM_TO.equals(property.getName())) {
            modelValues.doGenerateFromTo = true;
        } else if (CASE_FORMAT_LIBRARY.equals(property.getName())) {
            modelValues.caseFormatLibrary = Utils.getPropertyValue(property.getCaseFormatLibrary());
        } else if (VALIDATE_RESOURCES.equals(property.getName())) {
            if (property.getDoVerifyResources() != null) {
                for (int i = 0, m = property.getDoVerifyResources().size(); i < m; i++) {
                    modelValues.doVerifyResources.add(Utils.getPropertyValue(property.getDoVerifyResources().get(i)));
                }
            }
            if (property.getDoNotVerifyResources() != null) {
                for (int i = 0, m = property.getDoNotVerifyResources().size(); i < m; i++) {
                    modelValues.doNotVerifyResources
                            .add(Utils.getPropertyValue(property.getDoNotVerifyResources().get(i)));
                }
            }
        }
    }

    public static void setValue(ModelValues modelValues, DatabaseProperty property) {
        if (property == null)
            return;
        if (DATABASE_IS_ONLINE.equals(property.getName())) {
            modelValues.doResolveDb = true;
        } else if (DATABASE_IS_OFFLINE.equals(property.getName())) {
            modelValues.doResolveDb = false;
        } else if (DATABASE_HAS_URL.equals(property.getName())) {
            modelValues.dbUrl = Utils.getPropertyValue(property.getDbUrl());
        } else if (DATABASE_LOGIN_USERNAME.equals(property.getName())) {
            modelValues.dbUsername = Utils.getPropertyValue(property.getDbUsername());
        } else if (DATABASE_LOGIN_PASSWORD.equals(property.getName())) {
            modelValues.dbPassword = Utils.getPropertyValue(property.getDbPassword());
        } else if (DATABASE_IN_CATALOG.equals(property.getName())) {
            if (property.getDbCatalog() != null)
                modelValues.dbCatalog = Utils.getPropertyValue(property.getDbCatalog().getDbCatalog());
            else
                modelValues.dbCatalog = null;
        } else if (DATABASE_ACTIVE_SCHEMA.equals(property.getName())) {
            if (property.getDbSchema() != null)
                modelValues.dbSchema = Utils.getPropertyValue(property.getDbSchema().getDbSchema());
            else
                modelValues.dbSchema = null;
        } else if (DATABASE_JDBC_DRIVER.equals(property.getName())) {
            modelValues.dbDriver = Utils.getPropertyValue(property.getDbDriver());
        } else if (DATABASE_EXECUTE_BEFORE.equals(property.getName())) {
            modelValues.dbSqlsBefore = Utils.getPropertyValue(property.getDbExecuteBefore());
        } else if (DATABASE_EXECUTE_AFTER.equals(property.getName())) {
            modelValues.dbSqlsAfter = Utils.getPropertyValue(property.getDbExecuteAfter());
        } else if (DATABASE_INDEX_TYPES.equals(property.getName())) {
            modelValues.dbIndexTypes = Utils.getPropertyValue(property.getDbIndexTypes());
        } else if (DATABASE_SKIP_INDEXES.equals(property.getName())) {
            modelValues.dbSkipIndexes = true;
        } else if (DATABASE_SKIP_PROCEDURES.equals(property.getName())) {
            modelValues.dbSkipProcedures = true;
        } else if (DATABASE_SKIP_CHECK_CONSTRAINTS.equals(property.getName())) {
            modelValues.dbSkipCheckConstraints = true;
        } else if (DATABASE_OF_TYPE.equals(property.getName())) {
            if (property.getDbType() != null)
                modelValues.dbType = Utils.getPropertyValue(property.getDbType().getDbType());
            else
                modelValues.dbType = null;
        } else if (DATABASE_DEBUG_LEVEL.equals(property.getName()) && property.getDebug() != null) {
            modelValues.dbDebugLevel = property.getDebug().getDebug();
            modelValues.dbDebugScope = Utils.getPropertyValue(property.getDebug().getScope());
        } else if (DATABASE_TAKE_COMMENTS.equals(property.getName())) {
            modelValues.dbTakeComments = true;
        } else if (DATABASE_LOWERCASE_NAMES.equals(property.getName())) {
            modelValues.dbLowercaseNames = true;
        } else if (DATABASE_UPPERCASE_NAMES.equals(property.getName())) {
            modelValues.dbUppercaseNames = true;
        }
    }

    private static void setValue(ModelValues modelValues, PojogenProperty property) {
        if (property == null)
            return;
        if (POJOGEN_TYPE_SQLTYPES.equals(property.getName())) {
            for (int i = 0, m = property.getSqlTypes().size(); i < m; i++) {
                String sqlType = Utils.getPropertyValue(property.getSqlTypes().get(i).getSqlType());
                if (property.getSqlTypes().get(i).getSqlSize() != 0)
                    sqlType = sqlType + "(" + property.getSqlTypes().get(i).getSqlSize() + ")";
                PojoAttrTypeImpl type = new PojoAttrTypeImpl(null, sqlType, property.getSqlTypes().get(i).getType());
                modelValues.sqlTypes.put(sqlType, type);
            }
        } else if (POJOGEN_TYPE_IN_TABLE.equals(property.getName())) {
            if (!modelValues.tableTypes.containsKey(property.getDbTable()))
                modelValues.tableTypes.put(property.getDbTable(), new HashMap<String, PojoAttrType>());
            for (int i = 0, m = property.getSqlTypes().size(); i < m; i++) {
                String sqlType = Utils.getPropertyValue(property.getSqlTypes().get(i).getSqlType());
                if (property.getSqlTypes().get(i).getSqlSize() != 0)
                    sqlType = sqlType + "(" + property.getSqlTypes().get(i).getSqlSize() + ")";
                PojoAttrTypeImpl type = new PojoAttrTypeImpl(null, sqlType, property.getSqlTypes().get(i).getType());
                modelValues.tableTypes.get(property.getDbTable()).put(sqlType, type);
            }
        } else if (POJOGEN_TYPE_FOR_COLUMNS.equals(property.getName())) {
            if (!modelValues.columnTypes.containsKey(property.getDbTable()))
                modelValues.columnTypes.put(property.getDbTable(), new HashMap<String, PojoAttrType>());
            for (int i = 0, m = property.getColumnTypes().size(); i < m; i++) {
                PojoAttrTypeImpl type = new PojoAttrTypeImpl(property.getColumnTypes().get(i).getDbColumn(), null,
                        property.getColumnTypes().get(i).getType());
                modelValues.columnTypes.get(property.getDbTable()).put(property.getColumnTypes().get(i).getDbColumn(),
                        type);
            }
        } else if (POJOGEN_TYPE_FOR_PROCEDURE.equals(property.getName())) {
            if (!modelValues.procedureTypes.containsKey(property.getDbProcedure()))
                modelValues.procedureTypes.put(property.getDbProcedure(), new HashMap<String, PojoAttrType>());
            for (int i = 0, m = property.getColumnTypes().size(); i < m; i++) {
                PojoAttrTypeImpl type = new PojoAttrTypeImpl(property.getColumnTypes().get(i).getDbColumn(), null,
                        property.getColumnTypes().get(i).getType());
                modelValues.procedureTypes.get(property.getDbProcedure())
                        .put(property.getColumnTypes().get(i).getDbColumn(), type);
            }
        } else if (POJOGEN_TYPE_FOR_FUNCTION.equals(property.getName())) {
            if (!modelValues.functionTypes.containsKey(property.getDbFunction()))
                modelValues.functionTypes.put(property.getDbFunction(), new HashMap<String, PojoAttrType>());
            for (int i = 0, m = property.getColumnTypes().size(); i < m; i++) {
                PojoAttrTypeImpl type = new PojoAttrTypeImpl(property.getColumnTypes().get(i).getDbColumn(), null,
                        property.getColumnTypes().get(i).getType());
                modelValues.functionTypes.get(property.getDbFunction())
                        .put(property.getColumnTypes().get(i).getDbColumn(), type);
            }
        } else if (POJOGEN_RENAME_TABLES.equals(property.getName())) {
            for (int i = 0, m = property.getTables().size(); i < m; i++) {
                modelValues.tableNames.put(property.getTables().get(i).getDbTable(),
                        property.getTables().get(i).getNewName());
            }
        } else if (POJOGEN_RENAME_COLUMNS.equals(property.getName())) {
            if (!modelValues.columnNames.containsKey(property.getDbTable()))
                modelValues.columnNames.put(property.getDbTable(), new HashMap<String, String>());
            for (int i = 0, m = property.getColumns().size(); i < m; i++) {
                modelValues.columnNames.get(property.getDbTable()).put(property.getColumns().get(i).getDbColumn(),
                        property.getColumns().get(i).getNewName());
            }
        } else if (POJOGEN_IGNORE_TABLES.equals(property.getName())) {
            for (int i = 0, m = property.getDbTables().size(); i < m; i++) {
                modelValues.ignoreTables.add(property.getDbTables().get(i));
            }
        } else if (POJOGEN_ONLY_TABLES.equals(property.getName())) {
            for (int i = 0, m = property.getDbTables().size(); i < m; i++) {
                modelValues.onlyTables.add(property.getDbTables().get(i));
            }
        } else if (POJOGEN_NOT_ABSTRACT_TABLES_TABLES.equals(property.getName())) {
            for (int i = 0, m = property.getDbTables().size(); i < m; i++) {
                modelValues.notAbstractTables.add(property.getDbTables().get(i));
            }
        } else if (POJOGEN_CREATE_TABLES.equals(property.getName())) {
            for (int i = 0, m = property.getDbTables().size(); i < m; i++) {
                modelValues.createTables.add(property.getDbTables().get(i));
            }
        } else if (POJOGEN_IGNORE_COLUMNS.equals(property.getName())) {
            if (!modelValues.ignoreColumns.containsKey(property.getDbTable()))
                modelValues.ignoreColumns.put(property.getDbTable(), new HashSet<String>());
            for (int i = 0, m = property.getDbColumns().size(); i < m; i++) {
                modelValues.ignoreColumns.get(property.getDbTable()).add(property.getDbColumns().get(i));
            }
        } else if (POJOGEN_REQUIRED_COLUMNS.equals(property.getName())) {
            if (!modelValues.requiredColumns.containsKey(property.getDbTable()))
                modelValues.requiredColumns.put(property.getDbTable(), new HashSet<String>());
            for (int i = 0, m = property.getDbColumns().size(); i < m; i++) {
                modelValues.requiredColumns.get(property.getDbTable()).add(property.getDbColumns().get(i));
            }
        } else if (POJOGEN_NOT_REQUIRED_COLUMNS.equals(property.getName())) {
            if (!modelValues.notRequiredColumns.containsKey(property.getDbTable()))
                modelValues.notRequiredColumns.put(property.getDbTable(), new HashSet<String>());
            for (int i = 0, m = property.getDbColumns().size(); i < m; i++) {
                modelValues.notRequiredColumns.get(property.getDbTable()).add(property.getDbColumns().get(i));
            }
        } else if (POJOGEN_CREATE_COLUMNS.equals(property.getName())) {
            if (!modelValues.createColumns.containsKey(property.getDbTable()))
                modelValues.createColumns.put(property.getDbTable(), new HashMap<String, PojoAttrType>());
            for (int i = 0, m = property.getColumnTypes().size(); i < m; i++) {
                PojoAttrTypeImpl type = new PojoAttrTypeImpl(property.getColumnTypes().get(i).getDbColumn(), null,
                        property.getColumnTypes().get(i).getType());
                modelValues.createColumns.get(property.getDbTable()).put(property.getColumnTypes().get(i).getDbColumn(),
                        type);
            }
        } else if (POJOGEN_IGNORE_EXPORTS.equals(property.getName())) {
            if (!modelValues.ignoreExports.containsKey(property.getDbTable()))
                modelValues.ignoreExports.put(property.getDbTable(), new HashMap<String, Map<String, String>>());
            Map<String, Map<String, String>> exports = modelValues.ignoreExports.get(property.getDbTable());
            for (int i = 0, m = property.getExports().size(); i < m; i++) {
                ExportAssignement export = property.getExports().get(i);
                if (!exports.containsKey(export.getDbColumn()))
                    exports.put(export.getDbColumn(), new HashMap<String, String>());
                exports.get(export.getDbColumn()).put(export.getFkTable(), export.getFkColumn());
            }
        } else if (POJOGEN_IGNORE_IMPORTS.equals(property.getName())) {
            if (!modelValues.ignoreImports.containsKey(property.getDbTable()))
                modelValues.ignoreImports.put(property.getDbTable(), new HashMap<String, Map<String, String>>());
            Map<String, Map<String, String>> imports = modelValues.ignoreImports.get(property.getDbTable());
            for (int i = 0, m = property.getImports().size(); i < m; i++) {
                ImportAssignement _import = property.getImports().get(i);
                if (!imports.containsKey(_import.getDbColumn()))
                    imports.put(_import.getDbColumn(), new HashMap<String, String>());
                imports.get(_import.getDbColumn()).put(_import.getPkTable(), _import.getPkColumn());
            }
        } else if (POJOGEN_CREATE_EXPORTS.equals(property.getName())) {
            if (!modelValues.createExports.containsKey(property.getDbTable()))
                modelValues.createExports.put(property.getDbTable(), new HashMap<String, Map<String, String>>());
            Map<String, Map<String, String>> exports = modelValues.createExports.get(property.getDbTable());
            for (int i = 0, m = property.getExports().size(); i < m; i++) {
                ExportAssignement export = property.getExports().get(i);
                if (!exports.containsKey(export.getDbColumn()))
                    exports.put(export.getDbColumn(), new HashMap<String, String>());
                exports.get(export.getDbColumn()).put(export.getFkTable(), export.getFkColumn());
            }
        } else if (POJOGEN_CREATE_IMPORTS.equals(property.getName())) {
            if (!modelValues.createImports.containsKey(property.getDbTable()))
                modelValues.createImports.put(property.getDbTable(), new HashMap<String, Map<String, String>>());
            Map<String, Map<String, String>> imports = modelValues.createImports.get(property.getDbTable());
            for (int i = 0, m = property.getImports().size(); i < m; i++) {
                ImportAssignement _import = property.getImports().get(i);
                if (!imports.containsKey(_import.getDbColumn()))
                    imports.put(_import.getDbColumn(), new HashMap<String, String>());
                imports.get(_import.getDbColumn()).put(_import.getPkTable(), _import.getPkColumn());
            }
        } else if (POJOGEN_CREATE_121_IMPORTS.equals(property.getName())) {
            // pojogen-ignore-one-to-many PERSON ID->PERSON_DETAIL->ID;
            // pojogen-ignore-many-to-one PERSON_DETAIL ID->PERSON->ID;
            // pojogen-association-one-to-one PERSON ID->PERSON_DETAIL->ID;
            if (!modelValues.create121Imports.containsKey(property.getDbTable()))
                modelValues.create121Imports.put(property.getDbTable(), new HashMap<String, Map<String, String>>());
            Map<String, Map<String, String>> imports = modelValues.create121Imports.get(property.getDbTable());
            if (!modelValues.ignoreExports.containsKey(property.getDbTable()))
                modelValues.ignoreExports.put(property.getDbTable(), new HashMap<String, Map<String, String>>());
            Map<String, Map<String, String>> exports = modelValues.ignoreExports.get(property.getDbTable());
            for (int i = 0, m = property.getImports().size(); i < m; i++) {
                ImportAssignement _import = property.getImports().get(i);
                if (!imports.containsKey(_import.getDbColumn()))
                    imports.put(_import.getDbColumn(), new HashMap<String, String>());
                imports.get(_import.getDbColumn()).put(_import.getPkTable(), _import.getPkColumn());
                if (!exports.containsKey(_import.getDbColumn()))
                    exports.put(_import.getDbColumn(), new HashMap<String, String>());
                exports.get(_import.getDbColumn()).put(_import.getPkTable(), _import.getPkColumn());
                if (!modelValues.ignoreImports.containsKey(_import.getPkTable()))
                    modelValues.ignoreImports.put(_import.getPkTable(), new HashMap<String, Map<String, String>>());
                Map<String, Map<String, String>> _imports = modelValues.ignoreImports.get(_import.getPkTable());
                if (!_imports.containsKey(_import.getPkColumn()))
                    _imports.put(_import.getPkColumn(), new HashMap<String, String>());
                _imports.get(_import.getPkColumn()).put(property.getDbTable(), _import.getDbColumn());
                if (!modelValues.create121Imports.containsKey(_import.getPkTable()))
                    modelValues.create121Imports.put(_import.getPkTable(), new HashMap<String, Map<String, String>>());
                Map<String, Map<String, String>> _imports121 = modelValues.create121Imports.get(_import.getPkTable());
                if (!_imports121.containsKey(_import.getPkColumn()))
                    _imports121.put(_import.getPkColumn(), new HashMap<String, String>());
                _imports121.get(_import.getPkColumn()).put(property.getDbTable(), _import.getDbColumn());
            }
        } else if (POJOGEN_INHERIT_IMPORTS.equals(property.getName())) {
            if (!modelValues.inheritImports.containsKey(property.getDbTable()))
                modelValues.inheritImports.put(property.getDbTable(), new HashMap<String, Map<String, String>>());
            Map<String, Map<String, String>> imports = modelValues.inheritImports.get(property.getDbTable());
            for (int i = 0, m = property.getImports().size(); i < m; i++) {
                ImportAssignement _import = property.getImports().get(i);
                if (!imports.containsKey(_import.getDbColumn()))
                    imports.put(_import.getDbColumn(), new HashMap<String, String>());
                imports.get(_import.getDbColumn()).put(_import.getPkTable(), _import.getPkColumn());
            }
        } else if (POJOGEN_MANY_TO_MANY_IMPORTS.equals(property.getName())) {
            if (!modelValues.manyToManyImports.containsKey(property.getDbTable()))
                modelValues.manyToManyImports.put(property.getDbTable(), new HashMap<String, Map<String, String>>());
            Map<String, Map<String, String>> many2s = modelValues.manyToManyImports.get(property.getDbTable());
            for (int i = 0, m = property.getMany2s().size(); i < m; i++) {
                ManyToManyAssignement many2 = property.getMany2s().get(i);
                if (!many2s.containsKey(many2.getPkColumn()))
                    many2s.put(many2.getPkColumn(), new HashMap<String, String>());
                many2s.get(many2.getPkColumn()).put(many2.getPkTable(), many2.getFkColumn());
            }
        } else if (POJOGEN_INHERITANCE.equals(property.getName())) {
            if (!modelValues.inheritance.containsKey(property.getDbTable()))
                modelValues.inheritance.put(property.getDbTable(), new HashMap<String, Map<String, List<String>>>());
            modelValues.inheritanceColumns.put(property.getDbTable(), property.getDbColumn());
            Map<String, Map<String, List<String>>> inherits = modelValues.inheritance.get(property.getDbTable());
            for (int i = 0, m = property.getInheritance().size(); i < m; i++) {
                InheritanceAssignement _inherit = property.getInheritance().get(i);
                if (!inherits.containsKey(_inherit.getDiscriminator()))
                    inherits.put(_inherit.getDiscriminator(), new HashMap<String, List<String>>());
                inherits.get(_inherit.getDiscriminator()).put(_inherit.getDbTable(), _inherit.getDbColumns());
            }
        } else if (POJOGEN_GENERATE_METHODS.equals(property.getName())) {
            for (int i = 0, m = property.getMethods().size(); i < m; i++) {
                modelValues.generateMethods.add(property.getMethods().get(i));
            }
        } else if (POJOGEN_GENERATE_OPERATORS.equals(property.getName())) {
            modelValues.generateOperators = Utils.getPropertyValue(property.getOperatorsSuffix());
            if (modelValues.generateOperators == null)
                modelValues.generateOperators = "operators";
        } else if (POJOGEN_IMPLEMENTS_INTERFACES.equals(property.getName())) {
            ImplementsExtendsImpl ie = new ImplementsExtendsImpl(property.getToImplements().getToImplement(), false,
                    property.getToImplements().getDbTables(), property.getToImplements().getDbNotTables());
            modelValues.toImplements.put(ie.getIdentifier(), ie);
        } else if (POJOGEN_EXTENDS_CLASS.equals(property.getName())) {
            ImplementsExtendsImpl ie = new ImplementsExtendsImpl(property.getToExtends().getToExtends(), false,
                    property.getToExtends().getDbTables(), property.getToExtends().getDbNotTables());
            modelValues.toExtends = ie;
        } else if (POJOGEN_IMPLEMENTS_INTERFACES_GENERICS.equals(property.getName())) {
            ImplementsExtendsImpl ie = new ImplementsExtendsImpl(property.getToImplementsGenerics().getToImplement(),
                    true, property.getToImplementsGenerics().getDbTables(),
                    property.getToImplementsGenerics().getDbNotTables());
            modelValues.toImplements.put(ie.getIdentifier(), ie);
        } else if (POJOGEN_EXTENDS_CLASS_GENERICS.equals(property.getName())) {
            ImplementsExtendsImpl ie = new ImplementsExtendsImpl(property.getToExtendsGenerics().getToExtends(), true,
                    property.getToExtendsGenerics().getDbTables(), property.getToExtendsGenerics().getDbNotTables());
            modelValues.toExtends = ie;
        } else if (POJOGEN_JOIN_TABLES.equals(property.getName())) {
            for (int i = 0, m = property.getJoinTables().size(); i < m; i++) {
                JoinTableAssignement joinTableAssignement = property.getJoinTables().get(i);
                if (!modelValues.joinTables.containsKey(joinTableAssignement.getDbTable()))
                    modelValues.joinTables.put(joinTableAssignement.getDbTable(), new ArrayList<String>());
                for (String dbTable : joinTableAssignement.getDbTables()) {
                    modelValues.joinTables.get(joinTableAssignement.getDbTable()).add(dbTable);
                }
            }
        } else if (POJOGEN_GENERATE_WRAPPERS.equals(property.getName())) {
            modelValues.doGenerateWrappers = true;
        } else if (POJOGEN_GENERATE_VALIDATION_ANNOTATIONS.equals(property.getName())) {
            modelValues.doGenerateValidationAnnotations = true;
        } else if (POJOGEN_MAKE_IT_FINAL.equals(property.getName())) {
            modelValues.makeItFinal = true;
        } else if (POJOGEN_VERSION_COLUMN.equals(property.getName())) {
            String versionColumn = property.getVersion();
            if ((property.getDbTables() == null || property.getDbTables().isEmpty())
                    && (property.getDbNotTables() == null || property.getDbNotTables().isEmpty())) {
                modelValues.versionColumn = versionColumn;
            } else if (property.getDbTables() != null && !property.getDbTables().isEmpty()) {
                if (!modelValues.versionColumns.containsKey(versionColumn))
                    modelValues.versionColumns.put(versionColumn, new HashSet<String>());
                for (int i = 0, m = property.getDbTables().size(); i < m; i++) {
                    modelValues.versionColumns.get(versionColumn).add(property.getDbTables().get(i));
                }
            } else {
                if (!modelValues.notVersionColumns.containsKey(versionColumn))
                    modelValues.notVersionColumns.put(versionColumn, new HashSet<String>());
                for (int i = 0, m = property.getDbNotTables().size(); i < m; i++) {
                    modelValues.notVersionColumns.get(versionColumn).add(property.getDbNotTables().get(i));
                }
            }
        } else if (POJOGEN_DEBUG_LEVEL.equals(property.getName()) && property.getDebug().getDebug() != null) {
            modelValues.debugLevel = property.getDebug().getDebug();
            modelValues.debugScope = Utils.getPropertyValue(property.getDebug().getScope());
        } else if (POJOGEN_PRESERVE_FOREIGN_KEYS.equals(property.getName())) {
            if (property.getDbTables().isEmpty()) {
                modelValues.preserveForeignKeys.add("_ALL_");
            } else {
                for (int i = 0, m = property.getDbTables().size(); i < m; i++) {
                    modelValues.preserveForeignKeys.add(property.getDbTables().get(i));
                }
            }
        } else if (POJOGEN_POJOS_FOR_PROCEDURES.equals(property.getName())) {
            for (int i = 0, m = property.getProcPojos().size(); i < m; i++) {
                modelValues.pojosForProcedures.put(property.getProcPojos().get(i).getDbProcedure(),
                        new PojoEntityTypeImpl(property.getProcPojos().get(i).getPojo()));
            }
        } else if (POJOGEN_POJOS_FOR_FUNCTIONS.equals(property.getName())) {
            for (int i = 0, m = property.getFunPojos().size(); i < m; i++) {
                modelValues.pojosForFunctions.put(property.getFunPojos().get(i).getDbFunction(),
                        new PojoEntityTypeImpl(property.getFunPojos().get(i).getPojo()));
            }
        } else if (POJOGEN_ACTIVE_FILTER.equals(property.getName())) {
            modelValues.activeFilter = Utils.getPropertyValue(property.getActiveFilter());
        } else if (POJOGEN_PACKAGE.equals(property.getName())) {
            modelValues.pckg = Utils.getPropertyValue(property.getPckg());
        } else if (POJOGEN_ENUM_FOR_CHECK_CONSTRAINTS.equals(property.getName())) {
            for (int i = 0, m = property.getDbCheckConstraints().size(); i < m; i++) {
                if (i == 0)
                    modelValues.enumForCheckConstraints.put(property.getEnumName(),
                            property.getDbCheckConstraints().get(i));
                modelValues.enumForCheckConstraints.put(property.getDbCheckConstraints().get(i),
                        property.getEnumName());
            }
        }
    }

    private static void setValue(ModelValues modelValues, MetagenProperty property) {
        if (property == null)
            return;
        if (METAGEN_GLOBAL_IDENTITY.equals(property.getName())) {
            modelValues.metaGlobalIdentity = new PairValues(property.getIdentity(), property.getType());
            if (property.getDbTables() != null) {
                modelValues.metaGlobalIdentityForTables.addAll(property.getDbTables());
            }
            if (property.getDbNotTables() != null) {
                modelValues.metaGlobalIdentityNotForTables.addAll(property.getDbNotTables());
            }
        } else if (METAGEN_TABLE_IDENTITY.equals(property.getName())) {
            modelValues.metaTablesIdentity.put(property.getDbTable(),
                    new PairValues(property.getIdentity(), property.getType()));
        } else if (METAGEN_GLOBAL_SEQUENCE.equals(property.getName())) {
            modelValues.metaGlobalSequence = new PairValues(property.getSequence(), property.getType());
            if (property.getDbTables() != null) {
                modelValues.metaGlobalSequenceForTables.addAll(property.getDbTables());
            }
            if (property.getDbNotTables() != null) {
                modelValues.metaGlobalSequenceNotForTables.addAll(property.getDbNotTables());
            }
        } else if (METAGEN_TABLE_SEQUENCE.equals(property.getName())) {
            modelValues.metaTablesSequence.put(property.getDbTable(),
                    new PairValues(property.getSequence(), property.getType()));
        } else if (METAGEN_SQLTYPE_META_TYPE.equals(property.getName())) {
            for (int i = 0, m = property.getSqlTypes().size(); i < m; i++) {
                String sqlType = Utils.getPropertyValue(property.getSqlTypes().get(i).getSqlType());
                MetaSqlTypeAssignement metaType = property.getSqlTypes().get(i);
                modelValues.metaSqlTypes.put(sqlType, new PairValues(metaType.getType(), metaType.getExtension()));
            }
        } else if (METAGEN_COLUMN_META_TYPE.equals(property.getName())) {
            if (!modelValues.metaColumnsMetaTypes.containsKey(property.getDbTable()))
                modelValues.metaColumnsMetaTypes.put(property.getDbTable(), new HashMap<String, PairValues>());
            for (int i = 0, m = property.getMetaTypes().size(); i < m; i++) {
                MetaTypeAssignement metaType = property.getMetaTypes().get(i);
                modelValues.metaColumnsMetaTypes.get(property.getDbTable()).put(metaType.getDbColumn(),
                        new PairValues(metaType.getType(), metaType.getExtension()));
            }
        } else if (METAGEN_STATEMENT_META_TYPE.equals(property.getName())) {
            if (!modelValues.metaStatementsMetaTypes.containsKey(property.getDbStatement()))
                modelValues.metaStatementsMetaTypes.put(property.getDbStatement(), new HashMap<String, PairValues>());
            for (int i = 0, m = property.getMetaTypes().size(); i < m; i++) {
                MetaTypeAssignement metaType = property.getMetaTypes().get(i);
                modelValues.metaStatementsMetaTypes.get(property.getDbStatement()).put(metaType.getDbColumn(),
                        new PairValues(metaType.getType(), metaType.getExtension()));
            }
        } else if (METAGEN_MAKE_IT_FINAL.equals(property.getName())) {
            modelValues.metaMakeItFinal = true;
        } else if (METAGEN_LIKE_COLUMNS.equals(property.getName())) {
            if (property.getDbTable() == null) {
                modelValues.metaLikeColumns.put(STANDARD, new HashSet<String>());
            } else {
                if (!modelValues.metaLikeColumns.containsKey(property.getDbTable()))
                    modelValues.metaLikeColumns.put(property.getDbTable(), new HashSet<String>());
                for (int i = 0, m = property.getDbColumns().size(); i < m; i++) {
                    modelValues.metaLikeColumns.get(property.getDbTable()).add(property.getDbColumns().get(i));
                }
            }
        } else if (METAGEN_NOT_LIKE_COLUMNS.equals(property.getName())) {
            if (property.getDbTable() == null) {
                modelValues.metaNotLikeColumns.put(STANDARD, new HashSet<String>());
            } else {
                if (!modelValues.metaNotLikeColumns.containsKey(property.getDbTable()))
                    modelValues.metaNotLikeColumns.put(property.getDbTable(), new HashSet<String>());
                for (int i = 0, m = property.getDbColumns().size(); i < m; i++) {
                    modelValues.metaNotLikeColumns.get(property.getDbTable()).add(property.getDbColumns().get(i));
                }
            }
        } else if (METAGEN_GENERATE_SEQUENCES.equals(property.getName())) {
            modelValues.metaGenerateSequences = true;
        } else if (METAGEN_GENERATE_IDENTITIES.equals(property.getName())) {
            modelValues.metaGenerateIdentities = true;
        } else if (METAGEN_GENERATE_IDGENERATORS.equals(property.getName())) {
            modelValues.metaGenerateIdGenerators = true;
        } else if (METAGEN_GENERATE_INDIRECT_IDGENERATORS.equals(property.getName())) {
            modelValues.metaGenerateIndirectIdGenerators = true;
        } else if (METAGEN_FUNCTION_RESULT.equals(property.getName())) {
            modelValues.metaFunctionsResult.put(property.getDbFunction(), property.getType());
        } else if (METAGEN_FUNCTION_RESULT_SET.equals(property.getName())) {
            modelValues.metaFunctionsResultSet.put(property.getDbFunction(), property.getDbTable());
        } else if (METAGEN_PROCEDURE_RESULT_SET.equals(property.getName())) {
            modelValues.metaProceduresResultSet.put(property.getDbProcedure(), property.getDbTable());
        } else if (METAGEN_DEBUG_LEVEL.equals(property.getName()) && property.getDebug() != null) {
            modelValues.metaDebugLevel = property.getDebug().getDebug();
            modelValues.metaDebugScope = Utils.getPropertyValue(property.getDebug().getScope());
        } else if (METAGEN_GENERATE_OPERATORS.equals(property.getName())) {
            modelValues.metaGenerateOperators = true;
        } else if (METAGEN_OPTIMIZE_INSERT.equals(property.getName())) {
            if (property.getDbTables().isEmpty()) {
                modelValues.metaOptimizeInsert.add("_ALL_");
            } else {
                for (int i = 0, m = property.getDbTables().size(); i < m; i++) {
                    modelValues.metaOptimizeInsert.add(property.getDbTables().get(i));
                }
            }
        } else if (METAGEN_OPTIONAL_FEATURES.equals(property.getName())) {
            if (!modelValues.metaOptionalFeatures.containsKey(property.getDbStatement()))
                modelValues.metaOptionalFeatures.put(property.getDbStatement(), new HashSet<String>());
            for (int i = 0, m = property.getOptionalFeatures().size(); i < m; i++) {
                String optionalFeature = Utils.getPropertyValue(property.getOptionalFeatures().get(i));
                modelValues.metaOptionalFeatures.get(property.getDbStatement()).add(optionalFeature);
            }
        } else if (METAGEN_ACTIVE_FILTER.equals(property.getName())) {
            modelValues.metaActiveFilter = Utils.getPropertyValue(property.getActiveFilter());
        } else if (METAGEN_INSERT_SKIP_DEFAULT_VALUES.equals(property.getName())) {
            modelValues.metaInsertSkipDefaultValues = true;
            if (property.getDbTables() != null && !property.getDbTables().isEmpty()) {
                for (int i = 0, m = property.getDbTables().size(); i < m; i++) {
                    modelValues.metaInsertSkipDefaultValuesPos.add(property.getDbTables().get(i));
                }
            } else if (property.getDbNotTables() != null && !property.getDbNotTables().isEmpty()) {
                for (int i = 0, m = property.getDbNotTables().size(); i < m; i++) {
                    modelValues.metaInsertSkipDefaultValuesNeg.add(property.getDbNotTables().get(i));
                }
            }
        }
    }

    private static void setValue(ModelValues modelValues, DaogenProperty property) {
        if (property == null)
            return;
        if (DAOGEN_IGNORE_TABLES.equals(property.getName())) {
            for (int i = 0, m = property.getDbTables().size(); i < m; i++) {
                modelValues.daoIgnoreTables.add(property.getDbTables().get(i));
            }
        } else if (DAOGEN_ONLY_TABLES.equals(property.getName())) {
            for (int i = 0, m = property.getDbTables().size(); i < m; i++) {
                modelValues.daoOnlyTables.add(property.getDbTables().get(i));
            }
        } else if (DAOGEN_IMPLEMENTS_INTERFACES.equals(property.getName())) {
            ImplementsExtendsImpl ie = new ImplementsExtendsImpl(property.getToImplements().getToImplement(), false,
                    property.getToImplements().getDbTables(), property.getToImplements().getDbNotTables());
            modelValues.daoToImplements.put(ie.getIdentifier(), ie);
        } else if (DAOGEN_EXTENDS_CLASS.equals(property.getName())) {
            ImplementsExtendsImpl ie = new ImplementsExtendsImpl(property.getToExtends().getToExtends(), false,
                    property.getToExtends().getDbTables(), property.getToExtends().getDbNotTables());
            modelValues.daoToExtends = ie;
        } else if (DAOGEN_IMPLEMENTS_INTERFACES_GENERICS.equals(property.getName())) {
            ImplementsExtendsImpl ie = new ImplementsExtendsImpl(property.getToImplementsGenerics().getToImplement(),
                    true, property.getToImplementsGenerics().getDbTables(),
                    property.getToImplementsGenerics().getDbNotTables());
            modelValues.daoToImplements.put(ie.getIdentifier(), ie);
        } else if (DAOGEN_EXTENDS_CLASS_GENERICS.equals(property.getName())) {
            ImplementsExtendsImpl ie = new ImplementsExtendsImpl(property.getToExtendsGenerics().getToExtends(), true,
                    property.getToExtendsGenerics().getDbTables(), property.getToExtendsGenerics().getDbNotTables());
            modelValues.daoToExtends = ie;
        } else if (DAOGEN_MAKE_IT_FINAL.equals(property.getName())) {
            modelValues.daoMakeItFinal = true;
        } else if (DAOGEN_FUNCTION_RESULT.equals(property.getName())) {
            modelValues.daoFunctionsResult.put(property.getDbFunction(),
                    new PojoEntityTypeImpl(property.getResultType()));
        } else if (DAOGEN_DEBUG_LEVEL.equals(property.getName()) && property.getDebug().getDebug() != null) {
            modelValues.daoDebugLevel = property.getDebug().getDebug();
            modelValues.daoDebugScope = Utils.getPropertyValue(property.getDebug().getScope());
        } else if (DAOGEN_ACTIVE_FILTER.equals(property.getName())) {
            modelValues.daoActiveFilter = Utils.getPropertyValue(property.getActiveFilter());
        } else if (DAOGEN_PACKAGE.equals(property.getName())) {
            modelValues.daoPckg = Utils.getPropertyValue(property.getPckg());
        } else if (DAOGEN_IMPLEMENTATION_PACKAGE.equals(property.getName())) {
            modelValues.daoImplPckg = Utils.getPropertyValue(property.getImplPckg());
        }
    }

    @Override
    public ModelValues getModelValues(EObject model) {
        if (this.modelValues != null)
            return this.modelValues;

        EObject emodel = (EObject) model;
        Artifacts artifacts = EcoreUtil2.getContainerOfType(emodel, Artifacts.class);
        if (artifacts == null) {
            LOGGER.error("UKNOWN ARTIFACTS FOR " + emodel);
            return null;
        }
        if (artifacts.eResource() == null) {
            LOGGER.error("UKNOWN RESOURCE FOR " + artifacts);
            return null;
        }
        String dir = CommonUtils.resourceDir(artifacts.eResource());
        if (dir == null) {
            LOGGER.error("LOADED RESOURCE URI IS NOT VALID " + artifacts.eResource().getURI());
            return null;
        }
        return dirs2models.get(dir);
    }
}
