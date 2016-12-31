package com.mtleung.demoretrofitmvc.model.presentation;

import android.os.Handler;

import com.mtleung.demoretrofitmvc.interfaces.DatabaseInterface.LoggedInDatabase;
import com.mtleung.demoretrofitmvc.interfaces.MainInterface.MainModelIntf;
import com.mtleung.demoretrofitmvc.presenter.MainPresenter;

/**
 * Created by Marco on 31/12/2016.
 */

public class MainPresentationModel implements MainModelIntf {

    MainPresenter presenter;
    LoggedInDatabase database;

    public MainPresentationModel(MainPresenter presenter, LoggedInDatabase database) {
        this.presenter = presenter;
        this.database = database;
    }

    @Override
    public boolean isLoggedIn() {
        return database.isLoggedIn();
    }

    @Override
    public void login(final String username, final String password) {
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if (username.isEmpty()) {
                            onUsernameError();
                            return;
                        }
                        if (password.isEmpty()) {
                            onPasswordError();
                            return;
                        }
                        saveLogin(username);
                        onLoginSuccess();
                    }
                }, 800);
    }

    @Override
    public void onUsernameError() {
        presenter.onUsernameError();
    }

    @Override
    public void onPasswordError() {
        presenter.onPasswordError();
    }

    @Override
    public void onLoginSuccess() {
        presenter.onLoginSuccess();
    }

    @Override
    public void saveLogin(String username) {
        database.saveUsername(username);
    }
}
