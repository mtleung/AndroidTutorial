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

public class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.comment_name)TextView commentName;
    @BindView(R.id.comment_email) TextView commentEmail;
    @BindView(R.id.comment_body) TextView commentBody;

    CommentClickListener listener;

    public CommentViewHolder(View itemView, CommentClickListener listener) {
        super(itemView);
        this.listener = listener;

        ButterKnife.bind(this, itemView);

        commentName.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        listener.onNameClicked(getAdapterPosition(), view);

    }

    public static interface CommentClickListener {
        void onNameClicked(int position, View view);
    }
}
