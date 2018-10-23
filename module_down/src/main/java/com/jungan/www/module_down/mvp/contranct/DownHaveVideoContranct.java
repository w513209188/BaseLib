package com.jungan.www.module_down.mvp.contranct;

import com.baijiayun.download.DownloadTask;
import com.wb.baselib.base.mvp.BaseModel;
import com.wb.baselib.base.mvp.BasePreaenter;
import com.wb.baselib.base.mvp.MvpView;

import java.util.List;
import java.util.Set;

import io.reactivex.Observable;


public interface DownHaveVideoContranct {
    interface DownHaveVideoView extends MvpView{
        void getHaveDownView(List<DownloadTask> downloadTaskList);
        void videoDelect(Boolean b);
    }
    interface DownHaveVideoModel extends BaseModel{
        Observable<List<DownloadTask>> getHaveDownVideoList(String occ, String seach, String course);
        Observable<Boolean> userDelectVideo(Set<DownloadTask> downloadTasks);
    }
    abstract class DownHaveVideoPresenter extends BasePreaenter<DownHaveVideoView,DownHaveVideoModel>{
        public abstract void getHaveDownVideoList(String occ,String seach,String course);
        public abstract void userDelectVideo(Set<DownloadTask> downloadTasks);
    }
}
