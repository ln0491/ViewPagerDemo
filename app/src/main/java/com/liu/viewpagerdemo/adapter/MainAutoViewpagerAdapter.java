package com.liu.viewpagerdemo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * @Description: 广告viewpager适配器
 * @AUTHOR 刘楠  Create By 2016/10/8 0008 11:18
 */
public class MainAutoViewpagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<Integer> mDatas;

    public MainAutoViewpagerAdapter(Context context, List<Integer> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public int getCount() {
       // return mDatas.size();
       // return Integer.MAX_VALUE;
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(mContext);



        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setTag(position);
        //最余不然会下越界
        imageView.setImageResource(mDatas.get(position%mDatas.size()));

        container.addView(imageView);


        return imageView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
