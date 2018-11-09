package com.wb.baselib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.wb.baselib.R;
import com.wb.baselib.utils.UIUtil;
import com.wb.baselib.view.FilterCheckedTextView;
/**
 * auther: baiiu
 * time: 16/6/5 05 23:35
 * description:
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    private final FilterCheckedTextView textView;
    private View.OnClickListener mListener;


    public ItemViewHolder(Context mContext, ViewGroup parent, View.OnClickListener mListener) {
        super(UIUtil.infalte(mContext, R.layout.holder_item, parent));
        textView=itemView.findViewById( R.id.tv_item);
//        textView = parent.findViewById(itemView, R.id.tv_item);
        this.mListener = mListener;
    }


    public void bind(String s,boolean isReset,int post) {
        String ids="0";
        if(s.equals("全部")){
            ids="0";
        }else if(s.equals("直播课")){
            ids="1";
        }else if(s.equals("录播课")){
            ids="2";
        }else if(s.equals("音频课")){
            ids="3";
        }
        if(post==1){
            textView.setSelected(true);
        }
        textView.setText(s);
        textView.setTag(ids);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView.getId() == v.getId()) {
                    textView.setSelected(true);
                }else {
                    textView.setSelected(false);
                }
            }
        });
        textView.setOnClickListener(mListener);
    }
}