package com.example.renpeng.banyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;

/**
 * Created by renpeng on 17/6/4.
 */
public class TestDetailActivity extends FragmentActivity {
    private WebView webView;

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenery_detail_activity);

        webView = (WebView) findViewById(R.id.scenery_detail);
        webView.getSettings().setJavaScriptEnabled(true);
        type = getIntent().getStringExtra("type");
        webView.loadUrl("http://47.93.205.29/thundertech/subject/" + type);
    }

    public static void startTestDetailActivity(Activity activity, String type){
        Intent intent = new Intent(activity,TestDetailActivity.class);
        intent.putExtra("type",type);
        activity.startActivity(intent);
    }
}
