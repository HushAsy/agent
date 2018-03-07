package org.hhs;

import lombok.extern.slf4j.Slf4j;
import org.hhs.utils.LoadClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Hello world!
 *
 */
public class App {
    private static Logger logger = LoggerFactory.getLogger("RollingFile-normal");
    public static void main( String[] args ) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        init();
        countDownLatch.await();
    }

    public static void init(){
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        LoadClassName loadClassName = new LoadClassName();
        String packageName = "org.hhs.monitor";
        List<String> stringList = loadClassName.getStringList(packageName);
        for (String string : stringList){
            try {
                Class clazz = Class.forName(string);
                try {
                    Object obj = clazz.newInstance();
                    executorService.scheduleAtFixedRate((Runnable) obj, 0, 1, TimeUnit.SECONDS);
                } catch (InstantiationException e) {
                    logger.error("class InstantiationException error:{}",string+".class", e);
                } catch (IllegalAccessException e) {
                    logger.error("class IllegalAccessException error:{}",string+".class", e);
                }
            } catch (ClassNotFoundException e) {
                logger.error("load class error:{}",string+".class", e);
            }
        }
    }


}
