package com.wb.baselib.utils;

import android.content.Context;
import android.support.annotation.ColorRes;

import com.wangbo.smartrefresh.header.WaterDropHeader;
import com.wangbo.smartrefresh.layout.SmartRefreshLayout;
import com.wangbo.smartrefresh.layout.api.RefreshFooter;
import com.wangbo.smartrefresh.layout.api.RefreshHeader;
import com.wangbo.smartrefresh.layout.footer.ClassicsFooter;
import com.wb.baselib.R;

/**
 * Created by Administrator on 2018/4/2.
 */

public class RefreshUtils {
    private static  RefreshUtils refreshUtils;
    private static SmartRefreshLayout smartRefreshLayout;
    private static Context mContext;
    public static RefreshUtils getInstance(SmartRefreshLayout refreshLayout, Context context){
        smartRefreshLayout=refreshLayout;
        mContext=context;
        if(refreshUtils==null){
            refreshUtils=new RefreshUtils();
        }
        return refreshUtils;
    }




    /**
     * 默认样式
     */
    public void defaultRefreSh(){
        smartRefreshLayout.setRefreshHeader(new WaterDropHeader(mContext));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext).setDrawableSize(20));
//        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(mContext).setSpinnerStyle(SpinnerStyle.Scale));
        smartRefreshLayout.setEnableFooterTranslationContent(true);
        smartRefreshLayout.setEnableHeaderTranslationContent(true);
        smartRefreshLayout.setPrimaryColorsId(R.color.statusbar_color, android.R.color.white);
    }

    /**
     * 设置刷新头部样式
     * @param refreshHeader
     */
    public void ReshfreHeadStyle(RefreshHeader refreshHeader){
        smartRefreshLayout.setRefreshHeader(refreshHeader);
    }

    /**
     * 设置上啦加载样式
     * @param refreshFooter
     */
    public void ReshfreFootStyle(RefreshFooter refreshFooter){
        smartRefreshLayout.setRefreshFooter(refreshFooter);
    }

    /**
     * 设置头部和底部 上啦活下来是否偏移
     * @param headIsTran
     * @param footIsTran
     */
    public void setEndableFootAndHeadTrans(boolean headIsTran,boolean footIsTran){
        smartRefreshLayout.setEnableFooterTranslationContent(footIsTran);
        smartRefreshLayout.setEnableHeaderTranslationContent(headIsTran);
    }

    /**
     * 设置刷新样式主题颜色
     */
    public void setPrimaryColorsId(@ColorRes int... primaryColorId){
        smartRefreshLayout.setPrimaryColorsId(primaryColorId);
    }

    /**
     * 关闭各种刷新加载，并是否开启加载更多
     * @param is
     */
    public void isLoadData(boolean is) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
        smartRefreshLayout.setEnableLoadMore(is);
//        smartRefreshLayout.setEnableRefresh(!is);
    }
}
