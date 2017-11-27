package com.dleyy.bingphoto.view.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.dleyy.data.Exception.ExceptionHandler;
import com.dleyy.data.bean.BingBean;
import com.dleyy.data.net.DefaultObserver;
import com.dleyy.data.request.BingRequest;
import com.dleyy.data.response.BaseApiResponse;
import com.dleyy.data.response.BingResponse;
import com.google.gson.Gson;

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
    private AboutData aboutData;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        viewModel = new HomeFragmentViewModel();
        response = new BingResponse(getActivity());
        request = new BingRequest();

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

                        in.putParcelableArrayListExtra(Contants.KEY_BING_LIST,
                                response.getResBody().getList());

                        in.setClass(getActivity(), ImageDetailActivity.class);
                        startActivity(in);
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                ExceptionHandler.getInstance(getActivity())
                        .handleException(e.getMessage());
            }

            @Override
            public void onNext(String s) {

                Gson gson = new Gson();
                response = gson.fromJson(s, BingResponse.class);

                if (response.isPostSuccess()) {
                    ShowImage(response.getResBody().getList());
                }
            }
        });
    }

    private void ShowImage(List<BingBean.ListBean> listBeen) {

        String defaultImage = listBeen.get(0).get_$800x600();
        String nowPhoneImage = listBeen.get(0).get_$_19201080197();
        nowPhoneImage = nowPhoneImage.replace('*', 'x');

        if (!nowPhoneImage.isEmpty()) {
            binding.bingPhoto.setImageURI(nowPhoneImage);
        } else {
            binding.bingPhoto.setImageURI(defaultImage);
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
