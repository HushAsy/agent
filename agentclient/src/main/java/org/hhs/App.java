package org.hhs;

import org.hhs.nettyClient.ClientBootStrap;
import org.hhs.share.Constants;
import org.hhs.share.LoginMsg;
import org.hhs.share.MessageBody;
import org.hhs.utils.LoadClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
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
//        init();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Constants.setClientId("test");
        ClientBootStrap clientBootStrap = new ClientBootStrap(12345, "127.0.0.1");
        //登录验证
        LoginMsg loginMsg = new LoginMsg();
        clientBootStrap.getSocketChannel().writeAndFlush(loginMsg);
//        while (true) {
//            TimeUnit.SECONDS.sleep(3);
//            List<String> stringList = new ArrayList<String>();
//            stringList.add("hello");
//            stringList.add("hello1");
//            stringList.add("hello2");
//            MessageBody messageBody = new MessageBody();
//            messageBody.setBody(stringList);
//            messageBody.setHead("cpu");
//            clientBootStrap.getSocketChannel().writeAndFlush(messageBody);
//        }
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
