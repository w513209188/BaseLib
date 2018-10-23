package com.jungan.www.module_down.ui;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.baijiayun.download.DownloadTask;
import com.jungan.www.common_down.BjyBackPlayDownManager;
import com.jungan.www.common_down.bean.PlayDownConfig;
import com.jungan.www.common_down.config.BjyPlayDownConfig;
import com.jungan.www.common_down.config.DownVideoMessageTypeConfig;
import com.jungan.www.module_down.R;
import com.jungan.www.module_down.adapter.DownManagerAdapter;
import com.jungan.www.module_down.bean.DownManagerBean;
import com.jungan.www.module_down.mvp.contranct.DownManagerContranct;
import com.jungan.www.module_down.mvp.presenter.DownManagerPresenter;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.wb.baselib.base.activity.MvpActivity;
import com.wb.baselib.event.RxBus;
import com.wb.baselib.permissions.PerMissionsManager;
import com.wb.baselib.permissions.interfaces.PerMissionCall;
import com.wb.baselib.view.MultipleStatusView;
import com.wb.baselib.view.TopBarView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.functions.Consumer;

/**
 * 这个是下载管理器
 */
@Route(path = "/down/downmager")
public class DownManagerActivity extends MvpActivity<DownManagerPresenter> implements DownManagerContranct.DownManagerView {
    private MultipleStatusView multipleStatusView;
    private ListView mListView;
    private TopBarView topBarView;
    private List<DownloadTask> downloadTaskList;
    private Button down_bt;
    @Override
    protected void initView(Bundle bundle) {
        setContentView(R.layout.down_downmanager_layout);
        new BjyPlayDownConfig.Builder().with(DownManagerActivity.this).setBjyId(33177992).setFilePath(getVideoDownLoadPath()).bulider();
        multipleStatusView=getViewById(R.id.multiplestatusview);
        multipleStatusView.showContent();
        multipleStatusView.showLoading();
        mListView=getViewById(R.id.p_lv);
        down_bt=getViewById(R.id.down_bt);
        topBarView=getViewById(R.id.down_top);
        downloadTaskList=new ArrayList<>();
        mListView.setDivider(null);
        RxBus.getIntanceBus().registerRxBus(Integer.class, new Consumer<Integer>() {
            @Override
            public void accept(Integer s) throws Exception {
                //监听器
                        if(s== DownVideoMessageTypeConfig.RESHVIDEO){
                            mPresenter.getAllDownVideo();
                        }else if(s==DownVideoMessageTypeConfig.RESHDOING){
                            mPresenter.getAllDownVideo();
                        }
            }
        });
        PerMissionsManager.newInstance().getUserPerMissions(DownManagerActivity.this, new PerMissionCall() {
            @Override
            public void userPerMissionStatus(boolean is) {
                mPresenter.getAllDownVideo();
            }
        },new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE});
    }
    @Override
    public void SuccessData(DownManagerBean downManagerBean) {
        if(downManagerBean==null){
            multipleStatusView.showEmpty();
        }else {
            if(downManagerBean.isHave()||downManagerBean.isDoing()){
                if(downManagerBean.getDownDoingBean().getDownloadTasks()==null||downManagerBean.getDownDoingBean().getDownloadTasks().size()==0){
                    //没有下载
                }else {
                    downloadTaskList.addAll(downManagerBean.getDownDoingBean().getDownloadTasks());

                }
                mListView.setAdapter(new DownManagerAdapter(DownManagerActivity.this,downManagerBean));
                multipleStatusView.showContent();
            }else {
                multipleStatusView.showEmpty();
            }
        }
    }

    @Override
    public void reshData() {
        mPresenter.getAllDownVideo();
    }

    @Override
    protected DownManagerPresenter onCreatePresenter() {
        return new DownManagerPresenter(this);
    }

    @Override
    protected void setListener() {
        topBarView.setListener(new TopBarView.OnTitleBarListener() {
            @Override
            public void onClicked(View view, int i, String s) {
                if(i==TopBarView.ACTION_LEFT_BUTTON){
                    finish();
                }
            }
        });
    }
    @Override
    protected void processLogic(Bundle bundle) {

    }

    @Override
    public void ShowLoadView() {
        multipleStatusView.showLoading();
    }

    @Override
    public void NoNetWork() {
        multipleStatusView.showNoNetwork();
    }

    @Override
    public void NoData() {
            multipleStatusView.showEmpty();
    }

    @Override
    public void ErrorData() {
        multipleStatusView.showError();
    }

    @Override
    public void showErrorMsg(String s) {
        showLongToast(s);
    }

    @Override
    public void showLoadV(String s) {

    }

    @Override
    public void closeLoadV() {

    }

    @Override
    public void SuccessData(Object o) {

    }

    @Override
    public LifecycleTransformer binLifecycle() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getIntanceBus().unSubscribe(this);
    }
    public  String getVideoDownLoadPath(){
        String mCacheDirPath=null;
        File cacheDir = getExternalFilesDir("VideoFiles");
        if(null != cacheDir){
            mCacheDirPath = cacheDir.getAbsolutePath()+"/";
        }
        if(TextUtils.isEmpty(mCacheDirPath)){
            if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
                mCacheDirPath = Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.zhiyun88.www/cache/VideoFiles/";
            }
        }
        return mCacheDirPath;
    }
}
