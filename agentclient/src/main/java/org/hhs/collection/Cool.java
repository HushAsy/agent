package org.hhs.collection;

import org.hhs.cmd.ExecShell;
import org.hhs.config.Config;
import org.hhs.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cool {
    private Logger logger = LoggerFactory.getLogger("RollingFileErr");
    private Logger loggerInfo = LoggerFactory.getLogger("RollingFile-normal");
    private static Cool cool = null;
    private ExecShell execShell = new ExecShell();
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
        InputStream inputStream = execShell.getInputStream(Config.cpu.getCommand());
        List<String> list = new ArrayList();
        BufferedReader bufferedReader = null;
        try {
            String str = null;
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            while ((str = bufferedReader.readLine())!= null){
                list.add(str);
                loggerInfo.info(str);
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("unsupportedEncoding", e);
        } catch (IOException e) {
            logger.error("read stream error", e);
        }
        return new Cpu();
    }

    public Disk getDisk(){
        return null;
    }

    public Io getIo(){
        return null;
    }

    public Mem getMem(){
        return null;
    }

    public Net getNet(){
        return null;
    }
}
