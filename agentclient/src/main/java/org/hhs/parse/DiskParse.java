package org.hhs.parse;

import org.hhs.vo.Disk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiskParse extends BaseParse{
    public class DiskH{
        public List<Disk.DfH> getDiskHList(String str){
            String[] strs = str.replace("%25","%").replace("Mounted on","Mounted-on").split("\n");
            Map<String, List<String>> paramsMap = initParamMap(strs);
            List<Disk.DfH> dfHList = new ArrayList<Disk.DfH>();
            int size = paramsMap.get("filesystem").size();
            Disk.DfH dfh = null;
            for (int i = 0; i < size; i++){
                dfh = new Disk.DfH();
                dfh.setFs(paramsMap.get("filesystem").get(i));
                dfh.setSize(paramsMap.get("size").get(i));
                dfh.setUsed(paramsMap.get("used").get(i));
                dfh.setAvail(paramsMap.get("avail").get(i));
                dfh.setUse(paramsMap.get("use%").get(i));
                dfh.setMountedOn(paramsMap.get("mounted-on").get(i));
                dfHList.add(dfh);
            }
            return dfHList;
        }
    }

    public class DiskI{
        public List<Disk.DfI> getDiskIList(String str){
            String[] strs = str.replace("%25","%").replace("Mounted on","Mounted-on").split("\n");
            Map<String, List<String>> paramsMap = initParamMap(strs);
            List<Disk.DfI> dfHList = new ArrayList<Disk.DfI>();
            int size = paramsMap.get("filesystem").size();
            Disk.DfI dfi = null;
            for (int i = 0; i < size; i++){
                dfi = new Disk.DfI();
                dfi.setFs(paramsMap.get("filesystem").get(i));
                dfi.setInodes(paramsMap.get("inodes").get(i));
                dfi.setIUsed(paramsMap.get("iused").get(i));
                dfi.setIFree(paramsMap.get("ifree").get(i));
                dfi.setIUse(paramsMap.get("iuse").get(i));
                dfi.setMountedOn(paramsMap.get("mounted-on").get(i));
                dfHList.add(dfi);
            }
            return dfHList;
        }
    }
}
