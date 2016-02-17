package org.sqlproc.plugin.lib.generator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.sqlproc.plugin.lib.property.EnumAttribute;
import org.sqlproc.plugin.lib.property.FunctionDefinition;
import org.sqlproc.plugin.lib.property.ImplementsExtends;
import org.sqlproc.plugin.lib.property.ModelProperty;
import org.sqlproc.plugin.lib.property.PojoAttrType;
import org.sqlproc.plugin.lib.property.PojoAttribute;
import org.sqlproc.plugin.lib.property.PojoEntityType;
import org.sqlproc.plugin.lib.property.ProcedureDefinition;
import org.sqlproc.plugin.lib.property.TableDefinition;
import org.sqlproc.plugin.lib.resolver.DbCheckConstraint;
import org.sqlproc.plugin.lib.resolver.DbColumn;
import org.sqlproc.plugin.lib.resolver.DbExport;
import org.sqlproc.plugin.lib.resolver.DbImport;
import org.sqlproc.plugin.lib.resolver.DbIndex;
import org.sqlproc.plugin.lib.resolver.DbIndex.DbIndexDetail;
import org.sqlproc.plugin.lib.resolver.DbResolver;
import org.sqlproc.plugin.lib.resolver.DbResolver.DbType;
import org.sqlproc.plugin.lib.resolver.DbTable;
import org.sqlproc.plugin.lib.util.Debug;
import org.sqlproc.plugin.lib.util.Stats;

import com.google.common.base.CaseFormat;

public class TableBaseGenerator {

    protected Logger LOGGER = Logger.getLogger(TableBaseGenerator.class);
    protected Debug debug = new Debug(LOGGER);

    protected static String INDENT = "    ";
    protected static String NL = "\n";
    protected static String NLINDENT = NL + INDENT;
    protected static String NLINDENTINDENT = NL + INDENT + INDENT;

    protected enum PrimitiveType {
        BOOLEAN, BYTE, SHORT, INT, LONG, FLOAT, DOUBLE, CHAR, BYTE_ARRAY, CHAR_ARRAY;

        public String getName() {
            if (this == BYTE_ARRAY)
                return "byte []";
            if (this == CHAR_ARRAY)
                return "char []";
            return name().toLowerCase();
        }
    }

    protected static final String METHOD_TO_STRING = "toString";
    protected static final String METHOD_HASH_CODE = "hashCode";
    protected static final String METHOD_EQUALS = "equals";
    protected static final String METHOD_IS_DEF = "isDef";
    protected static final String ENUM_IS_DEF = "enumDef";
    protected static final String METHOD_TO_INIT = "toInit";
    protected static final String METHOD_INDEX = "index";
    protected static final String METHOD_ENUM_TO_INIT = "enumInit";
    protected static final String METHOD_PROC_ID = "procId";
    protected static final String METHOD_ENUM_INDEX = "enumIndex";
    protected static final String COLLECTION_LIST = "java.util.List";
    protected static final String ANNOTATION_NOT_NULL = "javax.validation.constraints.NotNull";
    protected static final String ANNOTATION_SIZE = "javax.validation.constraints.Size";

    protected EObject model;
    protected boolean doCompressMetaDirectives;
    protected boolean doGenerateFromTo;
    protected String caseFormatLibrary;
    protected Map<String, PojoAttrType> sqlTypes = new HashMap<String, PojoAttrType>();
    protected Map<String, Map<String, PojoAttrType>> tableTypes = new HashMap<String, Map<String, PojoAttrType>>();
    protected Map<String, Map<String, PojoAttrType>> columnTypes = new HashMap<String, Map<String, PojoAttrType>>();
    protected Map<String, Map<String, PojoAttrType>> functionTypes = new HashMap<String, Map<String, PojoAttrType>>();
    protected Map<String, Map<String, PojoAttrType>> procedureTypes = new HashMap<String, Map<String, PojoAttrType>>();
    protected Map<String, String> tableNames = new HashMap<String, String>();
    protected Map<String, Map<String, String>> columnNames = new HashMap<String, Map<String, String>>();
    protected Set<String> ignoreTables = new HashSet<String>();
    protected Set<String> onlyTables = new HashSet<String>();
    protected Set<String> notAbstractTables = new HashSet<String>();
    protected Set<String> createTables = new HashSet<String>();
    protected Map<String, Set<String>> ignoreColumns = new HashMap<String, Set<String>>();
    protected Map<String, Set<String>> requiredColumns = new HashMap<String, Set<String>>();
    protected Map<String, Set<String>> notRequiredColumns = new HashMap<String, Set<String>>();
    protected Map<String, Map<String, PojoAttrType>> createColumns = new HashMap<String, Map<String, PojoAttrType>>();
    protected Map<String, Map<String, Map<String, String>>> ignoreExports = new HashMap<String, Map<String, Map<String, String>>>();
    protected Map<String, Map<String, Map<String, String>>> ignoreImports = new HashMap<String, Map<String, Map<String, String>>>();
    protected Map<String, Map<String, Map<String, String>>> createExports = new HashMap<String, Map<String, Map<String, String>>>();
    protected Map<String, Map<String, Map<String, String>>> createImports = new HashMap<String, Map<String, Map<String, String>>>();
    protected Map<String, Map<String, Map<String, String>>> create121Imports = new HashMap<String, Map<String, Map<String, String>>>();
    protected Map<String, Map<String, Map<String, String>>> inheritImports = new HashMap<String, Map<String, Map<String, String>>>();
    protected Map<String, Map<String, Map<String, String>>> manyToManyImports = new HashMap<String, Map<String, Map<String, String>>>();
    protected Map<String, Map<String, Map<String, List<String>>>> inheritance = new HashMap<String, Map<String, Map<String, List<String>>>>();
    protected Map<String, String> inheritanceColumns = new HashMap<String, String>();
    protected Set<String> generateMethods = new HashSet<String>();
    protected Map<String, ImplementsExtends> toImplements = new TreeMap<String, ImplementsExtends>();
    protected ImplementsExtends toExtends = null;
    protected Map<String, List<String>> joinTables = new HashMap<String, List<String>>();
    protected boolean doGenerateWrappers;
    protected boolean doGenerateValidationAnnotations;
    protected boolean makeItFinal;
    protected String versionColumn;
    protected Map<String, Set<String>> versionColumns = new HashMap<String, Set<String>>();
    protected Map<String, Set<String>> notVersionColumns = new HashMap<String, Set<String>>();
    protected String generateOperators = null;
    protected Set<String> preserveForeignKeys = new HashSet<String>();
    protected Map<String, PojoEntityType> pojosForProcedures = new HashMap<String, PojoEntityType>();
    protected Map<String, PojoEntityType> pojosForFunctions = new HashMap<String, PojoEntityType>();
    protected Filter activeFilter = null;
    protected Map<String, String> enumForCheckConstraints = new HashMap<String, String>();

    protected Set<String> tables = new HashSet<String>();
    protected Map<String, Map<String, PojoAttribute>> pojos = new TreeMap<String, Map<String, PojoAttribute>>();
    protected Map<String, Map<String, PojoAttribute>> procedures = new TreeMap<String, Map<String, PojoAttribute>>();
    protected Map<String, Map<String, PojoAttribute>> functions = new TreeMap<String, Map<String, PojoAttribute>>();
    protected Map<String, String> pojoExtends = new HashMap<String, String>();
    protected Map<String, Set<String>> pojoInheritanceDiscriminator = new HashMap<String, Set<String>>();
    protected Map<String, Set<String>> pojoInheritanceSimple = new HashMap<String, Set<String>>();
    protected Map<String, String> pojoDiscriminators = new HashMap<String, String>();
    protected Map<String, List<Map<PojoAttribute, Boolean>>> indexes = new TreeMap<String, List<Map<PojoAttribute, Boolean>>>();
    protected Set<String> dbSequences = new TreeSet<String>();
    protected DbType dbType = null;
    protected Map<String, List<EnumAttribute>> enums = new TreeMap<String, List<EnumAttribute>>();
    protected Map<String, String> enumsTables = new HashMap<String, String>();
    protected Map<String, String> comments = new HashMap<String, String>();
    protected String pojoPackage;

    protected Map<String, String> metaFunctionsResult = new HashMap<String, String>();
    protected Map<String, String> metaProceduresResult = new HashMap<String, String>();
    protected Map<String, String> metaFunctionsResultSet = new HashMap<String, String>();
    protected Map<String, String> metaProceduresResultSet = new HashMap<String, String>();

    // protected Map<String, PojoDefinition> modelPojos = new TreeMap<String, PojoDefinition>();
    protected Map<String, TableDefinition> modelTables = new TreeMap<String, TableDefinition>();
    protected Map<String, ProcedureDefinition> modelProcedures = new TreeMap<String, ProcedureDefinition>();
    protected Map<String, FunctionDefinition> modelFunctions = new TreeMap<String, FunctionDefinition>();
    protected Map<String, TableDefinition> modelTablesInv = new TreeMap<String, TableDefinition>();
    protected Map<String, ProcedureDefinition> modelProceduresInv = new TreeMap<String, ProcedureDefinition>();
    protected Map<String, FunctionDefinition> modelFunctionsInv = new TreeMap<String, FunctionDefinition>();

