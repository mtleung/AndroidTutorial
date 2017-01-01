package com.mtleung.demoretrofitmvc.modules;

import com.mtleung.demoretrofitmvc.interfaces.ActivityScope;
import com.mtleung.demoretrofitmvc.interfaces.DatabaseInterface.LoggedInDatabase;
import com.mtleung.demoretrofitmvc.presenter.MainPresenter;
import com.mtleung.demoretrofitmvc.view.MainActivity;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by Marco on 01/01/2017.
 */

@Module
public class MainActivityModule {
    private MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    MainActivity providesMainActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MainPresenter providedPresenter(LoggedInDatabase loggedInDatabase) {
        return new MainPresenter(loggedInDatabase);
    }
}
