package com.mtleung.demoretrofitmvc.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Marco on 12/12/2016.
 */

public enum ApiManager {

    INSTANCE;
    private JSONPlaceholderService apiService;

    ApiManager() {
        // Init API Service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(JSONPlaceholderService.class);
    }

    public static ApiManager getInstance() {
        return INSTANCE;
    }

    public JSONPlaceholderService getApiService() {
        return apiService;
    }
}
