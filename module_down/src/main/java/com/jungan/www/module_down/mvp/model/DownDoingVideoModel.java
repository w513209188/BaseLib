package com.jungan.www.module_down.mvp.model;

import com.baijiayun.download.DownloadTask;
import com.jungan.www.common_down.BjyBackPlayDownManager;
import com.jungan.www.common_down.BjyPlayDownManager;
import com.jungan.www.common_down.call.DownVideoCall;
import com.jungan.www.common_down.call.VideoDeleteCall;
import com.jungan.www.module_down.mvp.contranct.DownDoingVideoContranct;
import com.wb.baselib.user.AppLoginUserInfoUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DownDoingVideoModel implements DownDoingVideoContranct.DownDoingVideoModel {
    @Override
    public Observable<List<DownloadTask>> getDownDoingVideo() {
        Observable<List<DownloadTask>> observable=Observable.create(new ObservableOnSubscribe<List<DownloadTask>>() {
            @Override
            public void subscribe(ObservableEmitter<List<DownloadTask>> e) throws Exception {
                getDownDoingVideoList(e);
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    @Override
    public Observable<Boolean> userDelectVideo(final Set<DownloadTask> set) {
        Observable<Boolean> observable=Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                    userDelectVideo(e,set);
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    private void getDownDoingVideoList(final ObservableEmitter<List<DownloadTask>> e){
        BjyBackPlayDownManager.Instance().getDownVideoByUid(AppLoginUserInfoUtils.getInstance().getUserLoginInfo().getUid(), false, new DownVideoCall() {
            @Override
            public void getDownVideo(List<DownloadTask> downloadTaskList) {
                e.onNext(downloadTaskList);
            }
        });
    }
    private void userDelectVideo(final ObservableEmitter<Boolean> e, Set<DownloadTask> downloadTasks){
        List<DownloadTask> result = new ArrayList<>(downloadTasks);
        BjyBackPlayDownManager.Instance().deleteAllVideo(result, new VideoDeleteCall() {
            @Override
            public void isAllDeleteVideo(boolean is) {
                e.onNext(is);
            }
        });
    }

}
