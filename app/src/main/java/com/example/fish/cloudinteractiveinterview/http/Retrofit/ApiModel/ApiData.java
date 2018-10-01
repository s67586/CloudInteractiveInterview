package com.example.fish.cloudinteractiveinterview.http.Retrofit.ApiModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiData implements Serializable{
    @SerializedName("albumId")
    private int mAlbumId;
    @SerializedName("id")
    private int mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("thumbnailUrl")
    private String mThumbnailUrl;

    public int getAlbumId() {
        return mAlbumId;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }
}
