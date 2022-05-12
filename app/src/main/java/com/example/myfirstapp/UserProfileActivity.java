package com.example.myfirstapp;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {

    private Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //set title to "User Profile"
        getSupportActionBar().setTitle("User Profile");


        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout = new Intent(UserProfileActivity.this,LoginActivity.class);
                startActivity(logout);
                Toast.makeText(UserProfileActivity.this,"Logout success",Toast.LENGTH_SHORT).show();
            }
        });
    }


}