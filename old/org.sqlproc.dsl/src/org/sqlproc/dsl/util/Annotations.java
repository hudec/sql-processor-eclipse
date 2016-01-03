package org.sqlproc.dsl.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sqlproc.dsl.processorDsl.AnnotatedEntity;
import org.sqlproc.dsl.processorDsl.Annotation;
import org.sqlproc.dsl.processorDsl.AnnotationDirective;
import org.sqlproc.dsl.processorDsl.AnnotationDirectiveAttribute;
import org.sqlproc.dsl.processorDsl.AnnotationDirectiveConflict;
import org.sqlproc.dsl.processorDsl.AnnotationDirectiveConstructor;
import org.sqlproc.dsl.processorDsl.AnnotationDirectiveGetter;
import org.sqlproc.dsl.processorDsl.AnnotationDirectiveSetter;
import org.sqlproc.dsl.processorDsl.AnnotationDirectiveStandard;
import org.sqlproc.dsl.processorDsl.AnnotationDirectiveStatic;
import org.sqlproc.dsl.processorDsl.AnnotationProperty;
import org.sqlproc.dsl.processorDsl.PojoAnnotatedProperty;
import org.sqlproc.dsl.processorDsl.PojoEntity;

public class Annotations {
    protected static final String INDENT = "  ";
    protected static final String NLINDENT = "\n  ";
    protected static final String NL = "\n";

    Map<String, List<Annotation>> entityAnnotations = new HashMap<String, List<Annotation>>();
    Map<String, List<Annotation>> constructorAnnotations = new HashMap<String, List<Annotation>>();
    Map<String, List<Annotation>> staticAnnotations = new HashMap<String, List<Annotation>>();
    Map<String, List<Annotation>> conflictAnnotations = new HashMap<String, List<Annotation>>();
    Map<String, Map<String, List<Annotation>>> setterAnnotations = new HashMap<String, Map<String, List<Annotation>>>();
    Map<String, Map<String, List<Annotation>>> getterAnnotations = new HashMap<String, Map<String, List<Annotation>>>();
    Map<String, Map<String, List<Annotation>>> attributeAnnotations = new HashMap<String, Map<String, List<Annotation>>>();

    public void addAnnotation(String pojoName, Annotation annotation, Map<String, List<Annotation>> annotations) {
        List<Annotation> list;
        if ((list = annotations.get(pojoName)) == null) {
            list = new ArrayList<Annotation>();
            annotations.put(pojoName, list);
        }
        list.add(annotation);
    }

    public void addAttributeAnnotation(String pojoName, String featureName, Annotation annotation,
            Map<String, Map<String, List<Annotation>>> annotations) {
        List<Annotation> list;
        Map<String, List<Annotation>> map;
        if ((map = annotations.get(pojoName)) == null) {
            map = new HashMap<String, List<Annotation>>();
            annotations.put(pojoName, map);
        }
        if ((list = map.get(featureName)) == null) {
            list = new ArrayList<Annotation>();
            map.put(featureName, list);
        }
        list.add(annotation);
    }

    public StringBuilder getEntityAnnotationsDefinitions(String pojoName, boolean simpleNames,
            boolean nonStandardAnnotations) {
        StringBuilder sb = new StringBuilder();
        if (!entityAnnotations.containsKey(pojoName))
            return sb;
        for (Annotation a : entityAnnotations.get(pojoName)) {
            getAnnotationDefinition(sb, a, ((nonStandardAnnotations) ? NLINDENT + "#Standard" : "") + NLINDENT + "@",
                    simpleNames);
        }
        return sb;
    }

    public StringBuilder getConstructorAnnotationsDefinitions(String pojoName, boolean simpleNames) {
        StringBuilder sb = new StringBuilder();
        if (!constructorAnnotations.containsKey(pojoName))
            return sb;
        for (Annotation a : constructorAnnotations.get(pojoName)) {
            getAnnotationDefinition(sb, a, NLINDENT + "#Constructor" + NLINDENT + "@", simpleNames);
        }
        return sb;
    }

    public StringBuilder getStaticAnnotationsDefinitions(String pojoName, boolean simpleNames) {
        StringBuilder sb = new StringBuilder();
        if (!staticAnnotations.containsKey(pojoName))
            return sb;
        for (Annotation a : staticAnnotations.get(pojoName)) {
            getAnnotationDefinition(sb, a, NLINDENT + "#Static" + NLINDENT + "@", simpleNames);
        }
        return sb;
    }

