package com.example.a76599.du.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a76599.du.R;

import com.example.a76599.du.application.Baseapplication;
import com.example.a76599.du.entity.TextData;

import com.example.a76599.du.ui.ContentActivity;
import com.example.a76599.du.ui.WebViewActivity;
import com.example.a76599.du.utils.L;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 76599 on 2017/5/3.
 */

public class HomeFragment extends Fragment {
    private ListView tListView;
    private List<TextData> mList = new ArrayList<>();
    private List<String> tListTitle = new ArrayList<>();
    private List<String> tListdate = new ArrayList<>();
    private TextAdApter mAdpater;
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_home,null);
        findView(view);
        //loadDate();
        return view;
    }

    //初始化view
    private void findView(View view) {
        mAdpater = new TextAdApter(getContext());
        loadDate();
        tListView= (ListView) view.findViewById(R.id.tListView);
        tListView.setAdapter(mAdpater);
        //设置点击事件
        tListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                L.i("position1111"+position);
                Intent intent = new Intent(Baseapplication.getContext(), ContentActivity.class);
                //intent传值
                Log.d("TAG",mAdpater.texttitle[position]);
                intent.putExtra("title",mAdpater.texttitle[position]);
                intent.putExtra("date",mAdpater.date[position]);
                intent.putExtra("content",mAdpater.textcontent[position]);
                startActivity(intent);
            }
        });
    }

    //获取数据
    private void loadDate() {
        BmobQuery<TextData>query =new BmobQuery<>();
        query.findObjects(new FindListener<TextData>() {
            @Override
            public void done(List<TextData> object, BmobException e) {
                if (e==null){
                    L.i("查询成功"+object.get(0).getTexttitle()+object.get(0).getDate());
                    final String[] texttitle=new String[object.size()];
                    final String[] date=new String[object.size()];
                    final String[] textcontent = new String[object.size()];
                  for (int i=0;i<object.size();i++) {
                      texttitle[i] = object.get(i).getTexttitle();
                      date[i] = object.get(i).getDate();
                      textcontent[i] = object.get(i).getTextcontent();
                  }
                    mAdpater.setDate(texttitle,date,textcontent);
                }else {
                    L.i("查询失败");
                }
            }
        });

    }

    class TextAdApter extends BaseAdapter{
        private Context context ;
        private String[] texttitle={};
        private String[] date={};
        private String[] textcontent = {};
        public TextAdApter(Context context){
            this.context=context;
        }

        public void setDate(String[] texttitle,String[] date,String[] textcontent){
            this.texttitle =texttitle;
            this.date = date;
            this.textcontent = textcontent;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return texttitle.length;
        }

        @Override
        public Object getItem(int position) {
            return texttitle[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        //传入list
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView==null){
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView=inflater.inflate(R.layout.text_item,null);
                viewHolder=new ViewHolder();
                viewHolder.tx_title=(TextView) convertView.findViewById(R.id.tx_title);
                viewHolder.tx_date= (TextView) convertView.findViewById(R.id.tx_date);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.tx_title.setText(texttitle[position]);
            viewHolder.tx_date.setText(date[position]);
            return convertView;
        }
        class ViewHolder{
            TextView tx_title;
            TextView tx_date;
            TextView tx_content;
        }
    }
}
