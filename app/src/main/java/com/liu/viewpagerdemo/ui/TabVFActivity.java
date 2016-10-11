package com.liu.viewpagerdemo.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.liu.viewpagerdemo.R;
import com.liu.viewpagerdemo.adapter.MyViewpagerAdapter;
import com.liu.viewpagerdemo.base.BaseFragment;
import com.liu.viewpagerdemo.fragment.CategoryFragment;
import com.liu.viewpagerdemo.fragment.HomeFragment;
import com.liu.viewpagerdemo.fragment.MessageFragment;
import com.liu.viewpagerdemo.fragment.MineFragment;
import com.liu.viewpagerdemo.fragment.ShopcartFragment;
import com.liu.viewpagerdemo.fragment.Test1Fragment;
import com.liu.viewpagerdemo.fragment.Test2Fragment;

public class TabVFActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    /**
     * 标题容器
     */
    private String[] mTabTitles = new String[]{};

    private BaseFragment[]  mFragments;
    private MyViewpagerAdapter mMyViewpagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_vf);

        initView();
        initDatas();
        initListener();
    }

    private void initView() {

        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

    }

    private void initDatas() {

        //为标题赋值
        mTabTitles = getResources().getStringArray(R.array.tabTitle);
        mFragments = new BaseFragment[]{new HomeFragment(), new CategoryFragment(), new ShopcartFragment(), new MessageFragment(), new MineFragment(),new Test1Fragment(),new Test2Fragment()};


        setTabAndViewPager();

    }

    private void setTabAndViewPager() {


        mMyViewpagerAdapter = new MyViewpagerAdapter(getSupportFragmentManager(),mTabTitles);

        mViewPager.setAdapter(mMyViewpagerAdapter);

        mViewPager.setOffscreenPageLimit(0);





        mTabLayout.setupWithViewPager(mViewPager,true);



    }

    private void initListener() {

    }
}
