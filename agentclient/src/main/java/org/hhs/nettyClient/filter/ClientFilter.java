package org.hhs.nettyClient.filter;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.hhs.nettyClient.handler.ClientHandler;

public class ClientFilter extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline ph = socketChannel.pipeline();
         /*
         * 解码和编码，应和服务端一致
         * */
        ph.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        ph.addLast("decoder", new StringDecoder());
        ph.addLast("encoder", new StringEncoder());
        ph.addLast("handler", new ClientHandler()); //客户端的逻辑
    }
}
