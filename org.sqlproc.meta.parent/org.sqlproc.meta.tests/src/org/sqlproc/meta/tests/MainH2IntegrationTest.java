package org.sqlproc.meta.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
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
 * Integration test for Main.generate with UUID_ENTITY DDL against H2 in-memory database. This test reproduces the
 * issue where INSERT has more target columns than value expressions.
 */
public class MainH2IntegrationTest {

    // @formatter:off
    private static final String EXPECTED_META =
        "SEQ=person_id_seq(OPT)=call next value for person_id_seq;\n" +
        "IDSEL=CONTACT(OPT)=call identity();\n" +
        "IDSEL=PERSON(OPT)=call identity();\n" +
        "\n" +
        "INSERT_CONTACT(CRUD,in=Contact,out=Contact,tab=contact)=\n" +
        "  insert into %%contact (%id, %person_id, %ctype, %address, %phone_number)\n" +
        "  {= values (:id(idgen=CONTACT,id=id), :person.id, :ctype, :address, :phoneNumber) }\n" +
        ";\n" +
        "\n" +
        "GET_CONTACT(CRUD,in=Contact,out=Contact,tab=contact=c,tab=person=p)=\n" +
        "  select %c.id @id(id), %c.person_id @person.id(id), %c.ctype @ctype, %c.address @address, %c.phone_number @phoneNumber\n" +
        "         {? :person(call=toInit) | , %p.first_name @person.firstName, %p.last_name @person.lastName, %p.date_of_birth @person.dateOfBirth, %p.gender @person.gender, %p.ssn @person.ssn }\n" +
        "  from %%contact c\n" +
        "  {? :person(call=toInit) | left join %%person p on %c.person_id = %p.id }\n" +
        "  {= where\n" +
        "    {& %c.id ::= :id }\n" +
        "    {& %c.person_id ::= :person.id }\n" +
        "    {& %c.ctype ::= :ctype }\n" +
        "    {& %c.address ::= :address }\n" +
        "    {& %c.phone_number ::= :phoneNumber }\n" +
        "  }\n" +
        ";\n" +
        "\n" +
        "UPDATE_CONTACT(CRUD,in=Contact,out=Contact,tab=contact)=\n" +
        "  update %%contact\n" +
        "  {= set\n" +
        "    { ,%person_id = :person.id(call=isDef) }\n" +
        "    { ,%ctype = :ctype(call=isDef) }\n" +
        "    { ,%address = :address(call=isDef) }\n" +
        "    { ,%phone_number = :phoneNumber(call=isDef) }\n" +
        "  }\n" +
        "  {= where\n" +
        "    {& %id = :id(!empty) }\n" +
        "  }\n" +
        ";\n" +
        "\n" +
        "DELETE_CONTACT(CRUD,in=Contact,out=Contact,tab=contact)=\n" +
        "  delete from %%contact\n" +
        "  {= where\n" +
        "    {& %id = :id(!empty) }\n" +
        "  }\n" +
        ";\n" +
        "\n" +
        "SELECT_CONTACT(QRY,in=Contact,out=Contact,tab=contact=c,tab=person=p)=\n" +
        "  select %c.id @id(id), %c.person_id @person.id(id), %c.ctype @ctype, %c.address @address, %c.phone_number @phoneNumber\n" +
        "         {? :person(call=toInit) | , %p.first_name @person.firstName, %p.last_name @person.lastName, %p.date_of_birth @person.dateOfBirth, %p.gender @person.gender, %p.ssn @person.ssn }\n" +
        "  from %%contact c\n" +
        "  {? :person(call=toInit) | left join %%person p on %c.person_id = %p.id }\n" +
        "  {= where\n" +
        "    {& %c.id ::= :id }\n" +
        "    {& %c.person_id ::= :person.id }\n" +
        "    {& %c.ctype ::= :ctype }\n" +
        "    {& UPPER(%c.address) like :+address }\n" +
        "    {& %c.phone_number ::= :phoneNumber }\n" +
        "  }\n" +
        "  {#ID order by %c.id }\n" +
        ";\n" +
        "\n" +
        "INSERT_PERSON(CRUD,in=Person,out=Person,tab=person)=\n" +
        "  insert into %%person (%id, %first_name, %last_name, %date_of_birth, %gender, %ssn)\n" +
        "  {= values (:id(idgen=person_id_seq,id=id), :firstName, :lastName, :dateOfBirth, :gender, :ssn) }\n" +
        ";\n" +
        "\n" +
        "GET_PERSON(CRUD,in=Person,out=Person,tab=person=p,tab=contact=c)=\n" +
        "  select %p.id @id(id), %p.first_name @firstName, %p.last_name @lastName, %p.date_of_birth @dateOfBirth, %p.gender @gender, %p.ssn @ssn\n" +
        "         {? :contacts(call=toInit) | , %c.id @contacts.id(id), %c.person_id @contacts.person.id, %c.ctype @contacts.ctype, %c.address @contacts.address, %c.phone_number @contacts.phoneNumber }\n" +
        "  from %%person p\n" +
        "  {? :contacts(call=toInit) | left join %%contact c on %p.id = %c.person_id }\n" +
        "  {= where\n" +
        "    {& %p.id ::= :id }\n" +
        "    {& %p.first_name ::= :firstName }\n" +
        "    {& %p.last_name ::= :lastName }\n" +
        "    {& %p.date_of_birth ::= :dateOfBirth }\n" +
        "    {& %p.gender ::= :gender }\n" +
        "    {& %p.ssn ::= :ssn }\n" +
        "  }\n" +
        ";\n" +
        "\n" +
        "UPDATE_PERSON(CRUD,in=Person,out=Person,tab=person)=\n" +
        "  update %%person\n" +
        "  {= set\n" +
        "    { ,%first_name = :firstName(call=isDef) }\n" +
        "    { ,%last_name = :lastName(call=isDef) }\n" +
        "    { ,%date_of_birth = :dateOfBirth(call=isDef) }\n" +
        "    { ,%gender = :gender(call=isDef) }\n" +
        "    { ,%ssn = :ssn(call=isDef) }\n" +
        "  }\n" +
        "  {= where\n" +
        "    {& %id = :id(!empty) }\n" +
        "  }\n" +
        ";\n" +
        "\n" +
        "DELETE_PERSON(CRUD,in=Person,out=Person,tab=person)=\n" +
        "  delete from %%person\n" +
        "  {= where\n" +
        "    {& %id = :id(!empty) }\n" +
        "  }\n" +
        ";\n" +
        "\n" +
        "SELECT_PERSON(QRY,in=Person,out=Person,tab=person=p,tab=contact=c)=\n" +
        "  select %p.id @id(id), %p.first_name @firstName, %p.last_name @lastName, %p.date_of_birth @dateOfBirth, %p.gender @gender, %p.ssn @ssn\n" +
        "         {? :contacts(call=toInit) | , %c.id @contacts.id(id), %c.person_id @contacts.person.id, %c.ctype @contacts.ctype, %c.address @contacts.address, %c.phone_number @contacts.phoneNumber }\n" +
        "  from %%person p\n" +
        "  {? :contacts(call=toInit) | left join %%contact c on %p.id = %c.person_id }\n" +
        "  {= where\n" +
        "    {& %p.id ::= :id }\n" +
        "    {& UPPER(%p.first_name) like :+firstName }\n" +
        "    {& UPPER(%p.last_name) like :+lastName }\n" +
        "    {& %p.date_of_birth ::= :dateOfBirth }\n" +
        "    {& UPPER(%p.gender) like :+gender }\n" +
        "    {& UPPER(%p.ssn) like :+ssn }\n" +
        "  }\n" +
        "  {#ID order by %p.id }\n" +
        "  {#LAST_NAME order by %p.last_name }\n" +
        ";\n" +
        "\n" +
        "IDSEL=TYPES_EXT(OPT)=call identity();\n" +
        "IDSEL=UUID_ENTITY(OPT)=JDBC;\n" +
        "\n" +
        "INSERT_TYPES_EXT(CRUD,in=TypesExt,out=TypesExt,tab=types_ext)=\n" +
        "  insert into %%types_ext (%id, {? :tUuid(type=uuid) | %t_uuid, } %t_offset_date_time)\n" +
        "  {= values (:id(idgen=TYPES_EXT,id=id), {? :tUuid(type=uuid) | :tUuid(type=uuid), } :tOffsetDateTime(type=offsetdatetime)) }\n" +
        ";\n" +
        "\n" +
        "GET_TYPES_EXT(CRUD,in=TypesExt,out=TypesExt,tab=types_ext)=\n" +
        "  select %id @id(id), %t_uuid @tUuid(type=uuid), %t_offset_date_time @tOffsetDateTime(type=offsetdatetime)\n" +
        "  from %%types_ext\n" +
        "  {= where\n" +
        "    {& %id ::= :id }\n" +
        "    {& %t_uuid ::= :tUuid(type=uuid) }\n" +
        "    {& %t_offset_date_time ::= :tOffsetDateTime(type=offsetdatetime) }\n" +
        "  }\n" +
        ";\n" +
        "\n" +
        "UPDATE_TYPES_EXT(CRUD,in=TypesExt,out=TypesExt,tab=types_ext)=\n" +
        "  update %%types_ext\n" +
        "  {= set\n" +
        "    { ,%t_uuid = :tUuid(type=uuid,call=isDef) }\n" +
        "    { ,%t_offset_date_time = :tOffsetDateTime(type=offsetdatetime,call=isDef) }\n" +
        "  }\n" +
        "  {= where\n" +
        "    {& %id = :id(!empty) }\n" +
        "  }\n" +
        ";\n" +
        "\n" +
        "DELETE_TYPES_EXT(CRUD,in=TypesExt,out=TypesExt,tab=types_ext)=\n" +
        "  delete from %%types_ext\n" +
        "  {= where\n" +
        "    {& %id = :id(!empty) }\n" +
        "  }\n" +
        ";\n" +
        "\n" +
        "SELECT_TYPES_EXT(QRY,in=TypesExt,out=TypesExt,tab=types_ext)=\n" +
        "  select %id @id(id), %t_uuid @tUuid(type=uuid), %t_offset_date_time @tOffsetDateTime(type=offsetdatetime)\n" +
        "  from %%types_ext\n" +
        "  {= where\n" +
        "    {& %id ::= :id }\n" +
        "    {& %t_uuid ::= :tUuid(type=uuid) }\n" +
        "    {& %t_offset_date_time ::= :tOffsetDateTime(type=offsetdatetime) }\n" +
        "  }\n" +
        "  {#ID order by %id }\n" +
        ";\n" +
        "\n" +
        "INSERT_UUID_ENTITY(CRUD,in=UuidEntity,out=UuidEntity,tab=uuid_entity)=\n" +
        "  insert into %%uuid_entity (%id, {? :myid(type=uuid) | %myid, } %name, %description)\n" +
        "  {= values (:id(idgen=UUID_ENTITY,id=id,type=uuid), {? :myid(type=uuid) | :myid(type=uuid), } :name, :description) }\n" +
        ";\n" +
        "\n" +
        "GET_UUID_ENTITY(CRUD,in=UuidEntity,out=UuidEntity,tab=uuid_entity)=\n" +
        "  select %id @id(id,type=uuid), %myid @myid(type=uuid), %name @name, %description @description\n" +
        "  from %%uuid_entity\n" +
        "  {= where\n" +
        "    {& %id ::= :id(type=uuid) }\n" +
        "    {& %myid ::= :myid(type=uuid) }\n" +
        "    {& %name ::= :name }\n" +
        "    {& %description ::= :description }\n" +
        "  }\n" +
        ";\n" +
        "\n" +
        "UPDATE_UUID_ENTITY(CRUD,in=UuidEntity,out=UuidEntity,tab=uuid_entity)=\n" +
        "  update %%uuid_entity\n" +
        "  {= set\n" +
        "    { ,%myid = :myid(type=uuid,call=isDef) }\n" +
        "    { ,%name = :name(call=isDef) }\n" +
        "    { ,%description = :description(call=isDef) }\n" +
        "  }\n" +
        "  {= where\n" +
        "    {& %id = :id(type=uuid,!empty) }\n" +
        "  }\n" +
        ";\n" +
        "\n" +
        "DELETE_UUID_ENTITY(CRUD,in=UuidEntity,out=UuidEntity,tab=uuid_entity)=\n" +
        "  delete from %%uuid_entity\n" +
        "  {= where\n" +
        "    {& %id = :id(type=uuid,!empty) }\n" +
        "  }\n" +
        ";\n" +
        "\n" +
        "SELECT_UUID_ENTITY(QRY,in=UuidEntity,out=UuidEntity,tab=uuid_entity)=\n" +
        "  select %id @id(id,type=uuid), %myid @myid(type=uuid), %name @name, %description @description\n" +
        "  from %%uuid_entity\n" +
        "  {= where\n" +
        "    {& %id ::= :id(type=uuid) }\n" +
        "    {& %myid ::= :myid(type=uuid) }\n" +
        "    {& UPPER(%name) like :+name }\n" +
        "    {& UPPER(%description) like :+description }\n" +
        "  }\n" +
        "  {#ID order by %id }\n" +
        ";\n";
    // @formatter:on

