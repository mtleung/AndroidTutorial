package com.mtleung.demoretrofitmvc.interfaces;

/**
 * Created by Marco on 11/12/2016.
 */

public class PostItemInterface {
    public interface PostItemViewIntf {
        // Methods for updating view
        void setPostTitle(String title);
        void setPostBody(String body);
        void setPostId(int id);

        void goToPostComments(int id);
    }

    public interface PostItemPresenterIntf {
        void onPostClicked();
    }
}
