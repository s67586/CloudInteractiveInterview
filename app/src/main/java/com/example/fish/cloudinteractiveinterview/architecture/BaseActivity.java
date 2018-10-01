package com.example.fish.cloudinteractiveinterview.architecture;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.fish.cloudinteractiveinterview.R;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity<P extends BaseIPresenter> extends AppCompatActivity implements BaseIView {
    protected P mPresenter;
    protected Bundle mSaveInstanceState;
    private CompositeDisposable mCompositeDisposable;//rxJava用重復用觀察者

    protected abstract P getPresenter();

    protected abstract int getViewLayout();

    protected abstract int getContainerViewId();

    protected abstract void initView();

    protected abstract void setListeners();

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Bundle getSaveInstance() {
        return mSaveInstanceState;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

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
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());
        mSaveInstanceState = savedInstanceState;
        mPresenter = getPresenter();
        mPresenter.onAttachView(this);
        mPresenter.onInitOtherModel();
        initView();
        setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mSaveInstanceState = outState;
        mPresenter.onSaveInstance();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePresenter();
        onUnSubscribe();
    }

    protected <V extends View> V findView(int id){
        return (V)findViewById(id);
    }

    //替換Fragment
    @Override
    public void replaceFragment(Fragment changeFragment) {
        hideSoftKeyBoard();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            fragmentTransaction.setCustomAnimations(
                    R.anim.slide_in_from_right, R.anim.slide_out_left,
                    R.anim.slide_in_from_left, R.anim.slide_out_right);
        }
        fragmentTransaction.replace(getContainerViewId(), changeFragment);
        fragmentTransaction.addToBackStack(changeFragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void releasePresenter() {
        if (mPresenter != null) {
            mPresenter.onDetachView();
            mPresenter.onReleaseModel();
            mPresenter = null;
        }
    }

    private void onUnSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
            mCompositeDisposable = null;
        }
    }
}
