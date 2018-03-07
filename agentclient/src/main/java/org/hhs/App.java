package org.hhs;

import lombok.extern.slf4j.Slf4j;
import org.hhs.utils.LoadClassName;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
@Slf4j
public class App {
    public static void main( String[] args ) throws IOException, InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);
        init();
        latch.await();
    }

    public static void init(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        LoadClassName loadClassName = new LoadClassName();
        List<String> stringList = loadClassName.getStringList();

        for (String string : stringList){
            System.out.println(string);
            try {
                Class clazz = Class.forName(string);
                try {
                    Object obj = clazz.newInstance();
                    executorService.scheduleAtFixedRate((Runnable) obj, 0, 1, TimeUnit.SECONDS);
                    new Thread((Runnable) obj).start();
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
