package org.sqlproc.plugin.lib.property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.sqlproc.plugin.lib.util.CommonUtils;

public abstract class ModelProperty extends AdapterImpl {

    public static final String RESOLVE_POJO_ON = "resolve-pojo-on";
    public static final String RESOLVE_POJO_OFF = "resolve-pojo-off";
    public static final String REPLACE_ALL_REGEX = "replace-all-regex";
    public static final String REPLACE_ALL_REPLACEMENT = "replace-all-replacement";
    public static final String REPLACE_TEXT = "replace-text";
    public static final String COMPRESS_META_DIRECTIVES = "compress-meta-directives";
    public static final String VALIDATE_RESOURCES = "validate-resources";
    public static final String GENERATE_FROM_TO = "generate-from-to";
    public static final String CASE_FORMAT_LIBRARY = "case-format-library";
    public static final String NEW_POJO_VALIDATOR = "new-pojo-validator";
    public static final String OLD_POJO_VALIDATOR = "old-pojo-validator";

    public static final String DATABASE = "database";
    public static final String DATABASE_IS_ONLINE = "is-online";
    public static final String DATABASE_IS_OFFLINE = "is-offline";
    public static final String DATABASE_HAS_URL = "has-url";
    public static final String DATABASE_EXECUTE_BEFORE = "ddl-create";
    public static final String DATABASE_EXECUTE_AFTER = "ddl-drop";
    public static final String DATABASE_LOGIN_USERNAME = "login-username";
    public static final String DATABASE_LOGIN_PASSWORD = "login-password";
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
    public static final String POJOGEN_SHOW_TYPE_FOR_COLUMN = "show-type-for-column";
    public static final String POJOGEN_SHOW_TYPE_FOR_PROCEDURE = "show-type-for-procedure";
    public static final String POJOGEN_SHOW_TYPE_FOR_FUNCTION = "show-type-for-function";
    public static final String POJOGEN_IGNORE_TABLES = "ignore-tables";
    public static final String POJOGEN_ONLY_TABLES = "only-tables";
    public static final String POJOGEN_CREATE_TABLES = "create-tables";
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
    public static final String POJOGEN_CREATE_121_IMPORTS = "association-one-to-one";
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
    public static final String POJOGEN_COLUMN_ANNOTATIONS = "column-annotations";

    public static final String METAGEN = "metagen";
    public static final String METAGEN_GLOBAL_SEQUENCE = "global-sequence";
    public static final String METAGEN_TABLE_SEQUENCE = "table-sequence";
    public static final String METAGEN_GLOBAL_IDENTITY = "global-identity";
    public static final String METAGEN_TABLE_IDENTITY = "table-identity";
    public static final String METAGEN_SQLTYPE_META_TYPE = "sqltype-meta-type";
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
    public static final String METAGEN_INSERT_SKIP_DEFAULT_VALUES = "insert-skip-default-values";

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
    public static final String DAOGEN_IMPLEMENTATION_PACKAGE = "implementation-package";

    public static final String STANDARD = "___GLOBAL";

    protected Logger LOGGER = Logger.getLogger(ModelProperty.class);

    public static final List<String> STANDARD_DIRECTIVES = new ArrayList<String>();

    static {
        STANDARD_DIRECTIVES.add(RESOLVE_POJO_ON);
        STANDARD_DIRECTIVES.add(RESOLVE_POJO_OFF);
        STANDARD_DIRECTIVES.add(REPLACE_ALL_REGEX);
        STANDARD_DIRECTIVES.add(REPLACE_ALL_REPLACEMENT);
        STANDARD_DIRECTIVES.add(COMPRESS_META_DIRECTIVES);
        STANDARD_DIRECTIVES.add(REPLACE_TEXT);
        STANDARD_DIRECTIVES.add(VALIDATE_RESOURCES);
        STANDARD_DIRECTIVES.add(GENERATE_FROM_TO);
        STANDARD_DIRECTIVES.add(CASE_FORMAT_LIBRARY);
        STANDARD_DIRECTIVES.add(NEW_POJO_VALIDATOR);
    }

    public static final List<String> DATABASE_DIRECTIVES = new ArrayList<String>();

