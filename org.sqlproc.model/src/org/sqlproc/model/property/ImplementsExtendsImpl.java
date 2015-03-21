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

	// TODO
	// public JvmType getToImplement() {
	// return toImplement;
	// }
	//
	// public void setToImplement(JvmType toImplement) {
	// this.toImplement = toImplement;
	// }

	public boolean isGenerics() {
		return generics;
	}

	public void setGenerics(boolean generics) {
		this.generics = generics;
	}

	public List<String> getDbTables() {
		return dbTables;
	}

	public void setDbTables(List<String> dbTables) {
		this.dbTables = dbTables;
	}

	public List<String> getDbNotTables() {
		return dbNotTables;
	}

	public void setDbNotTables(List<String> dbNotTables) {
		this.dbNotTables = dbNotTables;
	}

	@Override
	public String toString() {
		return "ImplementsExtends [toImplement=" + toImplement + ", generics=" + generics + ", dbTables=" + dbTables
		        + ", dbNotTables=" + dbNotTables + "]";
	}
}
