package com.dleyy.bingphoto.Utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 图片缓存类
 * Created by dleyy on 2017/10/30.
 */
public class Cache {
    private static int MAX_MEMORY = (int) (Runtime.getRuntime().maxMemory() / 1024);
    int cacheSize = MAX_MEMORY / 8;
    private LruCache<String, Bitmap> cache;

    public Cache() {
        cache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }


    public void addBitmap(String key, Bitmap bitmap) {
        if (getBitmap(key) == null) {
            cache.put(key, bitmap);
        }
    }

    public Bitmap getBitmap(String key) {
        return cache.get(key);
    }
}
