package com.mtleung.demoretrofitmvc.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.mtleung.demoretrofitmvc.PresenterManager;
import com.mtleung.demoretrofitmvc.R;
import com.mtleung.demoretrofitmvc.adapter.CommentAdapter;
import com.mtleung.demoretrofitmvc.api.ApiManager;
import com.mtleung.demoretrofitmvc.interfaces.CommentInterface.CommentViewIntf;
import com.mtleung.demoretrofitmvc.model.Comment;
import com.mtleung.demoretrofitmvc.presenter.CommentPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Marco on 11/12/2016.
 */

public class CommentActivity extends AppCompatActivity implements CommentViewIntf {

    @BindView(R.id.comment_animator) ViewAnimator animator;

    private final int POSITION_LIST = 0;
    private final int POSITION_LOADING = 1;

    private CommentAdapter adapter;
    private CommentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            presenter = new CommentPresenter(ApiManager.getInstance().getApiService());
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        RecyclerView listView = (RecyclerView) animator.getChildAt(POSITION_LIST);
        listView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new CommentAdapter();
        listView.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = getIntent();
        int postId = intent.getIntExtra("POST", -1);
        presenter.onStart(postId);
    }

    @Override
    public void showComments(List<Comment> comments) {
        adapter.clearAndAddAll(comments);
        adapter.notifyDataSetChanged();
        animator.setDisplayedChild(POSITION_LIST);
    }

    @Override
    public void showLoading() {
        animator.setDisplayedChild(POSITION_LOADING);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
