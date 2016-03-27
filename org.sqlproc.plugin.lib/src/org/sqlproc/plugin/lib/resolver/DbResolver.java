package org.sqlproc.plugin.lib.resolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.sqlproc.plugin.lib.property.ModelProperty;
import org.sqlproc.plugin.lib.resolver.DbResolverBean.DatabaseDirectives;

public interface DbResolver {

    public static final String IGNORE = "@Ignore";

    public enum DbType {
        ORACLE("Oracle"), MY_SQL("MySQL"), HSQLDB("HSQLDB"), POSTGRESQL("PostgreSQL"), INFORMIX("Informix"), DB2(
                "DB2"), MS_SQL("MSSQL"), H2("H2");
        private static Map<String, DbType> identifierMap = new HashMap<String, DbType>();

        static {
            for (DbType value : DbType.values()) {
                identifierMap.put(value.getValue(), value);
            }
        }

        private String value;

        private DbType(String value) {
            this.value = value;
        }

        public static DbType fromValue(String value) {
            DbType result = identifierMap.get(value);
            if (result == null) {
                throw new IllegalArgumentException("No DbType for value: " + value);
            }
            return result;
        }

        public static DbType[] fromDbMetaInfo(String dbMetaInfo) {
            if (dbMetaInfo == null)
                return values();
            List<DbType> result = new ArrayList<DbType>();
            String info = dbMetaInfo.toUpperCase();
            for (DbType dbType : values()) {
                if (info.indexOf(dbType.toString().toUpperCase()) >= 0
                        || info.indexOf(dbType.getValue().toUpperCase()) >= 0)
                    result.add(dbType);
                else if (dbType == DbType.HSQLDB && info.indexOf("HSQL") >= 0)
                    result.add(dbType);
                else if (dbType == DbType.MS_SQL && info.indexOf("MICROSOFT") >= 0)
                    result.add(dbType);
            }
            if (!result.isEmpty())
                return result.toArray(new DbType[0]);
            else
                return values();
        }

        public String getValue() {
            return value;
        }
    }

    boolean isResolveDb(EObject model);

    DatabaseDirectives getDatabaseDirectives(EObject model);

    List<String> getTables(EObject model);

    boolean checkTable(EObject model, String table);

    List<String> getColumns(EObject model, String table);

    boolean checkColumn(EObject model, String table, String column);

    List<DbColumn> getDbColumns(EObject model, String table);

    List<DbExport> getDbExports(EObject model, String table);

    List<DbImport> getDbImports(EObject model, String table);

    List<String> getDbPrimaryKeys(EObject model, String table);

    String getType(EObject model, String table, String column);

    List<DbIndex> getDbIndexes(EObject model, String table);

    List<String> getSequences(EObject model);

    String getDbMetaInfo(EObject model);

    String getDbDriverInfo(EObject model);

    String getDbJdbcInfo(EObject model);

    List<String> getCatalogs(EObject model);

    List<String> getSchemas(EObject model);

    Set<String> getDriverMethods(EObject model);

    Object getDriverMethodOutput(EObject model, String methodName);

    List<String> getProcedures(EObject model);

    List<String> getFunctions(EObject model);

    List<String> getProcColumns(EObject model, String table);

    boolean checkProcColumn(EObject model, String table, String column);

    List<String> getFunColumns(EObject model, String table);

    boolean checkFunColumn(EObject model, String table, String column);

    List<DbColumn> getDbProcColumns(EObject model, String procedure);

    List<DbColumn> getDbFunColumns(EObject model, String function);

    List<DbTable> getDbTables(EObject model, String table);

    List<DbTable> getDbProcedures(EObject model, String table);

    List<DbTable> getDbFunctions(EObject model, String table);

    boolean checkProcedure(EObject model, String table);

    boolean checkFunction(EObject model, String function);

    List<DbCheckConstraint> getDbCheckConstraints(EObject model, String table);

    List<String> getCheckConstraints(EObject model);

    List<String> getCheckColumns(EObject model, String table);

    String getProcType(EObject model, String procedure, String column);

    String getFunType(EObject model, String function, String column);

    boolean checkTableName(EObject model, String table);

    void init(ModelProperty modelProperty, Class<?> driverClass, String dbSqlsBefore, String dbSqlsAfter);
}
