package com.bink.netty.socket;

import com.bink.utils.Constant;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by chenbinghao on 16/9/26.下午2:22
 */
public class NettyTimeServer {

    public void bind() throws Exception {
        // 配置服务端NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());
            ChannelFuture channelFuture = serverBootstrap.bind(Constant.PORT).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        try {
            new NettyTimeServer().bind();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
