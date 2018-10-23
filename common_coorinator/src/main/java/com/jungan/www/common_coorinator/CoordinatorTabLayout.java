package com.jungan.www.common_coorinator;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jungan.www.common_coorinator.adapter.IndexItemTeacherAdaper;
import com.jungan.www.common_coorinator.bean.TeacherBean;
import com.jungan.www.common_coorinator.listener.LoadHeaderImagesListener;
import com.jungan.www.common_coorinator.listener.OnTabSelectedListener;
import com.jungan.www.common_coorinator.utils.SystemView;
import com.jungan.www.common_coorinator.view.HorizontalListView;

import java.util.List;

/**
 * @author hugeterry(http : / / hugeterry.cn)
 */

public class CoordinatorTabLayout extends CoordinatorLayout {
    private int[] mImageArray, mColorArray;

    private Context mContext;
    private Toolbar mToolbar;
    private ActionBar mActionbar;
    private TabLayout mTabLayout;
    private ImageView mImageView;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private LoadHeaderImagesListener mLoadHeaderImagesListener;
    private OnTabSelectedListener mOnTabSelectedListener;

    private TextView course_name_tv, course_start_tv, course_end_tv, course_teachername_tv, course_bmname_tv, course_ybm_tv, course_sy_tv, course1_name_tv, course1_ll_tv, course1_zjr_tv, course1_jj_tv;
    private LinearLayout zx_view, xx_view;

    public CoordinatorTabLayout(Context context) {
        super(context);
        mContext = context;
    }

