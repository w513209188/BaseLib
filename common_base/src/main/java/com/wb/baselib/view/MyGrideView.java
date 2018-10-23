package com.wb.baselib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
public class MyGrideView extends GridView {
    public MyGrideView(Context context) {
        super(context);
    }
    public MyGrideView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyGrideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

