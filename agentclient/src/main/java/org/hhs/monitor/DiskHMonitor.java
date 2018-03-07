package org.hhs.monitor;

import org.hhs.AbstractMonitor;
import org.hhs.collection.Cool;
import org.hhs.vo.Disk;

public class DiskHMonitor extends AbstractMonitor<Disk.DfH>{

    public Disk.DfH getMonitorInstance() {
        try {
            Cool cool = Cool.getCoolInstance();
            Disk.DfH dfh = cool.getDiskH();
            return dfh;
        } catch (Exception e) {
            printException(e, Disk.DfH.class);
        }
        return null;
    }
}
