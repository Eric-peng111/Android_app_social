package com.example.myfirstapp;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.userCollection.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.FirebaseApp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author Zihan Meng
 * @feature The home screen of app
 */
public class MainActivity extends AppCompatActivity {
    MyApplication app= MyApplication.getApplication();
    private Button btn_login_test;
    private TextView username;
    private ImageButton btn_profile;
    private ImageButton btn_search;
    private ImageButton btn_nearby;
    private FloatingActionButton btn_post;
    private ImageButton btn_myPost;

    private ListView lv_content;
    private SimpleAdapter mSimpleAdapter;
    private List<Map<String,Object>> mList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initialize firebase
        FirebaseApp.initializeApp(getBaseContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set title to "Home"
        getSupportActionBar().setTitle("Home");

        //Use findViewById function to match component
        btn_profile= findViewById(R.id.btn_profile);
        btn_search = findViewById(R.id.btn_search);
        btn_post=(FloatingActionButton) findViewById(R.id.fab_newPost);
        btn_myPost =findViewById(R.id.btn_myPost);

        //list view
        lv_content = findViewById(R.id.lv_mypost);

        User user=(User) getIntent().getExtras().getSerializable("USER");
        username=findViewById(R.id.tv_nickName_home);
        username.setText(user.getUsername());



        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference();

        ref.addValueEventListener(new ValueEventListener(){
            /**
             * @author Zihan Meng
             * @feature Bind hashmap data to the listview using simple adapter.
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i=0;
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    i++;
                    Map<String,Object> post = new HashMap();
                    post.put("title","#"+datas.child("index").getValue().toString()+" "+datas.child("title").getValue().toString() );
                    post.put("content",datas.child("about").getValue().toString());
                    mList.add(post);
                    if(i>99)
                        break;
                }
                mSimpleAdapter = new SimpleAdapter(MainActivity.this,mList,R.layout.list_item_layout,new String[]{"title","content"},new int[]{R.id.tv_title,R.id.tv_content});
                lv_content.setAdapter(mSimpleAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        /**
         * @author Zihan Meng
         * @feature Set setOnClickListener on items of list, When click item, show a toast to
         * which item has been pressed and go to ContentActivity.
         */
        lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String,Object> map = mList.get(i);
                String title = (String) map.get("title");
                Toast.makeText(MainActivity.this,"You pressed " + title,Toast.LENGTH_SHORT).show();
                Intent contentActivity= new Intent(getApplicationContext(), ContentActivity.class);
                contentActivity.putExtra("USER",user);
                int index=Integer.parseInt(getIndex(title));
                contentActivity.putExtra("INDEX",(Serializable) index);
                startActivity(contentActivity);
            }
        });


        /**
         * @author Zihan Meng
         * @feature Set setOnClickListener on button, When click btn_search button, go to SearchActivity.
         */
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchActivity= new Intent(MainActivity.this, SearchActivity.class);
                searchActivity.putExtra("POST", (Serializable) mList);
                startActivity(searchActivity);
            }
        });


        /**
         * @author Zihan Meng
         * @feature Set setOnClickListener on button, When click btn_myPost button, go to MyPostActivity.
         */
        btn_myPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myPostActivity= new Intent(getApplicationContext(), MyPostActivity.class);
                myPostActivity.putExtra("USER",user);
                startActivity(myPostActivity);
            }
        });

        /**
         * @author Zihan Meng
         * @feature Set setOnClickListener on button, When click btn_profile button, go to UserProfileActivity.
         */
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent UserProfileActivity= new Intent(MainActivity.this, UserProfileActivity.class);
                UserProfileActivity.putExtra("USER",user);
                startActivity(UserProfileActivity);
            }
        });

        /**
         * @author Zihan Meng
         * @feature Set setOnClickListener on button, When click btn_post button, go to PostActivity.
         */
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postActivity= new Intent(getApplicationContext(), PostActivity.class);


                postActivity.putExtra("USER",user);
                startActivity(postActivity);

            }
        });

    }

    /**
     * @author Zihan Meng
     * @feature get index of given string.
     * @param s
     * @return r
     */
    public String getIndex(String s){
        String r="";
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==' ')
                break;
            else
                r=r+s.charAt(i);
        }
        return r;
    }
}