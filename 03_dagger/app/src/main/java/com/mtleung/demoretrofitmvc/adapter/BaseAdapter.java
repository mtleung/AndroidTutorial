package com.mtleung.demoretrofitmvc.adapter;

import android.support.v7.widget.RecyclerView;

import com.mtleung.demoretrofitmvc.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marco on 12/12/2016.
 */

abstract class BaseAdapter<M, P extends BasePresenter, VH extends BaseViewHolder<P>> extends RecyclerView.Adapter<VH> {
    protected final Map<Object, P> presenters;
    protected final List<M> models;

    protected BaseAdapter() {
        presenters = new HashMap<>();
        models = new ArrayList<>();
    }

    // Called when API returns data
    public void clearAndAddAll(Collection<M> data) {
        presenters.clear();
        models.clear();
        for (M item : data) {
            models.add(item);
            presenters.put(getModelId(item), createPresenter(item));
        }
    }

    protected P getPresenter(M model) {
        return presenters.get(getModelId(model));
    }

    // Allow each adapter to define their own presenter
    protected abstract P createPresenter(M model);

    // Allow each adapter to define the 'primary key' for each item
    protected abstract Object getModelId(M model);

    protected abstract M getItem(int position);

    @Override
    public void onViewRecycled(VH holder) {
        super.onViewRecycled(holder);
        holder.unbindPresenter();
    }

    @Override
    public boolean onFailedToRecycleView(VH holder) {
        // Sometimes, if animations are running on the itemView's children, the RecyclerView won't
        // be able to recycle the view. We should still unbind the presenter.
        holder.unbindPresenter();

        return super.onFailedToRecycleView(holder);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bindPresenter(getPresenter(getItem(position)));
    }
}
