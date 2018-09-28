package com.example.fish.cloudinteractiveinterview.architecture;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import io.reactivex.disposables.Disposable;

public interface BaseIView {

    Context getContext();

    Bundle getSaveInstance();

    void startActivity(Intent intent);

    void showToast(String message);

    void addDisposable(Disposable disposable);

    void hideSoftKeyBoard();

    void onBackPressed();

}
