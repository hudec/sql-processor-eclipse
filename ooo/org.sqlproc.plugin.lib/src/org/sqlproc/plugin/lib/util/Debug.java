package org.sqlproc.plugin.lib.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Debug {
    String logScope = null;
    Logger logger = null;
    Boolean doSysout = false;

    public Debug(Logger LOGGER) {
        this.logger = LOGGER;
    }

    public Debug(String debugLevel, String debugScope, Logger LOGGER) {
        if ("DEBUG".equals(debugLevel)) {
            LOGGER.setLevel(Level.DEBUG);
        }
        if ("TRACE".equals(debugLevel)) {
            LOGGER.setLevel(Level.TRACE);
        }
        if ("INFO".equals(debugLevel)) {
            LOGGER.setLevel(Level.INFO);
        }
        if ("WARN".equals(debugLevel)) {
            LOGGER.setLevel(Level.WARN);
        }
        if ("debug".equals(debugLevel)) {
            LOGGER.setLevel(Level.DEBUG);
        }
        if ("trace".equals(debugLevel)) {
            LOGGER.setLevel(Level.TRACE);
        }
        if ("info".equals(debugLevel)) {
            LOGGER.setLevel(Level.INFO);
        }
        if ("warn".equals(debugLevel)) {
            LOGGER.setLevel(Level.WARN);
        }
        logScope = (debugScope != null) ? debugScope.toUpperCase() : null;
        this.logger = LOGGER;
    }

    public boolean isDebug() {
        return logger.isDebugEnabled();
    }

    public void trace(String msg) {
        if (logger.isTraceEnabled()) {
            if (doSysout)
                System.out.println(msg);
            else
                logger.trace(msg);
        }
    }

    public void trace(String scope, String msg) {
        if (!isScopeActive(scope))
            return;
        if (logger.isTraceEnabled()) {
            if (doSysout)
                System.out.println(scope + " " + msg);
            else
                logger.trace(scope + " " + msg);
        }
    }

    public void trace(String scope, String msg, Object object) {
        if (!isScopeActive(scope))
            return;
        if (logger.isTraceEnabled()) {
            if (doSysout)
                System.out.println(scope + " " + msg + " " + object);
            else
                logger.trace(scope + " " + msg + " " + object);
        }
    }

    public void debug(String msg) {
        if (logger.isDebugEnabled()) {
            if (doSysout)
                System.out.println(msg);
            else
                logger.debug(msg);
        }
    }

    public void debug(String scope, String msg) {
        if (!isScopeActive(scope))
            return;
        if (logger.isDebugEnabled()) {
            if (doSysout)
                System.out.println(scope + " " + msg);
            else
                logger.debug(scope + " " + msg);
        }
    }

    public void debug(String scope, String msg, Object object) {
        if (!isScopeActive(scope))
            return;
        if (logger.isDebugEnabled()) {
            if (doSysout)
                System.out.println(scope + " " + msg + " " + object);
            else
                logger.debug(scope + " " + msg + " " + object);
        }
    }

    public void info(String scope, String msg) {
        if (!isScopeActive(scope))
            return;
        if (logger.isInfoEnabled()) {
            if (doSysout)
                System.out.println(scope + " " + msg);
            else
                logger.info(scope + " " + msg);
        }
    }

    public void info(String msg) {
        if (logger.isInfoEnabled()) {
            if (doSysout)
                System.out.println(msg);
            else
                logger.info(msg);
        }

    }

    public void warn(String msg) {
        if (doSysout)
            System.out.println(msg);
        else
            logger.info(msg);
    }

    public void error(String msg, Exception e) {
        if (doSysout) {
            System.out.println(msg + " " + e.getMessage());
            e.printStackTrace();
        } else
            logger.error(msg, e);
    }

    public void error(String msg) {
        if (doSysout)
            System.out.println(msg);
        else
            logger.error(msg);
    }

    private boolean isScopeActive(String m) {
        if (logScope == null || m == null)
            return true;
        if (logScope.indexOf(m.toUpperCase()) >= 0)
            return true;
        return false;
    }
}
