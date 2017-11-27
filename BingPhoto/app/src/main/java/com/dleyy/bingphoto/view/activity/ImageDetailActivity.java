package com.dleyy.bingphoto.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.dleyy.bingphoto.BaseActivity;
import com.dleyy.bingphoto.Bean.BingPhoto;
import com.dleyy.bingphoto.Contants;
import com.dleyy.bingphoto.R;
import com.dleyy.bingphoto.Utils.AboutData;
import com.dleyy.bingphoto.databinding.ActivityImageBinding;
import com.dleyy.bingphoto.view.adapter.MyPagerAdapter;
import com.dleyy.bingphoto.view.fragment.HomeFragment;
import com.dleyy.bingphoto.view.serverce.DownLoadImageService;
import com.dleyy.bingphoto.viewmodel.ImageActivityViewModel;
import com.dleyy.data.bean.BingBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dleyy on 2017/9/30.
 */
public class ImageDetailActivity extends BaseActivity<ActivityImageBinding> {

    private String TAG = "ImageDetailActivity";

    private ImageActivityViewModel viewModel;

    private ArrayList<BingBean.ListBean> imageList;

    private ViewPager viewPager;
    private PagerAdapter adapter;
    private AboutData aboutData;
    private List<BingPhoto> list = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityImageBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_image);
        setBinding(binding);
    }

    @Override
    protected void initViews() {
        aboutData = new AboutData();
        viewModel = new ImageActivityViewModel(this);
        binding.setViewModel(viewModel);
        imageList = getIntent().getParcelableArrayListExtra(Contants.KEY_BING_LIST);
        viewPager = getBinding().imagePager;
        setImageBean(imageList);
        adapter = new MyPagerAdapter(this, list);
        viewModel.setImageInfo(0, list.size(),
                list.get(0));
        viewPager.setAdapter(adapter);
        getBinding().download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.imageDownload();
            }
        });

        getBinding().backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewModel.setImageInfo(position, list.size(),
                        list.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setImageBean(ArrayList<BingBean.ListBean> listBeen) {

        for (int i = 0; i < listBeen.size(); i++) {
            BingPhoto photo = new BingPhoto();
            photo.setTitle(listBeen.get(i).getTitle());
            photo.setContent(listBeen.get(i).getContent());
            photo.setImageUrl(listBeen.get(i).get_$720x1280());
            list.add(photo);
        }
    }


}
