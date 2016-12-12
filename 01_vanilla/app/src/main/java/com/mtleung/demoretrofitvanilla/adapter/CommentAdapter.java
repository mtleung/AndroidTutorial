package com.mtleung.demoretrofitvanilla.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtleung.demoretrofitvanilla.R;
import com.mtleung.demoretrofitvanilla.model.Comment;


import java.util.List;

/**
 * Created by Marco on 10/12/2016.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    private final String TAG = CommentAdapter.class.getSimpleName();

    private List<Comment> comments;
    private LayoutInflater inflater;

    public CommentAdapter(Context context, List<Comment> comments) {
        inflater = LayoutInflater.from(context);
        this.comments = comments;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_comment, parent, false);
        CommentViewHolder vh = new CommentViewHolder(view, new CommentViewHolder.CommentClickListener() {
            @Override
            public void onNameClicked(int position, View view) {
                Log.d(TAG, String.format("Comment %s (id %s)", position, comments.get(position).id));
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment c = comments.get(position);
        holder.commentName.setText(c.name);
        holder.commentEmail.setText(c.email);
        holder.commentBody.setText(c.body);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
