package org.sqlproc.model.property;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.sqlproc.model.processorModel.AnnotationDefinitionModel;
import org.sqlproc.plugin.lib.property.PojoAnnotations;
import org.sqlproc.plugin.lib.util.CommonUtils;

public class PojoAnnotationsImpl implements PojoAnnotations {

    Map<String, Integer> annotations = new TreeMap<>();
    Map<String, Set<String>> dbTables = new HashMap<>();
    Map<String, Set<String>> dbNotTables = new HashMap<>();

    public PojoAnnotationsImpl(final PojoAnnotationsImpl pojoAnnotations, final String dbColumn,
            final List<AnnotationDefinitionModel> annotations, final List<String> dbTables,
            final List<String> dbNotTables, final int type) {
        if (pojoAnnotations != null) {
            this.annotations = pojoAnnotations.annotations;
            this.dbTables = pojoAnnotations.dbTables;
            this.dbNotTables = pojoAnnotations.dbNotTables;
        } else {
            this.annotations = new TreeMap<>();
            this.dbTables = new HashMap<>();
            this.dbNotTables = new HashMap<>();
        }
        for (AnnotationDefinitionModel annotation : annotations) {
            String name = annotation.getName();
            Integer _type = this.annotations.get(name);
            if (_type == null)
                _type = type;
            else
                _type = _type | type;
            this.annotations.put(name, _type);
            for (String dbTable : dbTables) {
                Set<String> _dbTables = this.dbTables.get(name + type);
                if (_dbTables == null)
                    _dbTables = new HashSet<>();
                _dbTables.add(dbTable);
                _dbTables.add(CommonUtils.tableToCamelCase(dbTable));
                this.dbTables.put(name + type, _dbTables);
            }
            for (String dbTable : dbNotTables) {
                Set<String> _dbTables = this.dbNotTables.get(name + type);
                if (_dbTables == null)
                    _dbTables = new HashSet<>();
                _dbTables.add(dbTable);
                _dbTables.add(CommonUtils.tableToCamelCase(dbTable));
                this.dbNotTables.put(name + type, _dbTables);
            }
        }
    }

    @Override
    public Map<String, Integer> getAnnotations() {
        return annotations;
    }

    @Override
    public Set<String> getDbTables(String name) {
        return dbTables.get(name);
    }

    @Override
    public Set<String> getDbNotTables(String name) {
        return dbNotTables.get(name);
    }
}
