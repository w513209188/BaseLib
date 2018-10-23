package com.wb.baselib.http.observer;

import android.content.Context;
import android.util.Log;

import com.wb.baselib.appconfig.AppConfigManager;
import com.wb.baselib.http.exception.ApiErrorHelper;
import com.wb.baselib.http.exception.ApiException;
import com.wb.rxbus.taskBean.RxBus;
import com.wb.rxbus.taskBean.RxLoginBean;

import io.reactivex.Observer;


public abstract class BaseObserver<T> implements Observer<T> {
    private Context mContext;

    private BaseObserver() {
    }

    protected BaseObserver(Context context) {
        mContext = context;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }


    @Override
    public void onError(Throwable e) {
        ApiException apiException = ApiErrorHelper.handleCommonError(mContext, e);
        for(Integer integer: AppConfigManager.newInstance().getAppConfig().getHttpCodeOff()){
            if(apiException.getErrorCode()==integer){
                RxBus.getIntanceBus().post(new RxLoginBean(integer));
            }
        }
        onFail(apiException);
    }

    @Override
    public void onNext(T t) {
        Log.e("baseInfo",t.toString());
        onSuccess(t);

    }
    public abstract void onSuccess(T t);
    public abstract void onFail(ApiException e);
}
