package com.example.seele.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wb.baselib.crash.CrashHandler;
import com.wb.baselib.http.HttpConfig;
import com.wb.baselib.http.HttpManager;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity{
    private Button post_bt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        CrashHandler.getInstance().CheckAppCarchLog();
        post_bt=this.findViewById(R.id.post_bt);
        post_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post_bt.setText(null);
//                Map<String,String> map=new HashMap<>();
//                map.put("dsfds","fsdfds");
//                new HttpConfig.HttpConfigBuilder()
//                        .setmBaseUrl("http://www.baidu.com")
//                        .setIsReshConfig(true)
//                        .setmMapHeader(map)
//                        .setmIsUseLog(false).build();
//                Log.e("huoqu", HttpManager.newInstance().getHttpConfig().getmMapHeader().toString());
            }
        });
    }


    protected void initView(Bundle savedInstanceState) {

    }

}
