package com.example.myfirstapp;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.userCollection.User;

public class RegisterActivity extends AppCompatActivity {


    private EditText et_reg_account,et_reg_pass,et_reg_repeat_pass;
    private MyApplication app;
    private Button btn_signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_reg_account = findViewById(R.id.et_regAccount);
        et_reg_pass = findViewById(R.id.et_regPassword);
        et_reg_repeat_pass = findViewById(R.id.et_regRepeatPassword);
        btn_signUp = findViewById(R.id.btn_signUp);

        btn_signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name = et_reg_account.getText().toString();
                String pass = et_reg_pass.getText().toString();
                String repeat_pass=et_reg_repeat_pass.getText().toString();



                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(RegisterActivity.this, "username is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(RegisterActivity.this, "password is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!TextUtils.equals(pass,repeat_pass)){
                    Toast.makeText(RegisterActivity.this,"password confirmed failed",Toast.LENGTH_LONG).show();
                    return;
                }
                app = (MyApplication) getApplication();
                app.getUser().insert(new User(app.addID(),name,pass));

                System.out.println(app.getUser());
                Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_LONG).show();
                Intent mainActivity= new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(mainActivity);
            }
        });


    }

}