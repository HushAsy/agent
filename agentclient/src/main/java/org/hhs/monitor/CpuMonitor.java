package org.hhs.monitor;

import org.hhs.AbstractMonitor;
import org.hhs.config.Config;
import org.hhs.vo.Cpu;

public class CpuMonitor extends AbstractMonitor<Cpu>{

    public void setParam() {
        this.config = Config.cpu;
        this.t = Cpu.class;
    }
}
