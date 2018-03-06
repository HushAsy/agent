package org.hhs.monitor;

import org.hhs.AbstractMonitor;
import org.hhs.vo.Io;
import org.hhs.vo.Net;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IoMonitor extends AbstractMonitor<Io> implements Runnable{
    private Logger logger = LoggerFactory.getLogger("org.hhs.monitor.IoMonitor");
    public void run() {

    }

    public Io getMonitorInstance() {
        return null;
    }
}
