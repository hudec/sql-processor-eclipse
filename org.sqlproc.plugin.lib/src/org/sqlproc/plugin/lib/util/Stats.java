package org.sqlproc.plugin.lib.util;

public class Stats {
    public int tables;
    public int columns;
    public int primaryKeys;
    public int exports;
    public int imports;
    public int indexes;
    public int checkConstraints;
    public int procedures;
    public int procColumns;
    public int functions;
    public int funColumns;

    @Override
    public String toString() {
        return "\n// Model statistics\n// Tables=" + tables + "\n// Columns=" + columns + "\n// Primary Keys="
                + primaryKeys + "\n// Exports=" + exports + "\n// Imports=" + imports + "\n// Indexes=" + indexes
                + "\n// Check Constraints=" + checkConstraints + "\n// Procedures=" + procedures
                + "\n// Procedures Columns=" + procColumns + "\n// Functions=" + functions + "\n// Functions Columns="
                + funColumns + "\n";
    }
}
