package com.example.renpeng.banyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by renpeng on 17/5/30.
 */
public class RegisterActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity_layout);
    }

    public static void startRegisterActivity(Activity mActivity){
        Intent intent = new Intent(mActivity,RegisterActivity.class);
        mActivity.startActivity(intent);
    }

}
