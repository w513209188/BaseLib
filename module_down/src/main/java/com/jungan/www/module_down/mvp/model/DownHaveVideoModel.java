package com.jungan.www.module_down.mvp.model;

import android.util.Log;

import com.baijiayun.download.DownloadTask;
import com.jungan.www.common_down.BjyBackPlayDownManager;
import com.jungan.www.common_down.BjyPlayDownManager;
import com.jungan.www.common_down.call.DownVideoCall;
import com.jungan.www.common_down.call.VideoDeleteCall;
import com.jungan.www.module_down.mvp.contranct.DownHaveVideoContranct;
import com.wb.baselib.user.AppLoginUserInfoUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DownHaveVideoModel implements DownHaveVideoContranct.DownHaveVideoModel {

    @Override
    public Observable<List<DownloadTask>> getHaveDownVideoList(final String occ, final String seach, final String course) {
        Observable<List<DownloadTask>> observable=Observable.create(new ObservableOnSubscribe<List<DownloadTask>>() {
            @Override
            public void subscribe(ObservableEmitter<List<DownloadTask>> e) throws Exception {
                getHaveDownView(e,occ,seach,course);
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    @Override
    public Observable<Boolean> userDelectVideo(final Set<DownloadTask> downloadTasks) {
        Observable<Boolean> observable=Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                userDelectVideo(downloadTasks,e);
            }
        }).observeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    private void getHaveDownView(final ObservableEmitter<List<DownloadTask>> e,String occ,String seach,String course){
        BjyBackPlayDownManager.Instance().getDownVideoBy(AppLoginUserInfoUtils.getInstance().getUserLoginInfo().getUid(), occ, seach, course, true, new DownVideoCall() {
            @Override
            public void getDownVideo(List<DownloadTask> downloadTaskList) {
                e.onNext(downloadTaskList);
            }
        });
    }
    private void userDelectVideo(Set<DownloadTask> set, final ObservableEmitter<Boolean> e){
        List<DownloadTask> result = new ArrayList<>(set);
        BjyBackPlayDownManager.Instance().deleteAllVideo(result, new VideoDeleteCall() {
                @Override
                public void isAllDeleteVideo(boolean is) {
                    Log.e("删除完毕","-----");
                    e.onNext(is);
                }
            });
    }
}
