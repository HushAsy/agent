package org.hhs.nettyClient.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<String> {

    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("客户端接受的消息"+s);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("正在连接...");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接关闭!");
        super.channelInactive(ctx);
    }
}
