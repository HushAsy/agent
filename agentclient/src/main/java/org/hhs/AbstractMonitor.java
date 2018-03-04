package org.hhs;

import lombok.extern.slf4j.Slf4j;
import org.hhs.instance.SigarInstance;
import org.hyperic.sigar.Sigar;
import org.slf4j.Logger;

public abstract class AbstractMonitor<T> {
    protected Sigar sigar = SigarInstance.getSigarInstance();

    public void outPut(Logger log) {
        T t = getMonitorInstance();
        log.info(t.getClass().getName()+":"+t.toString());
    }

    public abstract T getMonitorInstance();

    protected void printException(Exception e, Class clzz, Logger log){
        log.error("get {} instance is error",clzz.getName(), e);
    }
}
