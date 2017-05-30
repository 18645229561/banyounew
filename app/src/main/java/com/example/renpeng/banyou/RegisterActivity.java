package com.example.renpeng.banyou;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by renpeng on 17/5/30.
 */
public class RegisterActivity extends FragmentActivity implements View.OnClickListener{

    private Button mButton;
    private EditText mUserNameEditText;
    private EditText mPassWordEditText;
    private EditText mSurePasswordEditText;
    private TextView mSexTextView;
    private TextView mAgeTextView;
    private EditText mNickNameEditText;

    private String username;
    private String password;
    private String surePassword;
    private String sex;
    private String age;
    private String nickName;

    private Dialog mSelectSexDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity_layout);
        initView();
    }

    private void initView(){
        mButton = (Button) findViewById(R.id.next);
        mUserNameEditText = (EditText) findViewById(R.id.username);
        mPassWordEditText = (EditText) findViewById(R.id.password);
        mSurePasswordEditText = (EditText) findViewById(R.id.sure_password);
        mSexTextView = (TextView) findViewById(R.id.sex);
        mAgeTextView = (TextView) findViewById(R.id.age);
        mNickNameEditText = (EditText) findViewById(R.id.name);

        mSexTextView.setOnClickListener(this);
        mAgeTextView.setOnClickListener(this);
        mButton.setOnClickListener(this);


    }

    public static void startRegisterActivity(Activity mActivity){
        Intent intent = new Intent(mActivity,RegisterActivity.class);
        mActivity.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next:
                if(checkInfoEmpty()){
                    QuestionInfoActivity.startQuestionInfoActivity(this);
                }
                break;
            case R.id.sex:
                mSelectSexDialog = DialogUtils.getSelectSexDialog(this, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()){
                            case R.id.btn_left:
                                mSexTextView.setText("男");
                                break;
                            case R.id.btn_right:
                                mSexTextView.setText("女");
                                break;
                            default:
                                break;
                        }
                        mSelectSexDialog.dismiss();
                    }
                });
                mSelectSexDialog.show();
                break;
            case R.id.age:
                break;
            default:
                break;

        }
    }

    private boolean checkInfoEmpty(){
        username = mUserNameEditText.getText().toString();
        password = mPassWordEditText.getText().toString();
        surePassword = mSurePasswordEditText.getText().toString();
        sex = mSexTextView.getText().toString();
        age = mAgeTextView.getText().toString();
        nickName = mNickNameEditText.getText().toString();

        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"请填写用户名",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"请填写密码",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(surePassword)){
            Toast.makeText(this,"请填写确认密码",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(sex)){
            Toast.makeText(this,"请选择性别",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(age)){
            Toast.makeText(this,"请选择年龄",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(nickName)){
            Toast.makeText(this,"请填写昵称",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!password.equals(surePassword)){
            Toast.makeText(this,"两次密码不一致",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
