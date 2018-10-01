package com.example.fish.cloudinteractiveinterview.flow;

import com.example.fish.cloudinteractiveinterview.architecture.BaseIPresenter;
import com.example.fish.cloudinteractiveinterview.architecture.BaseIView;

public class SecondContract {

    interface IView extends BaseIView {
        void setAdapter(SecondAdapter secondAdapter);
    }

    interface IPresenter extends BaseIPresenter<SecondContract.IView>{
        void onViewCreated();
    }
}
