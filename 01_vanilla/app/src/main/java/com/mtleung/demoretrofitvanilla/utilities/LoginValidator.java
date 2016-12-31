package com.mtleung.demoretrofitvanilla.utilities;

import android.os.Handler;

/**
 * Created by Marco on 31/12/2016.
 */

public class LoginValidator {

    public void validate(final String username, final String password, final LoginListener listener) {
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if (username.isEmpty()) {
                            listener.onUsernameError();
                            return;
                        }
                        if (password.isEmpty()) {
                            listener.onPasswordError();
                            return;
                        }
                        listener.onSuccess();
                    }
                }, 2000);
    }
}
