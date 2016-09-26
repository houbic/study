package com.bink.utils;

/**
 * Created by chenbinghao on 16/9/24.下午9:58
 */
public enum MessageType {

    SERVER_MESSAGE(1, "来自服务器的消息"),

    CLIENT_MESSAGE(2, "来自客户端的消息")

    ;


    private int typeCode;

    private String message;

    MessageType(int typeCode, String message) {
        this.typeCode = typeCode;
        this.message = message;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
