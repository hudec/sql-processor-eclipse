package org.sqlproc.meta.property;

import org.sqlproc.plugin.lib.property.ProcedureDefinition;

public class ProcedureDefinitionImpl implements ProcedureDefinition {

    String name;
    String procedure;

    public ProcedureDefinitionImpl(String name, String procedure) {
        super();
        this.name = name;
        this.procedure = procedure;
    }

    public String getName() {
        return name;
    }

    public String getProcedure() {
        return procedure;
    }
}
