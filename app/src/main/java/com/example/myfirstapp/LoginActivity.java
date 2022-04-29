package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText etAccount,etPassword;

    //user name and password for test
    //THIS IS ONLY FOR TEST
    //ONLY REMOVE WHEN "loginCheck" HAS BEEN DONE
    private String userName = "123456";
    private String pass = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //set title to "LOGIN"
        getSupportActionBar().setTitle("LOGIN");

        btnLogin = findViewById(R.id.btn_login);
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);

        //set onclick event on LOGIN button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = etAccount.getText().toString();
                System.out.println(account);
                String password = etPassword.getText().toString();
                System.out.println(password);
                //go to check password and username
                loginCheck(account,password);
            }
        });

    }

    //simple login check method
    //TODO Modify this method to read user names and information from existing data structures
    private void loginCheck(String account,String password){
        //user name match
        if (TextUtils.equals(account,userName)){
            //password match
            if (TextUtils.equals(password,pass))
                {
                    System.out.println("success");
                    Toast.makeText(LoginActivity.this,"Login success",Toast.LENGTH_SHORT).show();
                    Intent mainActivity= new Intent(this, MainActivity.class);
                    startActivity(mainActivity);
                }
            else{
                System.out.println("failed");
                Toast.makeText(LoginActivity.this,"Wrong password!",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            System.out.println("failed");
            Toast.makeText(LoginActivity.this,"Wrong username",Toast.LENGTH_SHORT).show();
        }
    }

}