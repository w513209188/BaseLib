package com.wb.baselib.base.mvp;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.wb.baselib.base.mvp.BaseView;

/**
 * Created by desin on 2017/2/23.
 */

public interface MvpView extends BaseView {
    //执行加载动画
    void ShowLoadView();
    //没有网络
    void NoNetWork();
    //没有数据
    void NoData();
    //请求到错误数据
    void ErrorData();
}
