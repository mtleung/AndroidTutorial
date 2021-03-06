package com.mtleung.demoretrofitmvc.presenter;

import android.util.Log;

import com.mtleung.demoretrofitmvc.api.JSONPlaceholderService;
import com.mtleung.demoretrofitmvc.interfaces.DatabaseInterface.LoggedInDatabase;
import com.mtleung.demoretrofitmvc.interfaces.PostInterface.PostPresenterCallback;
import com.mtleung.demoretrofitmvc.interfaces.PostInterface.PostPresenterIntf;
import com.mtleung.demoretrofitmvc.interfaces.PostInterface.PostViewIntf;
import com.mtleung.demoretrofitmvc.model.Post;
import com.mtleung.demoretrofitmvc.model.presentation.PostPresentationModel;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Marco on 11/12/2016.
 */

public class PostPresenter extends BasePresenter<PostPresentationModel, PostViewIntf> implements
        PostPresenterIntf, PostPresenterCallback{
    private final String TAG = PostPresenter.class.getSimpleName();

    LoggedInDatabase database;

    public PostPresenter (LoggedInDatabase database) {
        this.database = database;
    }

    @Override
    protected void updateView() {
        Log.d(TAG, "updateView");
        // Business logic
        view().showLoading();
        if (model.shouldFetchData()) {
            model.fetchData();
        } else {
            view().showPosts(model.getData());
        }
    }

    @Override
    public void bindView(PostViewIntf view) {
        Log.d(TAG, "bindView");
        super.bindView(view);
    }

    @Override
    public void onFetchCompleted(List<Post> posts) {
        Log.d(TAG, "onFetchCompleted");
        if (view() != null) {
            view().showPosts(posts);
        }

    }

    @Override
    public void onFetchFailed() {
        view().showToast("Failed to load data");
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
    }

    @Override
    public void onLogout() {
        database.removeUsername();
        view().navigateMainActivity();
    }
}
