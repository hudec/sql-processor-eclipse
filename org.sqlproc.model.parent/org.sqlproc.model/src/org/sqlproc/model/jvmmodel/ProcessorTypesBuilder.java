package org.sqlproc.model.jvmmodel;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.lib.Procedures;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.typesystem.InferredTypeIndicator;
import org.eclipse.xtext.xtype.XtypeFactory;
import org.sqlproc.model.processorModel.PojoAttribute;

import com.google.inject.Inject;

public class ProcessorTypesBuilder extends JvmTypesBuilder {

    // private static Logger LOG = Logger.getLogger(ProcessorTypesBuilder.class);

    @Inject
    private TypeReferences references;

    @Inject
    private TypesFactory typesFactory;

    @Inject(optional = true)
    private XtypeFactory xtypesFactory = XtypeFactory.eINSTANCE;

    public JvmOperation toGetter(/* @Nullable */final EObject sourceElement, /* @Nullable */final String propertyName,
            /* @Nullable */final String fieldName, /* @Nullable */JvmTypeReference typeRef,
            /* @Nullable */final Set<String> procNames, /* @Nullable */Procedure1<? super JvmOperation> initializer) {
        if (sourceElement == null || propertyName == null || fieldName == null)
            return null;
        JvmOperation result = typesFactory.createJvmOperation();
        result.setVisibility(JvmVisibility.PUBLIC);
        String prefix = "get";
        if (typeRef != null && !typeRef.eIsProxy() && !InferredTypeIndicator.isInferred(typeRef)
                && typeRef.getType() != null && !typeRef.getType().eIsProxy()
                && "boolean".equals(typeRef.getType().getIdentifier())) {
            prefix = "is";
        }
        result.setSimpleName(prefix + _toFirstUpper(propertyName));
        result.setReturnType(cloneWithProxies(typeRef));
        setBody(result, new Procedures.Procedure1<ITreeAppendable>() {
            public void apply(/* @Nullable */ITreeAppendable p) {
                if (p != null) {
                    p = p.trace(sourceElement);
                    p.append("return this.");
                    p.append(fieldName);
                    p.append(";");
                }
            }
        });
        associate(sourceElement, result);
        return initializeSafely(result, initializer);
    }

    /* @Nullable */
    public JvmOperation toSetterExt(/* @Nullable */final EObject sourceElement, /* @Nullable */
            final String propertyName, /* @Nullable */final String fieldName,
            /* @Nullable */JvmTypeReference typeRef, /* @Nullable */
            JvmTypeReference typeEntityRef, /* @Nullable */final String updateColumn1,
            /* @Nullable */final PojoAttribute updateColumn2, /* @Nullable */final PojoAttribute createColumn1,
            /* @Nullable */final String createColumn2, /* @Nullable */final Set<String> procNames) {
        return toSetterExt(sourceElement, propertyName, fieldName, typeRef, typeEntityRef, updateColumn1, updateColumn2,
                createColumn1, createColumn2, procNames, null);
    }

    /* @Nullable */
    public JvmOperation toSetter(/* @Nullable */final EObject sourceElement, /* @Nullable */
            final String propertyName, /* @Nullable */final String fieldName,
            /* @Nullable */JvmTypeReference typeRef, /* @Nullable */
            JvmTypeReference typeEntityRef, /* @Nullable */final Set<String> procNames,
            /* @Nullable */Procedure1<? super JvmOperation> initializer) {
        if (sourceElement == null || propertyName == null || fieldName == null)
            return null;
        JvmOperation result = typesFactory.createJvmOperation();
        result.setVisibility(JvmVisibility.PUBLIC);
        result.setReturnType(references.getTypeForName(Void.TYPE, sourceElement));
        result.setSimpleName("set" + _toFirstUpper(propertyName));
        result.getParameters().add(toParameter(sourceElement, propertyName, cloneWithProxies(typeRef)));
        setBody(result, new Procedures.Procedure1<ITreeAppendable>() {
            public void apply(/* @Nullable */ITreeAppendable p) {
                if (p != null) {
                    p = p.trace(sourceElement);
                    p.append("this.").append(fieldName).append(" = ").append(propertyName).append(";");
                }
            }
        });
        associate(sourceElement, result);
        return initializeSafely(result, initializer);
    }

