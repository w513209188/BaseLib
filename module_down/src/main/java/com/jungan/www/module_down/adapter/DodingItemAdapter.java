package com.jungan.www.module_down.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baijiahulian.common.networkv2.HttpException;
import com.baijiayun.download.DownloadListener;
import com.baijiayun.download.DownloadModel;
import com.baijiayun.download.DownloadTask;
import com.jungan.www.common_down.call.VideoDeleteCall;
import com.jungan.www.module_down.R;
import com.jungan.www.module_down.call.DoingDownCall;
import com.jungan.www.module_down.call.DownHaveVideoCall;
import com.wb.baselib.event.RxBus;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DodingItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<DownloadTask> downloadTaskList;
    private DoingDownCall mCall;
    private boolean Vist=false;
    private Set<DownloadTask> selectDown;
    private boolean isAllSelect=false;
    private DownHaveVideoCall call;

    public void setCall(DownHaveVideoCall call) {
        this.call = call;
    }
    public void setAllSelect(boolean allSelect) {
        isAllSelect = allSelect;
    }

    public Set<DownloadTask> getSelectDown() {
        return selectDown;
    }

    private Map<String, View> mConvertViews = new LinkedHashMap<String, View>();
    public DodingItemAdapter(List<DownloadTask> tasks, Context mContext) {
        this.mContext = mContext;
        this.downloadTaskList=tasks;
        selectDown=new HashSet<>();
        mCall= (DoingDownCall) mContext;
    }

    public void setVist(boolean vist) {
        Vist = vist;
    }

    @Override
    public int getCount() {
        return downloadTaskList.size();
    }
    @Override
    public Object getItem(int position) {
        return downloadTaskList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View arg1, ViewGroup parent) {
        VideoDoingHolder holder=null;
         final DownloadTask task= (DownloadTask) getItem(position);
        final DownloadModel model = task.getDownloadInfo();
        View view=mConvertViews.get(task.getDownloadInfo().url);
        if(view==null){
            holder=new VideoDoingHolder();
            view= LayoutInflater.from(mContext).inflate(R.layout.down_doingitem_layout,null);
            holder.pbProgress= (ProgressBar) view.findViewById(R.id.pbProgress);
            holder.tvFileName= (TextView) view.findViewById(R.id.tvFileName);
            holder.tvDownloadSize= (TextView) view.findViewById(R.id.tvDownloadSize);
            holder.tvTotalSize= (TextView) view.findViewById(R.id.tvTotalSize);
            holder.tvPercent= (TextView) view.findViewById(R.id.tvPercent);
            holder.down_xz_rel=view.findViewById(R.id.down_xz_rel);
            holder.select_cx=view.findViewById(R.id.select_cx);
            mConvertViews.put(task.getDownloadInfo().url, view);
            view.setTag(holder);
        }else {
            holder= (VideoDoingHolder) view.getTag();
        }
        if(Vist){
            holder.select_cx.setVisibility(View.VISIBLE);
        }else {
            holder.select_cx.setVisibility(View.GONE);
        }
        //视频下载的长度
        final String downloadLength = Formatter.formatFileSize(mContext, model.downloadLength);
        //视频总长度
        final String totalLength = Formatter.formatFileSize(mContext, model.totalLength);
        //视频下载进度
        final int progress = (int) (model.downloadLength / (float) model.totalLength * 100);
        holder.pbProgress.setProgress(progress);
        holder.tvPercent.setText(progress+"%");
        holder.tvDownloadSize.setText(downloadLength+"/");
        holder.tvTotalSize.setText(totalLength);
        holder.tvFileName.setText(model.videoName);
        switch (task.getTaskStatus()) {
            case Error:
                //下载出错
                holder.tvPercent.setText(mContext.getResources().getString(R.string.down_video_error));
                final VideoDoingHolder finalHolder = holder;
                holder.down_xz_rel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(Vist){
                            finalHolder.down_xz_rel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(finalHolder.select_cx.isChecked()){
                                        finalHolder.select_cx.setChecked(false);
                                        selectDown.remove(task);
                                    }else {
                                        finalHolder.select_cx.setChecked(true);
                                        selectDown.add(task);
                                    }
                                    if(mCall==null)
                                        return;
                                    call.selectVideo();
                                }
                            });
                        }else {
                            task.start();
                        }

                    }
                });
                break;
            case New:
                //新的下载
                holder.tvPercent.setText(mContext.getResources().getString(R.string.down_video_start));
                final VideoDoingHolder finalHolder3 = holder;
                holder.down_xz_rel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(Vist){
                            finalHolder3.down_xz_rel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(finalHolder3.select_cx.isChecked()){
                                        finalHolder3.select_cx.setChecked(false);
                                        selectDown.remove(task);
                                    }else {
                                        finalHolder3.select_cx.setChecked(true);
                                        selectDown.add(task);
                                    }
                                    if(mCall==null)
                                        return;
                                    call.selectVideo();
                                }
                            });
                        }else {
                            task.start();
                        }
                    }
                });
                break;
            case Pause:
                //暂停下载
                holder.tvPercent.setText(mContext.getResources().getString(R.string.down_video_go));
                final VideoDoingHolder finalHolder2 = holder;
                holder.down_xz_rel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(Vist){
                            finalHolder2.down_xz_rel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(finalHolder2.select_cx.isChecked()){
                                        finalHolder2.select_cx.setChecked(false);
                                        selectDown.remove(task);
                                    }else {
                                        finalHolder2.select_cx.setChecked(true);
                                        selectDown.add(task);
                                    }
                                    if(mCall==null)
                                        return;
                                    call.selectVideo();
                                }
                            });
                        }else {
                            task.start();
                        }
                    }
                });
                break;
            case Finish:
                //下载完成
                holder.tvPercent.setText(mContext.getResources().getString(R.string.down_video_finish));
                break;
            case Downloading:
                //正在下载
                holder.tvPercent.setText(mContext.getResources().getString(R.string.down_video_doing));
                final VideoDoingHolder finalHolder1 = holder;
                holder.down_xz_rel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(Vist){
                            finalHolder1.down_xz_rel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(finalHolder1.select_cx.isChecked()){
                                        finalHolder1.select_cx.setChecked(false);
                                        selectDown.remove(task);
                                    }else {
                                        finalHolder1.select_cx.setChecked(true);
                                        selectDown.add(task);
                                    }
                                    if(mCall==null)
                                        return;
                                    call.selectVideo();
                                }
                            });
                        }else {
                            task.pause();
                        }
                    }
                });
                break;
            case Cancel:
                break;
        }
        task.setDownloadListener(new DownloadListener() {
            @Override
            public void onProgress(DownloadTask downloadTask) {
                doing(downloadTask);
            }

            @Override
            public void onError(DownloadTask downloadTask, HttpException e) {
                error(downloadTask);
            }

            @Override
            public void onPaused(DownloadTask downloadTask) {
                pause(downloadTask);
            }

            @Override
            public void onStarted(DownloadTask downloadTask) {
                reStar(downloadTask);
            }

            @Override
            public void onFinish(DownloadTask downloadTask) {
                if(mCall==null)
                    return;
                mCall.doingFinsh();
            }

            @Override
            public void onDeleted(long l) {
            }
        });


        if(Vist){
            if(isAllSelect){
                selectDown.addAll(downloadTaskList);
                holder.select_cx.setChecked(true);
                if(mCall==null){
                }else {
                    call.selectVideo();
                }
            }else {
                selectDown.clear();
                holder.select_cx.setChecked(false);
                if(mCall==null){
                }else {
                    call.selectVideo();
                }
            }
            holder.select_cx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        selectDown.add(task);
                    }else {
                        selectDown.remove(task);
                    }
                    if(mCall==null)
                        return;
                    call.selectVideo();
                }
            });

        }


        return view;
    }
    class VideoDoingHolder{
        TextView tvFileName,tvDownloadSize,tvTotalSize,tvPercent;
        ProgressBar pbProgress;
        RelativeLayout down_xz_rel;
        CheckBox select_cx;
    }

    /**
     * 正在下载
     * @param task
     */
    public void doing(final DownloadTask task){
        View view=mConvertViews.get(task.getDownloadInfo().url);
        if(view!=null){
            DownloadModel model = task.getDownloadInfo();
            VideoDoingHolder holder= (VideoDoingHolder) view.getTag();
            String downloadLength = Formatter.formatFileSize(mContext, model.downloadLength);
            String totalLength = Formatter.formatFileSize(mContext, model.totalLength);
            final int progress = (int) (model.downloadLength / (float) model.totalLength * 100);
            holder.pbProgress.setProgress(progress);
            holder.tvPercent.setText(progress+"%");
            holder.tvDownloadSize.setText(downloadLength+"/");
            holder.tvTotalSize.setText(totalLength);
            holder.down_xz_rel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    task.pause();
                }
            });
        }

    }

    /**
     * 暂停
     * @param task
     */
    public void pause(final DownloadTask task){
        View view=mConvertViews.get(task.getDownloadInfo().url);
        if(view!=null){
            VideoDoingHolder holder= (VideoDoingHolder) view.getTag();
            holder.tvPercent.setText(mContext.getResources().getString(R.string.down_video_go));
            holder.down_xz_rel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    task.start();
                }
            });
        }
    }

    /**
     *
     * @param task 继续下载
     */
    public void reStar(final DownloadTask task){
        View view=mConvertViews.get(task.getDownloadInfo().url);
        if(view!=null){
            VideoDoingHolder holder= (VideoDoingHolder) view.getTag();
            holder.tvPercent.setText(mContext.getResources().getString(R.string.down_video_paush));
            holder.down_xz_rel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    task.pause();
                }
            });
        }
    }

    /**
     * 下载出错
     * @param task
     */
    public void error(final DownloadTask task){
        View view=mConvertViews.get(task.getDownloadInfo().url);
        if(view!=null){
            VideoDoingHolder holder= (VideoDoingHolder) view.getTag();
            holder.tvPercent.setText(mContext.getResources().getString(R.string.down_video_error));
            holder.down_xz_rel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    task.start();
                }
            });
        }
    }

    /**
     * 下载完成
     * @param task
     */
    public void finish(DownloadTask task){
        View view=mConvertViews.get(task.getDownloadInfo().url);
        if(view!=null){
            VideoDoingHolder holder= (VideoDoingHolder) view.getTag();
            holder.tvPercent.setText(mContext.getResources().getString(R.string.down_video_finish));
        }
        notifyDataSetChanged();
    }

    /**
     * 删除
     * @param task
     */
    public void delete(DownloadTask task){
        View view=mConvertViews.get(task.getDownloadInfo().url);
        if(view!=null){

        }
    }
}
