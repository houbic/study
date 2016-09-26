package com.bink.netty.socket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by chenbinghao on 16/9/26.下午2:39
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter{

    private Logger logger = LogManager.getLogger();

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String message = new String(req, "UTF-8");
        logger.info("server receive message:{}", message);
        ByteBuf resp = Unpooled.copiedBuffer("server ack".getBytes());
        context.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext context) {
        context.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        context.close();
    }

}
