package com.example.a76599.du.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.a76599.du.R;
import com.example.a76599.du.entity.TextData;

/**
 * Created by 76599 on 2017/5/9.
 */

public class ContentActivity extends AppCompatActivity{
    private TextView iv_title;
    private TextView iv_content;
    private TextView iv_date;;
    private TextData tda;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        initView();
        initData();
    }

    private void initData() {
        iv_title.setText(tda.getTexttitle());
        iv_content.setText(tda.getTextcontent());
        iv_date.setText(tda.getDate());

    }

    private void initView() {
        iv_title= (TextView) findViewById(R.id.iv_title);
        iv_content= (TextView) findViewById(R.id.iv_content);
        iv_date= (TextView) findViewById(R.id.iv_date);
        Intent intent = getIntent();
        tda = new TextData();
        String title = intent.getStringExtra("title");
        Log.d("TAG","content"+title);
        tda.setTexttitle(title);
        tda.setDate(intent.getStringExtra("date"));
        tda.setTextcontent(intent.getStringExtra("content"));
    }
}
