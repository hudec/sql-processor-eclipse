package org.sqlproc.model.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
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
import org.sqlproc.model.ProcessorModelStandaloneSetup;
import org.sqlproc.model.generator.TableDaoGenerator;
import org.sqlproc.model.generator.TablePojoGenerator;
import org.sqlproc.model.processorModel.Artifacts;
import org.sqlproc.model.property.ModelPropertyBean;
import org.sqlproc.plugin.lib.property.ModelProperty;
import org.sqlproc.plugin.lib.resolver.DbResolver;
import org.sqlproc.plugin.lib.resolver.PojoResolverFactory;
import org.sqlproc.plugin.lib.resolver.StandalonePojoResolverImpl;
import org.sqlproc.plugin.lib.util.MainUtils;
import org.sqlproc.plugin.lib.util.Stats;

import com.google.inject.Injector;
import com.google.inject.Provider;

/**
 * Integration test for POJO generation with UUID_ENTITY DDL against PostgreSQL.
 */
public class MainPostgresIntegrationTest {

    // @formatter:off
    private static final String EXPECTED_POJO =
        "\n" +
        "    #Serializable(1)\n" +
        "    #Enum ContactCtype {\n" +
        "        #Values(ABROAD = 2,BUSINESS = 1,HOME = 0)\n" +
        "        #Attr Integer value\n" +
        "    }\n" +
        "\n" +
        "    #Serializable(1)\n" +
        "    #Enum PersonGender {\n" +
        "        #Values(FEMALE = \"F\",MALE = \"M\",UNKNOWN = \"0\")\n" +
        "        #Attr String value\n" +
        "    }\n" +
        "\n" +
        "    #Operators #Serializable(1) #Equals(id) #HashCode(id) #ToString(id,ctype,address,phoneNumber)\n" +
        "    #Pojo Contact {\n" +
        "        #PrimaryKey #Index\n" +
        "        #Attr Long id // bigserial(19)\n" +
        "        #ToInit #Required #IsPojo\n" +
        "        #Attr Person person // bigserial(19)\n" +
        "        #Required\n" +
        "        #Attr ContactCtype ctype // int4(10)\n" +
        "        #IsDef\n" +
        "        #Attr String address // varchar(100)\n" +
        "        #IsDef\n" +
        "        #Attr String phoneNumber // varchar(100)\n" +
        "    }\n" +
        "\n" +
        "    #Operators #Serializable(1) #Equals(id) #HashCode(id) #ToString(id,firstName,lastName,dateOfBirth,gender,ssn)\n" +
        "    #Pojo Person {\n" +
        "        #PrimaryKey #Index\n" +
        "        #Attr Long id // bigserial(19)\n" +
        "        #Required\n" +
        "        #Attr String firstName // varchar(100)\n" +
        "        #Required #Index\n" +
        "        #Attr String lastName // varchar(100)\n" +
        "        #IsDef\n" +
        "        #Attr java.time.LocalDate dateOfBirth // date(13)\n" +
        "        #Required\n" +
        "        #Attr PersonGender gender // varchar(1)\n" +
        "        #IsDef\n" +
        "        #Attr String ssn // varchar(100)\n" +
        "        #ToInit\n" +
        "        #Attr java.util.List <Contact> contacts // null\n" +
        "    }\n" +
        "\n" +
        "    #Serializable(1) #ToString(t)\n" +
        "    #Pojo AnHourBefore {\n" +
        "        #Attr java.time.LocalDateTime t // timestamp\n" +
        "    }\n" +
        "\n" +
        "    #Serializable(1) #ToString(newid,dateOfBirth,ssn,firstName,lastName,gender)\n" +
        "    #Pojo NewPerson {\n" +
        "        #Attr Long newid // int8\n" +
        "        #Attr java.time.LocalDate dateOfBirth // date\n" +
        "        #Attr String ssn // varchar\n" +
        "        #Attr String firstName // varchar\n" +
        "        #Attr String lastName // varchar\n" +
        "        #Attr String gender // varchar\n" +
        "    }\n" +
        "\n" +
        "    #Serializable(1) #ToString(dateOfBirth,ssn,firstName,lastName,gender)\n" +
        "    #Pojo NewPersonRetRs {\n" +
        "        #Attr java.time.LocalDate dateOfBirth // date\n" +
        "        #Attr String ssn // varchar\n" +
        "        #Attr String firstName // varchar\n" +
        "        #Attr String lastName // varchar\n" +
        "        #Attr String gender // varchar\n" +
        "    }\n" +
        "\n" +
        "    #Operators #Serializable(1) #Equals(id) #HashCode(id) #ToString(id,tUuid,tOffsetDateTime)\n" +
        "    #Pojo TypesExt {\n" +
        "        #PrimaryKey #Index\n" +
        "        #Attr Long id // bigserial(19)\n" +
        "        #IsDef\n" +
        "        #Attr Object tUuid // uuid(2147483647)\n" +
        "        #IsDef\n" +
        "        #Attr java.time.OffsetDateTime tOffsetDateTime // timestamptz(35)\n" +
        "    }\n" +
        "\n" +
        "    #Operators #Serializable(1) #Equals(id) #HashCode(id) #ToString(id,myid,name,description)\n" +
        "    #Pojo UuidEntity {\n" +
        "        #PrimaryKey #Index\n" +
        "        #Attr Object id // uuid(2147483647)\n" +
        "        #Required\n" +
        "        #Attr Object myid // uuid(2147483647)\n" +
        "        #Required\n" +
        "        #Attr String name // varchar(100)\n" +
        "        #IsDef\n" +
        "        #Attr String description // varchar(500)\n" +
        "    }\n";
    // @formatter:on

