package org.hhs.parse;

import org.hhs.vo.Cpu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CpuParse extends BaseParse<Cpu> {

    public List<Cpu> getCpuList(String str){
        try {
            String[] strCPu = str.split("\n");
            Map<String, List<String>> paramsMap = null;
            List<Cpu> cpuList = new ArrayList<Cpu>();
            deleteByIndex(strCPu, 0);
            deleteByIndex(strCPu, 0);
            paramsMap = initParamMap(strCPu);
            int size = paramsMap.get("usr").size();
            Cpu cpu = null;
            for (int i = 0; i < size; i++) {
                cpu = new Cpu();
                cpu.setCpu(paramsMap.get("cpu").get(i));
                cpu.setUsr(Double.parseDouble(paramsMap.get("usr").get(i)));
                cpu.setNice(Double.parseDouble(paramsMap.get("nice").get(i)));
                cpu.setSys(Double.parseDouble(paramsMap.get("sys").get(i)));
                cpu.setIowait(Double.parseDouble(paramsMap.get("iowait").get(i)));
                cpu.setIrq(Double.parseDouble(paramsMap.get("irq").get(i)));
                cpu.setSoft(Double.parseDouble(paramsMap.get("soft").get(i)));
                cpu.setSteal(Double.parseDouble(paramsMap.get("steal").get(i)));
                cpu.setGuest(Double.parseDouble(paramsMap.get("guest").get(i)));
                cpu.setGnice(Double.parseDouble(paramsMap.get("gnice").get(i)));
                cpu.setIdle(Double.parseDouble(paramsMap.get("idle").get(i)));
                cpuList.add(cpu);
            }
            return cpuList;
        }catch (Exception e){
            logger.error("cpuparse", e);
        }
        return null;
    }

    private Object[] deleteByIndex(Object[] objects, int index){
        for(int i = index; i < objects.length; i++){
            if (i != objects.length-1){
                objects[i] = objects[i+1];
            }
        }
        objects[objects.length-1] = null;
        return objects;
    }

    public List<Cpu> getObjectList(String var) {
        return getCpuList(var);
    }
}
