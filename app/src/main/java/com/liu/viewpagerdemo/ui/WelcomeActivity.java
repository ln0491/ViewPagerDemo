package com.liu.viewpagerdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.liu.viewpagerdemo.R;
import com.liu.viewpagerdemo.adapter.WelcomeViewpagerAdapter;
import com.liu.viewpagerdemo.view.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    //viewpager
    private ViewPager    mViewPager;
    //dots容器
    private LinearLayout mLlLayoutDots;

    //viewpager数据集合也可以用数组
    private List<Integer> mDatas = new ArrayList<>();
    // private int[] imageIds = new int[] { R.mipmap.img_recom01,R.mipmap.img_recom02, R.mipmap.img_recom03 };
    //viewpager适配器
    private WelcomeViewpagerAdapter   mWelcomeViewpagerAdapter;
    //指示器布局参数
    private LinearLayout.LayoutParams mDotParams;

    //指示器容器列表
    private List<ImageView> dots = new ArrayList<ImageView>();
    //当前索引
    private int currPageIndex;


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

        //添加指示器  指示器
        initDots();

    }

    private void initViewPager() {
        mWelcomeViewpagerAdapter = new WelcomeViewpagerAdapter(this, mDatas);
        mViewPager.setAdapter(mWelcomeViewpagerAdapter);
        //添加切换效果
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        //        mViewPager.setPageTransformer(true,  new ZoomOutPageTransformer());
    }

    private void initDots() {
        mDotParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mDotParams.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());

        //根据viewpager 的数据去添加指示器
        for(int i = 0; i < mDatas.size(); i++) {

            ImageView imageView = new ImageView(getApplicationContext());

            //添加背景 选择器
            //imageView.setBackgroundResource(R.drawable.dot_selector);
            //添加图片选择器
            imageView.setImageResource(R.drawable.dot_selector);

            //默认为不选择灰色

            imageView.setSelected(false);

            //添加到dot容器
            mLlLayoutDots.addView(imageView, mDotParams);

            dots.add(imageView);
        }

        //设置第一个为选择状态
        dots.get(0).setSelected(true);

    }

    private void initListener() {
        initViewPagerListener();


    }

    private void initViewPagerListener() {


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private boolean flag;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //  Log.d("vivi", "onPageScrolled:  position    " + position + "   positionOffset   " + positionOffset + "  positionOffsetPixels " + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
               /* int childCount = mLlLayoutDots.getChildCount();

                for(int i = 0; i < childCount; i++) {
                    if(i == position) {
                        mLlLayoutDots.getChildAt(i).setSelected(true);
                    } else {
                        mLlLayoutDots.getChildAt(i).setSelected(false);
                    }

                }*/

                //旧点不亮
                dots.get(currPageIndex % mDatas.size()).setSelected(false);
                //设置新的点
                currPageIndex = position;
                //新点亮起来
                dots.get(currPageIndex % mDatas.size()).setSelected(true);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

                Log.d("vivi", "onPageScrollStateChanged: " + state);
                switch(state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        //拖的时候才进入下一页
                        flag = false;
                        Log.d("vivi", "SCROLL_STATE_DRAGGING: " + ViewPager.SCROLL_STATE_DRAGGING);

                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        flag = true;
                        Log.d("vivi", "SCROLL_STATE_SETTLING: " + ViewPager.SCROLL_STATE_SETTLING);
                        break;


                    case ViewPager.SCROLL_STATE_IDLE:
                        Log.d("vivi", "SCROLL_STATE_IDLE: " + ViewPager.SCROLL_STATE_IDLE+"  mViewPager.getCurrentItem() "+mViewPager.getCurrentItem());
                        /**
                         * 判断是不是最后一页，同是是不是拖的状态
                         */
                        if(mViewPager.getCurrentItem() == mWelcomeViewpagerAdapter.getCount() - 1 && !flag) {
                            Intent localIntent = new Intent();

                           // localIntent.setClass(WelcomeActivity.this, MainActivity.class);
                            localIntent.setClass(WelcomeActivity.this, MainActivity2.class);
                            startActivity(localIntent);
                            // overridePendingTransition(0, 0);
                            finish();
                        }
                        flag = true;

                        break;
                }

            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //不能按返回键
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }
}