    // @formatter:off
    private static final String EXPECTED_DAO =
        "\n" +
        "    #CRUD(org.sample.model.Contact) #Query(org.sample.model.Contact)\n" +
        "    #Dao ContactDao {\n" +
        "    }\n" +
        "\n" +
        "    #CRUD(org.sample.model.Person) #Query(org.sample.model.Person)\n" +
        "    #Dao PersonDao {\n" +
        "    }\n" +
        "\n" +
        "    #CRUD(org.sample.model.TypesExt) #Query(org.sample.model.TypesExt)\n" +
        "    #Dao TypesExtDao {\n" +
        "    }\n" +
        "\n" +
        "    #CRUD(org.sample.model.UuidEntity) #Query(org.sample.model.UuidEntity)\n" +
        "    #Dao UuidEntityDao {\n" +
        "    }\n" +
        "\n" +
        "    #ProcedureUpdate(int,org.sample.model.NewPerson)\n" +
        "    #Dao NewPersonDao {\n" +
        "    }\n" +
        "\n" +
        "    #ProcedureCallQuery(java.util.List<org.sample.model.Person>,org.sample.model.NewPersonRetRs)\n" +
        "    #Dao NewPersonRetRsDao {\n" +
        "    }\n" +
        "\n" +
        "    #FunctionCall(java.time.LocalDateTime,org.sample.model.AnHourBefore)\n" +
        "    #Dao AnHourBeforeDao {\n" +
        "    }\n";
    // @formatter:on

