package com.example.myfirstapp;

import android.content.Intent;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PostActivity extends AppCompatActivity {


    private EditText et_post_title;
    private EditText etml_content;
    private FloatingActionButton fab_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        //set title to "Home"
        getSupportActionBar().setTitle("New Post");

        et_post_title = findViewById(R.id.et_post_title);
        etml_content = findViewById(R.id.etml_content);
        fab_submit = findViewById(R.id.fab_submit);

        etml_content.setMovementMethod(new ScrollingMovementMethod());

        fab_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PostActivity.this,"You pressed submit!" ,Toast.LENGTH_SHORT).show();
                Intent submitActivity= new Intent(PostActivity.this, MainActivity.class);
                startActivity(submitActivity);
            }
        });




    }
}