package com.example.fish.cloudinteractiveinterview.flow;

import android.os.Bundle;
import android.widget.Button;

import com.example.fish.cloudinteractiveinterview.R;
import com.example.fish.cloudinteractiveinterview.architecture.BaseFragment;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

public class FirstFragment extends BaseFragment<FirstPresenter> implements FirstContract.IView {

    private Button mButton;

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected FirstPresenter getPresenter() {
        return new FirstPresenter();
    }

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView() {
        mButton = findView(R.id.btn_start_call_api);
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void setListeners() {
        addDisposable(RxView.clicks(mButton)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> mPresenter.onCallAPI()));
    }

    @Override
    protected void onViewCreated() {

    }
}
