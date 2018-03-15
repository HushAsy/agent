package org.hhs.utils;

import io.netty.channel.socket.SocketChannel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MessageUpload {

    private SocketChannel socketChannel;

    public MessageUpload(SocketChannel socketChannel){
        this.socketChannel = socketChannel;
    }


}
