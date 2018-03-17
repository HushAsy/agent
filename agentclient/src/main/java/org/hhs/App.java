package org.hhs;

import org.hhs.config.Common;
import org.hhs.filemonitor.FileListener;
import org.hhs.filemonitor.FileMonitor;
import org.hhs.nettyClient.ClientBootStrap;
import org.hhs.share.Constants;
import org.hhs.share.LoginMsg;
import org.hhs.utils.LoadClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App {
    private static Logger logger = LoggerFactory.getLogger("RollingFile-normal");
    public static void main( String[] args ) throws Exception {
        Constants.setClientId("test");
        ClientBootStrap clientBootStrap = new ClientBootStrap(12345, "127.0.0.1");
        //登录验证
        LoginMsg loginMsg = new LoginMsg();
        clientBootStrap.getSocketChannel().writeAndFlush(loginMsg);
        FileMonitor m = new FileMonitor(1000);
        m.monitor("D:\\logs\\agent-client", new FileListener(clientBootStrap));
        m.start();
        outPutLog();
        new App().shutdownGrace();
        Thread.sleep(5000);
        System.exit(1);
    }

    //输出日志
    public static void outPutLog(){
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

    //添加一个进程关闭的钩子
    public void shutdownGrace(){
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                Properties properties = new Properties();
                Set<Map.Entry<String, Long>> iterator = Common.stringLongMap.entrySet();
                for (Map.Entry entry: iterator){
                    properties.setProperty((String)entry.getKey(), String.valueOf(entry.getValue()));
                }
                String filePath = Thread.currentThread().getClass().getResource("/config.properties").getPath();
                File file = new File(filePath);
                FileOutputStream fileOutputStream = null;

                try {
                    fileOutputStream = new FileOutputStream(file);
                    properties.store(fileOutputStream,"");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }));
    }
}
