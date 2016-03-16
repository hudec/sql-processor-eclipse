package org.sqlproc.model.property;

import org.eclipse.xtext.common.types.JvmType;
import org.sqlproc.model.processorModel.AnnotationDefinitionModel;
import org.sqlproc.model.processorModel.PojoDefinitionModel;
import org.sqlproc.plugin.lib.property.PojoDefinition;

public class PojoDefinitionImpl implements PojoDefinition {

    String name;
    String clazz;
    JvmType classx;

    public PojoDefinitionImpl(PojoDefinitionModel model, PojoDefinition oldModel) {
        super();
        if (model != null) {
            name = model.getName();
            System.out.println("meta.name " + name);
            clazz = model.getClass_();
            System.out.println("meta.clazz " + clazz);
            if (model.getClassx() != null)
                clazz = model.getClassx();
            System.out.println("meta.classx " + classx);
            if (classx != null && oldModel != null && (oldModel instanceof PojoDefinitionImpl)
                    && ((PojoDefinitionImpl) oldModel).classx != null) {
                if (classx.toString().indexOf("JvmVoid") >= 0) {
                    classx = ((PojoDefinitionImpl) oldModel).classx;
                    System.out.println("meta.old.classx " + classx);
                }
            }
        }
    }

    public PojoDefinitionImpl(AnnotationDefinitionModel model, PojoDefinition oldModel) {
        super();
        if (model != null) {
            name = model.getName();
            System.out.println("meta.name " + name);
            clazz = model.getClass_();
            System.out.println("meta.clazz " + clazz);
            classx = model.getClassx();
            System.out.println("meta.classx " + classx);
            if (classx != null && oldModel != null && (oldModel instanceof PojoDefinitionImpl)
                    && ((PojoDefinitionImpl) oldModel).classx != null) {
                if (classx.toString().indexOf("JvmVoid") >= 0) {
                    classx = ((PojoDefinitionImpl) oldModel).classx;
                    System.out.println("meta.old.classx " + classx);
                }
            }
        }
    }

    @Override
    public JvmType getClassx() {
        return classx;
    }

    @Override
    public String getQualifiedName() {
        return (classx != null) ? classx.getQualifiedName() : clazz;
    }

    @Override
    public String toString() {
        return "meta.PojoDefinitionImpl [name=" + name + ", clazz=" + clazz + ", classx=" + classx + "]";
    }
}
