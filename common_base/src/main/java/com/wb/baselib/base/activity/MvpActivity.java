package com.wb.baselib.base.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jaeger.library.StatusBarUtil;
import com.wb.baselib.R;
import com.wb.baselib.base.mvp.BasePreaenter;

/**
 * Created by desin on 2017/2/14.
 */

public abstract class MvpActivity<P extends BasePreaenter> extends BaseActivity {
    protected P mPresenter;
    protected abstract P onCreatePresenter();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(onCreatePresenter()!=null){
            mPresenter=onCreatePresenter();
        }
        initView(savedInstanceState);
        setListener();
        processLogic(savedInstanceState);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.unSubscribe();
        }
    }
}
