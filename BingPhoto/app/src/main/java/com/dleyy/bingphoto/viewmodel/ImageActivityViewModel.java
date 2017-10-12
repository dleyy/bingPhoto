package com.dleyy.bingphoto.viewmodel;

import android.databinding.ObservableBoolean;

/**
 * Created by dleyy on 2017/10/9.
 */
public class ImageActivityViewModel {
    private ObservableBoolean showInfo = new ObservableBoolean();

    public boolean getShowInfo() {
        return showInfo.get();
    }

    public void setShowInfo(boolean showInfo) {
        this.showInfo.set(showInfo);
    }

    public void downLoadImage(String url){

    }
}
