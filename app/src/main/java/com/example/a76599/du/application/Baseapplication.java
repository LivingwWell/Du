package com.example.a76599.du.application;

import android.app.Application;
import android.content.Context;

import com.example.a76599.du.utils.StaticClass;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;

/**
 * Created by 76599 on 2017/5/3.
 * sdk
 */

public class Baseapplication extends Application {
    static Context mContex;
    public void onCreate(){
        super.onCreate();
        // 初始化bugly
        mContex = getApplicationContext();
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_KEY, true);
        //初始化Bmob
        Bmob.initialize(this,StaticClass.BMOB_APP_KEY);

    }
    public static Context getContext(){
        return mContex;
    }
}
