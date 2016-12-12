package com.mtleung.demoretrofitmvc.presenter;

import com.mtleung.demoretrofitmvc.interfaces.PostItemInterface.PostItemPresenterIntf;
import com.mtleung.demoretrofitmvc.interfaces.PostItemInterface.PostItemViewIntf;
import com.mtleung.demoretrofitmvc.model.Post;

/**
 * Created by Marco on 11/12/2016.
 */

public class PostItemPresenter extends BasePresenter<Post, PostItemViewIntf> implements
        PostItemPresenterIntf{

    @Override
    protected void updateView() {
        view().setPostId(model.id);
        view().setPostTitle(model.title);
        view().setPostBody(model.body);
    }

    @Override
    public void onPostClicked() {
        view().goToPostComments(model.id);
    }
}
