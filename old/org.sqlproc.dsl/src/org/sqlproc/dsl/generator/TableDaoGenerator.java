package org.sqlproc.dsl.generator;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.sqlproc.dsl.processorDsl.Artifacts;
import org.sqlproc.dsl.processorDsl.PojoType;
import org.sqlproc.dsl.property.ImplementsExtends;
import org.sqlproc.dsl.property.ModelProperty;
import org.sqlproc.dsl.property.PojoAttribute;
import org.sqlproc.dsl.resolver.DbResolver.DbType;
import org.sqlproc.dsl.util.Debug;

public class TableDaoGenerator extends TableMetaGenerator {

    protected Logger LOGGER = Logger.getLogger(TableDaoGenerator.class);
    private Debug debug = new Debug(LOGGER);

    protected Map<String, String> finalDaos;
    protected Set<String> daoIgnoreTables = new HashSet<String>();
    protected Set<String> daoOnlyTables = new HashSet<String>();
    protected Map<String, ImplementsExtends> daoToImplements = new HashMap<String, ImplementsExtends>();
    protected ImplementsExtends daoToExtends = null;
    protected boolean daoMakeItFinal;
    protected Map<String, PojoType> daoFunctionsResult = new HashMap<String, PojoType>();
    protected Filter daoActiveFilter = null;

    public TableDaoGenerator() {
        super();
    }

    public TableDaoGenerator(ModelProperty modelProperty, Artifacts artifacts, String suffix,
            IScopeProvider scopeProvider, Map<String, String> finalDaos, List<String> dbSequences, DbType dbType) {
        super(modelProperty, artifacts, null, Collections.<String, String> emptyMap(), dbSequences, dbType);

        debug = new Debug(modelProperty.getDaoDebugLevel(artifacts), modelProperty.getDaoDebugScope(artifacts), LOGGER);

        this.suffix = (suffix != null) ? suffix : "";
        this.finalDaos = finalDaos;

        Set<String> daoIgnoreTables = modelProperty.getDaoIgnoreTables(artifacts);
        if (daoIgnoreTables != null) {
            this.daoIgnoreTables.addAll(daoIgnoreTables);
        }
        Set<String> daoOnlyTables = modelProperty.getDaoOnlyTables(artifacts);
        if (daoOnlyTables != null) {
            this.daoOnlyTables.addAll(daoOnlyTables);
        }
        Map<String, ImplementsExtends> daoToImplements = modelProperty.getDaoToImplements(artifacts);
        if (daoToImplements != null) {
            this.daoToImplements.putAll(daoToImplements);
        }
        this.daoToExtends = modelProperty.getDaoToExtends(artifacts);
        this.daoMakeItFinal = modelProperty.isDaoMakeItFinal(artifacts);
        Map<String, PojoType> daoFunctionsResult = modelProperty.getDaoFunctionsResult(artifacts);
        if (daoFunctionsResult != null) {
            this.daoFunctionsResult.putAll(daoFunctionsResult);
        }
        this.daoActiveFilter = Filter.parse(modelProperty.getDaoActiveFilter(artifacts));

        if (debug.debug) {
            System.out.println("finalDaos " + this.finalDaos);
            System.out.println("daoIgnoreTables " + this.daoIgnoreTables);
            System.out.println("daoOnlyTables " + this.daoOnlyTables);
            System.out.println("daoToImplements " + this.daoToImplements);
            System.out.println("daoToExtends " + this.daoToExtends);
            System.out.println("daoFunctionsResult " + this.daoFunctionsResult);
            System.out.println("daoActiveFilter " + this.daoActiveFilter);
        }
    }

    public String getDaoDefinitions(ModelProperty modelProperty, Artifacts artifacts) {
        String result = getDaoDefinitions();
        return replaceAll(modelProperty, result, artifacts);
    }

    public String getDaoDefinitions() {
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
            }

