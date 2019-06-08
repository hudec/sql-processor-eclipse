package org.sqlproc.plugin.lib.property;

import org.eclipse.xtext.common.types.JvmType;

public interface PojoEntityType {

    public JvmType getType();

    public String getQualifiedName();

    public String getSimpleName();
}