    static {
        DATABASE_DIRECTIVES.add(DATABASE_IS_ONLINE);
        DATABASE_DIRECTIVES.add(DATABASE_IS_OFFLINE);
        DATABASE_DIRECTIVES.add(DATABASE_HAS_URL);
        DATABASE_DIRECTIVES.add(DATABASE_EXECUTE_BEFORE);
        DATABASE_DIRECTIVES.add(DATABASE_EXECUTE_AFTER);
        DATABASE_DIRECTIVES.add(DATABASE_LOGIN_USERNAME);
        DATABASE_DIRECTIVES.add(DATABASE_LOGIN_PASSWORD);
        DATABASE_DIRECTIVES.add(DATABASE_IN_CATALOG);
        DATABASE_DIRECTIVES.add(DATABASE_ACTIVE_SCHEMA);
        DATABASE_DIRECTIVES.add(DATABASE_JDBC_DRIVER);
        DATABASE_DIRECTIVES.add(DATABASE_INDEX_TYPES);
        DATABASE_DIRECTIVES.add(DATABASE_SKIP_INDEXES);
        DATABASE_DIRECTIVES.add(DATABASE_SKIP_CHECK_CONSTRAINTS);
        DATABASE_DIRECTIVES.add(DATABASE_SKIP_PROCEDURES);
        DATABASE_DIRECTIVES.add(DATABASE_OF_TYPE);
        DATABASE_DIRECTIVES.add(DATABASE_DEBUG_LEVEL);
        DATABASE_DIRECTIVES.add(DATABASE_TAKE_COMMENTS);
        DATABASE_DIRECTIVES.add(DATABASE_LOWERCASE_NAMES);
        DATABASE_DIRECTIVES.add(DATABASE_UPPERCASE_NAMES);
    }

    public static class ModelValues {
        public boolean initialized;
        public boolean doResolvePojo;
        public boolean doResolveDb;
        public Map<String, String> replaceAllRegex;
        public Map<String, String> replaceAllReplacement;
        public boolean doCompressMetaDirectives;
        public Set<String> doVerifyResources;
        public Set<String> doNotVerifyResources;
        public boolean doGenerateFromTo;
        public String caseFormatLibrary;
        public boolean oldPojoValidator;
        public String dbDriver;
        public PojoEntityType dbDriverPojo;
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
        public Set<String> createTables;
        public Map<String, Set<String>> ignoreColumns;
        public Map<String, Set<String>> requiredColumns;
        public Map<String, Set<String>> notRequiredColumns;
        public Map<String, Map<String, PojoAttrType>> createColumns;
        public Map<String, Map<String, Map<String, String>>> ignoreExports;
        public Map<String, Map<String, Map<String, String>>> ignoreImports;
        public Map<String, Map<String, Map<String, String>>> createExports;
        public Map<String, Map<String, Map<String, String>>> createImports;
        public Map<String, Map<String, Map<String, String>>> create121Imports;
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
        public Map<String, PojoEntityType> pojosForProcedures;
        public Map<String, PojoEntityType> pojosForFunctions;
        public String activeFilter;
        public String pckg;
        public Map<String, String> enumForCheckConstraints;
        public Map<String, ColumnAnnotations> columnAnnotations;

        public PairValues metaGlobalSequence;
        public Map<String, PairValues> metaTablesSequence;
        public PairValues metaGlobalIdentity;
        public Map<String, PairValues> metaTablesIdentity;
        public Map<String, PairValues> metaSqlTypes;
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
        public boolean metaInsertSkipDefaultValues;
        public Set<String> metaInsertSkipDefaultValuesPos;
        public Set<String> metaInsertSkipDefaultValuesNeg;

        public Set<String> daoIgnoreTables;
        public Set<String> daoOnlyTables;
        public Map<String, ImplementsExtends> daoToImplements;
        public ImplementsExtends daoToExtends;
        public boolean daoMakeItFinal;
        public Map<String, PojoEntityType> daoFunctionsResult;
        public String daoDebugLevel;
        public String daoDebugScope;
        public String daoActiveFilter;
        public String daoPckg;
        public String daoImplPckg;

        public Map<String, PojoDefinition> modelPojos;
        public Map<String, PojoDefinition> modelAnnotations;
        public Map<String, TableDefinition> modelTables;
        public Map<String, ProcedureDefinition> modelProcedures;
        public Map<String, FunctionDefinition> modelFunctions;

        public Map<String, Set<String>> defaultAttrs = new HashMap<String, Set<String>>();
        public Map<String, Set<String>> conditionalAttrs = new HashMap<String, Set<String>>();
        public Map<String, Set<String>> systemEnvAttrs = new HashMap<String, Set<String>>();

