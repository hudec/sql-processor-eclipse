package org.sqlproc.plugin.lib.property;

import org.eclipse.xtext.common.types.JvmType;

public interface PojoDefinition {

    public String getName();

    public String getClazz();

    public JvmType getClassx();

    public String getQualifiedName();
}
