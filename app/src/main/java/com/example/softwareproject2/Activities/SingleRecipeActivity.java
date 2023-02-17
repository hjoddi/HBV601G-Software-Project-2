package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.R;

/**
 * This class manages the view displaying information about a specific
 *  recipe.
 */
public class SingleRecipeActivity extends AppCompatActivity {

    // Instance variables.
    private Recipe recipe;
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