        public void initModel() {
            replaceAllRegex = new HashMap<String, String>();
            replaceAllReplacement = new HashMap<String, String>();
            doCompressMetaDirectives = false;
            doVerifyResources = new HashSet<String>();
            doNotVerifyResources = new HashSet<String>();
            doGenerateFromTo = false;
            caseFormatLibrary = null;
            oldPojoValidator = false;
            defaultAttrs.put(STANDARD, new HashSet<String>());
            conditionalAttrs.put(STANDARD, new HashSet<String>());
            systemEnvAttrs.put(STANDARD, new HashSet<String>());
        }

        public void initDatabaseModel() {
            doResolveDb = false;
            dbDriver = null;
            dbDriverPojo = null;
            dbUrl = null;
            dbUsername = null;
            dbPassword = null;
            dbCatalog = null;
            dbSchema = null;
            dbSqlsBefore = null;
            dbSqlsAfter = null;
            dbIndexTypes = null;
            dbSkipIndexes = false;
            dbSkipProcedures = false;
            dbSkipCheckConstraints = false;
            dbType = null;
            dbDebugLevel = null;
            dbDebugScope = null;
            dbTakeComments = false;
            dbLowercaseNames = false;
            dbUppercaseNames = false;
            defaultAttrs.put(DATABASE, new HashSet<String>());
            conditionalAttrs.put(DATABASE, new HashSet<String>());
            systemEnvAttrs.put(DATABASE, new HashSet<String>());
        }

        public void initPojogenModel() {
            sqlTypes = new HashMap<String, PojoAttrType>();
            tableTypes = new HashMap<String, Map<String, PojoAttrType>>();
            columnTypes = new HashMap<String, Map<String, PojoAttrType>>();
            procedureTypes = new HashMap<String, Map<String, PojoAttrType>>();
            functionTypes = new HashMap<String, Map<String, PojoAttrType>>();
            tableNames = new HashMap<String, String>();
            columnNames = new HashMap<String, Map<String, String>>();
            ignoreTables = new HashSet<String>();
            onlyTables = new HashSet<String>();
            notAbstractTables = new HashSet<String>();
            createTables = new HashSet<String>();
            ignoreColumns = new HashMap<String, Set<String>>();
            requiredColumns = new HashMap<String, Set<String>>();
            notRequiredColumns = new HashMap<String, Set<String>>();
            createColumns = new HashMap<String, Map<String, PojoAttrType>>();
            ignoreExports = new HashMap<String, Map<String, Map<String, String>>>();
            ignoreImports = new HashMap<String, Map<String, Map<String, String>>>();
            createExports = new HashMap<String, Map<String, Map<String, String>>>();
            createImports = new HashMap<String, Map<String, Map<String, String>>>();
            create121Imports = new HashMap<String, Map<String, Map<String, String>>>();
            inheritImports = new HashMap<String, Map<String, Map<String, String>>>();
            manyToManyImports = new HashMap<String, Map<String, Map<String, String>>>();
            inheritance = new HashMap<String, Map<String, Map<String, List<String>>>>();
            inheritanceColumns = new HashMap<String, String>();
            generateMethods = new HashSet<String>();
            generateOperators = null;
            toImplements = new HashMap<String, ImplementsExtends>();
            toExtends = null;
            joinTables = new HashMap<String, List<String>>();
            doGenerateWrappers = false;
            doGenerateValidationAnnotations = false;
            makeItFinal = false;
            versionColumn = null;
            versionColumns = new HashMap<String, Set<String>>();
            notVersionColumns = new HashMap<String, Set<String>>();
            debugLevel = null;
            debugScope = null;
            preserveForeignKeys = new HashSet<String>();
            pojosForProcedures = new HashMap<String, PojoEntityType>();
            pojosForFunctions = new HashMap<String, PojoEntityType>();
            activeFilter = null;
            pckg = null;
            enumForCheckConstraints = new HashMap<String, String>();
            defaultAttrs.put(POJOGEN, new HashSet<String>());
            conditionalAttrs.put(POJOGEN, new HashSet<String>());
            columnAnnotations = new HashMap<String, ColumnAnnotations>();
        }

