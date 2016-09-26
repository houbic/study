package com.bink.bio;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenbinghao on 16/9/23.下午5:19
 */
public class ConnectHandler {

    public void connectServer(Socket socket) {

//        BufferedReader br = null;
//        PrintWriter pw = null;
////        BufferedWriter bw = null;
//        try {
//            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            new Thread(new AcceptData(br)).start();
//            pw = new PrintWriter(socket.getOutputStream(), true);
////            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            new Thread(new SendData(pw)).start();
//            TimeUnit.SECONDS.sleep(1000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                socket.close();
//                br.close();
//                pw.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }
}
