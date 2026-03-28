# ✅ SOLUTION: Running MainPostgresIntegrationTest from Eclipse IDE

## 🔴 Problem You're Seeing

```
WARNING: Could not connect to PostgreSQL or PostgreSQL driver not available. Test will be skipped.
Reason: org.postgresql.Driver
Test skipped - PostgreSQL not available
```

## 🎯 Quick Fix (3 Steps)

### You were running as **regular JUnit Test**. You need to run as **JUnit Plug-in Test** instead!

1. **Right-click** on `MainPostgresIntegrationTest.java` (or in the editor)

2. **Select**: `Run As → JUnit Plug-in Test` 
   - ⚠️ **NOT** "JUnit Test" 
   - ✅ **YES** "JUnit Plug-in Test"

3. **Done!** The test will now run with the PostgreSQL driver available.

## 📋 Alternative: Use the Launch Configuration I Created

1. In **Package Explorer**, find:
   ```
   org.sqlproc.meta.tests/MainPostgresIntegrationTest-PluginTest.launch
   ```

2. **Right-click** on it

3. **Select**: `Run As → MainPostgresIntegrationTest-PluginTest`

## 🤔 Why Does This Happen?

| Run Type | PostgreSQL Driver | Works? |
|----------|------------------|--------|
| **JUnit Test** (regular) | ❌ Not in classpath | ❌ Test skipped |
| **JUnit Plug-in Test** | ✅ Available as OSGi bundle | ✅ Test runs |
| **Maven test** | ✅ Configured via Tycho | ✅ Test runs |

This is a **Tycho/OSGi project**. The PostgreSQL JDBC driver is configured as an OSGi bundle that's only available in the Eclipse/OSGi runtime (JUnit Plug-in Test), not in the regular Java classpath (JUnit Test).

## 🔧 What I Created for You

I've created 3 files to help:

1. ✅ **`MainPostgresIntegrationTest-PluginTest.launch`**
   - Pre-configured JUnit Plug-in Test runner
   - Just right-click and run!

2. ✅ **`POSTGRESQL_DRIVER_SETUP.md`**
   - Detailed setup instructions
   - Multiple solutions if Plug-in Test doesn't work
   - Troubleshooting tips

3. ✅ **`RUNNING_TESTS_IN_IDE.md`**
   - Complete guide for running tests
   - Keyboard shortcuts
   - Expected output examples

## 📦 Files in Your Project

```
org.sqlproc.meta.tests/
├── MainPostgresIntegrationTest.java          ← The test
├── MainPostgresIntegrationTest.launch         ← Regular JUnit (won't work)
├── MainPostgresIntegrationTest-PluginTest.launch  ← Plug-in Test (USE THIS!)
├── POSTGRESQL_DRIVER_SETUP.md                  ← Driver setup guide
└── RUNNING_TESTS_IN_IDE.md                     ← Complete running guide
```

## ✅ How to Verify It's Working

When successful, console will show:

```
Validating control resource...
Handling reference errors: [org.sample.model.UuidEntity, org.postgresql.Driver]
After handling - validation result: 0
Control resource validated
Reloaded with database-is-online
Tables in definitions: 1
Available tables from DB:
  - uuid_entity
Generating META SQL...
Column count: 4
Value count: 4
ID in columns: true
ID in values: true
MYID in columns: true
MYID in values: true
```

JUnit view shows:
- ✅ **Green bar**
- Runs: 1/1
- Errors: 0
- Failures: 0

## 🚀 Quick Start Commands

### From Eclipse:
- **Right-click test** → `Run As → JUnit Plug-in Test`

### From Terminal (works from any directory):
```bash
cd /home/hudec/git/sql-processor-eclipse/org.sqlproc.meta.parent
mvn verify -pl org.sqlproc.meta.tests -am
```

## ⚠️ Prerequisites

Make sure PostgreSQL is running:
```bash
sudo systemctl status postgresql
# If not running:
sudo systemctl start postgresql
```

Database should exist:
```bash
sudo -u postgres psql -c "CREATE DATABASE simple;"
sudo -u postgres psql -c "CREATE USER simple WITH PASSWORD 'simple';"
sudo -u postgres psql -c "GRANT ALL PRIVILEGES ON DATABASE simple TO simple;"
```

---

## 🎉 Summary

**The fix is simple**: Use `Run As → JUnit Plug-in Test` instead of regular JUnit Test!

This runs the test in an Eclipse/OSGi environment where the PostgreSQL JDBC driver bundle (from Eclipse Orbit) is available, just like when running via Maven/Tycho.

**Try it now!**
