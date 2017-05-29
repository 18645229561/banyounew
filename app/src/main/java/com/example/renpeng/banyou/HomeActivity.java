package com.example.renpeng.banyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

/**
 * Created by renpeng on 17/5/29.
 */
public class HomeActivity extends FragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home_activity_layout);

    }

    public static void startHomeActivity(Activity mActivity){
        Intent intent = new Intent(mActivity,HomeActivity.class);
        mActivity.startActivity(intent);
    }


}
