package com.mtleung.demoretrofitmvc.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mtleung.demoretrofitmvc.presenter.BasePresenter;

/**
 * Allows each row to bind to its own presenter
 * Created by Marco on 12/12/2016.
 */

public abstract class BaseViewHolder<P extends BasePresenter> extends RecyclerView.ViewHolder {
    protected P presenter;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void bindPresenter(P presenter) {
        this.presenter = presenter;
        presenter.bindView(this);
    }

    public void unbindPresenter() {
        presenter = null;
    }
}
