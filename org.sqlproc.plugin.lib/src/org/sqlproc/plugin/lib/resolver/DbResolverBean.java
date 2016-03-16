package org.sqlproc.plugin.lib.resolver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.util.StringInputStream;
import org.sqlproc.plugin.lib.property.ModelProperty;
import org.sqlproc.plugin.lib.property.PojoEntityType;
import org.sqlproc.plugin.lib.util.Debug;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DbResolverBean implements DbResolver {

    protected Logger LOGGER = Logger.getLogger(DbResolverBean.class);
    private Debug debug = new Debug(LOGGER);

    public static class DatabaseDirectives {
        public String dbDriver;
        public PojoEntityType dbDriverPojo;
        public String dbUrl;
        public String dbUsername;
        public String dbPassword;
        public String dbCatalog;
        public String dbSchema;
        public String dbSqlsBefore;
        public String dbSqlsAfter;
        public String dbIndexTypes;
        public boolean dbSkipIndexes;
        public boolean dbSkipProcedures;
        public boolean dbSkipCheckConstraints;
        public boolean dbTakeComments;
        public boolean dbLowercaseNames;
        public boolean dbUppercaseNames;
        public DbType dbType;
        public String dir;
        public Connection connection;
        boolean doReconnect;
        public List<String> ddlsBefore0;
        public List<String> ddlsBefore1;
        public List<String> ddlsAfter;
        public Set<Short> indexTypes;
        public boolean newPojoValidator;

        @Override
        public String toString() {
            return "DatabaseValues [dbDriver=" + dbDriver + ", dbDriverPojo=" + dbDriverPojo + ", dbUrl=" + dbUrl
                    + ", dbUsername=" + dbUsername + ", dbCatalog=" + dbCatalog + ", dbSchema=" + dbSchema
                    + ", dbSqlsBefore=" + dbSqlsBefore + ", dbSqlsAfter=" + dbSqlsAfter + ", connection=" + connection
                    + ", dbIndexTypes=" + dbIndexTypes + ", dbSkipIndexes=" + dbSkipIndexes + ", dbSkipProcedures="
                    + dbSkipProcedures + ", dbSkipCheckConstraints=" + dbSkipCheckConstraints + ", dbTakeComments="
                    + dbTakeComments + ", dbLowercaseNames=" + dbLowercaseNames + ", dbUppercaseNames="
                    + dbUppercaseNames + ", dbType=" + dbType + ", dir=" + dir + "]";
        }

    }

    @Inject
    ModelProperty modelProperty;

    @Inject
    PojoResolverFactory pojoResolverFactory;

    @Inject
    ProcessorClassLoader processorClassLoader;

    private Class<?> driverClass = null;
    private String dbSqlsBefore = null;
    private String dbSqlsAfter = null;

    private final Object sync = new Object();

    private final Map<String, DatabaseDirectives> connections = Collections
            .synchronizedMap(new HashMap<String, DatabaseDirectives>());

    private final Map<String, List<String>> catalogs = Collections.synchronizedMap(new HashMap<String, List<String>>());
    private final Map<String, List<String>> schemas = Collections.synchronizedMap(new HashMap<String, List<String>>());
    private final Map<String, List<String>> tables = Collections.synchronizedMap(new HashMap<String, List<String>>());
    private final Map<String, List<String>> procedures = Collections
            .synchronizedMap(new HashMap<String, List<String>>());
    private final Map<String, List<String>> functions = Collections
            .synchronizedMap(new HashMap<String, List<String>>());
    private final Map<String, List<String>> checkConstraints = Collections
            .synchronizedMap(new HashMap<String, List<String>>());
    private final Map<String, Map<String, List<String>>> columns = Collections
            .synchronizedMap(new HashMap<String, Map<String, List<String>>>());
    private final Map<String, Map<String, List<String>>> checkColumns = Collections
            .synchronizedMap(new HashMap<String, Map<String, List<String>>>());
    private final Map<String, Map<String, List<String>>> procColumns = Collections
            .synchronizedMap(new HashMap<String, Map<String, List<String>>>());
    private final Map<String, Map<String, List<String>>> funColumns = Collections
            .synchronizedMap(new HashMap<String, Map<String, List<String>>>());
    private final Map<String, Map<String, List<DbTable>>> dbTables = Collections
            .synchronizedMap(new HashMap<String, Map<String, List<DbTable>>>());
    private final Map<String, Map<String, List<DbColumn>>> dbColumns = Collections
            .synchronizedMap(new HashMap<String, Map<String, List<DbColumn>>>());
    private final Map<String, Map<String, List<DbTable>>> dbProcedures = Collections
            .synchronizedMap(new HashMap<String, Map<String, List<DbTable>>>());
    private final Map<String, Map<String, List<DbColumn>>> dbProcColumns = Collections
            .synchronizedMap(new HashMap<String, Map<String, List<DbColumn>>>());
    private final Map<String, Map<String, List<DbTable>>> dbFunctions = Collections
            .synchronizedMap(new HashMap<String, Map<String, List<DbTable>>>());
    private final Map<String, Map<String, List<DbColumn>>> dbFunColumns = Collections
            .synchronizedMap(new HashMap<String, Map<String, List<DbColumn>>>());
    private final Map<String, Map<String, List<String>>> dbPrimaryKeys = Collections
            .synchronizedMap(new HashMap<String, Map<String, List<String>>>());
    private final Map<String, Map<String, List<DbExport>>> dbExports = Collections
            .synchronizedMap(new HashMap<String, Map<String, List<DbExport>>>());
    private final Map<String, Map<String, List<DbImport>>> dbImports = Collections
            .synchronizedMap(new HashMap<String, Map<String, List<DbImport>>>());
    private final Map<String, Map<String, List<DbIndex>>> dbIndexes = Collections
            .synchronizedMap(new HashMap<String, Map<String, List<DbIndex>>>());
    private final Map<String, List<String>> dbSequences = Collections
            .synchronizedMap(new HashMap<String, List<String>>());
    private final Map<String, SortedSet<String>> driverMethods = Collections
            .synchronizedMap(new HashMap<String, SortedSet<String>>());
    private final Map<String, Map<String, List<DbCheckConstraint>>> dbChecksConstraints = Collections
            .synchronizedMap(new HashMap<String, Map<String, List<DbCheckConstraint>>>());
    private final Map<String, Map<String, String>> dbOriginalNames = Collections
            .synchronizedMap(new HashMap<String, Map<String, String>>());

    public DbResolverBean() {
    }

    public DbResolverBean(ModelProperty modelProperty, Class<?> driverClass, String dbSqlsBefore, String dbSqlsAfter) {
        init(modelProperty, driverClass, dbSqlsBefore, dbSqlsAfter);
    }

    @Override
    public void init(ModelProperty modelProperty, Class<?> driverClass, String dbSqlsBefore, String dbSqlsAfter) {
        this.modelProperty = modelProperty;
        this.driverClass = driverClass;
        this.dbSqlsBefore = dbSqlsBefore;
        this.dbSqlsAfter = dbSqlsAfter;
    }

    private DatabaseDirectives checkReconnect(EObject model) {
        String m = "checkReconnect";
        debug.trace(m, ">>>");
        if (model == null)
            return null;
        String modelDir = modelProperty.getModelDir(model);
        DatabaseDirectives modelDatabaseValues = connections.get(modelDir);
        if (modelDatabaseValues == null) {
            modelDatabaseValues = new DatabaseDirectives();
            modelDatabaseValues.dir = modelDir;
            connections.put(modelDir, modelDatabaseValues);
        }

        boolean doResolveDb = modelProperty.isDoResolveDb(model);
        if (!doResolveDb) {
            debug.trace(m, "DB CLOSE");
            closeConnection(modelDatabaseValues);
            return null;
        }

        modelDatabaseValues.doReconnect = (modelDatabaseValues.connection != null) ? false : true;

        debug = new Debug(modelProperty.getDbDebugLevel(model), modelProperty.getDbDebugScope(model), LOGGER);

        modelDatabaseValues.newPojoValidator = !modelProperty.isOldPojoValidator(model);

        if (!modelDatabaseValues.newPojoValidator) {
            String dbDriver = modelProperty.getDbDriver(model);
            if (dbDriver != null) {
                if (!dbDriver.equals(modelDatabaseValues.dbDriver)) {
                    modelDatabaseValues.dbDriver = dbDriver;
                    modelDatabaseValues.doReconnect = true;
                }
            } else {
                modelDatabaseValues.dbDriver = null;
                closeConnection(modelDatabaseValues);
                return null;
            }
        } else {
            PojoEntityType dbDriverPojo = modelProperty.getDbDrivePojo(model);
            if (dbDriverPojo != null) {
                if (dbDriverPojo != modelDatabaseValues.dbDriverPojo) {
                    modelDatabaseValues.dbDriverPojo = dbDriverPojo;
                    modelDatabaseValues.doReconnect = true;
                }
            } else {
                modelDatabaseValues.dbDriverPojo = null;
                closeConnection(modelDatabaseValues);
                return null;
            }
        }
        String dbUrl = modelProperty.getDbUrl(model);
        if (dbUrl != null) {
            if (!dbUrl.equals(modelDatabaseValues.dbUrl)) {
                modelDatabaseValues.dbUrl = dbUrl;
                modelDatabaseValues.doReconnect = true;
            }
        } else {
            modelDatabaseValues.dbUrl = null;
            closeConnection(modelDatabaseValues);
            return null;
        }

        String dbUsername = modelProperty.getDbUsername(model);
        if (dbUsername != null) {
            if (!dbUsername.equals(modelDatabaseValues.dbUsername)) {
                modelDatabaseValues.dbUsername = dbUsername;
                modelDatabaseValues.doReconnect = true;
            }
        } else {
            modelDatabaseValues.dbUsername = null;
            closeConnection(modelDatabaseValues);
            return null;
        }
        String dbPassword = modelProperty.getDbPassword(model);
        if (dbPassword != null) {
            if (!dbPassword.equals(modelDatabaseValues.dbPassword)) {
                modelDatabaseValues.dbPassword = dbPassword;
                modelDatabaseValues.doReconnect = true;
            }
        } else {
            modelDatabaseValues.dbPassword = null;
            closeConnection(modelDatabaseValues);
            return null;
        }
        String dbCatalog = modelProperty.getDbCatalog(model);
        if (dbCatalog != null) {
            if (!dbCatalog.equals(modelDatabaseValues.dbCatalog)) {
                modelDatabaseValues.dbCatalog = dbCatalog;
                modelDatabaseValues.doReconnect = true;
            }
        } else
            modelDatabaseValues.dbCatalog = null;
        String dbSchema = modelProperty.getDbSchema(model);
        if (dbSchema != null) {
            if (!dbSchema.equals(modelDatabaseValues.dbSchema)) {
                modelDatabaseValues.dbSchema = dbSchema;
                modelDatabaseValues.doReconnect = true;
            }
        } else
            modelDatabaseValues.dbSchema = null;
        String dbSqlsBefore = modelProperty.getDbSqlsBefore(model);
        if (dbSqlsBefore != null) {
            if (!dbSqlsBefore.equals(modelDatabaseValues.dbSqlsBefore)) {
                modelDatabaseValues.dbSqlsBefore = dbSqlsBefore;
            }
        } else
            modelDatabaseValues.dbSqlsBefore = null;
        String dbSqlsAfter = modelProperty.getDbSqlsAfter(model);
        if (dbSqlsAfter != null) {
            if (!dbSqlsAfter.equals(modelDatabaseValues.dbSqlsAfter)) {
                modelDatabaseValues.dbSqlsAfter = dbSqlsAfter;
            }
        } else
            modelDatabaseValues.dbSqlsAfter = null;
        String dbIndexTypes = modelProperty.getDbIndexTypes(model);
        if (dbIndexTypes != null) {
            if (!dbIndexTypes.equals(modelDatabaseValues.dbIndexTypes)) {
                modelDatabaseValues.dbIndexTypes = dbIndexTypes;
                String[] ss = dbIndexTypes.split(",");
                modelDatabaseValues.indexTypes = new HashSet<Short>();
                for (String s : ss) {
                    if (s.trim().length() > 0) {
                        try {
                            Short type = Short.parseShort(s.trim());
                            modelDatabaseValues.indexTypes.add(type);
                        } catch (NumberFormatException ignore) {
                        }
                    }
                }
            }
        } else {
            modelDatabaseValues.dbIndexTypes = null;
            modelDatabaseValues.indexTypes = new HashSet<Short>();
            modelDatabaseValues.indexTypes.add((short) 1);
            modelDatabaseValues.indexTypes.add((short) 3);
        }
        boolean dbSkipIndexes = modelProperty.getDbSkipIndexes(model);
        if (dbSkipIndexes != modelDatabaseValues.dbSkipIndexes) {
            modelDatabaseValues.dbSkipIndexes = dbSkipIndexes;
        }
        boolean dbSkipCheckConstraints = modelProperty.getDbSkipCheckConstraints(model);
        if (dbSkipCheckConstraints != modelDatabaseValues.dbSkipCheckConstraints) {
            modelDatabaseValues.dbSkipCheckConstraints = dbSkipCheckConstraints;
        }
        boolean dbSkipProcedures = modelProperty.getDbSkipProcedures(model);
        if (dbSkipProcedures != modelDatabaseValues.dbSkipProcedures) {
            modelDatabaseValues.dbSkipProcedures = dbSkipProcedures;
        }
        boolean dbTakeComments = modelProperty.getDbTakeComments(model);
        if (dbTakeComments != modelDatabaseValues.dbTakeComments) {
            modelDatabaseValues.dbTakeComments = dbTakeComments;
        }
        boolean dbLowercaseNames = modelProperty.getDbLowercaseNames(model);
        if (dbLowercaseNames != modelDatabaseValues.dbLowercaseNames) {
            modelDatabaseValues.dbLowercaseNames = dbLowercaseNames;
        }
        boolean dbUppercaseNames = modelProperty.getDbUppercaseNames(model);
        if (dbUppercaseNames != modelDatabaseValues.dbUppercaseNames) {
            modelDatabaseValues.dbUppercaseNames = dbUppercaseNames;
        }
        String dbType = modelProperty.getDbType(model);
        if (dbType != null) {
            if (modelDatabaseValues.dbType == null || !dbType.equals(modelDatabaseValues.dbType.getValue())) {
                modelDatabaseValues.dbType = DbType.fromValue(dbType);
            }
        } else {
            modelDatabaseValues.dbType = null;
        }
        debug.trace(m, "<<<DB RECONNECT " + modelDatabaseValues.doReconnect);
        return modelDatabaseValues;
    }

    private DatabaseDirectives getConnection(EObject model) {
        String m = "getConnection";
        debug.trace(m, ">>>");
        DatabaseDirectives modelDatabaseValues = checkReconnect(model);
        if (modelDatabaseValues == null)
            return null;
        if (!modelDatabaseValues.doReconnect)
            return modelDatabaseValues;
        closeConnection(modelDatabaseValues);
        debug.trace(m, "DB OPEN");
        synchronized (sync) {
            debug.trace(m, "DATA START FOR " + modelDatabaseValues.dir);
            debug.info(m, "DATA START FOR " + modelDatabaseValues.toString());
            URI uri = (model.eResource() != null) ? model.eResource().getURI() : null;

            Class<?> driverClass = this.driverClass;
            if (driverClass == null) {
                boolean useNewPojoLoader = true;
                if (!modelDatabaseValues.newPojoValidator)
                    useNewPojoLoader = false;
                else if (modelDatabaseValues.dbDriverPojo == null)
                    useNewPojoLoader = false;
                else if (modelDatabaseValues.dbDriverPojo.getType() == null)
                    useNewPojoLoader = false;
                if (useNewPojoLoader) {
                    System.out.println(modelDatabaseValues.dbDriverPojo.getType().getQualifiedName());
                    driverClass = processorClassLoader.loadClass(modelDatabaseValues.dbDriverPojo.getType().eResource(),
                            modelDatabaseValues.dbDriverPojo.getType().getQualifiedName());
                } else {
                    driverClass = pojoResolverFactory.getPojoResolver()
                            .loadClass((modelDatabaseValues.dbDriverPojo != null)
                                    ? modelDatabaseValues.dbDriverPojo.getQualifiedName()
                                    : modelDatabaseValues.dbDriver, uri);
                }
            }
            debug.trace(m, "DATA DRIVER " + driverClass);
            if (driverClass != null && Driver.class.isAssignableFrom(driverClass)) {
                try {
                    Driver driver = (Driver) driverClass.newInstance();
                    Properties props = new Properties();
                    props.setProperty("user", modelDatabaseValues.dbUsername);
                    props.setProperty("password", modelDatabaseValues.dbPassword);
                    String dbUrl = modelDatabaseValues.dbUrl.replaceAll("\\\\/", "/").replaceAll("\\\\/", "/")
                            .replaceAll("\\\\;", ";").replaceAll("\\\\.", ".");
                    modelDatabaseValues.connection = driver.connect(dbUrl, props);
                    debug.trace(m, "DB URL " + dbUrl);
                    debug.trace(m, "DATA CONNECTION " + modelDatabaseValues.connection);

                    InputStream is = null;
                    if (this.dbSqlsBefore != null) {
                        is = new StringInputStream(this.dbSqlsBefore);
                    } else if (this.driverClass == null && modelDatabaseValues.dbSqlsBefore != null
                            && modelDatabaseValues.dbSqlsBefore.trim().length() > 0) {
                        if (modelDatabaseValues.newPojoValidator)
                            is = processorClassLoader.loadFile(model.eResource(),
                                    modelDatabaseValues.dbSqlsBefore.trim());
                        else
                            is = pojoResolverFactory.getPojoResolver().getFile(modelDatabaseValues.dbSqlsBefore.trim(),
                                    uri);
                    }
                    if (is != null) {
                        List<String> ddls = loadDDL(is);
                        modelDatabaseValues.ddlsBefore0 = new ArrayList<String>();
                        modelDatabaseValues.ddlsBefore1 = new ArrayList<String>();
                        boolean inDrop = true;
                        for (String ddl : ddls) {
                            if (inDrop && !ddl.toUpperCase().trim().startsWith("DROP"))
                                inDrop = false;
                            if (inDrop)
                                modelDatabaseValues.ddlsBefore0.add(ddl);
                            else
                                modelDatabaseValues.ddlsBefore1.add(ddl);
                        }

                        try {
                            runDDLs(modelDatabaseValues.connection, modelDatabaseValues.ddlsBefore0, "BEFORE");
                        } catch (SQLException e) {
                            debug.error("getConnection error " + e, e);
                        }
                        runDDLs(modelDatabaseValues.connection, modelDatabaseValues.ddlsBefore1, "BEFORE");
                    }
                    InputStream is2 = null;
                    if (this.dbSqlsAfter != null) {
                        is2 = new StringInputStream(this.dbSqlsBefore);
                    } else if (this.driverClass == null && modelDatabaseValues.dbSqlsAfter != null
                            && modelDatabaseValues.dbSqlsAfter.trim().length() > 0) {
                        if (modelDatabaseValues.newPojoValidator)
                            is2 = processorClassLoader.loadFile(model.eResource(),
                                    modelDatabaseValues.dbSqlsAfter.trim());
                        else
                            is2 = pojoResolverFactory.getPojoResolver().getFile(modelDatabaseValues.dbSqlsAfter.trim(),
                                    uri);
                    }
                    if (is2 != null) {
                        modelDatabaseValues.ddlsAfter = loadDDL(is);
                    }
                } catch (InstantiationException e) {
                    debug.error("getConnection error " + e, e);
                } catch (IllegalAccessException e) {
                    debug.error("getConnection error " + e, e);
                } catch (SQLException e) {
                    debug.error("getConnection error " + e, e);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
            debug.trace(m, "<<<");
            return modelDatabaseValues;
        }
    }

    private void closeConnection(DatabaseDirectives modelDatabaseValues) {
        String m = "closeConnection";
        debug.trace(m, ">>>");
        synchronized (sync) {
            try {
                if (modelDatabaseValues.connection != null) {
                    if (modelDatabaseValues.ddlsAfter != null)
                        runDDLs(modelDatabaseValues.connection, modelDatabaseValues.ddlsAfter, "AFTER");

                    debug.trace(m, "DATA STOP FOR " + modelDatabaseValues.dir);
                    modelDatabaseValues.connection.close();
                }
            } catch (SQLException e) {
                debug.error("closeConnection error " + e, e);
            }
            modelDatabaseValues.connection = null;
            modelDatabaseValues.ddlsBefore0 = null;
            modelDatabaseValues.ddlsBefore1 = null;
            modelDatabaseValues.ddlsAfter = null;
            catalogs.remove(modelDatabaseValues.dir);
            schemas.remove(modelDatabaseValues.dir);
            tables.remove(modelDatabaseValues.dir);
            columns.remove(modelDatabaseValues.dir);
            procColumns.remove(modelDatabaseValues.dir);
            funColumns.remove(modelDatabaseValues.dir);
            dbTables.remove(modelDatabaseValues.dir);
            dbColumns.remove(modelDatabaseValues.dir);
            dbProcedures.remove(modelDatabaseValues.dir);
            dbProcColumns.remove(modelDatabaseValues.dir);
            dbFunctions.remove(modelDatabaseValues.dir);
            dbFunColumns.remove(modelDatabaseValues.dir);
            dbPrimaryKeys.remove(modelDatabaseValues.dir);
            dbExports.remove(modelDatabaseValues.dir);
            dbImports.remove(modelDatabaseValues.dir);
            dbIndexes.remove(modelDatabaseValues.dir);
            dbSequences.remove(modelDatabaseValues.dir);
            driverMethods.remove(modelDatabaseValues.dir);
            procedures.remove(modelDatabaseValues.dir);
            functions.remove(modelDatabaseValues.dir);
            dbChecksConstraints.remove(modelDatabaseValues.dir);
        }
        debug.trace(m, "<<<");
    }

    private void runDDLs(Connection connection, List<String> ddls, String msg) throws SQLException {
        String m = "runDDLs";
        debug.trace(m, ">>>");

        if (ddls == null || ddls.isEmpty())
            return;

        debug.trace(m, "Run DDLs " + msg + ", number of statements is " + ddls.size());

        Statement stmt = null;
        int[] result = null;

        try {
            stmt = connection.createStatement();
            for (int i = 0, n = ddls.size(); i < n; i++) {
                String ddl = ddls.get(i);
                if (ddl == null)
                    continue;
                debug.trace(m, "DB DDL " + ddl);
                stmt.addBatch(ddl);
            }
            result = stmt.executeBatch();

        } catch (BatchUpdateException e) {
            debug.error("Run DDLs chyba " + e, e);

        } finally {
            if (stmt != null)
                stmt.close();
        }

        debug.trace(m, "Run DDLs OK for " + ((result != null) ? result.length : -1));
        debug.trace(m, "<<<");
    }

    private List<String> loadDDL(InputStream is) {
        String m = "loadDDL";
        debug.trace(m, ">>>");
        BufferedReader bfr = null;
        List<String> ddls = new ArrayList<String>();

        if (is == null)
            return ddls;

        try {
            bfr = new BufferedReader(new InputStreamReader(is));
            StringBuilder completeLine = new StringBuilder("");
            String line;
            boolean inFuncOrProc = false;
            while ((line = bfr.readLine()) != null) {
                if (line.startsWith("--"))
                    continue;
                if (line.trim().length() > 0)
                    completeLine.append(" ").append(line);
                if (!inFuncOrProc && line.trim().toUpperCase().startsWith("CREATE")
                        && (line.indexOf("FUNCTION") >= 0 || line.indexOf("PROCEDURE") >= 0))
                    inFuncOrProc = true;
                boolean finishedDdl = false;
                if (!inFuncOrProc && line.trim().endsWith(";")) {
                    finishedDdl = true;
                } else if (line.trim().length() == 0) {
                    finishedDdl = true;
                }
                if (finishedDdl) {
                    if (completeLine.length() > 0)
                        ddls.add(completeLine.toString());
                    completeLine = new StringBuilder("");
                    inFuncOrProc = false;
                }
            }
            if (completeLine.length() > 0)
                ddls.add(completeLine.toString());
            debug.trace(m, "<<<");
            return ddls;

        } catch (Exception ex) {
            debug.error("loadDDL error", ex);
            return ddls;
        } finally {
            if (bfr != null) {
                try {
                    bfr.close();
                } catch (Exception ignore) {
                }
            }
        }
    }

    @Override
    public boolean isResolveDb(EObject model) {
        String m = "isResolveDb";
        debug.trace(m, ">>><<<");
        return getConnection(model) != null;
    }

    @Override
    public DatabaseDirectives getDatabaseDirectives(EObject model) {
        String m = "getDatabaseDirectives";
        debug.trace(m, ">>><<<");
        return getConnection(model);
    }

    @Override
    public List<String> getCatalogs(EObject model) {
        String m = "getCatalogs";
        debug.debug(m, ">>>");
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        List<String> catalogsForModel = catalogs.get(modelDatabaseValues.dir);
        if (catalogsForModel != null) {
            debug.debug(m, "<<<", catalogsForModel);
            return catalogsForModel;
        }
        catalogsForModel = Collections.synchronizedList(new ArrayList<String>());
        catalogs.put(modelDatabaseValues.dir, catalogsForModel);
        if (modelDatabaseValues.connection != null) {
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getCatalogs();
                while (result.next()) {
                    catalogsForModel.add(name(modelDatabaseValues, result.getString("TABLE_CAT")));
                }
            } catch (SQLException e) {
                debug.error("getCatalogs error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getCatalogs error " + e, e);
                }
            }
        }
        debug.debug(m, "<<<", catalogsForModel);
        return catalogsForModel;
    }

    @Override
    public List<String> getSchemas(EObject model) {
        String m = "getSchemas";
        debug.debug(m, ">>>");
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        List<String> schemasForModel = schemas.get(modelDatabaseValues.dir);
        if (schemasForModel != null) {
            debug.debug(m, "<<<", schemasForModel);
            return schemasForModel;
        }
        schemasForModel = Collections.synchronizedList(new ArrayList<String>());
        schemas.put(modelDatabaseValues.dir, schemasForModel);
        if (modelDatabaseValues.connection != null) {
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getSchemas(modelDatabaseValues.dbCatalog, null);
                while (result.next()) {
                    schemasForModel.add(name(modelDatabaseValues, result.getString("TABLE_SCHEM")));
                }
            } catch (SQLException e) {
                debug.error("getCatalogs error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getCatalogs error " + e, e);
                }
            }
        }
        debug.debug(m, "<<<", schemasForModel);
        return schemasForModel;
    }

    @Override
    public List<String> getTables(EObject model) {
        String m = "getTables";
        debug.debug(m, ">>>");
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        List<String> tablesForModel = tables.get(modelDatabaseValues.dir);
        if (tablesForModel != null) {
            debug.debug(m, "<<<", tablesForModel);
            return tablesForModel;
        }
        tablesForModel = Collections.synchronizedList(new ArrayList<String>());
        tables.put(modelDatabaseValues.dir, tablesForModel);
        if (modelDatabaseValues.connection != null) {
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getTables(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema, null,
                        new String[] { "TABLE", "VIEW" });
                while (result.next()) {
                    if (ignore(result))
                        continue;
                    tablesForModel.add(name(modelDatabaseValues, result.getString("TABLE_NAME")));
                }
            } catch (SQLException e) {
                debug.error("getTables error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getTables error " + e, e);
                }
            }
        }
        debug.debug(m, "<<<", tablesForModel);
        return tablesForModel;
    }

    @Override
    public List<String> getProcedures(EObject model) {
        String m = "getProcedures";
        debug.debug(m, ">>>");
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null || modelDatabaseValues.dbSkipProcedures) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        List<String> proceduresForModel = procedures.get(modelDatabaseValues.dir);
        if (proceduresForModel != null) {
            debug.debug(m, "<<<", proceduresForModel);
            return proceduresForModel;
        }
        proceduresForModel = Collections.synchronizedList(new ArrayList<String>());
        procedures.put(modelDatabaseValues.dir, proceduresForModel);
        if (modelDatabaseValues.connection != null) {
            DbType dbType = getDbType(model);
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getProcedures(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema, null);
                while (result.next()) {
                    if (ignore(result))
                        continue;
                    String name = result.getString("PROCEDURE_NAME");
                    if (dbType == DbType.MS_SQL) {
                        int ix = name.lastIndexOf(";");
                        if (ix >= 0)
                            name = name.substring(0, ix);
                    }
                    proceduresForModel.add(name(modelDatabaseValues, name));
                }
            } catch (SQLException e) {
                debug.error("getProcedures error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getProcedures error " + e, e);
                }
            }
        }
        debug.debug(m, "<<<", proceduresForModel);
        return proceduresForModel;
    }

    @Override
    public List<String> getFunctions(EObject model) {
        DbType dbType = getDbType(model);
        if (dbType == DbType.H2)
            return Collections.emptyList();

        String m = "getFunctions";
        debug.debug(m, ">>>");
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null || modelDatabaseValues.dbSkipProcedures) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        List<String> functionsForModel = functions.get(modelDatabaseValues.dir);
        if (functionsForModel != null) {
            debug.debug(m, "<<<", functionsForModel);
            return functionsForModel;
        }
        functionsForModel = Collections.synchronizedList(new ArrayList<String>());
        functions.put(modelDatabaseValues.dir, functionsForModel);
        if (modelDatabaseValues.connection != null) {
            if (dbType == DbType.POSTGRESQL || dbType == DbType.INFORMIX)
                return functionsForModel;
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getFunctions(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema, null);
                while (result.next()) {
                    if (ignore(result))
                        continue;
                    String name = result.getString("FUNCTION_NAME");
                    if (dbType == DbType.MS_SQL) {
                        int ix = name.lastIndexOf(";");
                        if (ix >= 0)
                            name = name.substring(0, ix);
                    }
                    functionsForModel.add(name(modelDatabaseValues, name));
                }
            } catch (SQLException e) {
                debug.error("getFunctions error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getFunctions error " + e, e);
                }
            }
        }
        debug.debug(m, "<<<", functionsForModel);
        return functionsForModel;
    }

    @Override
    public boolean checkTable(EObject model, String table) {
        if (table == null)
            return false;
        return getTables(model).contains(table);
    }

    @Override
    public boolean checkTableName(EObject model, String table) {
        if (table == null)
            return false;
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        String origName = origName(model, modelDatabaseValues, table);
        if (origName == null)
            return false;
        return true;
    }

    @Override
    public boolean checkProcedure(EObject model, String procedure) {
        if (procedure == null)
            return false;
        return getProcedures(model).contains(procedure);
    }

    @Override
    public boolean checkFunction(EObject model, String function) {
        if (function == null)
            return false;
        return getFunctions(model).contains(function);
    }

    @Override
    public List<String> getColumns(EObject model, String table) {
        String m = "getColumns";
        debug.debug(m, ">>> " + table);
        if (table == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        boolean doInit = false;
        Map<String, List<String>> allColumnsForModel = columns.get(modelDatabaseValues.dir);
        if (allColumnsForModel == null) {
            allColumnsForModel = Collections.synchronizedMap(new HashMap<String, List<String>>());
            columns.put(modelDatabaseValues.dir, allColumnsForModel);
            doInit = true;
        }
        List<String> columnsForModel = allColumnsForModel.get(table);
        if (columnsForModel == null) {
            columnsForModel = Collections.synchronizedList(new ArrayList<String>());
            allColumnsForModel.put(table, columnsForModel);
            doInit = true;
        }
        if (!doInit) {
            debug.debug(m, "<<<", columnsForModel);
            return columnsForModel;
        }
        if (modelDatabaseValues.connection != null) {
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getColumns(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema,
                        origName(model, modelDatabaseValues, table), null);
                while (result.next()) {
                    if (ignore(result))
                        continue;
                    columnsForModel.add(name(modelDatabaseValues, result.getString("COLUMN_NAME")));
                }
            } catch (SQLException e) {
                debug.error("getColumns error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getColumns error " + e, e);
                }
            }
        }
        debug.debug(m, "<<<", columnsForModel);
        return columnsForModel;
    }

    @Override
    public boolean checkColumn(EObject model, String table, String column) {
        if (table == null || column == null)
            return false;
        return getColumns(model, table).contains(column);
    }

    @Override
    public List<String> getProcColumns(EObject model, String table) {
        String m = "getProcColumns";
        debug.debug(m, ">>> " + table);
        if (table == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null || modelDatabaseValues.dbSkipProcedures) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        boolean doInit = false;
        Map<String, List<String>> allColumnsForModel = procColumns.get(modelDatabaseValues.dir);
        if (allColumnsForModel == null) {
            allColumnsForModel = Collections.synchronizedMap(new HashMap<String, List<String>>());
            procColumns.put(modelDatabaseValues.dir, allColumnsForModel);
            doInit = true;
        }
        List<String> columnsForModel = allColumnsForModel.get(table);
        if (columnsForModel == null) {
            columnsForModel = Collections.synchronizedList(new ArrayList<String>());
            allColumnsForModel.put(table, columnsForModel);
            doInit = true;
        }
        if (!doInit) {
            debug.debug(m, "<<<", columnsForModel);
            return columnsForModel;
        }
        if (modelDatabaseValues.connection != null) {
            DbType dbType = getDbType(model);
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getProcedureColumns(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema,
                        origName(model, modelDatabaseValues, table), null);
                while (result.next()) {
                    if (ignore(result))
                        continue;
                    String name = result.getString("COLUMN_NAME");
                    if (dbType == DbType.MS_SQL) {
                        if (name.startsWith("@"))
                            name = name.substring(1);
                    }
                    columnsForModel.add(name(modelDatabaseValues, name));
                }
            } catch (SQLException e) {
                debug.error("getProcColumns error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getProcColumns error " + e, e);
                }
            }
        }
        debug.debug(m, "<<<", columnsForModel);
        return columnsForModel;
    }

    @Override
    public boolean checkProcColumn(EObject model, String table, String column) {
        if (doSkipProcedures(model))
            return true;
        if (table == null || column == null)
            return false;
        return getProcColumns(model, table).contains(column);
    }

    @Override
    public List<String> getFunColumns(EObject model, String table) {
        DbType dbType = getDbType(model);
        if (dbType == DbType.H2)
            return Collections.emptyList();

        String m = "getFunColumns";
        debug.debug(m, ">>> " + table);
        if (table == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null || modelDatabaseValues.dbSkipProcedures) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        boolean doInit = false;
        Map<String, List<String>> allColumnsForModel = funColumns.get(modelDatabaseValues.dir);
        if (allColumnsForModel == null) {
            allColumnsForModel = Collections.synchronizedMap(new HashMap<String, List<String>>());
            funColumns.put(modelDatabaseValues.dir, allColumnsForModel);
            doInit = true;
        }
        List<String> columnsForModel = allColumnsForModel.get(table);
        if (columnsForModel == null) {
            columnsForModel = Collections.synchronizedList(new ArrayList<String>());
            allColumnsForModel.put(table, columnsForModel);
            doInit = true;
        }
        if (!doInit) {
            debug.debug(m, "<<<", columnsForModel);
            return columnsForModel;
        }
        if (modelDatabaseValues.connection != null) {
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getFunctionColumns(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema,
                        origName(model, modelDatabaseValues, table), null);
                while (result.next()) {
                    if (ignore(result))
                        continue;
                    String name = result.getString("COLUMN_NAME");
                    if (dbType == DbType.MS_SQL) {
                        if (name.startsWith("@"))
                            name = name.substring(1);
                    }
                    columnsForModel.add(name(modelDatabaseValues, name));
                }
            } catch (SQLException e) {
                debug.error("getFunColumns error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getFunColumns error " + e, e);
                }
            }
        }
        debug.debug(m, "<<<", columnsForModel);
        return columnsForModel;
    }

    @Override
    public boolean checkFunColumn(EObject model, String table, String column) {
        if (doSkipProcedures(model))
            return true;
        if (table == null || column == null)
            return false;
        return getFunColumns(model, table).contains(column);
    }

    protected boolean doSkipProcedures(EObject model) {
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null || modelDatabaseValues.dbSkipProcedures)
            return true;
        return false;
    }

    @Override
    public List<DbTable> getDbTables(EObject model, String table) {
        String m = "getDbTables";
        debug.debug(m, ">>> " + table);
        if (table == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        boolean doInit = false;
        Map<String, List<DbTable>> allTablesForModel = dbTables.get(modelDatabaseValues.dir);
        if (allTablesForModel == null) {
            allTablesForModel = Collections.synchronizedMap(new HashMap<String, List<DbTable>>());
            dbTables.put(modelDatabaseValues.dir, allTablesForModel);
            doInit = true;
        }
        List<DbTable> tablesForModel = allTablesForModel.get(table);
        if (tablesForModel == null) {
            tablesForModel = Collections.synchronizedList(new ArrayList<DbTable>());
            allTablesForModel.put(table, tablesForModel);
            doInit = true;
        }
        if (!doInit) {
            debug.debug(m, "<<<", tablesForModel);
            return tablesForModel;
        }
        if (modelDatabaseValues.connection != null) {
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getTables(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema,
                        origName(model, modelDatabaseValues, table), null);
                while (result.next()) {
                    if (ignore(result))
                        continue;
                    DbTable dbTable = new DbTable();
                    dbTable.setName(name(modelDatabaseValues, result.getString("TABLE_NAME")));
                    dbTable.setType(result.getString("TABLE_TYPE"));
                    if (modelDatabaseValues.dbTakeComments)
                        dbTable.setComment(result.getString("REMARKS"));
                    tablesForModel.add(dbTable);
                    // debug.debug(table + ": " + dbTable.toString());
                }
            } catch (SQLException e) {
                debug.error("getDbTables error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getDbTables error " + e, e);
                }
            }
        }
        debug.debug(m, "<<<", tablesForModel);
        return tablesForModel;
    }

    @Override
    public List<DbColumn> getDbColumns(EObject model, String table) {
        String m = "getDbColumns";
        debug.debug(m, ">>> " + table);
        if (table == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        boolean doInit = false;
        Map<String, List<DbColumn>> allColumnsForModel = dbColumns.get(modelDatabaseValues.dir);
        if (allColumnsForModel == null) {
            allColumnsForModel = Collections.synchronizedMap(new HashMap<String, List<DbColumn>>());
            dbColumns.put(modelDatabaseValues.dir, allColumnsForModel);
            doInit = true;
        }
        List<DbColumn> columnsForModel = allColumnsForModel.get(table);
        if (columnsForModel == null) {
            columnsForModel = Collections.synchronizedList(new ArrayList<DbColumn>());
            allColumnsForModel.put(table, columnsForModel);
            doInit = true;
        }
        if (!doInit) {
            debug.debug(m, "<<<", columnsForModel);
            return columnsForModel;
        }
        if (modelDatabaseValues.connection != null) {
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getColumns(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema,
                        origName(model, modelDatabaseValues, table), null);
                while (result.next()) {
                    if (ignore(result))
                        continue;
                    DbColumn dbColumn = new DbColumn();
                    dbColumn.setName(name(modelDatabaseValues, result.getString("COLUMN_NAME")));
                    dbColumn.setType(result.getString("TYPE_NAME"));
                    int ix = dbColumn.getType().indexOf('(');
                    if (ix > 0) {
                        String size = dbColumn.getType().substring(ix + 1);
                        dbColumn.setType(dbColumn.getType().substring(0, ix));
                        ix = size.indexOf(')');
                        if (ix > 0) {
                            size = size.substring(0, ix);
                        }
                        try {
                            dbColumn.setSize(Integer.parseInt(size));
                        } catch (Exception ignore) {
                        }
                    } else {
                        dbColumn.setSize(result.getInt("COLUMN_SIZE"));
                    }
                    dbColumn.setSqlType(result.getInt("DATA_TYPE"));
                    dbColumn.setNullable(result.getInt("NULLABLE") != DatabaseMetaData.columnNoNulls);
                    if (modelDatabaseValues.dbTakeComments)
                        dbColumn.setComment(result.getString("REMARKS"));
                    // dbColumn.setPosition(result.getInt("ORDINAL_POSITION"));
                    dbColumn.setDefaultValue(result.getString("COLUMN_DEF"));
                    columnsForModel.add(dbColumn);
                    // debug.debug(table + ": " + dbColumn.toString());
                }
            } catch (SQLException e) {
                debug.error("getDbColumns error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getDbColumns error " + e, e);
                }
            }
        }
        // Collections.sort(columnsForModel);
        debug.debug(m, "<<<", columnsForModel);
        return columnsForModel;
    }

    @Override
    public List<DbTable> getDbProcedures(EObject model, String table) {
        String m = "getDbProcedures";
        debug.debug(m, ">>> " + table);
        if (table == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null || modelDatabaseValues.dbSkipProcedures) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        boolean doInit = false;
        Map<String, List<DbTable>> allTablesForModel = dbProcedures.get(modelDatabaseValues.dir);
        if (allTablesForModel == null) {
            allTablesForModel = Collections.synchronizedMap(new HashMap<String, List<DbTable>>());
            dbProcedures.put(modelDatabaseValues.dir, allTablesForModel);
            doInit = true;
        }
        List<DbTable> tablesForModel = allTablesForModel.get(table);
        if (tablesForModel == null) {
            tablesForModel = Collections.synchronizedList(new ArrayList<DbTable>());
            allTablesForModel.put(table, tablesForModel);
            doInit = true;
        }
        if (!doInit) {
            debug.debug(m, "<<<", tablesForModel);
            return tablesForModel;
        }
        if (modelDatabaseValues.connection != null) {
            DbType dbType = getDbType(model);
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getProcedures(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema,
                        origName(model, modelDatabaseValues, table));
                while (result.next()) {
                    if (ignore(result))
                        continue;
                    String name = result.getString("PROCEDURE_NAME");
                    if (dbType == DbType.MS_SQL) {
                        int ix = name.lastIndexOf(";");
                        if (ix >= 0)
                            name = name.substring(0, ix);
                    }
                    DbTable dbTable = new DbTable();
                    dbTable.setName(name(modelDatabaseValues, name));
                    dbTable.setPtype(result.getShort("PROCEDURE_TYPE"));
                    if (modelDatabaseValues.dbTakeComments)
                        dbTable.setComment(result.getString("REMARKS"));
                    tablesForModel.add(dbTable);
                    // debug.debug(table + ": " + dbTable.toString());
                }
            } catch (SQLException e) {
                debug.error("getDbProcedures error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getDbProcedures error " + e, e);
                }
            }
        }
        debug.debug(m, "<<<", tablesForModel);
        return tablesForModel;
    }

    @Override
    public List<DbColumn> getDbProcColumns(EObject model, String procedure) {
        String m = "getDbProcColumns";
        debug.debug(m, ">>> " + procedure);
        if (procedure == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null || modelDatabaseValues.dbSkipProcedures) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        boolean doInit = false;
        Map<String, List<DbColumn>> allColumnsForModel = dbProcColumns.get(modelDatabaseValues.dir);
        if (allColumnsForModel == null) {
            allColumnsForModel = Collections.synchronizedMap(new HashMap<String, List<DbColumn>>());
            dbProcColumns.put(modelDatabaseValues.dir, allColumnsForModel);
            doInit = true;
        }
        List<DbColumn> columnsForModel = allColumnsForModel.get(procedure);
        if (columnsForModel == null) {
            columnsForModel = Collections.synchronizedList(new ArrayList<DbColumn>());
            allColumnsForModel.put(procedure, columnsForModel);
            doInit = true;
        }
        if (!doInit) {
            debug.debug(m, "<<<", columnsForModel);
            return columnsForModel;
        }
        if (modelDatabaseValues.connection != null) {
            DbType dbType = getDbType(model);
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getProcedureColumns(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema,
                        origName(model, modelDatabaseValues, procedure), null);
                while (result.next()) {
                    if (ignore(result))
                        continue;
                    DbColumn dbColumn = new DbColumn();
                    String name = result.getString("COLUMN_NAME");
                    if (dbType == DbType.MS_SQL) {
                        if (name.startsWith("@"))
                            name = name.substring(1);
                    }
                    dbColumn.setName(name(modelDatabaseValues, name));
                    dbColumn.setType(result.getString("TYPE_NAME"));
                    dbColumn.setColumnType(result.getShort("COLUMN_TYPE"));
                    int ix = dbColumn.getType().indexOf('(');
                    if (ix > 0) {
                        String size = dbColumn.getType().substring(ix + 1);
                        dbColumn.setType(dbColumn.getType().substring(0, ix));
                        ix = size.indexOf(')');
                        if (ix > 0) {
                            size = size.substring(0, ix);
                        }
                        try {
                            dbColumn.setSize(Integer.parseInt(size));
                        } catch (Exception ignore) {
                        }
                    } else {
                        dbColumn.setSize(result.getInt("LENGTH"));
                    }
                    dbColumn.setSqlType(result.getInt("DATA_TYPE"));
                    dbColumn.setNullable(result.getInt("NULLABLE") != DatabaseMetaData.columnNoNulls);
                    if (modelDatabaseValues.dbTakeComments)
                        dbColumn.setComment(result.getString("REMARKS"));
                    // if (DbType.MY_SQL != dbType && DbType.INFORMIX != dbType)
                    // dbColumn.setPosition(result.getInt("ORDINAL_POSITION"));
                    columnsForModel.add(dbColumn);
                }
            } catch (SQLException e) {
                debug.error("getDbProcColumns error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getDbProcColumns error " + e, e);
                }
            }
        }
        // Collections.sort(columnsForModel);
        debug.debug(m, "<<<", columnsForModel);
        return columnsForModel;
    }

    @Override
    public List<DbTable> getDbFunctions(EObject model, String table) {
        DbType dbType = getDbType(model);
        if (dbType == DbType.H2)
            return Collections.emptyList();

        String m = "getDbFunctions";
        debug.debug(m, ">>> " + table);
        if (table == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null || modelDatabaseValues.dbSkipProcedures) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        boolean doInit = false;
        Map<String, List<DbTable>> allTablesForModel = dbFunctions.get(modelDatabaseValues.dir);
        if (allTablesForModel == null) {
            allTablesForModel = Collections.synchronizedMap(new HashMap<String, List<DbTable>>());
            dbFunctions.put(modelDatabaseValues.dir, allTablesForModel);
            doInit = true;
        }
        List<DbTable> tablesForModel = allTablesForModel.get(table);
        if (tablesForModel == null) {
            tablesForModel = Collections.synchronizedList(new ArrayList<DbTable>());
            allTablesForModel.put(table, tablesForModel);
            doInit = true;
        }
        if (!doInit) {
            debug.debug(m, "<<<", tablesForModel);
            return tablesForModel;
        }
        if (modelDatabaseValues.connection != null) {
            if (dbType == DbType.POSTGRESQL || dbType == DbType.INFORMIX)
                return tablesForModel;
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getFunctions(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema,
                        origName(model, modelDatabaseValues, table));
                while (result.next()) {
                    if (ignore(result))
                        continue;
                    String name = result.getString("FUNCTION_NAME");
                    if (dbType == DbType.MS_SQL) {
                        int ix = name.lastIndexOf(";");
                        if (ix >= 0)
                            name = name.substring(0, ix);
                    }
                    DbTable dbTable = new DbTable();
                    dbTable.setName(name(modelDatabaseValues, name));
                    if (dbType != DbType.DB2 && dbType != DbType.ORACLE)
                        dbTable.setFtype(result.getShort("FUNCTION_TYPE"));
                    if (modelDatabaseValues.dbTakeComments)
                        dbTable.setComment(result.getString("REMARKS"));
                    tablesForModel.add(dbTable);
                }
            } catch (SQLException e) {
                debug.error("getDbFunctions error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getDbFunctions error " + e, e);
                }
            }
        }
        debug.debug(m, "<<<", tablesForModel);
        return tablesForModel;
    }

    @Override
    public List<DbColumn> getDbFunColumns(EObject model, String function) {
        DbType dbType = getDbType(model);
        if (dbType == DbType.H2)
            return Collections.emptyList();

        String m = "getDbFunColumns";
        debug.debug(m, ">>> " + function);
        if (function == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null || modelDatabaseValues.dbSkipProcedures) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        boolean doInit = false;
        Map<String, List<DbColumn>> allColumnsForModel = dbFunColumns.get(modelDatabaseValues.dir);
        if (allColumnsForModel == null) {
            allColumnsForModel = Collections.synchronizedMap(new HashMap<String, List<DbColumn>>());
            dbFunColumns.put(modelDatabaseValues.dir, allColumnsForModel);
            doInit = true;
        }
        List<DbColumn> columnsForModel = allColumnsForModel.get(function);
        if (columnsForModel == null) {
            columnsForModel = Collections.synchronizedList(new ArrayList<DbColumn>());
            allColumnsForModel.put(function, columnsForModel);
            doInit = true;
        }
        if (!doInit) {
            debug.debug(m, "<<<", columnsForModel);
            return columnsForModel;
        }
        if (modelDatabaseValues.connection != null) {
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getFunctionColumns(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema,
                        origName(model, modelDatabaseValues, function), null);
                while (result.next()) {
                    if (ignore(result))
                        continue;
                    String name = result.getString(dbType == DbType.DB2 ? "PARAMETER_NAME" : "COLUMN_NAME");
                    if (dbType == DbType.MS_SQL) {
                        if (name.startsWith("@"))
                            name = name.substring(1);
                    }
                    DbColumn dbColumn = new DbColumn();
                    dbColumn.setName(name(modelDatabaseValues, name));
                    dbColumn.setType(result.getString("TYPE_NAME"));
                    dbColumn.setColumnType(result.getShort(dbType == DbType.DB2 ? "PARAMETER_TYPE" : "COLUMN_TYPE"));
                    int ix = dbColumn.getType().indexOf('(');
                    if (ix > 0) {
                        String size = dbColumn.getType().substring(ix + 1);
                        dbColumn.setType(dbColumn.getType().substring(0, ix));
                        ix = size.indexOf(')');
                        if (ix > 0) {
                            size = size.substring(0, ix);
                        }
                        try {
                            dbColumn.setSize(Integer.parseInt(size));
                        } catch (Exception ignore) {
                        }
                    } else {
                        dbColumn.setSize(result.getInt("LENGTH"));
                    }
                    dbColumn.setSqlType(result.getInt("DATA_TYPE"));
                    dbColumn.setNullable(result.getInt("NULLABLE") != DatabaseMetaData.columnNoNulls);
                    if (modelDatabaseValues.dbTakeComments)
                        dbColumn.setComment(result.getString("REMARKS"));
                    // if (DbType.MY_SQL != dbType)
                    // dbColumn.setPosition(result.getInt("ORDINAL_POSITION"));
                    columnsForModel.add(dbColumn);
                    // debug.debug(function + ": " + dbColumn.toString());
                }
            } catch (SQLException e) {
                debug.error("getDbFunColumns error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getDbFunColumns error " + e, e);
                }
            }
        }
        // Collections.sort(columnsForModel);
        debug.debug(m, "<<<", columnsForModel);
        return columnsForModel;
    }

    @Override
    public List<String> getDbPrimaryKeys(EObject model, String table) {
        String m = "getDbPrimaryKeys";
        debug.debug(m, ">>> " + table);
        if (table == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        boolean doInit = false;
        Map<String, List<String>> allPrimaryKeysForModel = dbPrimaryKeys.get(modelDatabaseValues.dir);
        if (allPrimaryKeysForModel == null) {
            allPrimaryKeysForModel = Collections.synchronizedMap(new HashMap<String, List<String>>());
            dbPrimaryKeys.put(modelDatabaseValues.dir, allPrimaryKeysForModel);
            doInit = true;
        }
        List<String> primaryKeysForModel = allPrimaryKeysForModel.get(table);
        if (primaryKeysForModel == null) {
            primaryKeysForModel = Collections.synchronizedList(new ArrayList<String>());
            allPrimaryKeysForModel.put(table, primaryKeysForModel);
            doInit = true;
        }
        if (!doInit) {
            debug.debug(m, "<<<", primaryKeysForModel);
            return primaryKeysForModel;
        }
        if (modelDatabaseValues.connection != null) {
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getPrimaryKeys(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema,
                        origName(model, modelDatabaseValues, table));
                while (result.next()) {
                    primaryKeysForModel.add(name(modelDatabaseValues, result.getString("COLUMN_NAME")));
                }
            } catch (SQLException e) {
                debug.error("getDbPrimaryKeys error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getDbPrimaryKeys error " + e, e);
                }
            }
        }
        debug.debug(m, "<<<", primaryKeysForModel);
        return primaryKeysForModel;
    }

    @Override
    public List<DbExport> getDbExports(EObject model, String table) {
        String m = "getDbExports";
        debug.debug(m, ">>> " + table);
        if (table == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        boolean doInit = false;
        Map<String, List<DbExport>> allExportsForModel = dbExports.get(modelDatabaseValues.dir);
        if (allExportsForModel == null) {
            allExportsForModel = Collections.synchronizedMap(new HashMap<String, List<DbExport>>());
            dbExports.put(modelDatabaseValues.dir, allExportsForModel);
            doInit = true;
        }
        List<DbExport> exportsForModel = allExportsForModel.get(table);
        if (exportsForModel == null) {
            exportsForModel = Collections.synchronizedList(new ArrayList<DbExport>());
            allExportsForModel.put(table, exportsForModel);
            doInit = true;
        }
        if (!doInit) {
            debug.debug(m, "<<<", exportsForModel);
            return exportsForModel;
        }
        if (modelDatabaseValues.connection != null) {
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getExportedKeys(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema,
                        origName(model, modelDatabaseValues, table));
                while (result.next()) {
                    DbExport dbExport = new DbExport();
                    dbExport.setPkTable(name(modelDatabaseValues, result.getString("PKTABLE_NAME")));
                    dbExport.setPkColumn(name(modelDatabaseValues, result.getString("PKCOLUMN_NAME")));
                    dbExport.setFkTable(name(modelDatabaseValues, result.getString("FKTABLE_NAME")));
                    dbExport.setFkColumn(name(modelDatabaseValues, result.getString("FKCOLUMN_NAME")));
                    dbExport.setFkName(name(modelDatabaseValues, result.getString("FK_NAME")));
                    dbExport.setPkName(name(modelDatabaseValues, result.getString("PK_NAME")));
                    // debug.debug("BBB " + table + " " + dbExport.toString());
                    exportsForModel.add(dbExport);
                }
            } catch (SQLException e) {
                debug.error("getDbExports error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getDbExports error " + e, e);
                }
            }
        }
        debug.debug(m, "<<<", exportsForModel);
        return exportsForModel;
    }

    @Override
    public List<DbImport> getDbImports(EObject model, String table) {
        String m = "getDbImports";
        debug.debug(m, ">>> " + table);
        if (table == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        boolean doInit = false;
        Map<String, List<DbImport>> allImportsForModel = dbImports.get(modelDatabaseValues.dir);
        if (allImportsForModel == null) {
            allImportsForModel = Collections.synchronizedMap(new HashMap<String, List<DbImport>>());
            dbImports.put(modelDatabaseValues.dir, allImportsForModel);
            doInit = true;
        }
        List<DbImport> importsForModel = allImportsForModel.get(table);
        if (importsForModel == null) {
            importsForModel = Collections.synchronizedList(new ArrayList<DbImport>());
            allImportsForModel.put(table, importsForModel);
            doInit = true;
        }
        if (!doInit) {
            debug.debug(m, "<<<", importsForModel);
            return importsForModel;
        }
        if (modelDatabaseValues.connection != null) {
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getImportedKeys(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema,
                        origName(model, modelDatabaseValues, table));
                while (result.next()) {
                    DbImport dbImport = new DbImport();
                    dbImport.setPkTable(name(modelDatabaseValues, result.getString("PKTABLE_NAME")));
                    dbImport.setPkColumn(name(modelDatabaseValues, result.getString("PKCOLUMN_NAME")));
                    dbImport.setFkTable(name(modelDatabaseValues, result.getString("FKTABLE_NAME")));
                    dbImport.setFkColumn(name(modelDatabaseValues, result.getString("FKCOLUMN_NAME")));
                    dbImport.setFkName(name(modelDatabaseValues, result.getString("FK_NAME")));
                    dbImport.setPkName(name(modelDatabaseValues, result.getString("PK_NAME")));
                    // debug.debug("CCC " + table + " " + dbImport.toString());
                    importsForModel.add(dbImport);
                }
            } catch (SQLException e) {
                debug.error("getDbImports error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getDbImports error " + e, e);
                }
            }
        }
        debug.debug(m, "<<<", importsForModel);
        return importsForModel;
    }

    @Override
    public String getType(EObject model, String table, String column) {
        String m = "getType";
        debug.trace(m, ">>> " + table + " " + column);
        if (table == null || column == null) {
            debug.trace(m, "<<<");
            return "";
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.trace(m, "<<<");
            return "";
        }
        ResultSet result = null;
        String type = null;
        int typeSize = 0;
        try {
            DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
            result = meta.getColumns(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema,
                    origName(model, modelDatabaseValues, table), null);
            while (result.next()) {
                if (name(modelDatabaseValues, result.getString("COLUMN_NAME")).equals(column)) {
                    type = result.getString("TYPE_NAME");
                    int ix = type.indexOf('(');
                    if (ix > 0) {
                        String size = type.substring(ix + 1);
                        type = type.substring(0, ix);
                        ix = size.indexOf(')');
                        if (ix > 0) {
                            size = size.substring(0, ix);
                        }
                        try {
                            typeSize = Integer.parseInt(size);
                        } catch (Exception ignore) {
                        }
                    } else {
                        typeSize = result.getInt("COLUMN_SIZE");
                    }
                    break;
                }
            }
        } catch (SQLException e) {
            debug.error("getType error " + e, e);
        } finally {
            try {
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                debug.error("getType error " + e, e);
            }
        }
        debug.trace(m, "<<<", type + "(" + typeSize + ")");
        return type + "(" + typeSize + ")";
    }

    @Override
    public String getProcType(EObject model, String procedure, String column) {
        String m = "getProcType";
        debug.trace(m, ">>> " + procedure + " " + column);
        if (procedure == null || column == null) {
            debug.trace(m, "<<<");
            return "";
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.trace(m, "<<<");
            return "";
        }
        // DbType dbType = getDbType(model);
        ResultSet result = null;
        String type = null;
        int typeSize = 0;
        try {
            DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
            result = meta.getProcedureColumns(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema,
                    origName(model, modelDatabaseValues, procedure), null);
            while (result.next()) {
                if (column.equals(name(modelDatabaseValues, result.getString("COLUMN_NAME")))) {
                    type = result.getString("TYPE_NAME");
                    int ix = type.indexOf('(');
                    if (ix > 0) {
                        String size = type.substring(ix + 1);
                        type = type.substring(0, ix);
                        ix = size.indexOf(')');
                        if (ix > 0) {
                            size = size.substring(0, ix);
                        }
                        try {
                            typeSize = Integer.parseInt(size);
                        } catch (Exception ignore) {
                        }
                    } else {
                        typeSize = result.getInt("LENGTH");
                    }
                    break;
                }
            }
        } catch (SQLException e) {
            debug.error("getDbProcType error " + e, e);
        } finally {
            try {
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                debug.error("getDbProcType error " + e, e);
            }
        }
        debug.trace(m, "<<<", type + "(" + typeSize + ")");
        return type + "(" + typeSize + ")";
    }

    @Override
    public String getFunType(EObject model, String function, String column) {
        String m = "getFunType";
        debug.trace(m, ">>> " + function + " " + column);
        if (function == null || column == null) {
            debug.trace(m, "<<<");
            return "";
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.trace(m, "<<<");
            return "";
        }
        DbType dbType = getDbType(model);
        ResultSet result = null;
        String type = null;
        int typeSize = 0;
        try {
            DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
            result = meta.getFunctionColumns(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema,
                    origName(model, modelDatabaseValues, function), null);
            while (result.next()) {
                if (column.equals(name(modelDatabaseValues,
                        result.getString(dbType == DbType.DB2 ? "PARAMETER_NAME" : "COLUMN_NAME")))) {
                    type = result.getString("TYPE_NAME");
                    int ix = type.indexOf('(');
                    if (ix > 0) {
                        String size = type.substring(ix + 1);
                        type = type.substring(0, ix);
                        ix = size.indexOf(')');
                        if (ix > 0) {
                            size = size.substring(0, ix);
                        }
                        try {
                            typeSize = Integer.parseInt(size);
                        } catch (Exception ignore) {
                        }
                    } else {
                        typeSize = result.getInt("LENGTH");
                    }
                    break;
                }
            }
        } catch (SQLException e) {
            debug.error("getDbFunType error " + e, e);
        } finally {
            try {
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                debug.error("getDbFunType error " + e, e);
            }
        }
        debug.trace(m, "<<<", type + "(" + typeSize + ")");
        return type + "(" + typeSize + ")";
    }

    @Override
    public List<DbIndex> getDbIndexes(EObject model, String table) {
        String m = "getDbIndexes";
        debug.debug(m, ">>> " + table);
        if (table == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null || modelDatabaseValues.indexTypes == null
                || modelDatabaseValues.indexTypes.isEmpty() || modelDatabaseValues.dbSkipIndexes) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        boolean doInit = false;
        Map<String, List<DbIndex>> allIndexesForModel = dbIndexes.get(modelDatabaseValues.dir);
        if (allIndexesForModel == null) {
            allIndexesForModel = Collections.synchronizedMap(new HashMap<String, List<DbIndex>>());
            dbIndexes.put(modelDatabaseValues.dir, allIndexesForModel);
            doInit = true;
        }
        List<DbIndex> indexesForModel = allIndexesForModel.get(table);
        if (indexesForModel == null) {
            indexesForModel = Collections.synchronizedList(new ArrayList<DbIndex>());
            allIndexesForModel.put(table, indexesForModel);
            doInit = true;
        }
        if (!doInit) {
            debug.debug(m, "<<<", indexesForModel);
            return indexesForModel;
        }
        if (modelDatabaseValues.connection != null) {
            DbType dbType = getDbType(model);
            ResultSet result = null;
            try {
                Map<String, DbIndex> mapOfIndexes = new LinkedHashMap<String, DbIndex>();
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getIndexInfo(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema,
                        origName(model, modelDatabaseValues, table), false, true);
                short addToPosition = 0;
                while (result.next()) {
                    if (dbType == DbType.INFORMIX && result.getShort("ORDINAL_POSITION") == 0)
                        continue;
                    String name = name(modelDatabaseValues, result.getString("INDEX_NAME"));
                    if (!modelDatabaseValues.indexTypes.contains(result.getShort("TYPE"))) {
                        // debug.debug("INDEX TYPE " + result.getShort("TYPE") + " for " + name);
                        LOGGER.warn("INDEX TYPE " + result.getShort("TYPE") + " for " + name);
                        continue;
                    }
                    DbIndex dbIndex = mapOfIndexes.get(name);
                    if (dbIndex == null) {
                        dbIndex = new DbIndex();
                        dbIndex.setName(name);
                        mapOfIndexes.put(name, dbIndex);
                    }
                    DbIndex.DbIndexDetail detail = new DbIndex.DbIndexDetail();
                    short position = result.getShort("ORDINAL_POSITION");
                    detail.setColname(name(modelDatabaseValues, result.getString("COLUMN_NAME")));
                    detail.setDesc("D".equalsIgnoreCase(result.getString("ASC_OR_DESC")));
                    if (position == 0)
                        addToPosition = 1;
                    dbIndex.getColumns().add(position - 1 + addToPosition, detail);
                }
                // debug.debug("EEE " + table + " " + mapOfIndexes);
                indexesForModel.addAll(mapOfIndexes.values());
            } catch (SQLException e) {
                debug.error("getDbIndexes error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getDbIndexes error " + e, e);
                }
            }
        }
        debug.debug(m, "<<<", indexesForModel);
        return indexesForModel;
    }

    @Override
    public List<String> getSequences(EObject model) {
        String m = "getSequences";
        debug.debug(m, ">>>");
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        List<String> sequencesForModel = dbSequences.get(modelDatabaseValues.dir);
        if (sequencesForModel != null) {
            debug.debug(m, "<<<");
            return sequencesForModel;
        }
        sequencesForModel = Collections.synchronizedList(new ArrayList<String>());
        dbSequences.put(modelDatabaseValues.dir, sequencesForModel);
        if (modelDatabaseValues.connection != null) {
            ResultSet result = null;
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                result = meta.getTables(modelDatabaseValues.dbCatalog, modelDatabaseValues.dbSchema, null,
                        new String[] { "SEQUENCE" });
                while (result.next()) {
                    String type = result.getString("TABLE_TYPE");
                    if (type != null
                            && (type.toUpperCase().indexOf("TABLE") >= 0 || type.toUpperCase().indexOf("VIEW") >= 0))
                        continue;
                    String name = result.getString("TABLE_NAME");
                    if (name.startsWith(" "))
                        continue;
                    sequencesForModel.add(name(modelDatabaseValues, name));
                }
            } catch (SQLException e) {
                debug.error("getSequences error " + e, e);
            } finally {
                try {
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    debug.error("getSequences error " + e, e);
                }
            }
        }
        debug.debug(m, "<<<", sequencesForModel);
        return sequencesForModel;
    }

    @Override
    public String getDbMetaInfo(EObject model) {
        String m = "getDbMetaInfo";
        debug.trace(m, ">>>");
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.trace(m, "<<<");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
            sb.append(meta.getDatabaseProductName());
            sb.append(",").append(meta.getDatabaseProductVersion());
            sb.append(",").append(meta.getDatabaseMajorVersion());
            sb.append(",").append(meta.getDatabaseMinorVersion());
        } catch (SQLException e) {
            debug.error("getDbMetaInfo error " + e, e);
        }
        debug.trace(m, "<<<", sb);
        return sb.toString();
    }

    @Override
    public String getDbDriverInfo(EObject model) {
        String m = "getDbDriverInfo";
        debug.trace(m, ">>>");
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.trace(m, "<<<");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
            sb.append(meta.getDriverName());
            sb.append(",").append(meta.getDriverVersion());
            sb.append(",").append(meta.getDriverMajorVersion());
            sb.append(",").append(meta.getDriverMinorVersion());
        } catch (SQLException e) {
            debug.error("getDbDriverInfo error " + e, e);
        }
        debug.trace(m, "<<<", sb);
        return sb.toString();
    }

    @Override
    public String getDbJdbcInfo(EObject model) {
        String m = "getDbJdbcInfo";
        debug.trace(m, ">>>");
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.trace(m, "<<<");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
            sb.append(meta.getJDBCMajorVersion());
            sb.append(",").append(meta.getJDBCMinorVersion());
        } catch (SQLException e) {
            debug.error("getDbJdbcInfo error " + e, e);
        }
        debug.trace(m, "<<<", sb);
        return sb.toString();
    }

    @Override
    public Set<String> getDriverMethods(EObject model) {
        String m = "getDriverMethods";
        debug.trace(m, ">>>");
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.trace(m, "<<<");
            return Collections.emptySet();
        }
        SortedSet<String> driverMethodsForModel = driverMethods.get(modelDatabaseValues.dir);
        if (driverMethodsForModel != null) {
            debug.trace(m, "<<<");
            return driverMethodsForModel;
        }
        driverMethodsForModel = Collections.synchronizedSortedSet(new TreeSet<String>());
        driverMethods.put(modelDatabaseValues.dir, driverMethodsForModel);
        if (modelDatabaseValues.connection != null) {
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                for (Method mm : meta.getClass().getMethods()) {
                    if (mm.getParameterTypes().length > 0)
                        continue;
                    if (mm.getReturnType() != null && mm.getReturnType().equals(ResultSet.class))
                        continue;
                    if (mm.getReturnType() != null && mm.getReturnType().equals(Connection.class))
                        continue;
                    driverMethodsForModel.add(mm.getName());
                }
            } catch (SQLException e) {
                debug.error("getDriverMethods error " + e, e);
            } catch (SecurityException e) {
                debug.error("getDriverMethods error " + e, e);
            }
        }
        debug.trace(m, "<<<", driverMethodsForModel);
        driverMethodsForModel.add("getCatalogs");
        driverMethodsForModel.add("getSchemas");
        driverMethodsForModel.add("getTables");
        driverMethodsForModel.add("getProcedures");
        driverMethodsForModel.add("getFunctions");
        return driverMethodsForModel;
    }

    @Override
    public Object getDriverMethodOutput(EObject model, String methodName) {
        String m = "getDriverMethodOutput";
        debug.trace(m, ">>> " + methodName);
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.trace(m, "<<<");
            return null;
        }
        Object methodCallOutput = null;
        if ("getCatalogs".equals(methodName)) {
            methodCallOutput = getCatalogs(model);
        } else if ("getSchemas".equals(methodName)) {
            methodCallOutput = getSchemas(model);
        } else if ("getTables".equals(methodName)) {
            methodCallOutput = getTables(model);
        } else if ("getProcedures".equals(methodName)) {
            methodCallOutput = getProcedures(model);
        } else if ("getFunctions".equals(methodName)) {
            methodCallOutput = getFunctions(model);
        } else {
            try {
                DatabaseMetaData meta = modelDatabaseValues.connection.getMetaData();
                Method mm = meta.getClass().getMethod(methodName, new Class[] {});
                methodCallOutput = mm.invoke(meta, new Object[] {});
            } catch (SQLException e) {
                debug.error("getDriverMethodOutput error " + e, e);
            } catch (NoSuchMethodException e) {
                debug.error("getDriverMethodOutput error " + e, e);
            } catch (SecurityException e) {
                debug.error("getDriverMethodOutput error " + e, e);
            } catch (IllegalAccessException e) {
                debug.error("getDriverMethodOutput error " + e, e);
            } catch (IllegalArgumentException e) {
                debug.error("getDriverMethodOutput error " + e, e);
            } catch (InvocationTargetException e) {
                debug.error("getDriverMethodOutput error " + e, e);
            }
        }
        debug.trace(m, "<<<", methodCallOutput);
        return methodCallOutput;
    }

    @Override
    public List<String> getCheckConstraints(EObject model) {
        String m = "getCheckConstraints";
        debug.debug(m, ">>>");
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        List<String> checkConstraintsForModel = checkConstraints.get(modelDatabaseValues.dir);
        if (checkConstraintsForModel != null) {
            debug.debug(m, "<<<");
            return checkConstraintsForModel;
        }
        checkConstraintsForModel = Collections.synchronizedList(new ArrayList<String>());
        checkConstraints.put(modelDatabaseValues.dir, checkConstraintsForModel);
        if (modelDatabaseValues.connection != null) {
            try {
                DbType dbType = getDbType(model);
                Map<String, DbCheckConstraint> mapOfCheckConstraints = getCheckConstraints(modelDatabaseValues, null,
                        dbType);
                for (DbCheckConstraint check : mapOfCheckConstraints.values())
                    checkConstraintsForModel.add(name(modelDatabaseValues, check.getEnumName()));
            } catch (SQLException e) {
                debug.error("getCheckConstraints error " + e, e);
            }
        }
        debug.debug(m, "<<<", checkConstraintsForModel);
        return checkConstraintsForModel;
    }

    @Override
    public List<String> getCheckColumns(EObject model, String table) {
        String m = "getCheckColumns";
        debug.debug(m, ">>> " + table);
        if (table == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        boolean doInit = false;
        Map<String, List<String>> allCheckColumnsForModel = checkColumns.get(modelDatabaseValues.dir);
        if (allCheckColumnsForModel == null) {
            allCheckColumnsForModel = Collections.synchronizedMap(new HashMap<String, List<String>>());
            checkColumns.put(modelDatabaseValues.dir, allCheckColumnsForModel);
            doInit = true;
        }
        List<String> checkColumnsForModel = allCheckColumnsForModel.get(table);
        if (checkColumnsForModel == null) {
            checkColumnsForModel = Collections.synchronizedList(new ArrayList<String>());
            allCheckColumnsForModel.put(table, checkColumnsForModel);
            doInit = true;
        }
        if (!doInit) {
            debug.debug(m, "<<<", checkColumnsForModel);
            return checkColumnsForModel;
        }
        if (modelDatabaseValues.connection != null) {
            try {
                DbType dbType = getDbType(model);
                Map<String, DbCheckConstraint> mapOfCheckConstraints = getCheckConstraints(modelDatabaseValues, null,
                        dbType);
                for (DbCheckConstraint check : mapOfCheckConstraints.values()) {
                    if (table.equals(check.getEnumName())) {
                        for (String val : check.getValues()) {
                            if (!val.isEmpty()) {
                                if (Character.isDigit(val.charAt(0)))
                                    checkColumnsForModel.add("I" + val);
                                else
                                    checkColumnsForModel.add(val);
                            }
                        }
                        break;
                    }
                }
            } catch (SQLException e) {
                debug.error("getCheckColumns error " + e, e);
            }
        }
        debug.debug(m, "<<<", checkColumnsForModel);
        return checkColumnsForModel;
    }

    @Override
    public List<DbCheckConstraint> getDbCheckConstraints(EObject model, String table) {
        String m = "getDbCheckConstraints";
        debug.debug(m, ">>> " + table);
        if (table == null) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        DatabaseDirectives modelDatabaseValues = getConnection(model);
        if (modelDatabaseValues == null || modelDatabaseValues.dbSkipCheckConstraints) {
            debug.debug(m, "<<<");
            return Collections.emptyList();
        }
        boolean doInit = false;
        Map<String, List<DbCheckConstraint>> allCheckConstraintsForModel = dbChecksConstraints
                .get(modelDatabaseValues.dir);
        if (allCheckConstraintsForModel == null) {
            allCheckConstraintsForModel = Collections.synchronizedMap(new HashMap<String, List<DbCheckConstraint>>());
            dbChecksConstraints.put(modelDatabaseValues.dir, allCheckConstraintsForModel);
            doInit = true;
        }
        List<DbCheckConstraint> checkConstraintsForModel = allCheckConstraintsForModel.get(table);
        if (checkConstraintsForModel == null) {
            checkConstraintsForModel = Collections.synchronizedList(new ArrayList<DbCheckConstraint>());
            allCheckConstraintsForModel.put(table, checkConstraintsForModel);
            doInit = true;
        }
        if (!doInit) {
            debug.debug(m, "<<<", checkConstraintsForModel);
            return checkConstraintsForModel;
        }
        if (modelDatabaseValues.connection != null) {
            try {
                DbType dbType = getDbType(model);
                Map<String, DbCheckConstraint> mapOfCheckConstraints = getCheckConstraints(modelDatabaseValues,
                        origName(model, modelDatabaseValues, table), dbType);
                for (DbCheckConstraint check : mapOfCheckConstraints.values()) {
                    check.setTable(name(modelDatabaseValues, check.getTable()));
                    check.setColumn(name(modelDatabaseValues, check.getColumn()));
                }
                checkConstraintsForModel.addAll(mapOfCheckConstraints.values());
            } catch (SQLException e) {
                debug.error("getDbCheckConstraints error " + e, e);
            }
        }
        debug.debug(m, "<<<", checkConstraintsForModel);
        return checkConstraintsForModel;
    }

    private Map<String, DbCheckConstraint> getCheckConstraints(DatabaseDirectives modelDatabaseValues, String table,
            DbType dbType) throws SQLException {
        Map<String, DbCheckConstraint> mapOfCheckConstraints = new LinkedHashMap<String, DbCheckConstraint>();
        // TODO - might be also schema and catalog should be taken into account
        if (dbType == DbType.HSQLDB) {
            String query = "select tc.TABLE_NAME, tc.CONSTRAINT_NAME, cc.CHECK_CLAUSE from INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc, INFORMATION_SCHEMA.CHECK_CONSTRAINTS cc where tc.CONSTRAINT_NAME = cc.CONSTRAINT_NAME and tc.CONSTRAINT_TYPE = 'CHECK' and tc.TABLE_NAME = ?";
            String query2 = "select tc.TABLE_NAME, tc.CONSTRAINT_NAME, cc.CHECK_CLAUSE from INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc, INFORMATION_SCHEMA.CHECK_CONSTRAINTS cc where tc.CONSTRAINT_NAME = cc.CONSTRAINT_NAME and tc.CONSTRAINT_TYPE = 'CHECK'";
            Map<String, List<String>> map = getCheckConstraints(modelDatabaseValues, table, query, query2, false);
            for (String constraintName : map.keySet()) {
                DbCheckConstraint check = DbCheckConstraint.parseHsqldb(constraintName, map.get(constraintName).get(0));
                if (check != null)
                    mapOfCheckConstraints.put(constraintName, check);
            }
        } else if (dbType == DbType.H2) {
            String query = "select TABLE_NAME, CONSTRAINT_NAME, CHECK_EXPRESSION from INFORMATION_SCHEMA.CONSTRAINTS where CONSTRAINT_TYPE = 'CHECK' and TABLE_NAME = ?";
            String query2 = "select TABLE_NAME, CONSTRAINT_NAME, CHECK_EXPRESSION from INFORMATION_SCHEMA.CONSTRAINTS where CONSTRAINT_TYPE = 'CHECK'";
            Map<String, List<String>> map = getCheckConstraints(modelDatabaseValues, table, query, query2, false);
            for (String constraintName : map.keySet()) {
                DbCheckConstraint check = DbCheckConstraint.parseH2(constraintName, map.get(constraintName).get(0),
                        map.get(constraintName).get(1));
                if (check != null)
                    mapOfCheckConstraints.put(constraintName, check);
            }
        } else if (dbType == DbType.ORACLE) {
            String query = "select TABLE_NAME, CONSTRAINT_NAME, SEARCH_CONDITION from USER_CONSTRAINTS where TABLE_NAME not like 'BIN%' and CONSTRAINT_TYPE = 'C' and TABLE_NAME = ?";
            String query2 = "select TABLE_NAME, CONSTRAINT_NAME, SEARCH_CONDITION from USER_CONSTRAINTS where TABLE_NAME not like 'BIN%' and CONSTRAINT_TYPE = 'C'";
            Map<String, List<String>> map = getCheckConstraints(modelDatabaseValues, table, query, query2, false);
            for (String constraintName : map.keySet()) {
                DbCheckConstraint check = DbCheckConstraint.parseOracle(constraintName, map.get(constraintName).get(0),
                        map.get(constraintName).get(1));
                if (check != null)
                    mapOfCheckConstraints.put(constraintName, check);
            }
        } else if (dbType == DbType.INFORMIX) {
            String query = "select st.tabname, ss.constrname, sc.checktext from systables st, sysconstraints ss, syschecks sc where st.tabid = ss.tabid and ss.constrid = sc.constrid and ss.constrtype = 'C' and sc.type = 'T' and st.tabname = ?";
            String query2 = "select st.tabname, ss.constrname, sc.checktext from systables st, sysconstraints ss, syschecks sc where st.tabid = ss.tabid and ss.constrid = sc.constrid and ss.constrtype = 'C' and sc.type = 'T'";
            Map<String, List<String>> map = getCheckConstraints(modelDatabaseValues, table, query, query2, false);
            for (String constraintName : map.keySet()) {
                DbCheckConstraint check = DbCheckConstraint.parseInformix(constraintName,
                        map.get(constraintName).get(0), map.get(constraintName).get(1));
                if (check != null)
                    mapOfCheckConstraints.put(constraintName, check);
            }
        } else if (dbType == DbType.MY_SQL) {
            String query = "select TABLE_NAME,COLUMN_NAME,COLUMN_TYPE from information_schema.COLUMNS where DATA_TYPE = \"enum\" and TABLE_NAME = ?";
            String query2 = "select TABLE_NAME,COLUMN_NAME,COLUMN_TYPE from information_schema.COLUMNS where DATA_TYPE = \"enum\"";
            Map<String, List<String>> map = getCheckConstraints(modelDatabaseValues, table, query, query2, true);
            for (String constraintName : map.keySet()) {
                DbCheckConstraint check = DbCheckConstraint.parseMysql(map.get(constraintName).get(2),
                        map.get(constraintName).get(0), map.get(constraintName).get(1));
                if (check != null)
                    mapOfCheckConstraints.put(constraintName, check);
            }
        } else if (dbType == DbType.POSTGRESQL) {
            String query = "select ccu.table_name, ccu.column_name, cc.check_clause from information_schema.constraint_column_usage ccu, information_schema.check_constraints cc where ccu.constraint_name = cc.constraint_name and ccu.table_name = ?";
            String query2 = "select ccu.table_name, ccu.column_name, cc.check_clause from information_schema.constraint_column_usage ccu, information_schema.check_constraints cc where ccu.constraint_name = cc.constraint_name";
            Map<String, List<String>> map = getCheckConstraints(modelDatabaseValues, table, query, query2, true);
            for (String constraintName : map.keySet()) {
                DbCheckConstraint check = DbCheckConstraint.parsePostgresql(map.get(constraintName).get(2),
                        map.get(constraintName).get(0), map.get(constraintName).get(1));
                if (check != null)
                    mapOfCheckConstraints.put(constraintName, check);
            }
        } else if (dbType == DbType.DB2) {
            String query = "select cc.TABNAME, cc.COLNAME, ch.TEXT from SYSCAT.COLCHECKS cc, SYSCAT.CHECKS ch where cc.TABNAME = ch.TABNAME and cc.CONSTNAME = ch.CONSTNAME and ch.TYPE = 'C' and cc.TABNAME = ?";
            String query2 = "select cc.TABNAME, cc.COLNAME, ch.TEXT from SYSCAT.COLCHECKS cc, SYSCAT.CHECKS ch where cc.TABNAME = ch.TABNAME and cc.CONSTNAME = ch.CONSTNAME and ch.TYPE = 'C'";
            Map<String, List<String>> map = getCheckConstraints(modelDatabaseValues, table, query, query2, true);
            for (String constraintName : map.keySet()) {
                DbCheckConstraint check = DbCheckConstraint.parseDb2(map.get(constraintName).get(2),
                        map.get(constraintName).get(0), map.get(constraintName).get(1));
                if (check != null)
                    mapOfCheckConstraints.put(constraintName, check);
            }
        } else if (dbType == DbType.MS_SQL) {
            String query = "select t.name, cc.name, cc.definition  from sys.tables t inner join sys.check_constraints cc on t.object_id = cc.parent_object_id where cc.type = 'C' and t.name = ?";
            String query2 = "select t.name, cc.name, cc.definition  from sys.tables t inner join sys.check_constraints cc on t.object_id = cc.parent_object_id where cc.type = 'C'";
            Map<String, List<String>> map = getCheckConstraints(modelDatabaseValues, table, query, query2, false);
            for (String constraintName : map.keySet()) {
                DbCheckConstraint check = DbCheckConstraint.parseMssql(constraintName, map.get(constraintName).get(0),
                        map.get(constraintName).get(1));
                if (check != null)
                    mapOfCheckConstraints.put(constraintName, check);
            }
        } else {
            mapOfCheckConstraints = new LinkedHashMap<String, DbCheckConstraint>();
        }
        return mapOfCheckConstraints;
    }

    private Map<String, List<String>> getCheckConstraints(DatabaseDirectives modelDatabaseValues, String table,
            String query, String query2, boolean combine) throws SQLException {
        Map<String, List<String>> mapOfCheckConstraints = new LinkedHashMap<String, List<String>>();
        ResultSet result = null;
        try {
            if (table != null) {
                PreparedStatement stmt = modelDatabaseValues.connection.prepareStatement(query);
                stmt.setString(1, table);
                result = stmt.executeQuery();
            } else {
                Statement stmt = modelDatabaseValues.connection.createStatement();
                result = stmt.executeQuery(query2);
            }
            String lastTableName = null;
            String lastConstraintName = null;
            String lastCheckClause = null;
            while (result.next()) {
                String tableName = name(modelDatabaseValues, result.getString(1));
                String constraintName = result.getString(2);
                String checkClause = result.getString(3);
                if (lastConstraintName == null) {
                    lastTableName = tableName;
                    lastConstraintName = constraintName;
                    lastCheckClause = checkClause;
                } else if (lastConstraintName.equals(constraintName)) {
                    lastCheckClause = lastCheckClause + checkClause;
                } else {
                    addCheckConstraints(mapOfCheckConstraints, lastTableName, lastConstraintName, lastCheckClause,
                            combine);
                    lastTableName = tableName;
                    lastConstraintName = constraintName;
                    lastCheckClause = checkClause;
                }
            }
            if (lastConstraintName != null) {
                addCheckConstraints(mapOfCheckConstraints, lastTableName, lastConstraintName, lastCheckClause, combine);
            }
        } finally {
            try {
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                debug.error("getHsqldbCheckConstraints error " + e, e);
            }
        }
        return mapOfCheckConstraints;
    }

    private void addCheckConstraints(Map<String, List<String>> mapOfCheckConstraints, String tableName,
            String constraintName, String checkClause, boolean combine) {
        String m = "addCheckConstraints";
        debug.debug(m, tableName + " constraintName " + constraintName + ", " + " checkClause " + checkClause);
        List<String> list = new ArrayList<String>();
        list.add(checkClause);
        list.add(tableName);
        list.add(constraintName);
        if (combine)
            mapOfCheckConstraints.put(tableName + constraintName, list);
        else
            mapOfCheckConstraints.put(constraintName, list);
    }

    private DbType getDbType(EObject model) {
        DbType dbType = getDatabaseDirectives(model).dbType;
        if (dbType == null) {
            DbType[] dbTypes = DbType.fromDbMetaInfo(getDbMetaInfo(model));
            if (dbTypes != null && dbTypes.length > 0)
                dbType = dbTypes[0];
        }
        return dbType;
    }

    private String name(DatabaseDirectives modelDatabaseValues, String s) {
        if (s == null)
            return null;
        String ss = s;
        if (dbOriginalNames.get(modelDatabaseValues.dir) == null)
            dbOriginalNames.put(modelDatabaseValues.dir, Collections.synchronizedMap(new HashMap<String, String>()));
        if (modelDatabaseValues.dbUppercaseNames)
            ss = s.toUpperCase();
        else if (modelDatabaseValues.dbLowercaseNames)
            ss = s.toLowerCase();
        dbOriginalNames.get(modelDatabaseValues.dir).put(ss, s);
        return ss;
    }

    private String origName(EObject model, DatabaseDirectives modelDatabaseValues, String s) {
        String ss = s;
        if (s != null) {
            if (dbOriginalNames.get(modelDatabaseValues.dir) == null) {
                getTables(model);
                getProcedures(model);
                getFunctions(model);
                getCheckConstraints(model);
            }
            ss = dbOriginalNames.get(modelDatabaseValues.dir).get(s);
        }
        return ss;
    }

    private boolean ignore(ResultSet result) throws SQLException {
        String remark = result.getString("REMARKS");
        if (remark != null && remark.indexOf(IGNORE) >= 0)
            return true;
        return false;
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO - how to close connection in the process of the IDE shutdown?
        // for (Entry<String, DatabaseValues> modelDatabaseValues : connections.entrySet()) {
        // closeConnection(modelDatabaseValues.getValue());
        // }
        super.finalize();
    }
}
