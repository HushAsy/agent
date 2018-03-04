package org.hhs.instance;

import org.hyperic.sigar.Sigar;

public class SigarInstance {

    private static Sigar sigar = null;

    public static Sigar getSigarInstance() {
        if (sigar == null){
            synchronized (SigarInstance.class){
                if (sigar == null){
                    sigar = null;
                }
            }
        }
        return sigar;
    }
}
