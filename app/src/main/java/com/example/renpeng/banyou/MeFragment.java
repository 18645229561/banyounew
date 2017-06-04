package com.example.renpeng.banyou;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by renpeng on 17/5/29.
 */
public class MeFragment extends Fragment {

    public static final int UPDATE_ICON = 1;

    private ImageView userIcon;

    private Bitmap bitmap = null;

    private TextView username;

    private TextView money;

    private TextView type;

    private TextView stydyPath;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case UPDATE_ICON:
                    //在这里可以进行UI操作
                    //对msg.obj进行String强制转换
                    if(bitmap != null){
                        userIcon.setImageBitmap(bitmap);
                    }
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.view_page_4, container, false);

        userIcon = (ImageView) view.findViewById(R.id.user_icon);

        username = (TextView) view.findViewById(R.id.username);

        money = (TextView) view.findViewById(R.id.money);

        type = (TextView) view.findViewById(R.id.type);

        stydyPath = (TextView) view.findViewById(R.id.stydy_path);

        stydyPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckInActivity.startCheckInActivity(getActivity());
            }
        });
        getPicUrl();

        return view;
    }

    private void getPicUrl(){

        String host = UrlUtils.host;
        final String getUrl = host + "user/getUserInfo?username="+User.getUserName();
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
                    if("true" == result){

                        String nickname = info.getString("nickname");
                        username.setText(nickname);
                        String coins = info.getString("coins");
                        money.setText("伴伴币:" + coins);
                        String typeinfo = info.getString("typeinfo");
                        type.setText("学习风格:"+typeinfo);
                         final String url = info.getString("headpic");

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    getPic(url);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void  getPic(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
//        if (conn.getResponseCode() == 200){
//            InputStream inputStream = conn.getInputStream();
//            bitmap = BitmapFactory.decodeStream(inputStream);
//            Message message = new Message();
//            message.what = UPDATE_ICON;
//            message.obj=bitmap;
//            handler.sendMessage(message);
//
//        }
        InputStream inputStream = conn.getInputStream();
        bitmap = BitmapFactory.decodeStream(inputStream);
        Message message = new Message();
        message.what = UPDATE_ICON;
        message.obj=bitmap;
        handler.sendMessage(message);
    }


}
