package com.bink.netty.socket;

import com.bink.utils.Constant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by chenbinghao on 16/9/26.下午2:22
 */
public class NettyTimeClient {

    private void init() {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).
                    channel(NioSocketChannel.class).
                    option(ChannelOption.TCP_NODELAY, true).
                    handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    });

            // 发起异步连接
            ChannelFuture channelFutur =  bootstrap.connect(Constant.LOCAL_IP, Constant.PORT).sync();
            channelFutur.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new NettyTimeClient().init();
    }

}
