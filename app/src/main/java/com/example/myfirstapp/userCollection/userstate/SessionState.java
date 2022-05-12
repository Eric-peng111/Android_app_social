package com.example.myfirstapp.userCollection.userstate;

public class SessionState extends UserState{
    public SessionState(UserSession userSession) {
        super(userSession);
    }

    @Override
    public boolean login(String username, String password) {
        System.out.println("You cannot log in the session state");
        return false;
    }

    public boolean likePost(Integer postId) {
//        UserActivityDao userActivityDao = UserActivityDao.getInstance();
//        userActivityDao.likePost(userSession.getUsername(), postId);
        System.out.println("You liked post " + postId);
        return true;
    }
    @Override
    public boolean logout() {
        System.out.println("Logout done"); userSession.changeState(new NoSessionState(userSession)); return true;
    }


}
