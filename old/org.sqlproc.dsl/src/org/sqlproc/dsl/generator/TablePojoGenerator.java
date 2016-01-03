package org.sqlproc.dsl.generator;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Types;
import java.util.ArrayList;
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
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.sqlproc.dsl.processorDsl.Artifacts;
import org.sqlproc.dsl.processorDsl.PojoType;
import org.sqlproc.dsl.processorDsl.ProcessorDslPackage;
import org.sqlproc.dsl.property.EnumAttribute;
import org.sqlproc.dsl.property.ImplementsExtends;
import org.sqlproc.dsl.property.ModelProperty;
import org.sqlproc.dsl.property.PojoAttrType;
import org.sqlproc.dsl.property.PojoAttribute;
import org.sqlproc.dsl.resolver.DbCheckConstraint;
import org.sqlproc.dsl.resolver.DbColumn;
import org.sqlproc.dsl.resolver.DbExport;
import org.sqlproc.dsl.resolver.DbImport;
import org.sqlproc.dsl.resolver.DbIndex;
import org.sqlproc.dsl.resolver.DbIndex.DbIndexDetail;
import org.sqlproc.dsl.resolver.DbResolver;
import org.sqlproc.dsl.resolver.DbResolver.DbType;
import org.sqlproc.dsl.resolver.DbTable;
import org.sqlproc.dsl.util.Annotations;
import org.sqlproc.dsl.util.Debug;
import org.sqlproc.dsl.util.Utils;

public class TablePojoGenerator {

    protected Logger LOGGER = Logger.getLogger(TablePojoGenerator.class);
    private Debug debug = new Debug(LOGGER);

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
    protected static final String METHOD_INDEX = "index=";
    protected static final String ENUM_TO_INIT = "enumInit";
    protected static final String COLLECTION_LIST = "java.util.List";
    protected static final String ANNOTATION_NOT_NULL = "javax.validation.constraints.NotNull";
    protected static final String ANNOTATION_SIZE = "javax.validation.constraints.Size";
    protected static final String INDENT = "  ";
    protected static final String NLINDENT = "\n" + INDENT;
    protected static final String NLINDENTINDENT = "\n" + INDENT + INDENT;
    protected static final String NL = "\n";

    protected String suffix;
    protected Map<String, String> finalEntities;
    protected Annotations annotations;
    protected Set<String> imports = new HashSet<String>();
    protected boolean doCompressMetaDirectives;
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
    protected Map<String, Set<String>> ignoreColumns = new HashMap<String, Set<String>>();
    protected Map<String, Set<String>> requiredColumns = new HashMap<String, Set<String>>();
    protected Map<String, Set<String>> notRequiredColumns = new HashMap<String, Set<String>>();
    protected Map<String, Map<String, PojoAttrType>> createColumns = new HashMap<String, Map<String, PojoAttrType>>();
    protected Map<String, Map<String, Map<String, String>>> ignoreExports = new HashMap<String, Map<String, Map<String, String>>>();
    protected Map<String, Map<String, Map<String, String>>> ignoreImports = new HashMap<String, Map<String, Map<String, String>>>();
    protected Map<String, Map<String, Map<String, String>>> createExports = new HashMap<String, Map<String, Map<String, String>>>();
    protected Map<String, Map<String, Map<String, String>>> createImports = new HashMap<String, Map<String, Map<String, String>>>();
    protected Map<String, Map<String, Map<String, String>>> inheritImports = new HashMap<String, Map<String, Map<String, String>>>();
    protected Map<String, Map<String, Map<String, String>>> manyToManyImports = new HashMap<String, Map<String, Map<String, String>>>();
    protected Map<String, Map<String, Map<String, List<String>>>> inheritance = new HashMap<String, Map<String, Map<String, List<String>>>>();
    protected Map<String, String> inheritanceColumns = new HashMap<String, String>();
    protected Set<String> generateMethods = new HashSet<String>();
    protected Map<String, ImplementsExtends> toImplements = new HashMap<String, ImplementsExtends>();
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
    protected Map<String, PojoType> pojosForProcedures = new HashMap<String, PojoType>();
    protected Map<String, PojoType> pojosForFunctions = new HashMap<String, PojoType>();
    protected Filter activeFilter = null;
    protected Map<String, String> enumForCheckConstraints = new HashMap<String, String>();

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
    protected Map<String, String> comments = new HashMap<String, String>();

    protected Map<String, String> metaFunctionsResult = new HashMap<String, String>();
    protected Map<String, String> metaProceduresResult = new HashMap<String, String>();

    public TablePojoGenerator() {
    }

