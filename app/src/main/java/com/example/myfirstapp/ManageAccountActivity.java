package com.example.myfirstapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.myfirstapp.userCollection.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ManageAccountActivity extends AppCompatActivity {
    MyApplication app= MyApplication.getApplication();

    private ListView lv_content;
    private SimpleAdapter mSimpleAdapter;
    private List<Map<String,Object>> mList=new ArrayList<>();
    ArrayList<String>  al=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        al.clear();
        setContentView(R.layout.activity_manage_account);

        lv_content=findViewById(R.id.accounts_list);
        ArrayList<User> u=app.getUser().traverse(app.getUser().getRoot());

//        for(int i=0;i<u.size();i++){
//            al.add(u.get(i).getUsername());
//        }

        Iterator<User> it = u.iterator();
        while(it.hasNext()) {
            User i = it.next();
            al.add(i.getUsername());
        }

        System.out.println(al);

        ArrayAdapter a = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, al);
        lv_content.setAdapter(a);






        lv_content.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int t, long l) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ManageAccountActivity.this);
                builder.setIcon(null);
                builder.setTitle("Warning:");
                builder.setMessage("Are you sure to delete it?");

//              //Create OK and click events in the dialog box

                builder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.out.println(t);
                        User userx=u.get(t);
                        app.getUser().delete(app.getUser().getRoot(),userx);
                        al.remove(t);
                        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, al);


                        lv_content.setAdapter(arrayAdapter);
                    }
                }).create().show();
                //4. Create cancel and click events in the dialog box
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_SHORT).show();
                    }
                }).create().show();

                return false;
            }


        });




    }


}