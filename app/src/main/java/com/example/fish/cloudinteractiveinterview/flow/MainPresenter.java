package com.example.fish.cloudinteractiveinterview.flow;

import com.example.fish.cloudinteractiveinterview.architecture.BasePresenter;

import java.io.Serializable;

public class MainPresenter extends BasePresenter<MainContract.IView, Serializable> implements MainContract.IPresenter {
    @Override
    protected Serializable getModel() {
        return null;
    }
}
