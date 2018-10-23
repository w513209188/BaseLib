package com.jungan.www.module_down.mvp.contranct;

import com.jungan.www.module_down.bean.DownManagerBean;
import com.wb.baselib.base.mvp.BaseModel;
import com.wb.baselib.base.mvp.BasePreaenter;
import com.wb.baselib.base.mvp.MvpView;

import io.reactivex.Observable;

public interface DownManagerContranct {
    interface DownManagerView extends MvpView{
        void SuccessData(DownManagerBean downManagerBean);
        void reshData();
    }
    interface DownManagerModel extends BaseModel{
        Observable<DownManagerBean> getAllDownVideo();
        Observable<Long> reshData();
    }
    abstract class DownManagerPresenter extends BasePreaenter<DownManagerView,DownManagerModel>{
        public abstract void getAllDownVideo();
        public abstract void reshData();
    }
}
