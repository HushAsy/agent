package org.hhs.monitor;

import org.hhs.AbstractMonitor;
import org.hyperic.sigar.Mem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryMonitor extends AbstractMonitor<Mem> implements Runnable {
    private Logger logger = LoggerFactory.getLogger("org.hhs.monitor.MemoryMonitor");
    public Mem getMonitorInstance() {
        try {
            Mem mem = new Mem();
            return mem;
        } catch (Exception e) {
            printException(e, Mem.class, logger);
        }
        return null;
    }

    public void run() {
        outPut(logger);
    }
}
