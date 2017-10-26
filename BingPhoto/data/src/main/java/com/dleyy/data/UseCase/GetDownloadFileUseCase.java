package com.dleyy.data.UseCase;

import com.dleyy.data.request.GetDownloadImageRequest;

import rx.Observable;

/**
 * Created by dleyy on 2017/10/25.
 */
public class GetDownloadFileUseCase extends UseCase {

    private GetDownloadImageRequest request;

    public GetDownloadFileUseCase() {
        request = new GetDownloadImageRequest();
    }

    public void setImagePath(String imagePath) {
        request.setImagePath(imagePath);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return Observable.from(request.getDownloadPath());
    }
}
