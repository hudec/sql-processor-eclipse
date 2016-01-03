package org.sqlproc.dsl.property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.sqlproc.dsl.processorDsl.Artifacts;
import org.sqlproc.dsl.processorDsl.DaogenProperty;
import org.sqlproc.dsl.processorDsl.DatabaseProperty;
import org.sqlproc.dsl.processorDsl.ExportAssignement;
import org.sqlproc.dsl.processorDsl.ImportAssignement;
import org.sqlproc.dsl.processorDsl.InheritanceAssignement;
import org.sqlproc.dsl.processorDsl.JoinTableAssignement;
import org.sqlproc.dsl.processorDsl.ManyToManyAssignement;
import org.sqlproc.dsl.processorDsl.MetaTypeAssignement;
import org.sqlproc.dsl.processorDsl.MetagenProperty;
import org.sqlproc.dsl.processorDsl.PojoType;
import org.sqlproc.dsl.processorDsl.PojogenProperty;
import org.sqlproc.dsl.processorDsl.Property;
import org.sqlproc.dsl.util.Utils;

import com.google.inject.Singleton;

@Singleton
public class ModelPropertyBean extends AdapterImpl implements ModelProperty {

    protected Logger LOGGER = Logger.getLogger(ModelPropertyBean.class);

    public static final String RESOLVE_POJO_ON = "resolve-pojo-on";
    public static final String RESOLVE_POJO_OFF = "resolve-pojo-off";
    public static final String REPLACE_ALL_REGEX = "replace-all-regex";
    public static final String REPLACE_ALL_REPLACEMENT = "replace-all-replacement";
    public static final String COMPRESS_META_DIRECTIVES = "compress-meta-directives";
    public static final String DATABASE = "database";
    public static final String DATABASE_IS_ONLINE = "is-online";
    public static final String DATABASE_IS_OFFLINE = "is-offline";
    public static final String DATABASE_HAS_URL = "has-url";
    public static final String DATABASE_EXECUTE_BEFORE = "ddl-create";
    public static final String DATABASE_EXECUTE_AFTER = "ddl-drop";
    public static final String DATABASE_LOGIN_USERNAME = "login-username";
    public static final String DATABASE_LOGIN_NPASSWORD = "login-password";
    public static final String DATABASE_IN_CATALOG = "in-catalog";
    public static final String DATABASE_ACTIVE_SCHEMA = "active-schema";
    public static final String DATABASE_JDBC_DRIVER = "jdbc-driver";
    public static final String DATABASE_INDEX_TYPES = "index-types";
    public static final String DATABASE_SKIP_INDEXES = "skip-indexes";
    public static final String DATABASE_SKIP_CHECK_CONSTRAINTS = "skip-check-constraints";
    public static final String DATABASE_SKIP_PROCEDURES = "skip-functions-procedures";
    public static final String DATABASE_OF_TYPE = "is-of-type";
    public static final String DATABASE_DEBUG_LEVEL = "debug-level";
    public static final String DATABASE_TAKE_COMMENTS = "take-comments";
    public static final String DATABASE_LOWERCASE_NAMES = "lowercase-names";
    public static final String DATABASE_UPPERCASE_NAMES = "uppercase-names";
    public static final String POJOGEN = "pojogen";
    public static final String POJOGEN_TYPE_SQLTYPES = "types-sqltypes";
    public static final String POJOGEN_TYPE_IN_TABLE = "types-in-table";
    public static final String POJOGEN_TYPE_FOR_COLUMNS = "types-for-columns";
    public static final String POJOGEN_TYPE_FOR_PROCEDURE = "types-for-procedure";
    public static final String POJOGEN_TYPE_FOR_FUNCTION = "types-for-function";
    public static final String POJOGEN_IGNORE_TABLES = "ignore-tables";
    public static final String POJOGEN_ONLY_TABLES = "only-tables";
    public static final String POJOGEN_IGNORE_COLUMNS = "ignore-columns";
    public static final String POJOGEN_REQUIRED_COLUMNS = "required-columns";
    public static final String POJOGEN_NOT_REQUIRED_COLUMNS = "not-required-columns";
    public static final String POJOGEN_CREATE_COLUMNS = "create-columns";
    public static final String POJOGEN_RENAME_TABLES = "rename-tables";
    public static final String POJOGEN_RENAME_COLUMNS = "rename-columns";
    public static final String POJOGEN_IGNORE_EXPORTS = "ignore-one-to-many";
    public static final String POJOGEN_IGNORE_IMPORTS = "ignore-many-to-one";
    public static final String POJOGEN_CREATE_EXPORTS = "create-one-to-many";
    public static final String POJOGEN_CREATE_IMPORTS = "create-many-to-one";
    public static final String POJOGEN_INHERIT_IMPORTS = "inherit-many-to-one";
    public static final String POJOGEN_MANY_TO_MANY_IMPORTS = "table-many-to-many";
    public static final String POJOGEN_INHERITANCE = "inherit-discriminator";
    public static final String POJOGEN_GENERATE_METHODS = "generate-methods";
    public static final String POJOGEN_GENERATE_OPERATORS = "generate-operators";
    public static final String POJOGEN_IMPLEMENTS_INTERFACES = "implements-interfaces";
    public static final String POJOGEN_EXTENDS_CLASS = "extends-class";
    public static final String POJOGEN_IMPLEMENTS_INTERFACES_GENERICS = "implements-interfaces-generics";
    public static final String POJOGEN_EXTENDS_CLASS_GENERICS = "extends-class-generics";
    public static final String POJOGEN_JOIN_TABLES = "join-tables";
    public static final String POJOGEN_GENERATE_WRAPPERS = "generate-wrappers";
    public static final String POJOGEN_GENERATE_VALIDATION_ANNOTATIONS = "generate-validation-annotations";
    public static final String POJOGEN_NOT_ABSTRACT_TABLES_TABLES = "not-abstract-tables";
    public static final String POJOGEN_MAKE_IT_FINAL = "make-it-final";
    public static final String POJOGEN_VERSION_COLUMN = "version-column";
    public static final String POJOGEN_DEBUG_LEVEL = "debug-level";
    public static final String POJOGEN_PRESERVE_FOREIGN_KEYS = "preserve-foreign-keys";
    public static final String POJOGEN_POJOS_FOR_PROCEDURES = "pojos-for-procedures";
    public static final String POJOGEN_POJOS_FOR_FUNCTIONS = "pojos-for-functions";
    public static final String POJOGEN_ACTIVE_FILTER = "active-filter";
    public static final String POJOGEN_PACKAGE = "package";
    public static final String POJOGEN_ENUM_FOR_CHECK_CONSTRAINTS = "enum-for-check-constraints";
    public static final String METAGEN = "metagen";
    public static final String METAGEN_GLOBAL_SEQUENCE = "global-sequence";
    public static final String METAGEN_TABLE_SEQUENCE = "table-sequence";
    public static final String METAGEN_GLOBAL_IDENTITY = "global-identity";
    public static final String METAGEN_TABLE_IDENTITY = "table-identity";
    public static final String METAGEN_COLUMN_META_TYPE = "column-meta-type";
    public static final String METAGEN_STATEMENT_META_TYPE = "statement-meta-type";
    public static final String METAGEN_MAKE_IT_FINAL = "make-it-final";
    public static final String METAGEN_LIKE_COLUMNS = "like-columns";
    public static final String METAGEN_NOT_LIKE_COLUMNS = "not-like-columns";
    public static final String METAGEN_GENERATE_SEQUENCES = "generate-sequences";
    public static final String METAGEN_GENERATE_IDENTITIES = "generate-identities";
    public static final String METAGEN_GENERATE_IDGENERATORS = "generate-idgenerators";
    public static final String METAGEN_GENERATE_INDIRECT_IDGENERATORS = "generate-default-idgenerators";
    public static final String METAGEN_FUNCTION_RESULT = "function-result";
    public static final String METAGEN_FUNCTION_RESULT_SET = "function-result-set";
    public static final String METAGEN_PROCEDURE_RESULT_SET = "procedure-result-set";
    public static final String METAGEN_DEBUG_LEVEL = "debug-level";
    public static final String METAGEN_GENERATE_OPERATORS = "generate-operators";
    public static final String METAGEN_OPTIMIZE_INSERT = "optimize-insert";
    public static final String METAGEN_OPTIONAL_FEATURES = "optional-features";
    public static final String METAGEN_ACTIVE_FILTER = "active-filter";
    public static final String DAOGEN = "daogen";
    public static final String DAOGEN_IGNORE_TABLES = "ignore-tables";
    public static final String DAOGEN_ONLY_TABLES = "only-tables";
    public static final String DAOGEN_IMPLEMENTS_INTERFACES = "implements-interfaces";
    public static final String DAOGEN_EXTENDS_CLASS = "extends-class";
    public static final String DAOGEN_IMPLEMENTS_INTERFACES_GENERICS = "implements-interfaces-generics";
    public static final String DAOGEN_EXTENDS_CLASS_GENERICS = "extends-class-generics";
    public static final String DAOGEN_MAKE_IT_FINAL = "make-it-final";
    public static final String DAOGEN_FUNCTION_RESULT = "function-result";
    public static final String DAOGEN_DEBUG_LEVEL = "debug-level";
    public static final String DAOGEN_ACTIVE_FILTER = "active-filter";
    public static final String DAOGEN_PACKAGE = "package";

