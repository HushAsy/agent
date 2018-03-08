package org.hhs.monitor;

import org.hhs.AbstractMonitor;
import org.hhs.config.Config;
import org.hhs.vo.DfH;

public class DiskHMonitor extends AbstractMonitor<DfH>{

    public void setParam() {
        this.config = Config.disk_df_h;
        this.t = DfH.class;
    }
}
