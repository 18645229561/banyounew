package com.example.renpeng.banyou;

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
 * Created by renpeng on 17/6/4.
 */
public class CheckInActivity extends FragmentActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chech_in_activity);

        listView = (ListView) findViewById(R.id.list);
    }

    private void getCheckInInfos(){
        String host = UrlUtils.host;
        String url = host + "subject/getSubjects";


        HttpUtils.get(url,new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(CheckInActivity.this,"出错啦!",Toast.LENGTH_SHORT).show();
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
                    List<CheckInEntitiy> list = new ArrayList<CheckInEntitiy>();
                    for(int i = 0;i<n ; i++)
                    {
                        CheckInEntitiy checkInEntitiy = new CheckInEntitiy();
                        checkInEntitiy.name = ((JSONObject)(jsonArray.get(i))).getString("name");
                        checkInEntitiy.status = ((JSONObject)(jsonArray.get(i))).getString("name");
                        list.add(checkInEntitiy);
                    }

                    if("true" == result){
                        setData(list);
                    }else{
                        Toast.makeText(CheckInActivity.this,"出错啦!",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setData( List<CheckInEntitiy> list){

    }


}
