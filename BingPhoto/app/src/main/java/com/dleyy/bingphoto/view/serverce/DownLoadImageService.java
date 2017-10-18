package com.dleyy.bingphoto.view.serverce;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dleyy.bingphoto.Utils.NotificationUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import me.jessyan.progressmanager.ProgressListener;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.progressmanager.body.ProgressInfo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by dleyy on 2017/10/17.
 */
public class DownLoadImageService extends IntentService {

    private static String TAG = "DownLoadImageService";

    private static Context mContext;

    public static NotificationUtil notificationUtil;

    private int ID_NOTIFICATION_ID = 1;

    private static final String ACTION_DOWNLOADIMAGE =
            "com.dleyy.bingphoto.view.serverce.DownLoadImage.action.ACTION_DOWNLOADIMAGE";
    private static final String EXTRA_IMAGE_PATH =
            "com.dleyy.bingphoto.view.serverce.DownLoadImage.extra.EXTRA_IMAGE_PATH";
    private static final String EXTRA_IMAGE_URL =
            "com.dleyy.bingphoto.view.serverce.DownLoadImage.string.EXTRA_IMAGE_URL";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */

    public DownLoadImageService() {
        super("DownLoadImageService");
    }

    public static void downloadImage(final Context context,
                                     final String imagePath,
                                     final String imageUrl) {
        mContext = context;
        notificationUtil = new NotificationUtil(context);
        Intent intent = new Intent(context, DownLoadImageService.class);
        intent.setAction(ACTION_DOWNLOADIMAGE);
        intent.putExtra(EXTRA_IMAGE_PATH, imagePath);
        intent.putExtra(EXTRA_IMAGE_URL, imageUrl);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e(TAG, "onHandleIntent:" + (intent == null) + "  " + intent);
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DOWNLOADIMAGE.equals(action)) {
                final String path = intent.getStringExtra(EXTRA_IMAGE_PATH);
                final String url = intent.getStringExtra(EXTRA_IMAGE_URL);
                downLoadBingImage(url, path);
            }
        }
    }
    
    public void downLoadBingImage(final String url, final String path) {
        OkHttpClient client = ProgressManager.getInstance()
                .with(new OkHttpClient.Builder()).build();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        notificationUtil.showNotification(ID_NOTIFICATION_ID);
        ProgressManager.getInstance().addRequestListener(url, new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                long currentPercent = progressInfo.getCurrentbytes() / progressInfo.getContentLength();
                int nowProgress = Integer.parseInt(currentPercent + "");
                notificationUtil.changeNotificationProgress(
                        ID_NOTIFICATION_ID, nowProgress);
            }

            @Override
            public void onError(long id, Exception e) {
                notificationUtil.cancleNotification(ID_NOTIFICATION_ID);
            }
        });
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                fileOutputStream = new FileOutputStream(path);
                byte[] buffer = new byte[2048];
                int length = 0;
                while ((length = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, length);
                }
                fileOutputStream.flush();
            }
        });

    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
