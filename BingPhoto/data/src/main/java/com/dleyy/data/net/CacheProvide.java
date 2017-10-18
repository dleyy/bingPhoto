package com.dleyy.data.net;

import android.content.Context;

import okhttp3.Cache;

/**
 * 配置缓存路径
 * Created by dleyy on 2017/10/18.
 */
public class CacheProvide {

    /**
     * 缓存大小 10M;
     */
    private int CACHESIZE = 10240 * 1024;

    private Context mContext;

    public CacheProvide(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 使用App的缓存路径
     * @return
     */
    public Cache provideCache(){
        return new Cache(mContext.getCacheDir(),CACHESIZE);
    }
}
