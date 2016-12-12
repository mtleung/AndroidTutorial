package com.mtleung.demoretrofitmvc.interfaces;

import com.mtleung.demoretrofitmvc.model.Comment;

import java.util.List;

/**
 * Created by Marco on 12/12/2016.
 */

public class CommentInterface {
    public interface CommentViewIntf {
        // HOW to display things

        // Methods for updating the view
        void showComments(List<Comment> comments);
        void showLoading();
        void showToast(String message);
    }

    public interface CommentPresenterIntf {
        // WHEN to display to certain states and trigger model updates

        // Lifecycle events
        void onStart(int postId);

        // Input event methods
        // None
    }

    public interface CommentModelIntf {
        // WHAT to display
        boolean shouldFetchData();
        void fetchData(int postId);
        List<Comment> getData();
    }

    public interface CommentPresenterCallback {
        void onFetchCompleted(List<Comment> comments);
        void onFetchFailed();
    }
}
