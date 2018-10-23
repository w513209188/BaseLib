package com.wb.baselib.bean;

/**
 * 基础解析类
 * @param <T>
 */
public class Result<T> {
    private String msg;
    private int code;
    private T data;
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return code;
    }

    public void setStatus(int status) {
        this.code = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}