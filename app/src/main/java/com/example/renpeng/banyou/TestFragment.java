package com.example.renpeng.banyou;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by renpeng on 17/5/31.
 */
public class TestFragment extends Fragment implements View.OnClickListener{

    private TextView medium;
    private TextView high;

    private String type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.view_page_3, container, false);

        medium = (TextView) view.findViewById(R.id.test1);
        high = (TextView) view.findViewById(R.id.test2);

        medium.setOnClickListener(this);
        high.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test1:
                type = "TestJunior";
            case R.id.test2:
                type = "TestSenior";
                break;
        }
        TestDetailActivity.startTestDetailActivity(getActivity(),type);

    }
}