    /* @Nullable */
    public JvmOperation toSetterExt(/* @Nullable */final EObject sourceElement, /* @Nullable */
            final String propertyName, /* @Nullable */final String fieldName,
            /* @Nullable */JvmTypeReference typeRef, /* @Nullable */
            JvmTypeReference typeEntityRef, /* @Nullable */final String updateColumn1,
            /* @Nullable */final PojoAttribute updateColumn2, /* @Nullable */final PojoAttribute createColumn1,
            /* @Nullable */final String createColumn2, /* @Nullable */final Set<String> procNames,
            /* @Nullable */Procedure1<? super JvmOperation> initializer) {
        if (sourceElement == null || propertyName == null || fieldName == null)
            return null;
        JvmOperation result = typesFactory.createJvmOperation();
        result.setVisibility(JvmVisibility.PUBLIC);
        result.setReturnType(references.getTypeForName(Void.TYPE, sourceElement));
        result.setSimpleName("set" + _toFirstUpper(propertyName));
        result.getParameters().add(toParameter(sourceElement, propertyName, cloneWithProxies(typeRef)));
        setBody(result, new Procedures.Procedure1<ITreeAppendable>() {
            public void apply(/* @Nullable */ITreeAppendable p) {
                if (p != null) {
                    p = p.trace(sourceElement);
                    p.append("this.").append(fieldName).append(" = ").append(propertyName).append(";");
                    if (updateColumn1 != null && updateColumn2 != null) {
                        p.newLine().append("if (this.").append(fieldName).append(" != null)");
                        p.increaseIndentation();
                        p.newLine().append("this.").append(updateColumn2.getName()).append(" = this.").append(fieldName)
                                .append(".get").append(_toFirstUpper(updateColumn1)).append("();");
                        p.decreaseIndentation();
                    }
                    if (createColumn1 != null && createColumn2 != null) {
                        p.newLine().append("if (this.").append(createColumn1.getName()).append(" == null)");
                        p.increaseIndentation();
                        p.newLine().append("this.").append(createColumn1.getName()).append(" = new ")
                                .append(createColumn1.getType().getQualifiedName()).append("();");
                        p.decreaseIndentation();
                        p.newLine().append("this.").append(createColumn1.getName()).append(".set")
                                .append(_toFirstUpper(createColumn2)).append("(").append(propertyName).append(");");
                    }
                }
            }
        });
        associate(sourceElement, result);
        return initializeSafely(result, initializer);
    }

    /* @Nullable */
    public JvmOperation _toSetterExt(/* @Nullable */final EObject sourceElement, /* @Nullable */
            final String propertyName, /* @Nullable */final String fieldName,
            /* @Nullable */JvmTypeReference typeRef, /* @Nullable */
            JvmTypeReference typeEntityRef, /* @Nullable */final String updateColumn1,
            /* @Nullable */final PojoAttribute updateColumn2, /* @Nullable */final PojoAttribute createColumn1,
            /* @Nullable */final String createColumn2, /* @Nullable */final Set<String> procNames) {
        return _toSetterExt(sourceElement, propertyName, fieldName, typeRef, typeEntityRef, updateColumn1,
                updateColumn2, createColumn1, createColumn2, procNames, null);
    }

