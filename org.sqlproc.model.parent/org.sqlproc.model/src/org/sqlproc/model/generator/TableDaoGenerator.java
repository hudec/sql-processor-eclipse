package org.sqlproc.model.generator;

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
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.serializer.ISerializer;
import org.sqlproc.model.processorModel.AbstractEntity;
import org.sqlproc.model.processorModel.AnnotatedEntity;
import org.sqlproc.model.processorModel.AnnotatedFeature;
import org.sqlproc.model.processorModel.Artifacts;
import org.sqlproc.model.processorModel.DaoEntity;
import org.sqlproc.model.processorModel.Package;
import org.sqlproc.model.util.Annotations;
import org.sqlproc.model.util.Utils;
import org.sqlproc.plugin.lib.property.DaoAnnotations;
import org.sqlproc.plugin.lib.property.ImplementsExtends;
import org.sqlproc.plugin.lib.property.ModelProperty;
import org.sqlproc.plugin.lib.property.PojoAttribute;
import org.sqlproc.plugin.lib.property.PojoEntityType;
import org.sqlproc.plugin.lib.resolver.DbResolver;
import org.sqlproc.plugin.lib.resolver.DbResolver.DbType;
import org.sqlproc.plugin.lib.util.Debug;
import org.sqlproc.plugin.lib.util.Stats;

public class TableDaoGenerator extends TablePojoGenerator {

    protected Logger LOGGER = Logger.getLogger(TableDaoGenerator.class);
    private Debug debug = new Debug(LOGGER);

    protected Map<String, String> finalDaos;
    protected Map<String, Map<String, String>> finalDaosFeatures;
    protected Annotations daoEntityAnnotations;
    protected Set<String> daoImports;
    protected Set<String> daoIgnoreTables = new HashSet<String>();
    protected Set<String> daoOnlyTables = new HashSet<String>();
    protected Map<String, ImplementsExtends> daoToImplements = new TreeMap<String, ImplementsExtends>();
    protected ImplementsExtends daoToExtends = null;
    protected boolean daoMakeItFinal;
    protected Map<String, PojoEntityType> daoFunctionsResult = new HashMap<String, PojoEntityType>();
    protected Filter daoActiveFilter = null;
    protected String daoPackage;
    protected String daoImplPackage;
    protected DaoAnnotations daoAnnotations = null;

    public TableDaoGenerator(ModelProperty modelProperty, Artifacts artifacts, IScopeProvider scopeProvider,
            Map<String, String> finalDaos, Map<String, Map<String, String>> finalDaosFeatures,
            Annotations daoEntityAnnotations, Set<String> daoImports, List<String> dbSequences, DbType dbType) {
        super(modelProperty, artifacts, Collections.<String, String>emptyMap(),
                Collections.<String, Map<String, String>>emptyMap(), null, null, dbSequences, dbType);

        debug = new Debug(modelProperty.getDaoDebugLevel(artifacts), modelProperty.getDaoDebugScope(artifacts), LOGGER);

        this.finalDaos = finalDaos;
        this.finalDaosFeatures = finalDaosFeatures;
        this.daoEntityAnnotations = daoEntityAnnotations;
        this.daoImports = new TreeSet<String>();
        if (daoImports != null)
            this.daoImports.addAll(daoImports);

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
        Map<String, PojoEntityType> daoFunctionsResult = modelProperty.getDaoFunctionsResult(artifacts);
        if (daoFunctionsResult != null) {
            this.daoFunctionsResult.putAll(daoFunctionsResult);
        }
        this.daoActiveFilter = Filter.parse(modelProperty.getDaoActiveFilter(artifacts));
        daoPackage = modelProperty.getDaoPackage(artifacts);
        daoImplPackage = modelProperty.getDaoImplPackage(artifacts);
        daoAnnotations = modelProperty.getDaoAnnotations(model);

        debug.debug("finalDaos " + this.finalDaos);
        debug.debug("finalDaosFeatures " + this.finalDaosFeatures);
        debug.debug("daoAnnotations " + this.daoEntityAnnotations);
        debug.debug("daoImports " + this.daoImports);
        debug.debug("daoIgnoreTables " + this.daoIgnoreTables);
        debug.debug("daoOnlyTables " + this.daoOnlyTables);
        debug.debug("daoToImplements " + this.daoToImplements);
        debug.debug("daoToExtends " + this.daoToExtends);
        debug.debug("daoFunctionsResult " + this.daoFunctionsResult);
        debug.debug("daoActiveFilter " + this.daoActiveFilter);
        debug.debug("daoPackage " + this.daoPackage);
        debug.debug("daoImplPackage " + this.daoImplPackage);
        debug.debug("daoAnnotations " + this.daoAnnotations);
    }

