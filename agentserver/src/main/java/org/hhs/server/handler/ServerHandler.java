package org.hhs.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.hhs.server.NettyChannelMap;
import org.hhs.share.BaseMsg;
import org.hhs.share.MsgType;

import java.net.InetAddress;
import java.nio.channels.SocketChannel;
import java.util.Date;

public class ServerHandler extends SimpleChannelInboundHandler<BaseMsg>{

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyChannelMap.remove((SocketChannel) ctx.channel());
    }

    protected void messageReceived(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg) throws Exception {
//        //收到消息直接打印输出
//        System.out.println("服务端接受的消息:"+s);
//        if ("quit".equals(s)){
//            channelHandlerContext.close();
//        }
//        Date date = new Date();
//        channelHandlerContext.writeAndFlush(date+"\n");
        //心跳检查
        if (MsgType.PING.equals(baseMsg.getType())){

        }else
        //db存储
        ReferenceCountUtil.release(null);
    }
}
