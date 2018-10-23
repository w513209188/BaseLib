package com.wb.baselib.http.exception;

public interface ApiErrorCode {
   /** 访问成功*/
    int SUCCUSE_CLIENT = 200;
    /** 客户端错误*/
    int ERROR_CLIENT_AUTHORIZED = 0;
    /**
     * 未知错误
     */
     int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
     int NETWORD_ERROR = 1002;
    /**
     * 协议出错
     */
     int HTTP_ERROR = 1003;

}
