package com.example.renpeng.banyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renpeng on 17/5/30.
 */
public class QuestionInfoActivity extends FragmentActivity {

    private ListView mListView;

    private QuestionAdapter questionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity_layout);

        mListView = (ListView) findViewById(R.id.list);

        getQuestionInfo();
    }

    public static void startQuestionInfoActivity(Activity mActivity){
        Intent intent = new Intent(mActivity,QuestionInfoActivity.class);
        mActivity.startActivity(intent);
    }

    private void setData(List<String> list){
        questionAdapter = new QuestionAdapter(list);
        mListView.setAdapter(questionAdapter);
    }

    public void getQuestionInfo(){
        String url = "http://192.168.1.101:8080/thundertech/subject/getSubjects";
        HttpUtils.get(url, new TextHttpResponseHandler() {
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
                       // Toast.makeText(QuestionInfoActivity.this,"---"+list.size(),Toast.LENGTH_SHORT).show();
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
