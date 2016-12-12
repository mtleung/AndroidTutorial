package com.mtleung.demoretrofitmvc.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by Marco on 11/12/2016.
 */

public abstract class BasePresenter<M, V> {
    protected M model;
    private WeakReference<V> view;

    public void bindView(V view) {
        this.view = new WeakReference<>(view);
        if (setupDone()) {
            updateView();
        }
    }

    public void unbindView() {
        this.view = null;
    }

    public void setModel(M model) {
        resetState();
        this.model = model;
        if (setupDone()) {
            updateView();
        }
    }

    protected void resetState() {
        // Optionally can be over ridden
    }

    // Returns view if set
    protected V view() {
        if (view == null) {
            return null;
        } else {
            return view.get();
        }
    }

    protected boolean setupDone() {
        // Ensure model and view is set
        return view != null && model != null;
    }

    protected abstract void updateView();
}
