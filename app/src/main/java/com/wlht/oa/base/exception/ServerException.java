package com.wlht.oa.base.exception;

public class ServerException extends RuntimeException {
    public int code;
    public String message;

    public ServerException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}