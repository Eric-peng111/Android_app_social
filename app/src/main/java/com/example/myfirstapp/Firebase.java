package com.example.myfirstapp;

public class Firebase {
    // Fields
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

    // Set method
    public void setPost_num(int i) {
        post_num = i;
    }

    // Get methods
    public int getPost_num() {
        return post_num;
    }

    // The method for reducing the number of post
    public void deletePost() {
        post_num--;
    }

    // The method for increasing the number of post
    public void addPost() {
        post_num++;
    }

}
