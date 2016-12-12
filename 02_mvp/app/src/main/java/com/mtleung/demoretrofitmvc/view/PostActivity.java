package com.mtleung.demoretrofitmvc.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.mtleung.demoretrofitmvc.PresenterManager;
import com.mtleung.demoretrofitmvc.R;
import com.mtleung.demoretrofitmvc.adapter.PostAdapter;
import com.mtleung.demoretrofitmvc.api.ApiManager;
import com.mtleung.demoretrofitmvc.interfaces.PostInterface.PostViewIntf;
import com.mtleung.demoretrofitmvc.model.Post;
import com.mtleung.demoretrofitmvc.presenter.PostPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostActivity extends AppCompatActivity implements PostViewIntf {

    @BindView(R.id.activity_main) ViewAnimator animator;

    private final int POSITION_LIST = 0;
    private final int POSITION_LOADING = 1;

    private PostAdapter adapter;
    private PostPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            presenter = new PostPresenter(ApiManager.getInstance().getApiService());
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        RecyclerView listView = (RecyclerView) animator.getChildAt(POSITION_LIST);
        listView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new PostAdapter();
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
    public void showPosts(List<Post> posts) {
        adapter.clearAndAddAll(posts);
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
