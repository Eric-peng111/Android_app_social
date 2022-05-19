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
            /**
             * @author Yonghao Deng
             * This method is used for new user registration
             */
            @Override
            public void onClick(View view) {
                String name = et_reg_account.getText().toString();
                String pass = et_reg_pass.getText().toString();
                String repeat_pass=et_reg_repeat_pass.getText().toString();



                if (TextUtils.isEmpty(name)) {
                    //If the registration name is empty, a pop-up will appear "username is empty".
                    Toast.makeText(RegisterActivity.this, "username is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    //If the registration password is empty, a pop-up will appear "password is empty".
                    Toast.makeText(RegisterActivity.this, "password is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!TextUtils.equals(pass,repeat_pass)){
                    //If the registration password  is not equal to the repeat password, a pop-up will appear "password confirmed failed".
                    Toast.makeText(RegisterActivity.this,"password confirmed failed",Toast.LENGTH_LONG).show();
                    return;
                }

                // Create an instance of the tokenizer.


                // Print out the expression from the parser.

                String input_email=email.getText().toString();
                String phone=phonenum.getText().toString();
                //This method uses tokenizer to determine the correctness of the registered mailbox format
                try{
                    input_email=email.getText().toString();
                    Tokenizer e_tokenizer = new Tokenizer(input_email);
                    E_evaluate emailcheck = new E_evaluate(e_tokenizer);
                    emailcheck.atdot();
                    //The email address you fill in needs to have @
                }catch (E_evaluate.IllegalProductionException e){
                    Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    return;
                }
                try{
                    pass=et_reg_pass.getText().toString();
                    Tokenizer p_tokenizer= new Tokenizer(pass);
                    P_evaluate passwordcheck = new P_evaluate(p_tokenizer);
                    passwordcheck.password();
                }catch (P_evaluate.IllegalProductionException p){
                    Toast.makeText(RegisterActivity.this,p.getMessage(),Toast.LENGTH_LONG).show();
                    return;
                }


                app = (MyApplication) getApplication();
                app.getUser().insert(new User(app.addID(),name,pass,phone,input_email));

                System.out.println(app.getUser());
                Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_LONG).show();
                //If the format of the above checks are all correct, a registration success message will pop up and the registration will be successful
                Intent mainActivity= new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(mainActivity);
            }
        });


    }

}