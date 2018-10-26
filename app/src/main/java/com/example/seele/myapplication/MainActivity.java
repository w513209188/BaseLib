package com.example.seele.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.seele.myapplication.mvp.contranct.TestContranct;
import com.example.seele.myapplication.mvp.presenter.TestPresenter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.wb.baselib.base.activity.MvpActivity;


public class MainActivity extends MvpActivity<TestPresenter> implements TestContranct.TestView {
    private Button post_bt;

    @Override
    protected TestPresenter onCreatePresenter() {
        return new TestPresenter(this);
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        post_bt=this.findViewById(R.id.post_bt);
//        post_bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Map<String,String> map=new HashMap<>();
//                map.put("dsfds","fsdfds");
//                new HttpConfig.HttpConfigBuilder()
//                        .setmBaseUrl("http://www.baidu.com")
//                        .setIsReshConfig(true)
//                        .setmMapHeader(map)
//                        .setmIsUseLog(false).build();
//                Log.e("huoqu",HttpManager.newInstance().getHttpConfig().getmMapHeader().toString());
//            }
//        });
//    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_test);
        post_bt=getViewById(R.id.post_bt);
        post_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mPresenter.getTestData();

            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void ShowLoadView() {

    }

    @Override
    public void NoNetWork() {

    }

    @Override
    public void NoData() {

    }

    @Override
    public void ErrorData() {

    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void showLoadV(String msg) {

    }

    @Override
    public void closeLoadV() {

    }

    @Override
    public void SuccessData(Object o) {

    }

    @Override
    public LifecycleTransformer binLifecycle() {
        return bindToLifecycle();
    }
}
