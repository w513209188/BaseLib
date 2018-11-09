//package com.example.seele.myapplication;
//
//import com.wb.baselib.BaseApplication;
//import com.wb.baselib.appconfig.AppConfig;
//import com.wb.baselib.appconfig.AppConfigManager;
//import com.wb.baselib.crash.CrashHandler;
//import com.wb.baselib.http.HttpConfig;
//import com.wb.baselib.log.LogTools;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class mApp extends BaseApplication{
//    @Override
//    public String getRootPackAge() {
//        return "dsdsfsd";
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        List<Integer> httpCodeOff = new ArrayList<>();
//        httpCodeOff.add(201);
//        AppConfig appConfig = new AppConfig.Bulider()
//                .setMaxPage(10)
//                .setHttpCodeOff(httpCodeOff)
//                .setSendCarshLog(true)
//                .setCatshLog(true)
//                .setSendCarshLog(true)
//                .bulider();
//        AppConfigManager.newInstance().setAppConfig(appConfig);
//        LogTools.setDebug(true);
//
//        HttpConfig.HttpConfigBuilder httpConfig =
//                new HttpConfig.HttpConfigBuilder()
//                        .setmBaseUrl("http://test-px.huatu.com")
//                        .setmIsUseLog(true);
//        HttpConfig.newInstanceBuild(httpConfig);
//        CrashHandler.getInstance().init(this);
//    }
//}
