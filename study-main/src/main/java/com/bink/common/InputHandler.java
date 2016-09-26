package com.bink.common;

import com.bink.utils.MessageType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by chenbinghao on 16/9/24.下午9:54
 */
public class InputHandler implements Runnable {

    private Logger logger = LogManager.getLogger();

    private BufferedReader br;

    private MessageType messageType;

    public InputHandler(BufferedReader br, MessageType messageType) {
        this.br = br;
        this.messageType = messageType;
    }

    @Override
    public void run() {
        while (true) {
            String line;
            try {
                line = br.readLine();
                if (line != null) {
                    logger.info("{}:{}", messageType.getMessage(), line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
