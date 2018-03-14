package org.hhs.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.hhs.server.filter.ServerFilter;
import org.hhs.server.handler.ServerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgentServer {
    private int port;

    private Logger loggerInfo = LoggerFactory.getLogger("RollingFile-normal");
    private Logger loggerError = LoggerFactory.getLogger("RollingFileErr");

    private SocketChannel socketChannel;

    public static void main(String...args){

    }

    public AgentServer(int port){
        this.port = port;
        try {
            initServer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initServer() throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, 128);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline p = socketChannel.pipeline();
                p.addLast(new ObjectEncoder());
                p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                p.addLast(new ServerHandler());
            }
        });
        ChannelFuture f = bootstrap.bind(port).sync();
        if (f.isSuccess()){
            System.out.println("server start----");
        }
    }
}
