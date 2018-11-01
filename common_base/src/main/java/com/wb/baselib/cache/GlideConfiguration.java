//package com.wb.baselib.cache;
//
//import android.content.Context;
//
////import com.bumptech.glide.Glide;
////import com.bumptech.glide.GlideBuilder;
////import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
////import com.bumptech.glide.module.GlideModule;
//import com.wb.baselib.appconfig.AppConfigManager;
//
///**
// * Created by YaphetZhao
// * on 2016/12/19.
// * <p>
// * QQ:11613371
// * GitHub:https://github.com/YaphetZhao
// * Email:yaphetzhao@foxmail.com
// * Email_EN:yaphetzhao@gmail.com
// * <p>
// * GlideConfiguration
// */
//
//public class GlideConfiguration implements GlideModule {
//
//    // 需要在AndroidManifest.xml中声明
//    // <meta-data
//    //    android:name="com.yaphetzhao.glidecatchsimple.glide.GlideConfiguration"
//    //    android:value="GlideModule" />
//
//    @Override
//    public void applyOptions(Context context, GlideBuilder builder) {
//        //自定义缓存目录
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,
//                AppConfigManager.newInstance().getAppConfig().getGlidePath(),
//                AppConfigManager.newInstance().getAppConfig().getGlideSize()));
//    }
//
//    @Override
//    public void registerComponents(Context context, Glide glide) {
//        //nil
//    }
//}