package com.example.myfirstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class MyPostActivity extends AppCompatActivity {
    private ListView lv_content;
    private SimpleAdapter mSimpleAdapter;
    private List<Map<String,Object>> mList;
    private ImageButton btn_search_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        //set title to "MyPost"
        getSupportActionBar().setTitle("MyPost");
        //list view
        lv_content = findViewById(R.id.lv_content);
        mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Map<String,Object> map = new HashMap();
            map.put("title","This is post title " + i);
            map.put("content","This is post content " + i + ". The objective of this project is to gain some experience in the process of software construction (the design, speci铿乧ation, documentation, implementation, and testing of substantial software). This project will also give you some practice in the design and implementation of a graphical user interface (GUI) application along with the use of several important development tools (particularly Android Studio and Git). It is also an opportunity to put into practice and reason about some of the concepts presented during this course such as Data Structures, Tokenizer, Parser, Data Persistence, Design Patterns, Software Testing, etc.");
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
                Toast.makeText(MyPostActivity.this,"You pressed " + title,Toast.LENGTH_SHORT).show();

            }
        });

    }
}