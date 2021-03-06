package com.dleyy.bingphoto.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dleyy.bingphoto.Bean.BingPhoto;
import com.dleyy.bingphoto.R;
import com.dleyy.bingphoto.Utils.AboutData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dleyy on 2017/10/9.
 */
public class MyPagerAdapter extends PagerAdapter {

    private List<BingPhoto> list;
    private Context context;
    private List<View> imageViews = new ArrayList<>();
    private AboutData aboutData = AboutData.getInstance();

    public MyPagerAdapter(Context context, List<BingPhoto> list) {
        this.list = list;
        this.context = context;
        initImageViews();
    }

    private void initImageViews() {
        for (int i = 0; i < list.size(); i++) {
            SimpleDraweeView imageView;
            TextView title;
            ImageView back;
            final TextView content;
            Button download;
            View v = LayoutInflater.from(context).inflate(R.layout.item_image, null, false);

            imageView = (SimpleDraweeView) v.findViewById(R.id.item_image);

            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setImageURI(list.get(i).getImageUrl());

            final int position = i;

            imageViews.add(v);
        }
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position));
    }
}
