package org.sqlproc.model.generator;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.serializer.ISerializer;
import org.sqlproc.model.processorModel.AbstractEntity;
import org.sqlproc.model.processorModel.AnnotatedEntity;
import org.sqlproc.model.processorModel.AnnotatedFeature;
import org.sqlproc.model.processorModel.Artifacts;
import org.sqlproc.model.processorModel.EnumEntity;
import org.sqlproc.model.processorModel.Package;
import org.sqlproc.model.processorModel.PojoEntity;
import org.sqlproc.model.util.Annotations;
import org.sqlproc.model.util.Utils;
import org.sqlproc.plugin.lib.generator.TableBaseGenerator;
import org.sqlproc.plugin.lib.property.ColumnAnnotations;
import org.sqlproc.plugin.lib.property.EnumAttribute;
import org.sqlproc.plugin.lib.property.ImplementsExtends;
import org.sqlproc.plugin.lib.property.ModelProperty;
import org.sqlproc.plugin.lib.property.PojoAnnotations;
import org.sqlproc.plugin.lib.property.PojoAttribute;
import org.sqlproc.plugin.lib.property.PojoDefinition;
import org.sqlproc.plugin.lib.resolver.DbResolver;
import org.sqlproc.plugin.lib.resolver.DbResolver.DbType;
import org.sqlproc.plugin.lib.util.Stats;

public class TablePojoGenerator extends TableBaseGenerator {

    protected Artifacts artifacts;
    protected Annotations entityAnnotations;
    protected Map<String, String> finalEntities;
    protected Map<String, Map<String, String>> finalEntitiesFeatures;
    protected Set<String> entityImports;

    public TablePojoGenerator(ModelProperty modelProperty, Artifacts artifacts, Map<String, String> finalEntities,
            Map<String, Map<String, String>> finalEntitiesFeatures, Annotations entityAnnotations,
            Set<String> entityImports, List<String> dbSequences, DbType dbType) {
        super(modelProperty, artifacts, dbSequences, dbType);

        this.artifacts = artifacts;
        this.entityAnnotations = entityAnnotations;
        this.finalEntities = finalEntities;
        this.finalEntitiesFeatures = finalEntitiesFeatures;
        this.entityImports = new TreeSet<String>();
        if (entityImports != null)
            this.entityImports.addAll(entityImports);

        if (doGenerateValidationAnnotations) {
            this.entityImports.add(ANNOTATION_NOT_NULL);
            this.entityImports.add(ANNOTATION_SIZE);
        }

        debug.debug("entityAnnotations " + this.entityAnnotations);
        debug.debug("finalEntities " + this.finalEntities);
        debug.debug("finalEntitiesFeatures " + this.finalEntitiesFeatures);
        debug.debug("entityImports " + this.entityImports);
    }

    public String getPojoDefinitions(ModelProperty modelProperty, Artifacts artifacts, ISerializer serializer) {
        String result = getPojoDefinitions(serializer);
        return Utils.replaceAll(modelProperty, result, artifacts);
    }

