package org.hhs.monitor;

import org.hhs.AbstractMonitor;
import org.hhs.vo.Mem;

public class MemoryMonitor extends AbstractMonitor<Mem> {

    public Mem getMonitorInstance() {
        try {
            Mem mem = new Mem();
            return mem;
        } catch (Exception e) {
            printException(e, Mem.class);
        }
        return null;
    }

}
