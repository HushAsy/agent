package org.hhs;

import org.hhs.collection.Cool;
import org.hhs.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class AbstractMonitor<T> implements Runnable{
    protected Logger logger = LoggerFactory.getLogger("org.hhs.monitor."+this.getClass().getSimpleName());
    protected Config config;
    protected Class<?> t;

    public void outPut() {
        setParam();
        List<T> lists = Cool.getCoolInstance().getParseList(t, config);
        for (T t:lists){
            logger.info(t.toString());
        }
    }

    public void run() {
        outPut();
    }

    public abstract void setParam();
}
