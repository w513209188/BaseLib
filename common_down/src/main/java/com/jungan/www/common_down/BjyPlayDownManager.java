package com.jungan.www.common_down;
import android.util.Log;

import com.baijia.player.playback.downloader.PlaybackDownloader;
import com.baijiahulian.common.networkv2.HttpException;
import com.baijiayun.download.DownloadListener;
import com.baijiayun.download.DownloadManager;
import com.baijiayun.download.DownloadModel;
import com.baijiayun.download.DownloadService;
import com.baijiayun.download.DownloadTask;
import com.baijiayun.download.constant.VideoDefinition;
import com.jungan.www.common_down.bean.PlayDownConfig;
import com.jungan.www.common_down.call.DownVideoCall;
import com.jungan.www.common_down.call.DownVideoStrCall;
import com.jungan.www.common_down.call.VideoDeleteCall;
import com.jungan.www.common_down.config.BjyPlayDownConfig;
import com.jungan.www.common_down.config.DownVideoMessageTypeConfig;
import com.jungan.www.common_down.tools.StriTools;
import com.wb.rxbus.taskBean.RxBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.baijiayun.download.constant.TaskStatus.Finish;
/**
 * 百家云视频播放下载控制器
 *  需要注意的是这里需要WRITE_EXTERNAL_STORAGE和READ_EXTERNAL_STORAGE权限
 */
