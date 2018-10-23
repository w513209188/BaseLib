package com.wb.baselib.interfaces;

import java.io.File;
import java.util.List;

/**
 * 鲁班压缩图片回调
 */
public interface LuBanPhotoLoadCall {
    void getLoadPhoto(List<File> s, boolean isLoad, String msg);
}
