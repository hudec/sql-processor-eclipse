package org.sqlproc.model.property;

import org.sqlproc.model.processorModel.PojoType;
import org.sqlproc.model.util.Utils;
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
                return type.getRef().getClassx();
            return Utils.getSimpleName(type.getRef().getClass_());
        }
        return null;
    }

    @Override
    public String toString() {
        return "PojoEntityTypeImpl [type=" + type + "]";
    }
}
