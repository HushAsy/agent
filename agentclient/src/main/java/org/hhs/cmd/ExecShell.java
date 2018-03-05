package org.hhs.cmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class ExecShell {

    private Logger logger = LoggerFactory.getLogger("RollingFileErr");

    public  InputStream getInputStream(String cmd){
        java.lang.Runtime runtime = Runtime.getRuntimeInstance();
        InputStream in = null;
        Process process = null;
        try {
            process = runtime.exec(new String[] {"/bin/sh", "-c", cmd});
            in= process.getInputStream();
            process.destroy();
            return in;
        } catch (IOException e) {
            in = process.getErrorStream();
            logger.error("exec shell {} error", cmd, e);
            return in;
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error("inputstream close error", e);
            }
        }
    }

    private static class Runtime{
        private static java.lang.Runtime runtime = null;

        private Runtime(){

        }

        public static java.lang.Runtime getRuntimeInstance(){
            if (runtime == null){
                synchronized (Runtime.class){
                    if (runtime == null){
                        runtime = java.lang.Runtime.getRuntime();
                    }
                }
            }
            return runtime;
        }

    }

}

