package com.bink.bio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenbinghao on 16/9/23.下午5:08
 */
public class SendData implements Runnable {

//    private BufferedWriter bw;

    private PrintWriter pw;

    public SendData(PrintWriter pw) {
        this.pw = pw;
    }

    @Override
    public void run() {

        for (int i = 1; i < 1000; i++) {
            try {
                pw.println(String.format("第%s消息", i));
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
