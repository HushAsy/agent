package org.hhs.monitor;

import org.hhs.AbstractMonitor;
import org.hhs.collection.Cool;
import org.hhs.vo.Cpu;
public class CpuMonitor extends AbstractMonitor<Cpu>{

    public Cpu getMonitorInstance() {
        try {
            Cool cool = Cool.getCoolInstance();
            Cpu cpu = cool.getCpu();
            return cpu;
        } catch (Exception e) {
            printException(e, Cpu.class);
        }
        return null;
    }


}
