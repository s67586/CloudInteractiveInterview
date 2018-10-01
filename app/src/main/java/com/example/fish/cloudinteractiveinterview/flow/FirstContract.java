package com.example.fish.cloudinteractiveinterview.flow;

import com.example.fish.cloudinteractiveinterview.architecture.BaseIPresenter;
import com.example.fish.cloudinteractiveinterview.architecture.BaseIView;

public class FirstContract {

    interface IView extends BaseIView {
    }

    interface IPresenter extends BaseIPresenter<FirstContract.IView> {
        void onCallAPI();
    }
}
