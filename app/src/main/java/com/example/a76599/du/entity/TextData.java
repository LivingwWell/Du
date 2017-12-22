package com.example.a76599.du.entity;

import android.graphics.Bitmap;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 76599 on 2017/5/8.
 */

public class TextData extends BmobObject {
    //文章标题
    private String texttitle;
    private String textcontent;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTexttitle() {
        return texttitle;
    }

    public void setTexttitle(String texttitle) {
        this.texttitle = texttitle;
    }

    public String getTextcontent() {
        return textcontent;
    }

    public void setTextcontent(String textcontent) {
        this.textcontent = textcontent;
    }



}
