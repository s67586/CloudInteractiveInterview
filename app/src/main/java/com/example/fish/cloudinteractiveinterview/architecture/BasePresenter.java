package com.example.fish.cloudinteractiveinterview.architecture;

import com.example.fish.cloudinteractiveinterview.SharePreference;

import java.io.Serializable;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;

public abstract class BasePresenter<V extends BaseIView, M extends Serializable> implements BaseIPresenter<V> {

    protected V mView;
    protected M mModel;

    protected abstract M getModel();


    @Override
    public void onAttachView(V view) {
        this.mView = view;
        if (mView.getSaveInstance() != null) {
            mModel = (M) mView.getSaveInstance().getSerializable("AlanBundle");
        } else {
            mModel = getModel();
        }
    }

    @Override
    public void onInitOtherModel() {

    }

    @Override
    public void onResume() {
        //檢查版
    }

    @Override
    public void onCallApiFail(int sysCode, String message) {
        if (mView == null) return;
        if (sysCode == 405) {
            SharePreference.clearAll();
            SharePreference.clearAll(SharePreference.PREFERENCE_NAME_DATA);
        } else {
            mView.showToast(String.valueOf(sysCode) + " : " + message);
        }
    }

    @Override
    public void onCallApiError(Throwable throwable) {
        if (mView == null) return;
        String message = throwable.getMessage();
        if (throwable instanceof NoRouteToHostException) {
            message = "伺服器異常，請稍後在試！";
        } else if (throwable instanceof SocketTimeoutException) {
            message = "網路連線逾時，請稍後在試！";
        }
        mView.showToast(message);
    }

    @Override
    public void onDetachView() {
        this.mView = null;
    }

    @Override
    public void onSaveInstance() {
        mView.getSaveInstance().putSerializable("AlanBundle", mModel);
    }

    @Override
    public void onReleaseModel() {
        mModel = null;
    }
}