    // @formatter:off
    private static final String DDL = 
        "DROP TABLE IF EXISTS CONTACT CASCADE;\n" +
        "DROP TABLE IF EXISTS PERSON CASCADE;\n" +
        "DROP TABLE IF EXISTS TYPES_EXT CASCADE;\n" +
        "DROP TABLE IF EXISTS UUID_ENTITY CASCADE;\n" +
        "DROP SEQUENCE IF EXISTS PERSON_ID_SEQ;\n" +
        "\n" +
        "CREATE SEQUENCE PERSON_ID_SEQ START WITH 1 INCREMENT BY 1;\n" +
        "\n" +
        "CREATE TABLE PERSON (\n" +
        "  ID BIGINT AUTO_INCREMENT,\n" +
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
        "CREATE INDEX IX_PERSON_LAST_NAME ON PERSON (LAST_NAME);\n" +
        "\n" +
        "CREATE TABLE CONTACT (\n" +
        "  ID BIGINT AUTO_INCREMENT,\n" +
        "  PERSON_ID BIGINT NOT NULL,\n" +
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
        "  ID BIGINT AUTO_INCREMENT,\n" +
        "  T_UUID UUID,\n" +
        "  T_OFFSET_DATE_TIME TIMESTAMP WITH TIME ZONE\n" +
        ");\n" +
        "\n" +
        "ALTER TABLE TYPES_EXT ADD CONSTRAINT PK_TYPES_EXT\n" +
        "\tPRIMARY KEY (ID);\n" +
        "\n" +
        "CREATE TABLE UUID_ENTITY (\n" +
        "  ID UUID DEFAULT RANDOM_UUID() NOT NULL,\n" +
        "  MYID UUID DEFAULT RANDOM_UUID() NOT NULL,\n" +
        "  NAME VARCHAR(100) NOT NULL,\n" +
        "  DESCRIPTION VARCHAR(500)\n" +
        ");\n" +
        "\n" +
        "ALTER TABLE UUID_ENTITY ADD CONSTRAINT PK_UUID_ENTITY\n" +
        "\tPRIMARY KEY (ID);\n";
    // @formatter:on

