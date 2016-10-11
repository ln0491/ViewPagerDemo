package com.liu.viewpagerdemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.viewpagerdemo.R;
import com.liu.viewpagerdemo.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    private static final String ARG_SECTION_NUMBER = "section_number";
    private TextView mTv;

    @Override
    public View bindLayout(LayoutInflater inflater) {

        mRootView = inflater.inflate(R.layout.fragment_home,null);

        return mRootView;
    }


    public HomeFragment() {

    }



    public static HomeFragment newInstance(int sectionNumber) {
        HomeFragment fragment = new HomeFragment();
        Bundle                     args     = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView() {

        mTv = (TextView) mRootView.findViewById(R.id.tv);
    }

    @Override
    protected void initData() {

        Toast.makeText(mContext, "主页加载新数据", Toast.LENGTH_SHORT).show();
        mTv.setText("主页加载新数据");

    }
}
