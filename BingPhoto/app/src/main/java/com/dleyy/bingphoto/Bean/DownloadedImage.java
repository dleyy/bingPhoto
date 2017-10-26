package com.dleyy.bingphoto.Bean;

/**
 * Created by dleyy on 2017/10/25.
 */
public class DownloadedImage {

    /**
     * 图片名称
     */
    private String imageName;

    /**
     * 图片下载时间
     */
    private long imageTime;

    /**
     * 图片存储地址
     */
    private String imagePath;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public long getImageTime() {
        return imageTime;
    }

    public void setImageTime(long imageTime) {
        this.imageTime = imageTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "DownloadedImage{" +
                "imageName='" + imageName + '\'' +
                ", imageTime='" + imageTime + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
