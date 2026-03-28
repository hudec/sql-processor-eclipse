# Running MainPostgresIntegrationTest from Eclipse IDE

## Prerequisites

Before running the test, ensure you have:

1. **PostgreSQL Database Running**
   - Host: `localhost`
   - Port: `5432`
   - Database: `simple`
   - Username: `simple`
   - Password: `simple`

2. **Eclipse IDE with Required Plugins**
   - Eclipse with Maven (m2e) support
   - Eclipse PDE (Plugin Development Environment)
   - JUnit 4 support

3. **Project Imported**
   - Import the project as "Existing Maven Project"
   - Ensure all dependencies are resolved

## Method 1: Right-Click Run (Easiest)

1. **Open the test file** in Eclipse:
   - Navigate to: `org.sqlproc.meta.tests/src/org/sqlproc/meta/tests/MainPostgresIntegrationTest.java`

2. **Right-click on the file** in Package Explorer or in the editor

3. **Select**: `Run As → JUnit Test`

4. The test will execute and results will show in the JUnit view

## Method 2: Using Launch Configuration

1. **Open Run Configurations**:
   - Menu: `Run → Run Configurations...`

2. **Find the launch configuration**:
   - Look for `MainPostgresIntegrationTest` under `JUnit`
   - Or double-click `JUnit` to create a new configuration

3. **Configure if needed**:
   - **Name**: `MainPostgresIntegrationTest`
   - **Project**: `org.sqlproc.meta.tests`
   - **Test class**: `org.sqlproc.meta.tests.MainPostgresIntegrationTest`
   - **Test runner**: `JUnit 4`

4. **Click Run**

## Method 3: Using Existing .launch File

1. **In Package Explorer**, navigate to:
   ```
   org.sqlproc.meta.tests/MainPostgresIntegrationTest.launch
   ```

2. **Right-click** on the `.launch` file

3. **Select**: `Run As → MainPostgresIntegrationTest`

## Method 4: Run Specific Test Method

1. **Open** `MainPostgresIntegrationTest.java`

2. **Find the test method**: `testGenerateMetaForUuidEntity()`

3. **Right-click** on the method name

4. **Select**: `Run As → JUnit Test`

This will run only that specific test method.

## Expected Output in Console

When the test runs successfully, you'll see:

```
Validating control resource...
Handling reference errors: [org.sample.model.UuidEntity, org.postgresql.Driver]
After handling - validation result: 0
After handling - failed references: []
Control resource validated
Reloaded with database-is-online
Tables in definitions: 1
  Table: org.sqlproc.meta.processorMeta.impl.TableDefinitionModelImpl@... (name: uuid_entity, table: uuid_entity)
Model values doResolveDb: true
Available tables from DB:
  - contact
  - person
  - types_ext
  - uuid_entity
Total tables: 4
Generating META SQL...
Generated statements count: 0
Generated META SQL:
==================
[INSERT_UUID_ENTITY, GET_UUID_ENTITY, UPDATE_UUID_ENTITY, DELETE_UUID_ENTITY, SELECT_UUID_ENTITY statements...]
==================
Column count: 4
Value count: 4
Columns: %id, %myid, %name, %description
Values: :id, :myid, :name, :description
ID in columns: true
ID in values: true
MYID in columns: true
MYID in values: true
Generated META written to: target/generated-meta.sql
```

## JUnit View Results

The **JUnit view** (usually at the bottom) will show:
- ✅ Green bar indicating success
- **Runs**: 1/1
- **Errors**: 0
- **Failures**: 0

## Troubleshooting

### Test is Skipped
**Symptom**: Console shows "Test skipped - PostgreSQL not available"

**Solutions**:
1. Start PostgreSQL: `sudo systemctl start postgresql`
2. Create database:
   ```sql
   CREATE DATABASE simple;
   CREATE USER simple WITH PASSWORD 'simple';
   GRANT ALL PRIVILEGES ON DATABASE simple TO simple;
   ```
3. Verify connection: `psql -h localhost -U simple -d simple`

### ClassNotFoundException: org.postgresql.Driver
**Solution**: 
- The test uses reflection to load the driver
- Ensure Maven dependencies are resolved: Right-click project → Maven → Update Project

### OSGi/Tycho Issues
**Solution**:
- This test is designed to run in OSGi/Tycho environment
- Ensure you're running it as a **JUnit Plug-in Test** if needed:
  - Right-click → Run As → **JUnit Plug-in Test**

### Cannot Find Launch Configuration
**Solution**:
1. Refresh the project: F5
2. Or create manually:
   - Run → Run Configurations...
   - Double-click "JUnit"
   - Set project: `org.sqlproc.meta.tests`
   - Set test class: `org.sqlproc.meta.tests.MainPostgresIntegrationTest`

## Debugging the Test

To debug instead of run:

1. **Right-click** on the test file or method
2. **Select**: `Debug As → JUnit Test`
3. Set breakpoints by double-clicking in the left margin
4. Step through with F5 (step into), F6 (step over), F8 (continue)

## Viewing Generated Output

After the test runs, check:
- **Console output**: Shows detailed test execution
- **Generated file**: `org.sqlproc.meta.tests/target/generated-meta.sql`
  - Refresh the project (F5) to see the file
  - Double-click to view the generated META SQL

## Quick Keyboard Shortcuts

- **Run last test**: `Ctrl+F11` (or `Cmd+F11` on Mac)
- **Debug last test**: `F11`
- **Run test at cursor**: `Alt+Shift+X, T`
- **Debug test at cursor**: `Alt+Shift+D, T`

## Success Criteria

The test passes when:
1. ✅ PostgreSQL connection established
2. ✅ UUID_ENTITY table created
3. ✅ META SQL generated
4. ✅ INSERT statement has 4 columns and 4 values
5. ✅ Both UUID columns (ID and MYID) are properly handled

---

**Note**: This integration test validates that UUID entity generation correctly handles UUID columns with default values in INSERT statements.
