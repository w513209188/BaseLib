package com.example.seele.myapplication.mvp.model;

import com.example.seele.myapplication.api.TestServceApi;
import com.example.seele.myapplication.mvp.contranct.TestContranct;
import com.wb.baselib.bean.Result;
import com.wb.baselib.http.HttpManager;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

public class TestModel implements TestContranct.TestModel {
    @Override
    public Observable<Result> getTestData() {
        Map<String,String> map=new HashMap<>();
        map.put("uid","1");
        return HttpManager.newInstance().getService(TestServceApi.class).getTestData(map);
    }
}
