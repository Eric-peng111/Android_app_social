package com.example.myfirstapp;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.userCollection.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ContentActivity extends AppCompatActivity {

    private TextView tv_content_content;
    private TextView tv_title_content;
    private TextView textName;
    private TextView textTime;


//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        FirebaseApp.initializeApp(getBaseContext());

        tv_content_content=findViewById(R.id.tv_content_content);
        tv_title_content=findViewById(R.id.tv_title_content);
        textName=findViewById(R.id.textName);
        textTime=findViewById(R.id.textTime);

        User user=(User) getIntent().getExtras().getSerializable("USER");
        int index= (int) getIntent().getExtras().getSerializable("INDEX");


        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        DatabaseReference ref = db.getReference("https://comp2100-6442-4f4de-default-rtdb.asia-southeast1.firebasedatabase.app/");

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
        ref.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    if(datas.child("index").getValue().toString().equals(Integer.toString(index))){
                        tv_content_content.setText(datas.child("about").getValue().toString());
                        tv_title_content.setText(datas.child("title").getValue().toString());
                        textTime.setText(datas.child("time").getValue().toString());
                        textName.setText(datas.child("name").getValue().toString());
                        break;
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



        tv_content_content = findViewById(R.id.tv_content_content);
        tv_content_content.setMovementMethod(new ScrollingMovementMethod());



    }
}