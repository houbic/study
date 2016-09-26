package com.bink.common;

import com.bink.utils.MessageType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import static java.lang.System.in;

/**
 * Created by chenbinghao on 16/9/24.下午9:54
 */
public class OutputHandler implements Runnable {

    private PrintWriter pw;

    public OutputHandler(PrintWriter pw) {
        this.pw = pw;
    }

    @Override
    public void run() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line != null) {
                pw.println(line);
            }
        }

    }
}
