# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Androidstadio\android-studio_sdk\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 5          # 指定代码的压缩级别
-dontusemixedcaseclassnames   # 是否使用大小写混合

-libraryjars libs/*.jar





-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class com.android.vending.licensing.ILicensingService


#gson  混淆
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.** { *;}

#support-v4混淆
-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment

#okhttputils
-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}
-keep interface com.zhy.http.**{*;}

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
-keep interface okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}
-keep interface okio.**{*;}
#okio
-dontwarn com.nineoldandroids.**
-keep class com.nineoldandroids.**{*;}
-keep interface com.nineoldandroids.**{*;}







#-dontwarn okhttp3.**
#-dontwarn okio.**
#-dontwarn javax.annotation.**
#-dontwarn org.conscrypt.**
## Retrofit 2.X
#-keep class retrofit2.** { *; }
#-keep interface retrofit2.** { *; }
#-keepclasseswithmembers class * {
#    @retrofit2.http.* <methods>;
#}
#-keepclasseswithmembers interface * {
#    @retrofit2.* <methods>;
#}
#-dontwarn retrofit2.Platform$Java8
#-dontwarn org.codehaus.mojo.**
#-dontwarn org.codehaus.**
#-ignorewarnings