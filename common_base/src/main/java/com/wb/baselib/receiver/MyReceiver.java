//package com.wb.baselib.receiver;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.content.pm.ResolveInfo;
//import android.net.Uri;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.wb.rxbus.taskBean.RxBus;
//import com.wb.rxbus.taskBean.RxMessageBean;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.Iterator;
//import java.util.List;
//
//import cn.jpush.android.api.JPushInterface;
//
///**
// * 自定义接收器
// *
// * 如果不定义这个 Receiver，则：
// * 1) 默认用户会打开主界面
// * 2) 接收不到自定义消息
// */
//public class MyReceiver extends BroadcastReceiver {
//	private static final String TAG = "JIGUANG-Example";
//	@Override
//	public void onReceive(Context context, Intent intent) {
//
//		try {
//			Bundle bundle = intent.getExtras();
//			printBundle(bundle);
//			if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
//				String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
//                Log.e(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//				//接收Registration Id
//				processCustomMessage(context,bundle,1);
//			} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
//				//接收到推送下来的自定义消息
//                Log.e(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//				processCustomMessage(context,bundle,2);
//			} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
//				int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
//                Log.e(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//				//接收到推送下来的通知
//				processCustomMessage(context,bundle,3);
//			} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
//				//用户点击打开了通知
//                Log.e(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
////				processCustomMessage(context,bundle,4);
////                Log.e("点击了","-------");
//                goS("zahb://main",context);
//			} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
//				//用户收到到RICH PUSH CALLBACK 在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
////				processCustomMessage(context,bundle,5);
////                Log.e(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//
//
//
//			} else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
//				boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
////				processCustomMessage(context,bundle,6);
//                Log.e(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//			} else {
//			}
//		} catch (Exception e){
//
//		}
//
//	}
//
//	// 打印所有的 intent extra 数据
//	private static String printBundle(Bundle bundle) {
//		StringBuilder sb = new StringBuilder();
//		for (String key : bundle.keySet()) {
//			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
//				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
//			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
//				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
//			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
//				if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
//					continue;
//				}
//
//				try {
//					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
//					Iterator<String> it =  json.keys();
//
//					while (it.hasNext()) {
//						String myKey = it.next();
//						sb.append("\nkey:" + key + ", value: [" +
//								myKey + " - " +json.optString(myKey) + "]");
//					}
//				} catch (JSONException e) {
//				}
//
//			} else {
//				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
//			}
//		}
//		Log.e("接受的推送",sb.toString());
//		return sb.toString();
//	}
//
//	private void processCustomMessage(Context context, Bundle bundle,int messageType) {
//			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//			RxBus.getIntanceBus().post(new RxMessageBean(messageType,message,extras));
//	}
//	private void goS(String uil,Context mContext) {
//		PackageManager packageManager = mContext.getPackageManager();
//		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uil));
//		List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
//		boolean isValid = !activities.isEmpty();
//		if (isValid) {
//			mContext.startActivity(intent);
//		} else {
//		}
//	}
//}
