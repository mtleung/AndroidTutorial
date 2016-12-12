package com.mtleung.demoretrofitmvc.presenter;

import android.util.Log;

import com.mtleung.demoretrofitmvc.api.JSONPlaceholderService;
import com.mtleung.demoretrofitmvc.interfaces.PostInterface.PostPresenterIntf;
import com.mtleung.demoretrofitmvc.interfaces.PostInterface.PostPresenterCallback;
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

    public PostPresenter (JSONPlaceholderService apiService) {
        super.setModel(new PostPresentationModel(this, apiService));
    }

    @Override
    protected void updateView() {
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
        super.bindView(view);
    }

    @Override
    public void onFetchCompleted(List<Post> posts) {
        view().showPosts(posts);
    }

    @Override
    public void onFetchFailed() {
        view().showToast("Failed to load data");
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
    }
}
