package com.wb.baselib.typeview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wb.baselib.R;
import com.wb.baselib.adapter.DoubleGridAdapter;
import com.wb.baselib.interfaces.OnFilterDoneListener;
import com.wb.baselib.utils.FilterUrl;

import java.util.List;

/**
 * auther: baiiu
 * time: 16/6/5 05 23:03
 * description:
 */
public class BetterDoubleGridView extends LinearLayout implements View.OnClickListener {
//    private Button bt_confirm;
    RecyclerView recyclerView;
    private List<String> mTopGridData;
    private List<String> mBottomGridList;
    private OnFilterDoneListener mOnFilterDoneListener;
    private TextView qd_tv,cz_tv,textView;
    private String text;


    private DoubleGridAdapter adapter;
    public BetterDoubleGridView(Context context) {
        this(context, null);
    }

    public BetterDoubleGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BetterDoubleGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BetterDoubleGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        setBackgroundColor(Color.WHITE);
        View vie=inflate(context, R.layout.merge_filter_double_grid, this);
        recyclerView=vie.findViewById(R.id.recyclerView);
        qd_tv=vie.findViewById(R.id.qd_tv);
        qd_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterUrl.instance().doubleGridTop = mTopSelectedTextView == null ? "" : (String) mTopSelectedTextView.getTag();
                if (mOnFilterDoneListener != null) {
                    mOnFilterDoneListener.onFilterDone(2, text, "","");
                }
            }
        });
        cz_tv=vie.findViewById(R.id.cz_tv);
        cz_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView==null){
                }else {
                    if (mOnFilterDoneListener != null) {
                        FilterUrl.instance().clear();
                        adapter.setSelectOption(true);
                        adapter.notifyDataSetChanged();
                        mOnFilterDoneListener.onFilterDone(2, "", "","");
                    }
                }
            }
        });

    }


    public BetterDoubleGridView setmTopGridData(List<String> mTopGridData) {
        this.mTopGridData = mTopGridData;
        return this;
    }

    public BetterDoubleGridView setmBottomGridList(List<String> mBottomGridList) {
        this.mBottomGridList = mBottomGridList;
        return this;
    }

    public BetterDoubleGridView build() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override public int getSpanSize(int position) {
                if (position == 0 || position == mTopGridData.size() + 1) {
                    return 4;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter=new DoubleGridAdapter(getContext(), mTopGridData, mBottomGridList, this);
        recyclerView.setAdapter(adapter);
        return this;
    }

    private TextView mTopSelectedTextView;
    private TextView mBottomSelectedTextView;

    @Override
    public void onClick(View v) {

        selectOption(v);

    }

    private void selectOption(View v){
        Log.e("点击了","-----");
        /*adapter.setSelectOption(-1);
        adapter.notifyDataSetChanged();*/
//        FilterUrl.instance().clear();
//        adapter.setSelectOption(true);
//        adapter.notifyDataSetChanged();


        textView = (TextView) v;
         text = (String) textView.getTag();

        if (textView == mTopSelectedTextView) {
            mTopSelectedTextView = null;
            textView.setSelected(false);
        } else if (textView == mBottomSelectedTextView) {
            mBottomSelectedTextView = null;
            textView.setSelected(false);
        } else if (mTopGridData.contains(text)) {
            if (mTopSelectedTextView != null) {
                mTopSelectedTextView.setSelected(false);
            }
            mTopSelectedTextView = textView;
            textView.setSelected(true);
        } else {
            if (mBottomSelectedTextView != null) {
                mBottomSelectedTextView.setSelected(false);
            }
            mBottomSelectedTextView = textView;
            textView.setSelected(true);
        }
        Log.e("mTopSelectedTextView",text);
    }

    public BetterDoubleGridView setOnFilterDoneListener(OnFilterDoneListener listener) {
        mOnFilterDoneListener = listener;
        return this;
    }


}