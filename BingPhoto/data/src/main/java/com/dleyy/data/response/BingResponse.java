package com.dleyy.data.response;

import android.content.Context;

import javax.security.auth.callback.Callback;

/**
 * Created by dleyy on 2017/9/26.
 */
public class BingResponse extends BaseApiResponse {

    @Override
    public void setResult(String requestResult) {
        super.setResult(requestResult);
    }

    @Override
    public String getResult() {
        return result;
    }
}
