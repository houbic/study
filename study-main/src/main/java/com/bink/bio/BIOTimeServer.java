package com.bink.bio;

import com.bink.utils.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by chenbinghao on 16/9/23.下午2:27
 */
public class BIOTimeServer {

    private Logger logger = LogManager.getLogger();

    private Executor executor = Executors.newFixedThreadPool(Constant.THREAD_POOL_WORKING_SIZE);

    private void init() {

        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(Constant.PORT);
            while (true) {
                socket = serverSocket.accept();
                Integer clientPort = socket.getPort();
                logger.info("have client connected, client port:{}", clientPort);
                executor.execute(new TimeServerHandler(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new BIOTimeServer().init();
    }

}
