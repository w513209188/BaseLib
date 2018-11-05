//package com.wb.baselib.utils;
//
//import android.content.Context;
//import android.util.Log;
//import android.widget.Toast;
//import com.hss01248.dialog.StyledDialog;
//import com.hss01248.dialog.interfaces.MyItemDialogListener;
//import com.wb.baselib.app.AppUtils;
//import com.wb.baselib.app.AppUtils;
//import com.wb.baselib.appconfig.ShapeTypeConfig;
//import com.wb.baselib.bean.ShapeBean;
//import com.wb.baselib.interfaces.JuspLoginCall;
//import java.util.List;
//import cn.jiguang.share.android.api.AuthListener;
//import cn.jiguang.share.android.api.JShareInterface;
//import cn.jiguang.share.android.api.PlatActionListener;
//import cn.jiguang.share.android.api.Platform;
//import cn.jiguang.share.android.api.ShareParams;
//import cn.jiguang.share.android.model.AccessTokenInfo;
//import cn.jiguang.share.android.model.BaseResponseInfo;
//import cn.jiguang.share.android.utils.Logger;
//import cn.jiguang.share.qqmodel.QQ;
//import cn.jiguang.share.qqmodel.QZone;
//import cn.jiguang.share.wechat.Wechat;
//import cn.jiguang.share.wechat.WechatMoments;
//import cn.jiguang.share.weibo.SinaWeibo;
//import ly.rrnjnx.com.jpush_common.JpushApp;
//import ly.rrnjnx.com.jshape_common.JshapeApp;
//import ly.rrnjnx.com.jshape_common.bean.ShapeConfigBean;
//
///**
// * 极光推送 分享 三方登录 工具类 维护工具类
// * 2018年5月5日11:56:03
// * @author cj
// */
//public class JpushUtils {
//    private static JpushUtils jpushUtils;
//    public static JpushUtils getInstance(){
//        if(jpushUtils==null){
//            jpushUtils=new JpushUtils();
//        }
//        return jpushUtils;
//    }
//    /**
//     * 初始化推送
//     * @param mContext
//     */
//    public void initJpush(Context mContext,boolean isDeBug){
//
//        JpushApp.getInstance().initJpush(mContext,isDeBug);
//    }
//
//    /**
//     * 初始化分享
//     * @param mContext
//     */
//    public void initJshape(Context mContext, List<ShapeConfigBean> shapeConfigBeans,boolean isDebug){
//        JshapeApp.getInstance().initJshape(mContext,shapeConfigBeans,isDebug);
//    }
//    /**
//     * 吊起分享  如果
//     * @param shapePt 分享平台参数
//     */
//    public void openShape(final List<ShapeBean> shapePt, final PlatActionListener platActionListener){
//        if(shapePt==null||shapePt.size()==0){
//            Toast.makeText(AppUtils.getContext(),"没有有效的平台可以分享",Toast.LENGTH_LONG).show();
//            return;
//        }
//        StyledDialog.buildBottomSheetGv( "", shapePt, "",3, new MyItemDialogListener() {
//            @Override
//            public void onItemClick(CharSequence text, int position) {
//                ShapeBean shapeBean=shapePt.get(position);
//                ShareParams shareParams = new ShareParams();
//                shareParams.setTitle(shapeBean.getTitle());
//                shareParams.setText(shapeBean.getMsg());
//                shareParams.setShareType(Platform.SHARE_WEBPAGE);
//                shareParams.setImageUrl(shapeBean.getImageUrl());
//                shareParams.setUrl(shapeBean.getOpenUrl());
//                ShapeTypeConfig spt=shapePt.get(position).getType();
//                Log.e("分享工具",shapeBean.toString());
//                if(spt==ShapeTypeConfig.WX){
//                    JShareInterface.share(Wechat.Name, shareParams, platActionListener);
//                }else if(spt==ShapeTypeConfig.WXP){
//                    JShareInterface.share(WechatMoments.Name, shareParams, platActionListener);
//                }else if(spt==ShapeTypeConfig.QQ){
//                    JShareInterface.share(QQ.Name, shareParams, platActionListener);
//                }else if(spt==ShapeTypeConfig.QQZONE){
//                    JShareInterface.share(QZone.Name, shareParams, platActionListener);
//                }else if(spt==ShapeTypeConfig.SINA){
//                    JShareInterface.share(SinaWeibo.Name, shareParams, platActionListener);
//                }
//            }
//        }).show();
//    }
//
//    /**
//     * 第三方登录
//     * @param shapeTypeConfig 平台名称
//     * @param call 回调
//     */
//    public void UserLoginByJuspLogin(ShapeTypeConfig shapeTypeConfig, final JuspLoginCall call){
//        JShareInterface.authorize(getPtName(shapeTypeConfig), new AuthListener() {
//            @Override
//            public void onComplete(Platform platform, int action, BaseResponseInfo data) {
//                Log.e("----->>>action",action+"*****");
//                String toastMsg = null;
//                switch (action) {
//                    case Platform.ACTION_AUTHORIZING:
//                        if (data instanceof AccessTokenInfo) {        //授权信息
//                            String token = ((AccessTokenInfo) data).getToken();// token
//                            long expiration = ((AccessTokenInfo) data).getExpiresIn();// token有效时间，时间戳
//                            String refresh_token = ((AccessTokenInfo) data).getRefeshToken();//refresh_token
//                            String openid = ((AccessTokenInfo) data).getOpenid();//openid
//                            //授权原始数据，开发者可自行处理
//                            String originData = data.getOriginData();
//                            toastMsg = "授权成功:" + data.toString();
//                            Logger.e("1111", "openid:" + openid + ",token:" + token + ",expiration:" + expiration + ",refresh_token:" + refresh_token);
//                            Logger.e("1111", "originData:" + originData);
//                            call.getJuspLogin((AccessTokenInfo) data,true);
//                        }
//                        break;
//                }
//            }
//
//            @Override
//            public void onError(Platform platform, int action, int i1, Throwable throwable) {
//                String toastMsg = null;
//                switch (action) {
//                    case Platform.ACTION_AUTHORIZING:
//                        toastMsg = "授权失败";
//                        call.getJuspLogin(null,false);
//                        break;
//                }
//            }
//
//            @Override
//            public void onCancel(Platform platform, int action) {
//                Logger.e("111", "onCancel:" + platform + ",action:" + action);
//                String toastMsg = null;
//                switch (action) {
//                    case Platform.ACTION_AUTHORIZING:
//                        toastMsg = "取消授权";
//                        call.getJuspLogin(null,false);
//                        break;
//                }
//            }
//        });
//    }
//
//    /**
//     * 判断是否已经授权
//     * @param shapeTypeConfig
//     * @return
//     */
//    public boolean isJuspLoginAuthorize(ShapeTypeConfig shapeTypeConfig){
//        return JShareInterface.isAuthorize(getPtName(shapeTypeConfig));
//    }
//
//    /**
//     * 获取平台信息
//     * @return
//     */
//    public List<String> getPlats(){
//        return JShareInterface.getPlatformList();
//    }
//
//    /**
//     * 判断是否有效
//     * @param name
//     * @return
//     */
//    public boolean isClient(String name){
//        return JShareInterface.isClientValid(name);
//    }
//    /**
//     * 删除授权
//     * @param shapeTypeConfig
//     * @param authListener
//     */
//    public void  JuspLoginDeleteAuthorize(ShapeTypeConfig shapeTypeConfig,AuthListener authListener){
//         JShareInterface.removeAuthorize(getPtName(shapeTypeConfig),authListener);
//    }
//    private String getPtName(ShapeTypeConfig shapeTypeConfig){
//        String LoginName="";
//        if(shapeTypeConfig==ShapeTypeConfig.QQ){
//            LoginName="QQ";
//        }else if(shapeTypeConfig==ShapeTypeConfig.WX){
//            LoginName="Wechat";
//        }else {
//            LoginName="QQ";
//        }
//        return LoginName;
//    }
//}
