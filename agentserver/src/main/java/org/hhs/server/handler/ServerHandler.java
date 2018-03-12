package org.hhs.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;
import java.util.Date;

public class ServerHandler extends SimpleChannelInboundHandler<String>{

    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        //收到消息直接打印输出
        System.out.println("服务端接受的消息:"+s);
        if ("quit".equals(s)){
            channelHandlerContext.close();
        }
        Date date = new Date();
        channelHandlerContext.writeAndFlush(date+"\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接的客户端地址:"+ctx.channel().remoteAddress());
        ctx.writeAndFlush("客户端"+ InetAddress.getLocalHost().getHostName()+"成功与服务端建立连接");
        super.channelActive(ctx);
    }
}
