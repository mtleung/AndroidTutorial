package com.mtleung.demoretrofitmvc.interfaces;

/**
 * Created by Marco on 31/12/2016.
 */

public class DatabaseInterface {
    public interface LoggedInDatabase {
        boolean isLoggedIn();
        void saveUsername(String username);
        void removeUsername();
    }
}
