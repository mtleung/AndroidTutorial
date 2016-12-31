package com.mtleung.demoretrofitvanilla.utilities;

/**
 * Created by Marco on 31/12/2016.
 */

public interface LoginListener {
    void onUsernameError();
    void onPasswordError();
    void onSuccess();
}
