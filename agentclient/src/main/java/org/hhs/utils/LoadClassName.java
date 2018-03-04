package org.hhs.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class LoadClassName {
    public  List<String> getStringList(){
        String packageName = this.getClass().getResource("/org/hhs/monitor").getPath();
        List<String> stringList = new ArrayList<String>();
        File fileDir = new File(packageName);
        String clazzName = null;
        if (fileDir.isDirectory()){
            for (File file:fileDir.listFiles()){
                clazzName = file.getName().substring(0,file.getName().indexOf("."));
                stringList.add("org.hhs.monitor."+clazzName);
            }
        }
        return stringList;
    }
}
