package org.hhs.monitor;

import org.hhs.AbstractMonitor;
import org.hhs.collection.Cool;
import org.hhs.vo.Cpu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CpuMonitor extends AbstractMonitor<Cpu> implements Runnable{
    private Logger logger = LoggerFactory.getLogger("org.hhs.monitor.CpuMonitor");
    public Cpu getMonitorInstance() {
        try {
            Cool cool = Cool.getCoolInstance();
            Cpu cpu = cool.getCpu();
            return cpu;
        } catch (Exception e) {
            printException(e, Cpu.class, logger);
        }
        return null;
    }

    public void run() {
        outPut(logger);
    }
}
