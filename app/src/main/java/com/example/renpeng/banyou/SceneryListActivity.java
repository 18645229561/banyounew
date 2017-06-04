package com.example.renpeng.banyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renpeng on 17/6/4.
 */
public class SceneryListActivity extends FragmentActivity {

    private WebView webView;
    private ListView listView;

    private SceneryAdapter sceneryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenery_activity_layout);

        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://47.93.205.29/thundertech/map.html");

        listView = (ListView) findViewById(R.id.listview);

        getSceneryList();

    }

    private void getSceneryList(){
        String host = UrlUtils.host;
        String url = host + "spot/getSpots";

        HttpUtils.get(url,new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(SceneryListActivity.this,"出错啦!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                String text = responseString.substring(responseString.indexOf("{"));
                JSONTokener tokener = new JSONTokener(text);

                try {
                    JSONObject json = (JSONObject) tokener.nextValue();
                    String result = json.getString("result");
                    JSONArray jsonArray = json.getJSONArray("info");
                    int n = jsonArray.length();
                    List<ScentryEntity> list = new ArrayList<ScentryEntity>();
                    for(int i = 0;i<n ; i++)
                    {
                        ScentryEntity scentryEntity = new ScentryEntity();
                        scentryEntity.spotId= ((JSONObject)(jsonArray.get(i))).getString("spotId");
                        scentryEntity.name = ((JSONObject)(jsonArray.get(i))).getString("spotName");
                        list.add(scentryEntity);
                    }

                    if("true" == result){
                        setData(list);
                    }else{
                        Toast.makeText(SceneryListActivity.this,"出错啦!",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setData(final List<ScentryEntity> list){
        sceneryAdapter = new SceneryAdapter(list);
        listView.setAdapter(sceneryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SceneryDetailActivity.startSceneryDetailActivity(SceneryListActivity.this,list.get(position).spotId);
            }
        });
    }


    public static void startSceneryListActivity(Activity activity){
        Intent intent = new Intent(activity,SceneryListActivity.class);
        activity.startActivity(intent);

    }
}