    public TableBaseGenerator(ModelProperty modelProperty, EObject model, List<String> dbSequences, DbType dbType) {

        this.model = model;
        debug = new Debug(modelProperty.getDebugLevel(model), modelProperty.getDebugScope(model), LOGGER);

        this.doCompressMetaDirectives = modelProperty.isCompressMetaDirectives(model);
        this.doGenerateFromTo = modelProperty.isGenerateFromTo(model);
        this.caseFormatLibrary = modelProperty.getCaseFormatLibrary(model);
        Map<String, PojoAttrType> sqlTypes = modelProperty.getSqlTypes(model);
        if (sqlTypes != null) {
            this.sqlTypes.putAll(sqlTypes);
            for (Entry<String, PojoAttrType> e : sqlTypes.entrySet()) {
                this.sqlTypes.put(e.getKey().toLowerCase(), e.getValue());
                this.sqlTypes.put(e.getKey().toUpperCase(), e.getValue());
            }
        }
        Map<String, Map<String, PojoAttrType>> tableTypes = modelProperty.getTableTypes(model);
        if (tableTypes != null) {
            this.tableTypes.putAll(tableTypes);
        }
        Map<String, Map<String, PojoAttrType>> columnTypes = modelProperty.getColumnTypes(model);
        if (columnTypes != null) {
            this.columnTypes.putAll(columnTypes);
        }
        Map<String, Map<String, PojoAttrType>> procedureTypes = modelProperty.getProcedureTypes(model);
        if (procedureTypes != null) {
            this.procedureTypes.putAll(procedureTypes);
        }
        Map<String, Map<String, PojoAttrType>> functionTypes = modelProperty.getFunctionTypes(model);
        if (functionTypes != null) {
            this.functionTypes.putAll(functionTypes);
        }
        Map<String, String> tableNames = modelProperty.getTableNames(model);
        if (tableNames != null) {
            this.tableNames.putAll(tableNames);
        }
        Map<String, Map<String, String>> columnNames = modelProperty.getColumnNames(model);
        if (columnNames != null) {
            this.columnNames.putAll(columnNames);
        }
        Set<String> ignoreTables = modelProperty.getIgnoreTables(model);
        if (ignoreTables != null) {
            this.ignoreTables.addAll(ignoreTables);
        }
        Set<String> onlyTables = modelProperty.getOnlyTables(model);
        if (onlyTables != null) {
            this.onlyTables.addAll(onlyTables);
        }
        Set<String> notAbstractTables = modelProperty.getNotAbstractTables(model);
        if (onlyTables != null) {
            this.notAbstractTables.addAll(notAbstractTables);
        }
        Set<String> createTables = modelProperty.getCreateTables(model);
        if (createTables != null) {
            this.createTables.addAll(createTables);
        }
        Map<String, Set<String>> ignoreColumns = modelProperty.getIgnoreColumns(model);
        if (ignoreColumns != null) {
            this.ignoreColumns.putAll(ignoreColumns);
        }
        Map<String, Set<String>> requiredColumns = modelProperty.getRequiredColumns(model);
        if (requiredColumns != null) {
            this.requiredColumns.putAll(requiredColumns);
        }
        Map<String, Set<String>> notRequiredColumns = modelProperty.getNotRequiredColumns(model);
        if (notRequiredColumns != null) {
            this.notRequiredColumns.putAll(notRequiredColumns);
        }
        Map<String, Map<String, PojoAttrType>> createColumns = modelProperty.getCreateColumns(model);
        if (createColumns != null) {
            this.createColumns.putAll(createColumns);
        }
        Map<String, Map<String, Map<String, String>>> ignoreExports = modelProperty.getIgnoreExports(model);
        if (ignoreExports != null) {
            this.ignoreExports.putAll(ignoreExports);
        }
        Map<String, Map<String, Map<String, String>>> ignoreImports = modelProperty.getIgnoreImports(model);
        if (ignoreImports != null) {
            this.ignoreImports.putAll(ignoreImports);
        }
        Map<String, Map<String, Map<String, String>>> createExports = modelProperty.getCreateExports(model);
        if (createExports != null) {
            this.createExports.putAll(createExports);
        }
        Map<String, Map<String, Map<String, String>>> createImports = modelProperty.getCreateImports(model);
        if (createImports != null) {
            this.createImports.putAll(createImports);
        }
        Map<String, Map<String, Map<String, String>>> create121Imports = modelProperty.getCreate121Imports(model);
        if (create121Imports != null) {
            this.create121Imports.putAll(create121Imports);
        }
        Map<String, Map<String, Map<String, String>>> inheritImports = modelProperty.getInheritImports(model);
        if (inheritImports != null) {
            this.inheritImports.putAll(inheritImports);
        }
        Map<String, Map<String, Map<String, String>>> manyToManyImports = modelProperty.getManyToManyImports(model);
        if (manyToManyImports != null) {
            this.manyToManyImports.putAll(manyToManyImports);
        }
        Map<String, Map<String, Map<String, List<String>>>> inheritance = modelProperty.getInheritance(model);
        if (inheritance != null) {
            this.inheritance.putAll(inheritance);
        }
        Map<String, String> inheritanceColumns = modelProperty.getInheritanceColumns(model);
        if (inheritanceColumns != null) {
            this.inheritanceColumns.putAll(inheritanceColumns);
        }
        Set<String> generateMethods = modelProperty.getGenerateMethods(model);
        if (generateMethods != null) {
            this.generateMethods.addAll(generateMethods);
        }
        this.generateOperators = modelProperty.getGenerateOperators(model);
        Map<String, ImplementsExtends> toImplements = modelProperty.getToImplements(model);
        if (toImplements != null) {
            this.toImplements.putAll(toImplements);
        }
        this.toExtends = modelProperty.getToExtends(model);
        Map<String, List<String>> joinTables = modelProperty.getJoinTables(model);
        if (joinTables != null) {
            this.joinTables.putAll(joinTables);
        }
        this.doGenerateWrappers = modelProperty.isDoGenerateWrappers(model);
        this.doGenerateValidationAnnotations = modelProperty.isDoGenerateValidationAnnotations(model);
        this.makeItFinal = modelProperty.isMakeItFinal(model);
        this.versionColumn = modelProperty.getVersionColumn(model);
        Map<String, Set<String>> versionColumns = modelProperty.getVersionColumns(model);
        if (versionColumns != null) {
            this.versionColumns.putAll(versionColumns);
        }
        Map<String, Set<String>> notVersionColumns = modelProperty.getNotVersionColumns(model);
        if (notVersionColumns != null) {
            this.notVersionColumns.putAll(notVersionColumns);
        }
        Set<String> preserveForeignKeys = modelProperty.getPreserveForeignKeys(model);
        if (preserveForeignKeys != null) {
            this.preserveForeignKeys.addAll(preserveForeignKeys);
        }
        Map<String, PojoEntityType> pojosForProcedures = modelProperty.getPojosForProcedures(model);
        if (pojosForProcedures != null) {
            this.pojosForProcedures.putAll(pojosForProcedures);
        }
        Map<String, PojoEntityType> pojosForFunctions = modelProperty.getPojosForFunctions(model);
        if (pojosForProcedures != null) {
            this.pojosForFunctions.putAll(pojosForFunctions);
        }
        this.activeFilter = Filter.parse(modelProperty.getActiveFilter(model));
        Map<String, String> enumForCheckConstraints = modelProperty.getEnumForCheckConstraints(model);
        if (enumForCheckConstraints != null) {
            this.enumForCheckConstraints.putAll(enumForCheckConstraints);
        }
        pojoPackage = modelProperty.getPackage(model);

        for (Map.Entry<String, Map<String, Map<String, String>>> inheritImport : this.inheritImports.entrySet()) {
            for (Map.Entry<String, Map<String, String>> inherit : inheritImport.getValue().entrySet()) {
                for (Map.Entry<String, String> tabcol : inherit.getValue().entrySet()) {
                    if (!this.ignoreExports.containsKey(tabcol.getKey()))
                        this.ignoreExports.put(tabcol.getKey(), new HashMap<String, Map<String, String>>());
                    if (!this.ignoreExports.get(tabcol.getKey()).containsKey(tabcol.getValue()))
                        this.ignoreExports.get(tabcol.getKey()).put(tabcol.getValue(), new HashMap<String, String>());
                    this.ignoreExports.get(tabcol.getKey()).get(tabcol.getValue()).put(inheritImport.getKey(), null);
                    if (!this.ignoreColumns.containsKey(inheritImport.getKey()))
                        this.ignoreColumns.put(inheritImport.getKey(), new HashSet<String>());
                    this.ignoreColumns.get(inheritImport.getKey()).add(inherit.getKey());
                }
            }
        }
        this.dbSequences.addAll(dbSequences);
        this.dbType = dbType;

        Map<String, String> metaFunctionsResult = modelProperty.getMetaFunctionsResult(model);
        if (metaFunctionsResult != null) {
            this.metaFunctionsResult.putAll(metaFunctionsResult);
        }
        Map<String, String> metaFunctionsResultSet = modelProperty.getMetaFunctionsResultSet(model);
        if (metaFunctionsResultSet != null) {
            this.metaFunctionsResultSet.putAll(metaFunctionsResultSet);
        }
        Map<String, String> metaProceduresResultSet = modelProperty.getMetaProceduresResultSet(model);
        if (metaProceduresResultSet != null) {
            this.metaProceduresResultSet.putAll(metaProceduresResultSet);
        }

        // Map<String, PojoDefinition> modelPojos = modelProperty.getModelPojos(model);
        // if (modelPojos != null) {
        // this.modelPojos.putAll(modelPojos);
        // }
        Map<String, TableDefinition> modelTables = modelProperty.getModelTables(model);
        if (modelTables != null) {
            this.modelTables.putAll(modelTables);
            for (TableDefinition def : modelTables.values())
                this.modelTablesInv.put(def.getTable(), def);
        }
        Map<String, ProcedureDefinition> modelProcedures = modelProperty.getModelProcedures(model);
        if (modelProcedures != null) {
            this.modelProcedures.putAll(modelProcedures);
            for (ProcedureDefinition def : modelProcedures.values())
                this.modelProceduresInv.put(def.getProcedure(), def);
        }
        Map<String, FunctionDefinition> modelFunctions = modelProperty.getModelFunctions(model);
        if (modelFunctions != null) {
            this.modelFunctions.putAll(modelFunctions);
            for (FunctionDefinition def : modelFunctions.values())
                this.modelFunctionsInv.put(def.getFunction(), def);
        }

        debug.debug("doCompressMetaDirectives " + this.doCompressMetaDirectives);
        debug.debug("sqlTypes " + this.sqlTypes);
        debug.debug("tableTypes " + this.tableTypes);
        debug.debug("columnTypes " + this.columnTypes);
        debug.debug("procedureTypes " + this.procedureTypes);
        debug.debug("functionTypes " + this.functionTypes);
        debug.debug("tableNames " + this.tableNames);
        debug.debug("columnNames " + this.columnNames);
        debug.debug("ignoreTables " + this.ignoreTables);
        debug.debug("onlyTables " + this.onlyTables);
        debug.debug("notAbstractTables " + this.notAbstractTables);
        debug.debug("createTables " + this.createTables);
        debug.debug("ignoreColumns " + this.ignoreColumns);
        debug.debug("createColumns " + this.createColumns);
        debug.debug("ignoreExports " + this.ignoreExports);
        debug.debug("ignoreImports " + this.ignoreImports);
        debug.debug("createExports " + this.createExports);
        debug.debug("createImports " + this.createImports);
        debug.debug("create121Imports " + this.create121Imports);
        debug.debug("inheritImports " + this.inheritImports);
        debug.debug("manyToManyImports " + this.manyToManyImports);
        debug.debug("inheritance " + this.inheritance);
        debug.debug("inheritanceColumns " + this.inheritanceColumns);
        debug.debug("generateMethods " + this.generateMethods);
        debug.debug("generateOperators " + this.generateOperators);
        debug.debug("toImplements " + this.toImplements);
        debug.debug("toExtends " + this.toExtends);
        debug.debug("joinTables " + this.joinTables);
        debug.debug("doGenerateWrappers " + this.doGenerateWrappers);
        debug.debug("doGenerateValidationAnnotations " + this.doGenerateValidationAnnotations);
        debug.debug("makeItFinal " + this.makeItFinal);
        debug.debug("sequences " + this.dbSequences);
        debug.debug("dbType " + this.dbType);
        debug.debug("metaFunctionsResult " + this.metaFunctionsResult);
        debug.debug("metaFunctionsResultSet " + this.metaFunctionsResultSet);
        debug.debug("metaProceduresResultSet " + this.metaProceduresResultSet);
        debug.debug("preserveForeignKeys " + this.preserveForeignKeys);
        debug.debug("pojosForProcedures " + this.pojosForProcedures);
        debug.debug("pojosForFunctions " + this.pojosForFunctions);
        debug.debug("versionColumn " + this.versionColumn);
        debug.debug("versionColumns " + this.versionColumns);
        debug.debug("notVersionColumns " + this.notVersionColumns);
        debug.debug("activeFilter " + this.activeFilter);
        debug.debug("enumForCheckConstraints " + this.enumForCheckConstraints);
        // debug.debug("modelPojos " + this.modelPojos);
        debug.debug("modelTables " + this.modelTables);
        debug.debug("modelProcedures " + this.modelProcedures);
        debug.debug("modelFunctions " + this.modelFunctions);

        for (String table : createTables) {
            addTableDefinition(table, Collections.<DbColumn> emptyList(), Collections.<String> emptyList(),
                    Collections.<DbExport> emptyList(), Collections.<DbImport> emptyList(),
                    Collections.<DbIndex> emptyList(), Collections.<DbCheckConstraint> emptyList(), null);
            tables.remove(table);
        }
    }

