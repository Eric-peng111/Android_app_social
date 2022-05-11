package com.example.myfirstapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button btn_login_test;
    private ImageButton btn_profile;
    private ImageButton btn_search;
    private ImageButton btn_nearby;
    private FloatingActionButton fab_newPost;

    private ListView lv_content;
//    private List<String> content;
//    private ArrayAdapter<String> contentAdapter;
    private SimpleAdapter mSimpleAdapter;
    private List<Map<String,Object>> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set title to "Home"
        getSupportActionBar().setTitle("Home");

        //btn_login_test =findViewById(R.id.btn_login_test);
        btn_profile= findViewById(R.id.btn_profile);
        btn_nearby = findViewById(R.id.btn_nearby);
        btn_search = findViewById(R.id.btn_search);
        fab_newPost = findViewById(R.id.fab_newPost);

        //list view
        lv_content = findViewById(R.id.lv_content);
        mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Map<String,Object> map = new HashMap();
            map.put("title","This is post title " + i);
            map.put("content","This is post content " + i + ". The objective of this project is to gain some experience in the process of software construction (the design, speciﬁcation, documentation, implementation, and testing of substantial software). This project will also give you some practice in the design and implementation of a graphical user interface (GUI) application along with the use of several important development tools (particularly Android Studio and Git). It is also an opportunity to put into practice and reason about some of the concepts presented during this course such as Data Structures, Tokenizer, Parser, Data Persistence, Design Patterns, Software Testing, etc.");
            //map.put("content","This is post content " + i );
            mList.add(map);
        }

        mSimpleAdapter = new SimpleAdapter(this,mList,R.layout.list_item_layout,new String[]{"title","content"},new int[]{R.id.tv_title,R.id.tv_content});
        lv_content.setAdapter(mSimpleAdapter);
        lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String,Object> map = mList.get(i);
                String title = (String) map.get("title");
                Toast.makeText(MainActivity.this,"You pressed " + title,Toast.LENGTH_SHORT).show();
                Intent contentActivity= new Intent(MainActivity.this, ContentActivity.class);
                startActivity(contentActivity);

            }
        });

//        lv_content = findViewById(R.id.lv_content);
//        content = new ArrayList<>();
//
//        for (int i = 0; i < 50; i++) {
//            content.add("This is post " + i);
//        }
//
//
//        contentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, content);
//        lv_content.setAdapter(contentAdapter);

        //Set a test button to login page
//        btn_login_test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent loginActivity= new Intent(MainActivity.this,LoginActivity.class);
//                startActivity(loginActivity);
//            }
//        });

        //set a button to search page
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchActivity= new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchActivity);
            }
        });

        //set a button to nearby
        btn_nearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nearbyActivity= new Intent(MainActivity.this, NearbyActivity.class);
                startActivity(nearbyActivity);
            }
        });

        //Set a button to profile
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileActivity= new Intent(MainActivity.this, UserProfileActivity.class);
                startActivity(profileActivity);
            }
        });


        fab_newPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postActivity= new Intent(MainActivity.this, PostActivity.class);
                startActivity(postActivity);
            }
        });

    }
}
