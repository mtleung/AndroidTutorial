package com.mtleung.demoretrofitvanilla.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mtleung.demoretrofitvanilla.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Marco on 10/12/2016.
 */

public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.post_id)TextView postId;
    @BindView(R.id.post_title)TextView postTitle;
    @BindView(R.id.post_body) TextView postBody;

    PostClickListener listener;

    PostViewHolder(View view, PostClickListener listener) {
        super(view);
        this.listener = listener;

        ButterKnife.bind(this, view);

        postTitle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        listener.onTitleClicked(getAdapterPosition(), view);
    }

    public static interface PostClickListener {
        void onTitleClicked(int position, View view);
    }
}
