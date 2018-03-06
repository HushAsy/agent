package org.hhs.monitor;

import org.hhs.AbstractMonitor;
import org.hhs.vo.Net;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetMonitor extends AbstractMonitor<Net> implements Runnable{
    private Logger logger = LoggerFactory.getLogger("org.hhs.monitor.NetMonitor");
    public void run() {

    }

    public Net getMonitorInstance() {
        return null;
    }
}
