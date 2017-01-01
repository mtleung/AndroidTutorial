package com.mtleung.demoretrofitmvc.modules;

import com.mtleung.demoretrofitmvc.adapter.CommentAdapter;
import com.mtleung.demoretrofitmvc.adapter.PostAdapter;
import com.mtleung.demoretrofitmvc.api.JSONPlaceholderService;
import com.mtleung.demoretrofitmvc.interfaces.ActivityScope;
import com.mtleung.demoretrofitmvc.model.presentation.CommentPresentationModel;
import com.mtleung.demoretrofitmvc.presenter.CommentPresenter;
import com.mtleung.demoretrofitmvc.view.CommentActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Marco on 01/01/2017.
 */

@Module
public class CommentActivityModule {
    private CommentActivity activity;

    public CommentActivityModule(CommentActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    CommentActivity providesCommentActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    CommentPresenter providesPresenter(JSONPlaceholderService jsonPlaceholderService) {
        CommentPresenter presenter = new CommentPresenter();
        CommentPresentationModel model = new CommentPresentationModel(presenter, jsonPlaceholderService);
        presenter.setModel(model);
        return presenter;
    }

    @Provides
    @ActivityScope
    public CommentAdapter providesCommentAdapter() {
        return new CommentAdapter();
    }
}
