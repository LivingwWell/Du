package com.example.a76599.du.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by 76599 on 2017/5/3.
 * 数据、常量
 */

public class UtilTools {

    //保存到shareuitls下
    public static void putImgeToShare(Context mContext, ImageView imageView){
        //保存
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        //讲bitmap压缩成字节流出流
        ByteArrayOutputStream byStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byStream);
        //讲字节数组输出流转换成STRING
        byte[] byteArray = byStream.toByteArray();
        String imgString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        //将Strign保存到shareUtils
        ShareUtils.putString(mContext, "image_title", imgString);
    }

    //读取图片
    public static void getImageToShare(Context mContext, ImageView imageView){
        //1.拿到string
        String imgString = ShareUtils.getString(mContext, "image_title", "");
        if (!imgString.equals("")) {
            //2.讲字节数组输出流转换成STRING
            byte[] byteArray = Base64.decode(imgString, Base64.DEFAULT);
            ByteArrayInputStream byStream = new ByteArrayInputStream(byteArray);
            //3.生成bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(byStream);
            imageView.setImageBitmap(bitmap);
        }
    }
}

