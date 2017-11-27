package com.dleyy.data.request;

import com.dleyy.data.ApiList;
import com.dleyy.data.net.DefaultObserver;
import com.show.api.ShowApiRequest;

/**
 * Created by dleyy on 2017/11/24.
 */
public class HuaBanRequest extends BaseApiRequest {
    private String TAG = "HuaBanRequest";

    private ShowApiRequest request;

    /**
     * 类型 34-40
     */
    private int type;

    /**
     * 每页的item数量
     */
    private int num;

    /**
     * 第几页
     */
    private int page;

    public HuaBanRequest() {
        request = ApiList.getInstance().getHuaBanApi();
    }

    public void setType(int type) {
        request.addTextPara("type", String.valueOf(type));
    }

    public void setNum(int num) {
        request.addTextPara("num", String.valueOf(num));
    }

    public void setPage(int page) {
        request.addTextPara("page", String.valueOf(page));
    }

    public void HuaBanExcute(DefaultObserver defaultObserver) {
        excute(request, defaultObserver);
    }
}
