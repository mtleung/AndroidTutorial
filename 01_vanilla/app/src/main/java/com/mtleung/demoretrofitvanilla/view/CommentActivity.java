package com.mtleung.demoretrofitvanilla.view;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.mtleung.demoretrofitvanilla.MainApplication;
import com.mtleung.demoretrofitvanilla.R;
import com.mtleung.demoretrofitvanilla.adapter.CommentAdapter;
import com.mtleung.demoretrofitvanilla.api.JSONPlaceholderService;
import com.mtleung.demoretrofitvanilla.model.Comment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * Created by Marco on 10/12/2016.
 */

public class CommentActivity extends AppCompatActivity {
    private final String TAG = CommentActivity.class.getSimpleName();

    private final int POSITION_LIST = 0;
    private final int POSITION_LOADING = 1;

    @BindView(R.id.comment_animator) ViewAnimator animator;

    private List<Comment> comments;
    private CommentAdapter adapter;

    private Context context;
    private JSONPlaceholderService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get user id
        Intent intent = getIntent();
        if (! intent.hasExtra("POST")) {
            return;
        }
        int postId = intent.getIntExtra("POST", -1);

        // Set up UI
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

        // Set up list view
        comments = new ArrayList<>();
        adapter = new CommentAdapter(this, comments);

        RecyclerView listView = (RecyclerView) animator.getChildAt(POSITION_LIST);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(adapter);

        // Get singleton services
        MainApplication app = (MainApplication) getApplication();
        apiService = app.getApiService();

        // Get data
        context = this;
        showLoading();
        fetchData(postId);
    }

    private void showLoading() {
        animator.setDisplayedChild(POSITION_LOADING);
    }

    private void fetchData(int postId) {
        Log.d(TAG, "Fetching comments");
        // Toast.makeText(this, "Fetching comments", Toast.LENGTH_SHORT).show();
        GetCommentInfo asyncTask = new GetCommentInfo();
        asyncTask.execute(postId);
    }

    private class GetCommentInfo extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int postId = params[0];
            Call<List<Comment>> commentRequest = apiService.getComments(postId);
            try {
                comments.clear();
                comments.addAll(commentRequest.execute().body());

            } catch (IOException e) {
                Log.e(TAG, "onFailure");
                Toast.makeText(context, "Failed to load data", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            adapter.notifyDataSetChanged();
            animator.setDisplayedChild(POSITION_LIST);
            Toast.makeText(context, String.format("Comments: %s", comments.size()), Toast.LENGTH_SHORT).show();
        }
    }
}
