package com.mtleung.demoretrofitmvc.interfaces;

import com.mtleung.demoretrofitmvc.model.Post;

import java.util.List;

/**
 * Created by Marco on 11/12/2016.
 */

public class PostInterface {
    public interface PostViewIntf {
        // HOW to display things

        // Methods for updating the view
        void showPosts(List<Post> posts);
        void showLoading();
        void showToast(String message);

        // Navigation methods
        void onLogoutClicked();
        void navigateMainActivity();
    }

    public interface PostPresenterIntf {
        // WHEN to display to certain states and trigger model updates

        // Lifecycle events
        void onStart();

        // Input event methods
        void onLogout();
    }

    public interface PostModelIntf {
        // WHAT to display
        boolean shouldFetchData();
        void fetchData();
        List<Post> getData();
    }

    public interface PostPresenterCallback {
        void onFetchCompleted(List<Post> posts);
        void onFetchFailed();
    }
}
