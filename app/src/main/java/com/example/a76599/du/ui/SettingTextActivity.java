package com.example.a76599.du.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a76599.du.MainActivity;
import com.example.a76599.du.R;
import com.example.a76599.du.entity.TextData;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 76599 on 2017/5/7.
 */

public class SettingTextActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_title;
    private EditText et_textcontent;
    private Button btn_text;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_text);
        initView();
    }

    private void initView() {
        et_title= (EditText) findViewById(R.id.et_title);
        et_textcontent= (EditText) findViewById(R.id.et_textcontent);
        btn_text= (Button) findViewById(R.id.btn_text);
        btn_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            //获取输入的框的值
                switch (v.getId()){
                    case R.id.btn_text:
                        String title = et_title.getText().toString().trim();
                        String content = et_textcontent.getText().toString().trim();
                        //判断是否为空
                        if (!TextUtils.isEmpty(title)&!!TextUtils.isEmpty(content)){
                            Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                        }else{
                            //添加
                            TextData textdata=new TextData();
                            textdata.setTexttitle(title);
                            textdata.setTextcontent(content);
                            textdata.setDate(new SimpleDateFormat("yy-MM-dd").format(new Date()));
                            textdata.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e==null){
                                        Toast.makeText(SettingTextActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SettingTextActivity.this, MainActivity.class));
                                    }else {
                                        Toast.makeText(SettingTextActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        break;
                }
    }
}
