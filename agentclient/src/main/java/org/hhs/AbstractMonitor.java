package org.hhs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractMonitor<T> implements Runnable{
    protected Logger logger = LoggerFactory.getLogger("org.hhs.monitor."+this.getClass().getSimpleName());

    public void outPut() {
        T t = getMonitorInstance();
        logger.info(t.toString());
    }

    public void run() {
        outPut();
    }

    public abstract T getMonitorInstance();

    protected void printException(Exception e, Class clzz){
        logger.error("get {} instance is error",clzz.getName(), e);
    }
}
