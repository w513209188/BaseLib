package com.wb.baselib.http.observer;


import android.content.Context;

import io.reactivex.Observer;

/**
 * author:  ljy
 * date:    2017/9/27
 * description:  普通的api请求回调
 */

public abstract class CommonObserver<T> implements Observer<T> {


    public CommonObserver(Context context) {

    }

    @Override
    public void onError(Throwable throwable) {
        if (throwable instanceof Exception) {
            onError(throwable);
        } else {
            onError(new Throwable("未知错误"));
        }
    }

    @Override
    public void onNext(T t) {
        onResult(t);
    }

    public abstract void onResult(T result);

}
