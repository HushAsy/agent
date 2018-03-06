package org.hhs;

import org.slf4j.Logger;

public abstract class AbstractMonitor<T> {
    public void outPut(Logger log) {
        T t = getMonitorInstance();
        log.info(t.toString());
    }

    public abstract T getMonitorInstance();

    protected void printException(Exception e, Class clzz, Logger log){
        log.error("get {} instance is error",clzz.getName(), e);
    }
}
