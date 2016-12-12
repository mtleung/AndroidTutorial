package com.mtleung.demoretrofitmvc.api;

import com.mtleung.demoretrofitmvc.model.Comment;
import com.mtleung.demoretrofitmvc.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Marco on 11/12/2016.
 */

public interface JSONPlaceholderService {
    @GET("posts")
    Call<List<Post>> listPosts();

    @GET("posts/{post_id}/comments")
    Call<List<Comment>> getComments(
            @Path("post_id") int postId
    );
}
