package com.mtleung.demoretrofitmvc.adapter;

import android.view.View;
import android.widget.TextView;

import com.mtleung.demoretrofitmvc.R;
import com.mtleung.demoretrofitmvc.interfaces.CommentItemInterface.CommentItemViewIntf;
import com.mtleung.demoretrofitmvc.presenter.CommentItemPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Marco on 12/12/2016.
 */

public class CommentViewHolder extends BaseViewHolder<CommentItemPresenter> implements CommentItemViewIntf {
    private final String TAG = CommentViewHolder.class.getSimpleName();

    @BindView(R.id.comment_name) TextView name;
    @BindView(R.id.comment_email) TextView email;
    @BindView(R.id.comment_body) TextView body;

    public CommentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setCommentUser(String user) {
        name.setText(user);
    }

    @Override
    public void setCommentEmail(String email) {
        this.email.setText(email);
    }

    @Override
    public void setCommentBody(String body) {
        this.body.setText(body);
    }
}
