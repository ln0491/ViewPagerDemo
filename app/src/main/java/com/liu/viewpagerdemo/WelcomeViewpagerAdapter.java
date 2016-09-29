package com.liu.viewpagerdemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/9/29 0029 10:56
 */
public class WelcomeViewpagerAdapter extends PagerAdapter {
    //数据
    List<Integer> mDatas;
    Context mContext;

    public WelcomeViewpagerAdapter(Context context, List<Integer> datas) {
        mContext= context;
        mDatas = datas;
    }


    /**
     * 返回要滑动的VIew的个数
     * @return
     */
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    /**
     * 做了两件事，第一：将当前视图添加到container中，第二：返回当前View
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      //  View view = View.inflate(mContext,R.layout.item_pager,null);
       // ImageView imageView = (ImageView) view.findViewById(R.id.ivDispaly);

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mDatas.get(position));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //添加View到容器
        container.addView(imageView);

        //返回当前的View
        return imageView;
        //        return super.instantiateItem(container, position);
    }

    /**
     * 从当前container中删除指定位置（position）的View;
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       // super.destroyItem(container, position, object);

        container.removeView((View) object);
    }
}
