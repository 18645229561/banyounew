package com.example.renpeng.banyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;
import android.widget.ListView;

/**
 * Created by renpeng on 17/6/4.
 */
public class SceneryListActivity extends FragmentActivity {

    private WebView webView;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenery_activity_layout);

        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://47.93.205.29/thundertech/map.html");

        listView = (ListView) findViewById(R.id.listview);

    }

    private void getSceneryList(){

    }

    public static void startSceneryListActivity(Activity activity){
        Intent intent = new Intent(activity,SceneryListActivity.class);
        activity.startActivity(intent);

    }
}
