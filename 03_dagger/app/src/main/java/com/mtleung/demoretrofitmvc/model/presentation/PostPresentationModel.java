package com.mtleung.demoretrofitmvc.model.presentation;

import android.util.Log;

import com.mtleung.demoretrofitmvc.api.JSONPlaceholderService;
import com.mtleung.demoretrofitmvc.interfaces.PostInterface.PostModelIntf;
import com.mtleung.demoretrofitmvc.model.Post;
import com.mtleung.demoretrofitmvc.presenter.PostPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Marco on 11/12/2016.
 */

public class PostPresentationModel implements PostModelIntf {
    private final static String TAG = PostPresentationModel.class.getSimpleName();

    private PostPresenter presenter;
    private JSONPlaceholderService apiService;
    private List<Post> posts;

    public PostPresentationModel(PostPresenter presenter, JSONPlaceholderService apiService) {
        this.presenter = presenter;
        this.apiService = apiService;
        posts = new ArrayList<>();
    }


    @Override
    public boolean shouldFetchData() {
        return posts.size() == 0;
    }

    @Override
    public List<Post> getData() {
        return posts;
    }

    @Override
    public void fetchData() {
        apiService.listPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                posts.clear();
                posts = response.body();
                presenter.onFetchCompleted(posts);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e(TAG, "onFailure");
                presenter.onFetchFailed();
            }
        });
    }
}
