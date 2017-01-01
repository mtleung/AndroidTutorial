package com.mtleung.demoretrofitmvc;

import android.app.Application;
import android.content.Context;

import com.mtleung.demoretrofitmvc.components.AppComponent;
import com.mtleung.demoretrofitmvc.components.DaggerAppComponent;
import com.mtleung.demoretrofitmvc.modules.AppModule;
import com.mtleung.demoretrofitmvc.modules.NetModule;

/**
 * Created by Marco on 01/01/2017.
 */

public class MainApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
    }

    public static MainApplication get(Context context) {
        return (MainApplication) context.getApplicationContext();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://jsonplaceholder.typicode.com/"))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