    /* @Nullable */
    public JvmOperation _toSetterExt(/* @Nullable */final EObject sourceElement, /* @Nullable */
            final String propertyName, /* @Nullable */final String fieldName,
            /* @Nullable */JvmTypeReference typeRef, /* @Nullable */
            JvmTypeReference typeEntityRef, /* @Nullable */final String updateColumn1,
            /* @Nullable */final PojoAttribute updateColumn2, /* @Nullable */final PojoAttribute createColumn1,
            /* @Nullable */final String createColumn2, /* @Nullable */final Set<String> procNames,
            /* @Nullable */Procedure1<? super JvmOperation> initializer) {
        if (sourceElement == null || propertyName == null || fieldName == null)
            return null;
        JvmOperation result = typesFactory.createJvmOperation();
        result.setVisibility(JvmVisibility.PUBLIC);
        result.setReturnType(cloneWithProxies(typeEntityRef));
        result.setSimpleName("_set" + _toFirstUpper(propertyName));
        result.getParameters().add(toParameter(sourceElement, propertyName, cloneWithProxies(typeRef)));
        setBody(result, new Procedures.Procedure1<ITreeAppendable>() {
            public void apply(/* @Nullable */ITreeAppendable p) {
                if (p != null) {
                    p = p.trace(sourceElement);
                    p.append("this.").append(fieldName).append(" = ").append(propertyName).append(";");
                    if (updateColumn1 != null && updateColumn2 != null) {
                        p.newLine().append("if (this.").append(fieldName).append(" != null)");
                        p.increaseIndentation();
                        p.newLine().append("this.").append(updateColumn2.getName()).append(" = this.").append(fieldName)
                                .append(".get").append(_toFirstUpper(updateColumn1)).append("();");
                        p.decreaseIndentation();
                    }
                    if (createColumn1 != null && createColumn2 != null) {
                        p.newLine().append("if (this.").append(createColumn1.getName()).append(" == null)");
                        p.increaseIndentation();
                        p.newLine().append("this.").append(createColumn1.getName()).append(" = new ")
                                .append(createColumn1.getType().getQualifiedName()).append("();");
                        p.decreaseIndentation();
                        p.newLine().append("this.").append(createColumn1.getName()).append(".set")
                                .append(_toFirstUpper(createColumn2)).append("(").append(propertyName).append(");");
                    }
                    p.newLine().append("return this;");
                }
            }
        });
        associate(sourceElement, result);
        return initializeSafely(result, initializer);
    }

    /* @Nullable */
    public JvmOperation _toSetter(/* @Nullable */final EObject sourceElement, /* @Nullable */final String propertyName,
            /* @Nullable */final String fieldName, /* @Nullable */JvmTypeReference typeRef, /* @Nullable */
            JvmTypeReference typeEntityRef) {
        return _toSetter(sourceElement, propertyName, fieldName, typeRef, typeEntityRef, null);
    }

    /* @Nullable */
    public JvmOperation _toSetter(/* @Nullable */final EObject sourceElement, /* @Nullable */final String propertyName,
            /* @Nullable */final String fieldName, /* @Nullable */JvmTypeReference typeRef, /* @Nullable */
            JvmTypeReference typeEntityRef, /* @Nullable */Procedure1<? super JvmOperation> initializer) {
        if (sourceElement == null || propertyName == null || fieldName == null)
            return null;
        JvmOperation result = typesFactory.createJvmOperation();
        result.setVisibility(JvmVisibility.PUBLIC);
        result.setReturnType(cloneWithProxies(typeEntityRef));
        result.setSimpleName("_set" + _toFirstUpper(propertyName));
        result.getParameters().add(toParameter(sourceElement, propertyName, cloneWithProxies(typeRef)));
        setBody(result, new Procedures.Procedure1<ITreeAppendable>() {
            public void apply(/* @Nullable */ITreeAppendable p) {
                if (p != null) {
                    p = p.trace(sourceElement);
                    p.append("this.").append(fieldName).append(" = ").append(propertyName).append(";");
                    p.newLine().append("return this;");
                }
            }
        });
        associate(sourceElement, result);
        return initializeSafely(result, initializer);
    }

    public String toFirstUpper(String s) {
        if (s == null || s.length() == 0)
            return s;
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        if (s.length() == 1)
            return s.toUpperCase();
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public String _toFirstUpper(String name) {
        int l = name.length();
        if (l == 0)
            return name;
        if (l == 1)
            return name.toUpperCase();
        char c = name.charAt(1);
        if (Character.isUpperCase(c))
            return name;
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
