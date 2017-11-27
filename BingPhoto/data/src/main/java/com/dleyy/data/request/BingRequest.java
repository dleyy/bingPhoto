package com.dleyy.data.request;

import com.dleyy.data.ApiList;
import com.dleyy.data.bean.BingBean;
import com.dleyy.data.net.DefaultObserver;


/**
 * Created by dleyy on 2017/9/26.
 */
public class BingRequest extends BaseApiRequest {
    protected String TAG = "BingRequest";

    private ApiList apiList = ApiList.getInstance();


    /**
     * Bing 请求。
     */
    public void BingExcute(DefaultObserver defaultObserver) {
        excute(apiList.getBingApi(), defaultObserver);
    }

}
