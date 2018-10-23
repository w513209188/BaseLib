package com.wb.baselib.http.exception;

public class ApiException extends RuntimeException {
    private int errorCode;
    public String message;
    public ApiException(int code, String msg) {
        super(msg);
        this.errorCode = code;
        this.message = msg;
    }
    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
