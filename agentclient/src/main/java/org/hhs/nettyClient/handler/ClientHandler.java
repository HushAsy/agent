package org.hhs.nettyClient.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.hhs.share.BaseMsg;
import org.hhs.share.MsgType;
import org.hhs.share.PingMsg;

public class ClientHandler extends SimpleChannelInboundHandler<BaseMsg> {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent e = (IdleStateEvent) evt;
            if (IdleState.WRITER_IDLE.equals(e.state())){
                PingMsg pingMsg = new PingMsg();
                ctx.writeAndFlush(pingMsg);
                System.out.println("send ping to server");
            }
        }
    }

    protected void messageReceived(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg) throws Exception {
        MsgType msgType = baseMsg.getType();
        if (MsgType.PING.equals(msgType)){
            System.out.println("receive ping from server----------");
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("正在连接...");
        super.channelActive(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接关闭!");
        super.channelInactive(ctx);
    }
}