    public void addTableDefinition(String table, List<DbColumn> dbColumns, List<String> dbPrimaryKeys,
            List<DbExport> dbExports, List<DbImport> dbImports, List<DbIndex> dbIndexes,
            List<DbCheckConstraint> dbCheckConstraints, String comment) {
        debug.debug("addTableDefinition: " + table + " dbColumns " + dbColumns);
        debug.debug("addTableDefinition: " + table + " dbPrimaryKeys " + dbPrimaryKeys);
        debug.debug("addTableDefinition: " + table + " dbExports " + dbExports);
        debug.debug("addTableDefinition: " + table + " dbImports " + dbImports);
        debug.debug("addTableDefinition: " + table + " dbIndexes " + dbIndexes);
        debug.debug("addTableDefinition: " + table + " dbCheckConstraints " + dbCheckConstraints);

        if (table == null || dbColumns == null)
            return;
        Map<String, PojoAttribute> attributes = new LinkedHashMap<String, PojoAttribute>();
        for (DbColumn dbColumn : dbColumns) {
            PojoAttribute attribute = convertDbColumnDefinition(table, dbColumn, columnTypes);
            if (attribute != null) {
                attributes.put(dbColumn.getName(), attribute);
            } else {
                attribute = convertDbColumnDefault(table, dbColumn);
                if (attribute != null)
                    attributes.put(dbColumn.getName(), attribute);
            }
            if (dbPrimaryKeys.contains(dbColumn.getName())) {
                attribute.setPrimaryKey(true);
            }
            if (versionColumn != null && versionColumn.equals(dbColumn.getName())) {
                attribute.setVersion(true);
            } else if (versionColumns.containsKey(dbColumn.getName())
                    && versionColumns.get(dbColumn.getName()).contains(table)) {
                attribute.setVersion(true);
            } else if (notVersionColumns.containsKey(dbColumn.getName())
                    && !notVersionColumns.get(dbColumn.getName()).contains(table)) {
                attribute.setVersion(true);
            }
        }

        if (createColumns.containsKey(table)) {
            for (Map.Entry<String, PojoAttrType> createColumn : createColumns.get(table).entrySet()) {
                PojoAttribute attribute = convertDbColumnDefinition(createColumn.getKey(), createColumn.getValue());
                attributes.put(createColumn.getKey(), attribute);
            }
        }
        pojos.put(table, attributes);
        tables.add(table);
        if (comment != null)
            comments.put(table, comment);

        // pojogen-ignore-one-to-many PERSON ID->PERSON_DETAIL->ID;
        // pojogen-ignore-many-to-one PERSON_DETAIL ID->PERSON->ID;
        // pojogen-create-columns PERSON PERSON_DETAIL->java.lang.Long;
        // pojogen-create-many-to-one PERSON PERSON_DETAIL->PERSON_DETAIL->ID;
        // pojogen-create-columns PERSON_DETAIL PERSON->java.lang.Long;
        // pojogen-create-many-to-one PERSON_DETAIL PERSON->PERSON->ID;
        // pojogen-create-one-to-one PERSON ID->PERSON_DETAIL->ID;
        if (create121Imports.containsKey(table)) {
            for (Map.Entry<String, Map<String, String>> pentry : create121Imports.get(table).entrySet()) {
                for (Entry<String, String> pkEntry : pentry.getValue().entrySet()) {
                    PojoAttribute attribute = convertDbColumnDefinition(pkEntry.getKey(),
                            attributes.get(pentry.getKey()));
                    attribute.setPkTable(pkEntry.getKey());
                    attribute.setPkColumn(pkEntry.getValue());
                    attributes.put(pkEntry.getKey(), attribute);
                }
            }
        }

        for (DbImport dbImport : dbImports) {
            if (ignoreImports.containsKey(table)
                    && (ignoreImports.get(table) == null || ignoreImports.get(table).containsKey(dbImport.getFkColumn())
                            && ignoreImports.get(table).get(dbImport.getFkColumn()).containsKey(dbImport.getPkTable())))
                continue;
            if (manyToManyImports.containsKey(table)) {
                PojoAttribute attribute = attributes.get(dbImport.getFkColumn());
                attribute.setM2mTable(dbImport.getPkTable());
                attribute.setPkColumn(dbImport.getPkColumn());
            } else if (inheritImports.containsKey(table)
                    && inheritImports.get(table).containsKey(dbImport.getFkColumn())
                    && inheritImports.get(table).get(dbImport.getFkColumn()).containsKey(dbImport.getPkTable())) {
                PojoAttribute attribute = attributes.get(dbImport.getFkColumn());
                attribute.setParentTable(dbImport.getPkTable());
                attribute.setPkColumn(dbImport.getPkColumn());
            } else {
                PojoAttribute attribute = attributes.get(dbImport.getFkColumn());
                attribute.setPkTable(dbImport.getPkTable());
                attribute.setPkColumn(dbImport.getPkColumn());
            }
        }
        if (createImports.containsKey(table)) {
            for (Map.Entry<String, Map<String, String>> pentry : createImports.get(table).entrySet()) {
                PojoAttribute attribute = attributes.get(pentry.getKey());
                for (Entry<String, String> pkEntry : pentry.getValue().entrySet()) {
                    attribute.setPkTable(pkEntry.getKey());
                    attribute.setPkColumn(pkEntry.getValue());
                }
            }
        }
        for (DbExport dbExport : dbExports) {
            // AAA1 PERSON_LIBRARY MEDIA_ID MEDIA_ID MEDIA
            // AAA1 PERSON_LIBRARY PERSON_ID PERSON_ID PERSON
            // AAA3 PERSON PERSON ID PERSON_LIBRARY PERSON_ID
            // AAA3 MEDIA MEDIA ID PERSON_LIBRARY MEDIA_ID
            // pojogen table many-to-many PERSON_LIBRARY ID->MEDIA->LIBRARY;
            if (ignoreExports.containsKey(table) && ignoreExports.get(table).containsKey(dbExport.getPkColumn())
                    && ignoreExports.get(table).get(dbExport.getPkColumn()).containsKey(dbExport.getFkTable())) {
                String fkColumn = ignoreExports.get(table).get(dbExport.getPkColumn()).get(dbExport.getFkTable());
                if (fkColumn == null || fkColumn.length() == 0)
                    continue;
                if (fkColumn.equals(dbExport.getFkColumn()))
                    continue;
            }
            if (manyToManyImports.containsKey(dbExport.getFkTable())) {
                if (manyToManyImports.get(dbExport.getFkTable()).containsKey(dbExport.getPkColumn())) {
                    for (Map.Entry<String, String> manyToMany : manyToManyImports.get(dbExport.getFkTable())
                            .get(dbExport.getPkColumn()).entrySet()) {
                        String fkTable = manyToMany.getKey();
                        String fkColumn = manyToMany.getValue();
                        if (!dbExport.getPkTable().equals(fkTable)) {
                            PojoAttribute attribute = attributes.get(dbExport.getPkColumn());
                            attribute.getFkTables().put(fkTable, fkColumn);
                            attribute.getM2Tables().put(fkTable, dbExport.getFkTable());
                        }
                    }
                }
            } else {
                PojoAttribute attribute = attributes.get(dbExport.getPkColumn());
                attribute.getFkTables().put(dbExport.getFkTable(), null);
                if (!attribute.getFkColumns().containsKey(dbExport.getFkTable()))
                    attribute.getFkColumns().put(dbExport.getFkTable(), new ArrayList<String>());
                attribute.getFkColumns().get(dbExport.getFkTable()).add(dbExport.getFkColumn());
            }
        }
        if (createExports.containsKey(table)) {
            for (Map.Entry<String, Map<String, String>> pentry : createExports.get(table).entrySet()) {
                PojoAttribute attribute = attributes.get(pentry.getKey());
                for (Map.Entry<String, String> fk : pentry.getValue().entrySet()) {
                    attribute.getFkTables().put(fk.getKey(), null);
                    if (!attribute.getFkColumns().containsKey(fk.getKey()))
                        attribute.getFkColumns().put(fk.getKey(), new ArrayList<String>());
                    attribute.getFkColumns().get(fk.getKey()).add(fk.getValue());
                }
            }
        }
        if (inheritance.containsKey(table)) {
            // pojogen inherit discriminator BILLING_DETAILS BA->BANK_ACCOUNT->BA_ACCOUNT CC->CREDIT_CARD->CC_NUMBER;
            // pojogen rename columns BANK_ACCOUNT BA_ACCOUNT->ACCOUNT;
            // pojogen rename columns CREDIT_CARD CC_NUMBER->NUMBER;
            Map<String, PojoAttribute> allInheritedAttributes = new LinkedHashMap<String, PojoAttribute>();
            for (Map.Entry<String, Map<String, List<String>>> inheritance1 : inheritance.get(table).entrySet()) {
                String discriminator = inheritance1.getKey();
                Map<String, PojoAttribute> inheritedAttributes = new LinkedHashMap<String, PojoAttribute>();
                for (Map.Entry<String, List<String>> tabcols : inheritance1.getValue().entrySet()) {
                    String inheritedTable = tabcols.getKey();
                    for (String dbColumn : tabcols.getValue()) {
                        if (attributes.containsKey(dbColumn)) {
                            inheritedAttributes.put(dbColumn, attributes.get(dbColumn));
                        }
                    }
                    allInheritedAttributes.putAll(inheritedAttributes);
                    pojos.put(inheritedTable, inheritedAttributes);
                    pojoDiscriminators.put(inheritedTable, discriminator);
                    pojoExtends.put(inheritedTable, table);
                    break;
                }
            }
            for (String dbColumn : allInheritedAttributes.keySet()) {
                attributes.remove(dbColumn);
            }
            pojoInheritanceDiscriminator.put(table, new LinkedHashSet<String>());
        }

        for (int i = 0, l = dbIndexes.size(); i < l; i++) {
            DbIndex dbIndex = dbIndexes.get(i);
            List<Map<PojoAttribute, Boolean>> mainList = indexes.get(table);
            if (mainList == null) {
                mainList = new ArrayList<Map<PojoAttribute, Boolean>>();
                indexes.put(table, mainList);
            }
            Map<PojoAttribute, Boolean> list = new LinkedHashMap<PojoAttribute, Boolean>();
            mainList.add(list);
            for (int ii = 0, ll = dbIndex.getColumns().size(); ii < ll; ii++) {
                DbIndexDetail dbIndexDetail = dbIndex.getColumns().get(ii);
                if (dbIndexDetail == null) {
                    debug.warn("Missing index in " + dbIndex);
                    continue;
                }
                PojoAttribute attr = attributes.get(dbIndexDetail.getColname());
                if (attr == null) {
                    debug.warn("Missing attibute for " + dbIndex);
                    continue;
                }
                list.put(attr, dbIndexDetail.isDesc());
                if (ll == 1) {
                    attr.setIndex(i + 1);
                }
            }
        }

        for (int i = 0, l = dbCheckConstraints.size(); i < l; i++) {
            DbCheckConstraint check = dbCheckConstraints.get(i);
            PojoAttribute attribute = (pojos.containsKey(check.getTable())
                    && pojos.get(check.getTable()).containsKey(check.getColumn()))
                            ? pojos.get(check.getTable()).get(check.getColumn()) : null;
            if (attribute == null) {
                debug.warn("For the constraint " + check.getEnumName() + " there's no table or column");
                continue;
            }
            String name = check.getEnumName();
            if (name.startsWith(check.getTable()) && tableNames.containsKey(check.getTable())) {
                name = tableNames.get(check.getTable()) + name.substring(check.getTable().length());
            }
            boolean firstCheckConstraint = false;
            if (enumForCheckConstraints.containsKey(name)) {
                String enumName = enumForCheckConstraints.get(name);
                attribute.setDependencyClassName(enumName);
                if (enumForCheckConstraints.get(enumName).equals(name))
                    firstCheckConstraint = true;
            } else {
                attribute.setDependencyClassName(tableToCamelCase(getTableName(name)));
                attribute.setDependencyPojo(name);
                firstCheckConstraint = true;
            }
            attribute.setDependencyClassNameIsEnum(true);

            if (!firstCheckConstraint)
                continue;

            List<EnumAttribute> attrs = new ArrayList<EnumAttribute>();
            enums.put(name, attrs);
            enumsTables.put(name, check.getTable());
            EnumAttribute pattr = new EnumAttribute();
            pattr.setName("VALUE");
            pattr.setClassName(attribute.getClassName());
            pattr.setWrapperClassName(attribute.getWrapperClassName());
            attrs.add(pattr);

            for (String val : check.getValues()) {
                EnumAttribute attr = new EnumAttribute();
                attr.setClassName(attribute.getClassName());
                if (attribute.isString()) {
                    attr.setStrValue(val);
                    if (val.isEmpty())
                        continue;
                    else if (Character.isDigit(val.charAt(0)))
                        attr.setName("I" + val);
                    else
                        attr.setName(val);
                } else {
                    attr.setIntValue(val);
                    attr.setName("I" + val);
                }
                attrs.add(attr);
            }
        }
    }

