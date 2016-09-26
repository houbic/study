package com.bink.netty.socket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by chenbinghao on 16/9/26.下午4:06
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter{

    private Logger logger = LogManager.getLogger();
    
    private final ByteBuf firstMessage;

    public TimeClientHandler() {

        byte[] req = "client message".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        logger.info("server ack:{}", new String(req, "UTF-8"));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
