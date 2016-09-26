package com.bink.bio;

import com.bink.utils.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenbinghao on 16/9/23.下午4:15
 */
public class BIOTimeClient {

    public static void main(String[] args) {
        new BIOTimeClient().init();
    }

    private void init() {
        Socket socket = null;
        try {
            socket = new Socket(Constant.LOCAL_IP, Constant.PORT);
            System.out.println(String.format("connected server, time:%s", new Date(System.currentTimeMillis())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        new ConnectHandler().connectServer(socket);
    }

}


