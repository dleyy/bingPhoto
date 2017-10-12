package com.dleyy.data.request;

import android.content.Context;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dleyy on 2017/10/10.
 */
public class DownLoadImageRequest{
    private String TAG = "DownLoadImageRequest";


    /**
     *
     * @param imageUrl 图片地址
     * @param filePath 缓存的图片路径，形如：out/Image/a.png;
     */
    public void downLoadImage(final Context context, String imageUrl, final String filePath){
        OkHttpClient client = new OkHttpClient();
//        RequestBody requestBody = new FormBody.Builder()
//                .add(key,value)
//                .build();
        final Request request = new Request.Builder()
                .url(imageUrl)
//                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.cancel();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                fileOutputStream = new FileOutputStream(filePath);
                byte[] buffer = new byte[2048];
                int length = 0;
                while ((length=inputStream.read(buffer))!=-1){
                    fileOutputStream.write(buffer,0,length);
                }
                fileOutputStream.flush();

            }
        });
        
    }
}
