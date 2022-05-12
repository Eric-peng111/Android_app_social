package com.example.myfirstapp;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.myfirstapp.userCollection.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MyApplication extends Application{

    private static MyApplication application;
    public static RBTree<User> UserTree=new RBTree<User>();
    public Firebase fb;
    public int i=0;




    public static RBTree<User> getUser(){
        return UserTree;
    }

    public static MyApplication getApplication(){
        return application;
    }
    public int getID(){
        return i;
    }
    public int addID()
    {
        i++;
        return i;
    }
    public Firebase getFb(){
        return fb;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        fb=Firebase.getInstance();
        FirebaseApp.initializeApp(getBaseContext());

//        DatabaseReference ref = db.getReference("https://comp2100-6442-4f4de-default-rtdb.asia-southeast1.firebasedatabase.app/");

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
        ref.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                  i++;
                }
                fb.setPost_num(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("loginDetails.csv"), StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(",");
                UserTree.insert(new User(Integer.parseInt(tokens[0]),tokens[1],tokens[2]));
                addID();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {}

        application=this;
    }
}

