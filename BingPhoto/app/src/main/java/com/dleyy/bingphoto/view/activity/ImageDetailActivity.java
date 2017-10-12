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
import com.dleyy.bingphoto.viewmodel.ImageActivityViewModel;

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
    /**
     * 本机分辨率
     */
    private ArrayList<String> myPhoneList;
    /**
     * 必应图片信息
     */
    private String photoString;

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
        viewModel = new ImageActivityViewModel();
        Bundle bd = getIntent().getBundleExtra(Contants.KEY_INTENT_BUNDLE);
        myPhoneList = bd.getStringArrayList(Contants.KEY_PHONE_DISMITION);
        photoString = bd.getString(Contants.KEY_BING_LIST);
        viewPager = getBinding().imagePager;
        setImageBean(photoString);
        adapter = new MyPagerAdapter(this, list);
        viewPager.setAdapter(adapter);

    }

    private void setImageBean(String jsonArray) {
        try {
            JSONArray array = new JSONArray(jsonArray);
            if (array.length() > 0) {
                for (int j = 0; j < array.length(); j++) {
                    JSONObject object = array.getJSONObject(j);
                    String defaultImage = object.optString("800x600");
                    String nowPhoneImage = null;
                    String nowKey = null;
                    for (int i = 0; i < myPhoneList.size(); i++) {
                        if (!object.optString(myPhoneList.get(i)).isEmpty()) {
                            nowPhoneImage = object.optString(myPhoneList.get(i));
                            nowKey = myPhoneList.get(i);
                            break;
                        }
                    }
                    nowPhoneImage = aboutData.getCorrectPhoneImage(nowPhoneImage, nowKey);
                    BingPhoto photo = new BingPhoto();
                    photo.setTitle(object.optString("title"));
                    photo.setContent(object.optString("content"));
                    photo.setImageUrl(nowPhoneImage == null ? defaultImage : nowPhoneImage);
                    Log.d(TAG, "setImageBean:" + photo.toString());
                    list.add(photo);
                }
            } else {
                list = null;
            }
        } catch (JSONException e) {
            Log.e(TAG, "initImages: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