        public void initMetagenModel() {
            metaGlobalSequence = null;
            metaTablesSequence = new HashMap<String, PairValues>();
            metaGlobalIdentity = null;
            metaTablesIdentity = new HashMap<String, PairValues>();
            metaSqlTypes = new HashMap<String, PairValues>();
            metaColumnsMetaTypes = new HashMap<String, Map<String, PairValues>>();
            metaStatementsMetaTypes = new HashMap<String, Map<String, PairValues>>();
            metaMakeItFinal = false;
            metaLikeColumns = new HashMap<String, Set<String>>();
            metaNotLikeColumns = new HashMap<String, Set<String>>();
            metaGenerateSequences = false;
            metaGlobalSequenceForTables = new HashSet<String>();
            metaGlobalSequenceNotForTables = new HashSet<String>();
            metaGenerateIdentities = false;
            metaGlobalIdentityForTables = new HashSet<String>();
            metaGlobalIdentityNotForTables = new HashSet<String>();
            metaGenerateIdGenerators = false;
            metaGenerateIndirectIdGenerators = false;
            metaFunctionsResult = new HashMap<String, String>();
            metaFunctionsResultSet = new HashMap<String, String>();
            metaProceduresResultSet = new HashMap<String, String>();
            metaDebugLevel = null;
            metaDebugScope = null;
            metaGenerateOperators = false;
            metaOptimizeInsert = new HashSet<String>();
            metaOptionalFeatures = new HashMap<String, Set<String>>();
            metaActiveFilter = null;
            metaInsertSkipDefaultValues = false;
            metaInsertSkipDefaultValuesPos = new HashSet<String>();
            metaInsertSkipDefaultValuesNeg = new HashSet<String>();
            defaultAttrs.put(METAGEN, new HashSet<String>());
            conditionalAttrs.put(METAGEN, new HashSet<String>());
        }

        public void initDaogenModel() {
            daoIgnoreTables = new HashSet<String>();
            daoOnlyTables = new HashSet<String>();
            daoToImplements = new HashMap<String, ImplementsExtends>();
            daoToExtends = null;
            daoMakeItFinal = false;
            daoFunctionsResult = new HashMap<String, PojoEntityType>();
            daoDebugLevel = null;
            daoDebugScope = null;
            daoActiveFilter = null;
            daoPckg = null;
            daoImplPckg = null;
            defaultAttrs.put(DAOGEN, new HashSet<String>());
            conditionalAttrs.put(DAOGEN, new HashSet<String>());
        }

        public void initModelModel() {
            modelPojos = new HashMap<String, PojoDefinition>();
            modelAnnotations = new HashMap<String, PojoDefinition>();
            modelTables = new HashMap<String, TableDefinition>();
            modelProcedures = new HashMap<String, ProcedureDefinition>();
            modelFunctions = new HashMap<String, FunctionDefinition>();
        }

        public void setStandardValuesFromEnv() {
            for (String name : STANDARD_DIRECTIVES) {
                String envName = getEnvName(null, name);
                String value = System.getenv(envName);
                if (value == null)
                    continue;
                systemEnvAttrs.get(STANDARD).add(name);
                if (RESOLVE_POJO_ON.equals(name)) {
                    doResolvePojo = true;
                } else if (RESOLVE_POJO_OFF.equals(name)) {
                    doResolvePojo = false;
                } else if (COMPRESS_META_DIRECTIVES.equals(name)) {
                    doCompressMetaDirectives = true;
                } else if (GENERATE_FROM_TO.equals(name)) {
                    doGenerateFromTo = true;
                } else if (CASE_FORMAT_LIBRARY.equals(name)) {
                    caseFormatLibrary = CommonUtils.getPropertyValue(value);
                } else if (NEW_POJO_VALIDATOR.equals(name)) {
                    oldPojoValidator = false;
                } else if (OLD_POJO_VALIDATOR.equals(name)) {
                    oldPojoValidator = true;
                }
            }
        }

