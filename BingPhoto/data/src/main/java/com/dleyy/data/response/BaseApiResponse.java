package com.dleyy.data.response;

/**
 * Created by dleyy on 2017/9/27.
 */
public class BaseApiResponse {
    protected String result;

    public void setResult(String requestResult) {
        result = requestResult;
    }

    public String getResult(){
        return result;
    }
}
