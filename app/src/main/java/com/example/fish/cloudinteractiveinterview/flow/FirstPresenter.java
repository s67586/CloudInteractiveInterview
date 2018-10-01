package com.example.fish.cloudinteractiveinterview.flow;

import com.example.fish.cloudinteractiveinterview.architecture.BasePresenter;

import java.io.Serializable;

public class FirstPresenter extends BasePresenter<FirstContract.IView,Serializable> implements FirstContract.IPresenter{

    @Override
    protected Serializable getModel() {
        return null;
    }

    @Override
    public void onCallAPI() {
        mView.replaceFragment(SecondFragment.newInstance());
    }
}
