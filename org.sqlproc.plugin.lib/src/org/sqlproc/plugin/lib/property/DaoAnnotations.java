package org.sqlproc.plugin.lib.property;

import java.util.Map;
import java.util.Set;

public interface DaoAnnotations {

    public static final int IS_STANDARD = 8;

    public Map<String, Integer> getAnnotations();

    public Set<String> getDbTables(String name);

    public Set<String> getDbNotTables(String name);
}
