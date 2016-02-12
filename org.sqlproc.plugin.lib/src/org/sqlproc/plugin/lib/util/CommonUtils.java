package org.sqlproc.plugin.lib.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.util.Strings;
import org.sqlproc.plugin.lib.property.FunctionDefinition;
import org.sqlproc.plugin.lib.property.ModelProperty;
import org.sqlproc.plugin.lib.property.ProcedureDefinition;
import org.sqlproc.plugin.lib.property.TableDefinition;
import org.sqlproc.plugin.lib.resolver.DbResolver;
import org.sqlproc.plugin.lib.resolver.DbResolver.DbType;

public class CommonUtils {

    @SuppressWarnings("unchecked")
    public static <T extends EObject> List<T> getContentsOfType(EObject ele, Class<T> type) {
        List<T> result = new ArrayList<T>();
        if (ele == null)
            return result;
        for (EObject object : ele.eContents()) {
            if (type.isAssignableFrom(object.getClass())) {
                result.add((T) object);
            }
        }
        return result;
    }

    public static String resourceDir(Resource resource) {
        String uri = (resource.getURI() != null) ? resource.getURI().toString() : null;
        String dir = null;
        if (uri != null) {
            dir = uri;
            int ix = uri.lastIndexOf("/");
            if (ix > 0)
                dir = uri.substring(0, ix);
        }
        return dir;
    }

    public static DbType getDbType(DbResolver dbResolver, EObject model) {
        DbType dbType = dbResolver.getDatabaseDirectives(model).dbType;
        if (dbType == null) {
            DbType[] dbTypes = DbType.fromDbMetaInfo(dbResolver.getDbMetaInfo(model));
            if (dbTypes != null && dbTypes.length > 0)
                dbType = dbTypes[0];
        }
        return dbType;
    }

    public static String toCamelCase(String value) {
        if (value == null)
            return null;
        String[] parts = value.split("_");
        String camelCaseString = "";
        for (String part : parts) {
            if (camelCaseString.length() == 0)
                camelCaseString = camelCaseString + part.toLowerCase();
            else if (part.length() == 1)
                camelCaseString = camelCaseString + part.toUpperCase();
            else if (part.length() > 1)
                camelCaseString = camelCaseString + part.substring(0, 1).toUpperCase()
                        + part.substring(1).toLowerCase();
        }
        return camelCaseString;
    }

    public static String toCamelCase(Class<?> clazz) {
        if (clazz == null)
            return null;
        String camelCaseString = clazz.getSimpleName();
        if (camelCaseString.length() == 1)
            camelCaseString = camelCaseString.toLowerCase();
        else if (camelCaseString.length() > 1)
            camelCaseString = camelCaseString.substring(0, 1).toLowerCase() + camelCaseString.substring(1);
        return camelCaseString;
    }

