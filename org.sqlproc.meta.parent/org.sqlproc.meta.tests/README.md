# 🔴 ERROR: "PostgreSQL driver not available. Test will be skipped"

## ⚡ QUICK FIX

You're running as **regular JUnit Test**. Change to **JUnit Plug-in Test**:

```
Right-click MainPostgresIntegrationTest.java
→ Run As
→ JUnit Plug-in Test  ✅ (NOT "JUnit Test")
```

**That's it!** The test will now find the PostgreSQL driver.

---

## 📚 Documentation Files

I've created comprehensive guides for you:

### 1. **QUICK_FIX_POSTGRESQL_DRIVER.md** ⭐ START HERE
   - Simple 3-step solution
   - Why the error happens
   - What to use (Plug-in Test vs regular Test)

### 2. **POSTGRESQL_DRIVER_SETUP.md**
   - Alternative solutions if Plug-in Test doesn't work
   - Manual classpath configuration
   - Embedded database alternative

### 3. **RUNNING_TESTS_IN_IDE.md**
   - Complete guide for running tests in Eclipse
   - Keyboard shortcuts
   - Expected output
   - Troubleshooting

### 4. **MainPostgresIntegrationTest-PluginTest.launch**
   - Pre-configured launch file
   - Right-click → Run As → MainPostgresIntegrationTest-PluginTest

---

## 🎯 The Root Cause

This is a **Tycho/OSGi Eclipse plugin project**. The PostgreSQL JDBC driver is configured as an OSGi bundle (via Eclipse Orbit repository) which is only available in:

✅ **JUnit Plug-in Test** runtime (Eclipse/OSGi environment)  
✅ **Maven/Tycho** build  
❌ **Regular JUnit Test** (plain Java classpath)

When you run as regular JUnit Test, the driver isn't in the classpath, causing:
```
WARNING: Could not connect to PostgreSQL or PostgreSQL driver not available. Test will be skipped.
Reason: org.postgresql.Driver
```

---

## 🚀 Two Ways to Run

### Option 1: From Eclipse IDE (Recommended)
```
Right-click test → Run As → JUnit Plug-in Test
```

### Option 2: From Terminal
```bash
cd /home/hudec/git/sql-processor-eclipse/org.sqlproc.meta.parent
mvn verify -pl org.sqlproc.meta.tests -am
```

Both work perfectly! ✅

---

## ✅ Success Indicators

When working correctly:

**Console shows:**
```
Available tables from DB:
  - uuid_entity
Column count: 4
Value count: 4
ID in columns: true
MYID in columns: true
```

**JUnit view shows:**
- Green bar ✅
- Tests run: 1/1
- Errors: 0
- Failures: 0

**File created:**
```
target/generated-meta.sql  ← Contains generated SQL statements
```

---

## 📞 Need More Help?

Read the detailed guides:
1. Start with **QUICK_FIX_POSTGRESQL_DRIVER.md**
2. If issues persist, check **POSTGRESQL_DRIVER_SETUP.md**
3. For general IDE usage, see **RUNNING_TESTS_IN_IDE.md**

**Bottom line**: Use `JUnit Plug-in Test`, not regular `JUnit Test`! 🎉
