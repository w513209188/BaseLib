package com.wb.baselib.appconfig;

import android.util.Log;

import com.wb.baselib.app.AppUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 程序配置管理器
 */
public class AppConfigManager {
    public static Map<String, Long> map;
    private static AppConfigManager appConfigManager;
    private AppConfig appConfig;
    public static AppConfigManager newInstance() {
        synchronized (AppConfigManager.class) {
            if (appConfigManager == null) {
                appConfigManager = new AppConfigManager();
            }
        }
        return appConfigManager;
    }

    /**
     * 获取程序配置信息
     * @return
     */
    public AppConfig getAppConfig(){
        if(appConfig==null){
            throw new NullPointerException();
        }
        return appConfig;
    }

    /**
     * 配置全局信息
     * @param appConfig
     */
    public void setAppConfig(AppConfig appConfig){
        if(appConfig==null){
            Log.e("appConfig","yes");
        }else {
            Log.e("appConfig","no");
        }
        this.appConfig=appConfig;
    }
}
