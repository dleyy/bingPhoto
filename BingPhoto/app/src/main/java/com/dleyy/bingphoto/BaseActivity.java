package com.dleyy.bingphoto;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.GridLayout;

import com.dleyy.bingphoto.Bean.PhonePixel;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dleyy on 2017/9/26.
 */
public class BaseActivity<T> extends AppCompatActivity {

    private DisplayMetrics displayMetrics = new DisplayMetrics();
    private int widthPixels;
    private int heightPixels;
    protected T binding;


    /**
     * 获取当前屏幕分辨率
     */
    public ArrayList<String> getPhotoPixel() {
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthPixels = displayMetrics.widthPixels;
        heightPixels = displayMetrics.heightPixels;
        return new PhonePixel(heightPixels,widthPixels).getPhonePixelList();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                initViews();
                return false;
            }
        });
    }


    protected void initViews() {

    }

    protected void setBinding(T dataBinding) {
        binding = dataBinding;
    }

    protected T getBinding() {
        if (null != binding) {
            return binding;
        } else {
            throw new NullPointerException("should setBinding fist");
        }
    }

}
