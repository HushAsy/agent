package org.hhs.monitor;

import org.hhs.AbstractMonitor;
import org.hhs.config.Config;
import org.hhs.parse.NetParse;
import org.hhs.vo.Net;

public class NetMonitor extends AbstractMonitor<Net>{

    public void setParam() {
        this.config = Config.ifstat;
        this.t = NetParse.class;
    }
}
