package com.example.seele.myapplication;

import com.wb.baselib.BaseApplication;
import com.wb.baselib.appconfig.AppConfig;
import com.wb.baselib.appconfig.AppConfigManager;
import com.wb.baselib.http.HttpConfig;
import com.wb.baselib.http.HttpManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mApp extends BaseApplication {
    @Override
    public String getRootPackAge() {
        return "fsfsfs";
    }

    @Override
    public void onCreate() {
        super.onCreate();
        List<Integer> httpCodeOff = new ArrayList<>();
        httpCodeOff.add(201);
        AppConfig appConfig = new AppConfig.Bulider()
                .setMaxPage(10)
                .setHttpCodeOff(httpCodeOff)
                .bulider();
        AppConfigManager.newInstance().setAppConfig(appConfig);

        Map<String,String> map=new HashMap<>();
        map.put("Authorization","bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC90ZXN0LXB4Lmh1YXR1LmNvbVwvYXBpXC9hcHBcL2NoZWNrQ2FzIiwiaWF0IjoxNTQwMzUwNzM4LCJleHAiOjE1NDA2NTMxMzgsIm5iZiI6MTU0MDM1MDczOCwianRpIjoiWWNnbnN4UlJWNlNTYTJ2OSIsInN1YiI6MzA4NTAsInBydiI6ImI5MTI3OTk3OGYxMWFhN2JjNTY3MDQ4N2ZmZjAxZTIyODI1M2ZlNDgifQ.fMdAYDpuT0iJgBRfS5yP9O-L6TkkzmRQMsJRud1ly50");
        HttpConfig.HttpConfigBuilder httpConfig = new HttpConfig.HttpConfigBuilder()
                .setmBaseUrl("http://test-px.huatu.com")
                .setmMapHeader(map)
                .setmIsUseLog(true);
        HttpConfig.newInstanceBuild(httpConfig);
    }
}
