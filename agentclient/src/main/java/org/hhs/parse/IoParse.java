package org.hhs.parse;

import org.hhs.vo.Io;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IoParse extends BaseParse<Io> {

    public List<Io> getIoList(String str){
        try {
            String[] temps = str.split("\n");
            String[] strs = {temps[temps.length - 2], temps[temps.length - 1]};
            Map<String, List<String>> paramsMap = initParamMap(strs);
            List<Io> ioList = new ArrayList<Io>();
            int size = paramsMap.get("rrqm/s").size();
            Io io = null;
            for (int i = 0; i < size; i++) {
                io = new Io();
                io.setDevice(paramsMap.get("device:").get(i));
                io.setRrqm(Double.parseDouble(paramsMap.get("rrqm/s").get(i)));
                io.setWrqm(Double.parseDouble(paramsMap.get("wrqm/s").get(i)));
                io.setR(Double.parseDouble(paramsMap.get("r/s").get(i)));
                io.setW(Double.parseDouble(paramsMap.get("w/s").get(i)));
                io.setRkB(Double.parseDouble(paramsMap.get("rkb/s").get(i)));
                io.setWkB(Double.parseDouble(paramsMap.get("wkb/s").get(i)));
                io.setAvgrqSz(Double.parseDouble(paramsMap.get("avgrq-sz").get(i)));
                io.setAvgquSz(Double.parseDouble(paramsMap.get("avgqu-sz").get(i)));
                io.setAwait(Double.parseDouble(paramsMap.get("await").get(i)));
                io.setRAwait(Double.parseDouble(paramsMap.get("r_await").get(i)));
                io.setWAwait(Double.parseDouble(paramsMap.get("w_await").get(i)));
                io.setSvctm(Double.parseDouble(paramsMap.get("svctm").get(i)));
                io.setUtil(Double.parseDouble(paramsMap.get("util").get(i)));
                ioList.add(io);
            }
            return ioList;
        }catch (Exception e){
            logger.error("MemParse", e);
        }
        return null;
    }

    public List<Io> getObjectList(String var) {
        return getIoList(var);
    }
}
