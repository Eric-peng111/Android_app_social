package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.userCollection.User;

/**
 * @author Zhaoyu Cao
 * This class is to achieve login function.
 */
public class LoginActivity extends AppCompatActivity {
    private Button btnLogin,btn_goSignUp;
    private EditText etAccount,etPassword;

    // Introduce global variables
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
         * @author Zhaoyu Cao
         * Set setOnClickListener on button, When click btnLogin button, run loginCheck function .
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
         * @author Zhaoyu Cao
         * Set setOnClickListener on button, When click btn_goSignUp button,  go to RegisterActivity.
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

    /**
     * @author Zhaoyu Cao
     * Set setOnClickListener on button, When click btn_goSignUp button,  go to RegisterActivity.
     * @param userName input username
     * @param password input password
     * @return true while finding username and password, otherwise false
     */
    private boolean check(String userName,String password){
        if(app.getUser().find(new User(userName,password))==null)
            return false;

        return true;
    }


    /**
     * @author Zhaoyu Cao
     * Verify user name and password, If success go to mainActivity, else show a toast.
     * @param account input username
     * @param password input password
     * go to mainActivity while verifying username and password successfully, and pass the user's information to the next activity
     * otherwise show a toast saying wrong password.
     */
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
            System.out.println("failed");
            Toast.makeText(LoginActivity.this,"Wrong password!",Toast.LENGTH_SHORT).show();
        }

    }

}