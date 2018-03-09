package org.hhs.parse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class BaseParse<T> {
    protected Logger logger = LoggerFactory.getLogger("RollingFile-error");
    protected Map<String, List<String>> initParamMap(String[] str){
        Map<String, List<String>> strMap = new LinkedHashMap<String, List<String>>();
        for (int i = 0; i < str.length; ++i){
            if (str[i] == null){
                continue;
            }
            if (i == 0){
                String strs[] = str[i].replace("%", "").split("\\s+");
                for (String strTemp:strs){
                    if(strMap.get(strTemp.toLowerCase()) == null) {
                        strMap.put(strTemp.toLowerCase(), new ArrayList<String>());
                    }else{
                        strMap.put(strTemp, new ArrayList<String>());
                    }
                }
            }else {
                String strs[] = str[i].split("\\s+");
                Iterator<String> it = strMap.keySet().iterator();
                for (int k = 0; it.hasNext(); k++){
                    String key = it.next();
                    List<String> list = strMap.get(key);
                    list.add(strs[k]);
                }
            }
        }
        return strMap;
    }

    public abstract List<T> getObjectList(String var);

}
