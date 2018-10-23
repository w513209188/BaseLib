package com.wb.baselib.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.wb.baselib.R;
import com.wb.baselib.adapter.ViewPageAdapter;
import com.wb.baselib.base.fragment.BaseFragment;
import com.wb.baselib.jptabbar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class BottomBarView extends LinearLayout {
    private View mView;
    private JPTabBar bottomNavigationBar;
    private List<BaseFragment> mFragments;
    private MyViewPager mViewPager;
    public BottomBarView(Context context) {
        this(context,null);
    }

    public BottomBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BottomBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView(){
        mView= LayoutInflater.from(getContext()).inflate(R.layout.layout_bottombar_view,this);
        mViewPager=mView.findViewById(R.id.main_vp);
        bottomNavigationBar=mView.findViewById(R.id.tabbar);
    }

    /**
     * 设置底部导航将要绑定的fragment
     * @param fragment
     */
    public BottomBarView setFragments(List<BaseFragment> fragment){
        mFragments=new ArrayList<>();
        mFragments.addAll(fragment);

        return this;
    }

    /***
     * 设置底部导航未选择图标
     * @param iconNoIcon
     */
    public BottomBarView setBottomNoIcon(int... iconNoIcon){
        bottomNavigationBar.setNormalIcons(iconNoIcon);
        return this;
    }
    /***
     * 设置底部导航未选择字体颜色
     * @param iconNoIcon
     */
    public BottomBarView setBottomTextNoColor(int iconNoIcon){
        bottomNavigationBar.setNormalColor(iconNoIcon);
        return this;
    }
    /***
     * 设置底部导航选择字体颜色
     * @param iconNoIcon
     */
    public BottomBarView setBottomTextSelectColor(@ColorInt int iconNoIcon){
        bottomNavigationBar.setSelectedColor(iconNoIcon);
        return this;
    }
    /**
     * 设置底部导航选择图标
     * @param iconSelectIcon
     */
    public BottomBarView setBottomSelectIcon(int... iconSelectIcon){
        bottomNavigationBar.setSelectedIcons(iconSelectIcon);
        return this;
    }

    /**
     * 设置底部导航标题
     * @param titls
     */
    public BottomBarView setBottomTitles( String... titls){
        bottomNavigationBar.setTitles(titls);
        return this;
    }

    /**
     * 绑定数据
     * @param fragmentManager
     */
    public void bindFrament(FragmentManager fragmentManager){
        bottomNavigationBar.generate();
        mViewPager.setAdapter(new ViewPageAdapter(fragmentManager, mFragments));
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(mFragments.size());
        bottomNavigationBar.setContainer(mViewPager);
    }

    /**
     * 是否显示小红点
     * @param postion 在第几个选项卡上显示小红点
     * @param text 将要显示的文本
     */
    public void setBadge(int postion,String text){
        bottomNavigationBar.showBadge(postion,text);
    }

    /**
     * 隐藏小红点
     * @param postion 隐藏第几个小红点
     */
    public void hindBadge(int postion){
        bottomNavigationBar.hideBadge(postion);
    }

    /**
     * 设置底部导航的监听事件
     * @param onTabSelectListener
     */
    public void setBottomLister(OnTabSelectListener onTabSelectListener){
        bottomNavigationBar.setTabListener(onTabSelectListener);
    }

    /**
     * 重新设置某一个底部导航的文字
     * @param potsion
     * @param txt
     */
    public void setTitle(int potsion,String txt){
        bottomNavigationBar.setTitle(potsion,txt);
    }
    public void setVp(int postion){
        mViewPager.setCurrentItem(postion);
    }
}
