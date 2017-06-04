package com.example.renpeng.banyou;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by renpeng on 17/5/31.
 */
public class HomeFragment extends Fragment{

    private final static int UPDATE_TEXT = 1;

    private ImageView dog;

    private List<String> personList = new ArrayList<>();

    private List<String> quietList = new ArrayList<>();

    private Random random = new Random();

    private TextView textView;

    private boolean isCanThread = true;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    int n = random.nextInt(5);
                    String string= quietList.get(n);
                    textView.setText(string);
                    break;
                default:
                    break;
            }
        }
    };


    private  Thread thread = new Thread(){
        public void run(){
            try {

                while(isCanThread){

                    Thread.sleep(10000);
                    Message message = new Message();
                    message.what = UPDATE_TEXT;
                    handler.sendMessage(message);
                    fadeOut(textView);
                }


            } catch (InterruptedException e) { }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.view_page_1, container, false);

        textView = (TextView) view.findViewById(R.id.text);

        dog = (ImageView) view.findViewById(R.id.dag);
        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCanThread = false;
                rotateyAnimRun(dog);
                int n = random.nextInt(10);
                textView.setText(personList.get(n));
                fadeOut(textView);
                isCanThread = true;

            }
        });

        thread.start();

        getMessage(User.getUserName());


        return view;
    }



    private void fadeOut(View view){
        AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f);
        alpha.setDuration(2000);
        alpha.setFillAfter(true);
        view.setAnimation(alpha);
    }

    private void rotateyAnimRun(View view)
    {
        ObjectAnimator//
                .ofFloat(view, "rotationX", 0.0F, 360.0F)//
                .setDuration(500)//
                .start();
    }

    private void getMessage(String username){
        String host = UrlUtils.host;
        String getUrl = host + "user/getText?username="+username;
        HttpUtils.get(getUrl, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(),"访问错误",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                String text = responseString.substring(responseString.indexOf("{"));
                JSONTokener tokener = new JSONTokener(text);

                try {
                    JSONObject json = (JSONObject) tokener.nextValue();
                    String result = json.getString("result");
                    JSONObject info = json.getJSONObject("info");
                    JSONArray personArr = info.getJSONArray("personList");
                    JSONArray quietArr = info.getJSONArray("quietList");

                    if("true" == result){
                        initData(personArr,quietArr);
                    }else{
                        Toast.makeText(getActivity(),"没数据",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initData(JSONArray personArr,JSONArray quietArr) throws JSONException {
        for(int i = 0;i<personArr.length();i++){
            personList.add(((JSONObject)personArr.get(i)).getString("content"));
        }

        for(int i = 0;i<quietArr.length();i++){
            quietList.add(((JSONObject)quietArr.get(i)).getString("content"));
        }
    }

}
