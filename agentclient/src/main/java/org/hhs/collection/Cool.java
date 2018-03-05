package org.hhs.collection;

import org.hhs.vo.*;

public class Cool {
    private static Cool cool = null;

    private Cool(){

    }

    public static Cool getCoolInstance(){
        if (cool == null){
            synchronized (Cool.class){
                if (cool == null){
                    cool = new Cool();
                }
            }
        }
        return cool;
    }

    public Cpu getCpu(){
        return null;
    }

    public Disk getDisk(){
        return null;
    }

    public Io getIo(){
        return null;
    }

    public Mem getMem(){
        return null;
    }

    public Net getNet(){
        return null;
    }
}
