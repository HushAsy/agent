package org.hhs.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;
import org.hhs.server.NettyChannelMap;
import org.hhs.share.*;

import java.net.InetAddress;
import java.util.Date;

public class ServerHandler extends SimpleChannelInboundHandler<BaseMsg>{

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyChannelMap.remove((SocketChannel) ctx.channel());
    }

    protected void messageReceived(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg) throws Exception {
        System.out.println(baseMsg.getClientId());
        //心跳检查
        if (MsgType.LOGIN.equals(baseMsg.getType())){
            System.out.println("login");
            NettyChannelMap.add(baseMsg.getClientId(), (SocketChannel) channelHandlerContext.channel());
        }else if (MsgType.PING.equals(baseMsg.getType())){
            PingMsg pingMsg = (PingMsg) baseMsg;
            ReplyBody replyBody = new ReplyBody();
            replyBody.setResponse("ok");
            NettyChannelMap.get(pingMsg.getClientId()).writeAndFlush(replyBody);
        }else{
//            MessageBody messageBody = (MessageBody) baseMsg;
//            System.out.println(messageBody.toString());
        }
        //db存储
        ReferenceCountUtil.release(null);
    }
}
