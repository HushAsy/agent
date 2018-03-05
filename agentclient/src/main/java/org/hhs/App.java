package org.hhs;

import lombok.extern.slf4j.Slf4j;
import org.hhs.utils.LoadClassName;
import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
@Slf4j
public class App {
    public static void main( String[] args ) throws SigarException {
        init();
    }

    public static void init(){
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        LoadClassName loadClassName = new LoadClassName();
        List<String> stringList = loadClassName.getStringList();
        for (String string : stringList){
            try {
                Class clazz = Class.forName(string);
                try {
                    Object obj = clazz.newInstance();
                    executorService.scheduleAtFixedRate((Runnable) obj, 0, 1, TimeUnit.SECONDS);
                } catch (InstantiationException e) {
                    log.error("class InstantiationException error:{}",string+".class", e);
                } catch (IllegalAccessException e) {
                    log.error("class IllegalAccessException error:{}",string+".class", e);
                }
            } catch (ClassNotFoundException e) {
                log.error("load class error:{}",string+".class", e);
            }
        }
    }
}
