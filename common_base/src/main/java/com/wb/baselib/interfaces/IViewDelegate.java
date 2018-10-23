package com.wb.baselib.interfaces;
import android.support.annotation.Keep;
import android.view.View;

import com.wb.baselib.base.fragment.BaseFragment;
@Keep
public interface IViewDelegate {

    BaseFragment getFragment(String name);
    View getView(String name);

}
