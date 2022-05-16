package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.userCollection.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PostActivity extends AppCompatActivity {

    MyApplication app= MyApplication.getApplication();
    private EditText et_post_title;
    private EditText etml_content;
    private FloatingActionButton fab_submit;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        User user=(User) getIntent().getExtras().getSerializable("USER");

        FirebaseApp.initializeApp(getBaseContext());

//        DatabaseReference ref = db.getReference("https://comp2100-6442-4f4de-default-rtdb.asia-southeast1.firebasedatabase.app/");

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
        et_post_title=findViewById(R.id.et_post_title);
        etml_content=findViewById(R.id.etml_content);
        fab_submit=(FloatingActionButton)findViewById(R.id.fab_submit);

        fab_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String title = et_post_title.getText().toString();
                String content = etml_content.getText().toString();
                DateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                System.out.println(user.getID());
                ref.push().setValue(new Post(user.getID(),content,app.getFb().getPost_num(),user.getUsername(),0,0,dateFormat.format(Calendar.getInstance().getTime()) ,title));
                app.getFb().addPost();
                Toast.makeText(PostActivity.this, "Post Successfully", Toast.LENGTH_LONG).show();
                Intent contentActivity= new Intent(getApplicationContext(), ContentActivity.class);
                contentActivity.putExtra("USER",user);
                int index=app.getFb().getPost_num()-1;
                contentActivity.putExtra("INDEX",(Serializable) index);
                startActivity(contentActivity);
            }
        });




    }
}