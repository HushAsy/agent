package org.hhs.vo;

import lombok.Data;

@Data
public class DfH {
    private String fs;
    private String size;
    private String used;
    private String avail;
    private String use;
    private String mountedOn;

    @Override
    public String toString() {
        return fs+"|"+size+"|"+used+"|"+avail+"|"+use+"|"+mountedOn;
    }
}