    public void joinTables() {
        for (String table : joinTables.keySet()) {
            if (!pojos.containsKey(table))
                continue;
            Stack<String> stack = new Stack<String>();
            stack.push(table);
            for (String table1 : joinTables.get(table)) {
                if (!pojos.containsKey(table1))
                    break;
                stack.push(table1);
            }
            if (stack.size() != joinTables.get(table).size() + 1)
                continue;

            String table0 = stack.pop();
            Set<String> allAttributes = new HashSet<String>();
            allAttributes.addAll(pojos.get(table0).keySet());
            if (tableNames.containsKey(table0))
                table0 = tableNames.get(table0);
            while (stack.size() > 0) {
                String table1 = stack.pop();
                String newTable = (tableNames.containsKey(table1) ? tableNames.get(table1) : table1) + "_" + table0;
                Map<String, PojoAttribute> newAttributes = new LinkedHashMap<String, PojoAttribute>();
                for (Entry<String, PojoAttribute> attr : pojos.get(table1).entrySet()) {
                    if (!allAttributes.contains(attr.getKey())) {
                        newAttributes.put(attr.getKey(), attr.getValue());
                        allAttributes.add(attr.getKey());
                    }
                }
                pojos.put(newTable, newAttributes);
                pojoExtends.put(newTable, table0);
                if (!onlyTables.isEmpty())
                    onlyTables.add(newTable);
                table0 = newTable;
            }
        }
    }