    public TablePojoGenerator(ModelProperty modelProperty, Artifacts artifacts, String suffix,
            Map<String, String> finalEntities, Annotations annotations, List<String> dbSequences, DbType dbType) {

        debug = new Debug(modelProperty.getDebugLevel(artifacts), modelProperty.getDebugScope(artifacts), LOGGER);

        this.suffix = (suffix != null) ? suffix : "";
        this.finalEntities = finalEntities;
        this.annotations = annotations;
        if (annotations != null)
            this.imports = annotations.getImports();

        this.doCompressMetaDirectives = modelProperty.isCompressMetaDirectives(artifacts);
        Map<String, PojoAttrType> sqlTypes = modelProperty.getSqlTypes(artifacts);
        if (sqlTypes != null) {
            this.sqlTypes.putAll(sqlTypes);
        }
        Map<String, Map<String, PojoAttrType>> tableTypes = modelProperty.getTableTypes(artifacts);
        if (tableTypes != null) {
            this.tableTypes.putAll(tableTypes);
        }
        Map<String, Map<String, PojoAttrType>> columnTypes = modelProperty.getColumnTypes(artifacts);
        if (columnTypes != null) {
            this.columnTypes.putAll(columnTypes);
        }
        Map<String, Map<String, PojoAttrType>> procedureTypes = modelProperty.getProcedureTypes(artifacts);
        if (procedureTypes != null) {
            this.procedureTypes.putAll(procedureTypes);
        }
        Map<String, Map<String, PojoAttrType>> functionTypes = modelProperty.getFunctionTypes(artifacts);
        if (functionTypes != null) {
            this.functionTypes.putAll(functionTypes);
        }
        Map<String, String> tableNames = modelProperty.getTableNames(artifacts);
        if (tableNames != null) {
            this.tableNames.putAll(tableNames);
        }
        Map<String, Map<String, String>> columnNames = modelProperty.getColumnNames(artifacts);
        if (columnNames != null) {
            this.columnNames.putAll(columnNames);
        }
        Set<String> ignoreTables = modelProperty.getIgnoreTables(artifacts);
        if (ignoreTables != null) {
            this.ignoreTables.addAll(ignoreTables);
        }
        Set<String> onlyTables = modelProperty.getOnlyTables(artifacts);
        if (onlyTables != null) {
            this.onlyTables.addAll(onlyTables);
        }
        Set<String> notAbstractTables = modelProperty.getNotAbstractTables(artifacts);
        if (onlyTables != null) {
            this.notAbstractTables.addAll(notAbstractTables);
        }
        Map<String, Set<String>> ignoreColumns = modelProperty.getIgnoreColumns(artifacts);
        if (ignoreColumns != null) {
            this.ignoreColumns.putAll(ignoreColumns);
        }
        Map<String, Set<String>> requiredColumns = modelProperty.getRequiredColumns(artifacts);
        if (requiredColumns != null) {
            this.requiredColumns.putAll(requiredColumns);
        }
        Map<String, Set<String>> notRequiredColumns = modelProperty.getNotRequiredColumns(artifacts);
        if (notRequiredColumns != null) {
            this.notRequiredColumns.putAll(notRequiredColumns);
        }
        Map<String, Map<String, PojoAttrType>> createColumns = modelProperty.getCreateColumns(artifacts);
        if (createColumns != null) {
            this.createColumns.putAll(createColumns);
        }
        Map<String, Map<String, Map<String, String>>> ignoreExports = modelProperty.getIgnoreExports(artifacts);
        if (ignoreExports != null) {
            this.ignoreExports.putAll(ignoreExports);
        }
        Map<String, Map<String, Map<String, String>>> ignoreImports = modelProperty.getIgnoreImports(artifacts);
        if (ignoreImports != null) {
            this.ignoreImports.putAll(ignoreImports);
        }
        Map<String, Map<String, Map<String, String>>> createExports = modelProperty.getCreateExports(artifacts);
        if (createExports != null) {
            this.createExports.putAll(createExports);
        }
        Map<String, Map<String, Map<String, String>>> createImports = modelProperty.getCreateImports(artifacts);
        if (createImports != null) {
            this.createImports.putAll(createImports);
        }
        Map<String, Map<String, Map<String, String>>> inheritImports = modelProperty.getInheritImports(artifacts);
        if (inheritImports != null) {
            this.inheritImports.putAll(inheritImports);
        }
        Map<String, Map<String, Map<String, String>>> manyToManyImports = modelProperty.getManyToManyImports(artifacts);
        if (manyToManyImports != null) {
            this.manyToManyImports.putAll(manyToManyImports);
        }
        Map<String, Map<String, Map<String, List<String>>>> inheritance = modelProperty.getInheritance(artifacts);
        if (inheritance != null) {
            this.inheritance.putAll(inheritance);
        }
        Map<String, String> inheritanceColumns = modelProperty.getInheritanceColumns(artifacts);
        if (inheritanceColumns != null) {
            this.inheritanceColumns.putAll(inheritanceColumns);
        }
        Set<String> generateMethods = modelProperty.getGenerateMethods(artifacts);
        if (generateMethods != null) {
            this.generateMethods.addAll(generateMethods);
        }
        this.generateOperators = modelProperty.getGenerateOperators(artifacts);
        Map<String, ImplementsExtends> toImplements = modelProperty.getToImplements(artifacts);
        if (toImplements != null) {
            this.toImplements.putAll(toImplements);
        }
        this.toExtends = modelProperty.getToExtends(artifacts);
        Map<String, List<String>> joinTables = modelProperty.getJoinTables(artifacts);
        if (joinTables != null) {
            this.joinTables.putAll(joinTables);
        }
        this.doGenerateWrappers = modelProperty.isDoGenerateWrappers(artifacts);
        this.doGenerateValidationAnnotations = modelProperty.isDoGenerateValidationAnnotations(artifacts);
        this.makeItFinal = modelProperty.isMakeItFinal(artifacts);
        this.versionColumn = modelProperty.getVersionColumn(artifacts);
        Map<String, Set<String>> versionColumns = modelProperty.getVersionColumns(artifacts);
        if (versionColumns != null) {
            this.versionColumns.putAll(versionColumns);
        }
        Map<String, Set<String>> notVersionColumns = modelProperty.getNotVersionColumns(artifacts);
        if (notVersionColumns != null) {
            this.notVersionColumns.putAll(notVersionColumns);
        }
        Set<String> preserveForeignKeys = modelProperty.getPreserveForeignKeys(artifacts);
        if (preserveForeignKeys != null) {
            this.preserveForeignKeys.addAll(preserveForeignKeys);
        }
        Map<String, PojoType> pojosForProcedures = modelProperty.getPojosForProcedures(artifacts);
        if (pojosForProcedures != null) {
            this.pojosForProcedures.putAll(pojosForProcedures);
        }
        Map<String, PojoType> pojosForFunctions = modelProperty.getPojosForFunctions(artifacts);
        if (pojosForProcedures != null) {
            this.pojosForFunctions.putAll(pojosForFunctions);
        }
        this.activeFilter = Filter.parse(modelProperty.getActiveFilter(artifacts));
        Map<String, String> enumForCheckConstraints = modelProperty.getEnumForCheckConstraints(artifacts);
        if (enumForCheckConstraints != null) {
            this.enumForCheckConstraints.putAll(enumForCheckConstraints);
        }

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

        Map<String, String> metaFunctionsResult = modelProperty.getMetaFunctionsResult(artifacts);
        if (metaFunctionsResult != null) {
            this.metaFunctionsResult.putAll(metaFunctionsResult);
        }

        if (doGenerateValidationAnnotations) {
            if (this.imports == null)
                this.imports = new HashSet<String>();
            this.imports.add(ANNOTATION_NOT_NULL);
            this.imports.add(ANNOTATION_SIZE);
        }

        if (debug.debug) {
            System.out.println("finalEntities " + this.finalEntities);
            System.out.println("annotations " + this.annotations);
            System.out.println("doCompressMetaDirectives " + this.doCompressMetaDirectives);
            System.out.println("sqlTypes " + this.sqlTypes);
            System.out.println("tableTypes " + this.tableTypes);
            System.out.println("columnTypes " + this.columnTypes);
            System.out.println("procedureTypes " + this.procedureTypes);
            System.out.println("functionTypes " + this.functionTypes);
            System.out.println("tableNames " + this.tableNames);
            System.out.println("columnNames " + this.columnNames);
            System.out.println("ignoreTables " + this.ignoreTables);
            System.out.println("onlyTables " + this.onlyTables);
            System.out.println("ignoreColumns " + this.ignoreColumns);
            System.out.println("createColumns " + this.createColumns);
            System.out.println("ignoreExports " + this.ignoreExports);
            System.out.println("ignoreImports " + this.ignoreImports);
            System.out.println("createExports " + this.createExports);
            System.out.println("createImports " + this.createImports);
            System.out.println("inheritImports " + this.inheritImports);
            System.out.println("manyToManyImports " + this.manyToManyImports);
            System.out.println("inheritance " + this.inheritance);
            System.out.println("inheritanceColumns " + this.inheritanceColumns);
            System.out.println("generateMethods " + this.generateMethods);
            System.out.println("generateOperators " + this.generateOperators);
            System.out.println("toImplements " + this.toImplements);
            System.out.println("toExtends " + this.toExtends);
            System.out.println("joinTables " + this.joinTables);
            System.out.println("doGenerateWrappers " + this.doGenerateWrappers);
            System.out.println("doGenerateValidationAnnotations " + this.doGenerateValidationAnnotations);
            System.out.println("makeItFinal " + this.makeItFinal);
            System.out.println("sequences " + this.dbSequences);
            System.out.println("dbType " + this.dbType);
            System.out.println("metaFunctionsResult " + this.metaFunctionsResult);
            System.out.println("preserveForeignKeys " + this.preserveForeignKeys);
            System.out.println("pojosForProcedures " + this.pojosForProcedures);
            System.out.println("pojosForFunctions " + this.pojosForFunctions);
            System.out.println("versionColumn " + this.versionColumn);
            System.out.println("versionColumns " + this.versionColumns);
            System.out.println("notVersionColumns " + this.notVersionColumns);
            System.out.println("activeFilter " + this.activeFilter);
            System.out.println("enumForCheckConstraints " + this.enumForCheckConstraints);
        }
    }

