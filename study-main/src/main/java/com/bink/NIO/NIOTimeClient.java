package com.bink.nio;

import com.bink.utils.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by chenbinghao on 16/9/26.上午11:01
 */
public class NIOTimeClient {

    private Selector selector;

    private SocketChannel socketChannel;

    private Logger logger = LogManager.getLogger();

    private void init() {

        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(Constant.LOCAL_IP, Constant.PORT));
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            while (true) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                while (it.hasNext()) {
                    handleEvent(it.next());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void handleEvent(SelectionKey key) throws IOException {
        if (key.isValid()) {

            // 连接事件, 创建socket
            if (key.isConnectable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                if (socketChannel.finishConnect()) {
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    doWrite(socketChannel);
                } else {
                    System.exit(1);
                }
            }

            // read data event
//            if (key.isReadable()) {
//                SocketChannel socketChannel = (SocketChannel) key.channel();
//                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//                int readBytes = socketChannel.read(byteBuffer);
//                if (readBytes > 0) {
//                    byteBuffer.flip();
//                    byte[] bytes = new byte[byteBuffer.remaining()];
//                    byteBuffer.get(bytes);
//                    String dataString = new String(bytes, "UTF-8");
//                    logger.info("client message:{}", dataString);
//                } else if (readBytes < 0) { //对端客户端关闭连接,表示客户端不会再传递消息
//                    key.channel();
//                    socketChannel.close();
//                } else if (readBytes == 0) {
//                    // 忽略
//                }
//            }
        }
    }

    private void doWrite(SocketChannel socketChanne) throws IOException {


        byte[] message = "hello, i'm client".getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(message);
        byteBuffer.flip();
        socketChanne.write(byteBuffer);
        if (byteBuffer.hasRemaining()) {
            logger.info("send message succeed");
        }

    }

    public static void main(String[] args) {
        new NIOTimeClient().init();
    }

}