        public void setDatabaseValuesFromEnv() {
            for (String name : DATABASE_DIRECTIVES) {
                String envName = getEnvName(DATABASE, name);
                String value = System.getenv(envName);
                if (value == null)
                    continue;
                systemEnvAttrs.get(DATABASE).add(name);
                if (DATABASE_IS_ONLINE.equals(name)) {
                    doResolveDb = value.equalsIgnoreCase("true");
                } else if (DATABASE_IS_OFFLINE.equals(name)) {
                    doResolveDb = value.equalsIgnoreCase("true");
                } else if (DATABASE_HAS_URL.equals(name)) {
                    dbUrl = CommonUtils.getPropertyValue(value);
                } else if (DATABASE_LOGIN_USERNAME.equals(name)) {
                    dbUsername = CommonUtils.getPropertyValue(value);
                } else if (DATABASE_LOGIN_PASSWORD.equals(name)) {
                    dbPassword = CommonUtils.getPropertyValue(value);
                } else if (DATABASE_IN_CATALOG.equals(name)) {
                    dbCatalog = CommonUtils.getPropertyValue(value);
                } else if (DATABASE_ACTIVE_SCHEMA.equals(name)) {
                    dbSchema = CommonUtils.getPropertyValue(value);
                } else if (DATABASE_JDBC_DRIVER.equals(name)) {
                    dbDriver = CommonUtils.getPropertyValue(value);
                } else if (DATABASE_EXECUTE_BEFORE.equals(name)) {
                    dbSqlsBefore = CommonUtils.getPropertyValue(value);
                } else if (DATABASE_EXECUTE_AFTER.equals(name)) {
                    dbSqlsAfter = CommonUtils.getPropertyValue(value);
                } else if (DATABASE_INDEX_TYPES.equals(name)) {
                    dbIndexTypes = CommonUtils.getPropertyValue(value);
                } else if (DATABASE_SKIP_INDEXES.equals(name)) {
                    dbSkipIndexes = value.equalsIgnoreCase("true");
                } else if (DATABASE_SKIP_PROCEDURES.equals(name)) {
                    dbSkipProcedures = value.equalsIgnoreCase("true");
                } else if (DATABASE_SKIP_CHECK_CONSTRAINTS.equals(name)) {
                    dbSkipCheckConstraints = value.equalsIgnoreCase("true");
                } else if (DATABASE_OF_TYPE.equals(name)) {
                    dbType = CommonUtils.getPropertyValue(value);
                } else if (DATABASE_DEBUG_LEVEL.equals(name)) {
                    dbDebugLevel = CommonUtils.getPropertyValue(value);
                } else if (DATABASE_TAKE_COMMENTS.equals(name)) {
                    dbTakeComments = value.equalsIgnoreCase("true");
                } else if (DATABASE_LOWERCASE_NAMES.equals(name)) {
                    dbLowercaseNames = value.equalsIgnoreCase("true");
                } else if (DATABASE_UPPERCASE_NAMES.equals(name)) {
                    dbUppercaseNames = value.equalsIgnoreCase("true");
                }
            }
        }
    }

    protected ModelValues modelValues = null;
    protected Map<String, ModelValues> dirs2models = new HashMap<String, ModelValues>();

    public ModelProperty() {
    }

    public ModelProperty(ModelValues modelValues) {
        init(modelValues);
    }

    public void init(Object modelValues) {
        if (modelValues == null || !(modelValues instanceof ModelValues))
            this.modelValues = null;
        else {
            this.modelValues = (ModelValues) modelValues;
            this.modelValues.dir = "XXX";
        }
    }

    public boolean isDoResolvePojo(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.doResolvePojo : false;
    }

    public boolean isDoResolveDb(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.doResolveDb : false;
    }

    public Map<String, String> getReplaceAll(EObject model) {
        ModelValues modelValues = getModelValues(model);
        if (modelValues == null || modelValues.replaceAllRegex == null || modelValues.replaceAllReplacement == null)
            return Collections.<String, String>emptyMap();
        Map<String, String> result = new HashMap<String, String>();
        for (String replaceId : modelValues.replaceAllRegex.keySet()) {
            if (modelValues.replaceAllReplacement.containsKey(replaceId)) {
                result.put(modelValues.replaceAllRegex.get(replaceId),
                        modelValues.replaceAllReplacement.get(replaceId));
            }
        }
        return result;
    }

    public Set<String> getDoVerifyResources(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.doVerifyResources : Collections.<String>emptySet();
    }

    public Set<String> getDoNotVerifyResources(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.doNotVerifyResources : Collections.<String>emptySet();
    }

    public boolean isCompressMetaDirectives(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.doCompressMetaDirectives : false;
    }

    public boolean isGenerateFromTo(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.doGenerateFromTo : false;
    }

    public String getCaseFormatLibrary(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.caseFormatLibrary : null;
    }

    public boolean isOldPojoValidator(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.oldPojoValidator : false;
    }

