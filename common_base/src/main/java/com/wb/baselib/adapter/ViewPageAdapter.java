package com.wb.baselib.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wb.baselib.base.fragment.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */

public class ViewPageAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> fragments;
    public ViewPageAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }
    @Override
    public int getItemPosition(Object object) {
        return android.support.v4.view.PagerAdapter.POSITION_NONE;
    }
}
