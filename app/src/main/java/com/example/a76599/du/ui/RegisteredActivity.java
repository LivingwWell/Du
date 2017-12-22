package com.example.a76599.du.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.a76599.du.R;
import com.example.a76599.du.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 76599 on 2017/5/5.
 */

public class RegisteredActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_user;
    private EditText et_age;
    private EditText et_desc;
    private EditText et_pass;
    private EditText et_password;
    private EditText et_email;
    private RadioGroup mRadioGroup;
    private Button btnRegistered;
    //性别
    private boolean isGender = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        initView();
    }

    private void initView() {
        et_user = (EditText) findViewById(R.id.et_user);
        et_age = (EditText) findViewById(R.id.et_age);
        et_desc = (EditText) findViewById(R.id.et_desc);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_password = (EditText) findViewById(R.id.et_password);
        et_email = (EditText) findViewById(R.id.et_email);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        btnRegistered = (Button) findViewById(R.id.btnRegistered);
        btnRegistered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegistered:
                //获取到输入框的值
                String name = et_user.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String desc = et_desc.getText().toString().trim();
                String pass = et_pass.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                //判断是否为空
                if (!TextUtils.isEmpty(name) &
                        !TextUtils.isEmpty(age) &
                        !TextUtils.isEmpty(pass) &
                        !TextUtils.isEmpty(password) &
                        !TextUtils.isEmpty(email)) {
                    if (pass.equals(password)){
                        //判断性别
                        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                if (checkedId == R.id.rb_boy) {
                                    isGender = true;
                                } else if (checkedId == R.id.rb_girl) {
                                    isGender = false;
                                }
                            }
                        });
                        //判断简介是否为空
                        if (TextUtils.isEmpty(desc)) {
                            desc = "这个人很懒什么都没留下";
                            //注册
                            MyUser user=new MyUser();
                            user.setUsername(name);
                            user.setPassword(password);
                            user.setEmail(email);
                            user.setAge(Integer.parseInt(age));
                            user.setDesc(desc);
                            user.setSex(isGender);

                            user.signUp(new SaveListener<MyUser>() {
                                @Override
                                public void done(MyUser myUser, BmobException e) {
                                    if(e==null){
                                        Toast.makeText(RegisteredActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(RegisteredActivity.this,"长得太丑，注册失败"+ e.toString(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }else{
                        Toast.makeText(this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
