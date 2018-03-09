package org.hhs.parse;

import org.hhs.vo.DfI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiskIParse extends BaseParse<DfI> {

    public List<DfI> getDiskIList(String str){
        try {
            String[] strs = str.replace("%25", "%").replace("Mounted on", "Mounted-on").split("\n");
            Map<String, List<String>> paramsMap = initParamMap(strs);
            List<DfI> dfHList = new ArrayList<DfI>();
            int size = paramsMap.get("filesystem").size();
            DfI dfi = null;
            for (int i = 0; i < size; i++) {
                dfi = new DfI();
                try {
                    dfi.setFs(paramsMap.get("filesystem").get(i));
                    dfi.setInodes(paramsMap.get("inodes").get(i));
                    dfi.setIUsed(paramsMap.get("iused").get(i));
                    dfi.setIFree(paramsMap.get("ifree").get(i));
                    dfi.setIUse(paramsMap.get("iuse").get(i));
                    dfi.setMountedOn(paramsMap.get("mounted-on").get(i));
                    dfHList.add(dfi);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return dfHList;
            }
        }catch (Exception e){
            logger.error("DiskIParse", e);
        }
        return null;
    }

    public List<DfI> getObjectList(String var) {
        return getDiskIList(var);
    }
}
