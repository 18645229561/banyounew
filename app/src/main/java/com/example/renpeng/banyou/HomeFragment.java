package com.example.renpeng.banyou;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

    public void rotateyAnimRun(View view)
    {
        ObjectAnimator//
                .ofFloat(view, "rotationX", 0.0F, 360.0F)//
                .setDuration(500)//
                .start();
    }
}
