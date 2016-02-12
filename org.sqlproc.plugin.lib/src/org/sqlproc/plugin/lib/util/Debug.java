package org.sqlproc.plugin.lib.util;

import org.apache.log4j.Logger;

public class Debug {
    public boolean debug = false;
    public boolean trace = false;
    public boolean info = false;
    public String logScope = null;
    public Logger LOGGER = null;

    public Debug(Logger LOGGER) {
        this.LOGGER = LOGGER;
    }

    public Debug(String debugLevel, String debugScope, Logger LOGGER) {
        debug = "DEBUG".equalsIgnoreCase(debugLevel);
        trace = "TRACE".equalsIgnoreCase(debugLevel);
        info = "INFO".equalsIgnoreCase(debugLevel);
        logScope = (debugScope != null) ? debugScope.toUpperCase() : null;
        this.LOGGER = LOGGER;
    }

    public void trace(String m, String msg) {
        if (!isScopeActive(m))
            return;
        if (trace)
            System.out.println(m + " " + msg);
        else if (LOGGER.isTraceEnabled())
            LOGGER.trace(m + " " + msg);
    }

    public void trace(String m, String msg, Object object) {
        if (!isScopeActive(m))
            return;
        if (trace)
            System.out.println(m + " " + msg + " " + object);
        else if (LOGGER.isTraceEnabled())
            LOGGER.trace(m + " " + msg + " " + object);
    }

    public void debug(String m, String msg) {
        if (!isScopeActive(m))
            return;
        if (trace || debug)
            System.out.println(m + " " + msg);
        else if (LOGGER.isDebugEnabled())
            LOGGER.debug(m + " " + msg);
    }

    public void debug(String m, String msg, Object object) {
        if (!isScopeActive(m))
            return;
        if (trace || debug)
            System.out.println(m + " " + msg + " " + object);
        else if (LOGGER.isDebugEnabled())
            LOGGER.debug(m + " " + msg + " " + object);
    }

    public void info(String m, String msg) {
        if (!isScopeActive(m))
            return;
        info(msg);
    }

    public void info(String msg) {
        if (trace || debug || info)
            System.out.println(msg);
        else if (LOGGER.isInfoEnabled())
            LOGGER.info(msg);
    }

    public void error(String msg, Exception e) {
        System.out.println(msg);
        e.printStackTrace();
        LOGGER.error(msg, e);
    }

    public void error(String msg) {
        System.out.println(msg);
        LOGGER.error(msg);
    }

    private boolean isScopeActive(String m) {
        if (logScope == null || m == null)
            return true;
        if (logScope.indexOf(m.toUpperCase()) >= 0)
            return true;
        return false;
    }
}
