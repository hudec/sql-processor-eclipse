package org.sqlproc.plugin.lib.property;

import org.eclipse.xtext.common.types.JvmType;

public interface PojoDefinition {

    // public String getName();

    // public String getClazz();

    // ProcessorMetaValidator
    public JvmType getClassx();

    // ProcessorMetaProposalProvider
    // ProcessorMetaValidator
    public String getQualifiedName();
}
