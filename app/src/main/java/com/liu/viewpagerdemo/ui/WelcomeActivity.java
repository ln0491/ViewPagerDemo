package com.liu.viewpagerdemo.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.liu.viewpagerdemo.R;
import com.liu.viewpagerdemo.WelcomeViewpagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    //viewpager
    private ViewPager mViewPager;
    //dots容器
    private LinearLayout mLlLayoutDots;

    //viewpager数据集合也可以用数组
    private List<Integer> mDatas = new ArrayList<>();
   // private int[] imageIds = new int[] { R.mipmap.img_recom01,R.mipmap.img_recom02, R.mipmap.img_recom03 };
    //viewpager适配器
    private WelcomeViewpagerAdapter mWelcomeViewpagerAdapter;
    //指示器布局参数
    private LinearLayout.LayoutParams mDotParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        initData();
       initListener();
    }





    private void initView() {

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mLlLayoutDots = (LinearLayout) findViewById(R.id.llLayoutDots);


    }


    private void initData() {
        //添加图片
        mDatas.add(R.mipmap.img_recom01);
        mDatas.add(R.mipmap.img_recom02);
        mDatas.add(R.mipmap.img_recom03);


        initViewPager();

        //添加指示器
        initDots();

    }

    private void initViewPager() {
        mWelcomeViewpagerAdapter = new WelcomeViewpagerAdapter(this,mDatas);
        mViewPager.setAdapter(mWelcomeViewpagerAdapter);

    }

    private void initDots() {
        mDotParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        mDotParams.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics());

        //根据viewpager 的数据去添加指示器
        for(int i = 0; i < mDatas.size(); i++) {

            ImageView imageView = new ImageView(getApplicationContext());

            //添加背景 选择器
            //imageView.setBackgroundResource(R.drawable.dot_selector);
            //添加图片选择器
            imageView.setImageResource(R.drawable.dot_selector);

            //默认为不选择灰色

            if(i == 0) {
                imageView.setSelected(true);
            } else {
                imageView.setSelected(false);
            }


            //添加到dot容器
            mLlLayoutDots.addView(imageView, mDotParams);
        }

    }

    private void initListener() {
        initViewPagerListener();





    }

    private void initViewPagerListener() {


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("vivi", "onPageScrolled:  position    "+position+"   positionOffset   "+positionOffset+"  positionOffsetPixels "+positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                int childCount = mLlLayoutDots.getChildCount();

                for(int i = 0; i < childCount; i++) {
                    if(i==position){
                        mLlLayoutDots.getChildAt(i).setSelected(true);
                    }else {
                        mLlLayoutDots.getChildAt(i).setSelected(false);
                    }

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

                Log.d("vivi", "onPageScrollStateChanged: "+state);
            }
        });
    }
}
