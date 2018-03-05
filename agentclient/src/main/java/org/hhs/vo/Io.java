package org.hhs.vo;

import lombok.Data;
@Data
public class Io {
    private String device;
    private double rrqm = 0d;
    private double wrqm = 0d;
    private double r = 0d;
    private double w = 0d;
    private double rkB = 0d;
    private double wkB = 0d;
    private double avgrqSz = 0d;
    private double avgquSz = 0d;
    private double await = 0d;
    private double rAwait = 0d;
    private double wAwait = 0d;
    private double svctm = 0d;
    private double util = 0d;

    @Override
    public String toString() {
        return device+"|"+rrqm+"|"+wrqm+"|"+r+"|"+w+"|"+rkB+"|"+wkB+"|"+avgrqSz+"|"+avgquSz+"|"+await+rAwait+"|"+wAwait+"|"+svctm+"|"+util;
    }
}
