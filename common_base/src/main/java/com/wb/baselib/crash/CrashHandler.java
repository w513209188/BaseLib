package com.wb.baselib.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.wb.baselib.app.AppUtils;
import com.wb.baselib.appconfig.AppConfigManager;
import com.wb.baselib.bean.AppCarshLogAtBean;
import com.wb.baselib.bean.AppCarshLogBean;
import com.wb.baselib.bean.AppCarshLogTextBean;
import com.wb.baselib.prase.GsonUtils;
import com.wb.baselib.utils.SharedPrefsUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *	ClassName:	CrashHandler
 *	Function: 	UncaughtException处理类,当程序发生Uncaught异常的时候,由该类来接管程序,并记录发送错误报告.
 *	@version
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class CrashHandler implements UncaughtExceptionHandler {
	/**
	 * 	Log日志的tag
	 * 	String			:		TAG
	 * 	@since 2013-3-21下午8:44:28
	 */
	private static final String TAG = "NorrisInfo" ;
	/**
	 * 	系统默认的UncaughtException处理类
	 * 	Thread.UncaughtExceptionHandler			:		mDefaultHandler
	 * 	@since 2013-3-21下午8:44:43
	 */
	private UncaughtExceptionHandler mDefaultHandler ;
	/**
	 * 	CrashHandler实例
	 * 	CrashHandler			:		mInstance
	 * 	@since 2013-3-21下午8:44:53
	 */
	private static CrashHandler mInstance = new CrashHandler() ;
	/**
	 * 	程序的Context对象
	 * 	Context			:		mContext
	 * 	@since 2013-3-21下午8:45:02
	 */
	private Context mContext ;
	/**
	 * 	用来存储设备信息和异常信息
	 * 	Map<String,String>			:		mLogInfo
	 * 	@since 2013-3-21下午8:46:15
	 */
	private Map<String , String> mLogInfo = new HashMap<String , String>() ;
	/**
	 * 	用于格式化日期,作为日志文件名的一部分(FIXME 注意在windows下文件名无法使用：等符号！)
	 * 	SimpleDateFormat			:		mSimpleDateFormat
	 * 	@since 2013-3-21下午8:46:39
	 */
	private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyyMMdd_HH-mm-ss") ;
	/**
	 * 	Creates a new instance of CrashHandler.
	 */
	private CrashHandler() {
	}
	/**
	 * 	getInstance:{获取CrashHandler实例 ,单例模式 }
	 *  ──────────────────────────────────
	 * 	@return 	CrashHandler
	 * 	@throws
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2013-3-21下午8:52:24	Modified By Norris
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public static CrashHandler getInstance() {
		return mInstance ;
	}
	/**
	 * 	init:{初始化}
	 *  ──────────────────────────────────
	 * 	@param 		paramContext
	 * 	@return 	void
	 * 	@throws
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2013-3-21下午8:52:45	Modified By Norris
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public  String mAppName="appCarshLog";

	public void init(Context paramContext) {
		mContext = paramContext ;
		// 获取系统默认的UncaughtException处理器
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler() ;
		// 设置该CrashHandler为程序的默认处理器
		Thread.setDefaultUncaughtExceptionHandler(this) ;
	}

	/**
	 * 设置奔溃日志文件名称
	 * @param mAppName
	 */
	public void setmAppName(String mAppName) {
		this.mAppName = mAppName;
	}

	/**
	 * 	当UncaughtException发生时会转入该重写的方法来处理
	 * 	(non-Javadoc)
	 * 	@see UncaughtExceptionHandler#uncaughtException(Thread, Throwable)
	 */
	public void uncaughtException(Thread paramThread , Throwable paramThrowable) {
		if( ! handleException(paramThrowable) && mDefaultHandler != null) {
			// 如果自定义的没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(paramThread , paramThrowable) ;
		}
		else {
			try {
				// 如果处理了，让程序继续运行1秒再退出，保证文件保存并上传到服务器
				paramThread.sleep(1000) ;
			}
			catch(InterruptedException e) {
				e.printStackTrace() ;
			}
			// 退出程序
			android.os.Process.killProcess(android.os.Process.myPid()) ;
			System.exit(1) ;
		}
	}
	/**
	 * 	handleException:{自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.}
	 *  ──────────────────────────────────
	 * 	@param 		paramThrowable
	 * 	@return 	true:如果处理了该异常信息;否则返回false.
	 * 	@throws
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2013-3-24下午12:28:53	Modified By Norris
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public boolean handleException(Throwable paramThrowable) {
		if(paramThrowable == null)
			return false ;
		new Thread() {
			public void run() {
				Looper.prepare() ;
				Toast.makeText(mContext , "程序太累了，需要休息一会" , Toast.LENGTH_LONG).show() ;
				Looper.loop() ;
			}
		}.start() ;
		// 获取设备参数信息
		getDeviceInfo(mContext) ;
		// 保存日志文件
		saveCrashLogToFile(paramThrowable) ;
		return true ;
	}
	/**
	 * 	getDeviceInfo:{获取设备参数信息}
	 *  ──────────────────────────────────
	 * 	@param 		paramContext
	 * 	@throws
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2013-3-24下午12:30:02	Modified By Norris
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public void getDeviceInfo(Context paramContext) {
		try {
			// 获得包管理器
			PackageManager mPackageManager = paramContext.getPackageManager() ;
			// 得到该应用的信息，即主Activity
			PackageInfo mPackageInfo = mPackageManager.getPackageInfo(
					paramContext.getPackageName() , PackageManager.GET_ACTIVITIES) ;
			int appLab=mPackageInfo.applicationInfo.labelRes;
			String appName=paramContext.getResources().getString(appLab);

			if(mPackageInfo != null) {
				String versionName = mPackageInfo.versionName == null ? "null"
						: mPackageInfo.versionName ;
				String versionCode = mPackageInfo.versionCode + "" ;
				mLogInfo.put("versionName" , versionName) ;
				mLogInfo.put("versionCode" , versionCode) ;
				mLogInfo.put("appName",appName);
			}
		}
		catch(NameNotFoundException e) {
			e.printStackTrace() ;
		}
		// 反射机制
		Field[] mFields = Build.class.getDeclaredFields() ;
		// 迭代Build的字段key-value  此处的信息主要是为了在服务器端手机各种版本手机报错的原因
		for(Field field : mFields) {
			try {
				field.setAccessible(true) ;
				mLogInfo.put(field.getName() , field.get("").toString()) ;
			}
			catch(IllegalArgumentException e) {
				e.printStackTrace() ;
			}
			catch(IllegalAccessException e) {
				e.printStackTrace() ;
			}
		}
	}
	/**
	 * 	saveCrashLogToFile:{将崩溃的Log保存到本地}
	 * 	TODO 可拓展，将Log上传至指定服务器路径
	 *  ──────────────────────────────────
	 * 	@param 		paramThrowable
	 * 	@return		FileName
	 * 	@throws
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2013-3-24下午12:31:01	Modified By Norris
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private String saveCrashLogToFile(Throwable paramThrowable) {
		StringBuffer mStringBuffer = new StringBuffer() ;
		for(Map.Entry<String , String> entry : mLogInfo.entrySet()) {
			String key = entry.getKey() ;
			String value = entry.getValue() ;
			mStringBuffer.append(key + "=" + value + "\r\n") ;
		}
		Writer mWriter = new StringWriter() ;
		PrintWriter mPrintWriter = new PrintWriter(mWriter) ;
		paramThrowable.printStackTrace(mPrintWriter) ;
		paramThrowable.printStackTrace();
		Throwable mThrowable = paramThrowable.getCause() ;
		// 迭代栈队列把所有的异常信息写入writer中
		while(mThrowable != null) {
			mThrowable.printStackTrace(mPrintWriter) ;
			// 换行  每个个异常栈之间换行
			mPrintWriter.append("\r\n") ;
			mThrowable = mThrowable.getCause() ;
		}
		//记得关闭
		mPrintWriter.close() ;
		String mResult = mWriter.toString() ;
		mStringBuffer.append(mResult) ;
		// 保存文件，设置文件名
		String mTime = mSimpleDateFormat.format(new Date()) ;
		String mFileName = "CrashLog-" + mTime + ".log" ;
        SharedPrefsUtil.putValue(AppUtils.getContext(),  AppConfigManager.newInstance().getAppConfig().getAppCarshPath(),  AppConfigManager.newInstance().getAppConfig().getAppCarshPath(),mStringBuffer.toString());
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			try {
				File mDirectory = new File(Environment.getExternalStorageDirectory()+ "/"+mAppName);
				if( ! mDirectory.exists())
					mDirectory.mkdir() ;
				FileOutputStream mFileOutputStream = new FileOutputStream(mDirectory + "/"
						+ mFileName) ;
				mFileOutputStream.write(mStringBuffer.toString().getBytes()) ;
				mFileOutputStream.close() ;
				return mFileName ;
			}
			catch(FileNotFoundException e) {
				e.printStackTrace() ;
			}
			catch(IOException e) {
				e.printStackTrace() ;
			}
		}
		return null ;
	}
	public void CheckAppCarchLog(){
		if(!AppConfigManager.newInstance().getAppConfig().isSendCarshLog()){
			return;
		}
	    String logCarch=SharedPrefsUtil.getValue(AppUtils.getContext(), AppConfigManager.newInstance().getAppConfig().getAppCarshPath(), AppConfigManager.newInstance().getAppConfig().getAppCarshPath(),"");
	    if(logCarch==null||logCarch.equals("")){
	    return;
        }
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        AppCarshLogBean appCarshLogBean=new AppCarshLogBean();
        AppCarshLogTextBean appCarshLogTextBean=new AppCarshLogTextBean();
        appCarshLogTextBean.setContent("应用崩溃了，  @+86-"+AppConfigManager.newInstance().getAppConfig().getDdAccount()+logCarch);
        AppCarshLogAtBean appCarshLogAtBean=new AppCarshLogAtBean();
        List<String> strings=new ArrayList<>();
        strings.add("+86-"+AppConfigManager.newInstance().getAppConfig().getDdAccount());
        appCarshLogAtBean.setAtMobiles(strings);
        appCarshLogAtBean.setAtAll(false);
        appCarshLogBean.setAt(appCarshLogAtBean);
        appCarshLogBean.setText(appCarshLogTextBean);
        appCarshLogBean.setMsgtype("text");

        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , GsonUtils.newInstance().GsonToString(appCarshLogBean));
        Request request = new Request.Builder()
                .url("https://oapi.dingtalk.com/robot/send?access_token="+AppConfigManager.newInstance().getAppConfig().getDdToken())//请求的url
                .post(requestBody)
                .build();
        //创建/Call
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(new Callback() {
            //请求错误回调方法
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                SharedPrefsUtil.clearName(AppUtils.getContext(), AppConfigManager.newInstance().getAppConfig().getAppCarshPath());
            }
        });
    }
}
