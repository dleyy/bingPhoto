package com.dleyy.data.request;

import android.os.Environment;

import java.io.File;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dleyy on 2017/10/25.
 * 获取本地已经下载好的图片
 */
public class GetDownloadImageRequest extends BaseApiRequest {

    private final static String TAG = "GetDownloadImageRequest";

    private String imagePath = Environment.getExternalStorageDirectory() + "/Window";

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    /**
     * 获取该路径下的所有文件的集合。
     *
     * @return
     */
    public File[] getDownloadPath() {
        File file = new File(imagePath);
        if (file.exists()) {
            return file.listFiles();
        } else {
            return null;
        }
    }
}
