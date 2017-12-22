package com.example.a76599.du.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a76599.du.R;
import com.example.a76599.du.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 76599 on 2017/5/6.
 */

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
        private Button btn_forget_password;
       private EditText et_email;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        initView();
    }

    private void initView() {
        btn_forget_password= (Button) findViewById(R.id.btn_forget_password);
        btn_forget_password.setOnClickListener(this);
        et_email= (EditText) findViewById(R.id.et_email);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_forget_password:
                //获取输入框
                final String email=et_email.getText().toString().trim();
                //判断是否为空
                if (!TextUtils.isEmpty(email)){
                    //发送邮件
                    MyUser.resetPasswordByEmail(email, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e==null){
                                Toast.makeText(ForgetPasswordActivity.this,"邮件已发送至"+email,Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(ForgetPasswordActivity.this,"发送失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(this,"请输入邮箱",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
