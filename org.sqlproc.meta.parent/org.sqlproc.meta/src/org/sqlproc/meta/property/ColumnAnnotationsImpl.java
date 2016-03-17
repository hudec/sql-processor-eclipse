package org.sqlproc.meta.property;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.sqlproc.meta.processorMeta.AnnotationDefinitionModel;
import org.sqlproc.plugin.lib.property.ColumnAnnotations;
import org.sqlproc.plugin.lib.util.CommonUtils;

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
        for (String dbTable : this.dbTables)
            this.dbTables.add(CommonUtils.tableToCamelCase(dbTable));
        this.dbNotTables.addAll(dbNotTables);
        for (String dbTable : this.dbNotTables)
            this.dbNotTables.add(CommonUtils.tableToCamelCase(dbTable));
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
