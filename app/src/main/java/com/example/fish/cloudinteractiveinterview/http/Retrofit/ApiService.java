package com.example.fish.cloudinteractiveinterview.http.Retrofit;

import com.example.fish.cloudinteractiveinterview.http.Retrofit.ApiModel.ApiData;
import com.example.fish.cloudinteractiveinterview.http.Retrofit.ApiModel.ApiModel;
import com.example.fish.cloudinteractiveinterview.http.Retrofit.ApiModel.ApiPhotosData;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET(ApiConfig.API_PHOTOS)
    Observable<ApiResponse<ApiPhotosData>> getPhotos();

    @GET(ApiConfig.API_PHOTOS)
    Call<ResponseBody> getCall();
}
