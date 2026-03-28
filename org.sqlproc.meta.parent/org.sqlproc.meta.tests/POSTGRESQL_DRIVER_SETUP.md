# How to Run MainPostgresIntegrationTest from Eclipse IDE

## Problem: PostgreSQL Driver Not Found

When running from Eclipse IDE, you may see:
```
WARNING: Could not connect to PostgreSQL or PostgreSQL driver not available. Test will be skipped.
Reason: org.postgresql.Driver
Test skipped - PostgreSQL not available
```

This happens because the PostgreSQL JDBC driver is not in the Eclipse runtime classpath.

## Solution 1: Run as JUnit Plug-in Test (Recommended for Tycho Projects)

1. **Right-click** on `MainPostgresIntegrationTest.java`
2. **Select**: `Run As → JUnit Plug-in Test` (NOT regular JUnit Test)
3. This will run the test in an OSGi/Eclipse runtime where the PostgreSQL driver bundle is available

## Solution 2: Add PostgreSQL JDBC to Build Path

If running as regular JUnit Test:

### Step 1: Download PostgreSQL JDBC Driver
1. Download from: https://jdbc.postgresql.org/download/
2. Or use the one Maven already downloaded: `~/.m2/repository/org/postgresql/postgresql/42.7.2/postgresql-42.7.2.jar`

### Step 2: Add to Project Build Path
1. **Right-click** on `org.sqlproc.meta.tests` project
2. Select **Properties**
3. Go to **Java Build Path → Libraries**
4. Click **Add External JARs...**
5. Browse to: `~/.m2/repository/org/postgresql/postgresql/42.7.2/postgresql-42.7.2.jar`
6. Click **OK**

### Step 3: Run the Test
1. **Right-click** on `MainPostgresIntegrationTest.java`
2. **Select**: `Run As → JUnit Test`

## Solution 3: Create Custom Run Configuration

1. **Right-click** on `MainPostgresIntegrationTest.java`
2. **Select**: `Run As → Run Configurations...`
3. **Double-click** on `JUnit` to create new configuration
4. Set **Name**: `MainPostgresIntegrationTest`
5. Go to **Classpath** tab
6. Select **User Entries**
7. Click **Add External JARs...**
8. Add: `~/.m2/repository/org/postgresql/postgresql/42.7.2/postgresql-42.7.2.jar`
9. Click **Apply** and **Run**

## Solution 4: Run via Maven from IDE

Use Eclipse's Maven integration:

1. **Right-click** on `org.sqlproc.meta.tests` project
2. **Select**: `Run As → Maven test`

Or configure a Maven run configuration:
1. `Run → Run Configurations...`
2. Double-click **Maven Build**
3. **Name**: `Test org.sqlproc.meta.tests`
4. **Base directory**: `${workspace_loc:/org.sqlproc.meta.tests}`
5. **Goals**: `clean test`
6. Click **Run**

## Solution 5: Use Embedded Database (Alternative)

If PostgreSQL is not available, you can modify the test to use an embedded database like H2:

1. Add H2 dependency to `pom.xml`
2. Change connection URL to H2
3. Modify DDL to be H2-compatible

## Verifying PostgreSQL is Running

Before running the test, ensure PostgreSQL is available:

```bash
# Check PostgreSQL status
sudo systemctl status postgresql

# Start if needed
sudo systemctl start postgresql

# Test connection
psql -h localhost -U simple -d simple
```

## Expected Success Output

When working correctly, you'll see:
```
Validating control resource...
Control resource validated
Reloaded with database-is-online
Tables in definitions: 1
Available tables from DB:
  - contact
  - person
  - types_ext
  - uuid_entity
Total tables: 4
Generating META SQL...
Column count: 4
Value count: 4
ID in columns: true
ID in values: true
MYID in columns: true
MYID in values: true
```

## Quick Fix: Environment Variable

Set environment variable with PostgreSQL JAR path:
1. `Run → Run Configurations...`
2. Select your test configuration
3. Go to **Environment** tab
4. Add variable: `CLASSPATH` with value pointing to PostgreSQL JAR

---

## Recommended Approach

For **Tycho/OSGi projects** like this one, **Solution 1** (Run as JUnit Plug-in Test) is the most appropriate because:
- ✅ It matches the Maven build environment
- ✅ PostgreSQL driver is already configured as OSGi bundle
- ✅ No manual classpath configuration needed
- ✅ Tests run the same way in IDE and CI

**Just select**: `Run As → JUnit Plug-in Test` instead of regular JUnit Test!
