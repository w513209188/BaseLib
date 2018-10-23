package com.wb.baselib.base.fragment;

import android.os.Bundle;

import com.wb.baselib.base.mvp.BasePreaenter;

/**
 * Created by desin on 2017/1/12.
 */

public abstract class MvpFragment<P extends BasePreaenter> extends LazyFragment {
    protected P mPresenter;
    protected abstract P onCreatePresenter();
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
//        initView();
//        setListener();
    }
    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }
}
