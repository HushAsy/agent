package org.hhs.monitor;

import org.hhs.AbstractMonitor;
import org.hhs.collection.Cool;
import org.hhs.vo.Disk;

public class DiskIMonitor extends AbstractMonitor<Disk.DfI> {

    public Disk.DfI getMonitorInstance() {
        try {
            Cool cool = Cool.getCoolInstance();
            Disk.DfI dfi = cool.getDiskI();
            return dfi;
        } catch (Exception e) {
            printException(e, Disk.DfI.class);
        }
        return null;
    }
}
