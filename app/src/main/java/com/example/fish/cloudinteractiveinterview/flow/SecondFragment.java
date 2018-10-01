package com.example.fish.cloudinteractiveinterview.flow;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.example.fish.cloudinteractiveinterview.R;
import com.example.fish.cloudinteractiveinterview.architecture.BaseFragment;

public class SecondFragment extends BaseFragment<SecondPresenter> implements SecondContract.IView {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;

    public static SecondFragment newInstance() {
        SecondFragment fragment = new SecondFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected SecondPresenter getPresenter() {
        return new SecondPresenter();
    }

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initView() {
        mRecyclerView = findView(R.id.fl_fragment_container);
        mGridLayoutManager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void onViewCreated() {
        mPresenter.onViewCreated();
    }

    @Override
    public void setAdapter(SecondAdapter secondAdapter) {
        mRecyclerView.setAdapter(secondAdapter);
    }
}
