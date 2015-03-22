package org.sqlproc.model.property;

import java.util.ArrayList;
import java.util.List;

import org.sqlproc.model.processorModel.PojoType;
import org.sqlproc.plugin.lib.property.ImplementsExtends;

public class ImplementsExtendsImpl implements ImplementsExtends {

	PojoType toImplement;
	boolean generics;
	List<String> dbTables = new ArrayList<String>();
	List<String> dbNotTables = new ArrayList<String>();

	public ImplementsExtendsImpl(PojoType toImplement, boolean generics, List<String> dbTables, List<String> dbNotTables) {
		super();
		this.toImplement = toImplement;
		this.generics = generics;
		this.dbTables.addAll(dbTables);
		this.dbNotTables.addAll(dbNotTables);
	}

	public String getIdentifier() {
		// TODO
		return null;
	}

	@Override
	public boolean isGenerics() {
		return generics;
	}

	@Override
	public List<String> getDbTables() {
		return dbTables;
	}

	@Override
	public List<String> getDbNotTables() {
		return dbNotTables;
	}

	@Override
	public String toString() {
		return "ImplementsExtends [toImplement=" + toImplement + ", generics=" + generics + ", dbTables=" + dbTables
		        + ", dbNotTables=" + dbNotTables + "]";
	}
}
