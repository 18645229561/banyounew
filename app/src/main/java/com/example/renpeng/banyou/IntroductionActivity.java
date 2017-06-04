package com.example.renpeng.banyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by renpeng on 17/6/3.
 */
public class IntroductionActivity extends FragmentActivity implements View.OnClickListener{

    private TextView tvStartStudy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction_activity_laypout);

        tvStartStudy = (TextView) findViewById(R.id.start_study);
        tvStartStudy.setOnClickListener(this);
    }

    public static void startIntroductionActivity(Activity activity){
        Intent intent = new Intent(activity,IntroductionActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_study:
                SceneryListActivity.startSceneryListActivity(this);
                break;
        }
    }
}
