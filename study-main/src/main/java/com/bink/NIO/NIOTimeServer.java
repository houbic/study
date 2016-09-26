package com.bink.nio;

import com.bink.utils.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by chenbinghao on 16/9/24.下午10:32
 */
public class NIOTimeServer {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private boolean stop;

    private Logger logger = LogManager.getLogger();

    private void init() {

        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(Constant.PORT), 1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (!stop) {
                accept();
            }
            selector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void accept() {
        try {
            selector.select(1000);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();
            SelectionKey key = null;
            while (it.hasNext()) {
                key = it.next();
                it.remove();
                try {
                    handleEvent(key);
                } catch (Exception e) {
                    if (key != null) {
                        key.cancel();
                        if (key.channel() != null) key.channel().close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleEvent(SelectionKey key) throws IOException {
        if (key.isValid()) {

            // 连接事件, 创建socket
            if (key.isAcceptable()) {
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            }

            // read data event
            if (key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int readBytes = socketChannel.read(byteBuffer);
                if (readBytes > 0) {
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    String dataString = new String(bytes, "UTF-8");
                    logger.info("client message:{}", dataString);
                } else if (readBytes < 0) { //对端客户端关闭连接,表示客户端不会再传递消息
                    key.channel();
                    socketChannel.close();
                } else if (readBytes == 0) {
                    // 忽略
                }
            }
        }
    }

    public static void main(String[] args) {
        new NIOTimeServer().init();

    }

}
