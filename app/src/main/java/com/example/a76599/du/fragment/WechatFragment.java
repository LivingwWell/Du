package com.example.a76599.du.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a76599.du.R;
import com.example.a76599.du.adapter.WeChatAdapter;
import com.example.a76599.du.entity.WeChatData;
import com.example.a76599.du.ui.WebViewActivity;
import com.example.a76599.du.utils.L;
import com.example.a76599.du.utils.StaticClass;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 76599 on 2017/5/3.
 */

public class WechatFragment extends Fragment {
    private ListView mListView;
    private List<WeChatData> mList = new ArrayList<>();
    private List<String> mListTitle = new ArrayList<>();
    private List<String> mListUrl = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, null);
        findView(view);
        return view;
    }

    //初始化view
    private void findView(View view) {
        mListView = (ListView) view.findViewById(R.id.mListView);
        //解析接口
        String url = "http://v.juhe.cn/weixin/query?key=" + StaticClass.WECHAT_KEY + "&ps=20";
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                //Toast.makeText(getActivity(),t,Toast.LENGTH_SHORT).show();
                L.i("json" + t);
                parsingJson(t);
            }

        });

        //设置点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                L.i("position"+position);
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                //intent传值
                intent.putExtra("title",mListTitle.get(position) );
                intent.putExtra("url",mListUrl.get(position));
                startActivity(intent);
            }
        });
    }

    //解析json
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonresult = jsonObject.getJSONObject("result");
            JSONArray jsonList = jsonresult.getJSONArray("list");
            for (int i = 0; i < jsonList.length(); i++) {
                JSONObject json = (JSONObject) jsonList.get(i);

                WeChatData data = new WeChatData();

                String title=json.getString("title");
                String url=json.getString("url");
                data.setTitle(title);
                data.setSource(json.getString("source"));
                data.setImgUrl(json.getString("firstImg"));


                mList.add(data);
                mListTitle.add(title);
                mListUrl.add(url);
            }
            WeChatAdapter adapter = new WeChatAdapter(getActivity(), mList);
            mListView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
