package org.sqlproc.plugin.lib.property;

import java.util.List;
import java.util.Set;

public interface ColumnAnnotations {

    public String getDbColumn();

    public Set<String> getAnnotations();

    public List<String> getDbTables();

    public List<String> getDbNotTables();
}
