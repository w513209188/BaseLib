package com.jungan.www.module_down.mvp.presenter;

import android.util.Log;

import com.baijiayun.download.DownloadTask;
import com.jungan.www.module_down.mvp.contranct.DownHaveVideoContranct;
import com.jungan.www.module_down.mvp.model.DownHaveVideoModel;

import java.util.List;
import java.util.Set;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public  class DownHaveVideoPresenter extends DownHaveVideoContranct.DownHaveVideoPresenter {
    public DownHaveVideoPresenter(DownHaveVideoContranct.DownHaveVideoView iView) {
        this.mView=iView;
        this.mModel=new DownHaveVideoModel();
    }

    @Override
    public void getHaveDownVideoList(String occ,String seach,String course){
        mModel.getHaveDownVideoList(occ,seach,course).subscribe(new Observer<List<DownloadTask>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<DownloadTask> value) {
                Log.e("获取到下载的大小",value.size()+"----");
                mView.getHaveDownView(value);
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
    public void userDelectVideo(Set<DownloadTask> downloadTasks) {
        mModel.userDelectVideo(downloadTasks).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean value) {
                mView.videoDelect(value);
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
