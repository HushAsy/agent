package org.hhs.vo;

import lombok.Data;

@Data
public class Net {
    private String inter;
    private String rxPkts;
    private String rxPktsRate;
    private String rxErrs;
    private String rxErrsDrop;
    private String txPkts;
    private String txPktsRate;
    private String txErrs;
    private String txErrsDrop;
    private String rxData;
    private String rxDataRate;
    private String rxOver;
    private String rxOverRate;
    private String txData;
    private String txDataRate;
    private String txColl;
    private String txCollRate;
    @Override
    public String toString() {
        return inter+"|"+rxPkts+"|"+rxPktsRate+"|"+rxErrs+"|"+rxErrsDrop+"|"+
                txPkts+"|"+txPktsRate+"|"+txErrs+"|"+txErrsDrop+"|"+rxData+"|"+rxDataRate+"|"+rxOver+"|"+rxOverRate+"|"+txData+"|"+txDataRate+"|"+txColl+"|"+txCollRate;
    }
}
