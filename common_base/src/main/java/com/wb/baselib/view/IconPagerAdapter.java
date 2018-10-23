package com.wb.baselib.view;

public interface IconPagerAdapter {
    /**
     * Get icon representing the page at {@code index} in the com.jungan.www.module_public.adapter.
     */
    int getIconResId(int index);

    // From PagerAdapter
    int getCount();
}
