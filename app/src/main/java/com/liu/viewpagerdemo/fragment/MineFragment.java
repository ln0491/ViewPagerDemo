package com.liu.viewpagerdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.viewpagerdemo.R;
import com.liu.viewpagerdemo.base.BaseFragment;

public class MineFragment extends BaseFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private TextView mTv;

    public MineFragment() {
        // Required empty public constructor
    }


    public static MineFragment newInstance(int sectionNumber) {
        MineFragment fragment = new MineFragment();
        Bundle       args     = new Bundle();
       args.putInt(ARG_SECTION_NUMBER,sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View bindLayout(LayoutInflater inflater) {

        return inflater.inflate(R.layout.fragment_mine,null);
    }

    @Override
    public void initView() {
        mTv = (TextView) mRootView.findViewById(R.id.tv);
    }

    @Override
    protected void initData() {
        Toast.makeText(mContext, "我的加载新数据", Toast.LENGTH_SHORT).show();
        mTv.setText("我的加载新数据");
    }
}
