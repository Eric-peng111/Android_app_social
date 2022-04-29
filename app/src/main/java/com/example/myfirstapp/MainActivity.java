package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_login_test;
    private Button btn_profile_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login_test =findViewById(R.id.btn_login_test);
        btn_profile_test = findViewById(R.id.btn_profile_test);

        //Set a test button to login page
        btn_login_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginActivity= new Intent(MainActivity.this,LoginActivity.class);
                startActivity(loginActivity);
            }
        });


        //Set a test button to profile
        btn_profile_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginActivity= new Intent(MainActivity.this, UserProfileActivity.class);
                startActivity(loginActivity);
            }
        });

    }
}
