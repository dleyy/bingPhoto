package com.dleyy.data.net;

/**
 * Created by dleyy on 2017/10/18.
 */
public interface ProgressResponseListener {
    void onResponseProgress(long byteRead, long contentLength, boolean done);
}
