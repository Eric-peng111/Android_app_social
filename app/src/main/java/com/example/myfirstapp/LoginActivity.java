package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.userCollection.User;

//import com.google.firebase.FirebaseApp;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin,btn_goSignUp;
    private EditText etAccount,etPassword;


    MyApplication app= MyApplication.getApplication();

//    //user name and password for test
//    //THIS IS ONLY FOR TEST
//    //ONLY REMOVE WHEN "loginCheck" HAS BEEN DONE
//    private String userName = "123456";
//    private String pass = "123";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        FirebaseApp.initializeApp(getBaseContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //set title to "LOGIN"
        getSupportActionBar().setTitle("LOGIN");

        btnLogin = findViewById(R.id.btn_login);
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        btn_goSignUp = findViewById(R.id.btn_signUp);

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


        btn_goSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"Sign UP",Toast.LENGTH_SHORT).show();
                Intent registerActivity= new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerActivity);
            }
        });


    }


    private boolean check(String userName,String password){
        if(app.getUser().find(new User(userName,password))==null)
            return false;

        return true;
    }

    //simple login check method
    //TODO Modify this method to read user names and information from existing data structures
    private void loginCheck(String account,String password){

        if (check(account,password))
        {
            System.out.println("success");
            Toast.makeText(LoginActivity.this,"Login success",Toast.LENGTH_SHORT).show();
            Intent mainActivity= new Intent(this, MainActivity.class);
            User user=app.getUser().find(new User(account,password)).key;

            user.US.login(account,password);

            mainActivity.putExtra("USER",user);
            startActivity(mainActivity);
        }
        else{
//            app.getUser().traverse1();
            System.out.println("failed");
            Toast.makeText(LoginActivity.this,"Wrong password!",Toast.LENGTH_SHORT).show();
        }

    }

    public void printUser(){
        app.getUser();
    }

}