package com.example.myfirstapp;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ArrayList mlist= new ArrayList();
        mlist=(ArrayList) getIntent().getExtras().getSerializable("POST");
        //set title to "Search"
        getSupportActionBar().setTitle("Search");


    }
}