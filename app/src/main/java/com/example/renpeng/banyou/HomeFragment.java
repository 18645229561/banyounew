package com.example.renpeng.banyou;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by renpeng on 17/5/31.
 */
public class HomeFragment extends Fragment{

    private ImageView dog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.view_page_1, container, false);

        dog = (ImageView) view.findViewById(R.id.dag);
        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateyAnimRun(dog);
            }
        });

        return view;
    }

    private void rotateyAnimRun(View view)
    {
        ObjectAnimator//
                .ofFloat(view, "rotationX", 0.0F, 360.0F)//
                .setDuration(500)//
                .start();
    }

    private void getMessage(String username){
        String host = "http://47.93.205.29/thundertech";
        String getUrl = host + "/user/login?username="+username;
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
                    String info = json.getString("info");
                    if("true" == result){

                    }else{
                        Toast.makeText(getActivity(),info,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
