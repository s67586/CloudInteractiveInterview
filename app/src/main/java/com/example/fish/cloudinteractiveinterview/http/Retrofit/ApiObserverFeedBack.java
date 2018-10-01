package com.example.fish.cloudinteractiveinterview.http.Retrofit;


import android.util.Log;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public abstract class ApiObserverFeedBack<T> extends DisposableObserver<ApiResponse<T>> {

    protected abstract void sysCodeSuccess(T data, String message);

    protected abstract void sysCodeFail(int sysCode, String message);

    protected abstract void onException(Throwable throwable);

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("TAG","onStart");
    }

    @Override
    public void onNext(ApiResponse<T> apiResponse) {
        Log.e("TAG","onNext");
        if (apiResponse.getSyscode() == 200) {
            sysCodeSuccess(apiResponse.getData(), apiResponse.getSysmsg());
        } else {
            sysCodeFail(apiResponse.getSyscode(),apiResponse.getSysmsg());
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e("TAG","onError = "+e.getMessage());
        e.printStackTrace();
        onException(e);
        dispose();
    }

    @Override
    public void onComplete() {
        Log.e("TAG","onComplete");
        dispose();
    }
}
