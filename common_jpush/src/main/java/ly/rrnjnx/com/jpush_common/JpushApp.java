package ly.rrnjnx.com.jpush_common;

import android.app.Notification;
import android.content.Context;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import ly.rrnjnx.com.jshape_common.JshapeApp;

public class JpushApp  {
    private static JpushApp mJpushApp;
    public static JpushApp getInstance(){
        if(mJpushApp==null){
            mJpushApp=new JpushApp();
        }
        return mJpushApp;
    }

    /**
     * 初始化 极光推送
     * @param mContext
     */
    public void initJpush(Context mContext,boolean isDebug){
        JPushInterface.setDebugMode(isDebug);
        JPushInterface.init(mContext);
        JPushInterface.requestPermission(mContext);
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(mContext);
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
                | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
        builder.notificationDefaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
        JPushInterface.setPushNotificationBuilder(1,builder);
    }
}
