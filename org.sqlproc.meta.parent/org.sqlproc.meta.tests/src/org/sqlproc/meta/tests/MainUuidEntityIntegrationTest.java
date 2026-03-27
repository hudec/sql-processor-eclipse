package org.sqlproc.meta.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.util.LazyStringInputStream;
import org.eclipse.xtext.validation.IResourceValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sqlproc.meta.ProcessorMetaStandaloneSetup;
import org.sqlproc.meta.generator.TableMetaGenerator;
import org.sqlproc.meta.processorMeta.Artifacts;
import org.sqlproc.meta.processorMeta.MetaStatement;
import org.sqlproc.meta.property.ModelPropertyBean;
import org.sqlproc.plugin.lib.property.ModelProperty;
import org.sqlproc.plugin.lib.resolver.DbResolver;
import org.sqlproc.plugin.lib.resolver.PojoResolverFactory;
import org.sqlproc.plugin.lib.resolver.StandalonePojoResolverImpl;
import org.sqlproc.plugin.lib.util.MainUtils;
import org.sqlproc.plugin.lib.util.Stats;

import com.google.inject.Injector;
import com.google.inject.Provider;

/**
 * Integration test for Main.generate with UUID_ENTITY DDL against PostgreSQL.
 * This test reproduces the issue where INSERT has more target columns than value expressions.
 */
public class MainUuidEntityIntegrationTest {

    private static final String DDL = 
        "CREATE TABLE UUID_ENTITY (\n" +
        "  ID UUID DEFAULT gen_random_uuid() NOT NULL,\n" +
        "  MYID UUID DEFAULT gen_random_uuid() NOT NULL,\n" +
        "  NAME VARCHAR(100) NOT NULL,\n" +
        "  DESCRIPTION VARCHAR(500)\n" +
        ");\n" +
        "\n" +
        "ALTER TABLE UUID_ENTITY ADD CONSTRAINT PK_UUID_ENTITY\n" +
        "  PRIMARY KEY (ID);";

    private static final String DEFINITIONS =
        "resolve-pojo-on;\n" +
        "database-is-offline;\n" +
        "database-jdbc-driver org.postgresql.Driver;\n" +
        "database-has-url \"jdbc:postgresql://localhost:5432/simple\";\n" +
        "database-login-username simple;\n" +
        "database-login-password simple;\n" +
        "database-in-catalog simple;\n" +
        "database-is-of-type PostgreSQL;\n" +
        "\n" +
        "pojogen-generate-wrappers;\n" +
        "pojogen-generate-methods toString equals hashCode isDef toInit;\n" +
        "pojogen-implements-interfaces java.io.Serializable;\n" +
        "pojogen-generate-operators;\n" +
        "\n" +
        "is-table uuid_entity uuid_entity;\n" +
        "is-pojo UuidEntity org.sample.model.UuidEntity;\n";

    private Injector injector;
    private PojoResolverFactory pojoResolverFactory;
    private Provider<ResourceSet> resourceSetProvider;
    private IResourceValidator validator;
    private IScopeProvider scopeProvider;
    private IResourceFactory resourceFactory;
    private ModelProperty modelProperty;
    private DbResolver dbResolver;
    private Connection connection;
    private boolean skipTest = false;

    @Before
    public void setUp() throws Exception {
        // Create injector using standalone setup
        injector = new ProcessorMetaStandaloneSetup().createInjectorAndDoEMFRegistration();
        
        pojoResolverFactory = injector.getInstance(PojoResolverFactory.class);
        resourceSetProvider = injector.getProvider(ResourceSet.class);
        validator = injector.getInstance(IResourceValidator.class);
        scopeProvider = injector.getInstance(IScopeProvider.class);
        resourceFactory = injector.getInstance(IResourceFactory.class);
        modelProperty = injector.getInstance(ModelProperty.class);
        dbResolver = injector.getInstance(DbResolver.class);
        
        pojoResolverFactory.setPojoResolver(new StandalonePojoResolverImpl(""));
        
        // Try to connect to PostgreSQL and create table
        try {
            // Try multiple ways to load the driver (for IDE vs Maven environment)
            boolean driverLoaded = false;
            try {
                Class.forName("org.postgresql.Driver");
                driverLoaded = true;
            } catch (ClassNotFoundException e1) {
                // Try with context classloader
                try {
                    Thread.currentThread().getContextClassLoader().loadClass("org.postgresql.Driver");
                    driverLoaded = true;
                } catch (ClassNotFoundException e2) {
                    // Try explicit registration using reflection to avoid compile-time dependency
                    try {
                        Class<?> driverClass = Class.forName("org.postgresql.Driver");
                        java.sql.Driver driver = (java.sql.Driver) driverClass.getDeclaredConstructor().newInstance();
                        DriverManager.registerDriver(driver);
                        driverLoaded = true;
                    } catch (Exception e3) {
                        throw new ClassNotFoundException("Could not load PostgreSQL driver: " + e1.getMessage());
                    }
                }
            }
            
            if (!driverLoaded) {
                throw new ClassNotFoundException("PostgreSQL driver not available");
            }
            
            connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/simple", "simple", "simple");
            
            // Drop table if exists and recreate
            try (Statement stmt = connection.createStatement()) {
                try {
                    stmt.execute("DROP TABLE IF EXISTS UUID_ENTITY CASCADE");
                } catch (Exception e) {
                    // Ignore
                }
                stmt.execute(DDL);
            }
        } catch (Exception e) {
            System.err.println("WARNING: Could not connect to PostgreSQL or PostgreSQL driver not available. Test will be skipped.");
            System.err.println("Reason: " + e.getMessage());
            skipTest = true;
        }
    }

