package org.hhs.monitor;

import org.hhs.AbstractMonitor;
import org.hhs.config.Config;
import org.hhs.vo.Io;

public class IoMonitor extends AbstractMonitor<Io>{

    public void setParam() {
        this.config = Config.iostat;
        this.t = Io.class;
    }
}
