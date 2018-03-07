package org.hhs;

import org.hhs.parse.CpuParse;
import org.hhs.vo.Cpu;

import java.util.List;

public class FactoryVo {
    private static FactoryVo factoryVo = null;

    public static FactoryVo getFactoryVo(){
        if (factoryVo == null){
            synchronized (FactoryVo.class){
                factoryVo = new FactoryVo();
            }
        }
        return factoryVo;
    }

    public List<Cpu> getListCpus(String str){
        CpuParse cpuParse = new CpuParse();
        return cpuParse.getCpuList(str);
    }


}
