package com.example.myfirstapp.database_class;

import java.util.ArrayList;

public class Post {
    //private final int i;
    public int _id;
    public String about;
    public int index;
    public String name;
    public ArrayList<Thumbup>thumbup;
    public ArrayList<Comment>comments;
    public String time;
    public String title;

    public Post(int i, String about, int index, String name, String time, String title){

        this._id = i;
        this.about = about;
        this.index = index;
        this.name = name;
        this.thumbup = null;
        this.comments=null;
        this.time = time;
        this.title = title;
    }

}
