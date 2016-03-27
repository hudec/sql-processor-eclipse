package org.sqlproc.model.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation;
import org.sqlproc.model.processorModel.AnnotatedEntity;
import org.sqlproc.model.processorModel.Annotation;
import org.sqlproc.model.processorModel.AnnotationDirective;
import org.sqlproc.model.processorModel.AnnotationDirectiveAttribute;
import org.sqlproc.model.processorModel.AnnotationDirectiveConflict;
import org.sqlproc.model.processorModel.AnnotationDirectiveConstructor;
import org.sqlproc.model.processorModel.AnnotationDirectiveGetter;
import org.sqlproc.model.processorModel.AnnotationDirectiveSetter;
import org.sqlproc.model.processorModel.AnnotationDirectiveStandard;
import org.sqlproc.model.processorModel.AnnotationDirectiveStatic;
import org.sqlproc.model.processorModel.DaoEntity;
import org.sqlproc.model.processorModel.Entity;
import org.sqlproc.model.processorModel.EnumEntity;
import org.sqlproc.model.processorModel.PojoAttribute;
import org.sqlproc.model.processorModel.PojoEntity;
import org.sqlproc.model.processorModel.PojoProcedure;
import org.sqlproc.plugin.lib.util.CommonUtils;

public class Annotations {

    protected String INDENT = "  ";
    protected String NL = "\n";
    protected String NLINDENT = NL + INDENT;

    public Annotations(String indent, String nl) {
        INDENT = indent;
        NLINDENT = nl + indent;
        NL = nl;
    }

    Map<String, List<XAnnotation>> entityAnnotations = new HashMap<String, List<XAnnotation>>();
    Map<String, List<XAnnotation>> constructorAnnotations = new HashMap<String, List<XAnnotation>>();
    Map<String, List<XAnnotation>> staticAnnotations = new HashMap<String, List<XAnnotation>>();
    Map<String, List<XAnnotation>> conflictAnnotations = new HashMap<String, List<XAnnotation>>();
    Map<String, Map<String, List<XAnnotation>>> setterAnnotations = new HashMap<String, Map<String, List<XAnnotation>>>();
    Map<String, Map<String, List<XAnnotation>>> getterAnnotations = new HashMap<String, Map<String, List<XAnnotation>>>();
    Map<String, Map<String, List<XAnnotation>>> attributeAnnotations = new HashMap<String, Map<String, List<XAnnotation>>>();
    Map<String, Map<String, List<XAnnotation>>> procedureAnnotations = new HashMap<String, Map<String, List<XAnnotation>>>();

    public void addAnnotation(String pojoName, XAnnotation annotation, Map<String, List<XAnnotation>> annotations) {
        List<XAnnotation> list;
        if ((list = annotations.get(pojoName)) == null) {
            list = new ArrayList<XAnnotation>();
            annotations.put(pojoName, list);
        }
        list.add(annotation);
    }

    public void addAnnotation(String pojoName, String featureName, XAnnotation annotation,
            Map<String, Map<String, List<XAnnotation>>> annotations) {
        List<XAnnotation> list;
        Map<String, List<XAnnotation>> map;
        if ((map = annotations.get(pojoName)) == null) {
            map = new HashMap<String, List<XAnnotation>>();
            annotations.put(pojoName, map);
        }
        if ((list = map.get(featureName)) == null) {
            list = new ArrayList<XAnnotation>();
            map.put(featureName, list);
        }
        list.add(annotation);
    }

    public StringBuilder getAnnotationsDefinitions(String pojoName, ISerializer serializer, boolean simpleNames,
            boolean nonStandardAnnotations, Map<String, List<XAnnotation>> annotations, String directive) {
        StringBuilder sb = new StringBuilder();
        if (!annotations.containsKey(pojoName))
            return sb;
        for (XAnnotation a : annotations.get(pojoName)) {
            if (nonStandardAnnotations)
                sb.append(NLINDENT).append(directive);
            sb.append(NLINDENT).append("final ").append(CommonUtils.removeBlankLines(serializer.serialize(a)));
        }
        return sb;
    }

    public StringBuilder getEntityAnnotationsDefinitions(String pojoName, ISerializer serializer, boolean simpleNames,
            boolean nonStandardAnnotations) {
        return getAnnotationsDefinitions(pojoName, serializer, simpleNames, nonStandardAnnotations, entityAnnotations,
                "#Standard");
    }

    public StringBuilder getConstructorAnnotationsDefinitions(String pojoName, ISerializer serializer,
            boolean simpleNames) {
        return getAnnotationsDefinitions(pojoName, serializer, simpleNames, true, constructorAnnotations,
                "#Constructor");
    }

