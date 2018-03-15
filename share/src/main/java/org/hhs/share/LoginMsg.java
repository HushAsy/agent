package org.hhs.share;

public class LoginMsg extends BaseMsg {

    public LoginMsg(){
        super();
        setType(MsgType.LOGIN);
    }
}