    @After
    public void tearDown() throws Exception {
        // Clean up - drop test table
        if (connection != null) {
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("DROP TABLE IF EXISTS UUID_ENTITY CASCADE");
            } catch (Exception e) {
                // Ignore
            }
            connection.close();
        }
    }

    @Test
    public void testGenerateMetaForUuidEntity() throws Exception {
        if (skipTest) {
            System.out.println("Test skipped - PostgreSQL not available");
            return;
        }

        // Load definitions resource
        ResourceSet resourceSet = resourceSetProvider.get();
        Resource controlResource = resourceFactory.createResource(
            URI.createURI("test.meta"));
        resourceSet.getResources().add(controlResource);
        controlResource.load(new LazyStringInputStream(DEFINITIONS), null);
        
        System.out.println("Validating control resource...");
        Set<String> failedReferences = new HashSet<String>();
        int controlResourceIsOk = MainUtils.isValid(controlResource, failedReferences, validator);
        
        // Handle reference errors (driver class and POJO class not in Eclipse classpath at compile time)
        if (controlResourceIsOk == MainUtils.REFERENCE_ERROR) {
            System.out.println("Handling reference errors: " + failedReferences);
            String controlResourceContent = MainUtils.handleResourceReferences(controlResource, failedReferences);
            controlResource = MainUtils.reloadResourceFromString(
                controlResourceContent, resourceSet, resourceFactory, "meta");
            failedReferences.clear();
            controlResourceIsOk = MainUtils.isValid(controlResource, failedReferences, validator);
            System.out.println("After handling - validation result: " + controlResourceIsOk);
            System.out.println("After handling - failed references: " + failedReferences);
        }
        
        if (controlResourceIsOk != MainUtils.OK) {
            System.err.println("Validation failed with code: " + controlResourceIsOk);
            System.err.println("Failed references: " + failedReferences);
            System.err.println("Resource errors:");
            for (org.eclipse.emf.ecore.resource.Resource.Diagnostic error : controlResource.getErrors()) {
                System.err.println("  - " + error);
            }
        }
        
        assertEquals("Control resource should be valid", MainUtils.OK, controlResourceIsOk);
        System.out.println("Control resource validated");

        // Now reload with database-is-online for generation
        String onlineDefinitions = DEFINITIONS.replace("database-is-offline", "database-is-online");
        controlResource = MainUtils.reloadResourceFromString(
            onlineDefinitions, resourceSet, resourceFactory, "meta");
        System.out.println("Reloaded with database-is-online");

        // Initialize model property and db resolver
        Artifacts definitions = (Artifacts) controlResource.getContents().get(0);
        assertFalse("Definitions should not be empty", definitions.getProperties().isEmpty());
        
        System.out.println("Tables in definitions: " + definitions.getTables().size());
        for (Object table : definitions.getTables()) {
            System.out.println("  Table: " + table);
        }
        
        ModelProperty.ModelValues modelValues = ModelPropertyBean.loadModel(null, definitions);
        modelValues.doResolveDb = true;
        System.out.println("Model values doResolveDb: " + modelValues.doResolveDb);
        modelProperty.init(modelValues);
        
        // Initialize DbResolver with DDL and PostgreSQL driver (loaded via reflection)
        Class<?> driverClass;
        try {
            driverClass = Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL driver not available", e);
        }
        dbResolver.init(modelProperty, driverClass, DDL, null);

        // Generate META SQL
        List<MetaStatement> statements = new ArrayList<MetaStatement>();
        Stats stats = new Stats();
        
        // Check what tables are available in the database
        System.out.println("Available tables from DB:");
        List<String> tables = dbResolver.getTables(definitions);
        for (String table : tables) {
            System.out.println("  - " + table);
        }
        System.out.println("Total tables: " + tables.size());
        
        System.out.println("Generating META SQL...");
        String metaDefinitions = TableMetaGenerator.generateMeta(
            definitions, 
            statements,
            ((XtextResource) controlResource).getSerializer(), 
            dbResolver, 
            scopeProvider, 
            modelProperty, 
            stats);
        
        System.out.println("Generated statements count: " + statements.size());
        
        assertNotNull("Generated META should not be null", metaDefinitions);
        System.out.println("Generated META SQL:");
        System.out.println("==================");
        System.out.println(metaDefinitions);
        System.out.println("==================");
        System.out.println("Stats: " + stats);
        
        // Write to file for debugging
        try {
            java.nio.file.Files.write(
                java.nio.file.Paths.get("target/generated-meta.sql"),
                metaDefinitions.getBytes(java.nio.charset.StandardCharsets.UTF_8)
            );
            System.out.println("Generated META written to: target/generated-meta.sql");
        } catch (Exception e) {
            System.err.println("Could not write debug file: " + e.getMessage());
        }
        
        // Verify the generated INSERT statement
        assertTrue("Should contain INSERT statement. Generated content length: " + metaDefinitions.length(), 
            metaDefinitions.contains("INSERT_UUID_ENTITY"));
        assertTrue("Should contain insert into", metaDefinitions.contains("insert into"));
        
        // Count columns in INSERT
        int columnsStart = metaDefinitions.indexOf("insert into");
        assertTrue("Should find insert statement", columnsStart >= 0);
        
        int openParen = metaDefinitions.indexOf("(", columnsStart);
        int closeParen = metaDefinitions.indexOf(")", openParen);
        String columnsPart = metaDefinitions.substring(openParen + 1, closeParen);
        
        // Count columns (they're separated by commas, could have % prefix and {? optional syntax)
        String[] columns = columnsPart.split(",");
        int columnCount = 0;
        for (String col : columns) {
            if (col.trim().length() > 0 && !col.trim().startsWith("//")) {
                columnCount++;
            }
        }
        
        // Count values in VALUES clause
        int valuesStart = metaDefinitions.indexOf("values", closeParen);
        assertTrue("Should find values clause", valuesStart >= 0);
        
        int valuesOpenParen = metaDefinitions.indexOf("(", valuesStart);
        int valuesCloseParen = metaDefinitions.indexOf(")", valuesOpenParen);
        String valuesPart = metaDefinitions.substring(valuesOpenParen + 1, valuesCloseParen);
        
        // Count values (they're parameters starting with :)
        String[] values = valuesPart.split(",");
        int valueCount = 0;
        for (String val : values) {
            String trimmed = val.trim();
            if (trimmed.length() > 0 && !trimmed.startsWith("//") && !trimmed.startsWith("}")) {
                valueCount++;
            }
        }
        
        System.out.println("Column count: " + columnCount);
        System.out.println("Value count: " + valueCount);
        System.out.println("Columns: " + columnsPart);
        System.out.println("Values: " + valuesPart);
        
        // This is the assertion that should catch the bug
        assertEquals("INSERT should have same number of columns and values", 
            columnCount, valueCount);
        
        // Additional verification: ensure ID (PK with identity) is handled correctly
        // It should either be in both columns and values, or in neither (but handled by identity)
        boolean idInColumns = columnsPart.toUpperCase().contains("ID");
        boolean idInValues = valuesPart.toLowerCase().contains(":id");
        
        System.out.println("ID in columns: " + idInColumns);
        System.out.println("ID in values: " + idInValues);
        
        // Also check that MYID (non-PK UUID with default) is handled consistently
        boolean myidInColumns = columnsPart.toUpperCase().contains("MYID");
        boolean myidInValues = valuesPart.toLowerCase().contains(":myid");
        
        System.out.println("MYID in columns: " + myidInColumns);
        System.out.println("MYID in values: " + myidInValues);
        
        if (myidInColumns) {
            assertTrue("If MYID is in columns, it should be in values", myidInValues);
        }
    }
}
