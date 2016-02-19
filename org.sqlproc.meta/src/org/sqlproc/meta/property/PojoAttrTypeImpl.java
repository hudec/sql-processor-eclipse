package org.sqlproc.meta.property;

import org.eclipse.xtext.common.types.JvmPrimitiveType;
import org.eclipse.xtext.common.types.JvmType;
import org.sqlproc.meta.processorMeta.PojoType;
import org.sqlproc.meta.util.Utils;
import org.sqlproc.plugin.lib.property.PojoAttrType;

public class PojoAttrTypeImpl implements PojoAttrType {
    PojoType pojoType;
    String column;
    String sqlType;
    boolean nativeType;
    JvmType type;
    JvmType gtype;
    boolean array;
    PojoDefinitionImpl ref;
    PojoDefinitionImpl gref;

    public PojoAttrTypeImpl(String column, String sqlType, PojoType pojoType) {
        this.pojoType = pojoType;
        this.column = column;
        this.sqlType = sqlType;
        this.type = pojoType.getType();
        // JvmPrimitiveTypeImplCustom
        this.nativeType = (type != null && type instanceof JvmPrimitiveType) ? true : false;
        this.ref = (pojoType.getRef() != null) ? new PojoDefinitionImpl(pojoType.getRef(), null) : null;
        this.array = pojoType.isArray();
        this.gtype = pojoType.getGtype();
        this.gref = (pojoType.getGref() != null) ? new PojoDefinitionImpl(pojoType.getGref(), null) : null;
    }

    @Override
    public boolean isNativeType() {
        return nativeType;
    }

    @Override
    public String getQualifiedName() {
        return Utils.getPropertyValue(pojoType);
    }

    @Override
    public String toString() {
        return "PojoAttrTypeImpl [pojoType=" + pojoType + ", column=" + column + ", sqlType=" + sqlType
                + ", nativeType=" + nativeType + ", type=" + type + ", gtype=" + gtype + ", array=" + array + ", ref="
                + ref + ", gref=" + gref + "]";
    }
}
