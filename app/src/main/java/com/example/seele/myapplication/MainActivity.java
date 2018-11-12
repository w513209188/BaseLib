package com.example.seele.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


import com.wb.baselib.interfaces.OnFilterDoneListener;
import com.wb.baselib.view.DropDownMenu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements OnFilterDoneListener {
    private DropDownMenu dropDownMenu;
    private DropMenuAdapter dropMenuAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        dropDownMenu = this.findViewById(R.id.dropDownMenu);

        initFilterDropDownView(getC());
//        course_wb=this.findViewById(R.id.course_wb);
//
//        course_wb.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//                Log.e("jia","加载速度"+newProgress);
//            }
//        });
//        course_wb.loadUrl(" http://test-px.huatu.com/#/courseDetailhfive/143");
//
//        WebSettings webSettings = course_wb.getSettings();
//        if (webSettings == null) return;
//        // 支持 Js 使用
//        webSettings.setJavaScriptEnabled(true);
//        // 开启DOM缓存
//        webSettings.setDomStorageEnabled(true);
//        // 开启数据库缓存
//        webSettings.setDatabaseEnabled(true);
//        // 设置 WebView 的缓存模式
//        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
//        // 支持启用缓存模式
//        webSettings.setAppCacheEnabled(true);
//        // 设置 AppCache 最大缓存值(现在官方已经不提倡使用，已废弃)
//        webSettings.setAppCacheMaxSize(8 * 1024 * 1024);
//        // Android 私有缓存存储，如果你不调用setAppCachePath方法，WebView将不会产生这个目录
//        webSettings.setAppCachePath(getCacheDir().getAbsolutePath());
//        // 数据库路径
//        // 关闭密码保存提醒功能
//        webSettings.setSavePassword(false);
//        // 支持缩放
//        webSettings.setSupportZoom(true);
//        // 设置 UserAgent 属性
//        webSettings.setUserAgentString("");
//        // 允许加载本地 html 文件/false
//        webSettings.setAllowFileAccess(true);
//        // 允许通过 file url 加载的 Javascript 读取其他的本地文件,Android 4.1 之前默认是true，在 Android 4.1 及以后默认是false,也就是禁止
//        webSettings.setAllowFileAccessFromFileURLs(false);
//        // 允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源，
//        // Android 4.1 之前默认是true，在 Android 4.1 及以后默认是false,也就是禁止
//        // 如果此设置是允许，则 setAllowFileAccessFromFileURLs 不起做用
//        webSettings.setAllowUniversalAccessFromFileURLs(false);


    }
    private void initFilterDropDownView(CourseMainClassflyBean courseClassflyBeans) {
        String[] titleList = new String[]{ "排序", "筛选"};
        dropMenuAdapter=new DropMenuAdapter(MainActivity.this, titleList, this,courseClassflyBeans);
        dropMenuAdapter.setSelectOption(0);
        dropDownMenu.setMenuAdapter(dropMenuAdapter);
    }
    @Override
    public void onFilterDone(int position, String positionTitle, String id1, String id) {

    }

    private CourseMainClassflyBean getC(){
        CourseMainClassflyBean courseMainClassflyBean=new CourseMainClassflyBean();
        List<CourseMainClassflyData> list=new ArrayList<>();
        courseMainClassflyBean.setList(list);

        courseMainClassflyBean.setList(list);
        return courseMainClassflyBean;
    }
//    protected void initView(Bundle savedInstanceState) {
//
//    }

}
