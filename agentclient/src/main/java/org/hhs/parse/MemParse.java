package org.hhs.parse;

import org.hhs.vo.Mem;

import java.util.HashMap;
import java.util.Map;

public class MemParse extends BaseParse{

    public Mem getMem(String memStr){
        Map<String, String> stringMap = new HashMap<String, String>();
        String[] strs = memStr.split("\n");
        for (String str : strs){
            String[] temps = str.split(":");
            stringMap.put(temps[0].trim(), temps[1].replace("kB","").trim());
        }
        Mem mem = new Mem();
        mem.setTotalMem(Double.parseDouble(stringMap.get("MemTotal")));
        mem.setAvialeMem(Double.parseDouble(stringMap.get("MemAvailable")));
        mem.setBuffers(Double.parseDouble(stringMap.get("Buffers")));
        mem.setCached(Double.parseDouble(stringMap.get("Cached")));
        mem.setFreeMem(Double.parseDouble(stringMap.get("MemFree")));
        mem.setSwapFree(Double.parseDouble(stringMap.get("SwapFree")));
        mem.setSwapTotal(Double.parseDouble(stringMap.get("SwapTotal")));
        return mem;
    }
}