public class BjyPlayDownManager {
    private static BjyPlayDownManager bjyPlayDownManager;
    public static BjyPlayDownManager Instance(){
        synchronized (BjyPlayDownManager.class){
            if(bjyPlayDownManager==null){
                bjyPlayDownManager=new BjyPlayDownManager();
            }
        }
        return bjyPlayDownManager;
    }
    /**
     * 开始下载
     */
    public void startDown(PlayDownConfig playDownBean) throws Exception {
        if(playDownBean.getFileName()==null||playDownBean.getFileName().equals(""))
            throw new  Exception("FileName is null!");
        if( playDownBean.getVideoId()==0)
            throw new Exception("videoId is empty!");
        if(playDownBean.getToken()==null||playDownBean.getToken().equals(""))
            throw new Exception("token is null!");
        if(playDownBean.getuId()==0)
            throw new Exception("uid is empty!");
         List<VideoDefinition> definitionList = new ArrayList<>(Arrays.asList(VideoDefinition._720P,
                VideoDefinition.SHD,
                 VideoDefinition.HD,
                 VideoDefinition.SD,
                 VideoDefinition._1080P));
        getDownloadManager().newDownloadTask(playDownBean.getFileName(), playDownBean.getVideoId(), playDownBean.getToken(), definitionList, playDownBean.getEncryptType(), playDownBean.getuId()+","+playDownBean.getOccName()+","+playDownBean.getCourerName()+","+playDownBean.getSectionName())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DownloadTask>() {
                    @Override
                    public void call(DownloadTask task) {

                        //直接开始下载
                        task.start();
                        task.setDownloadListener(new DownloadListener() {
                            @Override
                            public void onProgress(DownloadTask downloadTask) {
                                Log.e("onProgress","-----"+downloadTask.getProgress());
                            }

                            @Override
                            public void onError(DownloadTask downloadTask, HttpException e) {
                                Log.e("onError","-----");
                            }

                            @Override
                            public void onPaused(DownloadTask downloadTask) {
                                Log.e("onPaused","-----");
                            }

                            @Override
                            public void onStarted(DownloadTask downloadTask) {
                                Log.e("onStarted","-----");
                                RxBus.getIntanceBus().post(DownVideoMessageTypeConfig.RESHVIDEO);
                            }

                            @Override
                            public void onFinish(DownloadTask downloadTask) {
                                Log.e("onFinish","-----");
                                RxBus.getIntanceBus().post(DownVideoMessageTypeConfig.RESHVIDEO);
                            }

                            @Override
                            public void onDeleted(long l) {
                                Log.e("onDeleted","-----");
                            }
                        });
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }
    public DownloadManager getDownloadManager(){
        //初始化下载
        DownloadManager manager = DownloadService.getDownloadManager(BjyPlayDownConfig.newInstacne().getmActivity());
        //设置缓存文件路径
        manager.setTargetFolder(BjyPlayDownConfig.newInstacne().getFilePath());
        //读取磁盘缓存的下载任务并初始化加密参数
        manager.loadDownloadInfo(BjyPlayDownConfig.newInstacne().getBjyId(), 1);
        return manager;
    }
    /**
     * 获取所有下载视频
     * @return
     */
    public void getAllDownVideo(final String uid, final DownVideoCall mCall){
        final List<DownloadTask> downloadTaskList=new ArrayList<>();
        Observable.create(new Observable.OnSubscribe<List<DownloadTask>>() {
            @Override
            public void call(Subscriber<? super List<DownloadTask>> subscriber) {
                List<DownloadTask> allVideo=getDownloadManager().getAllTasks();
                List<DownloadTask> newAllVideo=new ArrayList<>();
                for(DownloadTask downloadTask:allVideo){
                            DownloadModel downloadModel=downloadTask.getDownloadInfo();
                            //获取保存数据 0 用户Id 1 职业 2课程 3章
                            String[] exx=StriTools.convertStrToArray(downloadModel.extraInfo);
                            if(exx[0].equals(uid)){
                                newAllVideo.add(downloadTask);
                            }
                }
                subscriber.onNext(newAllVideo);
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<DownloadTask>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mCall.getDownVideo(downloadTaskList);
            }

            @Override
            public void onNext(List<DownloadTask> downloadTasks) {
                if(downloadTasks==null){
                }else {
                    downloadTaskList.addAll(downloadTasks);
                }
                mCall.getDownVideo(downloadTaskList);
            }
        });
    }

    /**
     * 通过uid获取下载的视频
     * @param uid  用户id
     * @param isFinsh 是否搜索下载完成
     * @return
     */
    public void getDownVideoByUid(final String uid, final boolean isFinsh, final DownVideoCall mCall){
        final List<DownloadTask> downloadTaskList=new ArrayList<>();;
        Observable.create(new Observable.OnSubscribe<List<DownloadTask>>() {
            @Override
            public void call(Subscriber<? super List<DownloadTask>> subscriber) {
                List<DownloadTask> allVideo=getDownloadManager().getAllTasks();
                List<DownloadTask> newAllVideo=new ArrayList<>();
                for(DownloadTask downloadTask:allVideo){
                    if(isFinsh){
                        if(downloadTask.getTaskStatus()==Finish){
                            DownloadModel downloadModel=downloadTask.getDownloadInfo();
                            //获取保存数据 0 用户Id 1 职业 2课程 3章
                            String[] exx=StriTools.convertStrToArray(downloadModel.extraInfo);
                            if(exx[0].equals(uid)){
                                newAllVideo.add(downloadTask);
                            }
                        }
                    }else {
                        if(downloadTask.getTaskStatus()!=Finish){
                            DownloadModel downloadModel=downloadTask.getDownloadInfo();
                            //获取保存数据 0 用户Id 1 职业 2课程 3章
                            String[] exx=StriTools.convertStrToArray(downloadModel.extraInfo);
                            if(exx[0].equals(uid)){
                                newAllVideo.add(downloadTask);
                            }
                        }
                    }

                }
                subscriber.onNext(newAllVideo);
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<DownloadTask>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<DownloadTask> downloadTasks) {
                if(downloadTasks==null){
                }else {
                    downloadTaskList.addAll(downloadTasks);
                }
                mCall.getDownVideo(downloadTaskList);
            }
        });
    }

    /**
     * 通过uid和职业获取下载视频
     * @param uid 用户id
     * @param occName 职业名称
     * @param isFinsh 是否下载完成
     * @return
     */
    public void getDownVideoBy(final String uid, final String occName, final boolean isFinsh, final DownVideoCall mCall){
        final List<DownloadTask> downloadTaskList=new ArrayList<>();;
        Observable.create(new Observable.OnSubscribe<List<DownloadTask>>() {
            @Override
            public void call(Subscriber<? super List<DownloadTask>> subscriber) {
                List<DownloadTask> allVideo=getDownloadManager().getAllTasks();
                List<DownloadTask> newAllVideo=new ArrayList<>();
                for(DownloadTask downloadTask:allVideo){
                    if(isFinsh){
                        if(downloadTask.getTaskStatus()==Finish){
                            DownloadModel downloadModel=downloadTask.getDownloadInfo();
                            //获取保存数据 0 用户Id 1 职业 2课程 3章
                            String[] exx=StriTools.convertStrToArray(downloadModel.extraInfo);
                            if(exx[0].equals(uid)&&exx[1].equals(occName)){
                                newAllVideo.add(downloadTask);
                            }
                        }
                    }else {
                        if(downloadTask.getTaskStatus()!=Finish){
                            DownloadModel downloadModel=downloadTask.getDownloadInfo();
                            //获取保存数据 0 用户Id 1 职业 2课程 3章
                            String[] exx=StriTools.convertStrToArray(downloadModel.extraInfo);
                            if(exx[0].equals(uid)&&exx[1].equals(occName)){
                                newAllVideo.add(downloadTask);
                            }
                        }
                    }

                }
                subscriber.onNext(newAllVideo);
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<DownloadTask>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<DownloadTask> downloadTasks) {
                if(downloadTasks==null){
                }else {
                    downloadTaskList.addAll(downloadTasks);
                }
                mCall.getDownVideo(downloadTaskList);
            }
        });
    }

    /**
     * 通过 uid、职业名称、课程名称获取到下载视频
     * @param uid  用户id
     * @param occName 职业名称
     * @param courseName  课程名称
     * @param isFinsh  是否下载完成
     * @return
     */
    public void getDownVideoBy(final String uid, final String occName, final String courseName, final boolean isFinsh, final DownVideoCall mCall){
        final List<DownloadTask> downloadTaskList=new ArrayList<>();;
        Observable.create(new Observable.OnSubscribe<List<DownloadTask>>() {
            @Override
            public void call(Subscriber<? super List<DownloadTask>> subscriber) {
                List<DownloadTask> allVideo=getDownloadManager().getAllTasks();
                List<DownloadTask> newAllVideo=new ArrayList<>();
                for(DownloadTask downloadTask:allVideo){
                    if(isFinsh){
                        if(downloadTask.getTaskStatus()==Finish){
                            DownloadModel downloadModel=downloadTask.getDownloadInfo();
                            //获取保存数据 0 用户Id 1 职业 2课程 3章
                            String[] exx=StriTools.convertStrToArray(downloadModel.extraInfo);
                            if(exx[0].equals(uid)&&exx[1].equals(occName)&&exx[2].equals(courseName)){
                                newAllVideo.add(downloadTask);
                            }
                        }
                    }else {
                        if(downloadTask.getTaskStatus()!=Finish){
                            DownloadModel downloadModel=downloadTask.getDownloadInfo();
                            //获取保存数据 0 用户Id 1 职业 2课程 3章
                            String[] exx=StriTools.convertStrToArray(downloadModel.extraInfo);
                            if(exx[0].equals(uid)&&exx[1].equals(occName)&&exx[2].equals(courseName)){
                                newAllVideo.add(downloadTask);
                            }
                        }
                    }

                }
                subscriber.onNext(newAllVideo);
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<DownloadTask>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<DownloadTask> downloadTasks) {
                if(downloadTasks==null){
                }else {
                    downloadTaskList.addAll(downloadTasks);
                }
                mCall.getDownVideo(downloadTaskList);
            }
        });
    }

    /**
     *  通过uid、occname、coursename、seachName获取到下载的视频
     * @param uid 用户id
     * @param occName 职业
     * @param courseName 课程
     * @param seachName 章
     * @param isFinsh 是否下载
     * @return
     */
    public void getDownVideoBy(final String uid, final String occName, final String courseName, final String seachName, final boolean isFinsh, final DownVideoCall mCall){
        final List<DownloadTask> downloadTaskList=new ArrayList<>();;
        Observable.create(new Observable.OnSubscribe<List<DownloadTask>>() {
            @Override
            public void call(Subscriber<? super List<DownloadTask>> subscriber) {
                List<DownloadTask> allVideo=getDownloadManager().getAllTasks();
                List<DownloadTask> newAllVideo=new ArrayList<>();
                for(DownloadTask downloadTask:allVideo){
                    if(isFinsh){
                        if(downloadTask.getTaskStatus()==Finish){
                            DownloadModel downloadModel=downloadTask.getDownloadInfo();
                            //获取保存数据 0 用户Id 1 职业 2课程 3章

                            String[] exx=StriTools.convertStrToArray(downloadModel.extraInfo);
                            for(String s:exx){
                                Log.e("获取的存储信息",s);
                            }
                            Log.e("这个传递的",uid+"-"+occName+"-"+seachName+"-"+courseName);
                            if(exx[0].equals(uid)&&exx[1].equals(occName)&&exx[2].equals(courseName)&&exx[3].equals(seachName)){
                                newAllVideo.add(downloadTask);
                            }
                        }
                    }else {
                        if(downloadTask.getTaskStatus()!=Finish){
                            DownloadModel downloadModel=downloadTask.getDownloadInfo();
                            //获取保存数据 0 用户Id 1 职业 2课程 3章
                            String[] exx=StriTools.convertStrToArray(downloadModel.extraInfo);
                            if(exx[0].equals(uid)&&exx[1].equals(occName)&&exx[2].equals(courseName)&&exx[3].equals(seachName)){
                                newAllVideo.add(downloadTask);
                            }
                        }
                    }

                }
                subscriber.onNext(newAllVideo);
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<DownloadTask>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<DownloadTask> downloadTasks) {
                if(downloadTasks==null){
                }else {
                    downloadTaskList.addAll(downloadTasks);
                }
                mCall.getDownVideo(downloadTasks);
            }
        });
    }

    /**
     * 获取名称集合
     * @param uid
     * @param occName
     * @param isFinsh
     * @return
     */
    public void getVideoByOccNameToKey(final String uid, final String occName, final boolean isFinsh, final DownVideoStrCall mCall){
        final List<String> stringList=new ArrayList<>();
        Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(final Subscriber<? super List<String>> subscriber) {
                final Set<String> strings=new HashSet<>();
                getDownVideoBy(uid, occName, isFinsh, new DownVideoCall() {
                    @Override
                    public void getDownVideo(List<DownloadTask> downloadTaskList) {
                        for(DownloadTask downloadTask:downloadTaskList){
                            DownloadModel downloadModel=downloadTask.getDownloadInfo();
                            if(isFinsh){
                                if(downloadTask.getTaskStatus()==Finish){
                                    String[] exx=StriTools.convertStrToArray(downloadModel.extraInfo);
                                    if(exx[1].equals(occName)){
                                        strings.add(exx[1]);
                                    }
                                }
                            }else {
                                if(downloadTask.getTaskStatus()!=Finish){
                                    String[] exx=StriTools.convertStrToArray(downloadModel.extraInfo);
                                    if(exx[1].equals(occName)){
                                        strings.add(exx[1]);
                                    }
                                }
                            }

                        }
                        subscriber.onNext(new ArrayList<>(strings));
                    }
                });

            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> strings) {
                if(strings==null||strings.size()==0){
                }else {
                    stringList.addAll(strings);
                }
                mCall.getDownVideoToName(stringList);
            }
        });
    }

