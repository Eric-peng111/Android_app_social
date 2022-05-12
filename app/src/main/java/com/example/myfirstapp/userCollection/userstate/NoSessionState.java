package com.example.myfirstapp.userCollection.userstate;

public class NoSessionState extends UserState{
    public NoSessionState(UserSession userSession) {
        super(userSession);
    }

    @Override
    public boolean login(String username, String password) {
        SessionState nextState = new SessionState(userSession);
        userSession.changeState(nextState);
        return true;
    }
    @Override
    public boolean likePost(Integer postId) {
        System.out.println("You cannot like post in no session state");
        return false;
    }
    @Override
    public boolean logout() {
        System.out.println("You cannot logout in no session state");
        return false;
    }


}
