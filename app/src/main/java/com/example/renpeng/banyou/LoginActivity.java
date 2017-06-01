package com.example.renpeng.banyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by renpeng on 17/5/29.
 */
public class LoginActivity extends FragmentActivity implements View.OnClickListener{

    private TextView mTextView;
    private  Button mButton;
    private EditText usernameEdit;
    private EditText passwordEdit;

    private String username = "";
    private String password = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_activity_layout);

        initView();

    }

    private void initView(){
        mTextView = (TextView) findViewById(R.id.register);
        mTextView.setText(Html.fromHtml("<u>"+"没有账号?快来注册!"+"</u>"));
        mButton = (Button) findViewById(R.id.login);

        usernameEdit = (EditText) findViewById(R.id.username);
        passwordEdit = (EditText) findViewById(R.id.password);

        mButton.setOnClickListener(this);
        mTextView.setOnClickListener(this);
    }

    private void getUserLoginInfo(){
        username = usernameEdit.getText().toString();
        password = passwordEdit.getText().toString();
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
                login();
                break;
            case R.id.register:
                RegisterActivity.startRegisterActivity(LoginActivity.this);
            default:
                break;
        }
    }

    private void login(){
        getUserLoginInfo();

        if(TextUtils.isEmpty(username))
        {
            Toast.makeText(LoginActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }

        User.registerName(username);

        String host = UrlUtils.host;
        String getUrl = host + "user/login?username="+username+ "&password=" + password;
        HttpUtils.get(getUrl, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(LoginActivity.this,"访问错误",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                String text = responseString.substring(responseString.indexOf("{"));
                JSONTokener tokener = new JSONTokener(text);

                try {
                    JSONObject json = (JSONObject) tokener.nextValue();
                    String result = json.getString("result");
                    String info = json.getString("info");
                    if("true" == result){
                        HomeActivity.startHomeActivity(LoginActivity.this);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this,info,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
