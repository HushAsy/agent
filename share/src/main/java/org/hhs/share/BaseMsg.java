package org.hhs.share;

import java.io.Serializable;

public class BaseMsg implements Serializable {
    private static final long serialVersionUID = 1L;
    private MsgType type;
    private String clientId;

    public BaseMsg(){
        this.clientId = Constants.getClientId();
    }

    public String getClientId() {
        return clientId;
    }

    public MsgType getType(){
        return type;
    }

    public void setType(MsgType type){
        this.type = type;
    }
}
