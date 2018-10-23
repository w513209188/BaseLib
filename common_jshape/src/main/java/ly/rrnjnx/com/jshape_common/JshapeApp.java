package ly.rrnjnx.com.jshape_common;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatformConfig;
import ly.rrnjnx.com.jshape_common.bean.ShapeConfigBean;
import ly.rrnjnx.com.jshape_common.interfaces.ShapeConfig;


public class JshapeApp   {
    private static JshapeApp jshapeApp;
    public static JshapeApp getInstance(){
        if(jshapeApp==null){
            jshapeApp=new JshapeApp();
        }
        return jshapeApp;
    }
    public void initJshape(Context mContext, List<ShapeConfigBean> shapeConfigBeans,boolean isDebug){
        if(shapeConfigBeans==null||shapeConfigBeans.size()==0){
            throw new UnsupportedOperationException("配置平台参数异常");
        }
        PlatformConfig platformConfig=new PlatformConfig();
        for(ShapeConfigBean shapeConfigBean:shapeConfigBeans){
            switch (shapeConfigBean.getShapeConfig()){
                case ShapeConfig.QQ:
                    platformConfig.setQQ(shapeConfigBean.getAppId(),shapeConfigBean.getAddKey());
                    break;
                case ShapeConfig.WX:
                    platformConfig.setWechat(shapeConfigBean.getAppId(),shapeConfigBean.getAddKey());
                    break;
                case ShapeConfig.SINA:
                    platformConfig.setSinaWeibo(shapeConfigBean.getAppId(),shapeConfigBean.getAddKey(),shapeConfigBean.getRedirectUrl());
                    break;
            }
        }
        JShareInterface.init(mContext,platformConfig);
        JShareInterface.setDebugMode(isDebug);
    }
}
