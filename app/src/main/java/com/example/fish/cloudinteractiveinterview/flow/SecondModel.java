package com.example.fish.cloudinteractiveinterview.flow;

import com.example.fish.cloudinteractiveinterview.http.Retrofit.ApiModel.ApiData;

import java.io.Serializable;
import java.util.ArrayList;

public class SecondModel implements Serializable {
    private ArrayList<ApiData> mApiData;

    public SecondModel() {
       mApiData = new ArrayList<>();
    }

    public ArrayList<ApiData> getApiData() {
        return mApiData;
    }

    public void setApiData(ArrayList<ApiData> mApiData) {
        this.mApiData = mApiData;
    }

    public int getApiDataSize(){
        return mApiData.size();
    }
}
