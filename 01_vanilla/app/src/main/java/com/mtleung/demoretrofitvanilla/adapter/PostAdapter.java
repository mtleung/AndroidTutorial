package com.mtleung.demoretrofitvanilla.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtleung.demoretrofitvanilla.R;
import com.mtleung.demoretrofitvanilla.model.Post;
import com.mtleung.demoretrofitvanilla.view.CommentActivity;

import java.util.List;

/**
 * Created by Marco on 10/12/2016.
 */

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {
    private final String TAG = PostAdapter.class.getSimpleName();

    private Context context;
    private List<Post> posts;
    private LayoutInflater inflater;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.posts = posts;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_post, parent, false);
        PostViewHolder vh = new PostViewHolder(view, new PostViewHolder.PostClickListener() {
            @Override
            public void onTitleClicked(int position, View view) {
                Log.d(TAG, String.format("Item %s (id %s)", position, posts.get(position).id));
                Intent intent = new Intent(context, CommentActivity.class);
                intent.putExtra("POST", posts.get(position).id);
                context.startActivity(intent);
            }
        });

        return vh;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post p = posts.get(position);
        holder.postId.setText(String.format("Post %s", p.id));
        holder.postTitle.setText(p.title);
        holder.postBody.setText(p.body);
    }
}
