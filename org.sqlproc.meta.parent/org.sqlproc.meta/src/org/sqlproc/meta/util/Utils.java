package org.sqlproc.meta.util;

import java.util.ArrayList;
import java.util.List;

import org.sqlproc.meta.processorMeta.Column;
import org.sqlproc.meta.processorMeta.ExtendedColumn;
import org.sqlproc.meta.processorMeta.ExtendedMappingItem;
import org.sqlproc.meta.processorMeta.MappingColumn;
import org.sqlproc.meta.processorMeta.MappingRule;
import org.sqlproc.meta.processorMeta.MetaStatement;
import org.sqlproc.meta.processorMeta.PojoType;
import org.sqlproc.meta.processorMeta.ValueType;
import org.sqlproc.plugin.lib.util.CommonUtils;

public class Utils extends CommonUtils {

    public static String getTokenFromModifier(MetaStatement statement, String tokenName) {
        if (statement.getModifiers() == null || statement.getModifiers().isEmpty()) {
            return null;
        }
        for (String filter : statement.getModifiers()) {
            int ix = filter.indexOf('=');
            if (ix <= 0)
                continue;
            String key = filter.substring(0, ix);
            String val = filter.substring(ix + 1);
            if (key.equals(tokenName)) {
                return val;
            }
        }
        return null;
    }

    public static String getTokenFromModifier(MappingRule rule, String tokenName) {
        if (rule.getModifiers() == null || rule.getModifiers().isEmpty()) {
            return null;
        }
        for (String filter : rule.getModifiers()) {
            int ix = filter.indexOf('=');
            if (ix <= 0)
                continue;
            String key = filter.substring(0, ix);
            String val = filter.substring(ix + 1);
            if (key.equals(tokenName)) {
                return val;
            }
        }
        return null;
    }

    public static String getTokenFromModifier(MetaStatement statement, String tokenName, String tokenSuffix) {
        if (statement.getModifiers() == null || statement.getModifiers().isEmpty()) {
            return null;
        }
        for (String filter : statement.getModifiers()) {
            int ix = filter.indexOf('=');
            if (ix <= 0)
                continue;
            String key = filter.substring(0, ix);
            String val = filter.substring(ix + 1);
            if (tokenSuffix != null) {
                int ix2 = val.indexOf('=');
                if (ix2 <= 0)
                    continue;
                if (!tokenSuffix.equals(val.substring(ix2 + 1)))
                    continue;
                val = val.substring(0, ix2);
            }
            if (key.equals(tokenName)) {
                return val;
            }
        }
        return null;
    }

    public static List<String> getTokensFromModifier(MetaStatement statement, String tokenName) {
        List<String> result = new ArrayList<String>();
        if (statement.getModifiers() == null || statement.getModifiers().isEmpty()) {
            return result;
        }
        for (String filter : statement.getModifiers()) {
            int ix = filter.indexOf('=');
            if (ix <= 0)
                continue;
            String key = filter.substring(0, ix);
            String val = filter.substring(ix + 1);
            int ix2 = val.indexOf('=');
            // String val2 = (ix2 > 0) ? val.substring(ix2 + 1) : null;
            val = (ix2 > 0) ? val.substring(0, ix2) : val;
            if (key.equals(tokenName)) {
                result.add(val);
            }
        }
        return result;
    }

    public static String constName(String name) {
        String result = "";
        int last = 0;
        for (int i = 0, l = name.length(); i < l; i++) {
            if (Character.isUpperCase(name.charAt(i))) {
                result = result + name.substring(last, i).toUpperCase() + "_";
                last = i;
            }
        }
        if (last < name.length())
            result = result + name.substring(last).toUpperCase();
        return result;
    }

    public static boolean isFinal(MetaStatement m) {
        String finalToken = getTokenFromModifier(m, "final");
        if (finalToken != null)
            return true;
        return false;
    }

    public static String getName(Column column) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (ExtendedColumn ei : column.getColumns()) {
            if (first)
                first = false;
            else
                sb.append(".");
            sb.append(ei.getCol().getName());
        }
        return sb.toString();
    }

    public static String getName(MappingColumn column) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (ExtendedMappingItem ei : column.getItems()) {
            if (first)
                first = false;
            else
                sb.append(".");
            sb.append(ei.getAttr().getName());
        }
        return sb.toString();
    }

    public static boolean isNumber(String s) {
        if (s == null)
            return false;
        s = s.trim();
        for (int i = 0, l = s.length(); i < l; i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    public static String _toFirstUpper(String name) {
        int l = name.length();
        if (l == 0)
            return name;
        if (l == 1)
            return name.toUpperCase();
        char c = name.charAt(1);
        if (Character.isUpperCase(c))
            return name;
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static String getPropertyValue(ValueType pv) {
        String s = _getPropertyValue(pv);
        if (s == null)
            return null;
        if (s.startsWith("$$")) {
            String ss = System.getenv(s.substring(2));
            if (ss != null)
                s = ss;
        }
        return s;
    }

    public static String _getPropertyValue(ValueType pv) {
        if (pv == null)
            return null;
        String s = pv.getValue();
        if (s != null) {
            s = s.trim();
            if (s.startsWith("\""))
                s = s.substring(1);
            if (s.endsWith("\""))
                s = s.substring(0, s.length() - 1);
            return s;
        } else if (pv.getId() != null)
            return pv.getId();
        else
            return "" + pv.getNumber();
    }

    public static String getPropertyValueRegex(ValueType pv) {
        String s = getPropertyValue(pv);
        if (s == null)
            return null;
        String s2 = s.replaceAll("\\\\\\\\", "\\\\");
        return s2;
    }

    public static String getPropertyValue(PojoType pv) {
        if (pv == null)
            return null;
        if (pv.getType() != null) {
            if (pv.getType() != null) {
                StringBuilder sType = new StringBuilder(pv.getType().getQualifiedName());
                if (pv.getGtype() != null)
                    sType.append("<").append(pv.getGtype().getQualifiedName()).append(">");
                if (pv.isArray())
                    sType.append("[]");
                return sType.toString();
            }
        }
        if (pv.getIdent() != null)
            return getPropertyValue(pv.getIdent());
        if (pv.getRef() != null) {
            if (pv.getRef().getClassx() != null)
                return pv.getRef().getClassx().getQualifiedName();
            return pv.getRef().getClass_();
        }
        return null;
    }
}
