package com.jungan.www.module_down.mvp.presenter;

import android.util.Log;

import com.baijiayun.download.DownloadTask;
import com.jungan.www.module_down.mvp.contranct.DownDoingVideoContranct;
import com.jungan.www.module_down.mvp.model.DownDoingVideoModel;

import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DownDoingVideoPresenter extends DownDoingVideoContranct.DownDoingVideoPresenter {
    public DownDoingVideoPresenter(DownDoingVideoContranct.DownDoingVideoView iView) {
        this.mView=iView;
        this.mModel=new DownDoingVideoModel();
    }

    @Override
    public void getDownDoingVideo() {
        mModel.getDownDoingVideo().subscribe(new Observer<List<DownloadTask>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<DownloadTask> value) {
                Log.e("这个是获取到的正在下载的大小",value.size()+"----");
                mView.getDoingDownVideList(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void userDelectVideo(Set<DownloadTask> set) {
        mModel.userDelectVideo(set).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean value) {
                mView.userDelectVideo(value);
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
