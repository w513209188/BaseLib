package com.jungan.www.module_down.mvp.presenter;

import android.util.Log;

import com.baijiayun.download.DownloadTask;
import com.jungan.www.common_down.BjyPlayDownManager;
import com.jungan.www.common_down.call.DownVideoCall;
import com.jungan.www.module_down.bean.DownManagerBean;
import com.jungan.www.module_down.mvp.contranct.DownManagerContranct;
import com.jungan.www.module_down.mvp.model.DownManagerModel;

import java.util.List;

import io.reactivex.disposables.Disposable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;

public class DownManagerPresenter extends DownManagerContranct.DownManagerPresenter {
    public DownManagerPresenter(DownManagerContranct.DownManagerView iView) {
        this.mView=iView;
        this.mModel=new DownManagerModel();
    }

    @Override
    public void getAllDownVideo() {
        Log.e("进来了","-------");
        mModel.getAllDownVideo().subscribe(new io.reactivex.Observer<DownManagerBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                addSubscribe(d);
            }

            @Override
            public void onNext(DownManagerBean value) {
                Log.e("-----","这个进来了");
                if(mView==null){
                    return;
                }
                mView.SuccessData(value);
            }

            @Override
            public void onError(Throwable e) {
                if(mView==null){
                    return;
                }
                mView.NoData();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void reshData() {
        mModel.reshData().subscribe(new io.reactivex.Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long value) {
                Log.e("获取的",value+"***");
                mView.reshData();
//                BjyPlayDownManager.Instance().getDownVideoByUid("1", false, new DownVideoCall() {
//                    @Override
//                    public void getDownVideo(List<DownloadTask> downloadTaskList) {
//                        mView.reshData();
//                    }
//                });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
