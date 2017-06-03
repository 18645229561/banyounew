package com.example.renpeng.banyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by renpeng on 17/6/3.
 */
public class IntroductionActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction_activity_laypout);
    }

    public static void startIntroductionActivity(Activity activity){
        Intent intent = new Intent(activity,IntroductionActivity.class);
        activity.startActivity(intent);
    }
}
