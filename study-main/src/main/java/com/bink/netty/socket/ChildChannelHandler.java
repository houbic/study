package com.bink.netty.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


/**
 * Created by chenbinghao on 16/9/26.下午2:27
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new TimeServerHandler());
    }
}
