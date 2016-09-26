package com.bink.bio;

import java.net.Socket;

/**
 * Created by chenbinghao on 16/9/23.下午3:52
 */
public class TimeServerHandler implements Runnable{

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        new ConnectHandler().connectServer(socket);
    }

}
