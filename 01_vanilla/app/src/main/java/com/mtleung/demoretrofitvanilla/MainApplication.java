package com.mtleung.demoretrofitvanilla;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.mtleung.demoretrofitvanilla.api.JSONPlaceholderService;
import com.mtleung.demoretrofitvanilla.utilities.SharedPrefHelper;
import com.squareup.leakcanary.LeakCanary;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Marco on 10/12/2016.
 */

public class MainApplication extends Application {

    private JSONPlaceholderService apiService;
    private SharedPrefHelper sharedPrefHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        // Init API Service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(JSONPlaceholderService.class);

        // Init shared preferences
        sharedPrefHelper = SharedPrefHelper.getInstance(this);
    }

    public JSONPlaceholderService getApiService() {
        return apiService;
    }

    public SharedPrefHelper getSharedPrefHelper() {
        return sharedPrefHelper;
    }
}
