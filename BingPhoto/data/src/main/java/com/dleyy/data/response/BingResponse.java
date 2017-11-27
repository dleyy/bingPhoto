package com.dleyy.data.response;

import android.content.Context;

import com.dleyy.data.bean.BingBean;

import java.util.List;

import javax.security.auth.callback.Callback;

/**
 * Created by dleyy on 2017/9/26.
 */
public class BingResponse extends BaseApiResponse<BingBean> {

    private BingBean bean;

    public BingResponse(Context context) {
        super(context);
    }

    public Boolean isResourceExist(){
        return getResCode()==0;
    }

}
