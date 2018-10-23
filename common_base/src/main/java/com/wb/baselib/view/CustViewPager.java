package com.wb.baselib.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * 1.屏蔽切换的时候需要经过中间页
 */
public class CustViewPager extends ViewPager {
    private boolean enabled=true;//false;//默认不可滑动


    public CustViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        //this.enabled = true;

    }

    //触摸没有反应就可以了
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabled) {
            try {
                return super.onTouchEvent(event);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabled) {
            try {
                return super.onInterceptTouchEvent(event);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    @SuppressLint("UseSparseArrays")
    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            map.put(i, h);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    public Map< Integer, Integer> getMap(){
        return this.map;
    }
}