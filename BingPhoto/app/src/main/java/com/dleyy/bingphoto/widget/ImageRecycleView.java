package com.dleyy.bingphoto.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.jude.easyrecyclerview.EasyRecyclerView;

/**
 * Created by dleyy on 2017/10/27.
 */
public class ImageRecycleView extends EasyRecyclerView {

    private RecyclerView.OnScrollListener listener;

    private Context context;

    private boolean loadData = true;

    public ImageRecycleView(Context context) {
        this(context, null);
    }

    public ImageRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }


    private void initListener() {
        listener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCREEN_STATE_ON) {
                    stopLoadData();
                } else {
                    startLoadData();
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }


        };
    }

    public void startLoadData() {
        loadData = true;
    }

    public void stopLoadData() {
        loadData = false;
    }
}
