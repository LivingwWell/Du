package com.example.a76599.du.utils;

import android.util.Log;

/**
 * Created by 76599 on 2017/5/4.
 *Log封装
 */

public class L {
    //开关
    public static final  boolean DEBUG=true;
    //TAG
    public static final String TAG="Smartbutler";
    //五个等级  DIWEF
    public static void d(String text){
        if(DEBUG){
            Log.d(TAG,text);
        }
    }
    public static void i(String text){
        if(DEBUG){
            Log.i(TAG,text);
        }
    }
    public static void w(String text){
        if(DEBUG){
            Log.w(TAG,text);
        }
    }
    public static void e(String text){
        if(DEBUG){
            Log.e(TAG,text);
        }
    }
}
