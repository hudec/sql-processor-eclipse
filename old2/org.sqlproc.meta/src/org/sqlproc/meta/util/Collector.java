package org.sqlproc.meta.util;

import java.util.Set;

import org.sqlproc.meta.processorMeta.Column;
import org.sqlproc.meta.processorMeta.Constant;
import org.sqlproc.meta.processorMeta.DatabaseColumn;
import org.sqlproc.meta.processorMeta.Identifier;
import org.sqlproc.meta.processorMeta.IfSql;
import org.sqlproc.meta.processorMeta.IfSqlBool;
import org.sqlproc.meta.processorMeta.IfSqlCond;
import org.sqlproc.meta.processorMeta.IfSqlFragment;
import org.sqlproc.meta.processorMeta.MappingColumn;
import org.sqlproc.meta.processorMeta.MappingItem;
import org.sqlproc.meta.processorMeta.MappingRule;
import org.sqlproc.meta.processorMeta.MetaStatement;
import org.sqlproc.meta.processorMeta.OrdSql2;
import org.sqlproc.meta.processorMeta.SqlFragment;

public class Collector {

    public static void allVariables(MetaStatement metaStatement, Set<Identifier> identifiers, Set<Constant> constants,
            Set<Column> columns, Set<DatabaseColumn> databaseColumns) {

        if (metaStatement.getStatement() != null && metaStatement.getStatement().getSqls() != null) {
            for (SqlFragment sqlFragment : metaStatement.getStatement().getSqls()) {
                if (sqlFragment.getIdent() != null) {
                    identifiers.add(sqlFragment.getIdent());
                } else if (sqlFragment.getCnst() != null) {
                    constants.add(sqlFragment.getCnst());
                } else if (sqlFragment.getCol() != null) {
                    columns.add(sqlFragment.getCol());
                } else if (sqlFragment.getDbcol() != null) {
                    databaseColumns.add(sqlFragment.getDbcol());
                } else if (sqlFragment.getMeta() != null) {
                    if (sqlFragment.getMeta().getIfs() != null) {
                        for (IfSql ifSql : sqlFragment.getMeta().getIfs()) {
                            allVariables(ifSql, identifiers, constants, columns, databaseColumns);
                        }
                    }
                    if (sqlFragment.getMeta().getCond() != null) {
                        allVariables(sqlFragment.getMeta().getCond(), identifiers, constants, columns, databaseColumns);
                    }
                    if (sqlFragment.getMeta().getOrd() != null && sqlFragment.getMeta().getOrd().getSqls() != null) {
                        for (OrdSql2 ordSql2 : sqlFragment.getMeta().getOrd().getSqls()) {
                            if (ordSql2.getIdent() != null) {
                                identifiers.add(sqlFragment.getIdent());
                            } else if (ordSql2.getCnst() != null) {
                                constants.add(sqlFragment.getCnst());
                            } else if (ordSql2.getDbcol() != null) {
                                databaseColumns.add(ordSql2.getDbcol());
                            }
                        }
                    }
                }
            }
        }
    }

    public static void allVariables(IfSql ifSql, Set<Identifier> identifiers, Set<Constant> constants,
            Set<Column> columns, Set<DatabaseColumn> databaseColumns) {

        if (ifSql.getSqls() != null) {
            for (IfSqlFragment ifSqlFragment : ifSql.getSqls()) {
                if (ifSqlFragment.getIdent() != null) {
                    identifiers.add(ifSqlFragment.getIdent());
                } else if (ifSqlFragment.getCnst() != null) {
                    constants.add(ifSqlFragment.getCnst());
                } else if (ifSqlFragment.getCol() != null) {
                    columns.add(ifSqlFragment.getCol());
                } else if (ifSqlFragment.getDbcol() != null) {
                    databaseColumns.add(ifSqlFragment.getDbcol());
                } else if (ifSqlFragment.getMeta() != null) {
                    if (ifSqlFragment.getMeta().getCond() != null) {
                        allVariables(ifSqlFragment.getMeta().getCond(), identifiers, constants, columns,
                                databaseColumns);
                    } else if (ifSqlFragment.getMeta().getIfs() != null) {
                        for (IfSql ifSql2 : ifSqlFragment.getMeta().getIfs()) {
                            allVariables(ifSql2, identifiers, constants, columns, databaseColumns);
                        }
                    }
                }
            }
        }
    }

    public static void allVariables(IfSqlCond ifSqlCond, Set<Identifier> identifiers, Set<Constant> constants,
            Set<Column> columns, Set<DatabaseColumn> databaseColumns) {

        if (ifSqlCond.getBool1() != null) {
            if (ifSqlCond.getBool1().getIdent() != null) {
                identifiers.add(ifSqlCond.getBool1().getIdent());
            } else if (ifSqlCond.getBool1().getCnst() != null) {
                constants.add(ifSqlCond.getBool1().getCnst());
            } else if (ifSqlCond.getBool1().getCond() != null) {
                allVariables(ifSqlCond.getBool1().getCond(), identifiers, constants, columns, databaseColumns);
            }
        }
        if (ifSqlCond.getBool2() != null) {
            for (IfSqlBool ifSqlBool : ifSqlCond.getBool2()) {
                if (ifSqlBool.getIdent() != null) {
                    identifiers.add(ifSqlBool.getIdent());
                } else if (ifSqlBool.getCnst() != null) {
                    constants.add(ifSqlBool.getCnst());
                } else if (ifSqlBool.getCond() != null) {
                    allVariables(ifSqlBool.getCond(), identifiers, constants, columns, databaseColumns);
                }
            }
        }
    }

    public static void allVariables(MappingRule mappingRule, Set<MappingColumn> columns) {

        if (mappingRule.getMapping() != null && mappingRule.getMapping().getMappingItems() != null) {
            for (MappingItem mappingItem : mappingRule.getMapping().getMappingItems()) {
                if (mappingItem.getAttr() != null) {
                    columns.add(mappingItem.getAttr());
                }
            }
        }
    }
}
