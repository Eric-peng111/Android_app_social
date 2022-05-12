package com.example.myfirstapp.userCollection.userstate;

import java.io.Serializable;

public abstract class UserState implements Serializable {

    protected UserSession userSession;

    public UserState(UserSession userSession) {
        this.userSession = userSession;
    }
    public abstract boolean login(String username, String password);
    public abstract boolean likePost(Integer postId);
    public abstract boolean logout();
//    public abstract List<ActionCount> generateActionCountReport();
//
//    public abstract List<UserActivity> findAllPosts();
}
