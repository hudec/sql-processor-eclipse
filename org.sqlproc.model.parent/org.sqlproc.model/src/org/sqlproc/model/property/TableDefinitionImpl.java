package org.sqlproc.model.property;

import org.sqlproc.plugin.lib.property.TableDefinition;

public class TableDefinitionImpl implements TableDefinition {

    String name;
    String table;

    public TableDefinitionImpl(String name, String table) {
        super();
        this.name = name;
        this.table = table;
    }

    public String getName() {
        return name;
    }

    public String getTable() {
        return table;
    }
}
