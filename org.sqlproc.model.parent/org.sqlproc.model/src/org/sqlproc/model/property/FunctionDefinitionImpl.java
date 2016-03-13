package org.sqlproc.model.property;

import org.sqlproc.plugin.lib.property.FunctionDefinition;

public class FunctionDefinitionImpl implements FunctionDefinition {

    String name;
    String function;

    public FunctionDefinitionImpl(String name, String function) {
        super();
        this.name = name;
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public String getFunction() {
        return function;
    }
}