    public Map<String, PojoAttrType> getSqlTypes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.sqlTypes : Collections.<String, PojoAttrType>emptyMap();
    }

    public Map<String, Map<String, PojoAttrType>> getTableTypes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.tableTypes
                : Collections.<String, Map<String, PojoAttrType>>emptyMap();
    }

    public Map<String, Map<String, PojoAttrType>> getColumnTypes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.columnTypes
                : Collections.<String, Map<String, PojoAttrType>>emptyMap();
    }

    public Map<String, Map<String, PojoAttrType>> getProcedureTypes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.procedureTypes
                : Collections.<String, Map<String, PojoAttrType>>emptyMap();
    }

    public Map<String, Map<String, PojoAttrType>> getFunctionTypes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.functionTypes
                : Collections.<String, Map<String, PojoAttrType>>emptyMap();
    }

    public Map<String, String> getTableNames(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.tableNames : Collections.<String, String>emptyMap();
    }

    public Map<String, Map<String, String>> getColumnNames(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.columnNames : Collections.<String, Map<String, String>>emptyMap();
    }

    public Set<String> getIgnoreTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.ignoreTables : Collections.<String>emptySet();
    }

    public Set<String> getOnlyTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.onlyTables : Collections.<String>emptySet();
    }

    public Set<String> getNotAbstractTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.notAbstractTables : Collections.<String>emptySet();
    }

    public Set<String> getCreateTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.createTables : Collections.<String>emptySet();
    }

    public Map<String, Set<String>> getIgnoreColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.ignoreColumns : Collections.<String, Set<String>>emptyMap();
    }

    public Map<String, Set<String>> getRequiredColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.requiredColumns : Collections.<String, Set<String>>emptyMap();
    }

    public Map<String, Set<String>> getNotRequiredColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.notRequiredColumns : Collections.<String, Set<String>>emptyMap();
    }

    public Map<String, Map<String, PojoAttrType>> getCreateColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.createColumns
                : Collections.<String, Map<String, PojoAttrType>>emptyMap();
    }

    public Map<String, Map<String, Map<String, String>>> getIgnoreExports(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.ignoreExports
                : Collections.<String, Map<String, Map<String, String>>>emptyMap();
    }

    public Map<String, Map<String, Map<String, String>>> getIgnoreImports(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.ignoreImports
                : Collections.<String, Map<String, Map<String, String>>>emptyMap();
    }

    public Map<String, Map<String, Map<String, String>>> getCreateExports(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.createExports
                : Collections.<String, Map<String, Map<String, String>>>emptyMap();
    }

    public Map<String, Map<String, Map<String, String>>> getCreateImports(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.createImports
                : Collections.<String, Map<String, Map<String, String>>>emptyMap();
    }

    public Map<String, Map<String, Map<String, String>>> getCreate121Imports(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.create121Imports
                : Collections.<String, Map<String, Map<String, String>>>emptyMap();
    }

    public Map<String, Map<String, Map<String, String>>> getInheritImports(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.inheritImports
                : Collections.<String, Map<String, Map<String, String>>>emptyMap();
    }

    public Map<String, Map<String, Map<String, String>>> getManyToManyImports(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.manyToManyImports
                : Collections.<String, Map<String, Map<String, String>>>emptyMap();
    }

    public Map<String, Map<String, Map<String, List<String>>>> getInheritance(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.inheritance
                : Collections.<String, Map<String, Map<String, List<String>>>>emptyMap();
    }

    public Map<String, String> getInheritanceColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.inheritanceColumns : Collections.<String, String>emptyMap();
    }

    public Set<String> getGenerateMethods(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.generateMethods : Collections.<String>emptySet();
    }

    public String getGenerateOperators(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.generateOperators : null;
    }

    public Map<String, ImplementsExtends> getToImplements(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.toImplements : Collections.<String, ImplementsExtends>emptyMap();
    }

    public ImplementsExtends getToExtends(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.toExtends : null;
    }

    public Map<String, List<String>> getJoinTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.joinTables : Collections.<String, List<String>>emptyMap();
    }

    public boolean isDoGenerateWrappers(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.doGenerateWrappers : false;
    }

    public boolean isDoGenerateValidationAnnotations(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.doGenerateValidationAnnotations : false;
    }

    public boolean isMakeItFinal(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.makeItFinal : false;
    }

    public String getVersionColumn(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.versionColumn : null;
    }

    public Map<String, Set<String>> getVersionColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.versionColumns : Collections.<String, Set<String>>emptyMap();
    }

    public Map<String, Set<String>> getNotVersionColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.notVersionColumns : Collections.<String, Set<String>>emptyMap();
    }

    public String getDebugLevel(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.debugLevel : null;
    }

    public String getDebugScope(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.debugScope : null;
    }

    public Set<String> getPreserveForeignKeys(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.preserveForeignKeys : Collections.<String>emptySet();
    }

    public Map<String, ColumnAnnotations> getColumnAnnotations(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.columnAnnotations
                : Collections.<String, ColumnAnnotations>emptyMap();
    }

    public PairValues getMetaGlobalIdentity(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGlobalIdentity : null;
    }

    public Map<String, PojoEntityType> getPojosForProcedures(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.pojosForProcedures : Collections.<String, PojoEntityType>emptyMap();
    }

    public Map<String, PojoEntityType> getPojosForFunctions(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.pojosForFunctions : Collections.<String, PojoEntityType>emptyMap();
    }

    public String getActiveFilter(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.activeFilter : null;
    }

    public String getPackage(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.pckg : null;
    }

    public Map<String, String> getEnumForCheckConstraints(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.enumForCheckConstraints : Collections.<String, String>emptyMap();
    }

    public Map<String, PairValues> getMetaTablesIdentity(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaTablesIdentity : Collections.<String, PairValues>emptyMap();
    }

    public PairValues getMetaGlobalSequence(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGlobalSequence : null;
    }

    public Map<String, PairValues> getMetaTablesSequence(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaTablesSequence : Collections.<String, PairValues>emptyMap();
    }

    public Map<String, PairValues> getMetaSqlTypes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaSqlTypes : Collections.<String, PairValues>emptyMap();
    }

    public Map<String, Map<String, PairValues>> getMetaColumnsMetaTypes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaColumnsMetaTypes
                : Collections.<String, Map<String, PairValues>>emptyMap();
    }

    public Map<String, Map<String, PairValues>> getMetaStatementsMetaTypes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaStatementsMetaTypes
                : Collections.<String, Map<String, PairValues>>emptyMap();
    }

    public boolean isMetaMakeItFinal(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaMakeItFinal : false;
    }

    public Map<String, Set<String>> getMetaLikeColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaLikeColumns : Collections.<String, Set<String>>emptyMap();
    }

    public Map<String, Set<String>> getMetaNotLikeColumns(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaNotLikeColumns : Collections.<String, Set<String>>emptyMap();
    }

    public boolean isMetaGenerateSequences(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGenerateSequences : false;
    }

    public Set<String> getMetaGlobalSequenceForTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGlobalSequenceForTables : Collections.<String>emptySet();
    }

    public Set<String> getMetaGlobalSequenceNotForTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGlobalSequenceNotForTables : Collections.<String>emptySet();
    }

    public boolean isMetaGenerateIdentities(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGenerateIdentities : false;
    }

    public Set<String> getMetaGlobalIdentityForTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGlobalIdentityForTables : Collections.<String>emptySet();
    }

    public Set<String> getMetaGlobalIdentityNotForTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGlobalIdentityNotForTables : Collections.<String>emptySet();
    }

    public boolean isMetaGenerateIdGenerators(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGenerateIdGenerators : false;
    }

    public boolean isMetaGenerateIndirectIdGenerators(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGenerateIndirectIdGenerators : false;
    }

    public Map<String, String> getMetaFunctionsResult(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaFunctionsResult : Collections.<String, String>emptyMap();
    }

    public Map<String, String> getMetaFunctionsResultSet(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaFunctionsResultSet : Collections.<String, String>emptyMap();
    }

    public Map<String, String> getMetaProceduresResultSet(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaProceduresResultSet : Collections.<String, String>emptyMap();
    }

    public String getMetaDebugLevel(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaDebugLevel : null;
    }

    public String getMetaDebugScope(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaDebugScope : null;
    }

    public boolean isMetaGenerateOperators(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaGenerateOperators : false;
    }

    public Set<String> getMetaOptimizeInsert(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaOptimizeInsert : Collections.<String>emptySet();
    }

    public Map<String, Set<String>> getMetaOptionalFeatures(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaOptionalFeatures : Collections.<String, Set<String>>emptyMap();
    }

    public String getMetaActiveFilter(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaActiveFilter : null;
    }

    public boolean isMetaInsertSkipDefaultValues(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaInsertSkipDefaultValues : false;
    }

    public Set<String> getMetaInsertSkipDefaultValuesPos(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaInsertSkipDefaultValuesPos : Collections.<String>emptySet();
    }

    public Set<String> getMetaInsertSkipDefaultValuesNeg(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.metaInsertSkipDefaultValuesNeg : Collections.<String>emptySet();
    }

    public Set<String> getDaoIgnoreTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoIgnoreTables : Collections.<String>emptySet();
    }

    public Set<String> getDaoOnlyTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoOnlyTables : Collections.<String>emptySet();
    }

    public Map<String, ImplementsExtends> getDaoToImplements(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoToImplements : Collections.<String, ImplementsExtends>emptyMap();
    }

    public ImplementsExtends getDaoToExtends(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoToExtends : null;
    }

    public boolean isDaoMakeItFinal(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoMakeItFinal : false;
    }

    public Map<String, PojoEntityType> getDaoFunctionsResult(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoFunctionsResult : Collections.<String, PojoEntityType>emptyMap();
    }

    public String getDaoDebugLevel(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoDebugLevel : null;
    }

    public String getDaoDebugScope(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoDebugScope : null;
    }

    public String getDaoActiveFilter(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoActiveFilter : null;
    }

    public String getDaoPackage(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoPckg : null;
    }

    public String getDaoImplPackage(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.daoImplPckg : null;
    }

    public String getDbDriver(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbDriver : null;
    }

    public PojoEntityType getDbDrivePojo(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbDriverPojo : null;
    }

    public String getDbUrl(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbUrl : null;
    }

    public String getDbUsername(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbUsername : null;
    }

    public String getDbPassword(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbPassword : null;
    }

    public String getDbCatalog(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbCatalog : null;
    }

    public String getDbSchema(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbSchema : null;
    }

    public String getDbSqlsBefore(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbSqlsBefore : null;
    }

    public String getDbSqlsAfter(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbSqlsAfter : null;
    }

    public String getDbIndexTypes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbIndexTypes : null;
    }

    public boolean getDbSkipIndexes(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbSkipIndexes : false;
    }

    public boolean getDbSkipProcedures(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbSkipProcedures : false;
    }

    public boolean getDbSkipCheckConstraints(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbSkipCheckConstraints : false;
    }

    public String getDbType(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbType : null;
    }

    public String getDbDebugLevel(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbDebugLevel : null;
    }

    public String getDbDebugScope(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbDebugScope : null;
    }

    public boolean getDbTakeComments(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbTakeComments : false;
    }

    public boolean getDbLowercaseNames(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbLowercaseNames : false;
    }

    public boolean getDbUppercaseNames(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dbUppercaseNames : false;
    }

    public String getModelDir(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.dir : null;
    }

    public Map<String, PojoDefinition> getModelPojos(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.modelPojos : Collections.<String, PojoDefinition>emptyMap();
    }

    public Map<String, PojoDefinition> getModelAnnotations(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.modelAnnotations : Collections.<String, PojoDefinition>emptyMap();
    }

    public Map<String, TableDefinition> getModelTables(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.modelTables : Collections.<String, TableDefinition>emptyMap();
    }

    public Map<String, ProcedureDefinition> getModelProcedures(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.modelProcedures
                : Collections.<String, ProcedureDefinition>emptyMap();
    }

    public Map<String, FunctionDefinition> getModelFunctions(EObject model) {
        ModelValues modelValues = getModelValues(model);
        return (modelValues != null) ? modelValues.modelFunctions : Collections.<String, FunctionDefinition>emptyMap();
    }

    public static String getEnvName(String category, String name) {
        StringBuilder sb = new StringBuilder();
        if (category != null) {
            sb.append(category).append("_");
        }
        sb.append(name.replaceAll("-", "_"));
        return sb.toString();
    }

    @Override
    public String toString() {
        return "ModelPropertyBean [dirs2models=" + dirs2models + "]";
    }

    public boolean skipVerification(EObject model) {
        if (model == null)
            return true;
        URI uri = (model.eResource() != null) ? model.eResource().getURI() : null;
        if (uri == null)
            return true;
        ModelValues modelValues = getModelValues(model);
        if (modelValues == null || !modelValues.initialized)
            return true;
        String suri = uri.toString();

        if ((modelValues.doVerifyResources == null || modelValues.doVerifyResources.isEmpty())
                && (modelValues.doNotVerifyResources == null || modelValues.doNotVerifyResources.isEmpty()))
            return false;
        if (modelValues.doVerifyResources != null && !modelValues.doVerifyResources.isEmpty()) {
            for (String res : modelValues.doVerifyResources) {
                if (suri.indexOf(res) >= 0)
                    return false;
            }
            return true;
        }
        if (modelValues.doNotVerifyResources != null && !modelValues.doNotVerifyResources.isEmpty()) {
            for (String res : modelValues.doNotVerifyResources) {
                if (suri.indexOf(res) >= 0)
                    return true;
            }
            return false;
        }
        assert false;
        return true;
    }

    public abstract ModelValues getModelValues(EObject model);
}
