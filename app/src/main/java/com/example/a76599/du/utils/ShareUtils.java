package com.example.a76599.du.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 76599 on 2017/5/4.
 * SharedPreferences封装
 */

public class ShareUtils {

    public static final String NAME="config";
    //键值
    public static void putString(Context mContext,String key,String value){
     SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_APPEND);
     sp.edit().putString(key,value).commit();
 }
    // 键默认值
    public static String getString(Context mContext,String key,String defValue){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_APPEND);
        return sp.getString(key,defValue);
    }
    //键值
    public static void putInt(Context mContext,String key,int value){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_APPEND);
        sp.edit().putInt(key,value).commit();
    }
    // 键默认值
    public static int getInt(Context mContext,String key,int defValue){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_APPEND);
        return sp.getInt(key,defValue);
    }
    //键值
    public static void putBoolean(Context mContext,String key,boolean value){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_APPEND);
        sp.edit().putBoolean(key,value).commit();
    }
    // 键默认值
    public static boolean getBoolean(Context mContext,String key,boolean defValue){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_APPEND);
        return sp.getBoolean(key,defValue);
    }
    //删除单个
    public static void deleShare(Context mContext,String key){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_APPEND);
        sp.edit().remove(key).commit();
    }
    //删除全部
    public static void deleALL(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_APPEND);
        sp.edit().clear().commit();
    }
}
