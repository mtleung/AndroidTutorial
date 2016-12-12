package com.mtleung.demoretrofitmvc.model.presentation;

import android.os.AsyncTask;
import android.util.Log;

import com.mtleung.demoretrofitmvc.api.JSONPlaceholderService;
import com.mtleung.demoretrofitmvc.interfaces.CommentInterface.CommentModelIntf;
import com.mtleung.demoretrofitmvc.model.Comment;
import com.mtleung.demoretrofitmvc.presenter.CommentPresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by Marco on 12/12/2016.
 */

public class CommentPresentationModel implements CommentModelIntf {
    private final static String TAG = CommentPresentationModel.class.getSimpleName();

    private CommentPresenter presenter;
    private JSONPlaceholderService apiService;
    private List<Comment> comments;

    public CommentPresentationModel(CommentPresenter presenter, JSONPlaceholderService apiService) {
        this.presenter = presenter;
        this.apiService = apiService;
        comments = new ArrayList<>();
    }

    @Override
    public boolean shouldFetchData() {
        return comments.size() == 0;
    }

    @Override
    public void fetchData(int postId) {
        Log.d(TAG, String.format("Getting data for %s", postId));
        GetCommentInfo task = new GetCommentInfo();
        task.execute(postId);
    }

    @Override
    public List<Comment> getData() {
        return comments;
    }

    private class GetCommentInfo extends AsyncTask<Integer, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Integer... params) {
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
                return true;
            } catch (IOException e) {
                Log.e(TAG, "onFailure");
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean status) {
            if (status) {
                presenter.onFetchCompleted(comments);
            } else {
                presenter.onFetchFailed();
            }

        }
    }
}
