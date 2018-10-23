package com.jungan.www.module_down.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jungan.www.module_down.R;
import com.jungan.www.module_down.bean.DownHaveBean;

import java.util.List;

public class DownHaveItemAdapter extends BaseAdapter {
    private List<DownHaveBean> downHaveBeanList;
    private Context mContext;

    public DownHaveItemAdapter(List<DownHaveBean> downHaveBeanList, Context mContext) {
        this.downHaveBeanList = downHaveBeanList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return downHaveBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return downHaveBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DownHaveBean haveBean= (DownHaveBean) getItem(position);
        DownHaveItemHolder haveItemHolder=null;
        if(convertView==null){
            haveItemHolder=new DownHaveItemHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.down_havedown_item,null);
            haveItemHolder.count_tv=convertView.findViewById(R.id.count_tv);
            haveItemHolder.seach_tv=convertView.findViewById(R.id.seach_tv);
            haveItemHolder.course_tv=convertView.findViewById(R.id.course_tv);
            haveItemHolder.year_tv=convertView.findViewById(R.id.year_tv);
            convertView.setTag(haveItemHolder);
        }else {
            haveItemHolder= (DownHaveItemHolder) convertView.getTag();
        }
        haveItemHolder.count_tv.setText(haveBean.getVideoCont()+"个视频");
        haveItemHolder.course_tv.setText(haveBean.getCourseName());
        haveItemHolder.seach_tv.setText(haveBean.getSeachName());
        haveItemHolder.year_tv.setText(haveBean.getOccName());
        return convertView;
    }
    class DownHaveItemHolder{
            TextView count_tv,seach_tv,course_tv,year_tv;
    }
}
