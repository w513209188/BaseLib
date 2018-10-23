package com.wb.baselib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wb.baselib.R;
import com.wb.baselib.utils.UIUtil;
import com.wb.baselib.view.FilterCheckedTextView;

/**
 * auther: baiiu
 * time: 16/6/5 05 23:30
 * description:
 */
public class TitleViewHolder extends RecyclerView.ViewHolder {

    private final TextView textView;
    public TitleViewHolder(Context mContext, ViewGroup parent) {
        super(UIUtil.infalte(mContext, R.layout.holder_title, parent));
        textView=itemView.findViewById( R.id.tv_item);
    }


    public void bind(String s) {
        textView.setText(s);
//        ((TextView) itemView).setText(s);
    }
}