    public StringBuilder getConflictAnnotationsDefinitions(String pojoName, boolean simpleNames) {
        StringBuilder sb = new StringBuilder();
        if (!conflictAnnotations.containsKey(pojoName))
            return sb;
        for (Annotation a : conflictAnnotations.get(pojoName)) {
            getAnnotationDefinition(sb, a, NLINDENT + "#Conflict" + NLINDENT + "@", simpleNames);
        }
        return sb;
    }

    public boolean isNonStandardPojoAnnotations(String pojoName) {
        return (constructorAnnotations.containsKey(pojoName) && !constructorAnnotations.get(pojoName).isEmpty())
                || (staticAnnotations.containsKey(pojoName) && !staticAnnotations.get(pojoName).isEmpty())
                || (conflictAnnotations.containsKey(pojoName) && !conflictAnnotations.get(pojoName).isEmpty());
    }

    public StringBuilder getGetterAnnotationsDefinitions(String pojoName, String featureName, boolean simpleNames) {
        StringBuilder sb = new StringBuilder();
        if (!getterAnnotations.containsKey(pojoName) || !getterAnnotations.get(pojoName).containsKey(featureName))
            return sb;
        for (Annotation a : getterAnnotations.get(pojoName).get(featureName)) {
            getAnnotationDefinition(sb, a, NLINDENT + INDENT + "#Getter" + NLINDENT + INDENT + "@", simpleNames);
        }
        return sb;
    }

    public StringBuilder getSetterAnnotationsDefinitions(String pojoName, String featureName, boolean simpleNames) {
        StringBuilder sb = new StringBuilder();
        if (!setterAnnotations.containsKey(pojoName) || !setterAnnotations.get(pojoName).containsKey(featureName))
            return sb;
        for (Annotation a : setterAnnotations.get(pojoName).get(featureName)) {
            getAnnotationDefinition(sb, a, NLINDENT + INDENT + "#Setter" + NLINDENT + INDENT + "@", simpleNames);
        }
        return sb;
    }

    public StringBuilder getAttributeAnnotationsDefinitions(String pojoName, String featureName, boolean simpleNames,
            boolean nonStandardAnnotations) {
        StringBuilder sb = new StringBuilder();
        if (!attributeAnnotations.containsKey(pojoName) || !attributeAnnotations.get(pojoName).containsKey(featureName))
            return sb;
        for (Annotation a : attributeAnnotations.get(pojoName).get(featureName)) {
            getAnnotationDefinition(sb, a, ((nonStandardAnnotations) ? NLINDENT + INDENT + "#Attribute" : "")
                    + NLINDENT + INDENT + "@", simpleNames);
        }
        return sb;
    }

    public boolean isNonStandardPojoAnnotations(String pojoName, String featureName) {
        return (getterAnnotations.containsKey(pojoName) && getterAnnotations.get(pojoName).containsKey(featureName) && !getterAnnotations
                .get(pojoName).get(featureName).isEmpty())
                || (setterAnnotations.containsKey(pojoName) && setterAnnotations.get(pojoName).containsKey(featureName) && !setterAnnotations
                        .get(pojoName).get(featureName).isEmpty());
    }

    public boolean hasAttributeAnnotationsDefinitions(String pojoName, String featureName, String annotationName) {
        if (attributeAnnotations == null)
            return false;
        if (!attributeAnnotations.containsKey(pojoName) || !attributeAnnotations.get(pojoName).containsKey(featureName))
            return false;
        for (Annotation a : attributeAnnotations.get(pojoName).get(featureName)) {
            String aName = a.getType().getQualifiedName();
            if (annotationName.equals(aName))
                return true;
        }
        return false;
    }

    public void getAnnotationDefinition(StringBuilder sb, Annotation a, String prefix, boolean simpleNames) {
        sb.append(prefix).append((simpleNames) ? a.getType().getSimpleName() : a.getType().getQualifiedName());
        if (a.getFeatures() != null && !a.getFeatures().isEmpty()) {
            sb.append("(");
            boolean first = true;
            for (AnnotationProperty ap : a.getFeatures()) {
                if (first)
                    first = false;
                else
                    sb.append(",");
                getAnnotationPropertyDefinition(sb, ap, simpleNames);
            }
            sb.append(")");
        }
    }

