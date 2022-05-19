package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.ParserToken.Parser.E_evaluate;
import com.example.myfirstapp.ParserToken.Parser.TopicCheck;
import com.example.myfirstapp.ParserToken.Tonkenizer.Tokenizer;
import com.example.myfirstapp.userCollection.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {
    private SimpleAdapter mSimpleAdapter;
    private ListView lv_content;
    private TextView et_search;
    private ImageButton sb;
    private List<Map<String,Object>> mList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        //set title to "Search"
        getSupportActionBar().setTitle("Search");

        lv_content=findViewById(R.id.lv_mypost);
        et_search=findViewById(R.id.et_search);
        sb=findViewById(R.id.btn_search2);
        User user=(User) getIntent().getExtras().getSerializable("USER");





        //btn_login_test =findViewById(R.id.btn_login_test);
//        mList = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            Map<String,Object> map = new HashMap();
//            map.put("title","This is post title " + i);
//            map.put("content","This is post content " + i + ". The objective of this project is to gain some experience in the process of software construction (the design, speci铿乧ation, documentation, implementation, and testing of substantial software). This project will also give you some practice in the design and implementation of a graphical user interface (GUI) application along with the use of several important development tools (particularly Android Studio and Git). It is also an opportunity to put into practice and reason about some of the concepts presented during this course such as Data Structures, Tokenizer, Parser, Data Persistence, Design Patterns, Software Testing, etc.");
//            //map.put("content","This is post content " + i );
//            mList.add(map);
//        }

        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv_content.setAdapter(null);
                mList.clear();
                String s=et_search.getText().toString();

                Tokenizer tokenizer = new Tokenizer(s);

                TopicCheck test = new TopicCheck(tokenizer);
                test.check();
                String topic=test.getHex();
                String content=test.getStar();
                System.out.println("topic"+topic);
                System.out.println("content"+content);

                FirebaseDatabase db = FirebaseDatabase.getInstance();
//        DatabaseReference ref = db.getReference("https://comp2100-6442-4f4de-default-rtdb.asia-southeast1.firebasedatabase.app/");

                DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
                ref.addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        int i=0;
                        for(DataSnapshot datas: dataSnapshot.getChildren()){
                            if(topic.length()>0&&content.length()==0){
                                if(datas.child("title").getValue().toString().contains(topic) ){
                                    i++;
                                    //System.out.println(datas.child("_id").getValue().toString());
                                    Map<String,Object> post = new HashMap();
                                    post.put("title","#"+datas.child("index").getValue().toString()+" "+datas.child("title").getValue().toString() );
                                    //System.out.println(datas.child("about").getValue().toString());
                                    post.put("content",datas.child("about").getValue().toString());
                                    mList.add(post);
                                    if(i>99)
                                        break;
                                }
                            }else if(content.length()>0 && topic.length()==0){
                                if(datas.child("about").getValue().toString().contains(content)){
                                    i++;
                                    //System.out.println(datas.child("_id").getValue().toString());
                                    Map<String,Object> post = new HashMap();
                                    post.put("title","#"+datas.child("index").getValue().toString()+" "+datas.child("title").getValue().toString() );
                                    //System.out.println(datas.child("about").getValue().toString());
                                    post.put("content",datas.child("about").getValue().toString());
                                    mList.add(post);
                                    if(i>99)
                                        break;
                                }
                            }
                            else if(content.length()>0 && topic.length()>0){
                                if(datas.child("title").getValue().toString().contains(topic) && datas.child("about").getValue().toString().contains(content)){
                                    i++;
                                    //System.out.println(datas.child("_id").getValue().toString());
                                    Map<String,Object> post = new HashMap();
                                    post.put("title","#"+datas.child("index").getValue().toString()+" "+datas.child("title").getValue().toString() );
                                    //System.out.println(datas.child("about").getValue().toString());
                                    post.put("content",datas.child("about").getValue().toString());
                                    mList.add(post);
                                    if(i>99)
                                        break;
                                }

                            }


                        }
                        mSimpleAdapter = new SimpleAdapter(SearchActivity.this,mList,R.layout.list_item_layout,new String[]{"title","content"},new int[]{R.id.tv_title,R.id.tv_content});
                        lv_content.setAdapter(mSimpleAdapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

            }
        });

        lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String,Object> map = mList.get(i);
                String title = (String) map.get("title");
                Toast.makeText(SearchActivity.this,"You pressed " + title,Toast.LENGTH_SHORT).show();
                Intent contentActivity= new Intent(getApplicationContext(), ContentActivity.class);
                contentActivity.putExtra("USER",user);
                int index=Integer.parseInt(getIndex(title));
                contentActivity.putExtra("INDEX",(Serializable) index);
                startActivity(contentActivity);


            }

        });




    }

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