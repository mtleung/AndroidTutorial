package com.mtleung.demoretrofitmvc;

import android.os.Bundle;

import com.mtleung.demoretrofitmvc.presenter.BasePresenter;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Marco on 11/12/2016.
 */

public class PresenterManager {
    private final static String PRESENTER_ID = "presenter_id";
    private static PresenterManager instance;
    private final AtomicLong currentId;

    private final HashMap<Long, BasePresenter> presenters;

    public static PresenterManager getInstance() {
        if (instance == null) {
            instance = new PresenterManager();
        }
        return instance;
    }

    private PresenterManager() {
        currentId = new AtomicLong();
        presenters = new HashMap<>();
    }

    // Saves presenter to an ID
    public void savePresenter(BasePresenter presenter, Bundle outState) {
        long presenterId = currentId.incrementAndGet();
        presenters.put(presenterId, presenter);
        outState.putLong(PRESENTER_ID, presenterId);
    }

    public <P extends BasePresenter> P restorePresenter(Bundle savedInstanceState) {
        long presenterId = savedInstanceState.getLong(PRESENTER_ID);
        P presenter = (P) presenters.get(presenterId);
        presenters.remove(presenterId);
        return presenter;
    }
}
