package com.mtleung.demoretrofitvanilla.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.mtleung.demoretrofitvanilla.MainApplication;
import com.mtleung.demoretrofitvanilla.R;
import com.mtleung.demoretrofitvanilla.adapter.PostAdapter;
import com.mtleung.demoretrofitvanilla.api.JSONPlaceholderService;
import com.mtleung.demoretrofitvanilla.model.Post;
import com.mtleung.demoretrofitvanilla.utilities.SharedPrefHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Marco on 29/12/2016.
 */

public class PostActivity extends AppCompatActivity {
    private final static String TAG = PostActivity.class.getSimpleName();

    @BindView(R.id.activity_post) ViewAnimator animator;

    private final int POSITION_LIST = 0;
    private final int POSITION_LOADING = 1;

    private List<Post> posts;
    private PostAdapter adapter;

    private MainApplication app;
    private Context context;
    private JSONPlaceholderService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up UI
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        // Set up listview
        posts = new ArrayList<>();
        adapter = new PostAdapter(this, posts);

        RecyclerView listView = (RecyclerView) animator.getChildAt(POSITION_LIST);

        listView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listView.setAdapter(adapter);
        listView.setHasFixedSize(false);

        // Get singleton services
        app = (MainApplication) getApplication();
        apiService = app.getApiService();

        // Get data
        context = this;
        showLoading();
        fetchData();
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
                // Clear the username
                SharedPrefHelper sharedPrefHelper = app.getSharedPrefHelper();
                sharedPrefHelper.deletePref(SharedPrefHelper.USERNAME);
                // Go back to login page
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showLoading() {
        animator.setDisplayedChild(POSITION_LOADING);
    }

    private void fetchData() {
        Toast.makeText(context, "Fetching Data", Toast.LENGTH_SHORT).show();
        Call<List<Post>> postRequest = apiService.listPosts();
        postRequest.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.d(TAG, "onResponse");
                posts.clear();
                posts.addAll(response.body());
                adapter.notifyDataSetChanged();
                Toast.makeText(context, String.format("Posts: %s", posts.size()), Toast.LENGTH_SHORT).show();

                animator.setDisplayedChild(POSITION_LIST);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e(TAG, "onFailure");
                Toast.makeText(context, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
