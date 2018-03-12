package org.hhs.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.hhs.server.filter.ServerFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgentServer {
    private final int port = 9418;
    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    private ServerBootstrap b = new ServerBootstrap();
    private Logger loggerInfo = LoggerFactory.getLogger("RollingFile-normal");
    private Logger loggerError = LoggerFactory.getLogger("RollingFileErr");
    public static void main(String...args){
        AgentServer server = new AgentServer();
        server.initServer();
    }

    public void initServer(){
        try {
            b.group(eventLoopGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ServerFilter());
            ChannelFuture f = b.bind(port).sync();
            System.out.println("服务端启动正常");
            f.channel().closeFuture().sync();
        }catch (Exception e){
            loggerError.error("server init error!", e);
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