    public void addTableDefinition(String table, List<DbColumn> dbColumns, List<String> dbPrimaryKeys,
            List<DbExport> dbExports, List<DbImport> dbImports, List<DbIndex> dbIndexes,
            List<DbCheckConstraint> dbCheckConstraints, String comment) {
        if (debug.debug) {
            System.out.println("addTableDefinition: " + table + " dbColumns " + dbColumns);
            System.out.println("addTableDefinition: " + table + " dbPrimaryKeys " + dbPrimaryKeys);
            System.out.println("addTableDefinition: " + table + " dbExports " + dbExports);
            System.out.println("addTableDefinition: " + table + " dbImports " + dbImports);
            System.out.println("addTableDefinition: " + table + " dbIndexes " + dbIndexes);
            System.out.println("addTableDefinition: " + table + " dbCheckConstraints " + dbCheckConstraints);
        }
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
        if (comment != null)
            comments.put(table, comment);
        for (DbImport dbImport : dbImports) {
            if (ignoreImports.containsKey(table)
                    && (ignoreImports.get(table) == null || ignoreImports.get(table)
                            .containsKey(dbImport.getFkColumn())
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
            // System.out.println("AAA3 " + table + " " + dbExport.getPkTable() + " " + dbExport.getPkColumn() + " "
            // + dbExport.getFkTable() + " " + dbExport.getFkColumn());
            if (ignoreExports.containsKey(table) && ignoreExports.get(table).containsKey(dbExport.getPkColumn())
                    && ignoreExports.get(table).get(dbExport.getPkColumn()).containsKey(dbExport.getFkTable())) {
                // System.out.println("AAAE " + ignoreExports.get(table));
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
                    System.out.println("Missing index in " + dbIndex);
                    continue;
                }
                PojoAttribute attr = attributes.get(dbIndexDetail.getColname());
                if (attr == null) {
                    System.out.println("Missing attibute for " + dbIndex);
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
            PojoAttribute attribute = (pojos.containsKey(check.getTable()) && pojos.get(check.getTable()).containsKey(
                    check.getColumn())) ? pojos.get(check.getTable()).get(check.getColumn()) : null;
            if (attribute == null) {
                System.out.println("For the constraint " + check.getEnumName() + " there's no table or column");
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
                attribute.setDependencyClassName(tableToCamelCase(name));
                firstCheckConstraint = true;
            }
            attribute.setDependencyClassNameIsEnum(true);

            if (!firstCheckConstraint)
                continue;

            List<EnumAttribute> attrs = new ArrayList<EnumAttribute>();
            enums.put(name, attrs);
            EnumAttribute pattr = new EnumAttribute();
            pattr.setName("VALUE");
            pattr.setClassName(attribute.getClassName());
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
            Map<String, PojoAttribute> newAttributes = new HashMap<String, PojoAttribute>();
            for (Entry<String, PojoAttribute> entry : pojos.get(pojo).entrySet()) {
                PojoAttribute attribute = entry.getValue();
                if (attribute.getPkTable() != null) {
                    if (pojos.containsKey(attribute.getPkTable())) {
                        attribute.setDependencyClassName(tableToCamelCase(getTableName(attribute.getPkTable())));
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
                                attrib.setClassName(COLLECTION_LIST + " <"
                                        + tableToCamelCase(getTableName(fk.getKey())) + ">");
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

    public void addProcedureDefinition(String procedure, DbTable dbProcedure, List<DbColumn> dbProcColumns,
            boolean isFunction, String comment) {
        if (debug.debug) {
            System.out.println("addProcedureDefinition: " + procedure + " dbProcedure " + dbProcedure);
            System.out.println("addProcedureDefinition: " + procedure + " dbProcColumns " + dbProcColumns);
        }
        if (procedure == null || dbProcColumns == null)
            return;
        Map<String, PojoAttribute> attributes = new LinkedHashMap<String, PojoAttribute>();
        int ix = 0;
        for (DbColumn dbColumn : dbProcColumns) {
            ix++;
            if ((dbColumn.getColumnType() == 3 || dbColumn.getColumnType() == 5)
                    && (dbColumn.getName() == null || dbColumn.getName().trim().length() == 0
                            || dbColumn.getName().equalsIgnoreCase("returnValue")
                            || dbColumn.getName().equalsIgnoreCase("RETURN_VALUE")
                            || dbColumn.getName().equalsIgnoreCase(FAKE_FUN_PROC_COLUMN_NAME) || dbColumn.getName()
                            .equalsIgnoreCase("null"))) {
                dbColumn.setName(FAKE_FUN_PROC_COLUMN_NAME);
            }
            if (dbType == DbType.INFORMIX && ix == 1 && isFunction
                    && !FAKE_FUN_PROC_COLUMN_NAME.equals(dbColumn.getName()))
                continue;
            PojoAttribute attribute = convertDbColumnDefinition(procedure, dbColumn, procedureTypes);
            if (attribute != null) {
                attributes.put(dbColumn.getName(), attribute);
            } else {
                attribute = convertDbColumnDefault(procedure, dbColumn);
                if (attribute != null)
                    attributes.put(dbColumn.getName(), attribute);
            }
            if (attribute != null) {
                attribute.setFunProcType(dbProcedure.getFtype());
                attribute.setFunProcColumnType(dbColumn.getColumnType());
            }
            if (FAKE_FUN_PROC_COLUMN_NAME.equals(dbColumn.getName()) && isFunction && attribute.getClassName() != null) {
                String metaType = className2metaType(attribute.getClassName());
                // System.out.println("XXXXXX " + procedure + " " + metaType + " " + attribute.getClassName());
                if (metaType != null)
                    metaFunctionsResult.put(procedure, metaType);
            } else if (FAKE_FUN_PROC_COLUMN_NAME.equals(dbColumn.getName()) && attribute.getClassName() != null
                    && (dbColumn.getColumnType() == 3 || dbColumn.getColumnType() == 5)) {
                String metaType = className2metaType(attribute.getClassName());
                // System.out.println("XXXXXX " + procedure + " " + metaType + " " + attribute.getClassName());
                if (metaType != null)
                    metaProceduresResult.put(procedure, metaType);
            }
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

    public void addFunctionDefinition(String function, DbTable dbFunction, List<DbColumn> dbFunColumns, String comment) {
        if (debug.debug) {
            System.out.println("addFunctionDefinition: " + function + " dbFunction " + dbFunction);
            System.out.println("addFunctionDefinition: " + function + " dbFunColumns " + dbFunColumns);
        }
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
            PojoAttribute attribute = convertDbColumnDefinition(FUN_PROC_COLUMN_NAME, metaFunctionsResult.get(function));
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

    public String getPojoDefinitions(ModelProperty modelProperty, Artifacts artifacts) {
        String result = getPojoDefinitions();
        return replaceAll(modelProperty, result, artifacts);
    }

    public String getPojoDefinitions() {
        try {
            if (debug.debug) {
                System.out.println("pojos " + this.pojos);
                System.out.println("pojoExtends " + this.pojoExtends);
                System.out.println("pojoInheritanceDiscriminator " + this.pojoInheritanceDiscriminator);
                System.out.println("pojoInheritanceSimple " + this.pojoInheritanceSimple);
                System.out.println("pojoDiscriminators " + this.pojoDiscriminators);
                System.out.println("indexes " + this.indexes);
                System.out.println("procedures " + this.procedures);
                System.out.println("functions " + this.functions);
                System.out.println("enums " + this.enums);
            }

            StringBuilder buffer = new StringBuilder(), bufferPartial, bufferMeta, bufferMetaAttr;
            boolean isSerializable = false;
            Set<String> serializables = new HashSet<String>();
            boolean oneMoreLine = false;
            if (!toImplements.isEmpty()) {
                for (ImplementsExtends ie : toImplements.values()) {
                    JvmType type = ie.getToImplement();
                    if (type.getIdentifier().endsWith("Serializable")) {
                        if (!ie.getDbTables().isEmpty()) {
                            for (String dbTable : ie.getDbTables()) {
                                serializables.add(dbTable);
                            }
                            continue;
                        }
                        if (!ie.getDbNotTables().isEmpty()) {
                            for (String pojo : pojos.keySet()) {
                                serializables.add(pojo);
                            }
                            for (String pojo : enums.keySet()) {
                                serializables.add(pojo);
                            }
                            for (String pojo : procedures.keySet()) {
                                serializables.add(pojo);
                            }
                            for (String pojo : functions.keySet()) {
                                serializables.add(pojo);
                            }
                            for (String dbTable : ie.getDbNotTables()) {
                                serializables.remove(dbTable);
                            }
                            continue;
                        }
                        isSerializable = true;
                        continue;
                    }
                    if (ie.isGenerics())
                        buffer.append(NLINDENT).append("#Generics");
                    buffer.append(NLINDENT).append("implements :").append(type.getIdentifier());
                    if (!ie.getDbTables().isEmpty()) {
                        buffer.append(" onlyPojos");
                        for (String dbTable : ie.getDbTables()) {
                            String pojoName = tableNames.get(dbTable);
                            if (pojoName == null)
                                pojoName = dbTable;
                            String realPojoName = tableToCamelCase(pojoName);
                            buffer.append(" ").append(realPojoName);
                        }
                    }
                    if (!ie.getDbNotTables().isEmpty()) {
                        buffer.append(" exceptPojos");
                        for (String dbTable : ie.getDbNotTables()) {
                            String pojoName = tableNames.get(dbTable);
                            if (pojoName == null)
                                pojoName = dbTable;
                            String realPojoName = tableToCamelCase(pojoName);
                            buffer.append(" ").append(realPojoName);
                        }
                    }
                }
                oneMoreLine = true;
            }
            if (toExtends != null) {
                if (toExtends.isGenerics())
                    buffer.append(NLINDENT).append("#Generics");
                JvmType type = toExtends.getToImplement();
                buffer.append(NLINDENT).append("extends :").append(type.getIdentifier());
                if (!toExtends.getDbTables().isEmpty()) {
                    buffer.append(" onlyPojos");
                    for (String dbTable : toExtends.getDbTables()) {
                        String pojoName = tableNames.get(dbTable);
                        if (pojoName == null)
                            pojoName = dbTable;
                        String realPojoName = tableToCamelCase(pojoName);
                        buffer.append(" ").append(realPojoName);
                    }
                }
                if (!toExtends.getDbNotTables().isEmpty()) {
                    buffer.append(" exceptPojos");
                    for (String dbTable : toExtends.getDbNotTables()) {
                        String pojoName = tableNames.get(dbTable);
                        if (pojoName == null)
                            pojoName = dbTable;
                        String realPojoName = tableToCamelCase(pojoName);
                        buffer.append(" ").append(realPojoName);
                    }
                }
                oneMoreLine = true;
            }
            if (oneMoreLine) {
                buffer.append("\n");
            }
            if (imports != null) {
                for (String qualifiedName : imports) {
                    buffer.append(INDENT).append("import ").append(qualifiedName).append("\n");
                }
            }
            for (Entry<String, List<EnumAttribute>> pentry : enums.entrySet()) {
                String pojo = pentry.getKey();
                // System.out.println("QQQQQ " + pojo);
                if (!onlyTables.isEmpty() && !onlyTables.contains(pojo))
                    continue;
                if (ignoreTables.contains(pojo))
                    continue;
                if (!Filter.isTable(activeFilter, pojo))
                    continue;
                String pojoName = tableNames.get(pojo);
                if (pojoName == null)
                    pojoName = pojo;
                if (finalEntities.containsKey(tableToCamelCase(pojoName))) {
                    buffer.append(getFinalContent(finalEntities.get(tableToCamelCase(pojoName))));
                    continue;
                }
                printComment(buffer, comments.get(pojo), INDENT);
                if (isSerializable || serializables.contains(pojo))
                    buffer.append(NLINDENT).append("#Serializable(1)");
                buffer.append(NLINDENT);
                if (makeItFinal)
                    buffer.append("final ");
                buffer.append("enum ");
                if (enumForCheckConstraints.containsKey(pojoName))
                    buffer.append(enumForCheckConstraints.get(pojoName));
                else
                    buffer.append(tableToCamelCase(pojoName));
                if (pojoExtends.containsKey(pojo))
                    buffer.append(" extends ").append(tableToCamelCase(pojoExtends.get(pojo)));
                buffer.append(" {");
                buffer.append(NLINDENT).append(INDENT).append("#Values(");
                boolean first = true;
                for (EnumAttribute attribute : pentry.getValue()) {
                    // System.out.println("  RRR " + attribute.getName());
                    if (attribute.getIntValue() == null && attribute.getStrValue() == null)
                        continue;
                    String name = (columnNames.containsKey(pojo)) ? columnNames.get(pojo).get(attribute.getName())
                            : null;
                    if (name == null)
                        name = attribute.getName();
                    if (attribute.getIntValue() == null && attribute.getStrValue() == null)
                        name = columnToCamelCase(name);
                    if (!first)
                        buffer.append(",");
                    else
                        first = false;
                    buffer.append(name).append(" = ");
                    if (attribute.getIntValue() != null) {
                        buffer.append(attribute.getIntValue());
                    } else if (attribute.getStrValue() != null) {
                        buffer.append("\"").append(attribute.getStrValue()).append("\"");
                    }
                }
                buffer.append(")");
                for (EnumAttribute attribute : pentry.getValue()) {
                    // System.out.println("  RRR " + attribute.getName());
                    if (attribute.getIntValue() == null && attribute.getStrValue() == null) {
                        String name = (columnNames.containsKey(pojo)) ? columnNames.get(pojo).get(attribute.getName())
                                : null;
                        if (name == null)
                            name = attribute.getName();
                        if (attribute.getIntValue() == null && attribute.getStrValue() == null)
                            name = columnToCamelCase(name);
                        buffer.append(NLINDENT).append(INDENT).append(name).append(' ');
                        buffer.append(":").append(attribute.getClassName());
                    }
                }
                // if (pojoExtends.containsKey(pojo)) {
                // getParentAttrs(pojoExtends.get(pojo), null, null);
                // }
                buffer.append(NLINDENT).append("}\n");
            }
            for (String pojo : pojos.keySet()) {
                // System.out.println("QQQQQ " + pojo);
                if (!onlyTables.isEmpty() && !onlyTables.contains(pojo))
                    continue;
                if (ignoreTables.contains(pojo))
                    continue;
                if (!Filter.isTable(activeFilter, pojo))
                    continue;
                String pojoName = tableNames.get(pojo);
                if (pojoName == null)
                    pojoName = pojo;
                if (finalEntities.containsKey(tableToCamelCase(pojoName))) {
                    buffer.append(getFinalContent(finalEntities.get(tableToCamelCase(pojoName))));
                    continue;
                }
                String realPojoName = tableToCamelCase(pojoName);
                printComment(buffer, comments.get(pojo), INDENT);
                if (annotations != null) {
                    buffer.append(annotations.getEntityAnnotationsDefinitions(realPojoName, true,
                            annotations.isNonStandardPojoAnnotations(realPojoName)));
                    buffer.append(annotations.getConstructorAnnotationsDefinitions(realPojoName, true));
                    buffer.append(annotations.getStaticAnnotationsDefinitions(realPojoName, true));
                    buffer.append(annotations.getConflictAnnotationsDefinitions(realPojoName, true));
                }
                {
                    bufferMeta = new StringBuilder();
                    if (pojoDiscriminators.containsKey(pojo))
                        bufferMeta.append(nlindent()).append("#InheritanceChild(").append(pojoDiscriminators.get(pojo))
                                .append(")");
                    if (generateOperators != null) {
                        bufferMeta.append(nlindent()).append("#Operators");
                        if (!"operators".equals(generateOperators))
                            bufferMeta.append("(").append(generateOperators).append(")");
                    }
                    if (isSerializable || serializables.contains(pojo))
                        bufferMeta.append(nlindent()).append("#Serializable(1)");
                    if (generateMethods.contains(METHOD_INDEX) && indexes.containsKey(pojo)) {
                        List<Map<PojoAttribute, Boolean>> mainList = indexes.get(pojo);
                        for (int i = 0, l = mainList.size(); i < l; i++) {
                            bufferMeta.append(nlindent()).append("#Index(").append(i + 1);
                            for (PojoAttribute attr : mainList.get(i).keySet()) {
                                String name = (columnNames.containsKey(pojo)) ? columnNames.get(pojo).get(
                                        attr.getName()) : null;
                                if (name == null)
                                    name = attr.getName();
                                else
                                    name = columnToCamelCase(name);
                                bufferMeta.append(",").append(name);
                            }
                            bufferMeta.append(")");
                        }
                    }
                }
                Set<String> pkeys = new HashSet<String>();
                Set<String> toStr = new HashSet<String>();
                {
                    bufferPartial = new StringBuilder();
                    bufferPartial.append(NLINDENT);
                    if (makeItFinal)
                        bufferPartial.append("final ");
                    if (pojoInheritanceDiscriminator.containsKey(pojo) || pojoInheritanceSimple.containsKey(pojo)) {
                        if (!notAbstractTables.contains(pojo))
                            bufferPartial.append("abstract ");
                    }
                    bufferPartial.append("pojo ");
                    bufferPartial.append(realPojoName);
                    if (pojoExtends.containsKey(pojo))
                        bufferPartial.append(" extends ").append(tableToCamelCase(pojoExtends.get(pojo)));
                    bufferPartial.append(" {");
                    // Set<String> isDef = new HashSet<String>();
                    // Set<String> toInit = new HashSet<String>();
                    Map<String, PojoAttribute> addedAttributes = new LinkedHashMap<String, PojoAttribute>();
                    for (Map.Entry<String, PojoAttribute> pentry : pojos.get(pojo).entrySet()) {
                        // System.out.println("  RRR " + pentry.getKey());
                        if (ignoreColumns.containsKey(pojo) && ignoreColumns.get(pojo).contains(pentry.getKey()))
                            continue;
                        PojoAttribute attribute = pentry.getValue();
                        String name = (columnNames.containsKey(pojo)) ? columnNames.get(pojo).get(pentry.getKey())
                                : null;
                        if (name == null)
                            name = attribute.getName();
                        else
                            name = columnToCamelCase(name);
                        printComment(bufferPartial, attribute.getComment(), INDENT, INDENT);
                        if (annotations != null) {
                            bufferPartial.append(annotations.getAttributeAnnotationsDefinitions(realPojoName, name,
                                    true, annotations.isNonStandardPojoAnnotations(realPojoName, name)));
                            bufferPartial.append(annotations.getGetterAnnotationsDefinitions(realPojoName, name, true));
                            bufferPartial.append(annotations.getSetterAnnotationsDefinitions(realPojoName, name, true));
                        }
                        if (doGenerateValidationAnnotations) {
                            if ((requiredColumns.containsKey(pojo) && requiredColumns.get(pojo).contains(
                                    pentry.getKey()))
                                    || (attribute.isRequired() && !attribute.isPrimaryKey())) {
                                if (!notRequiredColumns.containsKey(pojo)
                                        || !notRequiredColumns.get(pojo).contains(pentry.getKey()))
                                    if (annotations == null
                                            || !annotations.hasAttributeAnnotationsDefinitions(realPojoName, name,
                                                    ANNOTATION_NOT_NULL)) {
                                        bufferPartial.append(NLINDENT).append(INDENT).append("@NotNull");
                                    }
                            }
                            if (attribute.getDependencyClassName() == null && !attribute.isPrimitive()) {
                                if (attribute.getClassName().equals("java.lang.String") && attribute.getSize() > 0) {
                                    if (annotations == null
                                            || !annotations.hasAttributeAnnotationsDefinitions(realPojoName, name,
                                                    ANNOTATION_SIZE)) {
                                        bufferPartial.append(NLINDENT).append(INDENT).append("@Size(max = ")
                                                .append(attribute.getSize()).append(")");
                                    }
                                }
                            }
                        }
                        {
                            bufferMetaAttr = new StringBuilder();
                            if (attribute.isDef()) {
                                // isDef.add(name);
                                if (generateMethods.contains(METHOD_IS_DEF))
                                    bufferMetaAttr.append(nlindent2()).append("#IsDef");
                                else if (generateMethods.contains(ENUM_IS_DEF))
                                    bufferMetaAttr.append(nlindent2()).append("#EnumDef");
                            }
                            if (attribute.toInit()) {
                                // toInit.add(name);
                                if (generateMethods.contains(METHOD_TO_INIT))
                                    bufferMetaAttr.append(nlindent2()).append("#ToInit");
                                else if (generateMethods.contains(ENUM_TO_INIT))
                                    bufferMetaAttr.append(nlindent2()).append("#EnumInit");
                            }
                            if (inheritanceColumns.containsKey(pojo)
                                    && pentry.getKey().equals(inheritanceColumns.get(pojo))) {
                                bufferMetaAttr.append(nlindent2()).append("#InheritanceDiscriminator");
                            }
                            if (attribute.isVersion()) {
                                bufferMetaAttr.append(nlindent2()).append("#Version");
                            }
                            if (!attribute.isVersion()
                                    && ((requiredColumns.containsKey(pojo) && requiredColumns.get(pojo).contains(
                                            pentry.getKey())) || (attribute.isRequired() && !attribute.isPrimaryKey()))) {
                                if (!notRequiredColumns.containsKey(pojo)
                                        || !notRequiredColumns.get(pojo).contains(pentry.getKey()))
                                    bufferMetaAttr.append(nlindent2()).append("#Required");
                            }
                            if (attribute.isPrimaryKey()) {
                                bufferMetaAttr.append(nlindent2()).append("#PrimaryKey");
                                pkeys.add(name);
                            }
                            if (!generateMethods.contains(METHOD_INDEX) && attribute.getIndex() != null) {
                                bufferMetaAttr.append(nlindent2()).append("#Index(").append(attribute.getIndex())
                                        .append(")");
                            }
                            if (attribute.getDependencyClassName() != null) {
                                if (preserveForeignKeys.contains(pojo) || preserveForeignKeys.contains("_ALL_")) {
                                    if (attribute.getPkTable() != null) {
                                        addedAttributes.put(name, pentry.getValue());
                                        bufferMetaAttr.append(nlindent2()).append("#UpdateCol(")
                                                .append(columnToCamelCase(attribute.getPkColumn())).append(",")
                                                .append(columnToCamelCase(attribute.getDbName())).append(")");
                                    }
                                }
                            }
                        }
                        if (bufferMetaAttr.length() > 0 && bufferMetaAttr.charAt(0) == ' ')
                            bufferPartial.append(NLINDENTINDENT).append(bufferMetaAttr.substring(1));
                        else
                            bufferPartial.append(bufferMetaAttr);
                        bufferPartial.append(NLINDENT).append(INDENT).append(name).append(' ');
                        if (attribute.getDependencyClassName() != null) {
                            bufferPartial.append(attribute.getDependencyClassName());
                            if (attribute.isDependencyClassNameIsEnum())
                                toStr.add(name);
                        } else if (attribute.isPrimitive()) {
                            bufferPartial.append(':').append(attribute.getClassName());
                            toStr.add(name);
                        } else {
                            bufferPartial.append(":").append(attribute.getClassName());
                            if (!attribute.getClassName().startsWith(COLLECTION_LIST))
                                toStr.add(name);
                        }
                    }
                    // if (pojoExtends.containsKey(pojo)) {
                    // getParentAttrs(pojoExtends.get(pojo), null, null, toStr);
                    // }
                    for (Map.Entry<String, PojoAttribute> pentry : addedAttributes.entrySet()) {
                        PojoAttribute attribute = pentry.getValue();
                        bufferPartial.append(NLINDENT).append(INDENT).append("#CreateCol(").append(pentry.getKey())
                                .append(",").append(columnToCamelCase(attribute.getPkColumn())).append(")");
                        bufferPartial.append(NLINDENT).append(INDENT).append(columnToCamelCase(attribute.getDbName()))
                                .append(' ');
                        bufferPartial.append(": ").append(attribute.getClassName());
                        toStr.add(columnToCamelCase(attribute.getDbName()));
                    }
                }
                if (generateMethods.contains(METHOD_EQUALS) && !pkeys.isEmpty()) {
                    bufferMeta.append(nlindent()).append("#Equals(");
                    appendList(bufferMeta, pkeys);
                    bufferMeta.append(")");
                }
                if (generateMethods.contains(METHOD_HASH_CODE) && !pkeys.isEmpty()) {
                    bufferMeta.append(nlindent()).append("#HashCode(");
                    appendList(bufferMeta, pkeys);
                    bufferMeta.append(")");
                }
                if (generateMethods.contains(METHOD_TO_STRING) && !toStr.isEmpty()) {
                    bufferMeta.append(nlindent()).append("#ToString(");
                    appendList(bufferMeta, toStr);
                    bufferMeta.append(")");
                }
                if (bufferMeta.length() > 0 && bufferMeta.charAt(0) == ' ')
                    buffer.append(NLINDENT).append(bufferMeta.substring(1));
                else
                    buffer.append(bufferMeta);
                buffer.append(bufferPartial);
                buffer.append(NLINDENT).append("}\n");
            }
            for (String pojo : procedures.keySet()) {
                // System.out.println("QQQQQ " + pojo);
                if (ignoreTables.contains(pojo))
                    continue;
                if (pojosForProcedures.containsKey(pojo))
                    continue;
                if (!Filter.isTable(activeFilter, pojo))
                    continue;
                boolean isFunction = functions.containsKey(pojo);
                String pojoName = tableNames.get(pojo);
                if (pojoName == null)
                    pojoName = pojo;
                if (finalEntities.containsKey(tableToCamelCase(pojoName))) {
                    buffer.append(getFinalContent(finalEntities.get(tableToCamelCase(pojoName))));
                    continue;
                }
                printComment(buffer, comments.get(pojo), INDENT);
                {
                    bufferMeta = new StringBuilder();
                    if (isSerializable || serializables.contains(pojo))
                        bufferMeta.append(nlindent()).append("#Serializable(1)");
                }
                Set<String> toStr = new HashSet<String>();
                {
                    bufferPartial = new StringBuilder();
                    bufferPartial.append(NLINDENT);
                    if (makeItFinal)
                        bufferPartial.append("final ");
                    bufferPartial.append("pojo ");
                    bufferPartial.append(tableToCamelCase(pojoName));
                    if (pojoExtends.containsKey(pojo))
                        bufferPartial.append(" extends ").append(tableToCamelCase(pojoExtends.get(pojo)));
                    bufferPartial.append(" { // ");
                    if (isFunction)
                        bufferPartial.append("function");
                    else
                        bufferPartial.append("procedure");
                    for (Map.Entry<String, PojoAttribute> pentry : procedures.get(pojo).entrySet()) {
                        // System.out.println("  RRR " + pentry.getKey());
                        if (FAKE_FUN_PROC_COLUMN_NAME.equals(pentry.getKey()))
                            continue;
                        if (ignoreColumns.containsKey(pojo) && ignoreColumns.get(pojo).contains(pentry.getKey()))
                            continue;
                        PojoAttribute attribute = pentry.getValue();
                        String name = (columnNames.containsKey(pojo)) ? columnNames.get(pojo).get(pentry.getKey())
                                : null;
                        if (name == null)
                            name = attribute.getName();
                        else
                            name = columnToCamelCase(name);
                        bufferPartial.append(NLINDENT).append(INDENT).append(name).append(' ');
                        if (attribute.getDependencyClassName() != null) {
                            bufferPartial.append(attribute.getDependencyClassName());
                            toStr.add(name);
                        } else if (attribute.isPrimitive()) {
                            bufferPartial.append(':').append(attribute.getClassName());
                            toStr.add(name);
                        } else {
                            bufferPartial.append(":").append(attribute.getClassName());
                            if (!attribute.getClassName().startsWith(COLLECTION_LIST))
                                toStr.add(name);
                        }
                        if (!attribute.isVersion()
                                && ((requiredColumns.containsKey(pojo) && requiredColumns.get(pojo).contains(
                                        pentry.getKey())) || (attribute.isRequired() && !attribute.isPrimaryKey()))) {
                            if (!notRequiredColumns.containsKey(pojo)
                                    || !notRequiredColumns.get(pojo).contains(pentry.getKey()))
                                bufferPartial.append(" required");
                        }
                    }
                    // if (pojoExtends.containsKey(pojo)) {
                    // getParentAttrs(pojoExtends.get(pojo), null, null, toStr);
                    // }
                }
                if (generateMethods.contains(METHOD_TO_STRING) && !toStr.isEmpty()) {
                    bufferMeta.append(nlindent()).append("#ToString(");
                    appendList(bufferMeta, toStr);
                    bufferMeta.append(")");
                }
                if (bufferMeta.length() > 0 && bufferMeta.charAt(0) == ' ')
                    buffer.append(NLINDENT).append(bufferMeta.substring(1));
                else
                    buffer.append(bufferMeta);
                buffer.append(bufferPartial);
                buffer.append(NLINDENT).append("}\n");
            }
            for (String pojo : functions.keySet()) {
                // System.out.println("QQQQQ " + pojo);
                if (ignoreTables.contains(pojo))
                    continue;
                if (procedures.containsKey(pojo))
                    continue;
                if (pojosForFunctions.containsKey(pojo))
                    continue;
                if (!Filter.isTable(activeFilter, pojo))
                    continue;
                String pojoName = tableNames.get(pojo);
                if (pojoName == null)
                    pojoName = pojo;
                if (finalEntities.containsKey(tableToCamelCase(pojoName))) {
                    buffer.append(getFinalContent(finalEntities.get(tableToCamelCase(pojoName))));
                    continue;
                }
                printComment(buffer, comments.get(pojo), INDENT);
                {
                    bufferMeta = new StringBuilder();
                    if (isSerializable || serializables.contains(pojo))
                        bufferMeta.append(nlindent()).append("#Serializable(1)");
                }
                Set<String> toStr = new HashSet<String>();
                {
                    bufferPartial = new StringBuilder();
                    bufferPartial.append(NLINDENT);
                    if (makeItFinal)
                        bufferPartial.append("final ");
                    bufferPartial.append("pojo ");
                    bufferPartial.append(tableToCamelCase(pojoName));
                    if (pojoExtends.containsKey(pojo))
                        bufferPartial.append(" extends ").append(tableToCamelCase(pojoExtends.get(pojo)));
                    bufferPartial.append(" { // function");
                    for (Map.Entry<String, PojoAttribute> pentry : functions.get(pojo).entrySet()) {
                        // System.out.println("  RRR " + pentry.getKey());
                        if (FAKE_FUN_PROC_COLUMN_NAME.equals(pentry.getKey()))
                            continue;
                        if (ignoreColumns.containsKey(pojo) && ignoreColumns.get(pojo).contains(pentry.getKey()))
                            continue;
                        PojoAttribute attribute = pentry.getValue();
                        String name = (columnNames.containsKey(pojo)) ? columnNames.get(pojo).get(pentry.getKey())
                                : null;
                        if (name == null)
                            name = attribute.getName();
                        else
                            name = columnToCamelCase(name);
                        bufferPartial.append(NLINDENT).append(INDENT).append(name).append(' ');
                        if (attribute.getDependencyClassName() != null) {
                            bufferPartial.append(attribute.getDependencyClassName());
                            toStr.add(name);
                        } else if (attribute.isPrimitive()) {
                            bufferPartial.append(':').append(attribute.getClassName());
                            toStr.add(name);
                        } else {
                            bufferPartial.append(":").append(attribute.getClassName());
                            if (!attribute.getClassName().startsWith(COLLECTION_LIST))
                                toStr.add(name);
                        }
                        if (!attribute.isVersion()
                                && ((requiredColumns.containsKey(pojo) && requiredColumns.get(pojo).contains(
                                        pentry.getKey())) || (attribute.isRequired() && !attribute.isPrimaryKey()))) {
                            if (!notRequiredColumns.containsKey(pojo)
                                    || !notRequiredColumns.get(pojo).contains(pentry.getKey()))
                                bufferPartial.append(" required");
                        }
                    }
                    // if (pojoExtends.containsKey(pojo)) {
                    // getParentAttrs(pojoExtends.get(pojo), null, null, toStr);
                    // }
                }
                if (generateMethods.contains(METHOD_TO_STRING) && !toStr.isEmpty()) {
                    bufferMeta.append(nlindent()).append("#ToString(");
                    appendList(bufferMeta, toStr);
                    bufferMeta.append(")");
                }
                if (bufferMeta.length() > 0 && bufferMeta.charAt(0) == ' ')
                    buffer.append(NLINDENT).append(bufferMeta.substring(1));
                else
                    buffer.append(bufferMeta);
                buffer.append(bufferPartial);
                buffer.append(NLINDENT).append("}\n");
            }
            return buffer.toString();
        } catch (RuntimeException ex) {
            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);
            String s = writer.toString();
            return s;
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

    protected String tableToCamelCase(String value) {
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
        return camelCaseString + suffix;
    }

    protected String columnToCamelCase(String value) {
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

    protected String columnToDbConv(String value) {
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
        if (sqlType.getRef() != null) {
            attribute.setPrimitive(false);
            attribute.setDependencyClassName(sqlType.getRef().getName());
            if (attribute.getDependencyClassName() == null) {
                attribute.setDependencyClassName(sqlType.getText());
            }
        } else if (sqlType.isNativeType()) {
            // System.out.println("getIdentifier " + sqlType.getType().getIdentifier());
            // System.out.println("getQualifiedName " + sqlType.getType().getQualifiedName());
            // System.out.println("getSimpleName " + sqlType.getType().getSimpleName());
            // System.out.println("JvmPrimitiveType " + (sqlType.getType() instanceof JvmPrimitiveType));
            // if (sqlType.getType() instanceof JvmPrimitiveType) {
            // System.out.println("getArrayType " + ((JvmPrimitiveType) sqlType.getType()).getArrayType());
            // }
            // JvmPrimitiveTypeImplCustom
            attribute.setPrimitive(true);
            attribute.setClassName(sqlType.getType().getIdentifier() + (sqlType.isArray() ? " []" : ""));
        } else {
            attribute.setPrimitive(false);
            attribute.setClassName(sqlType.getType().getIdentifier());
            if (sqlType.getGtype() != null)
                attribute.setClassName(attribute.getClassName() + "<:" + sqlType.getGtype().getIdentifier() + ">");
        }
        return attribute;
    }

    protected PojoAttribute convertDbColumnDefinition(String dbName, String metaType) {
        PojoAttribute attribute = new PojoAttribute(dbName);
        attribute.setName(columnToCamelCase(dbName));
        attribute.setPrimitive(false);
        attribute.setClassName(metaType2className(metaType));
        return attribute;
    }

    protected PojoAttribute convertDbColumnDefinition(String table, DbColumn dbColumn,
            Map<String, Map<String, PojoAttrType>> redefinedTypes) {
        if (dbColumn == null)
            return null;
        PojoAttrType sqlType = redefinedTypes.containsKey(table) ? redefinedTypes.get(table).get(dbColumn.getName())
                : null;
        if (sqlType == null)
            sqlType = tableTypes.containsKey(table) ? tableTypes.get(table)
                    .get(dbColumn.getType() + dbColumn.getSize()) : null;
        if (sqlType == null)
            sqlType = sqlTypes.get(dbColumn.getType() + dbColumn.getSize());
        if (sqlType == null)
            return null;
        PojoAttribute attribute = new PojoAttribute(dbColumn.getName());
        attribute.setName(columnToCamelCase(dbColumn.getName()));
        attribute.setRequired(!dbColumn.isNullable());
        if (sqlType.getRef() != null) {
            attribute.setPrimitive(false);
            attribute.setDependencyClassName(sqlType.getRef().getName());
        } else if (sqlType.isNativeType()) {
            attribute.setPrimitive(true);
            attribute.setClassName(sqlType.getType().getIdentifier() + (sqlType.isArray() ? " []" : ""));
        } else {
            attribute.setPrimitive(false);
            attribute.setClassName(sqlType.getType().getIdentifier());
        }
        attribute.setSqlType(dbColumn.getSqlType());
        attribute.setSize(dbColumn.getSize());
        attribute.setComment(dbColumn.getComment());
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
            } else {
                attribute.setPrimitive(true);
                attribute.setClassName(PrimitiveType.BOOLEAN.getName());
            }
            break;
        case Types.TINYINT:
            if (dbColumn.isNullable() || doGenerateWrappers) {
                attribute.setPrimitive(false);
                attribute.setClassName(Byte.class.getName());
            } else {
                attribute.setPrimitive(true);
                attribute.setClassName(PrimitiveType.BYTE.getName());
            }
            break;
        case Types.SMALLINT:
            if (dbColumn.isNullable() || doGenerateWrappers) {
                attribute.setPrimitive(false);
                attribute.setClassName(Short.class.getName());
            } else {
                attribute.setPrimitive(true);
                attribute.setClassName(PrimitiveType.SHORT.getName());
            }
            break;
        case Types.INTEGER:
            if (dbColumn.isNullable() || doGenerateWrappers) {
                attribute.setPrimitive(false);
                attribute.setClassName(Integer.class.getName());
            } else {
                attribute.setPrimitive(true);
                attribute.setClassName(PrimitiveType.INT.getName());
            }
            break;
        case Types.BIGINT:
            if (dbColumn.isNullable() || doGenerateWrappers) {
                attribute.setPrimitive(false);
                attribute.setClassName(Long.class.getName());
            } else {
                attribute.setPrimitive(true);
                attribute.setClassName(PrimitiveType.LONG.getName());
            }
            break;
        case Types.FLOAT:
        case Types.REAL:
            if (dbColumn.isNullable() || doGenerateWrappers) {
                attribute.setPrimitive(false);
                attribute.setClassName(Float.class.getName());
            } else {
                attribute.setPrimitive(true);
                attribute.setClassName(PrimitiveType.FLOAT.getName());
            }
            break;
        case Types.DOUBLE:
            if (dbColumn.isNullable() || doGenerateWrappers) {
                attribute.setPrimitive(false);
                attribute.setClassName(Double.class.getName());
            } else {
                attribute.setPrimitive(true);
                attribute.setClassName(PrimitiveType.DOUBLE.getName());
            }
            break;
        case Types.NUMERIC:
            attribute.setPrimitive(false);
            attribute.setClassName(BigDecimal.class.getName());
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
            break;
        case Types.CHAR:
        case Types.NCHAR:
            if (dbColumn.isNullable() || dbColumn.getSize() > 1 || doGenerateWrappers) {
                attribute.setPrimitive(false);
                attribute.setClassName(String.class.getName());
            } else {
                attribute.setPrimitive(true);
                attribute.setClassName(PrimitiveType.CHAR.getName());
            }
            break;
        case Types.VARCHAR:
        case Types.LONGNVARCHAR:
        case Types.NVARCHAR:
            attribute.setPrimitive(false);
            attribute.setClassName(String.class.getName());
            break;
        case Types.DATE:
            // java.util.Date or java.sql.Date ?
            attribute.setPrimitive(false);
            attribute.setClassName(java.util.Date.class.getName());
            break;
        case Types.TIME:
            attribute.setPrimitive(false);
            attribute.setClassName(java.sql.Time.class.getName());
            break;
        case Types.TIMESTAMP:
            // java.util.Date or java.sql.Timestamp ?
            attribute.setPrimitive(false);
            attribute.setClassName(java.sql.Timestamp.class.getName());
            break;
        case Types.BINARY:
        case Types.VARBINARY:
        case Types.LONGVARBINARY:
        case Types.BLOB:
            attribute.setPrimitive(true);
            attribute.setClassName(PrimitiveType.BYTE_ARRAY.getName());
            break;
        case Types.CLOB:
            attribute.setPrimitive(false);
            attribute.setClassName(String.class.getName());
            break;
        default:
            // todo what type?
            attribute.setPrimitive(false);
            if (dbColumn.getType().indexOf("TIMESTAMP") == 0 || dbColumn.getType().indexOf("timestamp") == 0)
                attribute.setClassName(java.sql.Timestamp.class.getName());
            else
                attribute.setClassName("java.lang.Object");
        }
        attribute.setSqlType(dbColumn.getSqlType());
        attribute.setSize(dbColumn.getSize());
        attribute.setComment(dbColumn.getComment());
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
            if (!className2metaTypeMap.containsKey(value))
                className2metaTypeMap.put(value, entry.getKey());
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

    // meta filter only-insert,get,update,delete,select,call add-filter XXXX
    static class Filter {
        static final String ONLY_TABLE = "only-table";
        static final String ONLY_TABLES = "only-tables";
        static final String ADD = "add";
        static final String ADD_FILTER = "add-filter";
        Map<String, String> filters;
        Set<String> onlyTables;

        static Filter parse(String s) {
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

        static boolean isTable(Filter f, String table) {
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

    public static boolean addDefinitions(IScopeProvider scopeProvider, DbResolver dbResolver,
            TablePojoGenerator generator, Artifacts artifacts) {
        try {
            List<String> tables = Utils.findTables(null, artifacts,
                    scopeProvider.getScope(artifacts, ProcessorDslPackage.Literals.ARTIFACTS__TABLES));
            List<String> procedures = Utils.findProcedures(null, artifacts,
                    scopeProvider.getScope(artifacts, ProcessorDslPackage.Literals.ARTIFACTS__PROCEDURES));
            List<String> functions = Utils.findFunctions(null, artifacts,
                    scopeProvider.getScope(artifacts, ProcessorDslPackage.Literals.ARTIFACTS__FUNCTIONS));
            if (tables == null && procedures == null && functions == null)
                return false;
            if (tables != null) {
                for (String table : tables) {
                    if (table.toUpperCase().startsWith("BIN$"))
                        continue;
                    if (!dbResolver.checkTable(artifacts, table))
                        continue;
                    List<DbColumn> dbColumns = dbResolver.getDbColumns(artifacts, table);
                    if (dbColumns.isEmpty())
                        continue;
                    List<String> dbPrimaryKeys = dbResolver.getDbPrimaryKeys(artifacts, table);
                    List<DbExport> dbExports = dbResolver.getDbExports(artifacts, table);
                    List<DbImport> dbImports = dbResolver.getDbImports(artifacts, table);
                    List<DbIndex> dbIndexes = dbResolver.getDbIndexes(artifacts, table);
                    List<DbTable> ltables = dbResolver.getDbTables(artifacts, table);
                    String comment = (ltables != null && !ltables.isEmpty()) ? ltables.get(0).getComment() : null;
                    List<DbCheckConstraint> dbCheckConstraints = dbResolver.getDbCheckConstraints(artifacts, table);
                    generator.addTableDefinition(table, dbColumns, dbPrimaryKeys, dbExports, dbImports, dbIndexes,
                            dbCheckConstraints, comment);
                }
                // converter.resolveReferencesOnConvention();
                generator.resolveReferencesOnKeys();
                generator.joinTables();
            }
            if (procedures != null) {
                for (String procedure : procedures) {
                    if (procedure.toUpperCase().startsWith("BIN$"))
                        continue;
                    List<DbTable> dbProcedures = dbResolver.getDbProcedures(artifacts, procedure);
                    if (dbProcedures.isEmpty())
                        continue;
                    List<DbColumn> dbProcColumns = dbResolver.getDbProcColumns(artifacts, procedure);
                    List<DbTable> ltables = dbResolver.getDbProcedures(artifacts, procedure);
                    String comment = (ltables != null && !ltables.isEmpty()) ? ltables.get(0).getComment() : null;
                    generator.addProcedureDefinition(procedure, dbProcedures.get(0), dbProcColumns,
                            functions.contains(procedure), comment);
                }
            }
            if (functions != null) {
                for (String function : functions) {
                    if (function.toUpperCase().startsWith("BIN$"))
                        continue;
                    List<DbTable> dbFunctions = dbResolver.getDbFunctions(artifacts, function);
                    if (dbFunctions.isEmpty())
                        continue;
                    List<DbColumn> dbFunColumns = dbResolver.getDbFunColumns(artifacts, function);
                    List<DbTable> ltables = dbResolver.getDbFunctions(artifacts, function);
                    String comment = (ltables != null && !ltables.isEmpty()) ? ltables.get(0).getComment() : null;
                    generator.addFunctionDefinition(function, dbFunctions.get(0), dbFunColumns, comment);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected String replaceAll(ModelProperty modelProperty, String buffer, Artifacts artifacts) {
        for (Entry<String, String> entry : modelProperty.getReplaceAll(artifacts).entrySet()) {
            String regex = entry.getKey();
            String replacement = entry.getValue();
            System.out.println("REGEX " + regex);
            System.out.println("REPLACEMENT " + replacement);
            buffer = buffer.replaceAll(regex, replacement);
        }
        return buffer;
    }

    protected String getFinalContent(String s) {
        if (s == null)
            return null;
        if (s.startsWith("\n"))
            s = s.substring(1);
        return s + "\n";
    }
}
