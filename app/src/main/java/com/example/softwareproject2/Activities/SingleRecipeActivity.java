package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.R;

import java.util.ArrayList;

/**
 * This class manages the view displaying information about a specific
 *  recipe.
 */
public class SingleRecipeActivity extends AppCompatActivity {

    // Instance variables.
    private Recipe recipe;
    private ArrayList<String> mRecipeDescriptions;
    private ListView mListView;
    private Button mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect to layout.
        setContentView(R.layout.activity_single_recipe);

        // Access bundled extras.
        recipe = (Recipe) getIntent().getSerializableExtra("recipe");

        // Connect UI widgets.
        mListView = findViewById(R.id.singleRecipeActivityListView);
        mBtnBack = findViewById(R.id.singleRecipeActivityBtnBack);

        // Populate mListView.
        //      Gather the strings describing the recipe.
        mRecipeDescriptions = new ArrayList<>();
        mRecipeDescriptions.add("Name: " + recipe.getName());
        mRecipeDescriptions.add("Ingredients: " + recipe.getIngredients().toString());
        mRecipeDescriptions.add("Instructions: " + recipe.getInstructions());
        mRecipeDescriptions.add("Rating: " + Integer.toString(recipe.getRating()));
        if (!recipe.getComments().isEmpty()) {
            mRecipeDescriptions.add("Comments: " + recipe.getComments().toString());
        }


        //      Create and apply the ArrayAdapter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, mRecipeDescriptions
        );
        mListView.setAdapter(arrayAdapter);

        // Establish widget functionalities.
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            /**
             * Closes this activity, returning to the search
             *  result view.
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}