    protected String getTableName(String name) {
        String realName = tableNames.get(name);
        return (realName != null) ? realName : name;
    }

    public void resolveReferencesOnKeys() {

        for (String pojo : pojos.keySet()) {
            Map<String, PojoAttribute> newAttributes = new TreeMap<String, PojoAttribute>();
            for (Entry<String, PojoAttribute> entry : pojos.get(pojo).entrySet()) {
                PojoAttribute attribute = entry.getValue();
                if (attribute.getPkTable() != null) {
                    if (pojos.containsKey(attribute.getPkTable())) {
                        attribute.setDependencyClassName(tableToCamelCase(getTableName(attribute.getPkTable())));
                        attribute.setDependencyPojo(attribute.getPkTable());
                        attribute.setRef(attribute.getPkTable());
                        if (attribute.getName().length() >= 3) {
                            if (attribute.getName().startsWith("id")) {
                                attribute.setName(lowerFirstChar(attribute.getName().substring(2)));
                            } else if (attribute.getName().endsWith("Id")) {
                                attribute.setName(attribute.getName().substring(0, attribute.getName().length() - 2));
                            }
                        }
                    }
                }
                if (attribute.getParentTable() != null) {
                    if (pojos.containsKey(attribute.getParentTable())) {
                        pojoExtends.put(pojo, attribute.getParentTable());
                        pojoInheritanceSimple.put(attribute.getParentTable(), new LinkedHashSet<String>());
                    }
                }
                for (Map.Entry<String, String> fk : attribute.getFkTables().entrySet()) {
                    if (pojos.containsKey(fk.getKey())) {
                        if (attribute.getFkColumns().containsKey(fk.getKey())
                                && attribute.getFkColumns().get(fk.getKey()).size() > 1) {
                            for (String fkColumn : attribute.getFkColumns().get(fk.getKey())) {
                                PojoAttribute attrib = new PojoAttribute(null);
                                attrib.setName(collectionName(fk.getKey(), fkColumn));
                                attrib.setOneToManyColumn(entry.getKey());
                                attrib.setOneToManyOppositeColumn(fkColumn);
                                attrib.setOneToManyTable(fk.getKey());
                                attrib.setClassName(
                                        COLLECTION_LIST + " <" + tableToCamelCase(getTableName(fk.getKey())) + ">");
                                attrib.setRef(fk.getKey());
                                String dbColumnName = columnToDbConv(attrib.getName());
                                newAttributes.put(dbColumnName, attrib);
                            }
                        }
                        String referName = null;
                        if (fk.getValue() != null) {
                            referName = columnToCamelCase(fk.getValue());
                        } else if (attribute.getFkColumns().containsKey(fk.getKey())
                                && attribute.getFkColumns().get(fk.getKey()).size() > 1) {
                            continue;
                        } else {
                            referName = collectionName(fk.getKey(), null);
                        }
                        PojoAttribute attrib = new PojoAttribute(null);
                        attrib.setName(referName);
                        if (attribute.getM2Tables().containsKey(fk.getKey())) {
                            attrib.setManyToManyColumn(entry.getKey());
                            attrib.setManyToManyTable(fk.getKey());
                        } else if (attribute.getFkColumns().containsKey(fk.getKey())) {
                            attrib.setOneToManyColumn(entry.getKey());
                            attrib.setOneToManyOppositeColumn(attribute.getFkColumns().get(fk.getKey()).get(0));
                            attrib.setOneToManyTable(fk.getKey());
                        }
                        attrib.setClassName(COLLECTION_LIST + " <" + tableToCamelCase(getTableName(fk.getKey())) + ">");
                        attrib.setRef(fk.getKey());
                        String dbColumnName = columnToDbConv(attrib.getName());
                        newAttributes.put(dbColumnName, attrib);
                    }
                }
            }
            if (!newAttributes.isEmpty())
                pojos.get(pojo).putAll(newAttributes);
        }
        for (Entry<String, String> entry : pojoExtends.entrySet()) {
            if (pojoInheritanceDiscriminator.containsKey(entry.getValue())) {
                pojoInheritanceDiscriminator.get(entry.getValue()).add(entry.getKey());
            } else if (pojoInheritanceSimple.containsKey(entry.getValue())) {
                pojoInheritanceSimple.get(entry.getValue()).add(entry.getKey());
            }
        }
    }

    public static final String FAKE_FUN_PROC_COLUMN_NAME = "_result_";
    public static final String FUN_PROC_COLUMN_NAME = "RESULT";

    // procedureColumnUnknown - nobody knows 0
    // procedureColumnIn - IN parameter 1
    // procedureColumnInOut - INOUT parameter 2
    // procedureColumnOut - OUT parameter 4
    // procedureColumnReturn - procedure return value 5
    // procedureColumnResult - result column in ResultSet 3

    public void addProcedureDefinition(String procedure, DbTable dbProcedure, List<DbColumn> dbProcColumns,
            boolean isFunction, String comment) {
        debug.debug("addProcedureDefinition: " + procedure + " dbProcedure " + dbProcedure);
        debug.debug("addProcedureDefinition: " + procedure + " dbProcColumns " + dbProcColumns);
        if (procedure == null || dbProcColumns == null)
            return;
        Map<String, PojoAttribute> attributes = new LinkedHashMap<String, PojoAttribute>();
        Map<String, PojoAttribute> attributesResultSet = new LinkedHashMap<String, PojoAttribute>();
        int ix = 0;
        for (DbColumn dbColumn : dbProcColumns) {
            ix++;
            if ((dbColumn.getColumnType() == 3 || dbColumn.getColumnType() == 5) && (dbColumn.getName() == null
                    || dbColumn.getName().trim().length() == 0 || dbColumn.getName().equalsIgnoreCase("returnValue")
                    || dbColumn.getName().equalsIgnoreCase("RETURN_VALUE")
                    || dbColumn.getName().equalsIgnoreCase("null"))) {
                dbColumn.setName(FAKE_FUN_PROC_COLUMN_NAME);
            }
            if (dbType == DbType.INFORMIX && ix == 1 && isFunction
                    && !FAKE_FUN_PROC_COLUMN_NAME.equals(dbColumn.getName()))
                continue;
            PojoAttribute attribute = convertDbColumnDefinition(procedure, dbColumn, procedureTypes);
            if (attribute == null)
                attribute = convertDbColumnDefault(procedure, dbColumn);
            if (attribute != null) {
                if (!FAKE_FUN_PROC_COLUMN_NAME.equals(dbColumn.getName())
                        && (dbColumn.getColumnType() == 3 || dbColumn.getColumnType() == 5))
                    attributesResultSet.put(dbColumn.getName(), attribute);
                else
                    attributes.put(dbColumn.getName(), attribute);
                attribute.setFunProcType(dbProcedure.getFtype());
                attribute.setFunProcColumnType(dbColumn.getColumnType());
            }
            if (FAKE_FUN_PROC_COLUMN_NAME.equals(dbColumn.getName()) && isFunction
                    && attribute.getClassName() != null) {
                String metaType = className2metaType(attribute.getClassName());
                if (metaType != null)
                    metaFunctionsResult.put(procedure, metaType);
            } else if (FAKE_FUN_PROC_COLUMN_NAME.equals(dbColumn.getName()) && attribute.getClassName() != null
                    && (dbColumn.getColumnType() == 3 || dbColumn.getColumnType() == 5)) {
                String metaType = className2metaType(attribute.getClassName());
                if (metaType != null)
                    metaProceduresResult.put(procedure, metaType);
            }
        }
        if (!attributesResultSet.isEmpty()) {
            debug.warn("TODO " + procedure + " returns " + attributesResultSet);
            String procedureResult = procedure + "_RESULT";
            pojos.put(procedureResult, attributesResultSet);
            metaProceduresResultSet.put(procedure, procedureResult);
        }
        if (createColumns.containsKey(procedure)) {
            for (Map.Entry<String, PojoAttrType> createColumn : createColumns.get(procedure).entrySet()) {
                PojoAttribute attribute = convertDbColumnDefinition(createColumn.getKey(), createColumn.getValue());
                attributes.put(createColumn.getKey(), attribute);
                attribute.setFunProcType(dbProcedure.getFtype());
                attribute.setFunProcColumnType((short) 1);
            }
        }
        procedures.put(procedure, attributes);
        if (comment != null)
            comments.put(procedure, comment);
        if ((dbType == DbType.POSTGRESQL || dbType == DbType.INFORMIX) && isFunction)
            functions.put(procedure, attributes);
    }

