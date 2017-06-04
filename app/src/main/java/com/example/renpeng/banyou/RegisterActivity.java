package com.example.renpeng.banyou;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by renpeng on 17/5/30.
 */
public class RegisterActivity extends FragmentActivity implements View.OnClickListener{

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };

    private static final int IMAGE = 1;

    private String imgpath;

    private Button mButton;
    private EditText mUserNameEditText;
    private EditText mPassWordEditText;
    private EditText mSurePasswordEditText;
    private TextView mSexTextView;
    private TextView mAgeTextView;
    private EditText mNickNameEditText;
    private ImageView icon;

    private String username;
    private String password;
    private String surePassword;
    private int sex;
    private String age;
    private String nickName;

    private Dialog mSelectSexDialog;

    public static void verifyStoragePermissions(Activity activity) {
// Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
// We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity_layout);
        initView();
        verifyStoragePermissions(this);
    }

    private void initView(){
        mButton = (Button) findViewById(R.id.next);
        mUserNameEditText = (EditText) findViewById(R.id.username);
        mPassWordEditText = (EditText) findViewById(R.id.password);
        mSurePasswordEditText = (EditText) findViewById(R.id.sure_password);
        mSexTextView = (TextView) findViewById(R.id.sex);
        mAgeTextView = (TextView) findViewById(R.id.age);
        mNickNameEditText = (EditText) findViewById(R.id.name);
        icon = (ImageView) findViewById(R.id.icon);

        mSexTextView.setOnClickListener(this);
        mAgeTextView.setOnClickListener(this);
        mButton.setOnClickListener(this);
        icon.setOnClickListener(this);


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
                    User.registerName(username);
                    QuestionInfoActivity.startQuestionInfoActivity(this,username,password,sex,age,nickName,imgpath);
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
            case R.id.icon:
                selectPic();
                break;
            default:
                break;

        }
    }

    private void selectPic(){
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE);
    }

    private boolean checkInfoEmpty(){
        username = mUserNameEditText.getText().toString();
        password = mPassWordEditText.getText().toString();
        surePassword = mSurePasswordEditText.getText().toString();
        sex = "男" == mSexTextView.getText().toString()?1:0;
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

        if(TextUtils.isEmpty(sex+"")){
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            imgpath = c.getString(columnIndex);
            showImage(imgpath);
            c.close();
        }
    }

    //加载图片
    private void showImage(String imaePath){
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        icon.setImageBitmap(bm);
    }
}
