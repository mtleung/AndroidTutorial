package com.mtleung.demoretrofitmvc.presenter;

import android.util.Log;

import com.mtleung.demoretrofitmvc.api.JSONPlaceholderService;
import com.mtleung.demoretrofitmvc.interfaces.CommentInterface.CommentViewIntf;
import com.mtleung.demoretrofitmvc.interfaces.CommentInterface.CommentPresenterIntf;
import com.mtleung.demoretrofitmvc.interfaces.CommentInterface.CommentPresenterCallback;
import com.mtleung.demoretrofitmvc.model.Comment;
import com.mtleung.demoretrofitmvc.model.presentation.CommentPresentationModel;

import java.util.List;

/**
 * Created by Marco on 12/12/2016.
 */

public class CommentPresenter extends BasePresenter<CommentPresentationModel, CommentViewIntf>
    implements CommentPresenterIntf, CommentPresenterCallback {
    private final static String TAG = CommentPresenter.class.getSimpleName();

    private int postId;

    public CommentPresenter() {
    }

    @Override
    public void onStart(int postId) {
        this.postId = postId;
    }

    @Override
    public void onFetchCompleted(List<Comment> comments) {
        view().showComments(comments);
        Log.d(TAG, String.format("Showing %s comments", comments.size()));
    }

    @Override
    public void onFetchFailed() {
        view().showToast("Failed to load comments");
    }

    @Override
    protected void updateView() {
        // Business logic
        view().showLoading();
        if (model.shouldFetchData()) {
            model.fetchData(postId);
        } else {
            view().showComments(model.getData());
        }
    }
}
