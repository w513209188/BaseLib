package com.wb.baselib.base.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wb.baselib.R;
import com.wb.baselib.app.AppUtils;
import com.wb.baselib.base.fragment.BaseFragment;
import com.wb.baselib.http.HttpConfig;
import com.wb.baselib.view.ViewManager;

import java.util.HashMap;
import java.util.Map;

//import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
@Keep
public abstract class BaseActivity extends RxAppCompatActivity implements View.OnClickListener {
//    protected BGASwipeBackHelper mSwipeBackHelper;
    private Dialog mDiaLog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 在 super.onCreate(savedInstanceState) 之前调用该方法
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        initSwipeBackFinish();
        if(!isAllImage()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setStatusBarColor(this.getResources().getColor(R.color.statusbar_color),0);
            }else {
                setStatusBarColor(this.getResources().getColor(R.color.statusbar_color_K),0);
            }
        }else {
            StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
        }
        setStatusBarTextColor();
        super.onCreate(savedInstanceState);
        ViewManager.getInstance().addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("ExitApp");
        this.registerReceiver(this.broadcastReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewManager.getInstance().finishActivity(this);
        try {
            this.unregisterReceiver(this.broadcastReceiver);
        } catch (Exception e) {
        }

    }

    protected BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

//    /**
//     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
//     */
//    private void initSwipeBackFinish() {
//        mSwipeBackHelper = new BGASwipeBackHelper(this, this);
//        // 下面几项可以不配置，这里只是为了讲述接口用法。
//        // 如果需要启用微信滑动返回样式，必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this)
//        // 设置滑动返回是否可用。默认值为 true
//        mSwipeBackHelper.setSwipeBackEnable(false);
//        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
//        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
//        // 设置是否是微信滑动返回样式。默认值为 true
//        mSwipeBackHelper.setIsWeChatStyle(false);
//        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
//        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
//        // 设置是否显示滑动返回的阴影效果。默认值为 true
//        mSwipeBackHelper.setIsNeedShowShadow(false);
//        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
//        mSwipeBackHelper.setIsShadowAlphaGradient(false);
//    }

//    /**
//     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
//     *
//     * @return
//     */
//    @Override
//    public boolean isSupportSwipeBack() {
//        return true;
//    }
//
//    /**
//     * 正在滑动返回
//     *
//     * @param slideOffset 从 0 到 1
//     */
//    @Override
//    public void onSwipeBackLayoutSlide(float slideOffset) {
//    }
//
//    /**
//     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
//     */
//    @Override
//    public void onSwipeBackLayoutCancel() {
//    }

//    /**
//     * 滑动返回执行完毕，销毁当前 Activity
//     */
//    @Override
//    public void onSwipeBackLayoutExecuted() {
//        mSwipeBackHelper.swipeBackward();
//    }

//    @Override
//    public void onBackPressed() {
//        mSwipeBackHelper.backward();
//    }

    /**
     * 设置状态栏颜色
     *
     * @param color
     */
    protected void setStatusBarColor(@ColorInt int color) {
        setStatusBarColor(color, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置状态栏颜色
     *
     * @param color
     * @param statusBarAlpha 透明度
     */
    public void setStatusBarColor(@ColorInt int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setColorForSwipeBack(this, color, statusBarAlpha);
//        setStatusBarTextColor();
    }

    /**
     * 设置状态栏字体为黑色
     */
    public void setStatusBarTextColor(){
//        int type=UiStatusBarUtil.StatusBarLightMode(this);
//        Log.e("获取到的类型",type+"----");
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化布局以及View控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 给View控件添加事件监听器
     */
    protected abstract void setListener();

    /**
     * 处理业务逻辑，状态恢复等操作
     *
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    /**
     * 需要处理点击事件时，重写该方法
     *
     * @param v
     */
    public void onClick(View v) {
    }

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }

    /**
     * Md风格的对话框
     *
     * @param title
     * @param msg
     */
    public void showMdDialog(String title, String msg, String bt1, String bt2, MyDialogListener bul) {
        StyledDialog.buildMdAlert(title, msg, bul)
                .setBtnText(bt1, bt2)
                .show();
    }

    /**
     * 展示短的toast
     * @param msg
     */
    public void showShortToast(String msg) {
        Toast.makeText(this, msg, android.widget.Toast.LENGTH_SHORT).show();
    }

    /**
     * 展示长的toast
     * @param msg
     */
    public void showLongToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 关闭打开的activity
     */
    public void AppAllExit() {
        Map<String,String> map=new HashMap<>();
        HttpConfig.newInstance().setmMapHeader(map);
        Intent intent = new Intent();
        intent.setAction("ExitApp");
        this.sendBroadcast(intent);
        super.finish();
    }

    /**
     * 显示加载对话框
     * @param msg
     */
    public void showLoadDiaLog(String msg) {
        mDiaLog = StyledDialog.buildLoading(msg).show();
    }

    /**
     * 隐藏加载对话框
     */
    public void hidLoadDiaLog() {
        if (mDiaLog == null)
            return;
        if (mDiaLog.isShowing()) {
            mDiaLog.dismiss();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 添加fragment
     *
     * @param fragment
     * @param frameId
     */
    public void addFragment(BaseFragment fragment, @IdRes int frameId) {
        AppUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .add(frameId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();

    }


    /**
     * 替换fragment
     *
     * @param fragment
     * @param frameId
     */
    public void replaceFragment(BaseFragment fragment, @IdRes int frameId) {
        AppUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .replace(frameId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();

    }


    /**
     * 隐藏fragment
     *
     * @param fragment
     */
    public void hideFragment(BaseFragment fragment) {
        AppUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .hide(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 显示fragment
     *
     * @param fragment
     */
    public void showFragment(BaseFragment fragment) {
        AppUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .show(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 移除fragment
     *
     * @param fragment
     */
    public void removeFragment(BaseFragment fragment) {
        AppUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .remove(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 弹出栈顶部的Fragment
     */
    public void popFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
    public boolean isAllImage(){
        return false;
    }
}
