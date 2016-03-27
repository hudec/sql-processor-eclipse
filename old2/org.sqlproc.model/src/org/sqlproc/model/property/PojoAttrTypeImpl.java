package org.sqlproc.model.property;

import org.eclipse.xtext.common.types.JvmPrimitiveType;
import org.sqlproc.model.processorModel.PojoType;
import org.sqlproc.model.util.Utils;
import org.sqlproc.plugin.lib.property.PojoAttrType;

public class PojoAttrTypeImpl implements PojoAttrType {
    String column;
    String sqlType;
    boolean nativeType;
    PojoType type;

    public PojoAttrTypeImpl(String column, String sqlType, PojoType type) {
        this.column = column;
        this.sqlType = sqlType;
        this.type = type;
        // JvmPrimitiveTypeImplCustom
        this.nativeType = (type != null && type.getType() != null && type.getType() instanceof JvmPrimitiveType) ? true
                : false;
    }

    @Override
    public boolean isNativeType() {
        return nativeType;
    }

    @Override
    public String getQualifiedName() {
        return Utils.getPropertyValue(type);
    }

    @Override
    public String toString() {
        return "PojoAttrTypeImpl [column=" + column + ", sqlType=" + sqlType + ", nativeType=" + nativeType + ", type="
                + type + "]";
    }
}
