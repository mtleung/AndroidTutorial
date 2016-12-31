package com.mtleung.demoretrofitmvc.interfaces;

/**
 * Created by Marco on 31/12/2016.
 */

public class MainInterface {
    public interface MainViewIntf {
        // How to display things

        void showProgress();
        void hideProgress();

        void showUsernameError();
        void showPasswordError();

        void navigatePostActivity();
        void onLoginClicked();
    }

    public interface MainPresenterIntf {
        // When to display states and trigger updates
        void onStart();
        void checkLastLogin();
        void validateCredentials(String username, String password);
    }

    public interface MainModelIntf {
        // What to display
        boolean isLoggedIn();

        void login(String username, String password);
        void onUsernameError();
        void onPasswordError();
        void onLoginSuccess();
        void saveLogin(String username);
    }

    public interface MainPresenterCallback {
        void onUsernameError();
        void onPasswordError();
        void onLoginSuccess();
    }
}