    // @formatter:off
    private static final String DEFINITIONS =
        "resolve-pojo-on;\n" +
        "database-is-offline;\n" +
        "database-jdbc-driver org.h2.Driver;\n" +
        "database-has-url \"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1\";\n" +
        "database-login-username sa;\n" +
        "database-login-password \"\";\n" +
        "database-in-catalog TEST;\n" +
        "//database-active-schema PUBLIC;\n" +
        "database-show-database-info \"H2,2.2.224,2,2\";\n" +
        "database-show-driver-info \"H2 JDBC Driver,2.2.224,2,2\";\n" +
        "//database-is-of-type H2;\n" +
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
        "\n" +
        "//metagen-function-result an_hour_before stamp;\n" +
        "metagen-debug-level DEBUG;\n" +
        "compress-meta-directives;\n" +
        "\n" +
        "pojogen-package org.sample.model;\n" +
        "daogen-package org.sample.dao;\n" +
        "\n" +
        "is-pojo Contact org.sample.model.Contact;\n" +
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
        injector = new ProcessorMetaStandaloneSetup().createInjectorAndDoEMFRegistration();

        pojoResolverFactory = injector.getInstance(PojoResolverFactory.class);
        resourceSetProvider = injector.getProvider(ResourceSet.class);
        validator = injector.getInstance(IResourceValidator.class);
        scopeProvider = injector.getInstance(IScopeProvider.class);
        resourceFactory = injector.getInstance(IResourceFactory.class);
        modelProperty = injector.getInstance(ModelProperty.class);
        dbResolver = injector.getInstance(DbResolver.class);

