package com.mtleung.demoretrofitmvc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtleung.demoretrofitmvc.R;
import com.mtleung.demoretrofitmvc.model.Comment;
import com.mtleung.demoretrofitmvc.presenter.CommentItemPresenter;

/**
 * Created by Marco on 12/12/2016.
 */

public class CommentAdapter extends BaseAdapter<Comment, CommentItemPresenter, CommentViewHolder> {

    @Override
    protected CommentItemPresenter createPresenter(Comment model) {
        CommentItemPresenter presenter = new CommentItemPresenter();
        presenter.setModel(model);
        return presenter;
    }

    @Override
    protected Object getModelId(Comment model) {
        return model.id;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        holder.bindPresenter(getPresenter(getItem(position)));
    }

    @Override
    protected Comment getItem(int position) {
        return models.get(position);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}

