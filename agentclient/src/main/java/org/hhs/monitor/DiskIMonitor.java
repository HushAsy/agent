package org.hhs.monitor;

import org.hhs.AbstractMonitor;
import org.hhs.config.Config;
import org.hhs.vo.DfI;

public class DiskIMonitor extends AbstractMonitor<DfI> {

    public void setParam() {
        this.config = Config.disk_df_i;
        this.t = DfI.class;
    }
}
