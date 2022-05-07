package com.example.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {


    private EditText et_reg_account,et_reg_pass,et_reg_repeat_pass;
    private Button btn_signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_reg_account = findViewById(R.id.et_regAccount);
        et_reg_pass = findViewById(R.id.et_regPassword);
        et_reg_repeat_pass = findViewById(R.id.et_regRepeatPassword);
        btn_signUp = findViewById(R.id.btn_signUp);

    }

}