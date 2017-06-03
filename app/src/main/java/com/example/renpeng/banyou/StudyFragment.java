package com.example.renpeng.banyou;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renpeng on 17/5/31.
 */
public class StudyFragment extends Fragment implements View.OnClickListener{

    private RelativeLayout rlSelectPath;
    private Spinner selectPath;

    private List<String> datas  = new ArrayList<String>();

    private ArrayAdapter<String> adapter;

    private TextView sure;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.view_page_2, container, false);



        rlSelectPath = (RelativeLayout) view.findViewById(R.id.rl_select_path);
        rlSelectPath.setOnClickListener(this);
        selectPath = (Spinner) view.findViewById(R.id.select_path);
        initPathData();
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,datas);
        //设置样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        selectPath.setAdapter(adapter);


        sure = (TextView) view.findViewById(R.id.sure);
        sure.setOnClickListener(this);

        return view;
    }

    private void initPathData(){
        datas.add("奥运路线");
        datas.add("历史路线");
        datas.add("文化路线");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_select_path:
                Toast.makeText(getActivity(),"暂未开通",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sure:
                break;
        }
    }
}
