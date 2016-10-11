package com.liu.viewpagerdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.liu.viewpagerdemo.fragment.CategoryFragment;
import com.liu.viewpagerdemo.fragment.HomeFragment;
import com.liu.viewpagerdemo.fragment.MessageFragment;
import com.liu.viewpagerdemo.fragment.MineFragment;
import com.liu.viewpagerdemo.fragment.ShopcartFragment;
import com.liu.viewpagerdemo.fragment.Test1Fragment;
import com.liu.viewpagerdemo.fragment.Test2Fragment;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/10/11 0011 11:04
 */
public class MyViewpagerAdapter extends FragmentStatePagerAdapter {

    String[] mTabTitles;


    public MyViewpagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        mTabTitles = titles;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch(position) {
            case 0:
                fragment = HomeFragment.newInstance(position);
                break;
            case 1:
                fragment = CategoryFragment.newInstance(position);
                break;
            case 2:
                fragment = ShopcartFragment.newInstance(position);
                break;
            case 3:
                fragment = MessageFragment.newInstance(position);
                break;
            case 4:
                fragment = MineFragment.newInstance(position);
                break;
            case 5:
                fragment = Test1Fragment.newInstance(position);
                break;
            case 6:
                fragment = Test2Fragment.newInstance(position);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mTabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return mTabTitles[position];
    }
}
