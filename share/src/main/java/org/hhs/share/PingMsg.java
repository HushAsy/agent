package org.hhs.share;

public class PingMsg extends BaseMsg {
    public PingMsg(){
        super();
        setType(MsgType.PING);
    }
}
