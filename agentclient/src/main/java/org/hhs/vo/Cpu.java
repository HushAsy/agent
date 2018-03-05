package org.hhs.vo;

import lombok.Data;
import lombok.ToString;
@Data
public class Cpu {
    private double usr = 0d;
    private double nice = 0d;
    private double sys = 0d;
    private double iowait = 0d;
    private double irq = 0d;
    private double soft = 0d;
    private double steal = 0d;
    private double guest = 0d;
    private double gnice = 0d;
    private double idle = 0d;

    @Override
    public String toString() {
        return usr+"|"+nice+"|"+sys+"|"+iowait+"|"+irq+"|"+soft+"|"+steal+"|"+guest+"|"+gnice+"|"+idle;
    }
}
