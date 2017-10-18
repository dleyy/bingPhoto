package com.dleyy.data.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 缓存拦截器，只要在head中添加了Cache-Time，就可以根据值来进行数据的缓存。
 * Created by dleyy on 2017/10/18.
 */
public class CashInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        String cache = request.header("Cache-Time");
        if (null != cache) {
            Response response1 = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "max-age=" + cache)
                    .build();
            return response1;
        }
        return response;
    }
}
