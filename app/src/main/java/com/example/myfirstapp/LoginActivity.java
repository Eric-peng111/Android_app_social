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
/**
 * @author Zihan Meng
 * @author Zhaoyu Cao
 * @feature
 * @param
 * @return
 */
public class LoginActivity extends AppCompatActivity {
    private Button btnLogin,btn_goSignUp;
    private EditText etAccount,etPassword;


    MyApplication app= MyApplication.getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //set title to "LOGIN"
        getSupportActionBar().setTitle("LOGIN");

        //Use findViewById function to match component
        btnLogin = findViewById(R.id.btn_login);
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        btn_goSignUp = findViewById(R.id.btn_signUp);

        /**
         * @author Zihan Meng
         * @feature Set setOnClickListener on button, When click btnLogin button, run loginCheck function .
         */
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

        /**
         * @author Zihan Meng
         * @feature Set setOnClickListener on button, When click btn_goSignUp button,  go to RegisterActivity.
         */
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



    private void loginCheck(String account,String password){
        /**
         * @author Zihan Meng
         * @feature Verify user name and password, If success go to mainActivity, else show a toast.
         * @param  String account
         * @param  String password
         */
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