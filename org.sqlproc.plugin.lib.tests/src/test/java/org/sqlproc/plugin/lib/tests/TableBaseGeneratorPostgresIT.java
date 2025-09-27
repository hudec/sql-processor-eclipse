package org.sqlproc.plugin.lib.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sqlproc.plugin.lib.generator.TableBaseGenerator;
import org.sqlproc.plugin.lib.property.ModelProperty;
import org.sqlproc.plugin.lib.property.ModelProperty.ModelValues;
import org.sqlproc.plugin.lib.property.PojoAttribute;
import org.sqlproc.plugin.lib.property.TableDefinition;
import org.sqlproc.plugin.lib.resolver.DbResolver;
import org.sqlproc.plugin.lib.resolver.DbResolver.DbType;
import org.sqlproc.plugin.lib.resolver.DbResolverBean;
import org.sqlproc.plugin.lib.util.Stats;

class TableBaseGeneratorPostgresIT {

    private static final String JDBC_URL = System.getProperty("sqlproc.pg.url",
            "jdbc:postgresql://localhost:5432/simple");
    private static final String JDBC_USER = System.getProperty("sqlproc.pg.user", "simple");
    private static final String JDBC_PASSWORD = System.getProperty("sqlproc.pg.password", "simple");
    private static final String JDBC_SCHEMA = System.getProperty("sqlproc.pg.schema", "public");

    @BeforeAll
    static void ensureDatabaseIsReady() throws Exception {
        Class.forName("org.postgresql.Driver");
        assumeTrue(isDatabaseReachable(), () -> "PostgreSQL not reachable at " + JDBC_URL);
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            applyDdl(connection);
        }
    }

    @Test
    void loadsMetadataFromPostgres() throws Exception {
        ModelValues modelValues = PostgresModelValuesFactory.create(JDBC_URL, JDBC_USER, JDBC_PASSWORD, JDBC_SCHEMA,
                List.of("person", "address"));
        StaticModelProperty modelProperty = new StaticModelProperty(modelValues);
        EObject dummyModel = new MinimalEObjectImpl.Container();

        Class<?> driverClass = Class.forName("org.postgresql.Driver");
        DbResolverBean dbResolver = new DbResolverBean(modelProperty, driverClass, null, null);

        TestableTableBaseGenerator generator = new TestableTableBaseGenerator(modelProperty, dummyModel,
                dbResolver.getSequences(dummyModel), DbType.POSTGRESQL);
        Stats stats = new Stats();

        boolean loaded = generator.load(dbResolver, stats);

        assertThat(loaded).isTrue();
        assertThat(stats.tables).isEqualTo(2);
        assertThat(stats.columns).isGreaterThanOrEqualTo(7);
        assertThat(stats.primaryKeys).isEqualTo(2);
        assertThat(stats.indexes).isGreaterThanOrEqualTo(1);

        Map<String, PojoAttribute> person = generator.getPojo("person");
        assertThat(person).isNotNull();
        assertThat(person.get("id").isPrimaryKey()).isTrue();
        assertThat(person.get("full_name").getClassName()).isEqualTo("String");
        assertThat(generator.getComments().get("person")).isEqualTo("Person master table");

        Map<String, PojoAttribute> address = generator.getPojo("address");
        assertThat(address).isNotNull();
        PojoAttribute foreignKey = address.get("person_id");
        assertThat(foreignKey.getPkTable()).isEqualTo("person");
        assertThat(foreignKey.getPkColumn()).isEqualTo("id");
        assertThat(generator.getIndexes("address")).isNotEmpty();
    }

    private static boolean isDatabaseReachable() {
        try (Connection ignored = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    private static void applyDdl(Connection connection) throws IOException, SQLException {
        try {
            connection.setSchema(JDBC_SCHEMA);
        } catch (SQLException ignored) {
            // schema might not exist yet or driver does not support setSchema; ignore in that case
        }
        try (InputStream in = TableBaseGeneratorPostgresIT.class.getResourceAsStream("/postgresql.ddl")) {
            if (in == null) {
                throw new IllegalStateException("DDL resource postgresql.ddl not found on classpath");
            }
            String ddl = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            for (String rawStatement : ddl.split(";")) {
                String statement = rawStatement.trim();
                if (statement.isEmpty()) {
                    continue;
                }
                try (Statement sql = connection.createStatement()) {
                    sql.execute(statement);
                }
            }
        }
    }

    private static final class StaticModelProperty extends ModelProperty {

        private final ModelValues values;

        StaticModelProperty(ModelValues values) {
            super(values);
            this.values = values;
        }

        @Override
        public ModelValues getModelValues(EObject model) {
            return values;
        }
    }

    private static final class TestableTableBaseGenerator extends TableBaseGenerator {

        TestableTableBaseGenerator(ModelProperty modelProperty, EObject model, List<String> dbSequences, DbType dbType) {
            super(modelProperty, model, dbSequences, dbType);
        }

        boolean load(DbResolver dbResolver, Stats stats) {
            return addDefinitions(dbResolver, null, stats);
        }

        Map<String, PojoAttribute> getPojo(String table) {
            return pojos.get(table);
        }

        List<Map<PojoAttribute, Boolean>> getIndexes(String table) {
            return indexes.getOrDefault(table, List.of());
        }

        Map<String, String> getComments() {
            return comments;
        }
    }

    private static final class PostgresModelValuesFactory {

        private PostgresModelValuesFactory() {
        }

        static ModelValues create(String url, String user, String password, String schema, List<String> tables) {
            ModelValues values = new ModelValues();
            values.initModel();
            values.initDatabaseModel();
            values.initPojogenModel();
            values.initModelModel();
            values.dir = "integration-tests";
            values.initialized = true;
            values.doResolveDb = true;
            values.dbDriver = "org.postgresql.Driver";
            values.dbUrl = url;
            values.dbUsername = user;
            values.dbPassword = password;
            values.dbSchema = schema;
            values.dbType = DbType.POSTGRESQL.getValue();
            values.dbTakeComments = true;

            for (String table : tables) {
                values.modelTables.put(table, new SimpleTableDefinition(table));
            }
            return values;
        }
    }

    private record SimpleTableDefinition(String table) implements TableDefinition {

        @Override
        public String getName() {
            return table;
        }

        @Override
        public String getTable() {
            return table;
        }
    }
}
