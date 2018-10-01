package com.example.fish.cloudinteractiveinterview;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder mBuilder =
            new Retrofit.Builder()
                    .baseUrl(Pub.API_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    private ServiceGenerator() {

    }

    public static <S> S createService(Class<S> serviceClass) {
        initHttpClient();
        mHttpClient.addInterceptor(chain -> chain.proceed(getHeaderRequest(chain.request())));
        initHttpClientLog();
        OkHttpClient client = mHttpClient.build();
        Retrofit retrofit = mBuilder.client(client).build();
        return retrofit.create(serviceClass);
    }

    private static OkHttpClient.Builder mHttpClient;

    private static void initHttpClient() {
        int time = 10000;
        mHttpClient = new OkHttpClient.Builder();
        mHttpClient.connectTimeout(time, TimeUnit.MILLISECONDS);
        mHttpClient.readTimeout(time, TimeUnit.MILLISECONDS);
        mHttpClient.writeTimeout(time, TimeUnit.MILLISECONDS);
    }

    private static void initHttpClientLog() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mHttpClient.addInterceptor(interceptor);
    }


    private static Request getHeaderRequest(Request originalRequest) {
        Request.Builder requestBuilder;
        requestBuilder = originalRequest.newBuilder()
                .method(originalRequest.method(), originalRequest.body());
        return requestBuilder.build();
    }
}