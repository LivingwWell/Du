package com.example.a76599.du.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a76599.du.MainActivity;
import com.example.a76599.du.R;
import com.example.a76599.du.entity.MyUser;
import com.example.a76599.du.utils.ShareUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 76599 on 2017/5/5.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_registered;
    private EditText et_name;
    private EditText et_password;
    private Button btnLogin;
    private CheckBox keep_password;
    private TextView tv_forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        btn_registered = (Button) findViewById(R.id.btn_registered);
        btn_registered.setOnClickListener(this);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btnLogin.setOnClickListener(this);
        keep_password= (CheckBox) findViewById(R.id.keep_password);
        //设置选中状态
       boolean isCheck= ShareUtils.getBoolean(this,"keeppass",false);
        keep_password.setChecked(isCheck);
        if (isCheck){
            //设置密码
            et_name.setText(ShareUtils.getString(this,"name",""));
            et_password.setText(ShareUtils.getString(this,"password",""));
        }
        tv_forget= (TextView) findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())) {
            case R.id.tv_forget:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.btn_registered:
                startActivity(new Intent(this, RegisteredActivity.class));
                break;
            case R.id.btnLogin:
                //1.获取输入框的值
                String name = et_name.getText().toString().trim();
                String password = et_password.getText().toString();
                //2.判断是否为空
                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)) {
                    //3.登录
                    final MyUser user = new MyUser();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            if (e == null) {
                                   //判断邮箱是否验证
                                if (user.getEmailVerified()){
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                }else {
                                    Toast.makeText(LoginActivity.this, "请验证邮箱" + e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "长得太丑登录失败" + e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "请输入帐号密码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        //保存状态
        ShareUtils.putBoolean(this,"keeppass",keep_password.isChecked());
        //是否记住密码
        if (keep_password.isChecked()){
            ShareUtils.putString(this,"name",et_name.getText().toString().trim());
            ShareUtils.putString(this,"password",et_password.getText().toString().trim());
        }else {
            ShareUtils.deleShare(this,"name");
            ShareUtils.deleShare(this,"password");
        }
    }
}
