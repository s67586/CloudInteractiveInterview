package com.example.fish.cloudinteractiveinterview.http;


import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public abstract class ApiObserverFeedBack<T> extends DisposableObserver<ApiResponse<T>> {

    protected abstract void sysCodeSuccess(T data, String message);

    protected abstract void sysCodeFail(int sysCode, String message);

    protected abstract void onException(Throwable throwable);

    @Override
    public void onNext(@NonNull ApiResponse<T> apiResponse) {
        if (apiResponse.getSyscode() == 200) {
            sysCodeSuccess(apiResponse.getData(), apiResponse.getSysmsg());
        } else {
            sysCodeFail(apiResponse.getSyscode(),apiResponse.getSysmsg());
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onException(e);
        dispose();
    }

    @Override
    public void onComplete() {
        dispose();
    }
}
