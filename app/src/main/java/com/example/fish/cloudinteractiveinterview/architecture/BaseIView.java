package com.example.fish.cloudinteractiveinterview.architecture;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.reactivex.disposables.Disposable;

public interface BaseIView {

    Context getContext();

    Bundle getSaveInstance();

    void showToast(String message);

    void addDisposable(Disposable disposable);

    void hideSoftKeyBoard();

    void replaceFragment(Fragment changeFragment);

    void onBackPressed();

}
