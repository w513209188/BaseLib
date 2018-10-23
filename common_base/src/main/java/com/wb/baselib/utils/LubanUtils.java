package com.wb.baselib.utils;

import android.content.Context;
import android.text.TextUtils;

import com.wb.baselib.interfaces.LuBanPhotoLoadCall;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class LubanUtils  {
    private static LubanUtils lubanUtils;
    public static LubanUtils newInstcance(){
        if(lubanUtils==null){
            synchronized (LubanUtils.class){
                lubanUtils=new LubanUtils();
            }
        }
        return lubanUtils;
    }

    /**
     * 单张图片压缩  注意对于图片小于100KB的不进行压缩
     * @param mContxe 上下文
     * @param mfile 将要压缩的图片地址
     * @param tarPath 压缩后缓存地址
     * @param luBanPhotoLoadCall 回调
     */
    public void load(Context mContxe, final List<File> mfile, String tarPath, final LuBanPhotoLoadCall luBanPhotoLoadCall){
        if(mfile==null||mfile.size()==0){
            luBanPhotoLoadCall.getLoadPhoto(null,false,"图片不能为空！");
            return;
        }
        final List<File> newFile=new ArrayList<>();
        Luban.with(mContxe)
                .load(mfile)
                .ignoreBy(100)
                .setTargetDir(tarPath)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        newFile.add(file);
                        if(newFile.size()==mfile.size()){
                            luBanPhotoLoadCall.getLoadPhoto(newFile,true,"压缩成功！");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                        luBanPhotoLoadCall.getLoadPhoto(null,false,e.getMessage());
                    }
                }).launch();
    }

}
