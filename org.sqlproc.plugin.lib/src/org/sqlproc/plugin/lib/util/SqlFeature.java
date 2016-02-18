package org.sqlproc.plugin.lib.util;

public interface SqlFeature {

    public static final String SEQ = "SEQ";
    /**
     * <code>HSQLDB_DEFAULT_SEQ</code> is the default value related to the key <code>SEQ</code> in the case the filter
     * value <code>HSQLDB</code> is used for the {@link SqlProcessorLoader} instance creation.
     */
    public static final String HSQLDB_DEFAULT_SEQ = "call next value for $n";
    /**
     * <code>MYSQL_DEFAULT_SEQ</code> is the default value related to the key <code>SEQ</code> in the case the filter
     * value <code>MYSQL</code> is used for the {@link SqlProcessorLoader} instance creation.
     */
    public static final String MYSQL_DEFAULT_SEQ = "select auto_increment from information_schema.tables where table_name = '$t'";
    /**
     * <code>ORACLE_DEFAULT_SEQ</code> is the default value related to the key <code>SEQ</code> in the case the filter
     * value <code>ORACLE</code> is used for the {@link SqlProcessorLoader} instance creation.
     */
    public static final String ORACLE_DEFAULT_SEQ = "select $n.nextval from dual";
    /**
     * <code>POSTGRESQL_DEFAULT_SEQ</code> is the default value related to the key <code>SEQ</code> in the case the
     * filter value <code>POSTGRESQL</code> is used for the {@link SqlProcessorLoader} instance creation.
     */
    public static final String POSTGRESQL_DEFAULT_SEQ = "select nextval('$n')";
    /**
     * <code>INFORMIX_DEFAULT_SEQ</code> is the default value related to the key <code>SEQ</code> in the case the filter
     * value <code>INFORMIX</code> is used for the {@link SqlProcessorLoader} instance creation.
     */
    public static final String INFORMIX_DEFAULT_SEQ = "SELECT FIRST 1 $n.NEXTVAL FROM systables";
    /**
     * <code>DB2_DEFAULT_SEQ</code> is the default value related to the key <code>SEQ</code> in the case the filter
     * value <code>DB2</code> is used for the {@link SqlProcessorLoader} instance creation.
     */
    public static final String DB2_DEFAULT_SEQ = "values nextval for $n";

    /**
     * <code>DEFAULT_SEQ_NAME</code> is the default sequence name.
     */
    public static final String DEFAULT_SEQ_NAME = "SQLPROC_SEQUENCE";
    /**
     * <code>IDSEL</code> is the key for the SQL query pattern used to obtain the value of identities after the INSERT
     * command.
     */
    public static final String IDSEL = "IDSEL";
    /**
     * <code>IDSEL_JDBC</code> is the special value related to the key <code>IDSEL</code> indicating that the generated
     * identity value is determined using JDBC capabilities to return generated keys for SQL statement. This feature
     * requires that both the database and the JDBC driver support it.
     */
    public static final String IDSEL_JDBC = "JDBC";
    /**
     * <code>HSQLDB_DEFAULT_IDSEL</code> is the default value related to the key <code>IDSEL</code> in the case the
     * filter value <code>HSQLDB</code> is used for the {@link SqlProcessorLoader} instance creation.
     */
    public static final String HSQLDB_DEFAULT_IDSEL = "call identity()";
    /**
     * <code>MYSQL_DEFAULT_IDSEL</code> is the default value related to the key <code>IDSEL</code> in the case the
     * filter value <code>MYSQL</code> is used for the {@link SqlProcessorLoader} instance creation.
     */
    public static final String MYSQL_DEFAULT_IDSEL = "select last_insert_id()";
    /**
     * <code>POSTGRESQL_DEFAULT_IDSEL</code> is the default value related to the key <code>IDSEL</code> in the case the
     * filter value <code>POSTGRESQL</code> is used for the {@link SqlProcessorLoader} instance creation.
     */
    public static final String POSTGRESQL_DEFAULT_IDSEL = "select currval(pg_get_serial_sequence('$t','$c'))";
    /**
     * <code>INFORMIX_DEFAULT_IDSEL</code> is the default value related to the key <code>IDSEL</code> in the case the
     * filter value <code>INFORMIX</code> is used for the {@link SqlProcessorLoader} instance creation.
     */
    public static final String INFORMIX_DEFAULT_IDSEL = "SELECT FIRST 1 dbinfo('bigserial') FROM systables";
    /**
     * <code>INFORMIX_DEFAULT_IDSEL_Long</code> is the default value related to the key <code>IDSEL</code> in the case
     * the filter value <code>INFORMIX</code> is used for the {@link SqlProcessorLoader} instance creation.
     */
    // public static final String INFORMIX_DEFAULT_IDSEL_Long =
    // "select dbinfo('serial8') from informix.systables where tabid=1";
    public static final String INFORMIX_DEFAULT_IDSEL_Long = "select dbinfo('bigserial') from informix.systables where tabid=1";
    /**
     * <code>INFORMIX_DEFAULT_IDSEL_Integer</code> is the default value related to the key <code>IDSEL</code> in the
     * case the filter value <code>INFORMIX</code> is used for the {@link SqlProcessorLoader} instance creation.
     */
    public static final String INFORMIX_DEFAULT_IDSEL_Integer = "select dbinfo('sqlca.sqlerrd1') from informix.systables where tabid=1";
    /**
     * <code>MSSQL_DEFAULT_IDSEL</code> is the default value related to the key <code>IDSEL</code> in the case the
     * filter value <code>MSSQL</code> is used for the {@link SqlProcessorLoader} instance creation.
     * <p/>
     * The default value is {@link #IDSEL_JDBC}.
     */
    public static final String MSSQL_DEFAULT_IDSEL = IDSEL_JDBC;
    /**
     * /** <code>DB2_DEFAULT_IDSEL</code> is the default value related to the key <code>IDSEL</code> in the case the
     * filter value <code>DB2</code> is used for the {@link SqlProcessorLoader} instance creation.
     */
    public static final String DB2_DEFAULT_IDSEL = "SELECT identity_val_local() FROM SYSIBM.DUAL";
}
