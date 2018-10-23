package com.wb.baselib.base.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wb.baselib.app.AppUtils;
import com.wb.baselib.base.activity.BaseActivity;

import java.lang.reflect.Field;

import javax.annotation.Nonnull;

public abstract class BaseFragment extends RxFragment implements View.OnClickListener {
    protected LayoutInflater inflater;
    private View contentView;
    private Context context;
    private ViewGroup container;
    private Dialog mDiaLog;
    protected BaseActivity mActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }
    /**
     * 获取宿主Activity
     *
     * @return BaseActivity
     */
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }
    /**
     * 添加fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void addFragment(BaseFragment fragment, @IdRes int frameId) {
        AppUtils.checkNotNull(fragment);
        getHoldingActivity().addFragment(fragment, frameId);

    }
    /**
     * 替换fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void replaceFragment(BaseFragment fragment, @IdRes int frameId) {
        AppUtils.checkNotNull(fragment);
        getHoldingActivity().replaceFragment(fragment, frameId);
    }
    /**
     * 隐藏fragment
     *
     * @param fragment
     */
    protected void hideFragment(BaseFragment fragment) {
        AppUtils.checkNotNull(fragment);
        getHoldingActivity().hideFragment(fragment);
    }
    /**
     * 显示fragment
     *
     * @param fragment
     */
    protected void showFragment(BaseFragment fragment) {
        AppUtils.checkNotNull(fragment);
        getHoldingActivity().showFragment(fragment);
    }
    /**
     * 移除Fragment
     *
     * @param fragment
     */
    protected void removeFragment(BaseFragment fragment) {
        AppUtils.checkNotNull(fragment);
        getHoldingActivity().removeFragment(fragment);

    }
    /**
     * 弹出栈顶部的Fragment
     */
    protected void popFragment() {
        getHoldingActivity().popFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        onCreateView(savedInstanceState);
        if (contentView == null) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        return contentView;
    }

    protected void onCreateView(Bundle savedInstanceState) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        contentView = null;
        container = null;
        inflater = null;
    }

    public Context getApplicationContext() {
        return context;
    }

    public void setContentView(int layoutResID) {
        setContentView((ViewGroup) inflater.inflate(layoutResID, container, false));
    }

    public void setContentView(View view) {
        contentView = view;
    }

    public View getContentView() {
        return contentView;
    }

    public View findViewById(int id) {
        if (contentView != null)
            return contentView.findViewById(id);
        return null;
    }

    // http://stackoverflow.com/questions/15207305/getting-the-error-java-lang-illegalstateexception-activity-has-been-destroyed
    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void initView() {
    }

    protected void setListener() {

    }
    @Override
    public void onClick(View view) {
    }
    /**
     * 展示短的toast
     * @param msg
     */
    public void showShortToast(String msg) {
        Toast.makeText(getActivity(), msg, android.widget.Toast.LENGTH_SHORT).show();
    }

    /**
     * 展示长的toast
     * @param msg
     */
    public void showLongToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
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
     * 展示md风格的对话框
     * @param title
     * @param msg
     * @param bt1
     * @param bt2
     * @param bul
     */
    public void showMdDialog(String title, String msg, String bt1, String bt2, MyDialogListener bul){
        StyledDialog.buildMdAlert(title, msg, bul)
                .setBtnText(bt1, bt2)
                .show();
    }
    /**
     * 显示加载对话框
     * @param msg
     */
    public void showLoadDiaLog(String msg){
        mDiaLog=StyledDialog.buildLoading(msg).show();
    }
    /**
     * 隐藏加载对话框
     */
    public void hidLoadDiaLog(){
        if(mDiaLog==null)
            return;
        mDiaLog.dismiss();
    }

}
