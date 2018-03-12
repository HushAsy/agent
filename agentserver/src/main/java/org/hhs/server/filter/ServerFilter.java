package org.hhs.server.filter;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.hhs.server.handler.ServerHandler;


public class ServerFilter extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline ph = socketChannel.pipeline();
        ph.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        // 解码和编码，应和客户端一致
        ph.addLast("decoder", new StringDecoder());
        ph.addLast("encoder", new StringEncoder());
        ph.addLast("handler", new ServerHandler());// 服务端业务逻辑
    }
}
