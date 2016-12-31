package com.mtleung.demoretrofitmvc.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.mtleung.demoretrofitmvc.PresenterManager;
import com.mtleung.demoretrofitmvc.R;
import com.mtleung.demoretrofitmvc.adapter.PostAdapter;
import com.mtleung.demoretrofitmvc.api.ApiManager;
import com.mtleung.demoretrofitmvc.interfaces.PostInterface.PostViewIntf;
import com.mtleung.demoretrofitmvc.model.Post;
import com.mtleung.demoretrofitmvc.presenter.PostPresenter;
import com.mtleung.demoretrofitmvc.database.SharedPrefHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostActivity extends AppCompatActivity implements PostViewIntf {

    @BindView(R.id.activity_post) ViewAnimator animator;

    private final int POSITION_LIST = 0;
    private final int POSITION_LOADING = 1;

    private PostAdapter adapter;
    private PostPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        SharedPrefHelper sharedPrefHelper = SharedPrefHelper.getInstance(getApplication());
        if (savedInstanceState == null) {
            presenter = new PostPresenter(sharedPrefHelper, ApiManager.getInstance().getApiService());
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                onLogoutClicked();
                // Clear the username
//                SharedPrefHelper sharedPrefHelper = app.getSharedPrefHelper();
//                sharedPrefHelper.deletePref(SharedPrefHelper.USERNAME);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    public void onLogoutClicked() {
        presenter.onLogout();
    }

    @Override
    public void navigateMainActivity() {
        // Go back to login page
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