    public String getDaoDefinitions(ModelProperty modelProperty, Artifacts artifacts, ISerializer serializer) {
        String result = getDaoDefinitions(serializer);
        return Utils.replaceAll(modelProperty, result, artifacts);
    }

    public String getDaoDefinitions(ISerializer serializer) {
        StringBuilder bufferPartial = _getDaoDefinitions(serializer);
        if (daoImports == null || daoImports.isEmpty() || pojoPackage == null)
            return bufferPartial.toString();

        StringBuilder buffer = new StringBuilder("\n");
        for (String name : daoImports) {
            buffer.append(INDENT).append("import ").append(name).append("\n");
        }
        buffer.append(bufferPartial);
        return buffer.toString();
    }

    public StringBuilder _getDaoDefinitions(ISerializer serializer) {
        debug.debug("pojos " + this.pojos);
        debug.debug("pojoExtends " + this.pojoExtends);
        debug.debug("pojoInheritanceDiscriminator " + this.pojoInheritanceDiscriminator);
        debug.debug("pojoInheritanceSimple " + this.pojoInheritanceSimple);
        debug.debug("pojoDiscriminators " + this.pojoDiscriminators);
        debug.debug("indexes " + this.indexes);
        debug.debug("procedures " + this.procedures);
        debug.debug("functions " + this.functions);

        StringBuilder buffer = new StringBuilder(), bufferPartial, bufferMeta;
        boolean isSerializable = false;
        Set<String> serializables = new HashSet<String>();
        boolean oneMoreLine = false;
        if (!daoToImplements.isEmpty()) {
            for (ImplementsExtends ie : daoToImplements.values()) {
                String identifier = ie.getIdentifier();
                if (identifier.endsWith("Serializable")) {
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
                if (ie.isGenerics()) {
                    buffer.append(NLINDENT).append("#Generics");
                }
                if (!ie.getDbTables().isEmpty()) {
                    buffer.append(NLINDENT).append("#OnlyDaos(");
                    boolean first = true;
                    for (String dbColumn : ie.getDbTables()) {
                        if (!first)
                            buffer.append(",");
                        else
                            first = false;
                        String pojoName = tableNames.get(dbColumn);
                        if (pojoName == null)
                            pojoName = dbColumn;
                        String daoName = tableToCamelCase(pojoName) + "Dao";
                        buffer.append(daoName);
                    }
                    buffer.append(")");
                }
                if (!ie.getDbNotTables().isEmpty()) {
                    buffer.append(NLINDENT).append("#ExceptDaos(");
                    boolean first = true;
                    for (String dbColumn : ie.getDbNotTables()) {
                        if (!first)
                            buffer.append(",");
                        else
                            first = false;
                        String pojoName = tableNames.get(dbColumn);
                        if (pojoName == null)
                            pojoName = dbColumn;
                        String daoName = tableToCamelCase(pojoName) + "Dao";
                        buffer.append(daoName);
                    }
                    buffer.append(")");
                }
                buffer.append(NLINDENT).append("implements ").append(identifier);
            }
            oneMoreLine = true;
        }
        if (daoToExtends != null) {
            if (daoToExtends.isGenerics())
                buffer.append(NLINDENT).append("#Generics");
            if (!daoToExtends.getDbTables().isEmpty()) {
                buffer.append(NLINDENT).append("#OnlyDaos(");
                boolean first = true;
                for (String dbColumn : daoToExtends.getDbTables()) {
                    if (!first)
                        buffer.append(",");
                    else
                        first = false;
                    String pojoName = tableNames.get(dbColumn);
                    if (pojoName == null)
                        pojoName = dbColumn;
                    String daoName = tableToCamelCase(pojoName) + "Dao";
                    buffer.append(daoName);
                }
                buffer.append(")");
            }
            if (!daoToExtends.getDbNotTables().isEmpty()) {
                buffer.append(NLINDENT).append("#ExceptDaos(");
                boolean first = true;
                for (String dbColumn : daoToExtends.getDbNotTables()) {
                    if (!first)
                        buffer.append(",");
                    else
                        first = false;
                    String pojoName = tableNames.get(dbColumn);
                    if (pojoName == null)
                        pojoName = dbColumn;
                    String daoName = tableToCamelCase(pojoName) + "Dao";
                    buffer.append(daoName);
                }
                buffer.append(")");
            }
            oneMoreLine = true;
            buffer.append(NLINDENT).append("extends ").append(daoToExtends.getIdentifier());
        }
        if (oneMoreLine) {
            buffer.append(NL);
        }

        // Pojo

        for (String pojo : pojos.keySet()) {
            if (!daoOnlyTables.isEmpty() && !daoOnlyTables.contains(pojo))
                continue;
            if (daoIgnoreTables.contains(pojo)
                    || (metaProceduresResultSet.values().contains(pojo) && !tables.contains(pojo)))
                continue;
            if (!Filter.isTable(daoActiveFilter, pojo))
                continue;
            String pojoName = tableNames.get(pojo);
            if (pojoName == null)
                pojoName = pojo;
            pojoName = tableToCamelCase(pojoName);
            String daoName = pojoName + "Dao";
            if (finalDaos.containsKey(daoName)) {
                buffer.append(Utils.getFinalContent(finalDaos.get(daoName)));
                continue;
            }
            if (pojoInheritanceDiscriminator.containsKey(pojo) || pojoInheritanceSimple.containsKey(pojo)) {
                if (!notAbstractTables.contains(pojo))
                    continue;
            }
            if (daoEntityAnnotations != null) {
                buffer.append(daoEntityAnnotations.getEntityAnnotationsDefinitions(daoName, serializer, true,
                        daoEntityAnnotations.isNonStandardPojoAnnotations(daoName)));
                buffer.append(daoEntityAnnotations.getConstructorAnnotationsDefinitions(daoName, serializer, true));
                buffer.append(daoEntityAnnotations.getStaticAnnotationsDefinitions(daoName, serializer, true));
                buffer.append(daoEntityAnnotations.getConflictAnnotationsDefinitions(daoName, serializer, true));
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
                            bufferMeta.append("=").append(pojoPackage).append(".").append(tableToCamelCase(pojoName2));
                        }
                    } else {
                        for (String pojo2 : pojoInheritanceDiscriminator.get(entry.getValue())) {
                            bufferMeta.append(",\"").append(pojoDiscriminators.get(pojo2)).append("\"");
                            String pojoName2 = tableNames.get(pojo2);
                            if (pojoName2 == null)
                                pojoName2 = pojo2;
                            bufferMeta.append("=").append(pojoPackage).append(".").append(tableToCamelCase(pojoName2));
                        }
                    }
                    bufferMeta.append(")");
                }
                bufferMeta.append(nlindent()).append("#CRUD(").append(pojoPackage).append(".").append(pojoName)
                        .append(")");
                bufferMeta.append(nlindent()).append("#Query(").append(pojoPackage).append(".").append(pojoName)
                        .append(")");
            }
            if (bufferMeta.length() > 0 && bufferMeta.charAt(0) == ' ')
                buffer.append(NLINDENT).append(bufferMeta.substring(1));
            else
                buffer.append(bufferMeta);
            buffer.append(NLINDENT);
            if (daoMakeItFinal)
                buffer.append("final ");
            buffer.append("#Dao ");
            buffer.append(daoName);
            buffer.append(" {");
            if (finalDaosFeatures.containsKey(daoName)) {
                buffer.append("\n");
                for (Entry<String, String> e : finalDaosFeatures.get(daoName).entrySet()) {
                    buffer.append(Utils.getFinalContent(e.getValue()));
                }
                buffer.append(INDENT).append("}\n");
            } else
                buffer.append(NLINDENT).append("}\n");
        }

        // Procedure

        for (String procedure : procedures.keySet()) {
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
                buffer.append(Utils.getFinalContent(finalDaos.get(daoName)));
                continue;
            }
            if (daoEntityAnnotations != null) {
                buffer.append(daoEntityAnnotations.getEntityAnnotationsDefinitions(daoName, serializer, true,
                        daoEntityAnnotations.isNonStandardPojoAnnotations(daoName)));
                buffer.append(daoEntityAnnotations.getConstructorAnnotationsDefinitions(daoName, serializer, true));
                buffer.append(daoEntityAnnotations.getStaticAnnotationsDefinitions(daoName, serializer, true));
                buffer.append(daoEntityAnnotations.getConflictAnnotationsDefinitions(daoName, serializer, true));
            }

            // String procedureName = lowerFirstChar(pojoName);
            {
                bufferMeta = new StringBuilder();
                if (isSerializable || serializables.contains(procedure))
                    bufferMeta.append(nlindent()).append("#Serializable(1)");
                Map<String, PojoAttribute> attributes = procedures.get(procedure);
                if (metaProceduresResultSet.containsKey(procedure)) {
                    String name = metaProceduresResultSet.get(procedure);
                    // TODO
                    if (tableNames.containsKey(name))
                        name = tableNames.get(name);
                    bufferMeta.append(nlindent()).append("#ProcedureCallQuery(").append(COLLECTION_LIST).append("<")
                            .append(pojos.containsKey(name) ? pojoPackage + "." : "").append(tableToCamelCase(name))
                            .append(">");
                } else {
                    PojoAttribute returnAttribute = (attributes.containsKey(FAKE_FUN_PROC_COLUMN_NAME))
                            ? attributes.get(FAKE_FUN_PROC_COLUMN_NAME)
                            : null;
                    if (returnAttribute != null && dbType != DbType.POSTGRESQL && dbType != DbType.MS_SQL) {
                        bufferMeta.append(nlindent()).append("#ProcedureCallQuery(").append(COLLECTION_LIST).append("<")
                                .append(returnAttribute.getClassName()).append(">");
                    } else {
                        bufferMeta.append(nlindent()).append("#ProcedureUpdate(int");
                    }
                }
                String dispName = null;
                PojoEntityType ptype = pojosForProcedures.get(procedure);
                if (ptype != null)
                    dispName = ptype.getSimpleName();
                bufferMeta.append(",").append(pojoPackage).append(".").append((dispName != null) ? dispName : pojoName);
                bufferMeta.append(")");
            }
            if (bufferMeta.length() > 0 && bufferMeta.charAt(0) == ' ')
                buffer.append(NLINDENT).append(bufferMeta.substring(1));
            else
                buffer.append(bufferMeta);
            buffer.append(NLINDENT);
            if (daoMakeItFinal)
                buffer.append("final ");
            buffer.append("#Dao ");
            buffer.append(daoName);
            buffer.append(" {");
            if (finalDaosFeatures.containsKey(daoName)) {
                buffer.append("\n");
                for (Entry<String, String> e : finalDaosFeatures.get(daoName).entrySet()) {
                    buffer.append(Utils.getFinalContent(e.getValue()));
                }
                buffer.append(INDENT).append("}\n");
            } else
                buffer.append(NLINDENT).append("}\n");
        }

        // Procedure is function

        for (String function : procedures.keySet()) {
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
                buffer.append(Utils.getFinalContent(finalDaos.get(daoName)));
                continue;
            }
            if (daoEntityAnnotations != null) {
                buffer.append(daoEntityAnnotations.getEntityAnnotationsDefinitions(daoName, serializer, true,
                        daoEntityAnnotations.isNonStandardPojoAnnotations(daoName)));
                buffer.append(daoEntityAnnotations.getConstructorAnnotationsDefinitions(daoName, serializer, true));
                buffer.append(daoEntityAnnotations.getStaticAnnotationsDefinitions(daoName, serializer, true));
                buffer.append(daoEntityAnnotations.getConflictAnnotationsDefinitions(daoName, serializer, true));
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
                    bufferMeta.append(nlindent()).append("#FunctionCallQuery(").append(COLLECTION_LIST).append("<")
                            .append(pojos.containsKey(name) ? pojoPackage + "." : "").append(tableToCamelCase(name))
                            .append(">");
                } else if (metaFunctionsResult.containsKey(function)) {
                    bufferMeta.append(nlindent()).append("#FunctionCall(")
                            .append(metaType2className(metaFunctionsResult.get(function)));
                } else {
                    PojoAttribute returnAttribute = (attributes.containsKey(FAKE_FUN_PROC_COLUMN_NAME))
                            ? attributes.get(FAKE_FUN_PROC_COLUMN_NAME)
                            : null;
                    if (returnAttribute != null) {
                        bufferMeta.append(nlindent()).append("#FunctionCallQuery(").append(COLLECTION_LIST).append("<")
                                .append(returnAttribute.getClassName()).append(">");
                    } else {
                        bufferMeta.append(nlindent()).append("#FunctionUpdate(int");
                    }
                }
                String dispName = null;
                PojoEntityType ptype = pojosForProcedures.get(function);
                if (ptype != null)
                    dispName = ptype.getSimpleName();
                bufferMeta.append(",").append(pojoPackage).append(".").append((dispName != null) ? dispName : pojoName);
                bufferMeta.append(")");

            }
            if (bufferMeta.length() > 0 && bufferMeta.charAt(0) == ' ')
                buffer.append(NLINDENT).append(bufferMeta.substring(1));
            else
                buffer.append(bufferMeta);
            buffer.append(NLINDENT);
            if (daoMakeItFinal)
                buffer.append("final ");
            buffer.append("#Dao ");
            buffer.append(daoName);
            buffer.append(" {");
            if (finalDaosFeatures.containsKey(daoName)) {
                buffer.append("\n");
                for (Entry<String, String> e : finalDaosFeatures.get(daoName).entrySet()) {
                    buffer.append(Utils.getFinalContent(e.getValue()));
                }
                buffer.append(INDENT).append("}\n");
            } else
                buffer.append(NLINDENT).append("}\n");
        }

        // Function

        for (String function : functions.keySet()) {
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
                buffer.append(Utils.getFinalContent(finalDaos.get(daoName)));
                continue;
            }
            if (daoEntityAnnotations != null) {
                buffer.append(daoEntityAnnotations.getEntityAnnotationsDefinitions(daoName, serializer, true,
                        daoEntityAnnotations.isNonStandardPojoAnnotations(daoName)));
                buffer.append(daoEntityAnnotations.getConstructorAnnotationsDefinitions(daoName, serializer, true));
                buffer.append(daoEntityAnnotations.getStaticAnnotationsDefinitions(daoName, serializer, true));
                buffer.append(daoEntityAnnotations.getConflictAnnotationsDefinitions(daoName, serializer, true));
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
                    bufferMeta.append(nlindent()).append("#FunctionCallQuery(").append(COLLECTION_LIST).append("<")
                            .append(pojos.containsKey(name) ? pojoPackage + "." : "").append(tableToCamelCase(name))
                            .append(">");
                } else if (metaFunctionsResult.containsKey(function) && dbType == DbType.DB2) {
                    bufferMeta.append(nlindent()).append("#FunctionQuery(")
                            .append(metaType2className(metaFunctionsResult.get(function)));
                } else if (metaFunctionsResult.containsKey(function)) {
                    bufferMeta.append(nlindent()).append("callFunction ")
                            .append(metaType2className(metaFunctionsResult.get(function)));
                } else {
                    PojoAttribute returnAttribute = (attributes.containsKey(FAKE_FUN_PROC_COLUMN_NAME))
                            ? attributes.get(FAKE_FUN_PROC_COLUMN_NAME)
                            : null;
                    if (returnAttribute != null) {
                        bufferMeta.append(nlindent()).append("#FunctionCallQuery(").append(COLLECTION_LIST).append("<")
                                .append(returnAttribute.getClassName()).append(">");
                    } else {
                        bufferMeta.append(nlindent()).append("#FunctionUpdate(int");
                    }
                }
                String dispName = null;
                PojoEntityType ptype = pojosForProcedures.get(function);
                if (ptype != null)
                    dispName = ptype.getSimpleName();
                bufferMeta.append(",").append(pojoPackage).append(".").append((dispName != null) ? dispName : pojoName);
                bufferMeta.append(")");

            }
            if (bufferMeta.length() > 0 && bufferMeta.charAt(0) == ' ')
                buffer.append(NLINDENT).append(bufferMeta.substring(1));
            else
                buffer.append(bufferMeta);
            buffer.append(NLINDENT);
            if (daoMakeItFinal)
                buffer.append("final ");
            buffer.append("#Dao ");
            buffer.append(daoName);
            buffer.append(" {");
            if (finalDaosFeatures.containsKey(daoName)) {
                buffer.append("\n");
                for (Entry<String, String> e : finalDaosFeatures.get(daoName).entrySet()) {
                    buffer.append(Utils.getFinalContent(e.getValue()));
                }
                buffer.append(INDENT).append("}\n");
            } else
                buffer.append(NLINDENT).append("}\n");
        }

        return buffer;
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
                    }
                }
            }
        }
        if (pojoExtends.containsKey(pojo)) {
            toInits(pojoExtends.get(pojo), toInit);
        }
    }

    public static String generateDao(Artifacts artifacts, Package packagex, ISerializer serializer,
            DbResolver dbResolver, IScopeProvider scopeProvider, ModelProperty modelProperty, Stats stats) {
        if (artifacts == null || !dbResolver.isResolveDb(artifacts))
            return null;
        if (serializer == null)
            serializer = ((XtextResource) packagex.eResource()).getSerializer();

        Set<String> imports = (packagex != null) ? Utils.getImports(packagex, serializer) : null;
        Map<String, String> finalDaos = new HashMap<String, String>();
        Map<String, Map<String, String>> finalFeatures = new HashMap<String, Map<String, String>>();
        Annotations annotations = new Annotations(INDENT, NL);
        if (packagex != null) {
            for (AbstractEntity ape : packagex.getElements()) {
                if (ape instanceof AnnotatedEntity && ((AnnotatedEntity) ape).getEntity() instanceof DaoEntity) {
                    DaoEntity dao = (DaoEntity) ((AnnotatedEntity) ape).getEntity();
                    Annotations.grabAnnotations((AnnotatedEntity) ape, annotations);
                    if (dao.isFinal()) {
                        // ISerializer serializer = ((XtextResource) dao.eResource()).getSerializer();
                        finalDaos.put(dao.getName(), serializer.serialize(dao));
                    } else {
                        Map<String, String> map;
                        finalFeatures.put(dao.getName(), map = new LinkedHashMap<String, String>());
                        for (AnnotatedFeature feature : dao.getFeatures()) {
                            if (feature.getFeature().isFinal())
                                map.put(feature.getFeature().getName(), serializer.serialize(feature));
                        }
                    }
                }
            }
        }

        // List<String> tables = dbResolver.getTables(artifacts);
        List<String> dbSequences = dbResolver.getSequences(artifacts);
        DbType dbType = Utils.getDbType(dbResolver, artifacts);
        TableDaoGenerator generator = new TableDaoGenerator(modelProperty, artifacts, scopeProvider, finalDaos,
                finalFeatures, annotations, imports, dbSequences, dbType);
        try {
            if (generator.addDefinitions(dbResolver, scopeProvider, stats))
                return generator.getDaoDefinitions(modelProperty, artifacts, serializer);
        } catch (RuntimeException ex) {
            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);
            String s = writer.toString();
            return s;
        }
        return null;
    }
}
