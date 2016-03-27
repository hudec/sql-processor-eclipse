package org.sqlproc.plugin.lib.property;

import java.util.List;

public interface ImplementsExtends {

	public boolean isGenerics();

	public List<String> getDbTables();

	public List<String> getDbNotTables();

	public String getIdentifier();
}
