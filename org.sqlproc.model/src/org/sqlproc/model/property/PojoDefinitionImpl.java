package org.sqlproc.model.property;

import org.eclipse.xtext.common.types.JvmType;
import org.sqlproc.model.processorModel.PojoDefinitionModel;
import org.sqlproc.plugin.lib.property.PojoDefinition;

public class PojoDefinitionImpl implements PojoDefinition {

    String name;
    String clazz;
    JvmType classx;

    public PojoDefinitionImpl(PojoDefinitionModel model, PojoDefinition oldModel) {
        super();
        if (model != null) {
            this.name = model.getName();
            System.out.println("model.name " + name);
            this.clazz = model.getClass_();
            System.out.println("model.clazz " + clazz);
            this.classx = model.getClassx();
            System.out.println("model.classx " + classx);
            if (classx != null && oldModel != null && (oldModel instanceof PojoDefinitionImpl)
                    && ((PojoDefinitionImpl) oldModel).classx != null) {
                if (classx.toString().indexOf("JvmVoid") >= 0) {
                    classx = ((PojoDefinitionImpl) oldModel).classx;
                    System.out.println("model.old.classx " + classx);
                }
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getClazz() {
        return clazz;
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
        return "model.PojoDefinitionImpl [name=" + name + ", clazz=" + clazz + ", classx=" + classx + "]";
    }
}
