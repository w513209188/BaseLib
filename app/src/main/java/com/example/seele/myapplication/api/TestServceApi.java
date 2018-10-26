package com.example.seele.myapplication.api;

import com.wb.baselib.bean.Result;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TestServceApi {
    @POST("api/app/getAppToken")
    @FormUrlEncoded
    Observable<Result> getTestData(@FieldMap Map<String,String> map);
}
