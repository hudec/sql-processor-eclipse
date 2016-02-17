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

    public PojoDefinitionImpl(PojoDefinitionModel model) {
        super();
        if (model != null) {
            this.name = model.getName();
            this.clazz = model.getClass_();
            this.classx = model.getClassx();
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
}