    public static final String GLOBAL = "___GLOBAL";

    public static class PairValues {
        public String value1;
        public String value2;

        public PairValues(String value1, String value2) {
            this.value1 = value1;
            this.value2 = value2;
        }
    }

    public static class ModelValues {
        public boolean doResolvePojo;
        public boolean doResolveDb;
        public Map<String, String> replaceAllRegex;
        public Map<String, String> replaceAllReplacement;
        public boolean doCompressMetaDirectives;
        public String dbDriver;
        public String dbUrl;
        public String dbUsername;
        public String dbPassword;
        public String dbCatalog;
        public String dbSchema;
        public String dbSqlsBefore;
        public String dbSqlsAfter;
        public String dbIndexTypes;
        public boolean dbSkipIndexes;
        public boolean dbSkipProcedures;
        public boolean dbSkipCheckConstraints;
        public String dbType;
        public String dbDebugLevel;
        public String dbDebugScope;
        public boolean dbTakeComments;
        public boolean dbLowercaseNames;
        public boolean dbUppercaseNames;
        public String dir;
        public Map<String, PojoAttrType> sqlTypes;
        public Map<String, Map<String, PojoAttrType>> tableTypes;
        public Map<String, Map<String, PojoAttrType>> columnTypes;
        public Map<String, Map<String, PojoAttrType>> procedureTypes;
        public Map<String, Map<String, PojoAttrType>> functionTypes;
        public Map<String, String> tableNames;
        public Map<String, Map<String, String>> columnNames;
        public Set<String> ignoreTables;
        public Set<String> onlyTables;
        public Set<String> notAbstractTables;
        public Map<String, Set<String>> ignoreColumns;
        public Map<String, Set<String>> requiredColumns;
        public Map<String, Set<String>> notRequiredColumns;
        public Map<String, Map<String, PojoAttrType>> createColumns;
        public Map<String, Map<String, Map<String, String>>> ignoreExports;
        public Map<String, Map<String, Map<String, String>>> ignoreImports;
        public Map<String, Map<String, Map<String, String>>> createExports;
        public Map<String, Map<String, Map<String, String>>> createImports;
        public Map<String, Map<String, Map<String, String>>> inheritImports;
        public Map<String, Map<String, Map<String, String>>> manyToManyImports;
        public Map<String, Map<String, Map<String, List<String>>>> inheritance = new HashMap<String, Map<String, Map<String, List<String>>>>();
        public Map<String, String> inheritanceColumns;
        public Set<String> generateMethods;
        public String generateOperators;
        public Map<String, ImplementsExtends> toImplements;
        public ImplementsExtends toExtends;
        public Map<String, List<String>> joinTables;
        public boolean doGenerateWrappers;
        public boolean doGenerateValidationAnnotations;
        public boolean makeItFinal;
        public String versionColumn;
        public Map<String, Set<String>> versionColumns;
        public Map<String, Set<String>> notVersionColumns;
        public String debugLevel;
        public String debugScope;
        public Set<String> preserveForeignKeys;
        public Map<String, PojoType> pojosForProcedures;
        public Map<String, PojoType> pojosForFunctions;
        public String activeFilter;
        public String pckg;
        public Map<String, String> enumForCheckConstraints;

        public PairValues metaGlobalSequence;
        public Map<String, PairValues> metaTablesSequence;
        public PairValues metaGlobalIdentity;
        public Map<String, PairValues> metaTablesIdentity;
        public Map<String, Map<String, PairValues>> metaColumnsMetaTypes;
        public Map<String, Map<String, PairValues>> metaStatementsMetaTypes;
        public boolean metaMakeItFinal;
        public Map<String, Set<String>> metaLikeColumns;
        public Map<String, Set<String>> metaNotLikeColumns;
        public boolean metaGenerateSequences;
        public Set<String> metaGlobalSequenceForTables;
        public Set<String> metaGlobalSequenceNotForTables;
        public boolean metaGenerateIdentities;
        public Set<String> metaGlobalIdentityForTables;
        public Set<String> metaGlobalIdentityNotForTables;
        public boolean metaGenerateIdGenerators;
        public boolean metaGenerateIndirectIdGenerators;
        public Map<String, String> metaFunctionsResult;
        public Map<String, String> metaFunctionsResultSet;
        public Map<String, String> metaProceduresResultSet;
        public String metaDebugLevel;
        public String metaDebugScope;
        public boolean metaGenerateOperators;
        public Set<String> metaOptimizeInsert;
        public Map<String, Set<String>> metaOptionalFeatures;
        public String metaActiveFilter;

        public Set<String> daoIgnoreTables;
        public Set<String> daoOnlyTables;
        public Map<String, ImplementsExtends> daoToImplements;
        public ImplementsExtends daoToExtends;
        public boolean daoMakeItFinal;
        public Map<String, PojoType> daoFunctionsResult;
        public String daoDebugLevel;
        public String daoDebugScope;
        public String daoActiveFilter;
        public String daoPckg;
    }

