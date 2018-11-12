package com.wb.baselib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wb.baselib.R;
import com.wb.baselib.bean.FilterChilderType;
import com.wb.baselib.bean.FilterType;
import com.wb.baselib.utils.UIUtil;
import com.wb.baselib.view.FilterCheckedTextView;

import java.util.List;

/**
 * Created by baiiu on 15/12/23.
 * 菜单条目适配器
 */
public abstract class Simple1TextAdapter<T> extends ListBaseAdapter<T> {

    private final LayoutInflater inflater;
    private int postType;
    public Simple1TextAdapter(List<T> list, Context context,int type) {
        super(list, context);
        this.postType=type;
        inflater = LayoutInflater.from(context);
    }

    public static class FilterItemHolder {
        FilterCheckedTextView checkedTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FilterItemHolder holder;
        if (convertView == null) {
            if(postType==1){
                convertView = inflater.inflate(R.layout.lv3_item_filter, parent, false);
            }else {
                convertView = inflater.inflate(R.layout.lv_item_filter, parent, false);
            }

            holder = new FilterItemHolder();
            holder.checkedTextView = (FilterCheckedTextView) convertView;
            holder.checkedTextView.setPadding(0, UIUtil.dp(context, 15), 0, UIUtil.dp(context, 15));
            initCheckedTextView(holder.checkedTextView);

            convertView.setTag(holder);
        } else {
            holder = (FilterItemHolder) convertView.getTag();
        }

        T t = list.get(position);
        holder.checkedTextView.setText(provideText(t).desc);

        return convertView;
    }

    public abstract FilterType provideText(T t);

    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
    }


}
