package com.example.a76599.du;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.a76599.du.fragment.HomeFragment;
import com.example.a76599.du.fragment.UserFragment;
import com.example.a76599.du.fragment.MusicFragment;
import com.example.a76599.du.fragment.WechatFragment;
import com.example.a76599.du.ui.SettingTextActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String>mTitle;
    private List<Fragment>mFragment;
    //悬浮窗
    private FloatingActionButton fab_setting;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*去掉阴影
       getSupportActionBar().setElevation(0);*/
        initDate();
        initView();
    }
    //初始化数据
    private void initDate(){
        fab_setting= (FloatingActionButton) findViewById(R.id.fab_setting);
        fab_setting.setOnClickListener(this);
        mTitle=new ArrayList<>();
        mTitle.add("首页");
        mTitle.add("文章");
       // mTitle.add("音乐");
        mTitle.add("设置");

        mFragment=new ArrayList<>();
        mFragment.add(new HomeFragment());
        mFragment.add(new WechatFragment());
      //  mFragment.add(new MusicFragment());
        mFragment.add(new UserFragment());

    }
   //初始化view
    private void initView(){
    mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
    mViewPager= (ViewPager) findViewById(R.id.mViewPager);
        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());
        //mViewPager滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                  Log.i("TAG","position:"+position);
                if (position==1){
                    fab_setting.setVisibility(View.GONE);
                }else if (position==3){
                    fab_setting.setVisibility(View.GONE);
                }else{
                    fab_setting.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }
            //返回item的个数
            @Override
            public int getCount() {
                return mFragment.size();
            }
            //设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        //绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_setting:
                startActivity(new Intent(this, SettingTextActivity.class));
                break;
        }
    }
}