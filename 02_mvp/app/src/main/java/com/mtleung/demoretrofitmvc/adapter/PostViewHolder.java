package com.mtleung.demoretrofitmvc.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mtleung.demoretrofitmvc.R;
import com.mtleung.demoretrofitmvc.interfaces.PostItemInterface.PostItemViewIntf;
import com.mtleung.demoretrofitmvc.presenter.PostItemPresenter;
import com.mtleung.demoretrofitmvc.view.CommentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Marco on 11/12/2016.
 */

public class PostViewHolder extends BaseViewHolder<PostItemPresenter> implements PostItemViewIntf {
    private final String TAG = PostViewHolder.class.getSimpleName();

    @BindView(R.id.post_id) TextView postId;
    @BindView(R.id.post_title) TextView postTitle;
    @BindView(R.id.post_body) TextView postBody;

    private Context context;

    public PostViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setPostTitle(String title) {
        postTitle.setText(title);
    }

    @Override
    public void setPostBody(String body) {
        postBody.setText(body);
    }

    @Override
    public void setPostId(int id) {
        postId.setText(String.format("Post %s", id));
    }

    @OnClick(R.id.post_title)
    public void onTitleClicked() {
        presenter.onPostClicked();
    }

    @Override
    public void goToPostComments(int postId) {
        Log.d(TAG, String.format("Post Id %s", postId));
        Intent intent = new Intent(context, CommentActivity.class);
        intent.putExtra("POST", postId);
        context.startActivity(intent);
    }
}
