package com.liu.viewpagerdemo.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.viewpagerdemo.R;
import com.liu.viewpagerdemo.base.BaseFragment;


public class MessageFragment extends BaseFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private TextView mTv;


    public MessageFragment() {
    }


    public static MessageFragment newInstance(int sectionNumber) {
        MessageFragment fragment = new MessageFragment();
        Bundle          args     = new Bundle();
        args.putInt(ARG_SECTION_NUMBER,sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View bindLayout(LayoutInflater inflater) {
        mRootView = inflater.inflate(R.layout.fragment_message,null);

        return mRootView;
    }
    @Override
    public void initView() {
        mTv = (TextView) mRootView.findViewById(R.id.tv);
    }

    @Override
    protected void initData() {
        Toast.makeText(mContext, "消息加载新数据", Toast.LENGTH_SHORT).show();
        mTv.setText("消息加载新数据");
    }
}