    // @formatter:off
    private static final String DDL = 
        "DROP TABLE IF EXISTS CONTACT CASCADE;\n" +
        "DROP TABLE IF EXISTS PERSON CASCADE;\n" +
        "DROP TABLE IF EXISTS TYPES_EXT CASCADE;\n" +
        "DROP TABLE IF EXISTS UUID_ENTITY CASCADE;\n" +
        "\n" +
        "DROP FUNCTION IF EXISTS AN_HOUR_BEFORE(TIMESTAMP);\n" +
        "\n" +
        "CREATE TABLE PERSON (\n" +
        "  ID BIGSERIAL,\n" +
        "  FIRST_NAME VARCHAR(100) NOT NULL,\n" +
        "  LAST_NAME VARCHAR(100) NOT NULL,\n" +
        "  DATE_OF_BIRTH DATE,\n" +
        "  GENDER VARCHAR(1) NOT NULL,\n" +
        "  SSN VARCHAR(100)\n" +
        ");\n" +
        "\n" +
        "ALTER TABLE PERSON ADD CONSTRAINT PK_PERSON\n" +
        "\tPRIMARY KEY (ID);\n" +
        "\n" +
        "ALTER TABLE PERSON ADD CONSTRAINT CHECK_PERSON_GENDER\n" +
        "    CHECK (GENDER IN ('M', 'F', '0'))\n" +
        ";\n" +
        "\n" +
        "CREATE  INDEX IX_PERSON_LAST_NAME ON PERSON (LAST_NAME);\n" +
        "\n" +
        "CREATE TABLE CONTACT (\n" +
        "  ID BIGSERIAL,\n" +
        "  PERSON_ID BIGSERIAL NOT NULL,\n" +
        "  CTYPE INT NOT NULL,\n" +
        "  ADDRESS VARCHAR(100),\n" +
        "  PHONE_NUMBER VARCHAR(100)\n" +
        ");\n" +
        "\n" +
        "ALTER TABLE CONTACT ADD CONSTRAINT PK_CONTACT\n" +
        "\tPRIMARY KEY (ID);\n" +
        "\n" +
        "ALTER TABLE CONTACT ADD CONSTRAINT FK_CONTACT_PERSON\n" +
        "\tFOREIGN KEY (PERSON_ID) REFERENCES PERSON (ID) ON DELETE CASCADE;\n" +
        "\n" +
        "ALTER TABLE CONTACT ADD CONSTRAINT CHECK_CONTACT_TYPE\n" +
        "    CHECK (CTYPE IN (0, 1, 2))\n" +
        ";\n" +
        "\n" +
        "CREATE TABLE TYPES_EXT (\n" +
        "  ID BIGSERIAL,\n" +
        "  T_UUID UUID,\n" +
        "  T_OFFSET_DATE_TIME TIMESTAMP WITH TIME ZONE\n" +
        ");\n" +
        "\n" +
        "ALTER TABLE TYPES_EXT ADD CONSTRAINT PK_TYPES_EXT\n" +
        "\tPRIMARY KEY (ID);\n" +
        "\n" +
        "CREATE TABLE UUID_ENTITY (\n" +
        "  ID UUID DEFAULT gen_random_uuid() NOT NULL,\n" +
        "  MYID UUID DEFAULT gen_random_uuid() NOT NULL,\n" +
        "  NAME VARCHAR(100) NOT NULL,\n" +
        "  DESCRIPTION VARCHAR(500)\n" +
        ");\n" +
        "\n" +
        "ALTER TABLE UUID_ENTITY ADD CONSTRAINT PK_UUID_ENTITY\n" +
        "\tPRIMARY KEY (ID);\n" +
        "\n" +
        "CREATE OR REPLACE FUNCTION AN_HOUR_BEFORE(T TIMESTAMP) RETURNS TIMESTAMP\n" +
        "LANGUAGE SQL\n" +
        "AS $$\n" +
        "  SELECT $1 - interval '1 HOUR'\n" +
        "$$;\n" +
        "\n" +
        "CREATE OR REPLACE FUNCTION NEW_PERSON(OUT newid BIGINT, IN date_of_birth DATE, IN ssn VARCHAR(20), IN first_name VARCHAR(100), IN last_name VARCHAR(100), IN gender VARCHAR(1))\n" +
        "LANGUAGE plpgsql\n" +
        "AS $$\n" +
        "  BEGIN\n" +
        "    INSERT INTO PERSON VALUES (DEFAULT, first_name, last_name, date_of_birth, gender, ssn);\n" +
        "    newid := currval('person_id_seq');\n" +
        "  END;\n" +
        "$$;\n" +
        "\n" +
        "CREATE OR REPLACE FUNCTION NEW_PERSON_RET_RS(IN date_of_birth DATE, IN ssn VARCHAR(20), IN first_name VARCHAR(100), IN last_name VARCHAR(100), IN gender VARCHAR(1)) RETURNS REFCURSOR\n" +
        "LANGUAGE plpgsql\n" +
        "AS $$\n" +
        "  DECLARE\n" +
        "    result REFCURSOR;\n" +
        "  BEGIN\n" +
        "    INSERT INTO PERSON VALUES (DEFAULT, first_name, last_name, date_of_birth, gender, ssn);\n" +
        "    OPEN result FOR SELECT * FROM PERSON WHERE ID = currval('person_id_seq');\n" +
        "    RETURN result;\n" +
        "  END;\n" +
        "$$;\n";
    // @formatter:on

