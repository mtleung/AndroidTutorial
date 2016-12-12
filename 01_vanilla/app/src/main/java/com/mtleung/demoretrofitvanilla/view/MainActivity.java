package com.mtleung.demoretrofitvanilla.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.mtleung.demoretrofitvanilla.MainApplication;
import com.mtleung.demoretrofitvanilla.R;
import com.mtleung.demoretrofitvanilla.adapter.PostAdapter;
import com.mtleung.demoretrofitvanilla.api.JSONPlaceholderService;
import com.mtleung.demoretrofitvanilla.model.Post;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.activity_main) ViewAnimator animator;

    private final int POSITION_LIST = 0;
    private final int POSITION_LOADING = 1;

    private List<Post> posts;
    private PostAdapter adapter;

    private Context context;
    private JSONPlaceholderService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up UI
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Set up listview
        posts = new ArrayList<>();
        adapter = new PostAdapter(this, posts);

        RecyclerView listView = (RecyclerView) animator.getChildAt(POSITION_LIST);

        listView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listView.setAdapter(adapter);
        listView.setHasFixedSize(false);

        // Get singleton services
        MainApplication app = (MainApplication) getApplication();
        apiService = app.getApiService();

        // Get data
        context = this;
        showLoading();
        fetchData();
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
