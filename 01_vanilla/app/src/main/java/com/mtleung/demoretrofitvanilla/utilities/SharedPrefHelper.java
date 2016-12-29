package com.mtleung.demoretrofitvanilla.utilities;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.mtleung.demoretrofitvanilla.R;

/**
 * Created by Marco on 29/12/2016.
 */

public class SharedPrefHelper {
    private final String TAG = SharedPrefHelper.class.getSimpleName();

    // Shared Pref Keys
    public static String USERNAME = "com.demo.username";

    private static SharedPrefHelper instance;
    private SharedPreferences sharedPreferences;

    public static SharedPrefHelper getInstance(Application application) {
        if (instance == null) {
            instance = new SharedPrefHelper(application);
        }
        return instance;
    }

    private SharedPrefHelper(Application application) {
        sharedPreferences = application.getSharedPreferences(
                application.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public String getPref(String key) {
        return sharedPreferences.getString(key, null);
    }

    public boolean setPref(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public boolean deletePref(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        return editor.commit();
    }
}
