package com.example.renpeng.banyou;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renpeng on 17/5/29.
 */
public class HomeActivity extends FragmentActivity implements View.OnClickListener{

    private List<Fragment> fragments = new ArrayList<>();



    private ViewPager viewPager;

    private HomeAdapter homeAdapter;

    private LinearLayout homell;
    private LinearLayout studyll;
    private LinearLayout testll;
    private LinearLayout mell;

    private ImageView homeIcon;
    private ImageView studyIcon;
    private ImageView testIcon;
    private ImageView meIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home_activity_layout);

        initBottomBtn();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        initFragment();
        initViewPager();


    }

    private void initBottomBtn(){
        homell = (LinearLayout) findViewById(R.id.home_ll);
        studyll = (LinearLayout) findViewById(R.id.study_ll);
        testll = (LinearLayout) findViewById(R.id.test_ll);
        mell = (LinearLayout) findViewById(R.id.me_ll);

        homell.setOnClickListener(this);
        studyll.setOnClickListener(this);
        testll.setOnClickListener(this);
        mell.setOnClickListener(this);

        homeIcon = (ImageView) findViewById(R.id.home_icon);
        studyIcon = (ImageView) findViewById(R.id.study_icon);
        testIcon = (ImageView) findViewById(R.id.test_icon);
        meIcon  = (ImageView) findViewById(R.id.me_icon);

    }

    private void initFragment(){
        HomeFragment homeFragment = new HomeFragment();
        StudyFragment studyFragment = new StudyFragment();
        TestFragment testFragment = new TestFragment();
        MeFragment meFragment = new MeFragment();

        fragments.add(homeFragment);
        fragments.add(studyFragment);
        fragments.add(testFragment);
        fragments.add(meFragment);
    }

    private void initViewPager(){

        homeAdapter = new HomeAdapter(getSupportFragmentManager(),fragments);

        viewPager.setAdapter(homeAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        selectHomeIcon();
                        break;
                    case 1:
                        selectStudyIcon();
                        break;
                    case 2:
                        selectTestIcon();
                        break;
                    case 3:
                        selectMeIcon();
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        selectHomeIcon();
    }

    public static void startHomeActivity(Activity mActivity){
        Intent intent = new Intent(mActivity,HomeActivity.class);
        mActivity.startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_ll:
                viewPager.setCurrentItem(0);
                selectHomeIcon();
                break;
            case R.id.study_ll:
                viewPager.setCurrentItem(1);
                selectStudyIcon();
                break;
            case R.id.test_ll:
                viewPager.setCurrentItem(2);
                selectTestIcon();
                break;
            case R.id.me_ll:
                viewPager.setCurrentItem(3);
                selectMeIcon();
                break;
        }
    }

    private void selectHomeIcon(){
        homeIcon.setImageResource(R.mipmap.select_home);
        studyIcon.setImageResource(R.mipmap.study_icon);
        testIcon.setImageResource(R.mipmap.test_icon);
        meIcon.setImageResource(R.mipmap.my_set_icon);
    }

    private void selectStudyIcon(){
        homeIcon.setImageResource(R.mipmap.home_icon);
        studyIcon.setImageResource(R.mipmap.select_study);
        testIcon.setImageResource(R.mipmap.test_icon);
        meIcon.setImageResource(R.mipmap.my_set_icon);
    }

    private void selectTestIcon(){
        homeIcon.setImageResource(R.mipmap.home_icon);
        studyIcon.setImageResource(R.mipmap.study_icon);
        testIcon.setImageResource(R.mipmap.select_test);
        meIcon.setImageResource(R.mipmap.my_set_icon);
    }

    private void selectMeIcon(){
        homeIcon.setImageResource(R.mipmap.home_icon);
        studyIcon.setImageResource(R.mipmap.study_icon);
        testIcon.setImageResource(R.mipmap.test_icon);
        meIcon.setImageResource(R.mipmap.select_me);
    }


}