    public void getAnnotationPropertyDefinition(StringBuilder sb, AnnotationProperty ap, boolean simpleNames) {
        sb.append(ap.getName());
        sb.append(" = ");
        if (ap.getValue() != null)
            sb.append(ap.getValue());
        else if (ap.getNumber() != null)
            sb.append(ap.getNumber());
        else
            sb.append(ap.getConstant());
        if (ap.getType() != null)
            sb.append(" :").append((simpleNames) ? ap.getType().getSimpleName() : ap.getType().getQualifiedName());
        else if (ap.getRef() != null)
            sb.append(" ").append(ap.getRef().getName());
    }

    public Set<String> getImports() {
        Set<String> imports = new HashSet<String>();
        for (List<Annotation> al : entityAnnotations.values())
            getImports(imports, al);
        for (List<Annotation> al : constructorAnnotations.values())
            getImports(imports, al);
        for (List<Annotation> al : staticAnnotations.values())
            getImports(imports, al);
        for (List<Annotation> al : conflictAnnotations.values())
            getImports(imports, al);
        for (Map<String, List<Annotation>> am : attributeAnnotations.values()) {
            for (List<Annotation> al : am.values()) {
                getImports(imports, al);
            }
        }
        for (Map<String, List<Annotation>> am : getterAnnotations.values()) {
            for (List<Annotation> al : am.values()) {
                getImports(imports, al);
            }
        }
        for (Map<String, List<Annotation>> am : setterAnnotations.values()) {
            for (List<Annotation> al : am.values()) {
                getImports(imports, al);
            }
        }
        return imports;
    }

    public void getImports(Set<String> imports, List<Annotation> al) {
        for (Annotation a : al) {
            if (a.getType() != null)
                imports.add(a.getType().getQualifiedName());
            for (AnnotationProperty ap : a.getFeatures()) {
                if (ap.getType() != null)
                    imports.add(ap.getType().getQualifiedName());
            }
        }
    }

    public static void grabAnnotations(AnnotatedEntity apojo, PojoEntity pojo, Annotations as) {
        String pojoName = pojo.getName();
        if (apojo.getAnnotations() != null && !apojo.getAnnotations().isEmpty()) {
            for (Annotation an : apojo.getAnnotations()) {
                if (an.getDirectives() == null || an.getDirectives().isEmpty()) {
                    as.addAnnotation(pojoName, an, as.entityAnnotations);
                } else {
                    for (AnnotationDirective dir : an.getDirectives()) {
                        if (dir instanceof AnnotationDirectiveConflict) {
                            as.addAnnotation(pojoName, an, as.conflictAnnotations);
                        } else if (dir instanceof AnnotationDirectiveConstructor) {
                            as.addAnnotation(pojoName, an, as.constructorAnnotations);
                        } else if (dir instanceof AnnotationDirectiveStandard) {
                            as.addAnnotation(pojoName, an, as.entityAnnotations);
                        } else if (dir instanceof AnnotationDirectiveStatic) {
                            as.addAnnotation(pojoName, an, as.staticAnnotations);
                        }
                    }
                }
            }
        }
        for (PojoAnnotatedProperty feature : pojo.getFeatures()) {
            if (feature.getFeature() == null)
                continue;
            if (feature.getAnnotations() != null && !feature.getAnnotations().isEmpty()) {
                for (Annotation an : feature.getAnnotations()) {
                    if (an.getDirectives() == null || an.getDirectives().isEmpty()) {
                        as.addAttributeAnnotation(pojoName, feature.getFeature().getName(), an, as.attributeAnnotations);
                    } else {
                        for (AnnotationDirective dir : an.getDirectives()) {
                            if (dir instanceof AnnotationDirectiveSetter) {
                                as.addAttributeAnnotation(pojoName, feature.getFeature().getName(), an,
                                        as.setterAnnotations);
                            } else if (dir instanceof AnnotationDirectiveGetter) {
                                as.addAttributeAnnotation(pojoName, feature.getFeature().getName(), an,
                                        as.getterAnnotations);
                            } else if (dir instanceof AnnotationDirectiveAttribute) {
                                as.addAttributeAnnotation(pojoName, feature.getFeature().getName(), an,
                                        as.attributeAnnotations);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Annotations [entityAnnotations=" + entityAnnotations + ", constructorAnnotations="
                + constructorAnnotations + ", staticAnnotations=" + staticAnnotations + ", conflictAnnotations="
                + conflictAnnotations + ", setterAnnotations=" + setterAnnotations + ", getterAnnotations="
                + getterAnnotations + ", attributeAnnotations=" + attributeAnnotations + "]";
    }
}
