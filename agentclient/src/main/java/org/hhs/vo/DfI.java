package org.hhs.vo;

import lombok.Data;

@Data
public class DfI {
    private String fs;
    private String inodes;
    private String iUsed;
    private String iFree;
    private String iUse;
    private String mountedOn;

    @Override
    public String toString() {
        return fs+"|"+inodes+"|"+iUsed+"|"+iFree+"|"+iUse+"|"+mountedOn;
    }
}
