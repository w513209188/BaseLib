package com.wb.baselib.http.exception;

import android.content.Context;
import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONException;

import java.net.ConnectException;


public class ApiErrorHelper {
   public static ApiException handleCommonError(Context context , Throwable e) {
        ApiException ex;
        e.printStackTrace();
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(ApiErrorCode.HTTP_ERROR, httpException.message());
            ex.message = "网络错误";  //均视为网络错误
        }else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ApiException(ApiErrorCode.PARSE_ERROR, e.getMessage());
            ex.message = "解析错误";            //均视为解析错误
        } else if (e instanceof ConnectException) {
            ex = new ApiException(ApiErrorCode.NETWORD_ERROR,e.getMessage());
            ex.message = "连接失败";  //均视为网络错误
        } else if (e instanceof ApiException) {    //服务器返回的错误
            ex = (ApiException) e;
        }  else {
            ex = new ApiException(ApiErrorCode.UNKNOWN,e.getMessage());
            ex.message = "未知错误";          //未知错误
        }
        return ex;
    }
}
