package com.dleyy.bingphoto.viewmodel;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.dleyy.bingphoto.Bean.BingPhoto;
import com.dleyy.bingphoto.Utils.AboutData;
import com.dleyy.bingphoto.view.serverce.DownLoadImageService;

/**
 * Created by dleyy on 2017/10/9.
 */
public class ImageActivityViewModel {

    public ObservableField<String> nowPrecent = new ObservableField<>();

    public ObservableField<String> nowText = new ObservableField<>();

    private String imageUrl;

    private Context context;

    public ImageActivityViewModel(Context context) {
        this.context = context;
    }

    public ObservableField<String> getNowPrecent() {
        return nowPrecent;
    }

    public void setNowPrecent(String nowPrecent) {
        this.nowPrecent.set(nowPrecent);
    }

    public ObservableField<String> getNowText() {
        return nowText;
    }

    public void setNowText(String nowText) {
        this.nowText.set(nowText);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImageInfo(int currentPage, int totalSize, BingPhoto bingPhoto) {
        String nowPrecent = (currentPage + 1) + "/" + totalSize;
        setNowPrecent(nowPrecent);
        setNowText(bingPhoto.getTitle());
        setImageUrl(bingPhoto.getImageUrl());
    }

    public void imageDownload() {
        AboutData aboutData = new AboutData();
        aboutData.downLoadImages(imageUrl, nowText.get().substring(0, 5), context);
    }
}