    // functionColumnUnknown - nobody knows 0
    // functionColumnIn - IN parameter 1
    // functionColumnInOut - INOUT parameter 2
    // functionColumnOut - OUT parameter 3
    // functionColumnReturn - function return value 4
    // functionColumnResult - Indicates that the parameter or column is a column in the ResultSet 5

    public void addFunctionDefinition(String function, DbTable dbFunction, List<DbColumn> dbFunColumns,
            String comment) {
        debug.debug("addFunctionDefinition: " + function + " dbFunction " + dbFunction);
        debug.debug("addFunctionDefinition: " + function + " dbFunColumns " + dbFunColumns);
        if (function == null || dbFunColumns == null)
            return;
        Map<String, PojoAttribute> attributes = new LinkedHashMap<String, PojoAttribute>();
        for (DbColumn dbColumn : dbFunColumns) {
            PojoAttribute attribute = convertDbColumnDefinition(function, dbColumn, functionTypes);
            if (attribute != null) {
                attributes.put(dbColumn.getName(), attribute);
            } else {
                attribute = convertDbColumnDefault(function, dbColumn);
                if (attribute != null)
                    attributes.put(dbColumn.getName(), attribute);
            }
            if (attribute != null) {
                attribute.setFunProcType(dbFunction.getFtype());
                attribute.setFunProcColumnType(dbColumn.getColumnType());
            }
        }
        if (createColumns.containsKey(function)) {
            for (Map.Entry<String, PojoAttrType> createColumn : createColumns.get(function).entrySet()) {
                PojoAttribute attribute = convertDbColumnDefinition(createColumn.getKey(), createColumn.getValue());
                attributes.put(createColumn.getKey(), attribute);
                attribute.setFunProcType(dbFunction.getFtype());
                attribute.setFunProcColumnType((short) 1);
            }
        }
        if (dbType == DbType.DB2 && metaFunctionsResult.containsKey(function)) {
            PojoAttribute attribute = convertDbColumnDefinition(FUN_PROC_COLUMN_NAME,
                    metaFunctionsResult.get(function));
            attributes.put(FUN_PROC_COLUMN_NAME, attribute);
            attribute.setFunProcType(dbFunction.getFtype());
            attribute.setFunProcColumnType((short) 5);
        }
        functions.put(function, attributes);
        if (comment != null)
            comments.put(function, comment);
    }

    protected String collectionName(String fkTable, String fkColumn) {
        if (tableNames.containsKey(fkTable))
            fkTable = tableNames.get(fkTable);
        String referName = (fkColumn == null) ? lowerFirstChar(tableToCamelCase(fkTable)) : tableToCamelCase(fkTable);
        if (!referName.endsWith("s")) {
            if (referName.endsWith("y")) {
                referName = referName.substring(0, referName.length() - 1);
                referName += "ies";
            } else {
                referName += "s";
            }
        }
        if (fkColumn != null) {
            referName = lowerFirstChar(tableToCamelCase(fkColumn)) + referName;
        }
        return referName;
    }

    protected String lowerFirstChar(String s) {
        if (s == null)
            return null;
        String ss = s.substring(0, 1).toLowerCase();
        if (s.length() == 1)
            return ss;
        ss += s.substring(1);
        return ss;
    }

    public void resolveReferencesOnConvention() {
        for (String pojo : pojos.keySet()) {
            for (PojoAttribute attribute : pojos.get(pojo).values()) {
                if (attribute.getName().startsWith("id") && attribute.getName().length() > 2) {
                    String className = attribute.getName().substring(2);
                    if (pojo.equals(className))
                        continue;
                    Map<String, PojoAttribute> referPojoAttr = pojos.get(className);
                    if (referPojoAttr != null) {
                        String name = className.substring(0, 1).toLowerCase();
                        if (className.length() > 1)
                            name += className.substring(1);
                        attribute.setName(name);
                        attribute.setPrimitive(false);
                        attribute.setDependencyClassName(className);
                        // reverse dependency
                        String referName = pojo.substring(0, 1).toLowerCase();
                        if (className.length() > 1)
                            referName += pojo.substring(1);
                        PojoAttribute attrib = new PojoAttribute(null);
                        attrib.setName(referName + "s");
                        attrib.setClassName(COLLECTION_LIST + " <" + pojo + ">");
                        referPojoAttr.put(attrib.getName(), attrib);
                    }
                }
            }
        }
    }

    protected String nlindent() {
        return doCompressMetaDirectives ? " " : NLINDENT;
    }

    protected String nlindent2() {
        return doCompressMetaDirectives ? " " : NLINDENTINDENT;
    }

    protected String indent() {
        return doCompressMetaDirectives ? " " : INDENT;
    }

    protected void appendList(StringBuilder buffer, Set<String> list) {
        boolean first = true;
        for (String name : list) {
            if (!first)
                buffer.append(",");
            else
                first = false;
            buffer.append(name);
        }
    }

    protected void getParentAttrs(String pojo, Set<String> isDef, Set<String> toInit, Set<String> toStr) {
        for (Map.Entry<String, PojoAttribute> pentry : pojos.get(pojo).entrySet()) {
            if (ignoreColumns.containsKey(pojo) && ignoreColumns.get(pojo).contains(pentry.getKey()))
                continue;
            PojoAttribute attribute = pentry.getValue();
            String name = (columnNames.containsKey(pojo)) ? columnNames.get(pojo).get(pentry.getKey()) : null;
            if (name == null)
                name = attribute.getName();
            else
                name = columnToCamelCase(name);
            if (isDef != null && attribute.isDef())
                isDef.add(name);
            if (toInit != null && attribute.toInit())
                toInit.add(name);
            if (toStr != null) {
                if (attribute.getDependencyClassName() != null) {
                    if (attribute.isDependencyClassNameIsEnum())
                        toStr.add(name);
                } else if (attribute.isPrimitive()) {
                    toStr.add(name);
                } else {
                    if (!attribute.getClassName().startsWith(COLLECTION_LIST))
                        toStr.add(name);
                }
            }
        }
        if (pojoExtends.containsKey(pojo)) {
            getParentAttrs(pojoExtends.get(pojo), isDef, toInit, toStr);
        }
    }

    protected static final String LIBRARY_GUAVA = "GUAVA";

