package com.mtleung.demoretrofitmvc.presenter;

import com.mtleung.demoretrofitmvc.interfaces.DatabaseInterface.LoggedInDatabase;
import com.mtleung.demoretrofitmvc.interfaces.MainInterface.MainPresenterCallback;
import com.mtleung.demoretrofitmvc.interfaces.MainInterface.MainPresenterIntf;
import com.mtleung.demoretrofitmvc.interfaces.MainInterface.MainViewIntf;
import com.mtleung.demoretrofitmvc.model.presentation.MainPresentationModel;

/**
 * Created by Marco on 31/12/2016.
 */

public class MainPresenter extends BasePresenter<MainPresentationModel, MainViewIntf> implements
        MainPresenterIntf, MainPresenterCallback {

    public MainPresenter(LoggedInDatabase database) {
        super.setModel(new MainPresentationModel(this, database));
    }

    @Override
    public void bindView(MainViewIntf view) {
        super.bindView(view);
    }

    @Override
    protected void updateView() {
        // Business logic

    }

    @Override
    public void onStart() {
        checkLastLogin();
    }

    @Override
    public void checkLastLogin() {
        if (model.isLoggedIn()) {
            view().navigatePostActivity();
        }
    }

    @Override
    public void validateCredentials(String username, String password) {
        view().showProgress();
        model.login(username, password);
    }

    @Override
    public void onUsernameError() {
        view().hideProgress();
        view().showUsernameError();
    }

    @Override
    public void onPasswordError() {
        view().hideProgress();
        view().showPasswordError();
    }

    @Override
    public void onLoginSuccess() {
        view().hideProgress();
        view().navigatePostActivity();
    }
}
