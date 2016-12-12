package com.mtleung.demoretrofitmvc.presenter;

import com.mtleung.demoretrofitmvc.interfaces.CommentItemInterface.CommentItemPresenterIntf;
import com.mtleung.demoretrofitmvc.interfaces.CommentItemInterface.CommentItemViewIntf;
import com.mtleung.demoretrofitmvc.model.Comment;

/**
 * Created by Marco on 12/12/2016.
 */

public class CommentItemPresenter extends BasePresenter<Comment, CommentItemViewIntf> implements
        CommentItemPresenterIntf {
    @Override
    protected void updateView() {
        view().setCommentUser(model.name);
        view().setCommentEmail(model.email);
        view().setCommentBody(model.body);
    }
}
