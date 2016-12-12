package com.mtleung.demoretrofitmvc.interfaces;

/**
 * Created by Marco on 12/12/2016.
 */

public class CommentItemInterface {
    public interface CommentItemViewIntf{
        void setCommentUser(String user);
        void setCommentEmail(String email);
        void setCommentBody(String body);
    }

    public interface CommentItemPresenterIntf {
        // nothing to implement
    }
}
