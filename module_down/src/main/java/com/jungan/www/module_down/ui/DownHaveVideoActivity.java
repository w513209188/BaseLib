package com.jungan.www.module_down.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baijiayun.download.DownloadTask;
import com.baijiayun.download.constant.DownloadType;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.jungan.www.common_down.BjyBackPlayDownManager;
import com.jungan.www.common_down.config.DownVideoMessageTypeConfig;
import com.jungan.www.module_blackplay.activity.PBRoomActivity;
import com.jungan.www.module_down.R;
import com.jungan.www.module_down.adapter.DownHaveAdapter;
import com.jungan.www.module_down.call.DownHaveVideoCall;
import com.jungan.www.module_down.config.ToActicvityConfig;
import com.jungan.www.module_down.mvp.contranct.DownHaveVideoContranct;
import com.jungan.www.module_down.mvp.presenter.DownHaveVideoPresenter;
import com.jungan.www.module_playvideo.ui.PlayVodActivity;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.wb.baselib.base.activity.MvpActivity;
import com.wb.baselib.event.RxBus;
import com.wb.baselib.log.LogTools;
import com.wb.baselib.view.MultipleStatusView;
import com.wb.baselib.view.TopBarView;

import java.util.ArrayList;
import java.util.List;

public class DownHaveVideoActivity extends MvpActivity<DownHaveVideoPresenter> implements DownHaveVideoContranct.DownHaveVideoView {
   private MultipleStatusView multipleStatusView;
   private ListView mListView;
   private List<DownloadTask> downloadTaskLists;
   private DownHaveAdapter mAdapter;
   private String occN,seachN,courseN;
   private TopBarView mTopBarView;
   private TextView delect_num_tv,delect_select_tv;
   private LinearLayout bottom_ll;
    @Override
    protected void initView(Bundle bundle) {
        setContentView(R.layout.down_havedown_layout);
        occN=getIntent().getStringExtra(ToActicvityConfig.OCCNAME_KEY);
        seachN=getIntent().getStringExtra(ToActicvityConfig.SEACHNAME_KEY);
        courseN=getIntent().getStringExtra(ToActicvityConfig.COURSENAME_KEY);
        multipleStatusView=getViewById(R.id.multiplestatusview);
        multipleStatusView.showContent();
        multipleStatusView.showLoading();
        bottom_ll=getViewById(R.id.bottom_ll);
        mListView=getViewById(R.id.p_lv);
        mTopBarView=getViewById(R.id.top_tb);
        delect_num_tv=getViewById(R.id.delect_num_tv);
        delect_select_tv=getViewById(R.id.delect_select_tv);
        downloadTaskLists=new ArrayList<>();
        mAdapter=new DownHaveAdapter(downloadTaskLists,DownHaveVideoActivity.this);
        mListView.setAdapter(mAdapter);
        mPresenter.getHaveDownVideoList(occN,seachN,courseN);
    }
    @Override
    public void getHaveDownView(List<DownloadTask> downloadTaskList) {
        if(downloadTaskList==null||downloadTaskList.size()==0){
            multipleStatusView.showEmpty();
        }else {
            downloadTaskLists.clear();
            downloadTaskLists.addAll(downloadTaskList);
            mAdapter.setVist(false);
            mAdapter.notifyDataSetChanged();
            multipleStatusView.showContent();
        }

    }

    @Override
    public void videoDelect(Boolean b) {
        RxBus.getIntanceBus().post(DownVideoMessageTypeConfig.RESHVIDEO);
        mPresenter.getHaveDownVideoList(occN,seachN,courseN);
    }

