package com.mtleung.demoretrofitmvc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtleung.demoretrofitmvc.R;
import com.mtleung.demoretrofitmvc.model.Post;
import com.mtleung.demoretrofitmvc.presenter.PostItemPresenter;

/**
 * Created by Marco on 11/12/2016.
 */

public class PostAdapter extends BaseAdapter<Post, PostItemPresenter, PostViewHolder> {

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(parent.getContext(), view);
    }

    @Override
    protected PostItemPresenter createPresenter(Post model) {
        PostItemPresenter presenter = new PostItemPresenter();
        presenter.setModel(model);
        return presenter;
    }

    @Override
    protected Object getModelId(Post model) {
        return model.id;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.bindPresenter(getPresenter(getItem(position)));
    }

    @Override
    protected Post getItem(int position) {
        return models.get(position);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
