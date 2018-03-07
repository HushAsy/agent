package org.hhs.monitor;

import org.hhs.AbstractMonitor;
import org.hhs.collection.Cool;
import org.hhs.vo.Io;

public class IoMonitor extends AbstractMonitor<Io>{

    public Io getMonitorInstance() {
        try {
            Cool cool = Cool.getCoolInstance();
            Io io = cool.getIo();
            return io;
        } catch (Exception e) {
            printException(e, Io.class);
        }
        return null;
    }
}
