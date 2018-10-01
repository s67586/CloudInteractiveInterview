package com.example.fish.cloudinteractiveinterview.flow;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.fish.cloudinteractiveinterview.architecture.BasePresenter;
import com.example.fish.cloudinteractiveinterview.http.Retrofit.ApiModel.ApiData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class SecondPresenter extends BasePresenter<SecondContract.IView, SecondModel> implements SecondContract.IPresenter {
    private SecondAdapter mSecondAdapter;

    @Override
    protected SecondModel getModel() {
        return new SecondModel();
    }

    @Override
    public void onInitOtherModel() {
        super.onInitOtherModel();
        mSecondAdapter = new SecondAdapter(mModel);
    }

    @Override
    public void onViewCreated() {
        mView.setAdapter(mSecondAdapter);
        String urlParkingArea = "https://jsonplaceholder.typicode.com/photos";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlParkingArea, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                parserJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }

        );
        Volley.newRequestQueue(mView.getContext()).add(jsonArrayRequest);
    }

    private void parserJson(JSONArray response) {
        Type listType = new TypeToken<ArrayList<ApiData>>(){}.getType();

        ArrayList<ApiData> apiData = new Gson().fromJson(String.valueOf(response), listType);
        mModel.setApiData(apiData);
        mSecondAdapter.notifyDataSetChanged();
    }
}
