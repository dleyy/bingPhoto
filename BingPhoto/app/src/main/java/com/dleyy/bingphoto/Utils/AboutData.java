package com.dleyy.bingphoto.Utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.dleyy.bingphoto.R;
import com.dleyy.data.request.DownLoadImageRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by dleyy on 2017/9/27.
 */
public class AboutData {
    private String TAG = "AboutData";
    private static AboutData aboutData;
    private DownLoadImageRequest downLoadImageRequest = new DownLoadImageRequest();

    public static AboutData getInstance() {
        if (null == aboutData) {
            aboutData = new AboutData();
        }
        return aboutData;
    }

    public String getList(String s) {
        String list = null;
        try {
            JSONObject object = new JSONObject(s.toString());
            if (object.getInt("showapi_res_code") == 0) {
                JSONObject body = object.optJSONObject("showapi_res_body");
                list = body.getJSONArray("list").toString();
            }
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 输出正确的图片地址，由于Api原因，部分链接不能访问。
     *
     * @param nowPhoneImage 原始图片地址
     * @param correctKey    正确的图片分辨率
     * @return 正确的图片地址
     */
    public String getCorrectPhoneImage(String nowPhoneImage, String correctKey) {
        int keyLength = correctKey.length() + 4;
        correctKey = correctKey.replace("*", "x");
        return (nowPhoneImage.substring(0, nowPhoneImage.length() - keyLength) + correctKey + ".jpg");
    }

    //下载图片
    public void downLoadImages(String url, String imageName, Context context) {
        String imageRoot = creatImagePath();
        if (!imageRoot.equals(null)) {
            String path = imageRoot + "/" + imageName + ".png";
            downLoadImageRequest.downLoadImage(context,url, path);
        } else {
            Toast.makeText(context,context.getString(R.string.down_load_error_message), Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 检查SD卡是否可用 MEDIA_MOUNTED即代表可用。
     *
     * @return
     */
    public static boolean checkSDCard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取sd卡的文件路径
     * getExternalStorageDirectory 获取路径
     *
     * @return
     */
    public static String getSdPath() {
        return Environment.getExternalStorageDirectory() + "/";
    }

    /**
     * 创建文件夹
     *
     * @param fileDir
     */
    public static String createFileDir(String fileDir) {
        if (checkSDCard()) {
            String path = getSdPath() + fileDir;
            File path1 = new File(path);
            if (!path1.exists()) {
                path1.mkdirs();
            }
            return path;
        }
        return null;
    }

    /**
     * 创建图片下载地址.
     *
     * @return
     */
    public static String creatImagePath() {
        return createFileDir("outWindow/Image");
    }

}