        pojoResolverFactory.setPojoResolver(new StandalonePojoResolverImpl(""));

        // Try to connect to H2 in-memory database
        try {
            // Try multiple ways to load the driver (for IDE vs Maven environment)
            boolean driverLoaded = false;
            try {
                Class.forName("org.h2.Driver");
                driverLoaded = true;
            } catch (ClassNotFoundException e1) {
                // Try with context classloader
                try {
                    Thread.currentThread().getContextClassLoader().loadClass("org.h2.Driver");
                    driverLoaded = true;
                } catch (ClassNotFoundException e2) {
                    // Try explicit registration using reflection to avoid compile-time dependency
                    try {
                        Class<?> driverClass = Class.forName("org.h2.Driver");
                        java.sql.Driver driver = (java.sql.Driver) driverClass.getDeclaredConstructor().newInstance();
                        DriverManager.registerDriver(driver);
                        driverLoaded = true;
                    } catch (Exception e3) {
                        throw new ClassNotFoundException("Could not load H2 driver: " + e1.getMessage());
                    }
                }
            }

            if (!driverLoaded) {
                throw new ClassNotFoundException("H2 driver not available");
            }

            connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        } catch (Exception e) {
            System.err.println(
                    "WARNING: Could not connect to H2 or H2 driver not available. Test will be skipped.");
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
    public void testGenerateMetaForUuidEntity() throws Exception {
        if (skipTest) {
            System.out.println("Test skipped - H2 not available");
            return;
        }

        // Load definitions resource
        ResourceSet resourceSet = resourceSetProvider.get();
        Resource controlResource = resourceFactory.createResource(URI.createURI("test.meta"));
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
                    "meta");
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
        controlResource = MainUtils.reloadResourceFromString(onlineDefinitions, resourceSet, resourceFactory, "meta");
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

        // Initialize DbResolver with DDL and H2 driver (loaded via reflection)
        Class<?> driverClass;
        try {
            driverClass = Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("H2 driver not available", e);
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
        String metaDefinitions = TableMetaGenerator.generateMeta(definitions, statements,
                ((XtextResource) controlResource).getSerializer(), dbResolver, scopeProvider, modelProperty, stats);

        System.out.println("Generated statements count: " + statements.size());

        assertNotNull("Generated META should not be null", metaDefinitions);
        System.out.println("Generated META SQL:");
        System.out.println("==================");
        System.out.println(metaDefinitions);
        System.out.println("==================");
        System.out.println("Stats: " + stats);

        // Write to file for debugging
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get("target/generated-meta-h2.sql"),
                    metaDefinitions.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            System.out.println("Generated META written to: target/generated-meta-h2.sql");
        } catch (Exception e) {
            System.err.println("Could not write debug file: " + e.getMessage());
        }

        // Split both expected and actual into individual SQL statements and compare each one
        Map<String, String> expectedStatements = parseMetaStatements(EXPECTED_META);
        Map<String, String> actualStatements = parseMetaStatements(metaDefinitions);

        // Statements to temporarily ignore during comparison
        Set<String> ignoredStatements = new HashSet<String>();
//        ignoredStatements.add("IDSEL=UUID_ENTITY");

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
            StringBuilder sb = new StringBuilder("Statement names mismatch.");
            if (!missingKeys.isEmpty()) {
                sb.append(" Missing: ").append(missingKeys).append(".");
            }
            if (!extraKeys.isEmpty()) {
                sb.append(" Extra: ").append(extraKeys).append(".");
            }
            assertEquals(sb.toString(), expectedKeys, actualKeys);
        }

