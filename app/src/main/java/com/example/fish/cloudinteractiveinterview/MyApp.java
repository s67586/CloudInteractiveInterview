package com.example.fish.cloudinteractiveinterview;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;

import io.fabric.sdk.android.Fabric;


public class MyApp extends MultiDexApplication {

    private static Context mContext;

    public void onCreate() {
        super.onCreate();
        MyApp.mContext = getApplicationContext();
        if (true) {
            Stetho.initializeWithDefaults(this);
        }
        Fabric.with(this, new Crashlytics());
    }

    public static Context getAppContext() {
        return MyApp.mContext;
    }
}
