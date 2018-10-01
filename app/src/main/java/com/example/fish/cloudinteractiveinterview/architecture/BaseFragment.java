package com.example.fish.cloudinteractiveinterview.architecture;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragment<P extends BaseIPresenter> extends Fragment implements BaseIView {

    protected P mPresenter;

    private Activity mContext;

    protected Bundle mSaveInstanceState;

    private View mRootView;

    private CompositeDisposable mCompositeDisposable;

    protected abstract P getPresenter();

    protected abstract int getViewLayout();

    protected abstract void initView();

    protected abstract void initTitleBar();

    protected abstract void setListeners();

    protected abstract void onViewCreated();

    public BaseFragment() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setEnterTransition(getDefaultEnterTransition());
            setExitTransition(getDefaultExitTransition());
            setReenterTransition(getDefaultReenterTransition());
            setReturnTransition(getDefaultReturnTransition());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public Bundle getSaveInstance() {
        return mSaveInstanceState;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSaveInstanceState = savedInstanceState;
        mPresenter = getPresenter();
        mPresenter.onAttachView(this);
        mPresenter.onInitOtherModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getViewLayout(), container, false);
            initView();
        }
        initTitleBar();
        setListeners();
        return mRootView;
    }

    protected <V extends View> V findView(int id) {
        return (V) mRootView.findViewById(id);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        onViewCreated();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    //RxJava2收集監聽者
    @Override
    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    //關閉虛擬鍵盤
    @Override
    public void hideSoftKeyBoard() {
        ((BaseActivity) getActivity()).hideSoftKeyBoard();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mSaveInstanceState = outState;
        mPresenter.onSaveInstance();
    }

    //替換Fragment
    @Override
    public void replaceFragment(Fragment changeFragment) {
        ((BaseActivity) getActivity()).replaceFragment(changeFragment);
    }

    @Override
    public void onBackPressed() {
        getActivity().onBackPressed();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Object getDefaultEnterTransition() {
        Slide slideEnterFromRight = new Slide(Gravity.RIGHT);
        slideEnterFromRight.setDuration(200);
        slideEnterFromRight.excludeTarget(android.R.id.statusBarBackground, true);
        slideEnterFromRight.excludeTarget(android.R.id.navigationBarBackground, true);
        return slideEnterFromRight;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Object getDefaultExitTransition() {
        Fade fadeOut = new Fade(Fade.OUT);
        fadeOut.setDuration(200);
        fadeOut.excludeTarget(android.R.id.statusBarBackground, true);
        fadeOut.excludeTarget(android.R.id.navigationBarBackground, true);
        return fadeOut;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Object getDefaultReenterTransition() {
        Fade fadeIn = new Fade(Fade.IN);
        fadeIn.setDuration(200);
        fadeIn.excludeTarget(android.R.id.statusBarBackground, true);
        fadeIn.excludeTarget(android.R.id.navigationBarBackground, true);
        return fadeIn;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Object getDefaultReturnTransition() {
        Slide slideReturnToRight = new Slide(Gravity.RIGHT);
        slideReturnToRight.setDuration(200);
        slideReturnToRight.excludeTarget(android.R.id.statusBarBackground, true);
        slideReturnToRight.excludeTarget(android.R.id.navigationBarBackground, true);
        return slideReturnToRight;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onUnSubscribe();
        hideSoftKeyBoard();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePresenter();
    }

    private void onUnSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
            mCompositeDisposable = null;
        }
    }

    private void releasePresenter() {
        if (mPresenter != null) {
            mPresenter.onDetachView();
            mPresenter.onReleaseModel();
            mPresenter = null;
        }
    }
}