    public CoordinatorTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        if (!isInEditMode()) {
            initView(context);
            initWidget(context, attrs);
        }
    }

    public CoordinatorTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        if (!isInEditMode()) {
            initView(context);
            initWidget(context, attrs);
        }
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_coordinatortablayout, this, true);
        initToolbar();
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbarlayout);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mImageView = (ImageView) findViewById(R.id.imageview);
        course_name_tv = (TextView) findViewById(R.id.course_name_tv);
        course_start_tv = (TextView) findViewById(R.id.course_start_tv);
        course_end_tv = (TextView) findViewById(R.id.course_end_tv);
        course_teachername_tv = (TextView) findViewById(R.id.course_teachername_tv);
        course_bmname_tv = (TextView) findViewById(R.id.course_bmname_tv);
        course_ybm_tv = (TextView) findViewById(R.id.course_ybm_tv);
        course_sy_tv = (TextView) findViewById(R.id.course_sy_tv);
        course1_name_tv = (TextView) findViewById(R.id.course1_name_tv);
        course1_ll_tv = (TextView) findViewById(R.id.course1_ll_tv);
        course1_zjr_tv = (TextView) findViewById(R.id.course1_zjr_tv);
        course1_jj_tv = (TextView) findViewById(R.id.course1_jj_tv);
        zx_view = (LinearLayout) findViewById(R.id.zx_view);
        xx_view = (LinearLayout) findViewById(R.id.xx_view);

    }

    private void initWidget(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs
                , R.styleable.CoordinatorTabLayout);

        TypedValue typedValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        int contentScrimColor = typedArray.getColor(
                R.styleable.CoordinatorTabLayout_contentScrim, typedValue.data);
        mCollapsingToolbarLayout.setContentScrimColor(contentScrimColor);

        int tabIndicatorColor = typedArray.getColor(R.styleable.CoordinatorTabLayout_tabIndicatorColor, Color.WHITE);
        mTabLayout.setSelectedTabIndicatorColor(tabIndicatorColor);

        int tabIndicatorBgColor = typedArray.getColor(R.styleable.CoordinatorTabLayout_tabIndicatorBackBgColor, Color.WHITE);
        mTabLayout.setBackgroundColor(tabIndicatorBgColor);

        int tabTextColor = typedArray.getColor(R.styleable.CoordinatorTabLayout_tabTextColor, Color.BLACK);
        int tabSelectTextColor = typedArray.getColor(R.styleable.CoordinatorTabLayout_tabSelectTextColor, Color.BLACK);
        mTabLayout.setTabTextColors(tabTextColor, tabSelectTextColor);
        typedArray.recycle();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        ((AppCompatActivity) mContext).setSupportActionBar(mToolbar);
        mActionbar = ((AppCompatActivity) mContext).getSupportActionBar();
    }
    public CoordinatorTabLayout setClassFly(boolean istask){
        if(istask){
            zx_view.setVisibility(GONE);
            xx_view.setVisibility(VISIBLE);
        }else {
            zx_view.setVisibility(VISIBLE);
            xx_view.setVisibility(GONE);
        }
        return this;
    }
    /**
     * 设置Toolbar标题
     *
     * @param title 标题
     * @return CoordinatorTabLayout
     */
    public CoordinatorTabLayout setTitle(String title) {
        if (mActionbar != null) {
            mActionbar.setTitle(title);
        }
        return this;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public CoordinatorTabLayout setCourceTitle(String title) {
        if (course_name_tv != null) {
            course_name_tv.setText(title);
        }
        return this;
    }

    /**
     * 设置开课时间
     *
     * @param title
     * @return
     */
    public CoordinatorTabLayout setStartCourseTime(String title) {
        if (course_start_tv != null) {
            course_start_tv.setText("开课时间：" + title);
        }
        return this;
    }

    /**
     * 设置开课地址
     *
     * @param title
     * @return
     */
    public CoordinatorTabLayout setStartCourseAddress(String title) {
        if (course_end_tv != null) {
            course_end_tv.setText("开课地址：" + title);
        }
        return this;
    }

    /**
     * 设置授课老师
     *
     * @param title
     * @return
     */
    public CoordinatorTabLayout settalkTeacher(String title) {
        if (course_teachername_tv != null) {
            course_teachername_tv.setText("授课老师：" + title);
        }
        return this;
    }

    /**
     * 培训部门
     *
     * @param title
     * @return
     */
    public CoordinatorTabLayout setPxBm(String title) {
        if (course_bmname_tv != null) {
            if (title.equals("")) {
                course_bmname_tv.setText("培训部门：暂无部门");
            }else {
                course_bmname_tv.setText("培训部门：" + title);
            }
        }
        return this;
    }

    /**
     * 已报名
     *
     * @param title
     * @return
     */
    public CoordinatorTabLayout sethaveBm(String title) {
        if (course_ybm_tv != null) {
            course_ybm_tv.setText("已报名：" + title);
        }
        return this;
    }

    /**
     * 报名剩余量
     *
     * @param title
     * @return
     */
    public CoordinatorTabLayout setsyl(String title) {
        if (course_sy_tv != null) {
            course_sy_tv.setText( title);
        }
        return this;
    }

    /**
     * 第二级课程名称
     *
     * @param title
     * @return
     */
    public CoordinatorTabLayout setTwoCourseName(String title) {
        if (course1_name_tv != null) {
            course1_name_tv.setText(title);
        }
        return this;
    }

    /**
     * 第二级浏览量
     *
     * @param title
     * @return
     */
    public CoordinatorTabLayout setTwollNum(String title) {
        if (course1_ll_tv != null) {
            course1_ll_tv.setText(title);
        }
        return this;
    }

    /**
     * 第二级主讲人
     *
     * @param title
     * @return
     */
    public CoordinatorTabLayout setTwoTeacherName(String title) {
        if (course1_zjr_tv != null) {
            course1_zjr_tv.setText(title);
        }
        return this;
    }

    /**
     * 第二级简介
     *
     * @param title
     * @return
     */
    public CoordinatorTabLayout setTwoDes(String title) {
        if (course1_jj_tv != null) {
            course1_jj_tv.setText(title);
        }
        return this;
    }


    /**
     * 设置Toolbar显示返回按钮及标题
     *
     * @param canBack 是否返回
     * @return CoordinatorTabLayout
     */
    public CoordinatorTabLayout setBackEnable(Boolean canBack) {
        if (canBack && mActionbar != null) {
            mActionbar.setDisplayHomeAsUpEnabled(true);
            mActionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        }
        return this;
    }

    /**
     * @param canBack
     * @return
     */
    public CoordinatorTabLayout setToolInfo(Boolean canBack) {
        if (canBack && mActionbar != null) {
            mActionbar.setDisplayHomeAsUpEnabled(true);
            mActionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        }
        return this;
    }

    /**
     * 设置每个tab对应的头部图片
     *
     * @param imageArray 图片数组
     * @return CoordinatorTabLayout
     */
    public CoordinatorTabLayout setImageArray(@NonNull int[] imageArray) {
        mImageArray = imageArray;
        return this;
    }

    /**
     * 设置每个tab对应的头部照片和ContentScrimColor
     *
     * @param imageArray 图片数组
     * @param colorArray ContentScrimColor数组
     * @return CoordinatorTabLayout
     */
    public CoordinatorTabLayout setImageArray(@NonNull int[] imageArray, @NonNull int[] colorArray) {
        mImageArray = imageArray;
        mColorArray = colorArray;
        return this;
    }

    /**
     * 设置每个tab对应的ContentScrimColor
     *
     * @param colorArray 图片数组
     * @return CoordinatorTabLayout
     */
    public CoordinatorTabLayout setContentScrimColorArray(@NonNull int[] colorArray) {
        mColorArray = colorArray;
        return this;
    }

    private void setupTabLayout() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                mImageView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_dismiss));
