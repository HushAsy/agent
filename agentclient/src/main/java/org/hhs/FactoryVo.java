package org.hhs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class FactoryVo {
    private Logger logger = LoggerFactory.getLogger("RollingFileErr");
    private static FactoryVo factoryVo = null;
    private static ConcurrentHashMap<Class, Object> concurrentHashMap = new ConcurrentHashMap(6);
    public static FactoryVo getFactoryVo(){
        if (factoryVo == null){
            synchronized (FactoryVo.class){
                factoryVo = new FactoryVo();
            }
        }
        return factoryVo;
    }

    public Object getObj(Class clazz) {
        if (concurrentHashMap.get(clazz) == null){
            try {
                concurrentHashMap.put(clazz, clazz.newInstance());
            } catch (InstantiationException e) {
                logger.error("new Instance error!", e);
            } catch (IllegalAccessException e) {
                logger.error("illegalAccessException error", e);
            }
        }
        return concurrentHashMap.get(clazz);
    }


}
