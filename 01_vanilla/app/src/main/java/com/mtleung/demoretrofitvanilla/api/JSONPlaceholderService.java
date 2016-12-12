package com.mtleung.demoretrofitvanilla.api;

import com.mtleung.demoretrofitvanilla.model.Comment;
import com.mtleung.demoretrofitvanilla.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Marco on 10/12/2016.
 */

public interface JSONPlaceholderService {
    @GET("posts")
    Call<List<Post>> listPosts();

    @GET("posts/{post_id}/comments")
    Call<List<Comment>> getComments(
            @Path("post_id") int postId
    );
}