    public StringBuilder getStaticAnnotationsDefinitions(String pojoName, ISerializer serializer, boolean simpleNames) {
        return getAnnotationsDefinitions(pojoName, serializer, simpleNames, true, staticAnnotations, "#Static");
    }

    public StringBuilder getConflictAnnotationsDefinitions(String pojoName, ISerializer serializer, boolean simpleNames) {
        return getAnnotationsDefinitions(pojoName, serializer, simpleNames, true, conflictAnnotations, "#Conflict");
    }

    public boolean isNonStandardPojoAnnotations(String pojoName) {
        return (constructorAnnotations.containsKey(pojoName) && !constructorAnnotations.get(pojoName).isEmpty())
                || (staticAnnotations.containsKey(pojoName) && !staticAnnotations.get(pojoName).isEmpty())
                || (conflictAnnotations.containsKey(pojoName) && !conflictAnnotations.get(pojoName).isEmpty());
    }

    public StringBuilder getAnnotationsDefinitions(String pojoName, String featureName, ISerializer serializer,
            boolean simpleNames, Map<String, Map<String, List<XAnnotation>>> annotations, String directive) {
        StringBuilder sb = new StringBuilder();
        if (!annotations.containsKey(pojoName) || !annotations.get(pojoName).containsKey(featureName))
            return sb;
        for (XAnnotation a : annotations.get(pojoName).get(featureName)) {
            if (directive != null)
                sb.append(NLINDENT).append(INDENT).append(directive);
            sb.append(NLINDENT).append(INDENT).append("final ")
                    .append(CommonUtils.removeBlankLines(serializer.serialize(a)));
        }
        return sb;
    }

    public StringBuilder getGetterAnnotationsDefinitions(String pojoName, String featureName, ISerializer serializer,
            boolean simpleNames) {
        return getAnnotationsDefinitions(pojoName, featureName, serializer, simpleNames, getterAnnotations, "#Getter");
    }

    public StringBuilder getSetterAnnotationsDefinitions(String pojoName, String featureName, ISerializer serializer,
            boolean simpleNames) {
        return getAnnotationsDefinitions(pojoName, featureName, serializer, simpleNames, setterAnnotations, "#Setter");
    }

    public StringBuilder getAttributeAnnotationsDefinitions(String pojoName, String featureName,
            ISerializer serializer, boolean simpleNames, boolean nonStandardAnnotations) {
        return getAnnotationsDefinitions(pojoName, featureName, serializer, simpleNames, attributeAnnotations,
                "#Attribute");
    }

    public StringBuilder getProcedureAnnotationsDefinitions(String pojoName, String featureName,
            ISerializer serializer, boolean simpleNames, boolean nonStandardAnnotations) {
        return getAnnotationsDefinitions(pojoName, featureName, serializer, simpleNames, procedureAnnotations, null);
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
        for (XAnnotation a : attributeAnnotations.get(pojoName).get(featureName)) {
            String aName = a.getAnnotationType().getQualifiedName();
            if (annotationName.equals(aName))
                return true;
        }
        return false;
    }

    // public Set<String> getImports() {
    // Set<String> imports = new HashSet<String>();
    // for (List<Annotation> al : entityAnnotations.values())
    // getImports(imports, al);
    // for (List<Annotation> al : constructorAnnotations.values())
    // getImports(imports, al);
    // for (List<Annotation> al : staticAnnotations.values())
    // getImports(imports, al);
    // for (List<Annotation> al : conflictAnnotations.values())
    // getImports(imports, al);
    // for (Map<String, List<Annotation>> am : attributeAnnotations.values()) {
    // for (List<Annotation> al : am.values()) {
    // getImports(imports, al);
    // }
    // }
    // for (Map<String, List<Annotation>> am : getterAnnotations.values()) {
    // for (List<Annotation> al : am.values()) {
    // getImports(imports, al);
    // }
    // }
    // for (Map<String, List<Annotation>> am : setterAnnotations.values()) {
    // for (List<Annotation> al : am.values()) {
    // getImports(imports, al);
    // }
    // }
    // return imports;
    // }
    //
    // public void getImports(Set<String> imports, List<Annotation> al) {
    // for (Annotation a : al) {
    // if (a.getType() != null)
    // imports.add(a.getType().getQualifiedName());
    // for (AnnotationProperty ap : a.getFeatures()) {
    // if (ap.getType() != null)
    // imports.add(ap.getType().getQualifiedName());
    // }
    // }
    // }

