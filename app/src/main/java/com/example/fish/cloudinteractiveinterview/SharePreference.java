package com.example.fish.cloudinteractiveinterview;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreference {

    public static final String PREFERENCE_NAME_PERSONAL = "preference_name_personal";
    public static final String PREFERENCE_NAME_DATA = "preference_name_data";

    public static final String PREF_PERSONAL_IS_FIRST = "is_first";

    private SharePreference() {

    }

    private static SharedPreferences getInstances() {
        return getInstances(PREFERENCE_NAME_PERSONAL);
    }

    private static SharedPreferences getInstances(String spName) {
        return MyApp.getAppContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    public static boolean isFirst() {
        return  getBoolean(PREF_PERSONAL_IS_FIRST, true);
    }

    public static boolean getBoolean(String keyName, boolean defValue) {
        return getInstances().getBoolean(keyName, defValue);
    }

    public static boolean setBoolean(String keyName, boolean value) {
        return getInstances().edit().putBoolean(keyName, value).commit();
    }

    public static boolean clearAll() {
        boolean first = SharePreference.isFirst();
        boolean commit = getInstances().edit().clear().commit();
        SharePreference.setBoolean(SharePreference.PREF_PERSONAL_IS_FIRST, first);
        return commit;
    }

    public static boolean clearAll(String spName) {
        return getInstances(spName).edit().clear().commit();
    }
}
