package com.wb.baselib.base.mvp;

import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.Observable;

import io.reactivex.ObservableTransformer;

/**
 * Created by desin on 2017/1/12.
 */

public interface BaseView<T> {
    //展示错误信息
    void showErrorMsg(String msg);
    //展示等待加载动画
    void showLoadV(String msg);
    //关闭等待动画
    void closeLoadV();
    void SuccessData(T t);
    //初始化泄漏
    LifecycleTransformer binLifecycle();
}
