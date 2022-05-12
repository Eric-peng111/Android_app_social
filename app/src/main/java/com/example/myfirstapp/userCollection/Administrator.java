package com.example.myfirstapp.userCollection;

public class Administrator implements Participant {
    private int id;
    private String username;
    private String password;
    //private String post;
    private int hashcode;

    public Administrator(int i,String user, String password){
        this.username=user;
        this.password=password;
        this.id=i;
        this.hashcode=hashCode();

    }
    public int hashCode() {
        int h=0;
        if(username!=null){
            for(int i=0;i<this.username.length();i++){
                h=h+username.charAt(i)*(i+1);
            }
        }
        if(password!=null){
            for(int j=0;j<password.length();j++){
                h=h+password.charAt(j);
            }
        }
        return h;
    }
    @Override
    public int compareTo() {
        return 0;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public int compareTo(User user) {
        return 0;
    }
}
