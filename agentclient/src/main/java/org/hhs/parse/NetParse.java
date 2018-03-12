package org.hhs.parse;

import org.hhs.vo.Net;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NetParse extends BaseParse {

    public List<Net> getNetList(String sss){
//        String sss = "#kernel\n" +
//                "Interface        RX Pkts/Rate    TX Pkts/Rate    RX Data/Rate    TX Data/Rate  \n" +
//                "                 RX Errs/Drop    TX Errs/Drop    RX Over/Rate    TX Coll/Rate  \n" +
//                "lo                133195 0        133195 0         7218K 0         7218K 0      \n" +
//                "                       0 0             0 0             0 0             0 0      \n" +
//                "eth0              280065 0        267338 0        38322K 0        75394K 0      \n" +
//                "                       0 0             0 0             0 0             0 0      \n" +
//                "eth1               29226 0         18865 0         1708K 0        26022K 0      \n" +
//                "                       0 0             0 0             0 0             0 0";
        try {
            String[] str = sss.split("\n");
            List<String> lists = new ArrayList<String>(Arrays.asList(str));
            lists.remove(0);

            List<String> listPkts = new ArrayList<String>();
            List<String> listErrs = new ArrayList<String>();
            String head = null;
            for (int i = 0; i < lists.size(); i++) {
                if (i % 2 == 0) {
                    head = lists.get(i).split("\\s+")[0];
                    listPkts.add(lists.get(i));
                } else {
                    listErrs.add(head + " " + lists.get(i));
                }
            }
            Object[] strings1 = listPkts.toArray();
            Object[] strings2 = listErrs.toArray();
            Map<String, List<String>> listMap1 = initParamMap(objToStr(strings1));
            Map<String, List<String>> listMap2 = initParamMap(objToStr(strings2));
            int size = listMap1.get("interface").size();
            List<Net> netList = new ArrayList<Net>();
            Net net = null;
            for (int i = 0; i < size; i++) {
                net = new Net();
                net.setInter(listMap1.get("interface").get(i));
                net.setRxPkts(listMap1.get("rx").get(i));
                net.setRxPktsRate(listMap1.get("pkts/rate").get(i));
//                net.setRxOver(listMap2.get("RX").get(i));
//                net.setRxOverRate(listMap2.get("Errs/Drop").get(i));

                net.setTxPkts(listMap1.get("tx").get(i));
                net.setTxPktsRate(listMap1.get("pkts/rate").get(i));
                net.setTxErrs(listMap2.get("tx").get(i));
                net.setTxErrsDrop(listMap2.get("Errs/Drop").get(i));

                net.setRxData(listMap1.get("RX").get(i));
                net.setRxDataRate(listMap1.get("data/rate").get(i));
                net.setRxOver(listMap2.get("RX").get(i));
                net.setRxOverRate(listMap2.get("over/rate").get(i));

                net.setTxData(listMap1.get("TX").get(i));
                net.setTxDataRate(listMap1.get("Data/Rate").get(i));
                net.setTxColl(listMap2.get("TX").get(i));
                net.setTxCollRate(listMap2.get("coll/rate").get(i));

                net.setRxErrs(listMap2.get("rx").get(i));
                net.setRxErrsDrop(listMap2.get("errs/drop").get(i));
                netList.add(net);
            }
            return netList;
        }catch (Exception e){
            logger.error("NetParse", e);
        }
        return null;
    }

    private String[] objToStr(Object[] objects){
        String[] strings = new String[objects.length];
        for (int i = 0; i < objects.length; i++){
            strings[i] = objects[i].toString();
        }
        return strings;
    }

    public List<Net> getObjectList(String var) {
        return getNetList(var);
    }
}
