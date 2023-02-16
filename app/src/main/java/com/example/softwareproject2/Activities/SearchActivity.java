package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.R;
import com.example.softwareproject2.Services.TempBackendProvider;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    // Instance variables.
    private Button mBtnBack, mBtnSearch;
    private EditText mSearchInput;
    private List<Recipe> recipeBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Connect to layout.
        setContentView(R.layout.activity_search);

        // Connect UI widgets.
        mBtnBack = findViewById(R.id.searchActivityBtnBack);
        mBtnSearch = findViewById(R.id.searchActivityBtnSearch);

        // Initialize recipeBank. TODO: Decide if this stays or goes elsewhere.
        TempBackendProvider TBP = TempBackendProvider.INSTANCE;
        recipeBank = TBP.getRecipes();

        // Establish widget functionalities.
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    /**
     * Navigates to MainActivity and finishes the current activity.
     */
    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}