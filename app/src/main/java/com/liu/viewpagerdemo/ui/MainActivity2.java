package com.liu.viewpagerdemo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.liu.viewpagerdemo.R;
import com.liu.viewpagerdemo.adapter.MainAutoViewpagerAdapter;
import com.liu.viewpagerdemo.view.DepthPageTransformer;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class MainActivity2 extends AppCompatActivity {

    private ViewPager mAutoViewPager;

    private List<Integer> mDatas = new ArrayList<>();
    private MainAutoViewpagerAdapter  mMainAutoViewpagerAdapter;
    private LinearLayout              mDotsLayout;
    private LinearLayout.LayoutParams mDotParams;

    //指示器容器列表
    private List<ImageView> dots = new ArrayList<ImageView>();
    //当前索引
    private int mCurrPageIndex;

    private MyHandler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }


    private void initView() {
        mHandler = new MyHandler(this);
        mAutoViewPager = (ViewPager) findViewById(R.id.viewPagerAuto);
        mDotsLayout = (LinearLayout) findViewById(R.id.dotsLayout);

    }

    private void initData() {
        //添加图片资源
        mDatas.add(R.mipmap.img_home_banner1);
        mDatas.add(R.mipmap.img_home_banner2);
        mDatas.add(R.mipmap.img_home_banner3);
        mDatas.add(R.mipmap.img_home_banner4);
        //初始化ViewPager
        initViewPager();
        initDots();

    }


    private void initViewPager() {
        mMainAutoViewpagerAdapter = new MainAutoViewpagerAdapter(this, mDatas);

        mAutoViewPager.setAdapter(mMainAutoViewpagerAdapter);
        mAutoViewPager.setPageTransformer(true, new DepthPageTransformer());

    }

    private void initDots() {
        mDotParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mDotParams.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());

        //当前的位置放大1000倍，
        mCurrPageIndex = mDatas.size() * 1000;


        for(int i = 0; i < mDatas.size(); i++) {

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.dot_selector);

            imageView.setSelected(false);


            //添加到容器
            mDotsLayout.addView(imageView, mDotParams);

            dots.add(imageView);

        }
        dots.get(0).setSelected(true);

        mAutoViewPager.setCurrentItem(mCurrPageIndex);


    }

    private void initListener() {

        initViewPagerListener();

        initViewPagerTouchEvent();
    }


    private void initViewPagerListener() {

        mAutoViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                dots.get(mCurrPageIndex % mDatas.size()).setSelected(false);
                mCurrPageIndex = position;
                dots.get(mCurrPageIndex % mDatas.size()).setSelected(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private void initViewPagerTouchEvent() {

        mAutoViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按下时--停止

                        stopScroll();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        //移动时
                        break;
                    case MotionEvent.ACTION_UP:
                        //抬起时-开始
                        startAutoScroll();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        startAutoScroll();
                        break;
                    default:
                        break;
                }


                return false;
            }
        });

    }


    /**
     * 静态内部类
     * 防止内在泄漏
     */
    private static class MyHandler extends Handler {


        WeakReference<MainActivity2> mWeakReference;

        public MyHandler(MainActivity2 activity2) {
            mWeakReference = new WeakReference<MainActivity2>(activity2);
        }

        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            if(msg.what != 1) {
                return;
            }

            MainActivity2 mainActivity2 = mWeakReference.get();


            int currentItem = mainActivity2.mAutoViewPager.getCurrentItem();

            if(currentItem == mainActivity2.mAutoViewPager.getAdapter().getCount() - 1) {
                currentItem = 0;
            } else {
                currentItem++;
            }

            mainActivity2.mAutoViewPager.setCurrentItem(currentItem);

            //循环发送
            Message msg2 = Message.obtain();
            msg2.what = 1;
            sendMessageDelayed(msg2, 5000);

        }
    }

    /**
     * 开始播放
     */
    public void startAutoScroll() {
        Message msg = Message.obtain();
        msg.what = 1;
        mHandler.sendMessageDelayed(msg, 5000);
    }

    /**
     * 停止播放
     */
    public void stopScroll() {
        // 清除所有消息 handleMessage就不能执行
        mHandler.removeCallbacksAndMessages(null);//

    }

    @Override
    protected void onStart() {
        super.onStart();
        //可见时就循环播放
        startAutoScroll();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //不可见时就停止播放
        stopScroll();
    }
}
