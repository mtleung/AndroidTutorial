package com.mtleung.demoretrofitmvc.modules;

import com.mtleung.demoretrofitmvc.adapter.PostAdapter;
import com.mtleung.demoretrofitmvc.api.JSONPlaceholderService;
import com.mtleung.demoretrofitmvc.interfaces.ActivityScope;
import com.mtleung.demoretrofitmvc.interfaces.DatabaseInterface.LoggedInDatabase;
import com.mtleung.demoretrofitmvc.model.presentation.PostPresentationModel;
import com.mtleung.demoretrofitmvc.presenter.PostPresenter;
import com.mtleung.demoretrofitmvc.view.PostActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Marco on 01/01/2017.
 */

@Module
public class PostActivityModule {
    private PostActivity activity;

    public PostActivityModule(PostActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    PostActivity providesPostActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    PostPresenter providesPresenter(LoggedInDatabase loggedInDatabase, JSONPlaceholderService jsonPlaceholderService) {
        PostPresenter presenter =  new PostPresenter(loggedInDatabase);
        PostPresentationModel model = new PostPresentationModel(presenter, jsonPlaceholderService);
        presenter.setModel(model);
        return presenter;
    }

    @Provides
    @ActivityScope
    public PostAdapter providesPostAdapter() {
        return new PostAdapter();
    }
}
