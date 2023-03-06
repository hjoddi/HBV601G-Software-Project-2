package com.example.softwareproject2.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softwareproject2.R;
import com.example.softwareproject2.Services.backendSingleton;

public class LoginActivity extends AppCompatActivity {

    private Button mBtnExit;
    private Button mBtnLogin;
    private EditText mEditTextUsername;
    private EditText mEditTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Connect to layout.
        setContentView(R.layout.activity_login_page);

        // Connect UI widgets.
        mBtnExit = findViewById(R.id.loginActivityBtnExit);
        mBtnLogin = findViewById(R.id.loginActivityBtnLogin);
        mEditTextUsername = findViewById(R.id.loginActivityTextUsername);
        mEditTextPassword = findViewById(R.id.loginActivityTextPassword);


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
                backendSingleton backend = backendSingleton.getInstance();
                // Try to log in
                String usr = mEditTextUsername.getText().toString();
                String pw = mEditTextPassword.getText().toString();
                if(backend.logIn(usr,pw)==1){
                    finish();
                }
                else{
                    finish();
                }

            }
        });

    }
}
