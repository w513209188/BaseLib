package com.example.seele.myapplication.mvp.presenter;

import android.util.Log;

import com.example.seele.myapplication.mvp.contranct.TestContranct;
import com.example.seele.myapplication.mvp.model.TestModel;
import com.wb.baselib.app.AppUtils;
import com.wb.baselib.bean.Result;
import com.wb.baselib.http.HttpManager;
import com.wb.baselib.http.exception.ApiException;
import com.wb.baselib.http.observer.BaseObserver;

import io.reactivex.disposables.Disposable;

public class TestPresenter extends TestContranct.TestPresenter {
    public TestPresenter(TestContranct.TestView iView) {
        this.mView=iView;
        this.mModel=new TestModel();
    }

    @Override
    public void getTestData() {
        HttpManager.newInstance().commonRequest(mModel.getTestData(), new BaseObserver<Result>(AppUtils.getContext()) {
            @Override
            public void onSuccess(Result o) {
                Log.e("获取的数据",o.getMsg());
            }

            @Override
            public void onFail(ApiException e) {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }
        },mView.binLifecycle());
    }
}
