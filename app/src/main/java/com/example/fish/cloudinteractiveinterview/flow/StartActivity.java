package com.example.fish.cloudinteractiveinterview.flow;

import android.os.Bundle;
import android.widget.Button;

import com.example.fish.cloudinteractiveinterview.R;
import com.example.fish.cloudinteractiveinterview.architecture.BaseActivity;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

public class StartActivity extends BaseActivity<StartPresenter> implements StartContract.IView {
    private Button mButton;

    @Override
    protected StartPresenter getPresenter() {
        return new StartPresenter();
    }

    @Override
    protected int getViewLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        mButton = findView(R.id.btn_start_call_api);
    }

    @Override
    protected void setListeners() {
        addDisposable(RxView.clicks(mButton)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> mPresenter.onCallAPI()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
