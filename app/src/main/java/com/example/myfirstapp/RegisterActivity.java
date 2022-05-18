package com.example.myfirstapp;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.ParserToken.Parser.E_evaluate;
import com.example.myfirstapp.ParserToken.Parser.P_evaluate;
import com.example.myfirstapp.ParserToken.Tonkenizer.Tokenizer;
import com.example.myfirstapp.userCollection.User;

public class RegisterActivity extends AppCompatActivity {


    private EditText et_reg_account,et_reg_pass,et_reg_repeat_pass,email,phonenum;
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
        email=findViewById(R.id.et_email);
        phonenum=findViewById(R.id.et_regPassword3);

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
                String input_email=email.getText().toString();
                String phone=phonenum.getText().toString();

                // Create an instance of the tokenizer.
                Tokenizer e_tokenizer = new Tokenizer(input_email);
                Tokenizer p_tokenizer= new Tokenizer(pass);

                // Print out the expression from the parser.
                E_evaluate emailcheck = new E_evaluate(e_tokenizer);
                P_evaluate passwordcheck = new P_evaluate(p_tokenizer);
                try{
                    emailcheck.atdot();
                }catch (E_evaluate.IllegalProductionException e){
                    Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    return;
                }
                try{
                    passwordcheck.password();
                }catch (P_evaluate.IllegalProductionException p){
                    Toast.makeText(RegisterActivity.this,p.getMessage(),Toast.LENGTH_LONG).show();
                    return;
                }


                app = (MyApplication) getApplication();
                app.getUser().insert(new User(app.addID(),name,pass,phone,input_email));

                System.out.println(app.getUser());
                Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_LONG).show();
                Intent mainActivity= new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(mainActivity);
            }
        });


    }

}