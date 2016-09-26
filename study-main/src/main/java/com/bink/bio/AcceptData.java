package com.bink.bio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;

public class AcceptData implements Runnable {

    private BufferedReader br;

    public AcceptData(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void run() {
        while (true) {
            String line;
            try {
                line = br.readLine();
                if (line != null) {
                    System.out.println(String.format("接收到消息:%s", line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}