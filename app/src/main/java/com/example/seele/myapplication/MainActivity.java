package com.example.seele.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wb.baselib.http.HttpConfig;
import com.wb.baselib.http.HttpManager;


public class MainActivity extends AppCompatActivity {
    private Button post_bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        post_bt=this.findViewById(R.id.post_bt);
        post_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpConfig.HttpConfigBuilder httpConfig= new HttpConfig.HttpConfigBuilder()
                        .setmBaseUrl("http://www.baidu.com")
                        .setIsReshConfig(false)
                        .setmIsUseLog(false);
                        HttpConfig.newInstanceBuild(httpConfig);

                Log.e("huoqu",HttpManager.newInstance().getHttpConfig().getmBaseUrl());
            }
        });
    }
}
