package com.dleyy.bingphoto.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.dleyy.bingphoto.BaseActivity;
import com.dleyy.bingphoto.R;
import com.dleyy.bingphoto.databinding.ActivityMainBinding;
import com.dleyy.bingphoto.view.fragment.HomeFragment;
import com.dleyy.bingphoto.view.fragment.MineFragment;


import java.util.ArrayList;


public class MainActivity extends BaseActivity<ActivityMainBinding> {
    String TAG = "MainActivity";

    private FragmentPagerAdapter adapter;
    private ArrayList<Fragment> list = new ArrayList<>();
    private ViewPager viewPager;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        setBinding(binding);
    }

    @Override
    protected void initViews() {
        viewPager = getBinding().myPage;
        HomeFragment homeFragment = HomeFragment.newInstance(getPhotoPixel());
        MineFragment mine = new MineFragment();
        list.add(homeFragment);
        list.add(mine);
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };
        viewPager.setAdapter(adapter);
    }


}
