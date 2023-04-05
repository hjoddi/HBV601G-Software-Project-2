package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.softwareproject2.R;
import com.example.softwareproject2.Services.BackendSingleton;

public class AdminActivity extends AppCompatActivity {

    // Instance variables.
    private BackendSingleton mBackend;
    private Button mBtnDeleteUsers, mBtnDeleteRecipes, mBtnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Connect to layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Initialize variables.
        mBackend = BackendSingleton.getInstance();
        mBtnDeleteUsers = findViewById(R.id.adminActivityBtnDeleteUsers);
        mBtnDeleteRecipes = findViewById(R.id.adminActivityBtnDeleteRecipes);
        mBtnMainMenu = findViewById(R.id.adminActivityBtnMainMenu);

        // Button functionalities.
        mBtnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtnDeleteRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBackend.deleteRecipes();
            }
        });
        mBtnDeleteUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBackend.deleteUsers();
            }
        });
    }
}