    @Override
    protected DownHaveVideoPresenter onCreatePresenter() {
        return new DownHaveVideoPresenter(this);
    }
    @Override
    protected void setListener() {
        mAdapter.setmCall(new DownHaveVideoCall() {
            @Override
            public void selectVideo() {
                if(mAdapter.getSelectDown().size()==0){
                    delect_num_tv.setText(DownHaveVideoActivity.this.getResources().getString(R.string.down_delect));
                }else {
                    delect_num_tv.setText(DownHaveVideoActivity.this.getResources().getString(R.string.down_delect)+mAdapter.getSelectDown().size());
                }

                if(mAdapter.getSelectDown().size()==downloadTaskLists.size()){
                    delect_select_tv.setText(DownHaveVideoActivity.this.getResources().getString(R.string.down_all_cancel));
                }else {
                    delect_select_tv.setText(DownHaveVideoActivity.this.getResources().getString(R.string.down_all_select));
                }
            }
        });
        delect_select_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(delect_select_tv.getText().toString().equals(DownHaveVideoActivity.this.getResources().getString(R.string.down_all_select))){
                    //全选
                    mAdapter.setAllSelect(true);
                }else {
                    //反选
                    mAdapter.setAllSelect(false);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        mTopBarView.setListener(new TopBarView.OnTitleBarListener() {
            @Override
            public void onClicked(View view, int i, String s) {
                if (i == TopBarView.ACTION_RIGHT_TEXT) {
                    TextView textView=mTopBarView.getRightTextView();
                    Log.e("看看",textView.getText().toString()+"---"+DownHaveVideoActivity.this.getResources().getString(R.string.down_delect));

                    if(textView.getText().toString().equals(DownHaveVideoActivity.this.getResources().getString(R.string.down_delect))){
                        mAdapter.setVist(true);
                        textView.setText(DownHaveVideoActivity.this.getResources().getString(R.string.down_cancel));
                        bottom_ll.setVisibility(View.VISIBLE);
                    }else {
                        mAdapter.setVist(false);
                        textView.setText(DownHaveVideoActivity.this.getResources().getString(R.string.down_delect));
                        bottom_ll.setVisibility(View.GONE);
                    }
                    mAdapter.notifyDataSetChanged();
                }else if(i==TopBarView.ACTION_LEFT_BUTTON){
                    finish();
                }
            }
        });
        delect_num_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMdDialog(DownHaveVideoActivity.this.getResources().getString(R.string.down_message), DownHaveVideoActivity.this.getResources().getString(R.string.down_agin_delect), getResources().getString(R.string.down_ok), getResources().getString(R.string.down_cancel), new MyDialogListener() {
                    @Override
                    public void onFirst() {
                        mPresenter.userDelectVideo(mAdapter.getSelectDown());
                    }

                    @Override
                    public void onSecond() {

                    }
                });
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DownloadTask downloadTask= (DownloadTask) parent.getItemAtPosition(position);
                DownloadType downloadType=downloadTask.getDownloadType();
                if(downloadType==DownloadType.Video){
                    //点播
                    Intent intent=new Intent(DownHaveVideoActivity.this, PlayVodActivity.class);
                    intent.putExtra("isOnLine","2");
                    intent.putExtra("Filepath",downloadTask.getVideoDownloadInfo().targetFolder+downloadTask.getDownloadInfo().targetName);
                    intent.putExtra("Token",downloadTask.getDownloadInfo().targetName);
                    intent.putExtra("bjyId","33197");
                    startActivity(intent);
                }else {
                    //回放
                    String sign= downloadTask.getVideoDownloadInfo().targetFolder+"/"+BjyBackPlayDownManager.Instance().getPlayBackSig(downloadTask.getDownloadInfo().roomId,downloadTask.getDownloadInfo().sessionId);
                    Intent intent=new Intent(DownHaveVideoActivity.this, PBRoomActivity.class);
                    intent.putExtra("pb_room_id",downloadTask.getDownloadInfo().roomId+"");
                    intent.putExtra("pb_room_video_file_path",downloadTask.getVideoDownloadInfo().targetFolder+"/"+downloadTask.getVideoDownloadInfo().targetName);
                    intent.putExtra("pb_room_signal_file_path",sign);
                    startActivity(intent);
                }
//                LogTools.e("文件"+downloadTask.getVideoDownloadInfo().targetFolder);
//                String sign= downloadTask.getVideoDownloadInfo().targetFolder+"/"+BjyBackPlayDownManager.Instance().getPlayBackSig(downloadTask.getDownloadInfo().roomId,downloadTask.getDownloadInfo().sessionId);
//                Log.e("获取到的信息包",sign+"---");
//                Log.e("文件路径",downloadTask.getVideoDownloadInfo().targetFolder+"/"+downloadTask.getVideoDownloadInfo().targetName);
////                roomId = getIntent().getStringExtra(ConstantUtil.PB_ROOM_ID);
////                videoFilePath = getIntent().getStringExtra(ConstantUtil.PB_ROOM_VIDEOFILE_PATH);
////                signalFilePath = getIntent().getStringExtra(ConstantUtil.PB_ROOM_SIGNALFILE_PATH);
////                deployType = getIntent().getIntExtra(ConstantUtil.PB_ROOM_DEPLOY, 2);
//
//                ARouter.getInstance().build("/backplay/play").withString("pb_room_id",downloadTask.getDownloadInfo().roomId+"").withString("pb_room_video_file_path",downloadTask.getVideoDownloadInfo().targetFolder+"/"+downloadTask.getVideoDownloadInfo().targetName).withString("pb_room_signal_file_path",sign).navigation();

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
}
