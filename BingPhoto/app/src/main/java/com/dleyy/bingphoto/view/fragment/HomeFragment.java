package com.dleyy.bingphoto.view.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dleyy.bingphoto.Bean.PhonePixel;
import com.dleyy.bingphoto.Contants;
import com.dleyy.bingphoto.R;
import com.dleyy.bingphoto.Utils.AboutData;
import com.dleyy.bingphoto.databinding.FragmentHomeBinding;
import com.dleyy.bingphoto.view.activity.ImageDetailActivity;
import com.dleyy.bingphoto.viewmodel.HomeFragmentViewModel;
import com.dleyy.data.net.DefaultObserver;
import com.dleyy.data.request.BingRequest;
import com.dleyy.data.response.BingResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dleyy on 2017/9/27.
 */
public class HomeFragment extends Fragment {

    private String TAG = "HomeFragment";

    private FragmentHomeBinding binding;
    private HomeFragmentViewModel viewModel;
    private BingResponse response;
    private BingRequest request;
    private ArrayList<String> myPhoneList;
    private AboutData aboutData;
    private Bundle bd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        viewModel = new HomeFragmentViewModel();
        response = new BingResponse();
        request = new BingRequest();
        bd = getArguments();
        myPhoneList = bd.getStringArrayList(Contants.KEY_PHONE_DISMITION);
        aboutData = AboutData.getInstance();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initImages();

    }

    private void initImages() {
        request.BingExcute(new DefaultObserver<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
                binding.bingPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in = new Intent();
                        bd.putString(Contants.KEY_BING_LIST, response.getResult());
                        in.putExtra(Contants.KEY_INTENT_BUNDLE, bd);
                        in.setClass(getActivity(), ImageDetailActivity.class);
                        startActivity(in);
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                response.setResult(aboutData.getList(s));
                ShowImage(response.getResult());
            }
        });
    }

    private void ShowImage(String jsonArray) {
        try {
            JSONArray array = new JSONArray(jsonArray);
            if (array.length() > 0) {
                JSONObject object = array.getJSONObject(0);
                Log.e(TAG, "ShowImage: JSONOBject" + object.toString());
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
                Log.e(TAG, "initImages:33" + nowPhoneImage.isEmpty());
                Log.e(TAG, "initImages:11" + nowPhoneImage + "  " + defaultImage);
                if (!nowPhoneImage.isEmpty()) {
                    binding.bingPhoto.setImageURI(nowPhoneImage);
                } else {
                    binding.bingPhoto.setImageURI(defaultImage);
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "initImages: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static HomeFragment newInstance(ArrayList<String> list) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bd = new Bundle();
        bd.putStringArrayList(Contants.KEY_PHONE_DISMITION, list);

        homeFragment.setArguments(bd);
        return homeFragment;
    }
}
