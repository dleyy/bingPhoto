package com.dleyy.bingphoto.Utils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.dleyy.bingphoto.R;

import java.io.File;

import javax.security.auth.login.LoginException;

/**
 * Created by dleyy on 2017/10/31.
 */
public class LoadImage {

    private static String TAG = "LoadImage";

    private static Context mContext;
    private static LoadImage loadImage;

    public static LoadImage getInstance(Context context) {
        if (null == mContext) {
            mContext = context;
        }
        if (null == loadImage) {
            loadImage = new LoadImage();
        }
        return loadImage;
    }

    /**
     * 异步开启线程并加载图片，
     *
     * @param url
     * @param imageView
     */
    public synchronized void loadImageFromFile(final String url, final ImageView imageView) {
        final File file = new File(url);
        if (file.exists()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
                }
            });
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    imageView.setBackground(mContext.getDrawable(R.drawable.ic_load_img_fail));
                }
            });
        }
    }

}
