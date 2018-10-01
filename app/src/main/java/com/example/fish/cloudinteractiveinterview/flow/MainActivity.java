package com.example.fish.cloudinteractiveinterview.flow;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.fish.cloudinteractiveinterview.R;
import com.example.fish.cloudinteractiveinterview.architecture.BaseActivity;
import com.example.fish.cloudinteractiveinterview.architecture.BaseFragment;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IView {

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected int getContainerViewId() {
        return R.id.fl_fragment_container;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(FirstFragment.newInstance());
    }

    @Override
    public void onBackPressed() {
        hideSoftKeyBoard();
        FragmentManager fmClose = getSupportFragmentManager();
        BaseFragment currentFragment = (BaseFragment) fmClose.findFragmentById(getContainerViewId());
        if ((currentFragment instanceof FirstFragment && fmClose.getBackStackEntryCount() <= 1)) {
            supportFinishAfterTransition();
        } else {
            super.onBackPressed();
        }
    }
}
