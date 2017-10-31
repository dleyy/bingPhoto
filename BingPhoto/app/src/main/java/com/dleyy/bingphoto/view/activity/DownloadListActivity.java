package com.dleyy.bingphoto.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.dleyy.bingphoto.BaseActivity;
import com.dleyy.bingphoto.Bean.DownloadedImage;
import com.dleyy.bingphoto.R;
import com.dleyy.bingphoto.databinding.ActivityDownloadListBinding;
import com.dleyy.bingphoto.view.adapter.DownLoadListAdapter;
import com.dleyy.bingphoto.viewmodel.DownloadListViewModel;
import com.dleyy.data.UseCase.GetDownloadFileUseCase;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.io.File;

import rx.Subscriber;

/**
 * Created by dleyy on 2017/10/18.
 */
public class DownloadListActivity extends BaseActivity<ActivityDownloadListBinding> {

    private static String TAG = "DownloadListActivity";

    private ActivityDownloadListBinding binding;
    private DownloadListViewModel viewModel;
    private EasyRecyclerView recyclerView;
    private DownLoadListAdapter adapter;
    private GetDownloadFileUseCase useCase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_download_list);
        setBinding(binding);

    }

    /**
     * 在调用GridLayout的时候，滑动会出现卡顿。
     * 换成LinearLayout时，不存在这个问题。
     * 应该是使用GridLayout导致获取不到正确的图片大小，从而在测量图片宽度时消耗过多的时间。
     * 暂时先采用LinearLayout.
     */
    @Override
    protected void initViews() {
        viewModel = new DownloadListViewModel();
        getBinding().setViewModel(viewModel);
        recyclerView = binding.downloadList;
//        recyclerView.setLayoutManager(new GridLayoutManager(DownloadListActivity.this, 2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DownLoadListAdapter(this);
        useCase = new GetDownloadFileUseCase();
        useCase.execute(new Subscriber<File>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(File file) {
                DownloadedImage image = new DownloadedImage();
                image.setImagePath(file.getPath());
                image.setImageName(file.getName());
                image.setImageTime(file.lastModified());
                adapter.add(image);
            }
        });
        recyclerView.setAdapter(adapter);
    }

}
