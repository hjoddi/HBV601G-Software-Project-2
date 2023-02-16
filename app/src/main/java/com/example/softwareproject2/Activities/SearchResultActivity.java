package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.R;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    // Instance variables.
    List<Recipe> mFilteredRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect to layout.
        setContentView(R.layout.activity_search_result);

        // Access bundled extras.
        mFilteredRecipes = (List<Recipe>) getIntent().getSerializableExtra("filteredRecipes");
    }
}