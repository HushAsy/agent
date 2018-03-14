package org.hhs.cmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;

public class ExecShell {

    private Logger logger = LoggerFactory.getLogger("RollingFileErr");

    private Object exec(String[] cmdLinux){
        Process process = null;
        LineNumberReader br = null;
        StringBuffer sb = null;
        try {
            process = java.lang.Runtime.getRuntime().exec(cmdLinux);
            br = new LineNumberReader(new InputStreamReader(process.getInputStream(), "GB18030"));
        } catch (IOException e) {
            logger.error("exec {} error", cmdLinux, e);
            try {
                br = new LineNumberReader(new InputStreamReader(process.getErrorStream(), "GB18030"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }finally {
            sb = new StringBuffer();
            String line;
            try {
                while ((line = br.readLine()) != null){
                    sb.append(line).append("\n");
                }
            } catch (IOException e) {
                logger.error("exec cmd error:readLine", e);
            }
        }
        return sb.toString().replaceAll("%(?![0-9a-fA-F]{2})", "%25");
    }

    public Object execLinux(String cmd){
        String[] cmdLinux = {"/bin/sh", "-c", cmd};
        return exec(cmdLinux);
    }

    private ExecShell(){

    }

    private static ExecShell execShell = null;

    public static ExecShell getExecShellInstance(){
        if (execShell == null){
            synchronized (ExecShell.class){
                if (execShell == null){
                    execShell = new ExecShell();
                }
            }
        }
        return execShell;
    }

}


