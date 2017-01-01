package com.mtleung.demoretrofitmvc.components;

import android.app.Application;

import com.mtleung.demoretrofitmvc.modules.AppModule;
import com.mtleung.demoretrofitmvc.modules.CommentActivityModule;
import com.mtleung.demoretrofitmvc.modules.DatabaseModule;
import com.mtleung.demoretrofitmvc.modules.MainActivityModule;
import com.mtleung.demoretrofitmvc.modules.NetModule;
import com.mtleung.demoretrofitmvc.modules.PostActivityModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Marco on 01/01/2017.
 */

@Singleton
@Component(modules = { AppModule.class, NetModule.class, DatabaseModule.class })
public interface AppComponent {
    Application application();
    // Injection targets here

    // Factory method to instantiate the subcomponent defined here
    // (Passing in the module instance)
    MainActivityComponent getMainComponent(MainActivityModule mainModule);
    PostActivityComponent getPostComponent(PostActivityModule postModule);
    CommentActivityComponent getCommentComponent(CommentActivityModule postModule);
}
