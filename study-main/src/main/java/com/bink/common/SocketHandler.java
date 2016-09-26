package com.bink.common;

import com.bink.bio.AcceptData;
import com.bink.bio.SendData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenbinghao on 16/9/24.下午9:49
 */
public class SocketHandler {

    private void handler(Socket socket) {
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Thread(new AcceptData(br)).start();
            pw = new PrintWriter(socket.getOutputStream(), true);
            new Thread(new SendData(pw)).start();
            TimeUnit.SECONDS.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                br.close();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
