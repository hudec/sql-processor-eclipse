package org.sqlproc.meta.property;

import org.eclipse.xtext.common.types.JvmType;
import org.sqlproc.meta.processorMeta.PojoType;
import org.sqlproc.meta.util.Utils;
import org.sqlproc.plugin.lib.property.PojoEntityType;

public class PojoEntityTypeImpl implements PojoEntityType {

    PojoType type;

    public PojoEntityTypeImpl(PojoType type) {
        super();
        this.type = type;
    }

    @Override
    public String getSimpleName() {
        if (type.getType() != null)
            return type.getType().getSimpleName();
        if (type.getIdent() != null)
            return Utils.getSimpleName(Utils.getPropertyValue(type.getIdent()));
        if (type.getRef() != null) {
            if (type.getRef().getClassx() != null)
                return type.getRef().getClassx().getSimpleName();
            return Utils.getSimpleName(type.getRef().getClass_());
        }
        return null;
    }

    @Override
    public String getQualifiedName() {
        if (type.getType() != null)
            return type.getType().getQualifiedName();
        if (type.getIdent() != null)
            return Utils.getPropertyValue(type.getIdent());
        if (type.getRef() != null) {
            if (type.getRef().getClassx() != null)
                return type.getRef().getClassx().getQualifiedName();
            return type.getRef().getClass_();
        }
        return null;
    }

    public JvmType getType() {
        return type.getType();
    }

    @Override
    public String toString() {
        return "PojoEntityTypeImpl [type=" + type + "]";
    }
}