    public String getPojoDefinitions(ISerializer serializer) {
        debug.debug("pojos " + this.pojos);
        debug.debug("pojoExtends " + this.pojoExtends);
        debug.debug("pojoInheritanceDiscriminator " + this.pojoInheritanceDiscriminator);
        debug.debug("pojoInheritanceSimple " + this.pojoInheritanceSimple);
        debug.debug("pojoDiscriminators " + this.pojoDiscriminators);
        debug.debug("indexes " + this.indexes);
        debug.debug("procedures " + this.procedures);
        debug.debug("functions " + this.functions);
        debug.debug("enums " + this.enums);

        StringBuilder buffer = new StringBuilder(), bufferPojo, bufferPartial, bufferMeta, bufferMetaAttr;
        boolean isSerializable = false;
        Set<String> serializables = new HashSet<String>();
        boolean oneMoreLine = false;

        if (entityImports != null && !entityImports.isEmpty()) {
            for (String qualifiedName : entityImports) {
                buffer.append(NLINDENT).append("import ").append(qualifiedName);
            }
            oneMoreLine = true;
        }
        if (oneMoreLine) {
            buffer.append("\n");
        }

        if (!toImplements.isEmpty()) {
            for (ImplementsExtends ie : toImplements.values()) {
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
                if (ie.isGenerics())
                    buffer.append(NLINDENT).append("#Generics");
                if (!ie.getDbTables().isEmpty()) {
                    buffer.append(NLINDENT).append("#OnlyPojos(");
                    boolean first = true;
                    for (String dbTable : ie.getDbTables()) {
                        if (!first)
                            buffer.append(",");
                        else
                            first = false;
                        String pojoName = tableNames.get(dbTable);
                        if (pojoName == null)
                            pojoName = dbTable;
                        String realPojoName = tableToCamelCase(pojoName);
                        buffer.append(realPojoName);
                    }
                    buffer.append(")");
                }
                if (!ie.getDbNotTables().isEmpty()) {
                    buffer.append(NLINDENT).append("#ExceptPojos(");
                    boolean first = true;
                    for (String dbTable : ie.getDbNotTables()) {
                        if (!first)
                            buffer.append(",");
                        else
                            first = false;
                        String pojoName = tableNames.get(dbTable);
                        if (pojoName == null)
                            pojoName = dbTable;
                        String realPojoName = tableToCamelCase(pojoName);
                        buffer.append(realPojoName);
                    }
                    buffer.append(")");
                }
                buffer.append(NLINDENT).append("implements ").append(identifier);
            }
            oneMoreLine = true;
        }
        if (toExtends != null) {
            if (toExtends.isGenerics())
                buffer.append(NLINDENT).append("#Generics");
            if (!toExtends.getDbTables().isEmpty()) {
                buffer.append(NLINDENT).append("#OnlyPojos(");
                boolean first = true;
                for (String dbTable : toExtends.getDbTables()) {
                    if (!first)
                        buffer.append(",");
                    else
                        first = false;
                    String pojoName = tableNames.get(dbTable);
                    if (pojoName == null)
                        pojoName = dbTable;
                    String realPojoName = tableToCamelCase(pojoName);
                    buffer.append(realPojoName);
                }
                buffer.append(")");
            }
            if (!toExtends.getDbNotTables().isEmpty()) {
                buffer.append(NLINDENT).append("#ExceptPojos(");
                boolean first = true;
                for (String dbTable : toExtends.getDbNotTables()) {
                    if (!first)
                        buffer.append(",");
                    else
                        first = false;
                    String pojoName = tableNames.get(dbTable);
                    if (pojoName == null)
                        pojoName = dbTable;
                    String realPojoName = tableToCamelCase(pojoName);
                    buffer.append(realPojoName);
                }
                buffer.append(")");
            }
            buffer.append(NLINDENT).append("extends ").append(toExtends.getIdentifier());
            oneMoreLine = true;
        }
        if (oneMoreLine) {
            buffer.append("\n");
        }

        // Enum

        for (Entry<String, List<EnumAttribute>> pentry : enums.entrySet()) {
            String pojo = pentry.getKey();
            String table = enumsTables.get(pojo);
            if (table != null) {
                if (!onlyTables.isEmpty() && !onlyTables.contains(table))
                    continue;
                if (ignoreTables.contains(table))
                    continue;
                if (!Filter.isTable(activeFilter, table))
                    continue;
            }
            String pojoName = tableNames.get(pojo);
            if (pojoName == null)
                pojoName = pojo;
            String realPojoName = tableToCamelCase(pojoName);
            if (finalEntities.containsKey(realPojoName)) {
                buffer.append(Utils.getFinalContent(finalEntities.get(realPojoName)));
                continue;
            }
            if (entityAnnotations != null) {
                buffer.append(entityAnnotations.getEntityAnnotationsDefinitions(realPojoName, serializer, true,
                        entityAnnotations.isNonStandardPojoAnnotations(realPojoName)));
                buffer.append(entityAnnotations.getConstructorAnnotationsDefinitions(realPojoName, serializer, true));
                buffer.append(entityAnnotations.getStaticAnnotationsDefinitions(realPojoName, serializer, true));
                buffer.append(entityAnnotations.getConflictAnnotationsDefinitions(realPojoName, serializer, true));
            }
            if (isSerializable || serializables.contains(pojo))
                buffer.append(NLINDENT).append("#Serializable(1)");

            printComment(buffer, comments.get(pojo), INDENT);
            buffer.append(NLINDENT);
            if (makeItFinal)
                buffer.append("final ");
            buffer.append("#Enum ");
            if (enumForCheckConstraints.containsKey(pojoName))
                buffer.append(enumForCheckConstraints.get(pojoName));
            else
                buffer.append(realPojoName);
            if (pojoExtends.containsKey(pojo))
                buffer.append(" extends ").append(tableToCamelCase(pojoExtends.get(pojo)));
            buffer.append(" {");
            buffer.append(NLINDENT).append(INDENT).append("#Values(");
            Set<String> values = new TreeSet<>();
            for (EnumAttribute attribute : pentry.getValue()) {
                StringBuilder sb = new StringBuilder();
                if (attribute.getIntValue() == null && attribute.getStrValue() == null)
                    continue;
                String name = (columnNames.containsKey(pojo)) ? columnNames.get(pojo).get(attribute.getName()) : null;
                if (name == null)
                    name = attribute.getName();
                if (attribute.getIntValue() == null && attribute.getStrValue() == null)
                    name = columnToCamelCase(name);
                sb.append(name).append(" = ");
                if (attribute.getIntValue() != null) {
                    sb.append(attribute.getIntValue());
                } else if (attribute.getStrValue() != null) {
                    sb.append("\"").append(attribute.getStrValue()).append("\"");
                }
                values.add(sb.toString());
            }
            boolean first = true;
            for (String value : values) {
                if (!first)
                    buffer.append(",");
                else
                    first = false;
                buffer.append(value);
            }
            buffer.append(")");
            for (EnumAttribute attribute : pentry.getValue()) {
                if (attribute.getIntValue() == null && attribute.getStrValue() == null) {
                    String name = (columnNames.containsKey(pojo)) ? columnNames.get(pojo).get(attribute.getName())
                            : null;
                    if (name == null)
                        name = attribute.getName();
                    if (attribute.getIntValue() == null && attribute.getStrValue() == null)
                        name = columnToCamelCase(name);
                    buffer.append(NLINDENT).append(INDENT).append("#Attr ").append(attribute.getWrapperClassName())
                            .append(' ').append(name);
                }
            }
            // if (pojoExtends.containsKey(pojo)) {
            // getParentAttrs(pojoExtends.get(pojo), null, null);
            // }
            if (finalEntitiesFeatures.containsKey(realPojoName)) {
                buffer.append("\n");
                for (Entry<String, String> e : finalEntitiesFeatures.get(realPojoName).entrySet()) {
                    buffer.append(Utils.getFinalContent(e.getValue()));
                }
                buffer.append(INDENT).append("}\n");
            } else
                buffer.append(NLINDENT).append("}\n");
        }

        // Pojo

        for (String pojo : pojos.keySet()) {
            if (!onlyTables.isEmpty() && !onlyTables.contains(pojo))
                continue;
            if (ignoreTables.contains(pojo))
                continue;
            if (!Filter.isTable(activeFilter, pojo))
                continue;
            String pojoName = tableNames.get(pojo);
            if (pojoName == null)
                pojoName = pojo;
            String realPojoName = tableToCamelCase(pojoName);
            if (finalEntities.containsKey(realPojoName)) {
                buffer.append(Utils.getFinalContent(finalEntities.get(realPojoName)));
                continue;
            }
            if (entityAnnotations != null) {
                buffer.append(entityAnnotations.getEntityAnnotationsDefinitions(realPojoName, serializer, true,
                        entityAnnotations.isNonStandardPojoAnnotations(realPojoName)));
                buffer.append(entityAnnotations.getConstructorAnnotationsDefinitions(realPojoName, serializer, true));
                buffer.append(entityAnnotations.getStaticAnnotationsDefinitions(realPojoName, serializer, true));
                buffer.append(entityAnnotations.getConflictAnnotationsDefinitions(realPojoName, serializer, true));
            }
            addPojoAnnotations(realPojoName, buffer);
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
                    Set<String> indSet = new TreeSet<String>();
                    for (int i = 0, l = mainList.size(); i < l; i++) {
                        StringBuilder sb = new StringBuilder();
                        boolean first = true;
                        for (PojoAttribute attr : mainList.get(i).keySet()) {
                            if (attr.getDbName() != null) {
                                if (ignoreColumns.containsKey(pojo)
                                        && ignoreColumns.get(pojo).contains(attr.getDbName())) {
                                    sb = null;
                                    break;
                                }
                            }
                            String name = (columnNames.containsKey(pojo)) ? columnNames.get(pojo).get(attr.getName())
                                    : null;
                            if (name == null)
                                name = attr.getName();
                            else
                                name = columnToCamelCase(name);
                            if (!first)
                                sb.append(",");
                            else
                                first = false;
                            sb.append(name);
                        }
                        if (sb != null) {
                            indSet.add(sb.toString());
                            // indSet.add("#Index(" + (i + 1) + sb.toString());
                            // bufferMeta.append(nlindent()).append("#Index(").append(i + 1).append(sb).append(")");
                        }
                    }
                    int i = 0;
                    for (String s : indSet) {
                        ++i;
                        bufferMeta.append(nlindent());
                        if (generateMethods.contains(METHOD_ENUM_INDEX))
                            bufferMeta.append("#EnumIndex(");
                        else
                            bufferMeta.append("#Index(");
                        bufferMeta.append(s).append(")");
                    }
                }
            }