    public static String getPojoColumns(List<String> columns) {
        if (columns == null)
            return null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            builder.append(':').append(toCamelCase(columns.get(i)));
            if (i < columns.size() - 1)
                builder.append(", ");
        }
        return builder.toString();
    }

    public static String getSelectColumns(List<String> columns) {
        if (columns == null)
            return null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            builder.append("%" + columns.get(i)).append(" @").append(toCamelCase(columns.get(i)));
            if (i < columns.size() - 1)
                builder.append(", ");
        }
        return builder.toString();
    }

    public static String getInsertColumns(List<String> columns) {
        if (columns == null)
            return null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            builder.append("%" + columns.get(i));
            if (i < columns.size() - 1)
                builder.append(", ");
        }
        return builder.toString();
    }

    public static String getUpdateColumns(List<String> columns) {
        if (columns == null)
            return null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            builder.append("%" + columns.get(i)).append(" = :").append(toCamelCase(columns.get(i)));
            if (i < columns.size() - 1)
                builder.append(", ");
        }
        return builder.toString();
    }

    public static String getCondColumns(List<String> columns) {
        if (columns == null)
            return null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            builder.append("\n  {& ").append("%" + columns.get(i)).append(" = :").append(toCamelCase(columns.get(i)))
                    .append(" }");
        }
        return builder.toString();
    }

    public static String getVerUpdateColumns(List<String> columns) {
        if (columns == null)
            return null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).toUpperCase().indexOf("VER") == 0) {
                builder.append("%" + columns.get(i)).append(" = :").append(toCamelCase(columns.get(i)))
                        .append(" + 1, ");
                break;
            }
        }
        return builder.toString();
    }

    public static String getOptUpdateColumns(List<String> columns) {
        if (columns == null)
            return null;
        boolean idFind = false;
        boolean verFind = false;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            if (!idFind && (columns.get(i).toUpperCase().equals("ID")
                    || columns.get(i).toUpperCase().indexOf("ID_") == 0)) {
                idFind = true;
                continue;
            }
            if (!verFind && columns.get(i).toUpperCase().indexOf("VER") == 0) {
                verFind = true;
                continue;
            }
            builder.append("%" + columns.get(i)).append(" = :").append(toCamelCase(columns.get(i)));
            if (i < columns.size() - 1)
                builder.append(", ");
        }
        return builder.toString();
    }

    public static String getOptCondColumns(List<String> columns) {
        if (columns == null)
            return null;
        boolean idFind = false;
        boolean verFind = false;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            if (!idFind && (columns.get(i).toUpperCase().equals("ID")
                    || columns.get(i).toUpperCase().indexOf("ID_") == 0)) {
                idFind = true;
                builder.append("\n  {& ").append("%" + columns.get(i)).append(" = :")
                        .append(toCamelCase(columns.get(i))).append(" }");
                continue;
            }
            if (!verFind && columns.get(i).toUpperCase().indexOf("VER") == 0) {
                verFind = true;
                builder.append("\n  {& ").append("%" + columns.get(i)).append(" = :")
                        .append(toCamelCase(columns.get(i))).append(" }");
                continue;
            }
        }
        return builder.toString();
    }

    public static String getPojoDefinitions(List<Class<?>> pojoClasses) {
        if (pojoClasses == null)
            return null;
        TreeMap<String, String> map = new TreeMap<String, String>();
        for (Class<?> clazz : pojoClasses) {
            map.put(toCamelCase(clazz), clazz.getName());
        }

        StringBuilder builder = new StringBuilder();
        for (Entry<String, String> pojo : map.entrySet()) {
            builder.append("pojo ").append(Strings.toFirstUpper(pojo.getKey())).append(' ').append(pojo.getValue())
                    .append(";\n");
        }
        return builder.toString();
    }

    public static String getTablesDefinitions(List<String> tables, Map<String, TableDefinition> tablesPresented) {
        if (tables == null)
            return null;
        Map<String, String> map = new TreeMap<String, String>();
        if (tablesPresented != null) {
            for (TableDefinition def : tablesPresented.values()) {
                map.put(def.getTable(), def.getName());
            }
        }
        for (String table : tables) {
            if (table.toUpperCase().startsWith("BIN$"))
                continue;
            if (map.containsKey(table))
                continue;
            map.put(table, toCamelCase(table));
        }

        StringBuilder builder = new StringBuilder();
        for (Entry<String, String> table : map.entrySet()) {
            builder.append("table ").append(table.getValue()).append(' ').append(table.getKey()).append(";\n");
        }
        return builder.toString();
    }

    public static String getProceduresDefinitions(List<String> procedures,
            Map<String, ProcedureDefinition> proceduresPresented) {
        if (procedures == null)
            return null;
        Map<String, String> map = new TreeMap<String, String>();
        if (proceduresPresented != null) {
            for (ProcedureDefinition def : proceduresPresented.values()) {
                map.put(def.getProcedure(), def.getName());
            }
        }
        for (String procedure : procedures) {
            if (procedure.toUpperCase().startsWith("BIN$"))
                continue;
            if (map.containsKey(procedure))
                continue;
            map.put(procedure, toCamelCase(procedure));
        }

        StringBuilder builder = new StringBuilder();
        for (Entry<String, String> procedure : map.entrySet()) {
            builder.append("procedure ").append(procedure.getValue()).append(' ').append(procedure.getKey())
                    .append(";\n");
        }
        return builder.toString();
    }

    public static String getFunctionsDefinitions(List<String> functions,
            Map<String, FunctionDefinition> functionsPresented) {
        if (functions == null)
            return null;
        Map<String, String> map = new TreeMap<String, String>();
        if (functionsPresented != null) {
            for (FunctionDefinition def : functionsPresented.values()) {
                map.put(def.getFunction(), def.getName());
            }
        }
        for (String function : functions) {
            if (function.toUpperCase().startsWith("BIN$"))
                continue;
            if (map.containsKey(function))
                continue;
            map.put(function, toCamelCase(function));
        }

        StringBuilder builder = new StringBuilder();
        for (Entry<String, String> function : map.entrySet()) {
            builder.append("function ").append(function.getValue()).append(' ').append(function.getKey()).append(";\n");
        }
        return builder.toString();
    }

    public static StringBuilder removeBlankLines(StringBuilder sb) {
        int len = sb.length();
        while (len > 0) {
            for (int i = 0; i < len; i++) {
                char c = sb.charAt(i);
                if (c == ' ')
                    continue;
                if (c != '\n')
                    return sb;
                sb.delete(0, i + 1);
                break;
            }
            len = sb.length();
        }
        return sb;
    }

    public static String removeBlankLines(String s) {
        StringBuilder sb = new StringBuilder(s);
        int len = sb.length();
        while (len > 0) {
            for (int i = 0; i < len; i++) {
                char c = sb.charAt(i);
                if (c == ' ')
                    continue;
                if (c != '\n')
                    return sb.delete(0, i).toString();
                sb.delete(0, i + 1);
                break;
            }
            len = sb.length();
        }
        return sb.toString();
    }

    public static String replaceAll(ModelProperty modelProperty, String buffer, EObject model) {
        for (Entry<String, String> entry : modelProperty.getReplaceAll(model).entrySet()) {
            String regex = entry.getKey();
            String replacement = entry.getValue();
            buffer = buffer.replaceAll(regex, replacement);
        }
        return buffer;
    }

    public static String getFinalContent(String s) {
        if (s == null)
            return null;
        if (s.startsWith("\n"))
            s = s.substring(1);
        return s + "\n";
    }

    public static String getSimpleName(String qn) {
        if (qn == null)
            return null;
        int ix = qn.lastIndexOf('.');
        if (ix < 0)
            return qn;
        return qn.substring(ix + 1, qn.length());
    }

    public static String getPropertyValue(String value) {
        String s = _getPropertyValue(value);
        if (s == null)
            return null;
        if (s.startsWith("$$")) {
            String ss = System.getenv(s.substring(2));
            if (ss != null)
                s = ss;
        }
        return s;
    }

    public static String _getPropertyValue(String value) {
        if (value == null)
            return null;
        value = value.trim();
        if (value.startsWith("\""))
            value = value.substring(1);
        if (value.endsWith("\""))
            value = value.substring(0, value.length() - 1);
        return value;
    }

    public static boolean skipVerification(EObject model, ModelProperty modelProperty) {
        if (modelProperty == null)
            assert false;
        boolean result = modelProperty.skipVerification(model);
        // System.out.println(model.toString() + "->" + result);
        return result;
    }
}
