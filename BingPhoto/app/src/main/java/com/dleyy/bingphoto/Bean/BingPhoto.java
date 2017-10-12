package com.dleyy.bingphoto.Bean;

/**
 * Created by dleyy on 2017/10/9.
 */
public class BingPhoto {
    private String title;
    private String content;
    private String imageUrl;
    private boolean showInfo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isShowInfo() {
        return showInfo;
    }

    public void setShowInfo(boolean showInfo) {
        this.showInfo = showInfo;
    }

    @Override
    public String toString() {
        return "title:" + title +
                "、content" + content +
                "、imageUrl" + imageUrl;
    }
}
