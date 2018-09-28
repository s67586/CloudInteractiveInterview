package com.example.fish.cloudinteractiveinterview.architecture;

public interface BaseIPresenter<V extends BaseIView> {

    void onAttachView(V view);

    void onInitOtherModel();

    void onResume();

    void onCallApiFail(int sysCode, String message);

    void onCallApiError(Throwable throwable);

    void onDetachView();

    void onSaveInstance();

    void onReleaseModel();
}
