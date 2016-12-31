package com.mtleung.demoretrofitmvc.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.mtleung.demoretrofitmvc.R;
import com.mtleung.demoretrofitmvc.interfaces.DatabaseInterface.LoggedInDatabase;

/**
 * Created by Marco on 31/12/2016.
 */

public class SharedPrefHelper implements LoggedInDatabase {
    private final String TAG = SharedPrefHelper.class.getSimpleName();

    // Shared Pref Keys
    public static String USERNAME = "com.demo.username";

    private static SharedPrefHelper instance;
    private SharedPreferences sharedPreferences;

    public static SharedPrefHelper getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefHelper(context);
        }
        return instance;
    }

    private SharedPrefHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public String getPref(String key) {
        return sharedPreferences.getString(key, "");
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

    @Override
    public boolean isLoggedIn() {
        return ! getPref(SharedPrefHelper.USERNAME).isEmpty();
    }

    @Override
    public void saveUsername(String username) {
        setPref(SharedPrefHelper.USERNAME, username);
    }

    @Override
    public void removeUsername() {
        deletePref(SharedPrefHelper.USERNAME);
    }
}
