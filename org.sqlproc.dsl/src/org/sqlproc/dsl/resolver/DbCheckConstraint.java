package org.sqlproc.dsl.resolver;

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
    static final Pattern ORACLE_CHECK = Pattern
            .compile("(?i)(.*\\s*IS NULL OR\\s*\\()?([^\\)]*)\\s*IN\\s*\\(('?.*?'?,'?.*?'?)\\)\\)*");

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

    public static void main(String[] args) {
        Matcher matcher = INFORMIX_CHECK.matcher("(type IN (0 ,1 ))  ");
        if (matcher.matches()) {
            String relCol = matcher.group(1).trim();
            System.out.println("1 " + relCol);
            String[] constraintValues = matcher.group(2).trim().split(",");
            List<String> values = new ArrayList<String>();
            for (int j = 0; j < constraintValues.length; j++) {
                String value = constraintValues[j].trim();
                value = value.replaceAll("'", "");
                values.add(value);
            }
            System.out.println("9 " + values);
        }
    }

    @Override
    public String toString() {
        return "DbCheckConstraint [constraintName=" + constraintName + ", checkClause=" + checkClause + ", enumName="
                + enumName + ", values=" + values + ", table=" + table + ", column=" + column + "]";
    }
}