    // @formatter:off
    private static final String DEFINITIONS =
        "resolve-pojo-on;\n" +
        "database-is-offline;\n" +
        "database-jdbc-driver ::org.postgresql.Driver;\n" +
        "database-has-url \"jdbc:postgresql://localhost:5432/simple\";\n" +
        "database-login-username simple;\n" +
        "database-login-password simple;\n" +
        "database-in-catalog simple;\n" +
        "//database-active-schema simple;\n" +
        "//database-ddl-create postgresql.ddl; // should be located in the same directory as definitions.qry\n" +
        "database-show-database-info \"PostgreSQL,10.3 (Debian 10.3-1.pgdg90+1),10,3\";\n" +
        "database-show-driver-info \"PostgreSQL JDBC Driver,42.2.2,42,2\";\n" +
        "//database-is-of-type PostgreSQL;\n" +
        "\n" +
        "// native types\n" +
        "pojogen-generate-wrappers;\n" +
        "// POJO helpers' methods\n" +
        "pojogen-generate-methods toString equals hashCode isDef toInit;\n" +
        "// POJO are serializable\n" +
        "pojogen-implements-interfaces java.io.Serializable;\n" +
        "// can't overwrite existing POJO\n" +
        "//pojogen-make-it-final;\n" +
        "// rename enums\n" +
        "pojogen-rename-columns contact_ctype I0->HOME I1->BUSINESS I2->ABROAD;\n" +
        "pojogen-rename-columns person_gender M->MALE F->FEMALE I0->UNKNOWN;\n" +
        "// operators\n" +
        "pojogen-generate-operators;\n" +
        "\n" +
        "pojogen-debug-level DEBUG;\n" +
        "\n" +
        "// use identity for primary keys\n" +
        "metagen-table-identity contact CONTACT;\n" +
        "metagen-table-identity uuid_entity UUID_ENTITY;\n" +
        "//metagen-table-sequence contact contact_id_seq;\n" +
        "metagen-table-sequence person person_id_seq;\n" +
        "metagen-generate-idgenerators;\n" +
        "//metagen-generate-default-idgenerators;\n" +
        "// can't overwrite existing META SQL\n" +
        "//metagen-make-it-final;\n" +
        "// operators\n" +
        "metagen-not-like-columns contact phone_number;\n" +
        "\n" +
        "// can't overwrite existing DAO\n" +
        "//daogen-make-it-final;\n" +
        "\n" +
        "is-table contact contact;\n" +
        "is-table person person;\n" +
        "is-table types_ext types_ext;\n" +
        "is-table uuid_entity uuid_entity;\n" +
        "is-procedure anHourBefore an_hour_before;\n" +
        "is-procedure newPerson new_person;\n" +
        "is-procedure newPersonRetRs new_person_ret_rs;\n" +
        "is-function anHourBefore an_hour_before;\n" +
        "\n" +
        "//metagen-function-result an_hour_before stamp;\n" +
        "metagen-procedure-result-set new_person_ret_rs person;\n" +
        "metagen-debug-level DEBUG;\n" +
        "compress-meta-directives;\n" +
        "\n" +
        "pojogen-package org.sample.model;\n" +
        "daogen-package org.sample.dao;\n" +
        "\n" +
        "is-pojo AnHourBefore org.sample.model.AnHourBefore;\n" +
        "is-pojo Contact org.sample.model.Contact;\n" +
        "is-pojo NewPerson org.sample.model.NewPerson;\n" +
        "is-pojo NewPersonRetRs org.sample.model.NewPersonRetRs;\n" +
        "is-pojo Person org.sample.model.Person;\n" +
        "is-pojo TypesExt org.sample.model.TypesExt;\n" +
        "is-pojo UuidEntity org.sample.model.UuidEntity;\n";
    // @formatter:on

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
        injector = new ProcessorModelStandaloneSetup().createInjectorAndDoEMFRegistration();

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

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/simple", "simple", "simple");
        } catch (Exception e) {
            System.err.println(
                    "WARNING: Could not connect to PostgreSQL or PostgreSQL driver not available. Test will be skipped.");
            System.err.println("Reason: " + e.getMessage());
            skipTest = true;
        }
    }

    @After
    public void tearDown() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testGeneratePojoForAllTables() throws Exception {
        if (skipTest) {
            System.out.println("Test skipped - PostgreSQL not available");
            return;
        }

        // Load definitions resource
        ResourceSet resourceSet = resourceSetProvider.get();
        Resource controlResource = resourceFactory.createResource(URI.createURI("test.model"));
        resourceSet.getResources().add(controlResource);
        controlResource.load(new LazyStringInputStream(DEFINITIONS), null);

        System.out.println("Validating control resource...");
        Set<String> failedReferences = new HashSet<String>();
        int controlResourceIsOk = MainUtils.isValid(controlResource, failedReferences, validator);

        // Handle reference errors (driver class and POJO class not in Eclipse classpath at compile time)
        if (controlResourceIsOk == MainUtils.REFERENCE_ERROR) {
            System.out.println("Handling reference errors: " + failedReferences);
            String controlResourceContent = MainUtils.handleResourceReferences(controlResource, failedReferences);
            controlResource = MainUtils.reloadResourceFromString(controlResourceContent, resourceSet, resourceFactory,
                    "model");
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
        controlResource = MainUtils.reloadResourceFromString(onlineDefinitions, resourceSet, resourceFactory, "model");
        System.out.println("Reloaded with database-is-online");

        // Initialize model property and db resolver
        Artifacts definitions = (Artifacts) controlResource.getContents().get(0);
        assertFalse("Definitions should not be empty", definitions.getProperties().isEmpty());

        ModelProperty.ModelValues modelValues = ModelPropertyBean.loadModel(null, definitions);
        modelValues.doResolveDb = true;
        modelProperty.init(modelValues);

        // Initialize DbResolver with DDL and PostgreSQL driver (loaded via reflection)
        Class<?> driverClass;
        try {
            driverClass = Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL driver not available", e);
        }
        dbResolver.init(modelProperty, driverClass, DDL, null);

        // Generate POJOs
        Stats stats = new Stats();

        // Check what tables are available in the database
        System.out.println("Available tables from DB:");
        java.util.List<String> tables = dbResolver.getTables(definitions);
        for (String table : tables) {
            System.out.println("  - " + table);
        }
        System.out.println("Total tables: " + tables.size());

        System.out.println("Generating POJOs...");
        String pojoDefinitions = TablePojoGenerator.generatePojo(definitions, null,
                ((XtextResource) controlResource).getSerializer(), dbResolver, scopeProvider, modelProperty, stats);

        assertNotNull("Generated POJO should not be null", pojoDefinitions);
        System.out.println("Generated POJO definitions:");
        System.out.println("==================");
        System.out.println(pojoDefinitions);
        System.out.println("==================");
        System.out.println("Stats: " + stats);

        // Write to file for debugging
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get("target/generated-pojo.model"),
                    pojoDefinitions.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            System.out.println("Generated POJO written to: target/generated-pojo.model");
        } catch (Exception e) {
            System.err.println("Could not write debug file: " + e.getMessage());
        }

        // Verify POJO definitions using structured comparison (same logic as meta test)
        Map<String, String> expectedStatements = parsePojoDefinitions(EXPECTED_POJO);
        Map<String, String> actualStatements = parsePojoDefinitions(pojoDefinitions);

        // Statements to temporarily ignore during comparison
        Set<String> ignoredStatements = new HashSet<String>();
//        ignoredStatements.add("UuidEntity");

        // Compare statement keys (order-independent)
        Set<String> expectedKeys = new HashSet<String>(expectedStatements.keySet());
        Set<String> actualKeys = new HashSet<String>(actualStatements.keySet());
        expectedKeys.removeAll(ignoredStatements);
        actualKeys.removeAll(ignoredStatements);

        Set<String> missingKeys = new HashSet<String>(expectedKeys);
        missingKeys.removeAll(actualKeys);

        Set<String> extraKeys = new HashSet<String>(actualKeys);
        extraKeys.removeAll(expectedKeys);

        if (!missingKeys.isEmpty() || !extraKeys.isEmpty()) {
            StringBuilder sb = new StringBuilder("POJO definition names mismatch.");
            if (!missingKeys.isEmpty()) {
                sb.append(" Missing: ").append(missingKeys).append(".");
            }
            if (!extraKeys.isEmpty()) {
                sb.append(" Extra: ").append(extraKeys).append(".");
            }
            assertEquals(sb.toString(), expectedKeys, actualKeys);
        }

        // Compare each individual POJO/Enum definition
        for (Map.Entry<String, String> entry : expectedStatements.entrySet()) {
            String name = entry.getKey();
            if (ignoredStatements.contains(name)) {
                continue;
            }
            String expectedDef = entry.getValue();
            String actualDef = actualStatements.get(name);
            assertNotNull("Missing POJO/Enum definition: " + name, actualDef);
            assertEquals("POJO/Enum definition mismatch for: " + name, expectedDef, actualDef);
        }

        // Generate DAOs
        Stats daoStats = new Stats();
        System.out.println("Generating DAOs...");
        String daoDefinitions = TableDaoGenerator.generateDao(definitions, null,
                ((XtextResource) controlResource).getSerializer(), dbResolver, scopeProvider, modelProperty, daoStats);

        assertNotNull("Generated DAO should not be null", daoDefinitions);
        System.out.println("Generated DAO definitions:");
        System.out.println("==================");
        System.out.println(daoDefinitions);
        System.out.println("==================");
        System.out.println("DAO Stats: " + daoStats);

        // Write to file for debugging
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get("target/generated-dao.model"),
                    daoDefinitions.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            System.out.println("Generated DAO written to: target/generated-dao.model");
        } catch (Exception e) {
            System.err.println("Could not write debug file: " + e.getMessage());
        }

        // Verify DAO definitions using structured comparison
        Map<String, String> expectedDaoStatements = parseDaoDefinitions(EXPECTED_DAO);
        Map<String, String> actualDaoStatements = parseDaoDefinitions(daoDefinitions);

        // Statements to temporarily ignore during comparison
        Set<String> ignoredDaoStatements = new HashSet<String>();
