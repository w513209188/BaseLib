package com.jungan.www.module_down.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jungan.www.module_down.R;
import com.jungan.www.module_down.bean.DownHaveBean;
import com.jungan.www.module_down.bean.DownManagerBean;
import com.jungan.www.module_down.config.DownManagerTypeConfig;
import com.jungan.www.module_down.config.ToActicvityConfig;
import com.jungan.www.module_down.ui.DownDoingVideoActivity;
import com.jungan.www.module_down.ui.DownHaveVideoActivity;
import com.wb.baselib.utils.ToActivityUtil;
import com.wb.baselib.view.MyListView;

public class DownManagerAdapter extends BaseAdapter {
    private Context mContext;
    private DownManagerBean downManagerBean;

    public DownManagerAdapter(Context mContext, DownManagerBean downManagerBean) {
        this.mContext = mContext;
        this.downManagerBean = downManagerBean;
    }

    @Override
    public int getCount() {
        if(downManagerBean.isDoing()&&downManagerBean.isHave()){
            return DownManagerTypeConfig.COUT;
        }else {
            return DownManagerTypeConfig.COUTDO;
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)
            return DownManagerTypeConfig.DOINGDOWN;
        return DownManagerTypeConfig.HAVEDOWN;
    }

    @Override
    public int getViewTypeCount() {
        if(downManagerBean.isDoing()&&downManagerBean.isHave()){
            return DownManagerTypeConfig.COUT;
        }else {
            return DownManagerTypeConfig.COUTDO;
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(downManagerBean.isDoing()&&!downManagerBean.isHave()){
            return setDoingView(position,convertView);
        }else if (!downManagerBean.isDoing()&&downManagerBean.isHave()){
            return setHaveView(position,convertView);
        }else {
            if(getItemViewType(position)==DownManagerTypeConfig.DOINGDOWN){
                return setDoingView(position,convertView);
            }else if(getItemViewType(position)==DownManagerTypeConfig.HAVEDOWN){
                return setHaveView(position,convertView);
            }
        }
        return null;
    }
    public View setDoingView(int postion,View convertView){
        DoingDownHoder hoder=null;
        if(convertView==null){
            hoder=new DoingDownHoder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.down_doingdown_view,null);
            hoder.doing_tv=convertView.findViewById(R.id.doing_tv);
            hoder.doing_ll=convertView.findViewById(R.id.doing_ll);
            convertView.setTag(hoder);
        }else {
            hoder= (DoingDownHoder) convertView.getTag();
        }
        hoder.doing_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivityUtil.newInsance().toNextActivity(mContext, DownDoingVideoActivity.class);
            }
        });
        hoder.doing_tv.setText("正在下载");
        return convertView;
    }
    class DoingDownHoder{
        TextView doing_tv;
        LinearLayout doing_ll;
    }
    public View setHaveView(int postion,View convertView){
        HaveDownHoder hoder=null;
        if(convertView==null){
            hoder=new HaveDownHoder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.down_havedown_view,null);
            hoder.down_have_lv=convertView.findViewById(R.id.down_have_lv);
            convertView.setTag(hoder);
        }else {
            hoder= (HaveDownHoder) convertView.getTag();
        }
        hoder.down_have_lv.setAdapter(new DownHaveItemAdapter(downManagerBean.getDownHaveBeans(),mContext));
        hoder.down_have_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DownHaveBean downHaveBean= (DownHaveBean) parent.getItemAtPosition(position);
                ToActivityUtil.newInsance().toNextActivity(mContext, DownHaveVideoActivity.class
                        ,new String[][]{{ToActicvityConfig.OCCNAME_KEY,downHaveBean.getOccName()}
                ,{ToActicvityConfig.SEACHNAME_KEY,downHaveBean.getSeachName()}
                ,{ToActicvityConfig.COURSENAME_KEY,downHaveBean.getCourseName()}});
            }
        });
        return convertView;
    }
    class HaveDownHoder{
        MyListView down_have_lv;
    }
}
