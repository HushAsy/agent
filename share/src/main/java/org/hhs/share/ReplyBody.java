package org.hhs.share;

import java.io.Serializable;

public class ReplyBody extends BaseMsg implements Serializable {
    private static final long serialVersionUID = 1L;
    private String response;

    public void setResponse(String response){
        this.response = response;
    }

    public String getResponse(){
        return response;
    }
}
