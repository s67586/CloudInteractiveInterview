package com.example.fish.cloudinteractiveinterview.http.Retrofit;

import com.example.fish.cloudinteractiveinterview.architecture.BaseIPresenter;

public abstract class OnResponseListener<T> {

    private BaseIPresenter mBaseIPresenter;

    public OnResponseListener(BaseIPresenter baseIPresenter) {
        mBaseIPresenter = baseIPresenter;
    }

    public void onCallApiStart() {

    }

    public abstract void onCallApiSuccess(T data, String message);

    public void onCallApiFail(int sysCode, String message) {
        mBaseIPresenter.onCallApiFail(sysCode, message);
    }

    public void onCallApiError(Throwable throwable) {
        mBaseIPresenter.onCallApiError(throwable);
    }

}
