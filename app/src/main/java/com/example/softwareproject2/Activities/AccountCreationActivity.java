package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.softwareproject2.R;
import com.example.softwareproject2.Services.BackendSingleton;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This class manages the view for the creation of user accounts.
 */
public class AccountCreationActivity extends AppCompatActivity {

    private Button mBtnBack, mBtnCreateAccount;
    private EditText mEditTextUsername, mEditTextPassword;
    private TextView mTextViewUsernameTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Connect to layout.
        setContentView(R.layout.activity_account_creation_page);

        // Connect UI widgets.
        mBtnBack = findViewById(R.id.AccountCreationActivityBtnBack);
        mBtnCreateAccount = findViewById(R.id.AccountCreationActivityBtnCreateAccount);
        mEditTextUsername = findViewById(R.id.AccountCreationActivityTextUsername);
        mEditTextPassword = findViewById(R.id.AccountCreationActivityTextPassword);
        mTextViewUsernameTaken = findViewById(R.id.AccountCreationActivityTextViewUsernameTakenError);

        // Hide UI error text
        mTextViewUsernameTaken.setVisibility(View.GONE);

        mBtnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBtnCreateAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Get the fake backend
                BackendSingleton backend = BackendSingleton.getInstance();
                // Try to log in
                String usr = mEditTextUsername.getText().toString();
                String pw = mEditTextPassword.getText().toString();
                if(backend.isValidUsername(usr)){
                    backend.setLoggedIn(usr, pw);
                    backend.addUserToBackendAndSingleton(usr, pw, getApplicationContext());
                    openMainActivity();
                }
                else{
                    mTextViewUsernameTaken.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
