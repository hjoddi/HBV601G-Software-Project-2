package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.softwareproject2.R;

public class SearchActivity extends AppCompatActivity {

    // Instance variables.
    private Button mBtnBack, mBtnSearch;
    private EditText mSearchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Connect to layout.
        setContentView(R.layout.activity_search);
        // Connect UI widgets.
        mBtnBack = findViewById(R.id.searchActivityBtnBack);
        mBtnSearch = findViewById(R.id.searchActivityBtnSearch);

    }
}