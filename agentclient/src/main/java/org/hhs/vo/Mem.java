package org.hhs.vo;

import lombok.Data;
import lombok.ToString;

@Data
public class Mem {
    private double totalMem = 0d;
    private double freeMem = 0d;
    private double buffers = 0d;
    private double cached = 0d;
    private double swapTotal = 0d;
    private double swapFree = 0d;
    private double avialeMem = 0d;
    @Override
    public String toString() {
        return totalMem+"|"+freeMem+"|"+buffers+"|"+cached+"|"+swapTotal+"|"+swapFree+"|"+avialeMem;
    }
}
