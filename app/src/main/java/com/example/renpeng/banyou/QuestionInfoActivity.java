package com.example.renpeng.banyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by renpeng on 17/5/30.
 */
public class QuestionInfoActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity_layout);
        getQuestionInfo();
    }

    public static void startQuestionInfoActivity(Activity mActivity){
        Intent intent = new Intent(mActivity,QuestionInfoActivity.class);
        mActivity.startActivity(intent);
    }

    public void getQuestionInfo(){
        String url = "http://192.168.1.101:8080/thundertech/subject/getSubjects";
        HttpUtils.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

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
                        Log.d("info",info);
                    }else{

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