        // Compare each individual SQL statement
        for (Map.Entry<String, String> entry : expectedStatements.entrySet()) {
            String name = entry.getKey();
            if (ignoredStatements.contains(name)) {
                continue;
            }
            String expectedSql = entry.getValue();
            String actualSql = actualStatements.get(name);
            assertNotNull("Missing statement: " + name, actualSql);
            assertEquals("Statement mismatch for: " + name, expectedSql, actualSql);
        }
    }

    /**
     * Parses META SQL text into a map of individual statements keyed by statement name. Handles both single-line
     * statements (SEQ=...; IDSEL=...;) and multi-line statements (INSERT_CONTACT(...)=\n ...\n;\n).
     */
    private static Map<String, String> parseMetaStatements(String metaSql) {
        Map<String, String> statements = new LinkedHashMap<String, String>();
        String[] lines = metaSql.split("\n");
        StringBuilder currentStatement = null;
        String currentName = null;

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                // Empty line - if we have a current statement, it's already been added
                continue;
            }

            // A standalone ';' is the terminator of the current multi-line statement
            if (line.trim().equals(";")) {
                if (currentStatement != null) {
                    currentStatement.append("\n").append(line);
                }
                continue;
            }

            // Check if this line starts a new statement (non-whitespace at beginning)
            if (!line.startsWith(" ") && !line.startsWith("\t")) {
                // Extract statement name (everything before the first '=' or '(')
                String name = extractStatementName(line);

                if (currentName != null && currentStatement != null) {
                    // Save previous statement
                    String existing = statements.get(currentName);
                    if (existing != null) {
                        // Append to existing (e.g., PROC_NEW_PERSON_RET_RS has CALL and OUT parts)
                        statements.put(currentName, existing + "\n" + currentStatement.toString());
                    } else {
                        statements.put(currentName, currentStatement.toString());
                    }
                }

                currentName = name;
                currentStatement = new StringBuilder(line);
            } else if (currentStatement != null) {
                // Continuation line
                currentStatement.append("\n").append(line);
            }
        }

        // Don't forget the last statement
        if (currentName != null && currentStatement != null) {
            String existing = statements.get(currentName);
            if (existing != null) {
                statements.put(currentName, existing + "\n" + currentStatement.toString());
            } else {
                statements.put(currentName, currentStatement.toString());
            }
        }

        return statements;
    }

    /**
     * Extracts the statement name from a META SQL line. For "SEQ=contact_id_seq(OPT)=..." returns "SEQ=contact_id_seq"
     * For "IDSEL=CONTACT(OPT)=..." returns "IDSEL=CONTACT" For "INSERT_CONTACT(CRUD,...)=..." returns "INSERT_CONTACT"
     */
    private static String extractStatementName(String line) {
        int parenIdx = line.indexOf('(');
        int eqIdx = line.indexOf('=');

        if (parenIdx < 0 && eqIdx < 0) {
            return line.trim();
        }

        // For lines like "SEQ=contact_id_seq(OPT)=..." or "IDSEL=CONTACT(OPT)=..."
        // the name is everything before the '('
        if (parenIdx >= 0) {
            String beforeParen = line.substring(0, parenIdx);
            // Check if this is a SEQ= or IDSEL= prefix
            if (beforeParen.startsWith("SEQ=") || beforeParen.startsWith("IDSEL=")) {
                return beforeParen;
            }
            // Otherwise it's just the statement name like INSERT_CONTACT
            return beforeParen;
        }

        // Fallback: use everything before first '='
        return line.substring(0, eqIdx);
    }
}
