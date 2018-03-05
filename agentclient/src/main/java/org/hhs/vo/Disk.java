package org.hhs.vo;

import lombok.Data;

public class Disk {
    @Data
    class DfH {
        private String fs;
        private String size;
        private String used;
        private String avail;
        private double use = 0d;
        private String mountedOn;

        @Override
        public String toString() {
            return fs+"|"+size+"|"+used+"|"+avail+"|"+use+"|"+mountedOn;
        }
    }

    @Data
    class DfI {
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
}
