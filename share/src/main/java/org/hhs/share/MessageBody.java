package org.hhs.share;

import java.io.Serializable;
import java.util.List;

public class MessageBody extends BaseMsg implements Serializable{
    private static final long serialVersionUID = 2L;

    private String head;

    private List<String> body;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public List<String> getBody() {
        return body;
    }

    public void setBody(List<String> body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "MessageBody{" +
                "head='" + head + '\'' +
                ", body=" + body +
                '}';
    }
}
