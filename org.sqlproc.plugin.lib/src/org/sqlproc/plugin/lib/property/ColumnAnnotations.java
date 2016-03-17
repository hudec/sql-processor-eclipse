package org.sqlproc.plugin.lib.property;

import java.util.Map;
import java.util.Set;

public interface ColumnAnnotations {

    public static final int IS_GETTER = 1;
    public static final int IS_SETTER = 2;
    public static final int IS_ANNOTATION = 4;

    public String getDbColumn();

    public Map<String, Integer> getAnnotations();

    public Set<String> getDbTables(String name);

    public Set<String> getDbNotTables(String name);
}
