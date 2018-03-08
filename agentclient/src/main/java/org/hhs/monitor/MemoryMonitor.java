package org.hhs.monitor;

import org.hhs.AbstractMonitor;
import org.hhs.config.Config;
import org.hhs.vo.Mem;

public class MemoryMonitor extends AbstractMonitor<Mem> {

    public void setParam() {
        this.config = Config.mem;
        this.t = Mem.class;
    }
}
