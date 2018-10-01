package com.example.fish.cloudinteractiveinterview.http.Retrofit;

public class ApiResponse<T> {
    private int syscode;
    private String sysmsg;
    private T data;

    public int getSyscode() {
        return syscode;
    }

    public String getSysmsg() {
        return sysmsg;
    }

    public T getData() {
        return data;
    }
}
