package com.example.myfirstapp;

//import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.database_class.Comment;
import com.example.myfirstapp.database_class.Thumbup;
import com.example.myfirstapp.userCollection.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ldoublem.thumbUplib.ThumbUpView;

public class ContentActivity extends AppCompatActivity {

    private boolean likeornot=false;
    private boolean originlike=false;

    private TextView tv_content_content;
    private TextView tv_title_content;
    private TextView textName;
    private TextView textTime;
    private ThumbUpView star;
    public TextView starcount;

    private EditText comment;

    private ImageButton home;
    private ImageButton submit;
    private Button delete;


//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        FirebaseApp.initializeApp(getBaseContext());

        tv_content_content=findViewById(R.id.tv_content_content);
        tv_title_content=findViewById(R.id.tv_title_content);
        star=(ThumbUpView) findViewById(R.id.tpv);
        starcount=findViewById(R.id.starcount);
        textName=findViewById(R.id.textName);
        textTime=findViewById(R.id.textTime);
        home=(ImageButton)findViewById(R.id.btn_home);
        submit=(ImageButton)findViewById(R.id.btn_submit);
        comment = findViewById(R.id.et_comment);
        delete = findViewById(R.id.deletepost);






        User user=(User) getIntent().getExtras().getSerializable("USER");
        int index= (int) getIntent().getExtras().getSerializable("INDEX");



        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        DatabaseReference ref = db.getReference("https://comp2100-6442-4f4de-default-rtdb.asia-southeast1.firebasedatabase.app/");

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
        //setup two reference for
        final DatabaseReference[] post_ref = new DatabaseReference[1];
        final DatabaseReference[] thumb_ref = new DatabaseReference[1];
        ref.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    if(datas.child("index").getValue().toString().equals(Integer.toString(index))){
                        post_ref[0] =datas.getRef();

                        tv_content_content.setText(datas.child("about").getValue().toString());
                        tv_title_content.setText(datas.child("title").getValue().toString());
                        textTime.setText(datas.child("time").getValue().toString());
                        textName.setText(datas.child("name").getValue().toString());

                        Integer count=0;
                        Integer userid= user.getID();
                        //make delete button visible to deletion
                        if(datas.child("_id").getValue().toString().equals(userid.toString())){
                            delete.setVisibility(View.VISIBLE);
                        }

                        System.out.println("userid:"+user.getID());
                        for(DataSnapshot t: datas.child("thumbup").getChildren()){
                            count++;
                            System.out.println(t.getValue().toString());
                            if(t.getValue().toString().equals(userid.toString())){
                                star.Like();
                                likeornot=true;
                                originlike=true;
                                thumb_ref[0]=t.getRef();
                            }

                        }
                        if (originlike)
                            count--;
                        starcount.setText(count.toString());
                        break;
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        star.setOnThumbUp(new ThumbUpView.OnThumbUp() {
            @Override
            public void like(boolean like) {
                if (like) {
                    starcount.setText(String.valueOf(Integer.valueOf(starcount.getText().toString()) + 1));
                    likeornot=true;
                } else {
                    starcount.setText(String.valueOf(Integer.valueOf(starcount.getText().toString()) - 1));
                    likeornot=false;

                }
            }
        });


        tv_content_content.setMovementMethod(new ScrollingMovementMethod());


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = comment.getText().toString();
                //post new comments to database
                if(s!=""){
                    post_ref[0].child("comments").push().setValue(new Comment(user.getID(), s));
                }
                //judge if needs to update 'like ' in database
                if(likeornot==true && originlike==false){
                    post_ref[0].child("thumbup").push().setValue(user.getID());
                }else if(likeornot==false && originlike==true){
                    thumb_ref[0].removeValue();
                }

                Intent mainActivity= new Intent(getApplicationContext(), MainActivity.class);
                mainActivity.putExtra("USER",user);
                startActivity(mainActivity);
            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivity= new Intent(getApplicationContext(), MainActivity.class);
                mainActivity.putExtra("USER",user);
                startActivity(mainActivity);

            }


        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ContentActivity.this);
                builder.setIcon(null);
                builder.setTitle("Warning:");
                builder.setMessage("Are you sure to delete it?");

//              //Create OK and click events in the dialog box

                builder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        post_ref[0].removeValue();

                        Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();
                        Intent mainActivity= new Intent(getApplicationContext(), MainActivity.class);

                        mainActivity.putExtra("USER",user);
                        startActivity(mainActivity);
                    }
                }).create().show();
                //4. Create cancel and click events in the dialog box
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_SHORT).show();
                    }
                }).create().show();
            }
        });
    }
}