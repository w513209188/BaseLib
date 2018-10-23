package com.wb.baselib;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;

import com.hss01248.dialog.ActivityStackManager;
import com.hss01248.dialog.StyledDialog;
import com.wb.baselib.crash.CrashHandler;
import com.wb.baselib.interfaces.IApplicationDelegate;
import com.wb.baselib.classs.ClassUtils;
import com.wb.baselib.app.AppUtils;
import java.util.List;
public abstract class BaseApplication extends MultiDexApplication {
    public abstract String getRootPackAge();
    private static BaseApplication sInstance;
    private List<IApplicationDelegate> mAppDelegateList;
    public static BaseApplication getIns() {
        return sInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //程序基础配置初始化
        AppUtils.init(this);
        //加载根包下的所有类
        mAppDelegateList = ClassUtils.getObjectsWithInterface(this, IApplicationDelegate.class, getRootPackAge());
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onCreate();
        }
        registCallback(this);
        //初始化奔溃日志机制
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTerminate();
        }
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onLowMemory();
        }
    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTrimMemory(level);
        }
    }
    /**
     * 初始化对话框
     */
    private void registCallback(Application application) {
        StyledDialog.init(application);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ActivityStackManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityStackManager.getInstance().removeActivity(activity);
            }
        });
    }

}
