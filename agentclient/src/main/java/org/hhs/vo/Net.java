package org.hhs.vo;

import lombok.Data;

@Data
public class Net {
    private String inter;
    private String rxPktsRate;
    private String rxErrDrop;
    private String txPktsRate;
    private String txErrsDrop;
    private String rxDataRate;
    private String rxOverRate;
    private String txDataRate;
    private String txCollRate;
    @Override
    public String toString() {
        return inter+"|"+rxPktsRate+"|"+rxErrDrop+"|"+txPktsRate+"|"+txErrsDrop+"|"+rxDataRate+"|"+rxOverRate+"|"+txDataRate+"|"+txCollRate;
    }
}
