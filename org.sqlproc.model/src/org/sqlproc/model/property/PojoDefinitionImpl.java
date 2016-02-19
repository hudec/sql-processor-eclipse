package org.sqlproc.model.property;

import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.sqlproc.model.processorModel.PojoDefinitionModel;
import org.sqlproc.plugin.lib.property.PojoDefinition;

public class PojoDefinitionImpl implements PojoDefinition {

    String name;
    String clazz;
    JvmParameterizedTypeReference classx;

    public PojoDefinitionImpl(PojoDefinitionModel model) {
        super();
        if (model != null) {
            this.name = model.getName();
            System.out.println("MODEL name " + name);
            this.clazz = model.getClass_();
            System.out.println("MODEL clazz " + clazz);
            this.classx = model.getClassx();
            System.out.println("MODEL classx " + classx);
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

    // public JvmParameterizedTypeReference getClassx() {
    // return classx;
    // }

    @Override
    public String getQualifiedName() {
        return (classx != null) ? classx.getQualifiedName() : clazz;
    }

    @Override
    public String toString() {
        return "model.PojoDefinitionImpl [name=" + name + ", clazz=" + clazz + ", classx=" + classx + "]";
    }
}
