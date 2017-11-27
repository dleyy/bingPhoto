package com.dleyy.data.response;

import android.content.Context;

import com.dleyy.data.Exception.ExceptionHandler;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dleyy on 2017/9/27.
 */
public class BaseApiResponse<T> {

    protected ExceptionHandler exceptionHandler;

    @SerializedName("showapi_res_code")
    private int resCode;

    @SerializedName("showapi_res_error")
    private String resError;

    @SerializedName("showapi_res_body")
    private T resBody;

    public BaseApiResponse(Context context) {
        exceptionHandler = new ExceptionHandler(context);
    }

    /**
     * 请求是否成功
     * @return
     */
    public boolean isPostSuccess() {
        return resCode == 0;
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResError() {
        return resError;
    }

    public void setResError(String resError) {
        this.resError = resError;
    }

    public T getResBody() {
        return resBody;
    }

    public void setResBody(T resBody) {
        this.resBody = resBody;
    }
}
