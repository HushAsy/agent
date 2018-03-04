package org.hhs.monitor;

import org.apache.logging.log4j.core.LogEvent;
import org.hhs.AbstractMonitor;
import org.hyperic.sigar.Cpu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CpuMonitor extends AbstractMonitor<Cpu> implements Runnable{
    private Logger logger = LoggerFactory.getLogger("org.hhs.monitor.CpuMonitor");
    public Cpu getMonitorInstance() {
        try {
            Cpu cpu = new Cpu();
            return cpu;
        } catch (Exception e) {
            printException(e, Cpu.class, logger);
        }
        LogEvent logEvent = null;
        return null;
    }

    public void run() {
        outPut(logger);
    }
}
