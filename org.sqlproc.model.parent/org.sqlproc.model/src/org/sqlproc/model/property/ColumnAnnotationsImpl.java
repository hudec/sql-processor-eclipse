package org.sqlproc.model.property;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.sqlproc.model.processorModel.AnnotationDefinitionModel;
import org.sqlproc.plugin.lib.property.ColumnAnnotations;
import org.sqlproc.plugin.lib.util.CommonUtils;

public class ColumnAnnotationsImpl implements ColumnAnnotations {

    String dbColumn;
    Set<String> annotations = new TreeSet<String>();
    List<String> dbTables = new ArrayList<String>();
    List<String> dbNotTables = new ArrayList<String>();
    boolean isGetter;
    boolean isSetter;

    public ColumnAnnotationsImpl(String dbColumn, List<AnnotationDefinitionModel> annotations, List<String> dbTables,
            List<String> dbNotTables, boolean isGetter, boolean isSetter) {
        this.dbColumn = dbColumn;
        for (AnnotationDefinitionModel annotation : annotations) {
            this.annotations.add(annotation.getName());
        }
        this.dbTables.addAll(dbTables);
        for (String dbTable : dbTables)
            this.dbTables.add(CommonUtils.tableToCamelCase(dbTable));
        this.dbNotTables.addAll(dbNotTables);
        for (String dbTable : dbNotTables)
            this.dbNotTables.add(CommonUtils.tableToCamelCase(dbTable));
        this.isGetter = isGetter;
        this.isSetter = isSetter;
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
    public boolean isGetter() {
        return isGetter;
    }

    @Override
    public boolean isSetter() {
        return isSetter;
    }

    @Override
    public String toString() {
        return "ColumnAnnotationsImpl [dbColumn=" + dbColumn + ", annotations=" + annotations + ", dbTables=" + dbTables
                + ", dbNotTables=" + dbNotTables + ", isGetter=" + isGetter + ", isSetter=" + isSetter + "]";
    }
}
