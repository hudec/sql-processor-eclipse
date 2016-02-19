package org.sqlproc.meta.property;

import org.eclipse.xtext.common.types.JvmType;
import org.sqlproc.meta.processorMeta.PojoDefinitionModel;
import org.sqlproc.plugin.lib.property.PojoDefinition;

public class PojoDefinitionImpl implements PojoDefinition {

    String name;
    String clazz;
    JvmType classx;

    // public PojoDefinitionImpl(String name, String clazz, JvmType classx) {
    // super();
    // this.name = name;
    // this.clazz = clazz;
    // this.classx = classx;
    // }

    public PojoDefinitionImpl(PojoDefinitionModel model, PojoDefinition oldModel) {
        super();
        if (model != null) {
            this.name = model.getName();
            System.out.println("META name " + name);
            this.clazz = model.getClass_();
            System.out.println("META clazz " + clazz);
            this.classx = model.getClassx();
            System.out.println("META classx " + classx);
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

    // public JvmType getClassx() {
    // return classx;
    // }

    @Override
    public String getQualifiedName() {
        return (classx != null) ? classx.getQualifiedName() : clazz;
    }

    @Override
    public String toString() {
        return "meta.PojoDefinitionImpl [name=" + name + ", clazz=" + clazz + ", classx=" + classx + "]";
    }
}
