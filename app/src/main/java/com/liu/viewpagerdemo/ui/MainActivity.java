package com.liu.viewpagerdemo.ui;

import android.os.Bundle;
import android.os.Handler;
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

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ViewPager mAutoViewPager;

    private List<Integer> mDatas = new ArrayList<>();
    private MainAutoViewpagerAdapter  mMainAutoViewpagerAdapter;
    private LinearLayout              mDotsLayout;
    private LinearLayout.LayoutParams mDotParams;

    //指示器容器列表
    private List<ImageView> dots = new ArrayList<ImageView>();
    //当前索引
    private int mCurrPageIndex;

    private Handler mHandler;
    private AutoScrollTask mAutoScrollTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }


    private void initView() {
        mHandler = new Handler();
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
        mMainAutoViewpagerAdapter = new MainAutoViewpagerAdapter(this,mDatas);

        mAutoViewPager.setAdapter(mMainAutoViewpagerAdapter);
        mAutoViewPager.setPageTransformer(true,new DepthPageTransformer());

    }

    private void initDots() {
        mDotParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mDotParams.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());

        //当前的位置放大1000倍，
        mCurrPageIndex = mDatas.size()*1000;


        for(int i = 0; i < mDatas.size(); i++) {

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.dot_selector);

            imageView.setSelected(false);





            //添加到容器
            mDotsLayout.addView(imageView,mDotParams);

            dots.add(imageView);

        }
        dots.get(0).setSelected(true);

        mAutoViewPager.setCurrentItem(mCurrPageIndex);


    }

    private void initListener() {

        initViewPagerListener();
        initTask();
        initViewPagerTouchEvent();
    }




    private void initViewPagerListener() {

        mAutoViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                dots.get(mCurrPageIndex %mDatas.size()).setSelected(false);
                mCurrPageIndex =position;
                dots.get(mCurrPageIndex %mDatas.size()).setSelected(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    private void initTask() {

        mAutoScrollTask = new AutoScrollTask();
        mAutoScrollTask.start();


    }

    private void initViewPagerTouchEvent() {

        mAutoViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        //按下时--停止
                        mAutoScrollTask.stop();

                        break;

                    case MotionEvent.ACTION_MOVE:
                        //移动时
                        break;
                    case MotionEvent.ACTION_UP:
                        //抬起时-开始
                        mAutoScrollTask.start();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        mAutoScrollTask.stop();
                        break;
                    default:
                        break;
                }


                return false;
            }
        });

    }

    /**
     * 自动滚动任务
     */
    private class AutoScrollTask implements Runnable{



        //开始任务
        public void start(){

            //先移除
         stop();
            //再开始
            mHandler.postDelayed(this,5000);
        }

        @Override
        public void run() {

            // 完成viewpager的切换
            int currentItem = mAutoViewPager.getCurrentItem();
            //判断是不是最后一个
            if(currentItem==mAutoViewPager.getAdapter().getCount()-1){
                currentItem=0;
            }else {
                currentItem++;
            }
            //设置当前要显示的
            //mAutoViewPager.setCurrentItem(currentItem,true);
            mAutoViewPager.setCurrentItem(currentItem);

            start();
        }

        /**
         * 停止任务
         */
        public void stop(){

            //停止任务 不滚动
            mHandler.removeCallbacks(this);
        }
    }

}
