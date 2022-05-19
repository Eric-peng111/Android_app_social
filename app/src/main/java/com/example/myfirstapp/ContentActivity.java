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

/**
 * @author Zhaoyu Cao
 * This class is about functions in the content page.
 */
public class ContentActivity extends AppCompatActivity {
    private boolean likeOrNot = false;
    private boolean originLike = false;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        FirebaseApp.initializeApp(getBaseContext());

        tv_content_content = findViewById(R.id.tv_content_content);
        tv_title_content = findViewById(R.id.tv_title_content);
        star = (ThumbUpView) findViewById(R.id.tpv);
        starcount = findViewById(R.id.starcount);
        textName = findViewById(R.id.textName);
        textTime = findViewById(R.id.textTime);
        home = (ImageButton) findViewById(R.id.btn_home);
        submit = (ImageButton) findViewById(R.id.btn_submit);
        comment = findViewById(R.id.et_comment);
        delete = findViewById(R.id.deletepost);

        // Get user information from the last activity
        User user = (User) getIntent().getExtras().getSerializable("USER");
        int index = (int) getIntent().getExtras().getSerializable("INDEX");

        // get database reference
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        // setup two reference for post and thumb up
        final DatabaseReference[] post_ref = new DatabaseReference[1];
        final DatabaseReference[] thumb_ref = new DatabaseReference[1];

        /**
         * @author Zhaoyu Cao
         * Tranverse all the data in the firebase
         */
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    // find the post in the firebase and set content to UI
                    if (datas.child("index").getValue().toString().equals(Integer.toString(index))) {
                        post_ref[0] = datas.getRef();
                        tv_content_content.setText(datas.child("about").getValue().toString());
                        tv_title_content.setText(datas.child("title").getValue().toString());
                        textTime.setText(datas.child("time").getValue().toString());
                        textName.setText(datas.child("name").getValue().toString());

                        Integer count = 0;
                        Integer userid = user.getID();

                        //make delete button visible to deletion
                        if (datas.child("_id").getValue().toString().equals(userid.toString())) {
                            delete.setVisibility(View.VISIBLE);
                        }

                        System.out.println("userid:" + user.getID());
                        // get the total number of thumb up
                        for (DataSnapshot t : datas.child("thumbup").getChildren()) {
                            count++;
                            System.out.println(t.getValue().toString());
                            if (t.getValue().toString().equals(userid.toString())) {
                                star.Like();
                                likeOrNot = true;
                                originLike = true;
                                thumb_ref[0] = t.getRef();
                            }
                        }
                        // Set the right star count
                        if (originLike)
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

        /**
         * @author Zhaoyu Cao
         * While clicking on thumb up, star count will add 1, otherwise minus 1.
         */
        star.setOnThumbUp(new ThumbUpView.OnThumbUp() {
            @Override
            public void like(boolean like) {
                if (like) {
                    starcount.setText(String.valueOf(Integer.valueOf(starcount.getText().toString()) + 1));
                    likeOrNot = true;
                } else {
                    starcount.setText(String.valueOf(Integer.valueOf(starcount.getText().toString()) - 1));
                    likeOrNot = false;

                }
            }
        });

        // Make content have the sliding effect
        tv_content_content.setMovementMethod(new ScrollingMovementMethod());

        /**
         * @author Zhaoyu Cao
         * Add the comment and store it to firebase
         */
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = comment.getText().toString();
                // Post new comments to database
                if (s != "") {
                    post_ref[0].child("comments").push().setValue(new Comment(user.getID(), s));
                }
                // Judge if needs to update 'like ' in database
                if (likeOrNot == true && originLike == false) {
                    post_ref[0].child("thumbup").push().setValue(user.getID());
                } else if (likeOrNot == false && originLike == true) {
                    thumb_ref[0].removeValue();
                }

                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                mainActivity.putExtra("USER", user);
                startActivity(mainActivity);
            }
        });

        /**
         * @author Zhaoyu Cao
         * While click home button, we will go to main page and send user information to it.
         */
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                mainActivity.putExtra("USER", user);
                startActivity(mainActivity);
            }
        });

        /**
         * @author Zhaoyu Cao
         * Delete the post belonging to a user.
         */
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // A pop-up window pops up to remind the user
                AlertDialog.Builder builder = new AlertDialog.Builder(ContentActivity.this);
                builder.setIcon(null);
                builder.setTitle("Warning:");
                builder.setMessage("Are you sure to delete it?");


                // Create OK and click events in the dialog box
                builder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        post_ref[0].removeValue();

                        Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();
                        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);

                        mainActivity.putExtra("USER", user);
                        startActivity(mainActivity);
                    }
                }).create().show();

                //Create cancel and click events in the dialog box
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