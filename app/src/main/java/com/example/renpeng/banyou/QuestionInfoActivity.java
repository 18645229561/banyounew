package com.example.renpeng.banyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by renpeng on 17/5/30.
 */
public class QuestionInfoActivity extends FragmentActivity {

    private ListView mListView;

    private QuestionAdapter questionAdapter;

    private Button mButton;

    private Map<String,String> commitResults = new HashMap<>();


    private String username;
    private String password;
    private int sex;
    private String age;
    private String nickName;
    private String imgpath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity_layout);

        mListView = (ListView) findViewById(R.id.list);

        initData();

        getQuestionInfo();
    }

    private void initData(){
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        sex = getIntent().getIntExtra("sex",1);
        nickName = getIntent().getStringExtra("nickname");
        age = getIntent().getStringExtra("age");
        imgpath = getIntent().getStringExtra("imgpath");

    }

    public static void startQuestionInfoActivity(Activity mActivity,String username,
                                                 String password,int sex,String age,
                                                 String nickname,String imgpath){
        Intent intent = new Intent(mActivity,QuestionInfoActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("sex",sex);
        intent.putExtra("password",password);
        intent.putExtra("age",age);
        intent.putExtra("nickname",nickname);
        intent.putExtra("imgpath",imgpath);
        mActivity.startActivity(intent);
    }

    private void setData(List<String> list){
        questionAdapter = new QuestionAdapter(list,commitResults);
        mListView.setAdapter(questionAdapter);
        View footer = LayoutInflater.from(this).inflate(R.layout.question_footer_layout, null, false);
        mButton = (Button) footer.findViewById(R.id.commit);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    commitRegisterInfo();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        mListView.addFooterView(footer);
    }

    private void commitRegisterInfo() throws FileNotFoundException {

        String host = UrlUtils.host;
        String url = host+ "user/regist";

        File file = new File(imgpath);

        RequestParams requestParams = new RequestParams(getParams());
        requestParams.put("file",file);
        HttpUtils.post(url, requestParams,new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(QuestionInfoActivity.this,"出错啦!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                JSONTokener tokener = new JSONTokener(responseString);

                try {
                    JSONObject json = (JSONObject) tokener.nextValue();
                    String result = json.getString("result");
                    String info = json.getString("info");
                    if("true" == result){
                        HomeActivity.startHomeActivity(QuestionInfoActivity.this);
                    }else{
                        Toast.makeText(QuestionInfoActivity.this,info,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Map<String,String> getParams(){
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        map.put("gender",sex+"");
        map.put("age",age);
        map.put("nickname",nickName);

        StringBuilder stringBuilder = new StringBuilder();

        int n = commitResults.size();

        for(int i = 0;i<n;i++){
            if(i == n-1){
                stringBuilder.append(commitResults.get(i+""));
                break;
            }
            stringBuilder.append(commitResults.get(i+"")+",");
        }

        map.put("text",stringBuilder.toString());

        return map;
    }

    public void getQuestionInfo(){

        String host = UrlUtils.host;
        String url = host + "subject/getSubjects";


        HttpUtils.get(url,new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(QuestionInfoActivity.this,"出错啦!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                String text = responseString.substring(responseString.indexOf("{"));
                JSONTokener tokener = new JSONTokener(text);

                try {
                    JSONObject json = (JSONObject) tokener.nextValue();
                    String result = json.getString("result");
                    JSONArray jsonArray = json.getJSONArray("info");
                    int n = jsonArray.length();
                    List<String> list = new ArrayList<String>();
                    for(int i = 0;i<n ; i++)
                    {
                        String question = ((JSONObject)(jsonArray.get(i))).getString("content");
                        list.add(question);
                    }

                    if("true" == result){
                        setData(list);
                    }else{
                        Toast.makeText(QuestionInfoActivity.this,"出错啦!",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
