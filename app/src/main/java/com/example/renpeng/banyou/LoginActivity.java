package com.example.renpeng.banyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by renpeng on 17/5/29.
 */
public class LoginActivity extends FragmentActivity implements View.OnClickListener{

    TextView mTextView;

    Button mButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_activity_layout);

        mTextView = (TextView) findViewById(R.id.register);
        mTextView.setText(Html.fromHtml("<u>"+"没有账号?快来注册!"+"</u>"));

        mButton = (Button) findViewById(R.id.login);
        mButton.setOnClickListener(this);
        mTextView.setOnClickListener(this);
    }

    public static void startLoginActivity(Activity mActivity)
    {
        Intent intent = new Intent(mActivity,LoginActivity.class);
        mActivity.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login:
                HomeActivity.startHomeActivity(LoginActivity.this);
                break;
            case R.id.register:
                RegisterActivity.startRegisterActivity(LoginActivity.this);
            default:
                break;
        }
    }
}
