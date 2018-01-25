package com.lzy.commonokhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lzy.commonhttpsdk.HttpCallBackProgress;
import com.lzy.commonhttpsdk.HttpUtils;
import com.lzy.commonokhttp.network.HttpCallBackEntity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //网络请求
        HttpUtils.with(MainActivity.this).url("url").addParams("key","vlues").post().execute(new HttpCallBackEntity<Persion>() {
            @Override
            public void onSuccess(Persion persion) {

            }

            @Override
            public void onError(Exception e) {

            }
        });
        //下载
        HttpUtils.with(MainActivity.this).url("url").downLoadFilsUrl("sd卡地址").downLoadFiles().post().execute(new HttpCallBackProgress() {
            @Override
            public void onProgress(int progress) {
                //进度
            }

            @Override
            public void onError(Exception e) {
               //出错
            }

            @Override
            public void onSucceed(Object result) {
              //成功
            }
        });

    }
}
