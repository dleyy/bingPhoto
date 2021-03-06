package com.dleyy.bingphoto.Utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.RemoteViews;

import com.dleyy.bingphoto.R;
import com.dleyy.bingphoto.view.activity.DownloadListActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Notification 工具类
 * Created by dleyy on 2017/10/18.
 */
public class NotificationUtil {

    private static final String TAG = "NotificationUtil";

    private int FLAG_PENDINGINTENT = 0;
    private static int FINISHED_NOTIFICATION_ID = 1000;

    private Context context;
    private Map<Integer, Notification> map = null;
    private NotificationManager manager;

    public NotificationUtil(Context context) {
        this.context = context;
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        map = new HashMap<>();
    }

    public void showNotification(int notificationId) {
        if (!map.containsKey(notificationId)) {
            Notification notification = new Notification();
            notification.when = System.currentTimeMillis();
            //设置点击通知栏自动消失。
            notification.flags = Notification.FLAG_AUTO_CANCEL;
            Intent intent = new Intent(context, DownloadListActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context, 0, intent, FLAG_PENDINGINTENT);
            notification.contentIntent = pendingIntent;
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.notification_view);
            remoteViews.setImageViewResource(R.id.download_image, R.drawable.download);
            remoteViews.setProgressBar(R.id.progress_bar, 100, 0, false);
            remoteViews.setOnClickPendingIntent(R.layout.notification_view, pendingIntent);
            notification.contentView = remoteViews;
            notification.icon = R.drawable.ic_bck;
            manager.notify(notificationId, notification);
            map.put(notificationId, notification);
        }
    }

    /**
     * 取消通知
     *
     * @param notificationId
     */
    public void cancleNotification(int notificationId) {
        manager.cancel(notificationId);
        map.remove(notificationId);
    }

    /**
     * 修改通知的进度
     *
     * @param notificationID
     * @param progress
     */
    public synchronized void changeNotificationProgress(int notificationID, int progress) {
        Log.e("DDDD", "changeNotificationProgress: " + notificationID + "  " + progress);
        Notification notification = map.get(notificationID);
        if (null != notification) {
            notification.contentView.setProgressBar(R.id.progress_bar, 100, progress, false);
            manager.notify(notificationID, notification);
            if (progress == 100) {
                notification.contentView.setImageViewResource(R.id.download_image, R.drawable.downloaded);
                notification.contentView.setTextViewText(R.id.download_title, "下载完成");
                notification.flags = Notification.FLAG_AUTO_CANCEL;
            }
        } else {
            throw new RuntimeException("for notificationID" + notification + " didn't exit");
        }
    }

}
