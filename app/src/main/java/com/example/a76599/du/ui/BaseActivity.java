package com.example.a76599.du.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by 76599 on 2017/5/3.
 */

public class BaseActivity extends AppCompatActivity{
    protected void onCeart(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //返回键
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
        //菜单栏操作
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
