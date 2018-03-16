package org.hhs.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class Common {

    public static Map<String, Long> stringLongMap = new ConcurrentHashMap(8);

    static {
        InputStream inputStream = Thread.currentThread().getClass().getResourceAsStream("config.properties");
        Properties p = new Properties();
        try {
            p.load(inputStream);
            Iterator<String> it= p.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
                String value = p.getProperty(key);
                stringLongMap.put(key, Long.valueOf(value));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