    public static void grabAnnotations(AnnotatedEntity apojo, Annotations as) {
        Entity entity = apojo.getEntity();
        if (entity == null)
            return;
        PojoEntity pojo = (entity instanceof PojoEntity) ? (PojoEntity) entity : null;
        EnumEntity epojo = (entity instanceof EnumEntity) ? (EnumEntity) entity : null;
        DaoEntity dao = (entity instanceof DaoEntity) ? (DaoEntity) entity : null;
        String name = (pojo != null) ? pojo.getName() : (epojo != null) ? epojo.getName() : (dao != null) ? dao
                .getName() : null;
        if (name == null)
            return;

        if (apojo.getAnnotations() != null && !apojo.getAnnotations().isEmpty()) {
            for (Annotation an : apojo.getAnnotations()) {
                if (!an.isFinal())
                    continue;
                if (an.getDirectives() == null || an.getDirectives().isEmpty()) {
                    as.addAnnotation(name, an.getAnnotation(), as.entityAnnotations);
                } else {
                    for (AnnotationDirective dir : an.getDirectives()) {
                        if (dir instanceof AnnotationDirectiveConflict) {
                            as.addAnnotation(name, an.getAnnotation(), as.conflictAnnotations);
                        } else if (dir instanceof AnnotationDirectiveConstructor) {
                            as.addAnnotation(name, an.getAnnotation(), as.constructorAnnotations);
                        } else if (dir instanceof AnnotationDirectiveStandard) {
                            as.addAnnotation(name, an.getAnnotation(), as.entityAnnotations);
                        } else if (dir instanceof AnnotationDirectiveStatic) {
                            as.addAnnotation(name, an.getAnnotation(), as.staticAnnotations);
                        }
                    }
                }
            }
        }
        if (pojo != null) {
            for (PojoAttribute feature : pojo.getAttributes()) {
                if (feature.getAnnotations() != null && !feature.getAnnotations().isEmpty()) {
                    for (Annotation an : feature.getAnnotations()) {
                        if (!an.isFinal())
                            continue;
                        if (an.getDirectives() == null || an.getDirectives().isEmpty()) {
                            as.addAnnotation(name, feature.getName(), an.getAnnotation(), as.attributeAnnotations);
                        } else {
                            for (AnnotationDirective dir : an.getDirectives()) {
                                if (dir instanceof AnnotationDirectiveSetter) {
                                    as.addAnnotation(name, feature.getName(), an.getAnnotation(), as.setterAnnotations);
                                } else if (dir instanceof AnnotationDirectiveGetter) {
                                    as.addAnnotation(name, feature.getName(), an.getAnnotation(), as.getterAnnotations);
                                } else if (dir instanceof AnnotationDirectiveAttribute) {
                                    as.addAnnotation(name, feature.getName(), an.getAnnotation(),
                                            as.attributeAnnotations);
                                }
                            }
                        }
                    }
                }
            }
            for (PojoProcedure feature : pojo.getProcedures()) {
                if (feature.getAnnotations() != null && !feature.getAnnotations().isEmpty()) {
                    for (Annotation an : feature.getAnnotations()) {
                        if (!an.isFinal())
                            continue;
                        as.addAnnotation(name, feature.getName(), an.getAnnotation(), as.procedureAnnotations);
                    }
                }
            }
        }
        if (dao != null) {
            for (PojoAttribute feature : dao.getAttributes()) {
                if (feature.getAnnotations() != null && !feature.getAnnotations().isEmpty()) {
                    for (Annotation an : feature.getAnnotations()) {
                        if (!an.isFinal())
                            continue;
                        if (an.getDirectives() == null || an.getDirectives().isEmpty()) {
                            as.addAnnotation(name, feature.getName(), an.getAnnotation(), as.attributeAnnotations);
                        } else {
                            for (AnnotationDirective dir : an.getDirectives()) {
                                if (dir instanceof AnnotationDirectiveSetter) {
                                    as.addAnnotation(name, feature.getName(), an.getAnnotation(), as.setterAnnotations);
                                } else if (dir instanceof AnnotationDirectiveGetter) {
                                    as.addAnnotation(name, feature.getName(), an.getAnnotation(), as.getterAnnotations);
                                } else if (dir instanceof AnnotationDirectiveAttribute) {
                                    as.addAnnotation(name, feature.getName(), an.getAnnotation(),
                                            as.attributeAnnotations);
                                }
                            }
                        }
                    }
                }
            }
            for (PojoProcedure feature : dao.getProcedures()) {
                if (feature.getAnnotations() != null && !feature.getAnnotations().isEmpty()) {
                    for (Annotation an : feature.getAnnotations()) {
                        if (!an.isFinal())
                            continue;
                        as.addAnnotation(name, feature.getName(), an.getAnnotation(), as.procedureAnnotations);
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