//                if (mLoadHeaderImagesListener == null) {
//                    if (mImageArray != null) {
//                        mImageView.setImageResource(mImageArray[tab.getPosition()]);
//                    }
//                } else {
//                    mLoadHeaderImagesListener.loadHeaderImages(mImageView, tab);
//                }
//                if (mColorArray != null) {
//                    mCollapsingToolbarLayout.setContentScrimColor(
//                            ContextCompat.getColor(
//                                    mContext, mColorArray[tab.getPosition()]));
//                }
////                mImageView.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_show));
//
//                if (mOnTabSelectedListener != null) {
//                    mOnTabSelectedListener.onTabSelected(tab);
//                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (mOnTabSelectedListener != null) {
                    mOnTabSelectedListener.onTabUnselected(tab);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (mOnTabSelectedListener != null) {
                    mOnTabSelectedListener.onTabReselected(tab);
                }
            }
        });
    }

    /**
     * 设置TabLayout TabMode
     *
     * @param mode
     * @return CoordinatorTabLayout
     */
    public CoordinatorTabLayout setTabMode(@TabLayout.Mode int mode) {
        mTabLayout.setTabMode(mode);
        return this;
    }

    /**
     * 设置与该组件搭配的ViewPager
     *
     * @param viewPager 与TabLayout结合的ViewPager
     * @return CoordinatorTabLayout
     */
    public CoordinatorTabLayout setupWithViewPager(ViewPager viewPager) {
        setupTabLayout();
        mTabLayout.setupWithViewPager(viewPager);
        return this;
    }

    /**
     * 获取该组件中的ActionBar
     */
    public ActionBar getActionBar() {
        return mActionbar;
    }

    /**
     * 获取该组件中的TabLayout
     */
    public TabLayout getTabLayout() {
        return mTabLayout;
    }

    /**
     * 获取该组件中的ImageView
     */
    public ImageView getImageView() {
        return mImageView;
    }

    /**
     * 设置LoadHeaderImagesListener
     *
     * @param loadHeaderImagesListener 设置LoadHeaderImagesListener
     * @return CoordinatorTabLayout
     */
    public CoordinatorTabLayout setLoadHeaderImagesListener(LoadHeaderImagesListener loadHeaderImagesListener) {
        mLoadHeaderImagesListener = loadHeaderImagesListener;
        return this;
    }

    /**
     * 设置onTabSelectedListener
     *
     * @param onTabSelectedListener 设置onTabSelectedListener
     * @return CoordinatorTabLayout
     */
    public CoordinatorTabLayout addOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        mOnTabSelectedListener = onTabSelectedListener;
        return this;
    }

    /**
     * 设置透明状态栏
     *
     * @param activity 当前展示的activity
     * @return CoordinatorTabLayout
     */
    public CoordinatorTabLayout setTranslucentStatusBar(@NonNull Activity activity) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return this;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            activity.getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow()
                    .setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (mToolbar != null) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mToolbar.getLayoutParams();
            layoutParams.setMargins(
                    layoutParams.leftMargin,
                    layoutParams.topMargin + SystemView.getStatusBarHeight(activity),
                    layoutParams.rightMargin,
                    layoutParams.bottomMargin);
        }

        return this;
    }

    /**
     * 设置沉浸式
     *
     * @param activity 当前展示的activity
     * @return CoordinatorTabLayout
     */
    public CoordinatorTabLayout setTranslucentNavigationBar(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return this;
        } else {
            mToolbar.setPadding(0, SystemView.getStatusBarHeight(activity) >> 1, 0, 0);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        return this;
    }

}