            StringBuilder buffer = new StringBuilder(), bufferPartial, bufferMeta;
            boolean isSerializable = false;
            Set<String> serializables = new HashSet<String>();
            boolean oneMoreLine = false;
            if (!daoToImplements.isEmpty()) {
                for (ImplementsExtends ie : daoToImplements.values()) {
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
                        buffer.append(" onlyDaos");
                        for (String dbColumn : ie.getDbTables()) {
                            String pojoName = tableNames.get(dbColumn);
                            if (pojoName == null)
                                pojoName = dbColumn;
                            String daoName = tableToCamelCase(pojoName) + "Dao";
                            buffer.append(" ").append(daoName);
                        }
                    }
                    if (!ie.getDbNotTables().isEmpty()) {
                        buffer.append(" exceptDaos");
                        for (String dbColumn : ie.getDbNotTables()) {
                            String pojoName = tableNames.get(dbColumn);
                            if (pojoName == null)
                                pojoName = dbColumn;
                            String daoName = tableToCamelCase(pojoName) + "Dao";
                            buffer.append(" ").append(daoName);
                        }
                    }
                }
                oneMoreLine = true;
            }
            if (daoToExtends != null) {
                JvmType type = daoToExtends.getToImplement();
                if (daoToExtends.isGenerics())
                    buffer.append(NLINDENT).append("#Generics");
                buffer.append(NLINDENT).append("extends :").append(type.getIdentifier());
                if (!daoToExtends.getDbTables().isEmpty()) {
                    buffer.append(" onlyDaos");
                    for (String dbColumn : daoToExtends.getDbTables()) {
                        String pojoName = tableNames.get(dbColumn);
                        if (pojoName == null)
                            pojoName = dbColumn;
                        String daoName = tableToCamelCase(pojoName) + "Dao";
                        buffer.append(" ").append(daoName);
                    }
                }
                if (!daoToExtends.getDbNotTables().isEmpty()) {
                    buffer.append(" exceptDaos");
                    for (String dbColumn : daoToExtends.getDbNotTables()) {
                        String pojoName = tableNames.get(dbColumn);
                        if (pojoName == null)
                            pojoName = dbColumn;
                        String daoName = tableToCamelCase(pojoName) + "Dao";
                        buffer.append(" ").append(daoName);
                    }
                }
                oneMoreLine = true;
            }
            if (oneMoreLine) {
                buffer.append(NL);
            }
            for (String pojo : pojos.keySet()) {
                // System.out.println("QQQQQ " + pojo);
                if (!daoOnlyTables.isEmpty() && !daoOnlyTables.contains(pojo))
                    continue;
                if (daoIgnoreTables.contains(pojo))
                    continue;
                if (!Filter.isTable(daoActiveFilter, pojo))
                    continue;
                String pojoName = tableNames.get(pojo);
                if (pojoName == null)
                    pojoName = pojo;
                String daoName = tableToCamelCase(pojoName) + "Dao";
                if (finalDaos.containsKey(daoName)) {
                    buffer.append(getFinalContent(finalDaos.get(daoName)));
                    continue;
                }
                if (pojoInheritanceDiscriminator.containsKey(pojo) || pojoInheritanceSimple.containsKey(pojo)) {
                    if (!notAbstractTables.contains(pojo))
                        continue;
                }
                {
                    bufferMeta = new StringBuilder();
                    if (isSerializable || serializables.contains(pojo))
                        bufferMeta.append(nlindent()).append("#Serializable(1)");
                    Map<String, String> toInit = new LinkedHashMap<String, String>();
                    toInits(pojo, toInit);
                    for (Entry<String, String> entry : toInit.entrySet()) {
                        bufferMeta.append(nlindent()).append("#InheritanceHandler(").append(entry.getKey());
                        // pojoExtends {BANK_ACCOUNT=BILLING_DETAILS, MOVIE=MEDIA, CREDIT_CARD=BILLING_DETAILS,
                        // BOOK=MEDIA}
                        // pojoInheritanceDiscriminator {BILLING_DETAILS=[BANK_ACCOUNT, CREDIT_CARD]}
                        // pojoInheritanceSimple {MEDIA=[MOVIE, BOOK]}
                        // pojoDiscriminators {BANK_ACCOUNT=BA, CREDIT_CARD=CC}
                        if (pojoInheritanceSimple.containsKey(entry.getValue())) {
                            for (String pojo2 : pojoInheritanceSimple.get(entry.getValue())) {
                                bufferMeta.append(",\"").append(columnToCamelCase(pojo2)).append("\"");
                                String pojoName2 = tableNames.get(pojo2);
                                if (pojoName2 == null)
                                    pojoName2 = pojo2;
                                bufferMeta.append("=").append(tableToCamelCase(pojoName2));
                            }
                        } else {
                            for (String pojo2 : pojoInheritanceDiscriminator.get(entry.getValue())) {
                                bufferMeta.append(",\"").append(pojoDiscriminators.get(pojo2)).append("\"");
                                String pojoName2 = tableNames.get(pojo2);
                                if (pojoName2 == null)
                                    pojoName2 = pojo2;
                                bufferMeta.append("=").append(tableToCamelCase(pojoName2));
                            }
                        }
                        bufferMeta.append(")");
                    }
                    bufferMeta.append(nlindent()).append("#CRUD(").append(tableToCamelCase(pojoName)).append(")");
                    bufferMeta.append(nlindent()).append("#Query(").append(tableToCamelCase(pojoName)).append(")");
                }
                if (bufferMeta.length() > 0 && bufferMeta.charAt(0) == ' ')
                    buffer.append(NLINDENT).append(bufferMeta.substring(1));
                else
                    buffer.append(bufferMeta);
                buffer.append(NLINDENT);
                if (daoMakeItFinal)
                    buffer.append("final ");
                buffer.append("dao ");
                buffer.append(daoName);
                buffer.append(" {");
                buffer.append(NLINDENT).append("}\n");
            }
            for (String procedure : procedures.keySet()) {
                // System.out.println("QQQQQ " + pojo);
                if (!daoOnlyTables.isEmpty() && !daoOnlyTables.contains(procedure))
                    continue;
                if (daoIgnoreTables.contains(procedure))
                    continue;
                if (!Filter.isTable(daoActiveFilter, procedure))
                    continue;
                boolean isFunction = functions.containsKey(procedure);
                if (isFunction)
                    continue;
                String pojoName = tableNames.get(procedure);
                if (pojoName == null)
                    pojoName = procedure;
                pojoName = tableToCamelCase(pojoName);
                String daoName = pojoName + "Dao";
                if (finalDaos.containsKey(daoName)) {
                    buffer.append(getFinalContent(finalDaos.get(daoName)));
                    continue;
                }

                // String procedureName = lowerFirstChar(pojoName);
                {
                    bufferMeta = new StringBuilder();
                    if (isSerializable || serializables.contains(procedure))
                        bufferMeta.append(nlindent()).append("#Serializable(1)");
                    Map<String, PojoAttribute> attributes = procedures.get(procedure);
                    if (metaProceduresResultSet.containsKey(procedure)) {
                        String name = metaProceduresResultSet.get(procedure);
                        if (tableNames.containsKey(name))
                            name = tableNames.get(name);
                        bufferMeta.append(nlindent()).append("#ProcedureCallQuery(").append(":")
                                .append(COLLECTION_LIST).append("<").append(tableToCamelCase(name)).append(">");
                    } else {
                        PojoAttribute returnAttribute = (attributes.containsKey(FAKE_FUN_PROC_COLUMN_NAME)) ? attributes
                                .get(FAKE_FUN_PROC_COLUMN_NAME) : null;
                        if (returnAttribute != null && dbType != DbType.POSTGRESQL && dbType != DbType.MS_SQL) {
                            bufferMeta.append(nlindent()).append("#ProcedureCallQuery(:").append(COLLECTION_LIST)
                                    .append("<").append(":").append(returnAttribute.getClassName()).append(">");
                        } else {
                            bufferMeta.append(nlindent()).append("#ProcedureUpdate(").append(":int");
                        }
                    }
                    String dispName = null;
                    PojoType ptype = pojosForProcedures.get(procedure);
                    if (ptype != null)
                        dispName = (ptype.getRef() != null) ? ptype.getRef().getName() : ":"
                                + ptype.getType().getSimpleName();
                    bufferMeta.append(",").append((dispName != null) ? dispName : pojoName);
                    bufferMeta.append(")");
                }
                if (bufferMeta.length() > 0 && bufferMeta.charAt(0) == ' ')
                    buffer.append(NLINDENT).append(bufferMeta.substring(1));
                else
                    buffer.append(bufferMeta);
                buffer.append(NLINDENT);
                if (daoMakeItFinal)
                    buffer.append("final ");
                buffer.append("dao ");
                buffer.append(daoName);
                buffer.append(" {");
                buffer.append(NLINDENT).append("}\n");
            }
            for (String function : procedures.keySet()) {
                // System.out.println("QQQQQ " + function);
                if (!daoOnlyTables.isEmpty() && !daoOnlyTables.contains(function))
                    continue;
                if (daoIgnoreTables.contains(function))
                    continue;
                if (!Filter.isTable(daoActiveFilter, function))
                    continue;
                boolean isFunction = functions.containsKey(function);
                if (!isFunction)
                    continue;
                String pojoName = tableNames.get(function);
                if (pojoName == null)
                    pojoName = function;
                pojoName = tableToCamelCase(pojoName);
                String daoName = pojoName + "Dao";
                if (finalDaos.containsKey(daoName)) {
                    buffer.append(getFinalContent(finalDaos.get(daoName)));
                    continue;
                }

                // String procedureName = lowerFirstChar(pojoName);
                {
                    bufferMeta = new StringBuilder();
                    if (isSerializable || serializables.contains(function))
                        bufferMeta.append(nlindent()).append("#Serializable(1)");
                    Map<String, PojoAttribute> attributes = procedures.get(function);
                    if (metaFunctionsResultSet.containsKey(function)) {
                        String name = metaFunctionsResultSet.get(function);
                        if (tableNames.containsKey(name))
                            name = tableNames.get(name);
                        bufferMeta.append(nlindent()).append("#FunctionCallQuery(").append(":").append(COLLECTION_LIST)
                                .append("<").append(tableToCamelCase(name)).append(">");
                    } else if (metaFunctionsResult.containsKey(function)) {
                        bufferMeta.append(nlindent()).append("#FunctionCall(").append(":")
                                .append(metaType2className(metaFunctionsResult.get(function)));
                    } else {
                        PojoAttribute returnAttribute = (attributes.containsKey(FAKE_FUN_PROC_COLUMN_NAME)) ? attributes
                                .get(FAKE_FUN_PROC_COLUMN_NAME) : null;
                        if (returnAttribute != null) {
                            bufferMeta.append(nlindent()).append("#FunctionCallQuery(:").append(COLLECTION_LIST)
                                    .append("<").append(":").append(returnAttribute.getClassName()).append(">");
                        } else {
                            bufferMeta.append(nlindent()).append("#FunctionUpdate(").append(":int");
                        }
                    }
                    String dispName = null;
                    PojoType ptype = pojosForProcedures.get(function);
                    if (ptype != null)
                        dispName = (ptype.getRef() != null) ? ptype.getRef().getName() : ":"
                                + ptype.getType().getSimpleName();
                    bufferMeta.append(",").append((dispName != null) ? dispName : pojoName);
                    bufferMeta.append(")");

                }
                if (bufferMeta.length() > 0 && bufferMeta.charAt(0) == ' ')
                    buffer.append(NLINDENT).append(bufferMeta.substring(1));
                else
                    buffer.append(bufferMeta);
                buffer.append(NLINDENT);
                if (daoMakeItFinal)
                    buffer.append("final ");
                buffer.append("dao ");
                buffer.append(daoName);
                buffer.append(" {");
                buffer.append(NLINDENT).append("}\n");
            }

            for (String function : functions.keySet()) {
                // System.out.println("QQQQQ " + function);
                if (!daoOnlyTables.isEmpty() && !daoOnlyTables.contains(function))
                    continue;
                if (daoIgnoreTables.contains(function))
                    continue;
                if (!Filter.isTable(daoActiveFilter, function))
                    continue;
                boolean isProcedure = procedures.containsKey(function);
                if (isProcedure)
                    continue;
                String pojoName = tableNames.get(function);
                if (pojoName == null)
                    pojoName = function;
                pojoName = tableToCamelCase(pojoName);
                String daoName = pojoName + "Dao";
                if (finalDaos.containsKey(daoName)) {
                    buffer.append(getFinalContent(finalDaos.get(daoName)));
                    continue;
                }

                // String procedureName = lowerFirstChar(pojoName);
                {
                    bufferMeta = new StringBuilder();
                    if (isSerializable || serializables.contains(function))
                        bufferMeta.append(nlindent()).append("#Serializable(1)");
                    Map<String, PojoAttribute> attributes = functions.get(function);
                    if (metaFunctionsResultSet.containsKey(function)) {
                        String name = metaFunctionsResultSet.get(function);
                        if (tableNames.containsKey(name))
                            name = tableNames.get(name);
                        bufferMeta.append(nlindent()).append("#FunctionCallQuery(").append(":").append(COLLECTION_LIST)
                                .append("<").append(tableToCamelCase(name)).append(">");
                    } else if (metaFunctionsResult.containsKey(function) && dbType == DbType.DB2) {
                        bufferMeta.append(nlindent()).append("#FunctionQuery(").append(":")
                                .append(metaType2className(metaFunctionsResult.get(function)));
                    } else if (metaFunctionsResult.containsKey(function)) {
                        bufferMeta.append(nlindent()).append("callFunction ").append(":")
                                .append(metaType2className(metaFunctionsResult.get(function)));
                    } else {
                        PojoAttribute returnAttribute = (attributes.containsKey(FAKE_FUN_PROC_COLUMN_NAME)) ? attributes
                                .get(FAKE_FUN_PROC_COLUMN_NAME) : null;
                        if (returnAttribute != null) {
                            bufferMeta.append(nlindent()).append("#FunctionCallQuery(:").append(COLLECTION_LIST)
                                    .append("<").append(":").append(returnAttribute.getClassName()).append(">");
                        } else {
                            bufferMeta.append(nlindent()).append("#FunctionUpdate(").append(":int");
                        }
                    }
                    String dispName = null;
                    PojoType ptype = pojosForProcedures.get(function);
                    if (ptype != null)
                        dispName = (ptype.getRef() != null) ? ptype.getRef().getName() : ":"
                                + ptype.getType().getSimpleName();
                    bufferMeta.append(",").append((dispName != null) ? dispName : pojoName);
                    bufferMeta.append(")");

                }
                if (bufferMeta.length() > 0 && bufferMeta.charAt(0) == ' ')
                    buffer.append(NLINDENT).append(bufferMeta.substring(1));
                else
                    buffer.append(bufferMeta);
                buffer.append(NLINDENT);
                if (daoMakeItFinal)
                    buffer.append("final ");
                buffer.append("dao ");
                buffer.append(daoName);
                buffer.append(" {");
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

    protected void toInits(String pojo, Map<String, String> toInit) {
        for (Map.Entry<String, PojoAttribute> pentry : pojos.get(pojo).entrySet()) {
            if (ignoreColumns.containsKey(pojo) && ignoreColumns.get(pojo).contains(pentry.getKey()))
                continue;
            PojoAttribute attribute = pentry.getValue();
            String name = (columnNames.containsKey(pojo)) ? columnNames.get(pojo).get(pentry.getKey()) : null;
            if (name == null)
                name = attribute.getName();
            else
                name = columnToCamelCase(name);
            if (attribute.toInit()) {
                if (attribute.getRef() != null) {
                    if (pojoInheritanceDiscriminator.containsKey(attribute.getRef())
                            || pojoInheritanceSimple.containsKey(attribute.getRef())) {
                        toInit.put(name, attribute.getRef());
                        // System.out.println("AAAAAAAAA " + " " + pojo + " " + attribute.getRef() + " " + name);
                    }
                }
            }
        }
        if (pojoExtends.containsKey(pojo)) {
            toInits(pojoExtends.get(pojo), toInit);
        }
    }
}
