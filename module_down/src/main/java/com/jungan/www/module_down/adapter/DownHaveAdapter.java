package com.jungan.www.module_down.adapter;

import android.content.Context;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baijiayun.download.DownloadTask;
import com.jungan.www.common_down.call.DownVideoCall;
import com.jungan.www.module_down.R;
import com.jungan.www.module_down.call.DownHaveVideoCall;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DownHaveAdapter extends BaseAdapter {
    private List<DownloadTask> downloadTasks;
    private Context mContext;
    private boolean isVist=false;
    private Set<DownloadTask> selectDown;
    private DownHaveVideoCall mCall;
    private boolean isAllSelect=false;

    public void setAllSelect(boolean allSelect) {
        isAllSelect = allSelect;
    }
    public void setmCall(DownHaveVideoCall mCall) {
        this.mCall = mCall;
    }
    public void setVist(boolean vist) {
        isVist = vist;
    }
    public Set<DownloadTask> getSelectDown() {
        return selectDown;
    }

    public DownHaveAdapter(List<DownloadTask> downloadTasks, Context mContext) {
        this.downloadTasks = downloadTasks;
        this.mContext = mContext;
        selectDown=new HashSet<>();
    }
    @Override
    public int getCount() {
        return downloadTasks.size();
    }

    @Override
    public Object getItem(int position) {
        return downloadTasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DownHaveHolder holder=null;
        final DownloadTask downloadTask= (DownloadTask) getItem(position);
        if(convertView==null){
            holder=new DownHaveHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.down_downhave_item,null);
            holder.fileName_tv=convertView.findViewById(R.id.fileName_tv);
            holder.select_rb=convertView.findViewById(R.id.select_rb);
            holder.select_rel=convertView.findViewById(R.id.select_rel);
            convertView.setTag(holder);
        }else {
            holder= (DownHaveHolder) convertView.getTag();
        }
        if(isVist){
            holder.select_rb.setVisibility(View.VISIBLE);
        }else {
            holder.select_rb.setVisibility(View.GONE);
        }
        holder.fileName_tv.setText(downloadTask.getFileName());

        if(isVist){
            if(isAllSelect){
                selectDown.addAll(downloadTasks);
                holder.select_rb.setChecked(true);
                if(mCall==null){
                }else {
                    mCall.selectVideo();
                }
            }else {
                selectDown.clear();
                holder.select_rb.setChecked(false);
                if(mCall==null){
                }else {
                    mCall.selectVideo();
                }
            }
            holder.select_rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        selectDown.add(downloadTask);
                    }else {
                        selectDown.remove(downloadTask);
                    }
                    if(mCall==null)
                        return;
                    mCall.selectVideo();
                }
            });
            final DownHaveHolder finalHolder = holder;
            holder.select_rel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(finalHolder.select_rb.isChecked()){
                        finalHolder.select_rb.setChecked(false);
                        selectDown.remove(downloadTask);
                    }else {
                        finalHolder.select_rb.setChecked(true);
                        selectDown.add(downloadTask);
                    }
                    if(mCall==null)
                        return;
                    mCall.selectVideo();
                }
            });
        }
        return convertView;
    }
    class DownHaveHolder{
            TextView fileName_tv;
            CheckBox select_rb;
            RelativeLayout select_rel;
    }
}
