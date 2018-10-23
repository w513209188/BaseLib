package com.jungan.www.module_down.mvp.model;
import android.util.Log;

import com.baijiayun.download.DownloadModel;
import com.baijiayun.download.DownloadTask;
import com.jungan.www.common_down.BjyBackPlayDownManager;
import com.jungan.www.common_down.BjyPlayDownManager;
import com.jungan.www.common_down.call.DownVideoCall;
import com.jungan.www.common_down.tools.StriTools;
import com.jungan.www.module_down.bean.DownDoingBean;
import com.jungan.www.module_down.bean.DownHaveBean;
import com.jungan.www.module_down.bean.DownManagerBean;
import com.jungan.www.module_down.mvp.contranct.DownManagerContranct;
import com.wb.baselib.user.AppLoginUserInfoUtils;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.baijiayun.download.constant.TaskStatus.Finish;

public class DownManagerModel implements DownManagerContranct.DownManagerModel {

    @Override
    public Observable<DownManagerBean> getAllDownVideo() {
        Log.e("这个是model","------");

        Observable<DownManagerBean> observable=Observable.create(new ObservableOnSubscribe<DownManagerBean>() {
            @Override
            public void subscribe(ObservableEmitter<DownManagerBean> e) throws Exception {
                Log.e("这个是model2","------");
                getAll(e, AppLoginUserInfoUtils.getInstance().getUserLoginInfo().getUid());
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

//                (new ObservableOnSubscribe<DownManagerBean>() {
//                    @Override
//                    public void call(Subscriber<? super DownManagerBean> subscriber) {
//                        getAll(subscriber,"1");
//                    }
//                }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    @Override
    public Observable<Long> reshData() {
//        Observable observable=Observable.interval(0, 1, TimeUnit.SECONDS)
//                .map(new Function() {
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
        Observable<Long> observable=Observable.interval(0,5,TimeUnit.SECONDS).map(new Function<Long, Long>() {
            @Override
            public Long apply(Long aLong) throws Exception {
                return 6L;
            }
        }) .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    private void getAll(final ObservableEmitter<DownManagerBean> subscriber, String uid){
        final List<DownloadTask> downloadDoingTaskList=new ArrayList<>();
        final List<DownHaveBean> downloadHaveTaskList=new ArrayList<>();
        BjyBackPlayDownManager.Instance().getAllDownVideo(uid,new DownVideoCall() {
            @Override
            public void getDownVideo(List<DownloadTask> downloadTaskList) {
                Log.e("downloadTaskList",downloadTaskList.size()+"*****");
                for(DownloadTask task:downloadTaskList){
                    Log.e("task.getTaskStatus()",task.getTaskStatus()+"");
                    if(task.getTaskStatus()== Finish){
                        //下载完成的
                        DownloadModel downloadModel=task.getDownloadInfo();
                        //获取保存数据 0 用户Id 1 职业 2课程 3章
                        String[] exx= StriTools.convertStrToArray(downloadModel.extraInfo);
                        DownHaveBean downHaveBean=new DownHaveBean();
                        downHaveBean.setCourseName(exx[2]);
                        downHaveBean.setGroup(2);
                        downHaveBean.setOccName(exx[1]);
                        downHaveBean.setSeachName(exx[3]);
//                        downHaveBean.setVideoCont(downloadTaskList.size());
                        downHaveBean.setVideoName(task.getFileName());
                        downHaveBean.setUid(exx[0]);
                        downloadHaveTaskList.add(downHaveBean);
                    }else {
                        //未下载完成的
                        downloadDoingTaskList.add(task);
                    }
                }
                removeDuplicate(downloadHaveTaskList);
                DownDoingBean downDoingBean=new DownDoingBean();
                downDoingBean.setGroup(1);
                downDoingBean.setDownloadTasks(downloadDoingTaskList);
                downDoingBean.setVideoCont(downloadDoingTaskList.size());
                for(final DownHaveBean downHaveBean:downloadHaveTaskList){
                    //下载的循环
                    BjyPlayDownManager.Instance().getDownVideoBy(downHaveBean.getUid(), downHaveBean.getOccName(), downHaveBean.getCourseName(), downHaveBean.getSeachName(), true, new DownVideoCall() {
                        @Override
                        public void getDownVideo(List<DownloadTask> downloadTaskList) {
                            downHaveBean.setVideoCont(downloadTaskList.size());
                        }
                    });
                }
                DownManagerBean downManagerBean=new DownManagerBean();
                downManagerBean.setDownDoingBean(downDoingBean);
                if(downDoingBean.getVideoCont()==0){
                    downManagerBean.setDoing(false);
                }else {
                    downManagerBean.setDoing(true);
                }

                if(downloadHaveTaskList==null||downloadHaveTaskList.size()==0){
                    downManagerBean.setHave(false);
                }else {
                    downManagerBean.setHave(true);
                }
                downManagerBean.setDownHaveBeans(downloadHaveTaskList);
                subscriber.onNext(downManagerBean);
            }
        });
    }
    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }
}
