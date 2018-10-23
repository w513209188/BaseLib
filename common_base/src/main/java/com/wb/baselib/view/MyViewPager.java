package com.wb.baselib.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 1.屏蔽切换的时候需要经过中间页
 */
public class MyViewPager extends ViewPager {
    //是否可以进行滑动
    private boolean isSlide = false;
    /**
     * 是否可以滑动
     */
    private boolean isCanScroll = false;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /**
     * 解决切换需要经过中间页
     */
    @Override
    public void setCurrentItem(int item) {
        //super.setCurrentItem(item);源码
        super.setCurrentItem(item,false);//false表示切换的时候,不经过两个页面的中间页
    }

    /**
     * 是否允许滑动
     * @param slide
     */
    public void setSlide(boolean slide) {
        isSlide = slide;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSlide;
    }
}