    /**
     * 获取集合
     * @param uid
     * @param occName
     * @param courseName
     * @param isFinsh
     * @return
     */
    public void getVideoByOccNameToKey(final String uid, final String occName, final String courseName, final boolean isFinsh, final DownVideoStrCall mCall){
        final List<String> stringList=new ArrayList<>();
        Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(final Subscriber<? super List<String>> subscriber) {

                final Set<String> strings=new HashSet<>();
              getDownVideoBy(uid, occName, isFinsh, new DownVideoCall() {
                  @Override
                  public void getDownVideo(List<DownloadTask> downloadTaskList) {
                      for(DownloadTask downloadTask:downloadTaskList){
                          DownloadModel downloadModel=downloadTask.getDownloadInfo();
                          if(isFinsh){
                              if(downloadTask.getTaskStatus()==Finish){
                                  String[] exx=StriTools.convertStrToArray(downloadModel.extraInfo);
                                  if(exx[1].equals(occName)&&exx[2].equals(courseName)){
                                      strings.add(exx[1]);
                                  }
                              }
                          }else {
                              if(downloadTask.getTaskStatus()!=Finish){
                                  String[] exx=StriTools.convertStrToArray(downloadModel.extraInfo);
                                  if(exx[1].equals(occName)&&exx[2].equals(courseName)){
                                      strings.add(exx[2]);
                                  }
                              }
                          }

                      }
                      subscriber.onNext(new ArrayList<>(strings));
                  }
              });

            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> strings) {
                if(strings==null||strings.size()==0){
                }else {
                    stringList.addAll(strings);
                }
                mCall.getDownVideoToName(stringList);
            }
        });
    }

    /**
     * 获取集合
     * @param uid
     * @param occName
     * @param courseName
     * @param seacheName
     * @param isFinsh
     * @return
     */
    public void getVideoByOccNameToKey(final String uid, final String occName, final String courseName, final String seacheName, final boolean isFinsh, final DownVideoStrCall mCall){
        final List<String> stringList=new ArrayList<>();
        Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(final Subscriber<? super List<String>> subscriber) {

                final Set<String> strings=new HashSet<>();
                getDownVideoBy(uid, occName, isFinsh, new DownVideoCall() {
                    @Override
                    public void getDownVideo(List<DownloadTask> downloadTaskList) {
                        for(DownloadTask downloadTask:downloadTaskList){
                            DownloadModel downloadModel=downloadTask.getDownloadInfo();
                            if(isFinsh){
                                if(downloadTask.getTaskStatus()==Finish){
                                    String[] exx=StriTools.convertStrToArray(downloadModel.extraInfo);
                                    if(exx[1].equals(occName)&&exx[2].equals(courseName)&&exx[3].equals(seacheName)){
                                        strings.add(exx[1]);
                                    }
                                }
                            }else {
                                if(downloadTask.getTaskStatus()!=Finish){
                                    String[] exx=StriTools.convertStrToArray(downloadModel.extraInfo);
                                    if(exx[1].equals(occName)&&exx[2].equals(courseName)&&exx[3].equals(seacheName)){
                                        strings.add(exx[3]);
                                    }
                                }
                            }

                        }
                        subscriber.onNext(new ArrayList<>(strings));
                    }
                });

            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> strings) {
                if(strings==null||strings.size()==0){
                }else {
                    stringList.addAll(strings);
                }
                mCall.getDownVideoToName(stringList);
            }
        });
    }

    /**
     * 删除下载的视频
     * @param downloadTaskList
     * @param mCall
     */
    public void deleteAllVideo(final List<DownloadTask> downloadTaskList, final VideoDeleteCall mCall){
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                for(DownloadTask downloadTask:downloadTaskList){
                    getDownloadManager().deleteTask(downloadTask);
                }
                subscriber.onNext(true);
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mCall.isAllDeleteVideo(false);
            }

            @Override
            public void onNext(Boolean aBoolean) {
                mCall.isAllDeleteVideo(aBoolean);
            }
        });
    }
}
