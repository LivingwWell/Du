package com.example.a76599.du.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by 76599 on 2017/5/6.
 * 用户属性
 */

public class MyUser extends BmobUser{
    private int age;
    private boolean sex;
    private String desc;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
