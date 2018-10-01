package com.example.fish.cloudinteractiveinterview.http.Retrofit;

import com.example.fish.cloudinteractiveinterview.ServiceGenerator;
import com.example.fish.cloudinteractiveinterview.http.Retrofit.ApiModel.ApiModel;
import com.example.fish.cloudinteractiveinterview.http.Retrofit.ApiModel.ApiPhotosData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiManager {

    public ApiManager() {
    }

    private static class ApiManagerHolder {
        private static final ApiManager INSTANCE = new ApiManager();
    }

    public static ApiManager getInstance() {
        return ApiManagerHolder.INSTANCE;
    }

    public void callApi(OnResponseListener<ApiPhotosData> onResponseListener) {

        ServiceGenerator.createService(ApiService.class)
                .getPhotos()
                .retryWhen(new RetryRequestWithDelay(2, 3000))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .safeSubscribe(new ApiObserverFeedBack<ApiPhotosData>() {

                    @Override
                    protected void onStart() {
                        if (onResponseListener != null) {
                            onResponseListener.onCallApiStart();
                        }
                    }

                    @Override
                    protected void sysCodeSuccess(ApiPhotosData data, String message) {
                        if (onResponseListener != null) {
                            onResponseListener.onCallApiSuccess(data, message);
                        }
                    }

                    @Override
                    protected void sysCodeFail(int sysCode, String message) {
                        if (onResponseListener != null) {
                            onResponseListener.onCallApiFail(sysCode, message);
                        }
                    }

                    @Override
                    protected void onException(Throwable throwable) {
                        if (onResponseListener != null) {
                            onResponseListener.onCallApiError(throwable);
                        }
                    }
                });
    }
}
