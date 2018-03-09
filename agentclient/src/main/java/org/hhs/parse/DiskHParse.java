package org.hhs.parse;

import org.hhs.vo.DfH;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiskHParse extends BaseParse<DfH> {

    public List<DfH> getDiskHList(String str){
        try {
            String[] strs = str.replace("%25", "%").replace("Mounted on", "Mounted-on").split("\n");
            Map<String, List<String>> paramsMap = initParamMap(strs);
            List<DfH> dfHList = new ArrayList<DfH>();
            int size = paramsMap.get("filesystem").size();
            DfH dfh = null;
            for (int i = 0; i < size; i++) {
                dfh = new DfH();
                dfh.setFs(paramsMap.get("filesystem").get(i));
                dfh.setSize(paramsMap.get("size").get(i));
                dfh.setUsed(paramsMap.get("used").get(i));
                dfh.setAvail(paramsMap.get("avail").get(i));
                dfh.setUse(paramsMap.get("use").get(i));
                dfh.setMountedOn(paramsMap.get("mounted-on").get(i));
                dfHList.add(dfh);
            }
            return dfHList;
        }catch (Exception e){
            logger.error("DiskHParse", e);
        }
        return null;
    }

    public List<DfH> getObjectList(String var) {
        return getDiskHList(var);
    }
}
