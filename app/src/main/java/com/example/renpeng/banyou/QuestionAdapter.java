package com.example.renpeng.banyou;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by renpeng on 17/5/30.
 */

public class QuestionAdapter extends BaseAdapter {

    private List<String> list;

    private Map<String,String> results;

    public QuestionAdapter(List<String> list,Map<String,String> results){
        this.list = list;
        this.results = results;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item_layout, null, false);
        TextView textView  = (TextView) view.findViewById(R.id.question_text);
        textView.setText(list.get(position));

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.redio1:
                        results.put(position+"","1");
                        break;
                    case R.id.redio2:
                        results.put(position+"","2");
                        break;
                    case R.id.redio3:
                        results.put(position+"","3");
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }
}
