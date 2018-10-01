package com.example.fish.cloudinteractiveinterview.http.Retrofit;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class RetryRequestWithDelay implements Function<Observable<? extends Throwable>, Observable<?>> {

    private final int mMaxRetries;
    private final int mRetryDelayMillis;
    private int mRetryCount;

    public RetryRequestWithDelay(int maxRetries, int retryDelayMillis) {
        this.mMaxRetries = maxRetries;
        this.mRetryDelayMillis = retryDelayMillis;
    }

    @Override
    public Observable<?> apply(@NonNull Observable<? extends Throwable> error) throws Exception {
        return error.flatMap((Function<Throwable, ObservableSource<?>>) throwable -> {
            if (++mRetryCount <= mMaxRetries) {
                return Observable.interval(mRetryDelayMillis, TimeUnit.MILLISECONDS);
            }
            return Observable.error(throwable);
        });
    }
}