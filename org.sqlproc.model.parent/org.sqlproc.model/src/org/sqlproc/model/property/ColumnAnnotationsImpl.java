package org.sqlproc.model.property;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.sqlproc.model.processorModel.AnnotationDefinitionModel;
import org.sqlproc.plugin.lib.property.ColumnAnnotations;

public class ColumnAnnotationsImpl implements ColumnAnnotations {

    String dbColumn;
    Set<String> annotations = new TreeSet<String>();
    List<String> dbTables = new ArrayList<String>();
    List<String> dbNotTables = new ArrayList<String>();

    public ColumnAnnotationsImpl(String dbColumn, List<AnnotationDefinitionModel> annotations, List<String> dbTables,
            List<String> dbNotTables) {
        this.dbColumn = dbColumn;
        for (AnnotationDefinitionModel annotation : annotations) {
            this.annotations.add(annotation.getName());
        }
        this.dbTables.addAll(dbTables);
        this.dbNotTables.addAll(dbNotTables);
    }

    public String getDbColumn() {
        return dbColumn;
    }

    public Set<String> getAnnotations() {
        return annotations;
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
        return "ColumnAnnotationsImpl [dbColumn=" + dbColumn + ", annotations=" + annotations + ", dbTables=" + dbTables
                + ", dbNotTables=" + dbNotTables + "]";
    }
}
