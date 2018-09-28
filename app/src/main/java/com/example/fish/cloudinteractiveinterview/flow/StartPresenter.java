package com.example.fish.cloudinteractiveinterview.flow;

import android.content.Intent;
import android.util.Log;

import com.example.fish.cloudinteractiveinterview.architecture.BasePresenter;

public class StartPresenter extends BasePresenter<StartContract.IView, StartModel> implements StartContract.IPresenter {
    @Override
    protected StartModel getModel() {
        return new StartModel();
    }

    @Override
    public void onCallAPI() {
        Log.e("TAG","16987");
        Intent intent = new Intent(mView.getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mView.startActivity(intent);
    }
}