    private ModelValues modelValues = null;
    private Map<String, ModelValues> dirs2models = new HashMap<String, ModelValues>();

    public ModelPropertyBean() {
    }

    public ModelPropertyBean(ModelValues modelValues) {
        init(modelValues);
    }

    @Override
    public void init(Object modelValues) {
        if (modelValues == null || !(modelValues instanceof ModelValues))
            this.modelValues = null;
        else {
            this.modelValues = (ModelValues) modelValues;
            this.modelValues.dir = "XXX";
        }
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
                    LOGGER.error("LOADED RESOURCE IS NOT VALID: for parseResult " + parseResult
                            + " and rootASTElement " + rootASTElement + " and msg " + msg);
                    return;
                }
                String dir = Utils.resourceDir(resource);
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
                if (loadModel(modelValues, artifacts) == null)
                    return;

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

    public static ModelValues loadModel(ModelValues modelValues, Artifacts artifacts) {

        if (artifacts.getProperties().isEmpty())
            return null;
        if (modelValues == null)
            modelValues = new ModelValues();

        initModel(modelValues);
        boolean reloadDatabase = false;
        for (Property property : artifacts.getProperties()) {
            if (property.getName().startsWith(DATABASE)) {
                reloadDatabase = true;
                break;
            }
        }
        if (reloadDatabase) {
            initDatabaseModel(modelValues);
        }
        boolean reloadPojogen = false;
        for (Property property : artifacts.getProperties()) {
            if (property.getName().startsWith(POJOGEN)) {
                reloadPojogen = true;
                break;
            }
        }
        if (reloadPojogen) {
            initPojogenModel(modelValues);
        }
        boolean reloadMetagen = false;
        for (Property property : artifacts.getProperties()) {
            if (property.getName().startsWith(METAGEN)) {
                reloadMetagen = true;
                break;
            }
        }
        if (reloadMetagen) {
            initMetagenModel(modelValues);
        }
        boolean reloadDaogen = false;
        for (Property property : artifacts.getProperties()) {
            if (property.getName().startsWith(DAOGEN)) {
                reloadDaogen = true;
                break;
            }
        }
        if (reloadDaogen) {
            initDaogenModel(modelValues);
        }
        try {
            for (Property property : artifacts.getProperties()) {
                if (property.getName().startsWith(DATABASE)) {
                    setValue(modelValues, property.getDatabase());
                } else if (property.getName().startsWith(POJOGEN)) {
                    setValue(modelValues, property.getPojogen());
                } else if (property.getName().startsWith(METAGEN)) {
                    setValue(modelValues, property.getMetagen());
                } else if (property.getName().startsWith(DAOGEN)) {
                    setValue(modelValues, property.getDaogen());
                } else {
                    setValue(modelValues, property);
                }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return modelValues;
    }

    private static void initModel(ModelValues modelValues) {
        modelValues.replaceAllRegex = new HashMap<String, String>();
        modelValues.replaceAllReplacement = new HashMap<String, String>();
        modelValues.doCompressMetaDirectives = false;
    }

    private static void initDatabaseModel(ModelValues modelValues) {
        modelValues.doResolveDb = false;
        modelValues.dbDriver = null;
        modelValues.dbUrl = null;
        modelValues.dbUsername = null;
        modelValues.dbPassword = null;
        modelValues.dbCatalog = null;
        modelValues.dbSchema = null;
        modelValues.dbSqlsBefore = null;
        modelValues.dbSqlsAfter = null;
        modelValues.dbIndexTypes = null;
        modelValues.dbSkipIndexes = false;
        modelValues.dbSkipProcedures = false;
        modelValues.dbSkipCheckConstraints = false;
        modelValues.dbType = null;
        modelValues.dbDebugLevel = null;
        modelValues.dbDebugScope = null;
        modelValues.dbTakeComments = false;
        modelValues.dbLowercaseNames = false;
        modelValues.dbUppercaseNames = false;
    }

    private static void initPojogenModel(ModelValues modelValues) {
        modelValues.sqlTypes = new HashMap<String, PojoAttrType>();
        modelValues.tableTypes = new HashMap<String, Map<String, PojoAttrType>>();
        modelValues.columnTypes = new HashMap<String, Map<String, PojoAttrType>>();
        modelValues.procedureTypes = new HashMap<String, Map<String, PojoAttrType>>();
        modelValues.functionTypes = new HashMap<String, Map<String, PojoAttrType>>();
        modelValues.tableNames = new HashMap<String, String>();
        modelValues.columnNames = new HashMap<String, Map<String, String>>();
        modelValues.ignoreTables = new HashSet<String>();
        modelValues.onlyTables = new HashSet<String>();
        modelValues.notAbstractTables = new HashSet<String>();
        modelValues.ignoreColumns = new HashMap<String, Set<String>>();
        modelValues.requiredColumns = new HashMap<String, Set<String>>();
        modelValues.notRequiredColumns = new HashMap<String, Set<String>>();
        modelValues.createColumns = new HashMap<String, Map<String, PojoAttrType>>();
        modelValues.ignoreExports = new HashMap<String, Map<String, Map<String, String>>>();
        modelValues.ignoreImports = new HashMap<String, Map<String, Map<String, String>>>();
        modelValues.createExports = new HashMap<String, Map<String, Map<String, String>>>();
        modelValues.createImports = new HashMap<String, Map<String, Map<String, String>>>();
        modelValues.inheritImports = new HashMap<String, Map<String, Map<String, String>>>();
        modelValues.manyToManyImports = new HashMap<String, Map<String, Map<String, String>>>();
        modelValues.inheritance = new HashMap<String, Map<String, Map<String, List<String>>>>();
        modelValues.inheritanceColumns = new HashMap<String, String>();
        modelValues.generateMethods = new HashSet<String>();
        modelValues.generateOperators = null;
        modelValues.toImplements = new HashMap<String, ImplementsExtends>();
        modelValues.toExtends = null;
        modelValues.joinTables = new HashMap<String, List<String>>();
        modelValues.doGenerateWrappers = false;
        modelValues.doGenerateValidationAnnotations = false;
        modelValues.makeItFinal = false;
        modelValues.versionColumn = null;
        modelValues.versionColumns = new HashMap<String, Set<String>>();
        modelValues.notVersionColumns = new HashMap<String, Set<String>>();
        modelValues.debugLevel = null;
        modelValues.debugScope = null;
        modelValues.preserveForeignKeys = new HashSet<String>();
        modelValues.pojosForProcedures = new HashMap<String, PojoType>();
        modelValues.pojosForFunctions = new HashMap<String, PojoType>();
        modelValues.activeFilter = null;
        modelValues.pckg = null;
        modelValues.enumForCheckConstraints = new HashMap<String, String>();
    }

    private static void initMetagenModel(ModelValues modelValues) {
        modelValues.metaGlobalSequence = null;
        modelValues.metaTablesSequence = new HashMap<String, PairValues>();
        modelValues.metaGlobalIdentity = null;
        modelValues.metaTablesIdentity = new HashMap<String, PairValues>();
        modelValues.metaColumnsMetaTypes = new HashMap<String, Map<String, PairValues>>();
        modelValues.metaStatementsMetaTypes = new HashMap<String, Map<String, PairValues>>();
        modelValues.metaMakeItFinal = false;
        modelValues.metaLikeColumns = new HashMap<String, Set<String>>();
        modelValues.metaNotLikeColumns = new HashMap<String, Set<String>>();
        modelValues.metaGenerateSequences = false;
        modelValues.metaGlobalSequenceForTables = new HashSet<String>();
        modelValues.metaGlobalSequenceNotForTables = new HashSet<String>();
        modelValues.metaGenerateIdentities = false;
        modelValues.metaGlobalIdentityForTables = new HashSet<String>();
        modelValues.metaGlobalIdentityNotForTables = new HashSet<String>();
        modelValues.metaGenerateIdGenerators = false;
        modelValues.metaGenerateIndirectIdGenerators = false;
        modelValues.metaFunctionsResult = new HashMap<String, String>();
        modelValues.metaFunctionsResultSet = new HashMap<String, String>();
        modelValues.metaProceduresResultSet = new HashMap<String, String>();
        modelValues.metaDebugLevel = null;
        modelValues.metaDebugScope = null;
        modelValues.metaGenerateOperators = false;
        modelValues.metaOptimizeInsert = new HashSet<String>();
        modelValues.metaOptionalFeatures = new HashMap<String, Set<String>>();
        modelValues.metaActiveFilter = null;
    }

    private static void initDaogenModel(ModelValues modelValues) {
        modelValues.daoIgnoreTables = new HashSet<String>();
        modelValues.daoOnlyTables = new HashSet<String>();
        modelValues.daoToImplements = new HashMap<String, ImplementsExtends>();
        modelValues.daoToExtends = null;
        modelValues.daoMakeItFinal = false;
        modelValues.daoFunctionsResult = new HashMap<String, PojoType>();
        modelValues.daoDebugLevel = null;
        modelValues.daoDebugScope = null;
        modelValues.daoActiveFilter = null;
        modelValues.daoPckg = null;
    }

    public static void setValue(ModelValues modelValues, Property property) {
        if (property == null)
            return;
        if (RESOLVE_POJO_ON.equals(property.getName())) {
            modelValues.doResolvePojo = true;
        } else if (RESOLVE_POJO_OFF.equals(property.getName())) {
            modelValues.doResolvePojo = false;
        } else if (REPLACE_ALL_REGEX.equals(property.getName())) {
            if (property.getRegex() != null && property.getReplaceId() != null)
                modelValues.replaceAllRegex.put(property.getReplaceId(), property.getRegex());
        } else if (REPLACE_ALL_REPLACEMENT.equals(property.getName())) {
            if (property.getReplacement() != null && property.getReplaceId() != null)
                modelValues.replaceAllReplacement.put(property.getReplaceId(), property.getReplacement());
        } else if (COMPRESS_META_DIRECTIVES.equals(property.getName())) {
            modelValues.doCompressMetaDirectives = true;
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
            modelValues.dbUrl = getPropertyValue(property.getDbUrl());
        } else if (DATABASE_LOGIN_USERNAME.equals(property.getName())) {
            modelValues.dbUsername = getPropertyValue(property.getDbUsername());
        } else if (DATABASE_LOGIN_NPASSWORD.equals(property.getName())) {
            modelValues.dbPassword = getPropertyValue(property.getDbPassword());
        } else if (DATABASE_IN_CATALOG.equals(property.getName())) {
            if (property.getDbCatalog() != null)
                modelValues.dbCatalog = getPropertyValue(property.getDbCatalog().getDbCatalog());
            else
                modelValues.dbCatalog = null;
        } else if (DATABASE_ACTIVE_SCHEMA.equals(property.getName())) {
            if (property.getDbSchema() != null)
                modelValues.dbSchema = getPropertyValue(property.getDbSchema().getDbSchema());
            else
                modelValues.dbSchema = null;
        } else if (DATABASE_JDBC_DRIVER.equals(property.getName())) {
            if (property.getDbDriverx() != null)
                modelValues.dbDriver = getPropertyValue(property.getDbDriverx().getQualifiedName());
            else
                modelValues.dbDriver = getPropertyValue(property.getDbDriver());
        } else if (DATABASE_EXECUTE_BEFORE.equals(property.getName())) {
            modelValues.dbSqlsBefore = getPropertyValue(property.getDbExecuteBefore());
        } else if (DATABASE_EXECUTE_AFTER.equals(property.getName())) {
            modelValues.dbSqlsAfter = getPropertyValue(property.getDbExecuteAfter());
        } else if (DATABASE_INDEX_TYPES.equals(property.getName())) {
            modelValues.dbIndexTypes = getPropertyValue(property.getDbIndexTypes());
        } else if (DATABASE_SKIP_INDEXES.equals(property.getName())) {
            modelValues.dbSkipIndexes = true;
        } else if (DATABASE_SKIP_PROCEDURES.equals(property.getName())) {
            modelValues.dbSkipProcedures = true;
        } else if (DATABASE_SKIP_CHECK_CONSTRAINTS.equals(property.getName())) {
            modelValues.dbSkipCheckConstraints = true;
        } else if (DATABASE_OF_TYPE.equals(property.getName())) {
            if (property.getDbType() != null)
                modelValues.dbType = getPropertyValue(property.getDbType().getDbType());
            else
                modelValues.dbType = null;
        } else if (DATABASE_DEBUG_LEVEL.equals(property.getName()) && property.getDebug() != null) {
            modelValues.dbDebugLevel = property.getDebug().getDebug();
            modelValues.dbDebugScope = property.getDebug().getScope();
        } else if (DATABASE_TAKE_COMMENTS.equals(property.getName())) {
            modelValues.dbTakeComments = true;
        } else if (DATABASE_LOWERCASE_NAMES.equals(property.getName())) {
            modelValues.dbLowercaseNames = true;
        } else if (DATABASE_UPPERCASE_NAMES.equals(property.getName())) {
            modelValues.dbUppercaseNames = true;
        }
    }

    private static String getPropertyValue(String value) {
        if (value == null)
            return null;
        value = value.trim();
        if (value.startsWith("\""))
            value = value.substring(1);
        if (value.endsWith("\""))
            value = value.substring(0, value.length() - 1);
        return value;
    }

    private static void setValue(ModelValues modelValues, PojogenProperty property) {
        if (property == null)
            return;
        if (POJOGEN_TYPE_SQLTYPES.equals(property.getName())) {
            // if (modelValues.sqlTypes == null)
            // modelValues.sqlTypes = new HashMap<String, PojoAttrType>();
            for (int i = 0, m = property.getSqlTypes().size(); i < m; i++) {
                PojoAttrType type = new PojoAttrType(property.getSqlTypes().get(i).getTypeName(), property
                        .getSqlTypes().get(i).getSize(), property.getSqlTypes().get(i).getType());
                modelValues.sqlTypes.put(type.getName() + type.getSize(), type);
            }
        } else if (POJOGEN_TYPE_IN_TABLE.equals(property.getName())) {
            // if (modelValues.tableTypes == null)
            // modelValues.tableTypes = new HashMap<String, Map<String, PojoAttrType>>();
            if (!modelValues.tableTypes.containsKey(property.getDbTable()))
                modelValues.tableTypes.put(property.getDbTable(), new HashMap<String, PojoAttrType>());
            for (int i = 0, m = property.getSqlTypes().size(); i < m; i++) {
                PojoAttrType type = new PojoAttrType(property.getSqlTypes().get(i).getTypeName(), property
                        .getSqlTypes().get(i).getSize(), property.getSqlTypes().get(i).getType());
                modelValues.tableTypes.get(property.getDbTable()).put(type.getName() + type.getSize(), type);
            }
        } else if (POJOGEN_TYPE_FOR_COLUMNS.equals(property.getName())) {
            // if (modelValues.columnTypes == null)
            // modelValues.columnTypes = new HashMap<String, Map<String, PojoAttrType>>();
            if (!modelValues.columnTypes.containsKey(property.getDbTable()))
                modelValues.columnTypes.put(property.getDbTable(), new HashMap<String, PojoAttrType>());
            for (int i = 0, m = property.getColumnTypes().size(); i < m; i++) {
                PojoAttrType type = new PojoAttrType(property.getColumnTypes().get(i).getDbColumn(), null, property
                        .getColumnTypes().get(i).getType());
                modelValues.columnTypes.get(property.getDbTable()).put(type.getName(), type);
            }
        } else if (POJOGEN_TYPE_FOR_PROCEDURE.equals(property.getName())) {
            // if (modelValues.columnTypes == null)
            // modelValues.columnTypes = new HashMap<String, Map<String, PojoAttrType>>();
            if (!modelValues.procedureTypes.containsKey(property.getDbProcedure()))
                modelValues.procedureTypes.put(property.getDbProcedure(), new HashMap<String, PojoAttrType>());
            for (int i = 0, m = property.getColumnTypes().size(); i < m; i++) {
                PojoAttrType type = new PojoAttrType(property.getColumnTypes().get(i).getDbColumn(), null, property
                        .getColumnTypes().get(i).getType());
                modelValues.procedureTypes.get(property.getDbProcedure()).put(type.getName(), type);
            }
        } else if (POJOGEN_TYPE_FOR_FUNCTION.equals(property.getName())) {
            // if (modelValues.columnTypes == null)
            // modelValues.columnTypes = new HashMap<String, Map<String, PojoAttrType>>();
            if (!modelValues.functionTypes.containsKey(property.getDbFunction()))
                modelValues.functionTypes.put(property.getDbFunction(), new HashMap<String, PojoAttrType>());
            for (int i = 0, m = property.getColumnTypes().size(); i < m; i++) {
                PojoAttrType type = new PojoAttrType(property.getColumnTypes().get(i).getDbColumn(), null, property
                        .getColumnTypes().get(i).getType());
                modelValues.functionTypes.get(property.getDbFunction()).put(type.getName(), type);
            }
        } else if (POJOGEN_RENAME_TABLES.equals(property.getName())) {
            // if (modelValues.tableNames == null)
            // modelValues.tableNames = new HashMap<String, String>();
            for (int i = 0, m = property.getTables().size(); i < m; i++) {
                modelValues.tableNames.put(property.getTables().get(i).getDbTable(), property.getTables().get(i)
                        .getNewName());
            }
        } else if (POJOGEN_RENAME_COLUMNS.equals(property.getName())) {
            // if (modelValues.columnNames == null)
            // modelValues.columnNames = new HashMap<String, Map<String, String>>();
            if (!modelValues.columnNames.containsKey(property.getDbTable()))
                modelValues.columnNames.put(property.getDbTable(), new HashMap<String, String>());
            for (int i = 0, m = property.getColumns().size(); i < m; i++) {
                modelValues.columnNames.get(property.getDbTable()).put(property.getColumns().get(i).getDbColumn(),
                        property.getColumns().get(i).getNewName());
            }
        } else if (POJOGEN_IGNORE_TABLES.equals(property.getName())) {
            // if (modelValues.ignoreTables == null)
            // modelValues.ignoreTables = new HashSet<String>();
            for (int i = 0, m = property.getDbTables().size(); i < m; i++) {
                modelValues.ignoreTables.add(property.getDbTables().get(i));
            }
        } else if (POJOGEN_ONLY_TABLES.equals(property.getName())) {
            // if (modelValues.onlyTables == null)
            // modelValues.onlyTables = new HashSet<String>();
            for (int i = 0, m = property.getDbTables().size(); i < m; i++) {
                modelValues.onlyTables.add(property.getDbTables().get(i));
            }
        } else if (POJOGEN_NOT_ABSTRACT_TABLES_TABLES.equals(property.getName())) {
            // if (modelValues.notAbstractTables == null)
            // modelValues.notAbstractTables = new HashSet<String>();
            for (int i = 0, m = property.getDbTables().size(); i < m; i++) {
                modelValues.notAbstractTables.add(property.getDbTables().get(i));
            }
        } else if (POJOGEN_IGNORE_COLUMNS.equals(property.getName())) {
            // if (modelValues.ignoreColumns == null)
            // modelValues.ignoreColumns = new HashMap<String, Set<String>>();
            if (!modelValues.ignoreColumns.containsKey(property.getDbTable()))
                modelValues.ignoreColumns.put(property.getDbTable(), new HashSet<String>());
            for (int i = 0, m = property.getDbColumns().size(); i < m; i++) {
                modelValues.ignoreColumns.get(property.getDbTable()).add(property.getDbColumns().get(i));
            }
        } else if (POJOGEN_REQUIRED_COLUMNS.equals(property.getName())) {
            // if (modelValues.requiredColumns == null)
            // modelValues.requiredColumns = new HashMap<String, Set<String>>();
            if (!modelValues.requiredColumns.containsKey(property.getDbTable()))
                modelValues.requiredColumns.put(property.getDbTable(), new HashSet<String>());
            for (int i = 0, m = property.getDbColumns().size(); i < m; i++) {
                modelValues.requiredColumns.get(property.getDbTable()).add(property.getDbColumns().get(i));
            }
        } else if (POJOGEN_NOT_REQUIRED_COLUMNS.equals(property.getName())) {
            // if (modelValues.notRequiredColumns == null)
            // modelValues.notRequiredColumns = new HashMap<String, Set<String>>();
            if (!modelValues.notRequiredColumns.containsKey(property.getDbTable()))
                modelValues.notRequiredColumns.put(property.getDbTable(), new HashSet<String>());
            for (int i = 0, m = property.getDbColumns().size(); i < m; i++) {
                modelValues.notRequiredColumns.get(property.getDbTable()).add(property.getDbColumns().get(i));
            }
        } else if (POJOGEN_CREATE_COLUMNS.equals(property.getName())) {
            // if (modelValues.createColumns == null)
            // modelValues.createColumns = new HashMap<String, Map<String, PojoAttrType>>();
            if (!modelValues.createColumns.containsKey(property.getDbTable()))
                modelValues.createColumns.put(property.getDbTable(), new HashMap<String, PojoAttrType>());
            for (int i = 0, m = property.getColumnTypes().size(); i < m; i++) {
                PojoAttrType type = new PojoAttrType(property.getColumnTypes().get(i).getDbColumn(), null, property
                        .getColumnTypes().get(i).getType());
                modelValues.createColumns.get(property.getDbTable()).put(type.getName(), type);
            }
        } else if (POJOGEN_IGNORE_EXPORTS.equals(property.getName())) {
            // if (modelValues.ignoreExports == null)
            // modelValues.ignoreExports = new HashMap<String, Map<String, Map<String, String>>>();
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
            // if (modelValues.ignoreImports == null)
            // modelValues.ignoreImports = new HashMap<String, Map<String, Map<String, String>>>();
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
            // if (modelValues.createExports == null)
            // modelValues.createExports = new HashMap<String, Map<String, Map<String, String>>>();
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
            // if (modelValues.createImports == null)
            // modelValues.createImports = new HashMap<String, Map<String, Map<String, String>>>();
            if (!modelValues.createImports.containsKey(property.getDbTable()))
                modelValues.createImports.put(property.getDbTable(), new HashMap<String, Map<String, String>>());
            Map<String, Map<String, String>> imports = modelValues.createImports.get(property.getDbTable());
            for (int i = 0, m = property.getImports().size(); i < m; i++) {
                ImportAssignement _import = property.getImports().get(i);
                if (!imports.containsKey(_import.getDbColumn()))
                    imports.put(_import.getDbColumn(), new HashMap<String, String>());
                imports.get(_import.getDbColumn()).put(_import.getPkTable(), _import.getPkColumn());
            }
        } else if (POJOGEN_INHERIT_IMPORTS.equals(property.getName())) {
            // if (modelValues.inheritImports == null)
            // modelValues.inheritImports = new HashMap<String, Map<String, Map<String, String>>>();
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
            // if (modelValues.manyToManyImports == null)
            // modelValues.manyToManyImports = new HashMap<String, Map<String, Map<String, String>>>();
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
            // if (modelValues.inheritance == null)
            // modelValues.inheritance = new HashMap<String, Map<String, Map<String, List<String>>>>();
            if (!modelValues.inheritance.containsKey(property.getDbTable()))
                modelValues.inheritance.put(property.getDbTable(), new HashMap<String, Map<String, List<String>>>());
            // if (modelValues.inheritanceColumns == null)
            // modelValues.inheritanceColumns = new HashMap<String, String>();
            modelValues.inheritanceColumns.put(property.getDbTable(), property.getDbColumn());
            Map<String, Map<String, List<String>>> inherits = modelValues.inheritance.get(property.getDbTable());
            for (int i = 0, m = property.getInheritance().size(); i < m; i++) {
                InheritanceAssignement _inherit = property.getInheritance().get(i);
                if (!inherits.containsKey(_inherit.getDiscriminator()))
                    inherits.put(_inherit.getDiscriminator(), new HashMap<String, List<String>>());
                inherits.get(_inherit.getDiscriminator()).put(_inherit.getDbTable(), _inherit.getDbColumns());
            }
        } else if (POJOGEN_GENERATE_METHODS.equals(property.getName())) {
            // if (modelValues.generateMethods == null)
            // modelValues.generateMethods = new HashSet<String>();
            for (int i = 0, m = property.getMethods().size(); i < m; i++) {
                modelValues.generateMethods.add(property.getMethods().get(i));
            }
        } else if (POJOGEN_GENERATE_OPERATORS.equals(property.getName())) {
            modelValues.generateOperators = getPropertyValue(property.getOperatorsSuffix());
            if (modelValues.generateOperators == null)
                modelValues.generateOperators = "operators";
        } else if (POJOGEN_IMPLEMENTS_INTERFACES.equals(property.getName())) {
            // if (modelValues.toImplements == null)
            // modelValues.toImplements = new HashMap<String, JvmType>();
            ImplementsExtends ie = new ImplementsExtends(property.getToImplements().getToImplement(), false, property
                    .getToImplements().getDbTables(), property.getToImplements().getDbNotTables());
            modelValues.toImplements.put(property.getToImplements().getToImplement().getIdentifier(), ie);
        } else if (POJOGEN_EXTENDS_CLASS.equals(property.getName())) {
            ImplementsExtends ie = new ImplementsExtends(property.getToExtends().getToExtends(), false, property
                    .getToExtends().getDbTables(), property.getToExtends().getDbNotTables());
            modelValues.toExtends = ie;
        } else if (POJOGEN_IMPLEMENTS_INTERFACES_GENERICS.equals(property.getName())) {
            // if (modelValues.toImplements == null)
            // modelValues.toImplements = new HashMap<String, JvmType>();
            ImplementsExtends ie = new ImplementsExtends(property.getToImplementsGenerics().getToImplement(), true,
                    property.getToImplementsGenerics().getDbTables(), property.getToImplementsGenerics()
                            .getDbNotTables());
            modelValues.toImplements.put(property.getToImplementsGenerics().getToImplement().getIdentifier(), ie);
        } else if (POJOGEN_EXTENDS_CLASS_GENERICS.equals(property.getName())) {
            ImplementsExtends ie = new ImplementsExtends(property.getToExtendsGenerics().getToExtends(), true, property
                    .getToExtendsGenerics().getDbTables(), property.getToExtendsGenerics().getDbNotTables());
            modelValues.toExtends = ie;
        } else if (POJOGEN_JOIN_TABLES.equals(property.getName())) {
            // if (modelValues.joinTables == null)
            // modelValues.joinTables = new HashMap<String, List<String>>();
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
            modelValues.debugScope = property.getDebug().getScope();
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
                modelValues.pojosForProcedures.put(property.getProcPojos().get(i).getDbProcedure(), property
                        .getProcPojos().get(i).getPojo());
            }
        } else if (POJOGEN_POJOS_FOR_FUNCTIONS.equals(property.getName())) {
            for (int i = 0, m = property.getFunPojos().size(); i < m; i++) {
                modelValues.pojosForFunctions.put(property.getFunPojos().get(i).getDbFunction(), property.getFunPojos()
                        .get(i).getPojo());
            }
        } else if (POJOGEN_ACTIVE_FILTER.equals(property.getName())) {
            modelValues.activeFilter = property.getActiveFilter();
        } else if (POJOGEN_PACKAGE.equals(property.getName())) {
            modelValues.pckg = property.getPckg();
        } else if (POJOGEN_ENUM_FOR_CHECK_CONSTRAINTS.equals(property.getName())) {
            for (int i = 0, m = property.getDbCheckConstraints().size(); i < m; i++) {
                if (i == 0)
                    modelValues.enumForCheckConstraints.put(property.getEnumName(), property.getDbCheckConstraints()
                            .get(i));
                modelValues.enumForCheckConstraints
                        .put(property.getDbCheckConstraints().get(i), property.getEnumName());
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
                modelValues.metaLikeColumns.put(GLOBAL, new HashSet<String>());
            } else {
                if (!modelValues.metaLikeColumns.containsKey(property.getDbTable()))
                    modelValues.metaLikeColumns.put(property.getDbTable(), new HashSet<String>());
                for (int i = 0, m = property.getDbColumns().size(); i < m; i++) {
                    modelValues.metaLikeColumns.get(property.getDbTable()).add(property.getDbColumns().get(i));
                }
            }
        } else if (METAGEN_NOT_LIKE_COLUMNS.equals(property.getName())) {
            if (property.getDbTable() == null) {
                modelValues.metaNotLikeColumns.put(GLOBAL, new HashSet<String>());
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
            modelValues.metaDebugScope = property.getDebug().getScope();
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
                String optionalFeature = property.getOptionalFeatures().get(i);
                modelValues.metaOptionalFeatures.get(property.getDbStatement()).add(optionalFeature);
            }
        } else if (METAGEN_ACTIVE_FILTER.equals(property.getName())) {
            modelValues.metaActiveFilter = property.getActiveFilter();
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
            ImplementsExtends ie = new ImplementsExtends(property.getToImplements().getToImplement(), false, property
                    .getToImplements().getDbTables(), property.getToImplements().getDbNotTables());
            modelValues.daoToImplements.put(property.getToImplements().getToImplement().getIdentifier(), ie);
        } else if (DAOGEN_EXTENDS_CLASS.equals(property.getName())) {
            ImplementsExtends ie = new ImplementsExtends(property.getToExtends().getToExtends(), false, property
                    .getToExtends().getDbTables(), property.getToExtends().getDbNotTables());
            modelValues.daoToExtends = ie;
        } else if (DAOGEN_IMPLEMENTS_INTERFACES_GENERICS.equals(property.getName())) {
            ImplementsExtends ie = new ImplementsExtends(property.getToImplementsGenerics().getToImplement(), true,
                    property.getToImplementsGenerics().getDbTables(), property.getToImplementsGenerics()
                            .getDbNotTables());
            modelValues.daoToImplements.put(property.getToImplementsGenerics().getToImplement().getIdentifier(), ie);
        } else if (DAOGEN_EXTENDS_CLASS_GENERICS.equals(property.getName())) {
            ImplementsExtends ie = new ImplementsExtends(property.getToExtendsGenerics().getToExtends(), true, property
                    .getToExtendsGenerics().getDbTables(), property.getToExtendsGenerics().getDbNotTables());
            modelValues.daoToExtends = ie;
        } else if (DAOGEN_MAKE_IT_FINAL.equals(property.getName())) {
            modelValues.daoMakeItFinal = true;
        } else if (DAOGEN_FUNCTION_RESULT.equals(property.getName())) {
            modelValues.daoFunctionsResult.put(property.getDbFunction(), property.getResultType());
        } else if (DAOGEN_DEBUG_LEVEL.equals(property.getName()) && property.getDebug().getDebug() != null) {
            modelValues.daoDebugLevel = property.getDebug().getDebug();
            modelValues.daoDebugScope = property.getDebug().getScope();
        } else if (DAOGEN_ACTIVE_FILTER.equals(property.getName())) {
            modelValues.daoActiveFilter = property.getActiveFilter();
        } else if (DAOGEN_PACKAGE.equals(property.getName())) {
            modelValues.daoPckg = property.getPckg();
        }
    }

    @Override
    public boolean isDoResolvePojo(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.doResolvePojo : false;
    }

    @Override
    public boolean isDoResolveDb(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.doResolveDb : false;
    }

    @Override
    public Map<String, String> getReplaceAll(EObject model) {
        ModelValues modelValues = getModelValues(model);
        if (modelValues == null || modelValues.replaceAllRegex == null || modelValues.replaceAllReplacement == null)
            return Collections.<String, String> emptyMap();
        Map<String, String> result = new HashMap<String, String>();
        for (String replaceId : modelValues.replaceAllRegex.keySet()) {
            if (modelValues.replaceAllReplacement.containsKey(replaceId)) {
                result.put(modelValues.replaceAllRegex.get(replaceId), modelValues.replaceAllReplacement.get(replaceId));
            }
        }
        return result;
    }

    @Override
    public boolean isCompressMetaDirectives(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.doCompressMetaDirectives : false;
    }

    @Override
    public Map<String, PojoAttrType> getSqlTypes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.sqlTypes : Collections.<String, PojoAttrType> emptyMap();
    }