//        ignoredDaoStatements.add("UuidEntityDao");

        // Compare statement keys (order-independent)
        Set<String> expectedDaoKeys = new HashSet<String>(expectedDaoStatements.keySet());
        Set<String> actualDaoKeys = new HashSet<String>(actualDaoStatements.keySet());
        expectedDaoKeys.removeAll(ignoredDaoStatements);
        actualDaoKeys.removeAll(ignoredDaoStatements);

        Set<String> missingDaoKeys = new HashSet<String>(expectedDaoKeys);
        missingDaoKeys.removeAll(actualDaoKeys);

        Set<String> extraDaoKeys = new HashSet<String>(actualDaoKeys);
        extraDaoKeys.removeAll(expectedDaoKeys);

        if (!missingDaoKeys.isEmpty() || !extraDaoKeys.isEmpty()) {
            StringBuilder sb = new StringBuilder("DAO definition names mismatch.");
            if (!missingDaoKeys.isEmpty()) {
                sb.append(" Missing: ").append(missingDaoKeys).append(".");
            }
            if (!extraDaoKeys.isEmpty()) {
                sb.append(" Extra: ").append(extraDaoKeys).append(".");
            }
            assertEquals(sb.toString(), expectedDaoKeys, actualDaoKeys);
        }

        // Compare each individual DAO definition
        for (Map.Entry<String, String> entry : expectedDaoStatements.entrySet()) {
            String name = entry.getKey();
            if (ignoredDaoStatements.contains(name)) {
                continue;
            }
            String expectedDef = entry.getValue();
            String actualDef = actualDaoStatements.get(name);
            assertNotNull("Missing DAO definition: " + name, actualDef);
            assertEquals("DAO definition mismatch for: " + name, expectedDef, actualDef);
        }
    }

    /**
     * Parses POJO definition text into a map of individual definitions keyed by POJO/Enum name.
     * Each definition block starts with annotation lines (e.g. #Serializable, #Operators) followed by
     * #Pojo Name { or #Enum Name { and ends with the closing }.
     */
    private static Map<String, String> parsePojoDefinitions(String pojoText) {
        Map<String, String> definitions = new LinkedHashMap<String, String>();
        String[] lines = pojoText.split("\n");
        StringBuilder currentBlock = null;
        String currentName = null;
        int braceDepth = 0;

        for (String line : lines) {
            String trimmed = line.trim();

            if (trimmed.isEmpty()) {
                // Empty line may separate blocks
                if (currentBlock == null) {
                    continue;
                }
            }

            // Detect start of a new block: annotation line starting with #
            if (currentBlock == null && trimmed.startsWith("#")) {
                currentBlock = new StringBuilder(line);
                // Check if this line itself contains #Pojo or #Enum
                String name = extractPojoName(trimmed);
                if (name != null) {
                    currentName = name;
                }
                if (trimmed.contains("{")) {
                    braceDepth++;
                }
                continue;
            }

            if (currentBlock != null) {
                currentBlock.append("\n").append(line);

                // Check for name if not yet found
                if (currentName == null) {
                    String name = extractPojoName(trimmed);
                    if (name != null) {
                        currentName = name;
                    }
                }

                // Track braces
                if (trimmed.contains("{")) {
                    braceDepth++;
                }
                if (trimmed.contains("}")) {
                    braceDepth--;
                    if (braceDepth <= 0) {
                        // Block is complete
                        if (currentName != null) {
                            definitions.put(currentName, currentBlock.toString());
                        }
                        currentBlock = null;
                        currentName = null;
                        braceDepth = 0;
                    }
                }
            }
        }

        // Handle last block if not closed
        if (currentName != null && currentBlock != null) {
            definitions.put(currentName, currentBlock.toString());
        }

        return definitions;
    }

    /**
     * Extracts the POJO or Enum name from a line like "#Pojo Contact {" or "#Enum PersonGender {".
     */
    private static String extractPojoName(String line) {
        int pojoIdx = line.indexOf("#Pojo ");
        int enumIdx = line.indexOf("#Enum ");
        int idx = -1;
        int prefixLen = 0;
        if (pojoIdx >= 0) {
            idx = pojoIdx;
            prefixLen = "#Pojo ".length();
        } else if (enumIdx >= 0) {
            idx = enumIdx;
            prefixLen = "#Enum ".length();
        }
        if (idx < 0) {
            return null;
        }
        String rest = line.substring(idx + prefixLen).trim();
        // Name is the first word
        int spaceIdx = rest.indexOf(' ');
        int braceIdx = rest.indexOf('{');
        int endIdx = rest.length();
        if (spaceIdx >= 0) endIdx = Math.min(endIdx, spaceIdx);
        if (braceIdx >= 0) endIdx = Math.min(endIdx, braceIdx);
        return rest.substring(0, endIdx).trim();
    }

    /**
     * Parses DAO definition text into a map of individual definitions keyed by DAO name.
     * Each definition block starts with annotation lines (e.g. #CRUD, #Query, #ProcedureUpdate) followed by
     * #Dao Name { and ends with the closing }.
     */
    private static Map<String, String> parseDaoDefinitions(String daoText) {
        Map<String, String> definitions = new LinkedHashMap<String, String>();
        String[] lines = daoText.split("\n");
        StringBuilder currentBlock = null;
        String currentName = null;
        int braceDepth = 0;

        for (String line : lines) {
            String trimmed = line.trim();

            if (trimmed.isEmpty()) {
                if (currentBlock == null) {
                    continue;
                }
            }

            // Detect start of a new block: annotation line starting with #
            if (currentBlock == null && trimmed.startsWith("#")) {
                currentBlock = new StringBuilder(line);
                String name = extractDaoName(trimmed);
                if (name != null) {
                    currentName = name;
                }
                if (trimmed.contains("{")) {
                    braceDepth++;
                }
                continue;
            }

            if (currentBlock != null) {
                currentBlock.append("\n").append(line);

                if (currentName == null) {
                    String name = extractDaoName(trimmed);
                    if (name != null) {
                        currentName = name;
                    }
                }

                if (trimmed.contains("{")) {
                    braceDepth++;
                }
                if (trimmed.contains("}")) {
                    braceDepth--;
                    if (braceDepth <= 0) {
                        if (currentName != null) {
                            definitions.put(currentName, currentBlock.toString());
                        }
                        currentBlock = null;
                        currentName = null;
                        braceDepth = 0;
                    }
                }
            }
        }

        if (currentName != null && currentBlock != null) {
            definitions.put(currentName, currentBlock.toString());
        }

        return definitions;
    }

    /**
     * Extracts the DAO name from a line like "#Dao ContactDao {".
     */
    private static String extractDaoName(String line) {
        int daoIdx = line.indexOf("#Dao ");
        if (daoIdx < 0) {
            return null;
        }
        String rest = line.substring(daoIdx + "#Dao ".length()).trim();
        int spaceIdx = rest.indexOf(' ');
        int braceIdx = rest.indexOf('{');
        int endIdx = rest.length();
        if (spaceIdx >= 0) endIdx = Math.min(endIdx, spaceIdx);
        if (braceIdx >= 0) endIdx = Math.min(endIdx, braceIdx);
        return rest.substring(0, endIdx).trim();
    }
}
