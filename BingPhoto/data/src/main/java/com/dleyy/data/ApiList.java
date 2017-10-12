package com.dleyy.data;

import com.show.api.ShowApiRequest;

/**
 * Created by dleyy on 2017/9/26.
 */
public class ApiList {
    private static ApiList apilist;
    private String APP_SECRET = "c97947245ae14c20b8a91c845fdf959a";
    private String APP_ID = "46890";
    private String ShowApiBaseUrl = "http://route.showapi.com/";

    public static ApiList getInstance() {
        if (apilist == null) {
            apilist = new ApiList();
        }
        return apilist;
    }

    /**
     * 基础Api
     *
     * @param ApiNum api编号
     * @return
     */
    protected ShowApiRequest GetBaseSHowApi(String ApiNum) {
        return new ShowApiRequest(ShowApiBaseUrl + ApiNum, APP_ID, APP_SECRET);
    }


    /**
     * Bing Api
     *
     * @return
     */
    public ShowApiRequest getBingApi() {
        return GetBaseSHowApi("1377-1");
    }

    /**
     * 花瓣Api
     *
     * @return
     */
    public ShowApiRequest getHuaBanApi() {
        return GetBaseSHowApi("819-1");
    }

}
