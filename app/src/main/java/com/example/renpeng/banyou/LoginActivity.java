package com.example.renpeng.banyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by renpeng on 17/5/29.
 */
public class LoginActivity extends FragmentActivity {

    TextView mTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_activity_layout);

        mTextView = (TextView) findViewById(R.id.register);
        mTextView.setText(Html.fromHtml("<u>"+"没有账号?快来注册!"+"</u>"));
    }

    public static void startLogioActivity(Activity mActivity)
    {
        Intent intent = new Intent(mActivity,LoginActivity.class);
        mActivity.startActivity(intent);
    }
}
