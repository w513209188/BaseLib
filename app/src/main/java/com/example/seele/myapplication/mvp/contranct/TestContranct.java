package com.example.seele.myapplication.mvp.contranct;

import com.wb.baselib.base.mvp.BaseModel;
import com.wb.baselib.base.mvp.BasePreaenter;
import com.wb.baselib.base.mvp.MvpView;
import com.wb.baselib.bean.Result;

import io.reactivex.Observable;

public interface TestContranct {
    interface TestView extends MvpView{

    }
    interface TestModel extends BaseModel{
        Observable<Result> getTestData();
    }
    abstract class TestPresenter extends BasePreaenter<TestView,TestModel>{
            public abstract void getTestData();
    }
}
