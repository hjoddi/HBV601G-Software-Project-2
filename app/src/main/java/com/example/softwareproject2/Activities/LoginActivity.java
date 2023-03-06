package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.softwareproject2.Model.User;
import com.example.softwareproject2.R;
import com.example.softwareproject2.Services.BackendSingleton;
import android.widget.EditText;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {

    private Button mBtnExit, mBtnLogin, mBtnCreateAccount;
    private EditText mEditTextUsername, mEditTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Connect to layout.
        setContentView(R.layout.activity_login_page);

        // Connect UI widgets.
        mBtnExit = findViewById(R.id.loginActivityBtnBack);
        mBtnLogin = findViewById(R.id.loginActivityBtnLogin);
        mEditTextUsername = findViewById(R.id.loginActivityTextUsername);
        mEditTextPassword = findViewById(R.id.loginActivityTextPassword);
        mBtnCreateAccount = findViewById(R.id.loginActivityBtnCreateAccount);


        mBtnExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Get the fake backend
                BackendSingleton backend = BackendSingleton.getInstance();
                // Try to log in
                String usr = mEditTextUsername.getText().toString();
                String pw = mEditTextPassword.getText().toString();
                if(backend.logIn(usr,pw)==1){
                    backend.setLoggedIn(usr,pw);
                    openMainActivity();
                }

            }
        });

        mBtnCreateAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openAccountCreationActivity();
            }
        });


    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void openAccountCreationActivity() {
        Intent intent = new Intent(this, AccountCreationActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