    @Override
    public Map<String, Map<String, PojoAttrType>> getTableTypes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.tableTypes : Collections
                .<String, Map<String, PojoAttrType>> emptyMap();
    }

    @Override
    public Map<String, Map<String, PojoAttrType>> getColumnTypes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.columnTypes : Collections
                .<String, Map<String, PojoAttrType>> emptyMap();
    }

    @Override
    public Map<String, Map<String, PojoAttrType>> getProcedureTypes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.procedureTypes : Collections
                .<String, Map<String, PojoAttrType>> emptyMap();
    }

    @Override
    public Map<String, Map<String, PojoAttrType>> getFunctionTypes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.functionTypes : Collections
                .<String, Map<String, PojoAttrType>> emptyMap();
    }

    @Override
    public Map<String, String> getTableNames(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.tableNames : Collections.<String, String> emptyMap();
    }

    @Override
    public Map<String, Map<String, String>> getColumnNames(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.columnNames : Collections.<String, Map<String, String>> emptyMap();
    }

    @Override
    public Set<String> getIgnoreTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.ignoreTables : Collections.<String> emptySet();
    }

    @Override
    public Set<String> getOnlyTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.onlyTables : Collections.<String> emptySet();
    }

    @Override
    public Set<String> getNotAbstractTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.notAbstractTables : Collections.<String> emptySet();
    }

    @Override
    public Map<String, Set<String>> getIgnoreColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.ignoreColumns : Collections.<String, Set<String>> emptyMap();
    }

    @Override
    public Map<String, Set<String>> getRequiredColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.requiredColumns : Collections.<String, Set<String>> emptyMap();
    }

    @Override
    public Map<String, Set<String>> getNotRequiredColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.notRequiredColumns : Collections.<String, Set<String>> emptyMap();
    }

    @Override
    public Map<String, Map<String, PojoAttrType>> getCreateColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.createColumns : Collections
                .<String, Map<String, PojoAttrType>> emptyMap();
    }

    @Override
    public Map<String, Map<String, Map<String, String>>> getIgnoreExports(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.ignoreExports : Collections
                .<String, Map<String, Map<String, String>>> emptyMap();
    }

    @Override
    public Map<String, Map<String, Map<String, String>>> getIgnoreImports(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.ignoreImports : Collections
                .<String, Map<String, Map<String, String>>> emptyMap();
    }

    @Override
    public Map<String, Map<String, Map<String, String>>> getCreateExports(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.createExports : Collections
                .<String, Map<String, Map<String, String>>> emptyMap();
    }

    @Override
    public Map<String, Map<String, Map<String, String>>> getCreateImports(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.createImports : Collections
                .<String, Map<String, Map<String, String>>> emptyMap();
    }

    @Override
    public Map<String, Map<String, Map<String, String>>> getInheritImports(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.inheritImports : Collections
                .<String, Map<String, Map<String, String>>> emptyMap();
    }

    @Override
    public Map<String, Map<String, Map<String, String>>> getManyToManyImports(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.manyToManyImports : Collections
                .<String, Map<String, Map<String, String>>> emptyMap();
    }

    @Override
    public Map<String, Map<String, Map<String, List<String>>>> getInheritance(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.inheritance : Collections
                .<String, Map<String, Map<String, List<String>>>> emptyMap();
    }

    @Override
    public Map<String, String> getInheritanceColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.inheritanceColumns : Collections.<String, String> emptyMap();
    }

    @Override
    public Set<String> getGenerateMethods(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.generateMethods : Collections.<String> emptySet();
    }

    @Override
    public String getGenerateOperators(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.generateOperators : null;
    }

    @Override
    public Map<String, ImplementsExtends> getToImplements(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.toImplements : Collections.<String, ImplementsExtends> emptyMap();
    }

    @Override
    public ImplementsExtends getToExtends(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.toExtends : null;
    }

    @Override
    public Map<String, List<String>> getJoinTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.joinTables : Collections.<String, List<String>> emptyMap();
    }

    @Override
    public boolean isDoGenerateWrappers(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.doGenerateWrappers : false;
    }

    @Override
    public boolean isDoGenerateValidationAnnotations(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.doGenerateValidationAnnotations : false;
    }

    @Override
    public boolean isMakeItFinal(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.makeItFinal : false;
    }

    @Override
    public String getVersionColumn(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.versionColumn : null;
    }

    @Override
    public Map<String, Set<String>> getVersionColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.versionColumns : Collections.<String, Set<String>> emptyMap();
    }

    @Override
    public Map<String, Set<String>> getNotVersionColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.notVersionColumns : Collections.<String, Set<String>> emptyMap();
    }

    @Override
    public String getDebugLevel(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.debugLevel : null;
    }

    @Override
    public String getDebugScope(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.debugScope : null;
    }

    @Override
    public Set<String> getPreserveForeignKeys(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.preserveForeignKeys : Collections.<String> emptySet();
    }

    @Override
    public PairValues getMetaGlobalIdentity(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGlobalIdentity : null;
    }

    @Override
    public Map<String, PojoType> getPojosForProcedures(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.pojosForProcedures : Collections.<String, PojoType> emptyMap();
    }

    @Override
    public Map<String, PojoType> getPojosForFunctions(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.pojosForFunctions : Collections.<String, PojoType> emptyMap();
    }

    @Override
    public String getActiveFilter(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.activeFilter : null;
    }

    @Override
    public String getPackage(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.pckg : null;
    }

    @Override
    public Map<String, String> getEnumForCheckConstraints(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.enumForCheckConstraints : Collections.<String, String> emptyMap();
    }

    @Override
    public Map<String, PairValues> getMetaTablesIdentity(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaTablesIdentity : Collections.<String, PairValues> emptyMap();
    }

    @Override
    public PairValues getMetaGlobalSequence(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGlobalSequence : null;
    }

    @Override
    public Map<String, PairValues> getMetaTablesSequence(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaTablesSequence : Collections.<String, PairValues> emptyMap();
    }

    @Override
    public Map<String, Map<String, PairValues>> getMetaColumnsMetaTypes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaColumnsMetaTypes : Collections
                .<String, Map<String, PairValues>> emptyMap();
    }

    @Override
    public Map<String, Map<String, PairValues>> getMetaStatementsMetaTypes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaStatementsMetaTypes : Collections
                .<String, Map<String, PairValues>> emptyMap();
    }

    @Override
    public boolean isMetaMakeItFinal(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaMakeItFinal : false;
    }

    @Override
    public Map<String, Set<String>> getMetaLikeColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaLikeColumns : Collections.<String, Set<String>> emptyMap();
    }

    @Override
    public Map<String, Set<String>> getMetaNotLikeColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaNotLikeColumns : Collections.<String, Set<String>> emptyMap();
    }

    @Override
    public boolean isMetaGenerateSequences(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGenerateSequences : false;
    }

    @Override
    public Set<String> getMetaGlobalSequenceForTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGlobalSequenceForTables : Collections.<String> emptySet();
    }

    @Override
    public Set<String> getMetaGlobalSequenceNotForTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGlobalSequenceNotForTables : Collections.<String> emptySet();
    }

    @Override
    public boolean isMetaGenerateIdentities(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGenerateIdentities : false;
    }

    @Override
    public Set<String> getMetaGlobalIdentityForTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGlobalIdentityForTables : Collections.<String> emptySet();
    }

    @Override
    public Set<String> getMetaGlobalIdentityNotForTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGlobalIdentityNotForTables : Collections.<String> emptySet();
    }

    @Override
    public boolean isMetaGenerateIdGenerators(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGenerateIdGenerators : false;
    }

    @Override
    public boolean isMetaGenerateIndirectIdGenerators(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGenerateIndirectIdGenerators : false;
    }

    @Override
    public Map<String, String> getMetaFunctionsResult(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaFunctionsResult : Collections.<String, String> emptyMap();
    }

    @Override
    public Map<String, String> getMetaFunctionsResultSet(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaFunctionsResultSet : Collections.<String, String> emptyMap();
    }

    @Override
    public Map<String, String> getMetaProceduresResultSet(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaProceduresResultSet : Collections.<String, String> emptyMap();
    }

    @Override
    public String getMetaDebugLevel(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaDebugLevel : null;
    }

    @Override
    public String getMetaDebugScope(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaDebugScope : null;
    }

    @Override
    public boolean isMetaGenerateOperators(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGenerateOperators : false;
    }

    @Override
    public Set<String> getMetaOptimizeInsert(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaOptimizeInsert : Collections.<String> emptySet();
    }

    @Override
    public Map<String, Set<String>> getMetaOptionalFeatures(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaOptionalFeatures : Collections.<String, Set<String>> emptyMap();
    }

    @Override
    public String getMetaActiveFilter(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaActiveFilter : null;
    }

    @Override
    public Set<String> getDaoIgnoreTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoIgnoreTables : Collections.<String> emptySet();
    }

    @Override
    public Set<String> getDaoOnlyTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoOnlyTables : Collections.<String> emptySet();
    }

    @Override
    public Map<String, ImplementsExtends> getDaoToImplements(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoToImplements : Collections.<String, ImplementsExtends> emptyMap();
    }

    @Override
    public ImplementsExtends getDaoToExtends(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoToExtends : null;
    }

    @Override
    public boolean isDaoMakeItFinal(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoMakeItFinal : false;
    }

    @Override
    public Map<String, PojoType> getDaoFunctionsResult(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoFunctionsResult : Collections.<String, PojoType> emptyMap();
    }

    @Override
    public String getDaoDebugLevel(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoDebugLevel : null;
    }

    @Override
    public String getDaoDebugScope(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoDebugScope : null;
    }

    @Override
    public String getDaoActiveFilter(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoActiveFilter : null;
    }

    @Override
    public String getDaoPackage(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoPckg : null;
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
        String dir = Utils.resourceDir(artifacts.eResource());
        if (dir == null) {
            LOGGER.error("LOADED RESOURCE URI IS NOT VALID " + artifacts.eResource().getURI());
            return null;
        }
        return dirs2models.get(dir);
    }

    @Override
    public String toString() {
        return "ModelPropertyBean [dirs2models=" + dirs2models + "]";
    }
}
