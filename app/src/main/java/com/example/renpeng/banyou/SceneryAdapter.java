package com.example.renpeng.banyou;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by renpeng on 17/6/4.
 */
public class SceneryAdapter extends BaseAdapter {

    private List<ScentryEntity> list;

    public SceneryAdapter(List<ScentryEntity> list){
        this.list = list;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scenery_item_layout, null, false);
        TextView textView = (TextView) view.findViewById(R.id.sceneryname);
        textView.setText(list.get(position).name);
        return view;
    }
}