    // TODO
    protected String tableToCamelCase(String value) {
        if (LIBRARY_GUAVA.equalsIgnoreCase(caseFormatLibrary)) {
            if (Character.isUpperCase(value.charAt(0)))
                return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, value);
            else
                return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, value);
        } else
            return _tableToCamelCase(value);
    }

    protected String _tableToCamelCase(String value) {
        if (value == null)
            return null;
        String[] parts = value.split("_");
        String camelCaseString = "";
        for (String part : parts) {
            if (camelCaseString.length() == 0)
                if (part.length() > 1)
                    camelCaseString = camelCaseString + part.substring(0, 1).toUpperCase()
                            + part.substring(1).toLowerCase();
                else
                    camelCaseString = camelCaseString + part.toUpperCase();
            else if (part.length() == 1)
                camelCaseString = camelCaseString + part.toUpperCase();
            else if (part.length() > 1)
                camelCaseString = camelCaseString + part.substring(0, 1).toUpperCase()
                        + part.substring(1).toLowerCase();
        }
        return camelCaseString;
    }

    // TODO
    protected String columnToCamelCase(String value) {
        if (LIBRARY_GUAVA.equalsIgnoreCase(caseFormatLibrary)) {
            if (Character.isUpperCase(value.charAt(0)))
                return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, value);
            else
                return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, value);
        } else
            return _columnToCamelCase(value);
    }

    protected String _columnToCamelCase(String value) {
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

    // TODO
    protected String columnToDbConv(String value) {
        if (LIBRARY_GUAVA.equalsIgnoreCase(caseFormatLibrary)) {
            if (Character.isUpperCase(value.charAt(0)))
                return CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, value);
            else
                return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, value);
        } else
            return _columnToDbConv(value);
    }

    protected String _columnToDbConv(String value) {
        if (value == null)
            return null;
        String result = "";
        int last = 0;
        for (int i = 0, l = value.length(); i < l; i++) {
            if (Character.isUpperCase(value.charAt(i))) {
                result = result + value.substring(last, i).toUpperCase() + "_";
                last = i;
            }
        }
        if (last < value.length())
            result = result + value.substring(last).toUpperCase();
        return result;
    }

    protected PojoAttribute convertDbColumnDefinition(String dbName, PojoAttrType sqlType) {
        PojoAttribute attribute = new PojoAttribute(dbName);
        attribute.setName(columnToCamelCase(dbName));
        attribute.setPrimitive(sqlType.isNativeType());
        attribute.setClassName(sqlType.getQualifiedName());
        return attribute;
    }

    protected PojoAttribute convertDbColumnDefinition(String dbName, String metaType) {
        PojoAttribute attribute = new PojoAttribute(dbName);
        attribute.setName(columnToCamelCase(dbName));
        attribute.setPrimitive(false);
        attribute.setClassName(metaType2className(metaType));
        return attribute;
    }

    protected PojoAttribute convertDbColumnDefinition(String dbName, PojoAttribute pkAttribute) {
        PojoAttribute attribute = new PojoAttribute(dbName);
        attribute.setName(columnToCamelCase(dbName));
        attribute.setPrimitive(pkAttribute.isPrimitive());
        attribute.setClassName(pkAttribute.getClassName());
        attribute.setOne2one(pkAttribute.getDbName());
        return attribute;
    }

    protected PojoAttribute convertDbColumnDefinition(String table, DbColumn dbColumn,
            Map<String, Map<String, PojoAttrType>> redefinedTypes) {
        if (dbColumn == null)
            return null;
        PojoAttrType sqlType = redefinedTypes.containsKey(table) ? redefinedTypes.get(table).get(dbColumn.getName())
                : null;
        if (sqlType == null)
            sqlType = tableTypes.containsKey(table) ? tableTypes.get(table).get(dbColumn.getCompleteType()) : null;
        if (sqlType == null)
            sqlType = sqlTypes.get(dbColumn.getCompleteType());
        if (sqlType == null)
            return null;
        PojoAttribute attribute = new PojoAttribute(dbColumn.getName());
        attribute.setName(columnToCamelCase(dbColumn.getName()));
        attribute.setRequired(!dbColumn.isNullable());
        attribute.setPrimitive(sqlType.isNativeType());
        attribute.setClassName(sqlType.getQualifiedName());
        attribute.setSqlType(dbColumn.getSqlType());
        attribute.setCompleteSqlType(dbColumn.getCompleteType());
        attribute.setSize(dbColumn.getSize());
        attribute.setComment(dbColumn.getComment());
        attribute.setDefaultValue(dbColumn.getDefaultValue());
        return attribute;
    }

    protected PojoAttribute convertDbColumnDefault(String table, DbColumn dbColumn) {
        if (dbColumn == null)
            return null;
        PojoAttribute attribute = new PojoAttribute(dbColumn.getName());
        attribute.setName(columnToCamelCase(dbColumn.getName()));
        attribute.setRequired(!dbColumn.isNullable());
        switch (dbColumn.getSqlType()) {
        case Types.BIT:
        case Types.BOOLEAN:
            if (dbColumn.isNullable() || doGenerateWrappers) {
                attribute.setPrimitive(false);
                attribute.setClassName(Boolean.class.getName());
                attribute.setWrapperClassName(Boolean.class.getName());
            } else {
                attribute.setPrimitive(true);
                attribute.setClassName(PrimitiveType.BOOLEAN.getName());
                attribute.setWrapperClassName(Boolean.class.getName());
            }
            break;
        case Types.TINYINT:
            if (dbColumn.isNullable() || doGenerateWrappers) {
                attribute.setPrimitive(false);
                attribute.setClassName(Byte.class.getName());
                attribute.setWrapperClassName(Byte.class.getName());
            } else {
                attribute.setPrimitive(true);
                attribute.setClassName(PrimitiveType.BYTE.getName());
                attribute.setWrapperClassName(Byte.class.getName());
            }
            break;
        case Types.SMALLINT:
            if (dbColumn.isNullable() || doGenerateWrappers) {
                attribute.setPrimitive(false);
                attribute.setClassName(Short.class.getName());
                attribute.setWrapperClassName(Short.class.getName());
            } else {
                attribute.setPrimitive(true);
                attribute.setClassName(PrimitiveType.SHORT.getName());
                attribute.setWrapperClassName(Short.class.getName());
            }
            break;
        case Types.INTEGER:
            if (dbColumn.isNullable() || doGenerateWrappers) {
                attribute.setPrimitive(false);
                attribute.setClassName(Integer.class.getName());
                attribute.setWrapperClassName(Integer.class.getName());
            } else {
                attribute.setPrimitive(true);
                attribute.setClassName(PrimitiveType.INT.getName());
                attribute.setWrapperClassName(Integer.class.getName());
            }
            break;
        case Types.BIGINT:
            if (dbColumn.isNullable() || doGenerateWrappers) {
                attribute.setPrimitive(false);
                attribute.setClassName(Long.class.getName());
                attribute.setWrapperClassName(Long.class.getName());
            } else {
                attribute.setPrimitive(true);
                attribute.setClassName(PrimitiveType.LONG.getName());
                attribute.setWrapperClassName(Long.class.getName());
            }
            break;
        case Types.FLOAT:
        case Types.REAL:
            if (dbColumn.isNullable() || doGenerateWrappers) {
                attribute.setPrimitive(false);
                attribute.setClassName(Float.class.getName());
                attribute.setWrapperClassName(Float.class.getName());
            } else {
                attribute.setPrimitive(true);
                attribute.setClassName(PrimitiveType.FLOAT.getName());
                attribute.setWrapperClassName(Float.class.getName());
            }
            break;
        case Types.DOUBLE:
            if (dbColumn.isNullable() || doGenerateWrappers) {
                attribute.setPrimitive(false);
                attribute.setClassName(Double.class.getName());
                attribute.setWrapperClassName(Double.class.getName());
            } else {
                attribute.setPrimitive(true);
                attribute.setClassName(PrimitiveType.DOUBLE.getName());
                attribute.setWrapperClassName(PrimitiveType.DOUBLE.getName());
            }
            break;
        case Types.NUMERIC:
            attribute.setPrimitive(false);
            attribute.setClassName(BigDecimal.class.getName());
            attribute.setWrapperClassName(attribute.getClassName());
            break;
        case Types.DECIMAL:
            attribute.setPrimitive(false);
            if (dbColumn.getSize() < 3)
                attribute.setClassName(Byte.class.getName());
            else if (dbColumn.getSize() < 5)
                attribute.setClassName(Short.class.getName());
            else if (dbColumn.getSize() < 10)
                attribute.setClassName(Integer.class.getName());
            else if (dbColumn.getSize() < 20)
                attribute.setClassName(Long.class.getName());
            else
                attribute.setClassName(BigInteger.class.getName());
            attribute.setWrapperClassName(attribute.getClassName());
            break;
        case Types.CHAR:
        case Types.NCHAR:
            if (dbColumn.isNullable() || dbColumn.getSize() > 1 || doGenerateWrappers) {
                attribute.setPrimitive(false);
                attribute.setClassName(String.class.getName());
                attribute.setWrapperClassName(String.class.getName());
            } else {
                attribute.setPrimitive(true);
                attribute.setClassName(PrimitiveType.CHAR.getName());
                attribute.setWrapperClassName(String.class.getName());
            }
            break;
        case Types.VARCHAR:
        case Types.LONGNVARCHAR:
        case Types.NVARCHAR:
            attribute.setPrimitive(false);
            attribute.setClassName(String.class.getName());
            attribute.setWrapperClassName(String.class.getName());
            break;
        case Types.DATE:
            // java.util.Date or java.sql.Date ?
            attribute.setPrimitive(false);
            attribute.setClassName(java.util.Date.class.getName());
            attribute.setWrapperClassName(java.util.Date.class.getName());
            break;
        case Types.TIME:
            attribute.setPrimitive(false);
            attribute.setClassName(java.sql.Time.class.getName());
            attribute.setWrapperClassName(java.sql.Time.class.getName());
            break;
        case Types.TIMESTAMP:
            // java.util.Date or java.sql.Timestamp ?
            attribute.setPrimitive(false);
            attribute.setClassName(java.sql.Timestamp.class.getName());
            attribute.setWrapperClassName(java.sql.Timestamp.class.getName());
            break;
        case Types.BINARY:
        case Types.VARBINARY:
        case Types.LONGVARBINARY:
        case Types.BLOB:
            attribute.setPrimitive(true);
            attribute.setClassName(PrimitiveType.BYTE_ARRAY.getName());
            attribute.setWrapperClassName(PrimitiveType.BYTE_ARRAY.getName());
            break;
        case Types.CLOB:
            attribute.setPrimitive(false);
            attribute.setClassName(String.class.getName());
            attribute.setWrapperClassName(String.class.getName());
            break;
        default:
            // todo what type?
            attribute.setPrimitive(false);
            if (dbColumn.getType().indexOf("TIMESTAMP") == 0 || dbColumn.getType().indexOf("timestamp") == 0)
                attribute.setClassName(java.sql.Timestamp.class.getName());
            else
                attribute.setClassName("java.lang.Object");
            attribute.setWrapperClassName(attribute.getClassName());
        }
        attribute.setSqlType(dbColumn.getSqlType());
        attribute.setCompleteSqlType(dbColumn.getCompleteType());
        attribute.setSize(dbColumn.getSize());
        attribute.setComment(dbColumn.getComment());
        attribute.setDefaultValue(dbColumn.getDefaultValue());
        return attribute;
    }

    protected static Map<String, String> metaType2classNameMap = new LinkedHashMap<String, String>();

    static {
        metaType2classNameMap.put("stamp", java.sql.Timestamp.class.getName());
        metaType2classNameMap.put("timestamp", java.sql.Timestamp.class.getName());
        metaType2classNameMap.put("date", java.sql.Date.class.getName());
        metaType2classNameMap.put("datetime", java.util.Date.class.getName());
        metaType2classNameMap.put("time", java.sql.Time.class.getName());
        metaType2classNameMap.put("byte", java.lang.Byte.class.getName());
        metaType2classNameMap.put("boolean", java.lang.Boolean.class.getName());
        metaType2classNameMap.put("bool", java.lang.Boolean.class.getName());
        metaType2classNameMap.put("character", java.lang.Character.class.getName());
        metaType2classNameMap.put("char", java.lang.Character.class.getName());
        metaType2classNameMap.put("short", java.lang.Short.class.getName());
        metaType2classNameMap.put("int", java.lang.Integer.class.getName());
        metaType2classNameMap.put("integer", java.lang.Integer.class.getName());
        metaType2classNameMap.put("long", java.lang.Long.class.getName());
        metaType2classNameMap.put("string", java.lang.String.class.getName());
        metaType2classNameMap.put("str", java.lang.String.class.getName());
        metaType2classNameMap.put("text", java.lang.String.class.getName());
        metaType2classNameMap.put("double", java.lang.Double.class.getName());
        metaType2classNameMap.put("float", java.lang.Float.class.getName());
        metaType2classNameMap.put("bigint", java.math.BigInteger.class.getName());
        metaType2classNameMap.put("biginteger", java.math.BigInteger.class.getName());
        metaType2classNameMap.put("bigdec", java.math.BigDecimal.class.getName());
        metaType2classNameMap.put("bigdecimal", java.math.BigDecimal.class.getName());
        metaType2classNameMap.put("blob", java.sql.Blob.class.getName());
        metaType2classNameMap.put("clob", java.sql.Clob.class.getName());
        metaType2classNameMap.put("bytes", java.lang.Byte[].class.getName());
        metaType2classNameMap.put("bytearr", java.lang.Byte[].class.getName());
        metaType2classNameMap.put("bytearray", java.lang.Byte[].class.getName());
    }

    protected String metaType2className(String metaType) {
        if (metaType == null)
            return null;
        String className = metaType2classNameMap.get(metaType.toLowerCase());
        if (className == null)
            return metaType;
        return className;
    }

    protected static Map<String, String> className2metaTypeMap = new HashMap<String, String>();

    static {
        for (Entry<String, String> entry : metaType2classNameMap.entrySet()) {
            String value = entry.getValue();
            if (!className2metaTypeMap.containsKey(value)) {
                className2metaTypeMap.put(value, entry.getKey());
                if (value.startsWith("java.lang."))
                    className2metaTypeMap.put(value.substring(10), entry.getKey());
            }
        }
    }

    protected String className2metaType(String className) {
        if (className == null)
            return null;
        String metaType = className2metaTypeMap.get(className);
        if (metaType == null)
            return null;
        return metaType;
    }

    protected void printComment(StringBuilder buffer, String comment, String... indents) {
        if (comment == null || comment.trim().length() == 0)
            return;
        for (int l = comment.length(); l > 0; l = comment.length()) {
            buffer.append("\n");
            for (String indent : indents)
                buffer.append(indent);
            buffer.append("// ");
            if (l > 100) {
                buffer.append(comment.substring(0, 100));
                comment = comment.substring(100);
            } else {
                buffer.append(comment);
                comment = "";
            }
        }
    }

    protected String constName(PojoAttribute attr) {
        return constantName(attr.getName());
    }

    protected String constName(Map<PojoAttribute, Boolean> l) {
        StringBuilder result = new StringBuilder("");
        boolean first = true;
        for (Entry<PojoAttribute, Boolean> e : l.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("_");
            }
            result.append(constantName(e.getKey().getName()));
        }
        return result.toString();
    }

    protected String constantName(String name) {
        StringBuilder result = new StringBuilder("");
        for (Character c : name.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append("_");
                result.append(c);
            } else {
                result.append(Character.toUpperCase(c));
            }
        }
        return result.toString();
    }

    // meta filter only-insert,get,update,delete,select,call add-filter XXXX
    public static class Filter {
        static final String ONLY_TABLE = "only-table";
        static final String ONLY_TABLES = "only-tables";
        static final String ADD = "add";
        static final String ADD_FILTER = "add-filter";
        Map<String, String> filters;
        Set<String> onlyTables;

        public static Filter parse(String s) {
            if (s == null)
                return null;
            if (s.startsWith("\""))
                s = s.substring(1);
            if (s.endsWith("\""))
                s = s.substring(0, s.length() - 1);
            Filter f = new Filter();
            f.filters = new HashMap<String, String>();
            f.onlyTables = new HashSet<String>();
            String[] ss = s.split(" ");
            boolean isFilter = false;
            boolean isTable = false;
            boolean isTables = false;
            for (String s1 : ss) {
                if (isFilter) {
                    f.filters.put(ADD, s1);
                    isFilter = false;
                } else if (isTable) {
                    f.onlyTables.add(s1);
                    isTable = false;
                } else if (isTables) {
                    String[] tt = s1.split(",");
                    for (String t1 : tt)
                        f.onlyTables.add(t1);
                    isTables = false;
                } else if (s1.equalsIgnoreCase(ADD_FILTER)) {
                    isFilter = true;
                } else if (s1.equalsIgnoreCase(ONLY_TABLE)) {
                    isTable = true;
                } else if (s1.equalsIgnoreCase(ONLY_TABLES)) {
                    isTables = true;
                }
            }
            return f;
        }

        public static boolean isTable(Filter f, String table) {
            if (f == null || f.onlyTables.isEmpty())
                return true;
            return f.onlyTables.contains(table);
        }

        static String get(Filter f, String what) {
            if (f == null || !f.filters.containsKey(what))
                return null;
            return f.filters.get(what);
        }

        @Override
        public String toString() {
            return "Filter [filters=" + filters + "]";
        }
    }

    protected boolean addDefinitions(DbResolver dbResolver, IScopeProvider scopeProvider, Stats stats) {
        for (TableDefinition _table : modelTables.values()) {
            String table = _table.getTable();
            if (table == null)
                continue;
            if (table.toUpperCase().startsWith("BIN$"))
                continue;
            if (!dbResolver.checkTable(model, table))
                continue;
            List<DbColumn> dbColumns = dbResolver.getDbColumns(model, table);
            if (dbColumns.isEmpty())
                continue;
            stats.tables += 1;
            stats.columns += dbColumns.size();
            List<String> dbPrimaryKeys = dbResolver.getDbPrimaryKeys(model, table);
            stats.primaryKeys += dbPrimaryKeys.size();
            List<DbExport> dbExports = dbResolver.getDbExports(model, table);
            stats.exports += dbExports.size();
            List<DbImport> dbImports = dbResolver.getDbImports(model, table);
            stats.imports += dbImports.size();
            List<DbIndex> dbIndexes = dbResolver.getDbIndexes(model, table);
            stats.indexes += dbIndexes.size();
            List<DbTable> ltables = dbResolver.getDbTables(model, table);
            String comment = (ltables != null && !ltables.isEmpty()) ? ltables.get(0).getComment() : null;
            List<DbCheckConstraint> dbCheckConstraints = dbResolver.getDbCheckConstraints(model, table);
            stats.checkConstraints += dbCheckConstraints.size();
            addTableDefinition(table, dbColumns, dbPrimaryKeys, dbExports, dbImports, dbIndexes, dbCheckConstraints,
                    comment);
        }
        // converter.resolveReferencesOnConvention();
        resolveReferencesOnKeys();
        joinTables();
        for (ProcedureDefinition _procedure : modelProcedures.values()) {
            String procedure = _procedure.getProcedure();
            if (procedure == null)
                continue;
            if (procedure.toUpperCase().startsWith("BIN$"))
                continue;
            List<DbTable> dbProcedures = dbResolver.getDbProcedures(model, procedure);
            if (dbProcedures.isEmpty())
                continue;
            stats.procedures += 1;
            List<DbColumn> dbProcColumns = dbResolver.getDbProcColumns(model, procedure);
            stats.procColumns += dbProcColumns.size();
            List<DbTable> ltables = dbResolver.getDbProcedures(model, procedure);
            String comment = (ltables != null && !ltables.isEmpty()) ? ltables.get(0).getComment() : null;
            addProcedureDefinition(procedure, dbProcedures.get(0), dbProcColumns,
                    modelFunctions.containsKey(_procedure.getName()), comment);
        }
        for (FunctionDefinition _function : modelFunctions.values()) {
            String function = _function.getFunction();
            if (function == null)
                continue;
            if (function.toUpperCase().startsWith("BIN$"))
                continue;
            List<DbTable> dbFunctions = dbResolver.getDbFunctions(model, function);
            if (dbFunctions.isEmpty())
                continue;
            stats.functions += 1;
            List<DbColumn> dbFunColumns = dbResolver.getDbFunColumns(model, function);
            stats.funColumns += dbFunColumns.size();
            List<DbTable> ltables = dbResolver.getDbFunctions(model, function);
            String comment = (ltables != null && !ltables.isEmpty()) ? ltables.get(0).getComment() : null;
            addFunctionDefinition(function, dbFunctions.get(0), dbFunColumns, comment);
        }
        return true;
    }
}
