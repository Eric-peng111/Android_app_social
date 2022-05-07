package com.example.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ContentActivity extends AppCompatActivity {

    private TextView tv_content_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        tv_content_content = findViewById(R.id.tv_content_content);
        tv_content_content.setMovementMethod(new ScrollingMovementMethod());

    }
}