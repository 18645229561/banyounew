package com.example.renpeng.banyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;

/**
 * Created by renpeng on 17/6/4.
 */
public class SceneryDetailActivity extends FragmentActivity {

    private WebView webView;

    private String spotId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenery_detail_activity);

        webView = (WebView) findViewById(R.id.scenery_detail);
        webView.getSettings().setJavaScriptEnabled(true);
        spotId = getIntent().getStringExtra("spotId");
        webView.loadUrl("http://47.93.205.29/thundertech/spot/getSpotInfo?spotId="+spotId + "&username=" + User.getUserName());
    }

    public static void startSceneryDetailActivity(Activity activity,String spotId){
        Intent intent = new Intent(activity,SceneryDetailActivity.class);
        intent.putExtra("spotId",spotId);
        activity.startActivity(intent);

    }
}
