package com.example.myfirstapp.userCollection;

import com.example.myfirstapp.userCollection.userstate.UserSession;

/**
 * General user and their attributes
 */
public class User implements Participant {

    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;

    private int hashcode;

    public UserSession US;

    public User(int i,String user, String password,String p,String e){
        this.username=user;
        this.password=password;
        this.id=i;
        this.email=e;
        this. phone=p;
        this.hashcode=hashCode();
        this.US=new UserSession();

    }
    public User(String user, String password,String p,String e){
        this.username=user;
        this.password=password;
        this.email=e;
        this.phone=p;
        this.hashcode=hashCode();
        this.US=new UserSession();
    }
    public User(String user, String password){
        this.username=user;
        this.password=password;
        this.email=null;
        this.phone=null;
        this.hashcode=hashCode();
        this.US=new UserSession();
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
    public int getID() {
        return id;
    }

    @Override
    public int compareTo(User user) {
        if(hashcode>user.hashcode)
            return 1;
        else if(hashcode== user.hashcode)
            return 0;
        else
            return -1;
    }

    @Override
    public int compareTo() {
        return 0;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getEmail(){ return email; }
    public String getPhone(){ return  phone;}
}
