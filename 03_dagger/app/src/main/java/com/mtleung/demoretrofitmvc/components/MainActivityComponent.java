package com.mtleung.demoretrofitmvc.components;

import com.mtleung.demoretrofitmvc.MainApplication;
import com.mtleung.demoretrofitmvc.interfaces.ActivityScope;
import com.mtleung.demoretrofitmvc.modules.MainActivityModule;
import com.mtleung.demoretrofitmvc.view.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Marco on 01/01/2017.
 */

@ActivityScope
@Subcomponent(modules = { MainActivityModule.class })
public interface MainActivityComponent {
    MainActivity inject(MainActivity activity);
}
