package com.example.myfirstapp;

public class Firebase {
    private int post_num;
    private static Firebase firebase_instance = null;

    private Firebase() {
        post_num = 0;
    }

    public static Firebase getInstance() {
        if (firebase_instance == null) {
            firebase_instance = new Firebase();
        }

        return firebase_instance;

    }

    public void setPost_num(int i) {
        post_num = i;
    }

    public int getPost_num() {
        return post_num;
    }

    public void deletePost() {
        post_num--;
    }

    public void addPost() {
        post_num++;
    }

}
