package com.mtleung.demoretrofitmvc.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mtleung.demoretrofitmvc.R;
import com.mtleung.demoretrofitmvc.database.SharedPrefHelper;
import com.mtleung.demoretrofitmvc.interfaces.DatabaseInterface.LoggedInDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Marco on 01/01/2017.
 */
@Module
public class DatabaseModule {

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return application.getSharedPreferences(
                application.getString(R.string.app_name), Context.MODE_PRIVATE
        );
    }

    @Provides
    @Singleton
    SharedPrefHelper providesSharedPrefHelper(SharedPreferences sharedPreferences) {
        return new SharedPrefHelper(sharedPreferences);
    }

    @Provides
    @Singleton
    LoggedInDatabase providesLoggedInDatabase(SharedPreferences sharedPreferences) {
        return new SharedPrefHelper(sharedPreferences);
    }
}
