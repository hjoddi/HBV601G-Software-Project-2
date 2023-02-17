package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.R;

/**
 * This class manages the view displaying information about a specific
 *  recipe.
 */
public class SingleRecipeActivity extends AppCompatActivity {

    // Instance variables.
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect to layout.
        setContentView(R.layout.activity_single_recipe);

        // Access bundled extras.
        recipe = (Recipe) getIntent().getSerializableExtra("recipe");
    }
}