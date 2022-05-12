package com.example.myfirstapp.userCollection.userstate;

import java.io.Serializable;

public class UserSession implements Serializable {

    UserState userState;
    // user on session
    String username;
    public UserSession() {
        UserState defaultState = new NoSessionState(this);
        changeState(defaultState);
    }
    public void changeState(UserState state) {
        this.userState = state;
    }

    public boolean login(String username, String password) {
        boolean loginOk = userState.login(username, password);
        if(loginOk) {
        this.username = username;
    }
        return loginOk;
    }

    public boolean likePost(Integer postId) {
        return userState.likePost(postId);
    }

    public boolean logout() {
        boolean logoutOk = userState.logout();
        if(logoutOk) {
            this.username = null;
        }
        return logoutOk;
    }
}