            Set<String> pkeys = new LinkedHashSet<String>();
            Set<String> toStr = new LinkedHashSet<String>();
            // TODO
            Set<String> procId = new LinkedHashSet<String>();
            {
                bufferPartial = new StringBuilder();
                printComment(bufferPartial, comments.get(pojo), INDENT);
                bufferPartial.append(NLINDENT);
                if (makeItFinal)
                    bufferPartial.append("final ");
                if (pojoInheritanceDiscriminator.containsKey(pojo) || pojoInheritanceSimple.containsKey(pojo)) {
                    if (!notAbstractTables.contains(pojo))
                        bufferPartial.append("abstract ");
                }
                bufferPartial.append("#Pojo ");
                bufferPartial.append(realPojoName);
                if (pojoExtends.containsKey(pojo))
                    bufferPartial.append(" extends ").append(tableToCamelCase(pojoExtends.get(pojo)));
                bufferPartial.append(" {");
                // Set<String> isDef = new HashSet<String>();
                // Set<String> toInit = new HashSet<String>();
                Map<String, PojoAttribute> addedAttributes = new LinkedHashMap<String, PojoAttribute>();
                PojoAttribute pkAttribute = null;
                // TODO
                // boolean morePkAttributes = false;
                for (Map.Entry<String, PojoAttribute> pentry : pojos.get(pojo).entrySet()) {
                    if (ignoreColumns.containsKey(pojo) && ignoreColumns.get(pojo).contains(pentry.getKey()))
                        continue;
                    PojoAttribute attribute = pentry.getValue();
                    String name = (columnNames.containsKey(pojo)) ? columnNames.get(pojo).get(pentry.getKey()) : null;
                    if (name == null)
                        name = attribute.getName();
                    else
                        name = columnToCamelCase(name);
                    if (entityAnnotations != null) {
                        bufferPartial.append(entityAnnotations.getAttributeAnnotationsDefinitions(realPojoName, name,
                                serializer, true, entityAnnotations.isNonStandardPojoAnnotations(realPojoName, name)));
                        bufferPartial.append(entityAnnotations.getGetterAnnotationsDefinitions(realPojoName, name,
                                serializer, true));
                        bufferPartial.append(entityAnnotations.getSetterAnnotationsDefinitions(realPojoName, name,
                                serializer, true));
                    }
                    if (doGenerateValidationAnnotations) {
                        if ((requiredColumns.containsKey(pojo) && requiredColumns.get(pojo).contains(pentry.getKey()))
                                || (attribute.isRequired() && !attribute.isPrimaryKey())) {
                            if (!notRequiredColumns.containsKey(pojo)
                                    || !notRequiredColumns.get(pojo).contains(pentry.getKey()))
                                if (entityAnnotations == null || !entityAnnotations
                                        .hasAttributeAnnotationsDefinitions(realPojoName, name, ANNOTATION_NOT_NULL)) {
                                    bufferPartial.append(NLINDENT).append(INDENT).append("@NotNull");
                                }
                        }
                        if (attribute.getDependencyClassName() == null && !attribute.isPrimitive()) {
                            if (attribute.isString() && attribute.getSize() > 0) {
                                if (entityAnnotations == null || !entityAnnotations
                                        .hasAttributeAnnotationsDefinitions(realPojoName, name, ANNOTATION_SIZE)) {
                                    bufferPartial.append(NLINDENT).append(INDENT).append("@Size(max = ")
                                            .append(attribute.getSize()).append(")");
                                }
                            }
                        }
                    }
                    addColumnAnnotations(realPojoName, name, bufferPartial);
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
                            else if (generateMethods.contains(METHOD_ENUM_TO_INIT))
                                bufferMetaAttr.append(nlindent2()).append("#EnumInit");
                        }
                        if (inheritanceColumns.containsKey(pojo)
                                && pentry.getKey().equals(inheritanceColumns.get(pojo))) {
                            bufferMetaAttr.append(nlindent2()).append("#InheritanceDiscriminator");
                        }
                        if (attribute.isVersion()) {
                            bufferMetaAttr.append(nlindent2()).append("#Version");
                        }
                        if (!attribute.isVersion() && ((requiredColumns.containsKey(pojo)
                                && requiredColumns.get(pojo).contains(pentry.getKey()))
                                || (attribute.isRequired() && !attribute.isPrimaryKey()))) {
                            if (!notRequiredColumns.containsKey(pojo)
                                    || !notRequiredColumns.get(pojo).contains(pentry.getKey()))
                                bufferMetaAttr.append(nlindent2()).append("#Required");
                        }
                        if (attribute.isPrimaryKey()) {
                            bufferMetaAttr.append(nlindent2()).append("#PrimaryKey");
                            if (doGenerateFromTo) {
                                if (pkAttribute == null)
                                    pkAttribute = attribute;
                                if (attribute.getDependencyPojo() != null) {
                                    if (attribute.isDependencyClassNameIsEnum()) {
                                        List<EnumAttribute> enumAttrs = enums.get(attribute.getDependencyPojo());
                                        if (enumAttrs != null)
                                            bufferMetaAttr.append("(value,").append(enumAttrs.get(0).getClassName())
                                                    .append(")");
                                    } else {
                                        PojoAttribute depPkAttribute = getPrimaryKey(attribute.getDependencyPojo());
                                        if (depPkAttribute != null)
                                            bufferMetaAttr.append("(")
                                                    .append(columnToCamelCase(depPkAttribute.getDbName())).append(",")
                                                    .append(depPkAttribute.getClassName()).append(")");
                                    }
                                } else if (attribute.isDependencyClassNameIsEnum()) {
                                    bufferMetaAttr.append("(value,").append("").append(")");
                                } else {
                                    bufferMetaAttr.append("()");
                                }
                            }
                            pkeys.add(name);
                        }
                        if (!generateMethods.contains(METHOD_INDEX) && attribute.getIndex() != null) {
                            bufferMetaAttr.append(nlindent2());
                            if (generateMethods.contains(METHOD_ENUM_INDEX))
                                bufferMetaAttr.append("#EnumIndex");
                            else
                                bufferMetaAttr.append("#Index");
                        }
                        if (attribute.getDependencyClassName() != null) {
                            if (!attribute.isDependencyClassNameIsEnum())
                                bufferMetaAttr.append(nlindent2()).append("#IsPojo");
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
                    printComment(bufferPartial, attribute.getComment(), INDENT, INDENT);
                    bufferPartial.append(NLINDENT).append(INDENT).append("#Attr ");
                    if (attribute.getDependencyClassName() != null) {
                        bufferPartial.append(attribute.getDependencyClassName());
                        if (attribute.isDependencyClassNameIsEnum()) {
                            toStr.add(name);
                        }
                        procId.add(name);
                    } else if (attribute.isPrimitive()) {
                        bufferPartial.append(attribute.getClassName());
                        toStr.add(name);
                        procId.add(name);
                    } else {
                        bufferPartial.append(attribute.getClassName());
                        if (!attribute.getClassName().startsWith(COLLECTION_LIST)) {
                            toStr.add(name);
                            procId.add(name);
                        }
                    }
                    bufferPartial.append(' ').append(name);
                    if (debug.isDebug())
                        bufferPartial.append(" // ").append(attribute.getCompleteSqlType());
                }
                if (pkAttribute != null) {
                    addColumnAnnotations(realPojoName, "onlyIds_", bufferPartial);
                    bufferPartial.append(NLINDENT).append(INDENT).append("#Attr boolean onlyIds_");
                    addColumnAnnotations(realPojoName, "ids_", bufferPartial);
                    bufferPartial.append(NLINDENT).append(INDENT).append("#Attr java.util.List <")
                            .append(pkAttribute.getWrapperClassName()).append("> ids_");
                }
                // if (pojoExtends.containsKey(pojo)) {
                // getParentAttrs(pojoExtends.get(pojo), null, null, toStr);
                // }
                for (Map.Entry<String, PojoAttribute> pentry : addedAttributes.entrySet()) {
                    PojoAttribute attribute = pentry.getValue();
                    bufferPartial.append(NLINDENT).append(INDENT).append("#CreateCol(").append(pentry.getKey())
                            .append(",").append(columnToCamelCase(attribute.getPkColumn())).append(")");
                    bufferPartial.append(NLINDENT).append(INDENT).append("#Attr ").append(attribute.getClassName())
                            .append(' ').append(columnToCamelCase(attribute.getDbName()));
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
            if (generateMethods.contains(METHOD_PROC_ID) && !procId.isEmpty()) {
                bufferMeta.append(nlindent()).append("#ProcId(");
                appendList(bufferMeta, procId);
                bufferMeta.append(")");
            }
            if (bufferMeta.length() > 0 && bufferMeta.charAt(0) == ' ')
                buffer.append(NLINDENT).append(bufferMeta.substring(1));
            else
                buffer.append(bufferMeta);
            buffer.append(bufferPartial);
            if (finalEntitiesFeatures.containsKey(realPojoName)) {
                buffer.append("\n");
                for (Entry<String, String> e : finalEntitiesFeatures.get(realPojoName).entrySet()) {
                    buffer.append(Utils.getFinalContent(e.getValue()));
                }
                buffer.append(INDENT).append("}\n");
            } else
                buffer.append(NLINDENT).append("}\n");
        }

        // Procedure

        for (String pojo : procedures.keySet()) {
            if (ignoreTables.contains(pojo))
                continue;
            if (pojosForProcedures.containsKey(pojo))
                continue;
            if (!Filter.isTable(activeFilter, pojo))
                continue;
            String pojoName = tableNames.get(pojo);
            if (pojoName == null)
                pojoName = pojo;
            String realPojoName = tableToCamelCase(pojoName);
            if (finalEntities.containsKey(realPojoName)) {
                buffer.append(Utils.getFinalContent(finalEntities.get(realPojoName)));
                continue;
            }
            if (entityAnnotations != null) {
                buffer.append(entityAnnotations.getEntityAnnotationsDefinitions(realPojoName, serializer, true,
                        entityAnnotations.isNonStandardPojoAnnotations(realPojoName)));
                buffer.append(entityAnnotations.getConstructorAnnotationsDefinitions(realPojoName, serializer, true));
                buffer.append(entityAnnotations.getStaticAnnotationsDefinitions(realPojoName, serializer, true));
                buffer.append(entityAnnotations.getConflictAnnotationsDefinitions(realPojoName, serializer, true));
            }
            addPojoAnnotations(realPojoName, buffer);
            {
                bufferMeta = new StringBuilder();
                if (isSerializable || serializables.contains(pojo))
                    bufferMeta.append(nlindent()).append("#Serializable(1)");
            }
            Set<String> toStr = new LinkedHashSet<String>();
            {
                bufferPartial = new StringBuilder();
                printComment(bufferPartial, comments.get(pojo), INDENT);
                bufferPartial.append(NLINDENT);
                if (makeItFinal)
                    bufferPartial.append("final ");
                bufferPartial.append("#Pojo ");
                bufferPartial.append(realPojoName);
                if (pojoExtends.containsKey(pojo))
                    bufferPartial.append(" extends ").append(tableToCamelCase(pojoExtends.get(pojo)));
                bufferPartial.append(" {");
                for (Map.Entry<String, PojoAttribute> pentry : procedures.get(pojo).entrySet()) {
                    if (FAKE_FUN_PROC_COLUMN_NAME.equals(pentry.getKey()))
                        continue;
                    if (ignoreColumns.containsKey(pojo) && ignoreColumns.get(pojo).contains(pentry.getKey()))
                        continue;
                    PojoAttribute attribute = pentry.getValue();
                    String name = (columnNames.containsKey(pojo)) ? columnNames.get(pojo).get(pentry.getKey()) : null;
                    if (name == null)
                        name = attribute.getName();
                    else
                        name = columnToCamelCase(name);
                    printComment(bufferPartial, attribute.getComment(), INDENT, INDENT);
                    if (entityAnnotations != null) {
                        bufferPartial.append(entityAnnotations.getAttributeAnnotationsDefinitions(realPojoName, name,
                                serializer, true, entityAnnotations.isNonStandardPojoAnnotations(realPojoName, name)));
                        bufferPartial.append(entityAnnotations.getGetterAnnotationsDefinitions(realPojoName, name,
                                serializer, true));
                        bufferPartial.append(entityAnnotations.getSetterAnnotationsDefinitions(realPojoName, name,
                                serializer, true));
                    }
                    if (doGenerateValidationAnnotations) {
                        if ((requiredColumns.containsKey(pojo) && requiredColumns.get(pojo).contains(pentry.getKey()))
                                || (attribute.isRequired() && !attribute.isPrimaryKey())) {
                            if (!notRequiredColumns.containsKey(pojo)
                                    || !notRequiredColumns.get(pojo).contains(pentry.getKey()))
                                if (entityAnnotations == null || !entityAnnotations
                                        .hasAttributeAnnotationsDefinitions(realPojoName, name, ANNOTATION_NOT_NULL)) {
                                    bufferPartial.append(NLINDENT).append(INDENT).append("@NotNull");
                                }
                        }
                        if (attribute.getDependencyClassName() == null && !attribute.isPrimitive()) {
                            if (attribute.isString() && attribute.getSize() > 0) {
                                if (entityAnnotations == null || !entityAnnotations
                                        .hasAttributeAnnotationsDefinitions(realPojoName, name, ANNOTATION_SIZE)) {
                                    bufferPartial.append(NLINDENT).append(INDENT).append("@Size(max = ")
                                            .append(attribute.getSize()).append(")");
                                }
                            }
                        }
                    }
                    addColumnAnnotations(realPojoName, name, bufferPartial);
                    if (!attribute.isVersion() && ((requiredColumns.containsKey(pojo)
                            && requiredColumns.get(pojo).contains(pentry.getKey()))
                            || (attribute.isRequired() && !attribute.isPrimaryKey()))) {
                        if (!notRequiredColumns.containsKey(pojo)
                                || !notRequiredColumns.get(pojo).contains(pentry.getKey()))
                            bufferPartial.append(nlindent2()).append("#Required");
                    }
                    bufferPartial.append(NLINDENT).append(INDENT).append("#Attr ");
                    if (attribute.getDependencyClassName() != null) {
                        bufferPartial.append(attribute.getDependencyClassName());
                        toStr.add(name);
                    } else if (attribute.isPrimitive()) {
                        bufferPartial.append(attribute.getClassName());
                        toStr.add(name);
                    } else {
                        bufferPartial.append(attribute.getClassName());
                        if (!attribute.getClassName().startsWith(COLLECTION_LIST))
                            toStr.add(name);
                    }
                    bufferPartial.append(' ').append(name);
                    if (debug.isDebug())
                        bufferPartial.append(" // ").append(attribute.getCompleteSqlType());
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
            if (finalEntitiesFeatures.containsKey(realPojoName)) {
                buffer.append("\n");
                for (Entry<String, String> e : finalEntitiesFeatures.get(realPojoName).entrySet()) {
                    buffer.append(Utils.getFinalContent(e.getValue()));
                }
                buffer.append(INDENT).append("}\n");
            } else
                buffer.append(NLINDENT).append("}\n");
        }

        // Function

        for (String pojo : functions.keySet()) {
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
            String realPojoName = tableToCamelCase(pojoName);
            if (finalEntities.containsKey(realPojoName)) {
                buffer.append(Utils.getFinalContent(finalEntities.get(realPojoName)));
                continue;
            }
            if (entityAnnotations != null) {
                buffer.append(entityAnnotations.getEntityAnnotationsDefinitions(realPojoName, serializer, true,
                        entityAnnotations.isNonStandardPojoAnnotations(realPojoName)));
                buffer.append(entityAnnotations.getConstructorAnnotationsDefinitions(realPojoName, serializer, true));
                buffer.append(entityAnnotations.getStaticAnnotationsDefinitions(realPojoName, serializer, true));
                buffer.append(entityAnnotations.getConflictAnnotationsDefinitions(realPojoName, serializer, true));
            }
            addPojoAnnotations(realPojoName, buffer);
            {
                bufferMeta = new StringBuilder();
                if (isSerializable || serializables.contains(pojo))
                    bufferMeta.append(nlindent()).append("#Serializable(1)");
            }

            Set<String> toStr = new LinkedHashSet<String>();
            {
                bufferPartial = new StringBuilder();
                printComment(bufferPartial, comments.get(pojo), INDENT);
                bufferPartial.append(NLINDENT);
                if (makeItFinal)
                    bufferPartial.append("final ");
                bufferPartial.append("#Pojo ");
                bufferPartial.append(realPojoName);
                if (pojoExtends.containsKey(pojo))
                    bufferPartial.append(" extends ").append(tableToCamelCase(pojoExtends.get(pojo)));
                bufferPartial.append(" {");
                for (Map.Entry<String, PojoAttribute> pentry : functions.get(pojo).entrySet()) {
                    if (FAKE_FUN_PROC_COLUMN_NAME.equals(pentry.getKey()))
                        continue;
                    if (ignoreColumns.containsKey(pojo) && ignoreColumns.get(pojo).contains(pentry.getKey()))
                        continue;
                    PojoAttribute attribute = pentry.getValue();
                    String name = (columnNames.containsKey(pojo)) ? columnNames.get(pojo).get(pentry.getKey()) : null;
                    if (name == null)
                        name = attribute.getName();
                    else
                        name = columnToCamelCase(name);
                    printComment(bufferPartial, attribute.getComment(), INDENT, INDENT);
                    if (entityAnnotations != null) {
                        bufferPartial.append(entityAnnotations.getAttributeAnnotationsDefinitions(realPojoName, name,
                                serializer, true, entityAnnotations.isNonStandardPojoAnnotations(realPojoName, name)));
                        bufferPartial.append(entityAnnotations.getGetterAnnotationsDefinitions(realPojoName, name,
                                serializer, true));
                        bufferPartial.append(entityAnnotations.getSetterAnnotationsDefinitions(realPojoName, name,
                                serializer, true));
                    }
                    if (doGenerateValidationAnnotations) {
                        if ((requiredColumns.containsKey(pojo) && requiredColumns.get(pojo).contains(pentry.getKey()))
                                || (attribute.isRequired() && !attribute.isPrimaryKey())) {
                            if (!notRequiredColumns.containsKey(pojo)
                                    || !notRequiredColumns.get(pojo).contains(pentry.getKey()))
                                if (entityAnnotations == null || !entityAnnotations
                                        .hasAttributeAnnotationsDefinitions(realPojoName, name, ANNOTATION_NOT_NULL)) {
                                    bufferPartial.append(NLINDENT).append(INDENT).append("@NotNull");
                                }
                        }
                        if (attribute.getDependencyClassName() == null && !attribute.isPrimitive()) {
                            if (attribute.isString() && attribute.getSize() > 0) {
                                if (entityAnnotations == null || !entityAnnotations
                                        .hasAttributeAnnotationsDefinitions(realPojoName, name, ANNOTATION_SIZE)) {
                                    bufferPartial.append(NLINDENT).append(INDENT).append("@Size(max = ")
                                            .append(attribute.getSize()).append(")");
                                }
                            }
                        }
                    }
                    addColumnAnnotations(realPojoName, name, bufferPartial);
                    if (!attribute.isVersion() && ((requiredColumns.containsKey(pojo)
                            && requiredColumns.get(pojo).contains(pentry.getKey()))
                            || (attribute.isRequired() && !attribute.isPrimaryKey()))) {
                        if (!notRequiredColumns.containsKey(pojo)
                                || !notRequiredColumns.get(pojo).contains(pentry.getKey()))
                            bufferPartial.append(nlindent2()).append("#Required");
                    }
                    bufferPartial.append(NLINDENT).append(INDENT).append("#Attr ");
                    if (attribute.getDependencyClassName() != null) {
                        bufferPartial.append(attribute.getDependencyClassName());
                        toStr.add(name);
                    } else if (attribute.isPrimitive()) {
                        bufferPartial.append(attribute.getClassName());
                        toStr.add(name);
                    } else {
                        bufferPartial.append(attribute.getClassName());
                        if (!attribute.getClassName().startsWith(COLLECTION_LIST))
                            toStr.add(name);
                    }
                    bufferPartial.append(' ').append(name);
                    if (debug.isDebug())
                        bufferPartial.append(" // ").append(attribute.getCompleteSqlType());
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
            if (finalEntitiesFeatures.containsKey(realPojoName)) {
                buffer.append("\n");
                for (Entry<String, String> e : finalEntitiesFeatures.get(realPojoName).entrySet()) {
                    buffer.append(Utils.getFinalContent(e.getValue()));
                }
                buffer.append(INDENT).append("}\n");
            } else
                buffer.append(NLINDENT).append("}\n");
        }
        return buffer.toString();
    }

    protected PojoAttribute getPrimaryKey(String pojo) {
        for (Map.Entry<String, PojoAttribute> pentry : pojos.get(pojo).entrySet()) {
            PojoAttribute attr = pentry.getValue();
            if (attr == null)
                continue;
            if (attr.isPrimaryKey()) {
                return attr;
            }
        }
        return null;
    }

    protected void addPojoAnnotations(String pojoName, StringBuilder buffer) {

        if (pojoAnnotations == null)
            return;

        for (Entry<String, Integer> e : pojoAnnotations.getAnnotations().entrySet()) {
            PojoDefinition annotation = modelAnnotations.get(e.getKey());
            if (annotation != null) {
                boolean doit = false;
                if ((e.getValue() & PojoAnnotations.IS_CONFLICT) != 0
                        && doAddPojoAnnotations(pojoAnnotations, pojoName, e.getKey(), PojoAnnotations.IS_CONFLICT)) {
                    buffer.append(NLINDENT).append("#Conflict");
                    doit = true;
                }
                if ((e.getValue() & PojoAnnotations.IS_CONSTRUCTOR) != 0 && doAddPojoAnnotations(pojoAnnotations,
                        pojoName, e.getKey(), PojoAnnotations.IS_CONSTRUCTOR)) {
                    buffer.append(NLINDENT).append("#Constructor");
                    doit = true;
                }
                if ((e.getValue() & PojoAnnotations.IS_STATIC) != 0
                        && doAddPojoAnnotations(pojoAnnotations, pojoName, e.getKey(), PojoAnnotations.IS_STATIC)) {
                    buffer.append(NLINDENT).append("#Static");
                    doit = true;
                }
                if ((e.getValue() & PojoAnnotations.IS_STANDARD) != 0
                        && doAddPojoAnnotations(pojoAnnotations, pojoName, e.getKey(), PojoAnnotations.IS_STANDARD)) {
                    buffer.append(NLINDENT).append("#Standard");
                    doit = true;
                }
                if (doit)
                    buffer.append(NLINDENT).append("@").append(annotation.getQualifiedName());
            }
        }
    }

    protected boolean doAddPojoAnnotations(PojoAnnotations pa, String pojoName, String name, Integer type) {
        boolean doit = false;
        if (pa.getDbTables(name + type) != null && !pa.getDbTables(name + type).isEmpty()) {
            if (pa.getDbTables(name + type).contains(pojoName))
                doit = true;
        } else if (pa.getDbNotTables(name + type) != null && !pa.getDbNotTables(name + type).isEmpty()) {
            if (!pa.getDbNotTables(name + type).contains(pojoName))
                doit = true;
        } else
            doit = true;
        return doit;
    }

    protected void addColumnAnnotations(String pojoName, String attrName, StringBuilder buffer) {

        ColumnAnnotations ca = columnAnnotations.get(attrName);
        if (ca == null)
            ca = columnAnnotations2.get(attrName);
        if (ca == null)
            return;

        for (Entry<String, Integer> e : ca.getAnnotations().entrySet()) {
            PojoDefinition annotation = modelAnnotations.get(e.getKey());
            if (annotation != null) {
                boolean doit = false;
                if ((e.getValue() & ColumnAnnotations.IS_ATTRIBUTE) != 0
                        && doAddColumnAnnotations(ca, pojoName, e.getKey(), ColumnAnnotations.IS_ATTRIBUTE)) {
                    buffer.append(NLINDENT).append(INDENT).append("#Attribute");
                    doit = true;
                }
                if ((e.getValue() & ColumnAnnotations.IS_GETTER) != 0
                        && doAddColumnAnnotations(ca, pojoName, e.getKey(), ColumnAnnotations.IS_GETTER)) {
                    buffer.append(NLINDENT).append(INDENT).append("#Getter");
                    doit = true;
                }
                if ((e.getValue() & ColumnAnnotations.IS_SETTER) != 0
                        && doAddColumnAnnotations(ca, pojoName, e.getKey(), ColumnAnnotations.IS_SETTER)) {
                    buffer.append(NLINDENT).append(INDENT).append("#Setter");
                    doit = true;
                }
                if (doit)
                    buffer.append(NLINDENT).append(INDENT).append("@").append(annotation.getQualifiedName());
            }
        }
    }

    protected boolean doAddColumnAnnotations(ColumnAnnotations ca, String pojoName, String name, Integer type) {
        boolean doit = false;
        if (ca.getDbTables(name + type) != null && !ca.getDbTables(name + type).isEmpty()) {
            if (ca.getDbTables(name + type).contains(pojoName))
                doit = true;
        } else if (ca.getDbNotTables(name + type) != null && !ca.getDbNotTables(name + type).isEmpty()) {
            if (!ca.getDbNotTables(name + type).contains(pojoName))
                doit = true;
        } else
            doit = true;
        return doit;
    }

    public static String generatePojo(Artifacts artifacts, Package packagex, ISerializer serializer,
            DbResolver dbResolver, IScopeProvider scopeProvider, ModelProperty modelProperty, Stats stats) {
        if (artifacts == null || !dbResolver.isResolveDb(artifacts))
            return null;
        if (serializer == null)
            serializer = ((XtextResource) packagex.eResource()).getSerializer();

        Set<String> imports = (packagex != null) ? Utils.getImports(packagex, serializer) : null;
        Map<String, String> finalEntities = new HashMap<String, String>();
        Map<String, Map<String, String>> finalFeatures = new HashMap<String, Map<String, String>>();
        Annotations annotations = new Annotations(INDENT, NL);
        if (packagex != null) {
            for (AbstractEntity ape : packagex.getElements()) {
                if (ape instanceof AnnotatedEntity && ((AnnotatedEntity) ape).getEntity() instanceof PojoEntity) {
                    PojoEntity pojo = (PojoEntity) ((AnnotatedEntity) ape).getEntity();
                    Annotations.grabAnnotations((AnnotatedEntity) ape, annotations);
                    if (pojo.isFinal()) {
                        // ISerializer serializer = ((XtextResource) pojo.eResource()).getSerializer();
                        finalEntities.put(pojo.getName(), serializer.serialize(pojo));
                    } else {
                        Map<String, String> map;
                        finalFeatures.put(pojo.getName(), map = new LinkedHashMap<String, String>());
                        for (AnnotatedFeature feature : pojo.getFeatures()) {
                            if (feature.getFeature().isFinal())
                                map.put(feature.getFeature().getName(), serializer.serialize(feature));
                        }
                    }
                } else if (ape instanceof AnnotatedEntity
                        && ((AnnotatedEntity) ape).getEntity() instanceof EnumEntity) {
                    EnumEntity pojo = (EnumEntity) ((AnnotatedEntity) ape).getEntity();
                    Annotations.grabAnnotations((AnnotatedEntity) ape, annotations);
                    if (pojo.isFinal()) {
                        // ISerializer serializer = ((XtextResource) pojo.eResource()).getSerializer();
                        finalEntities.put(pojo.getName(), serializer.serialize(pojo));
                    } else {
                        Map<String, String> map;
                        finalFeatures.put(pojo.getName(), map = new LinkedHashMap<String, String>());
                        // for (PojoProcedure proc : pojo.getProcedures()) {
                        // // if (proc.isFinal())
                        // map.put(proc.getName(), serializer.serialize(proc));
                        // }
                    }
                }
            }
        }

        // List<String> tables = dbResolver.getTables(artifacts);
        List<String> dbSequences = dbResolver.getSequences(artifacts);
        DbType dbType = Utils.getDbType(dbResolver, artifacts);
        TablePojoGenerator generator = new TablePojoGenerator(modelProperty, artifacts, finalEntities, finalFeatures,
                annotations, imports, dbSequences, dbType);
        try {
            if (generator.addDefinitions(dbResolver, scopeProvider, stats))
                return generator.getPojoDefinitions(modelProperty, artifacts, serializer);
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
