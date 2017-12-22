package com.example.a76599.du.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.a76599.du.MainActivity;
import com.example.a76599.du.R;
import com.example.a76599.du.utils.ShareUtils;
import com.example.a76599.du.utils.StaticClass;

/**
 * Created by 76599 on 2017/5/4.
 * 首页闪屏
 */

public class SplashAvtivity extends AppCompatActivity {
    /*
    1.延时2000ms
    2.判断程序是否第一次运行
    3.自定义字体
    4.全屏主题
     */
    private TextView tv_splash;
    private Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.HANDLE_SPLASH:
                    //判断程序是否第一次运行
                    if (isFirst()) {
                        startActivity(new Intent(SplashAvtivity.this, GuideActivity.class));
                    } else {
                        startActivity(new Intent(SplashAvtivity.this, LoginActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    //初始化View
    private void initView() {
        //延时2000ms
        handle.sendEmptyMessageDelayed(StaticClass.HANDLE_SPLASH, 2000);
        tv_splash = (TextView) findViewById(R.id.tv_splash);

    }

    //判断程序是否第一次运行方法
    private boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this,StaticClass.SHARE_IS_FIRST,true);
        if (isFirst) {
            //标记已经启动过APP
            ShareUtils.putBoolean(this, StaticClass.SHARE_IS_FIRST, false);
            return true;
        } else {
            return false;
        }
    }
    //禁止返回键
    public void onBackPressed(){
        //super.onBackPressed();
    }
}
