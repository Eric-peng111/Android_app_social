package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
/**
 * @author Zihan Meng
 * @author Enze Peng
 */
public class ManageAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        //set title to "MyPost"
        getSupportActionBar().setTitle("Manage Account");

        //Create an ArrayList to store URLs
        ArrayList<String> accounts = new ArrayList<>();
        accounts.add("Simon");
        accounts.add("EricCarmelo");
        accounts.add("Carmelo");
        accounts.add("Arthur");

        //Create Adapter for listView
        ListView myAccountsList = (ListView) findViewById(R.id.accounts_list);
        ArrayAdapter myAccountssAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,accounts);
        myAccountsList.setAdapter(myAccountssAdapter);

        //when long click on items of list
        myAccountsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String currentClicked = accounts.get(i);
                Toast.makeText(ManageAccountActivity.this,"You long clicked "+currentClicked,Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}