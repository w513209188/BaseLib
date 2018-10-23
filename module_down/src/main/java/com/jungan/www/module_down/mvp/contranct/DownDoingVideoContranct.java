package com.jungan.www.module_down.mvp.contranct;

import com.baijiayun.download.DownloadTask;
import com.wb.baselib.base.mvp.BaseModel;
import com.wb.baselib.base.mvp.BasePreaenter;
import com.wb.baselib.base.mvp.MvpView;

import java.util.List;
import java.util.Set;

import io.reactivex.Observable;

public interface DownDoingVideoContranct  {
    interface DownDoingVideoView extends MvpView{
        void getDoingDownVideList(List<DownloadTask> lists);
        void userDelectVideo(Boolean is);
    }
    interface  DownDoingVideoModel extends BaseModel{
        Observable<List<DownloadTask>> getDownDoingVideo();
        Observable<Boolean> userDelectVideo(Set<DownloadTask> set);
    }
    abstract  class DownDoingVideoPresenter extends BasePreaenter<DownDoingVideoView,DownDoingVideoModel>{
            public abstract void getDownDoingVideo();
            public abstract void userDelectVideo(Set<DownloadTask> set);
    }
}
