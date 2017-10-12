package com.dleyy.data.request;

import android.util.Log;

import com.dleyy.data.net.DefaultObserver;
import com.dleyy.data.response.BaseApiResponse;
import com.dleyy.data.response.BingResponse;
import com.show.api.ShowApiRequest;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dleyy on 2017/9/27.
 */
public class BaseApiRequest {

    private String TAG = "BaseApiRequest";

    protected void excute(final ShowApiRequest request, DefaultObserver observer) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String response = request.post();
                subscriber.onNext(response);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


    }

}
