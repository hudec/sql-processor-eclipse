package org.sqlproc.plugin.lib.resolver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbCheckConstraint {
    String constraintName;
    String checkClause;
    String enumName;
    List<String> values;
    String table;
    String column;

    public String getConstraintName() {
        return constraintName;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
    }

    public String getCheckClause() {
        return checkClause;
    }

    public void setCheckClause(String checkClause) {
        this.checkClause = checkClause;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    static final Pattern HSQLDB_CHECK = Pattern.compile("(?i)\\(([^\\)]*)\\)\\s*IN\\s*\\(\\(('?.*?'?,'?.*?'?)\\)\\)*");

    public static DbCheckConstraint parseHsqldb(String name, String clause) {
        // (PUBLIC.CONTACT.TYPE) IN ((0),(1))
        // (PUBLIC.PERSON.GENDER) IN (('M'),('F'))
        Matcher matcher = HSQLDB_CHECK.matcher(clause);
        if (matcher.matches()) {
            String[] names = matcher.group(1).trim().split("\\.");
            String relTable = names[names.length - 2];
            String relCol = names[names.length - 1];
            String enumName = relTable + "_" + relCol;
            String[] constraintValues = matcher.group(2).trim().split(",");
            List<String> values = new ArrayList<String>();
            for (int j = 0; j < constraintValues.length; j++) {
                String value = constraintValues[j].trim();
                value = value.replaceAll("'|\\(|\\)", "");
                values.add(value);
            }
            DbCheckConstraint dbCheckConstraint = new DbCheckConstraint();
            dbCheckConstraint.setConstraintName(name);
            dbCheckConstraint.setCheckClause(clause);
            dbCheckConstraint.setEnumName(enumName);
            dbCheckConstraint.setValues(values);
            dbCheckConstraint.setTable(relTable);
            dbCheckConstraint.setColumn(relCol);
            return dbCheckConstraint;
        }
        return null;
    }

    static final Pattern H2_CHECK = Pattern.compile("(?i)\\((.*)\\s*IN\\s*\\(('?.*?'?\\s*,'?.*?'?\\s*)\\)*");

    public static DbCheckConstraint parseH2(String name, String clause, String relTable) {
        // (TYPE IN(0, 1, 2))
        // (GENDER IN('M', 'F')
        Matcher matcher = H2_CHECK.matcher(clause);
        if (matcher.matches()) {
            String[] names = matcher.group(1).trim().split("\\.");
            String relCol = names[names.length - 1];
            String enumName = relTable + "_" + relCol;
            String[] constraintValues = matcher.group(2).trim().split(",");
            List<String> values = new ArrayList<String>();
            for (int j = 0; j < constraintValues.length; j++) {
                String value = constraintValues[j].trim();
                value = value.replaceAll("'|\\(|\\)", "");
                values.add(value);
            }
            DbCheckConstraint dbCheckConstraint = new DbCheckConstraint();
            dbCheckConstraint.setConstraintName(name);
            dbCheckConstraint.setCheckClause(clause);
            dbCheckConstraint.setEnumName(enumName);
            dbCheckConstraint.setValues(values);
            dbCheckConstraint.setTable(relTable);
            dbCheckConstraint.setColumn(relCol);
            return dbCheckConstraint;
        }
        return null;
    }

    public static void main(String args[]) {
        parseH2("", "(TYPE IN(0, 1, 2))", "CONTACT");
    }

    static final Pattern ORACLE_CHECK = Pattern
            .compile("(?i)(.*\\s*IS NULL OR\\s*\\()?([^\\)]*)\\s*IN\\s*\\(('?.*?'?,'?.*?'?)\\)\\)*");

    public static DbCheckConstraint parseOracle(String name, String clause, String relTable) {
        // GENDER IN ('M','F')
        // TYPE IN (0, 1)
        // TABLE_NAME is null or (TABLE_NAME in ('O','P',''))
        Matcher matcher = ORACLE_CHECK.matcher(clause);
        if (matcher.matches()) {
            String relCol = matcher.group(2).trim();
            String enumName = relTable + "_" + relCol;
            String[] constraintValues = matcher.group(3).trim().split(",");
            List<String> values = new ArrayList<String>();
            for (int j = 0; j < constraintValues.length; j++) {
                String value = constraintValues[j].trim();
                value = value.replaceAll("'", "");
                values.add(value);
            }
            DbCheckConstraint dbCheckConstraint = new DbCheckConstraint();
            dbCheckConstraint.setConstraintName(name);
            dbCheckConstraint.setCheckClause(clause);
            dbCheckConstraint.setEnumName(enumName);
            dbCheckConstraint.setValues(values);
            dbCheckConstraint.setTable(relTable);
            dbCheckConstraint.setColumn(relCol);
            return dbCheckConstraint;
        }
        return null;
    }

    static final Pattern INFORMIX_CHECK = Pattern.compile("(?i)\\((.*)\\s*IN\\s*\\(('?.*?'?\\s*,'?.*?'?\\s*)\\)*");

    public static DbCheckConstraint parseInformix(String name, String clause, String relTable) {
        // (type IN (0 ,1 ))
        // (gender IN ('M' ,'F' ))
        Matcher matcher = INFORMIX_CHECK.matcher(clause.trim());
        if (matcher.matches()) {
            String relCol = matcher.group(1).trim();
            String enumName = relTable + "_" + relCol;
            String[] constraintValues = matcher.group(2).trim().split(",");
            List<String> values = new ArrayList<String>();
            for (int j = 0; j < constraintValues.length; j++) {
                String value = constraintValues[j].trim();
                value = value.replaceAll("'", "");
                values.add(value);
            }
            DbCheckConstraint dbCheckConstraint = new DbCheckConstraint();
            dbCheckConstraint.setConstraintName(name);
            dbCheckConstraint.setCheckClause(clause);
            dbCheckConstraint.setEnumName(enumName);
            dbCheckConstraint.setValues(values);
            dbCheckConstraint.setTable(relTable);
            dbCheckConstraint.setColumn(relCol);
            return dbCheckConstraint;
        }
        return null;
    }

    static final Pattern DB2_CHECK = Pattern.compile("(?i).*IN\\s*\\(('?.*?'?\\s*,'?.*?'?\\s*)\\)*");

    public static DbCheckConstraint parseDb2(String relCol, String clause, String relTable) {
        // TYPE IN (0, 1)
        // GENDER IN ('M', 'F')
        Matcher matcher = DB2_CHECK.matcher(clause.trim());
        if (matcher.matches()) {
            String enumName = relTable + "_" + relCol;
            String[] constraintValues = matcher.group(1).trim().split(",");
            List<String> values = new ArrayList<String>();
            for (int j = 0; j < constraintValues.length; j++) {
                String value = constraintValues[j].trim();
                int ix = value.indexOf("'");
                if (ix >= 0)
                    value = value.substring(ix + 1);
                ix = value.indexOf("'");
                if (ix >= 0)
                    value = value.substring(0, ix);
                values.add(value);
            }
            DbCheckConstraint dbCheckConstraint = new DbCheckConstraint();
            dbCheckConstraint.setConstraintName(enumName);
            dbCheckConstraint.setCheckClause(clause);
            dbCheckConstraint.setEnumName(enumName);
            dbCheckConstraint.setValues(values);
            dbCheckConstraint.setTable(relTable);
            dbCheckConstraint.setColumn(relCol);
            return dbCheckConstraint;
        }
        return null;
    }

    static final Pattern MYSQL_CHECK = Pattern.compile("(?i)\\s*enum\\((.*)\\)\\s*");

    public static DbCheckConstraint parseMysql(String relCol, String clause, String relTable) {
        // enum('0','1','2')
        // enum('M','F','0')
        Matcher matcher = MYSQL_CHECK.matcher(clause.trim());
        if (matcher.matches()) {
            String enumName = relTable + "_" + relCol;
            String[] constraintValues = matcher.group(1).trim().split(",");
            List<String> values = new ArrayList<String>();
            for (int j = 0; j < constraintValues.length; j++) {
                String value = constraintValues[j].trim();
                value = value.replaceAll("'", "");
                values.add(value);
            }
            DbCheckConstraint dbCheckConstraint = new DbCheckConstraint();
            dbCheckConstraint.setConstraintName(enumName);
            dbCheckConstraint.setCheckClause(clause);
            dbCheckConstraint.setEnumName(enumName);
            dbCheckConstraint.setValues(values);
            dbCheckConstraint.setTable(relTable);
            dbCheckConstraint.setColumn(relCol);
            return dbCheckConstraint;
        }
        return null;
    }

    static final Pattern POSTGRESQL_CHECK = Pattern.compile("(?i).*ARRAY\\[(.*?)\\].*");

    public static DbCheckConstraint parsePostgresql(String relCol, String clause, String relTable) {
        // ((ctype = ANY (ARRAY[0, 1, 2])))
        // (((gender)::text = ANY ((ARRAY['M'::character varying, 'F'::character varying, '0'::character
        // varying])::text[])))
        List<String> values = new ArrayList<String>();
        String enumName = relTable + "_" + relCol;
        Matcher matcher = POSTGRESQL_CHECK.matcher(clause.trim());
        if (matcher.matches()) {
            String[] constraintValues = matcher.group(1).trim().split(",");
            for (int j = 0; j < constraintValues.length; j++) {
                String value = constraintValues[j].trim();
                int ix = value.indexOf("'");
                if (ix >= 0)
                    value = value.substring(ix + 1);
                ix = value.indexOf("'");
                if (ix >= 0)
                    value = value.substring(0, ix);
                values.add(value);
            }
        }
        if (!values.isEmpty()) {
            DbCheckConstraint dbCheckConstraint = new DbCheckConstraint();
            dbCheckConstraint.setConstraintName(enumName);
            dbCheckConstraint.setCheckClause(clause);
            dbCheckConstraint.setEnumName(enumName);
            dbCheckConstraint.setValues(values);
            dbCheckConstraint.setTable(relTable);
            dbCheckConstraint.setColumn(relCol);
            return dbCheckConstraint;
        }
        return null;
    }

    static final Pattern MSSQL_CHECK = Pattern.compile("(?i)\\s*\\((.*)\\)\\s*");

    public static DbCheckConstraint parseMssql(String name, String clause, String relTable) {
        // PERSON CK__PERSON__GENDER__014935CB ([GENDER]='0' OR [GENDER]='F' OR [GENDER]='M')
        // CONTACT CK__CONTACT__CTYPE__0EA330E9 ([CTYPE]=(2) OR [CTYPE]=(1) OR [CTYPE]=(0)) return null;
        Matcher matcher = MSSQL_CHECK.matcher(clause.trim());
        if (matcher.matches()) {
            String[] constraintValues = matcher.group(1).trim().split("OR");
            String relCol = null;
            List<String> values = new ArrayList<String>();
            for (int j = 0; j < constraintValues.length; j++) {
                String[] values2 = constraintValues[j].trim().split("=");
                if (relCol == null) {
                    relCol = values2[0];
                    if (relCol.startsWith("["))
                        relCol = relCol.substring(1);
                    if (relCol.endsWith("]"))
                        relCol = relCol.substring(0, relCol.length() - 1);
                }
                String value = values2[1];
                if (value.startsWith("("))
                    value = value.substring(1);
                if (value.endsWith(")"))
                    value = value.substring(0, value.length() - 1);
                if (value.startsWith("'"))
                    value = value.substring(1);
                if (value.endsWith("'"))
                    value = value.substring(0, value.length() - 1);
                values.add(value);
            }
            String enumName = relTable + "_" + relCol;
            DbCheckConstraint dbCheckConstraint = new DbCheckConstraint();
            dbCheckConstraint.setConstraintName(name);
            dbCheckConstraint.setCheckClause(clause);
            dbCheckConstraint.setEnumName(enumName);
            dbCheckConstraint.setValues(values);
            dbCheckConstraint.setTable(relTable);
            dbCheckConstraint.setColumn(relCol);
            return dbCheckConstraint;
        }
        return null;
    }

    @Override
    public String toString() {
        return "DbCheckConstraint [constraintName=" + constraintName + ", checkClause=" + checkClause + ", enumName="
                + enumName + ", values=" + values + ", table=" + table + ", column=" + column + "]";
    }
}
