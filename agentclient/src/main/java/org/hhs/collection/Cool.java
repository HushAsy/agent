package org.hhs.collection;

import org.hhs.cmd.ExecShell;
import org.hhs.config.Config;
import org.hhs.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cool {
    private Logger loggerInfo = LoggerFactory.getLogger("RollingFile-normal");
    private static Cool cool = null;
    private Cool(){

    }

    public static Cool getCoolInstance(){
        if (cool == null){
            synchronized (Cool.class){
                if (cool == null){
                    cool = new Cool();
                }
            }
        }
        return cool;
    }

    public Cpu getCpu(){
        ExecShell execShell = ExecShell.getExecShellInstance();
        Object object = execShell.execLinux(Config.cpu.getCommand());
        loggerInfo.info(object.toString());
        return new Cpu();
    }

    public Disk getDisk(){
        ExecShell execShell = ExecShell.getExecShellInstance();
        Object object = execShell.execLinux(Config.disk_df_h.getCommand());
        loggerInfo.info(object.toString());
        return new Disk();
    }

    public Io getIo(){
        ExecShell execShell = ExecShell.getExecShellInstance();
        Object object = execShell.execLinux(Config.iostat.getCommand());
        loggerInfo.info(object.toString());
        return new Io();
    }

    public Mem getMem(){
        ExecShell execShell = ExecShell.getExecShellInstance();
        Object object = execShell.execLinux(Config.mem.getCommand());
        loggerInfo.info(object.toString());
        return new Mem();
    }

    public Net getNet(){
        ExecShell execShell = ExecShell.getExecShellInstance();
        Object object = execShell.execLinux(Config.ifstat.getCommand());
        loggerInfo.info(object.toString());
        return new Net();
    }
}
