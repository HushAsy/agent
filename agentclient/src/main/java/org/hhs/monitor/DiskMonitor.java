package org.hhs.monitor;

import org.hhs.AbstractMonitor;
import org.hhs.vo.Disk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiskMonitor extends AbstractMonitor<Disk> implements Runnable {
    private Logger logger = LoggerFactory.getLogger("org.hhs.monitor.DiskMonitor");

    public void run() {

    }

    public Disk getMonitorInstance() {
        return null;
    }
}
