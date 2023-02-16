package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.R;
import com.example.softwareproject2.Services.RecipeService;
import com.example.softwareproject2.Services.TempBackendProvider;

import java.io.Serializable;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    // Instance variables.
    private Button mBtnBack, mBtnSearch;
    private EditText mSearchInput;
    private TextView mInputPrompt;

    private RecipeService recipeService;
    private List<Recipe> mFilteredRecipes; // List of recipes matching the given filter.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Connect to layout.
        setContentView(R.layout.activity_search);

        // Connect UI widgets.
        mBtnBack = findViewById(R.id.searchActivityBtnBack);
        mBtnSearch = findViewById(R.id.searchActivityBtnSearch);
        mSearchInput = findViewById(R.id.searchActivityTxtSearch);
        mInputPrompt = findViewById(R.id.searchActivityInputPrompt);

        // Connect RecipeService.
        recipeService = new RecipeService();

        // Establish widget functionalities.
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            /**
             * Calls the openMainActivity() function.
             * @param v - The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            /**
             * Checks if user wrote something in the filter. If the
             *  filter is empty, every recipe will be returned by the
             *  search; otherwise only those recipes matching the
             *  filter will be returned by the search.
             * @param v - The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                // TODO: Move on to SearchResultActivity.

                // Check if input empty.
                String filterInput = mSearchInput.getText().toString();
                if (filterInput.equals("")) {
                    mFilteredRecipes = recipeService.allRecipes();
                }
                else {
                    mFilteredRecipes = recipeService.filteredRecipeList(filterInput);
                }

                // Navigate to SearchResultActivity.
                openSearchResultActivity();
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

    /**
     * Navigates to SearchResultActivity; takes with it the list of
     *  filtered recipes.
     */
    private void openSearchResultActivity() {
        Intent intent = new Intent(this, SearchResultActivity.class);
        intent.putExtra("filteredRecipes", (Serializable) mFilteredRecipes);
        startActivity(intent);
    }
}