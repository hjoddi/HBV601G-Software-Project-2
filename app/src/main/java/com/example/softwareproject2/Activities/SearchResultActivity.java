package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the view for the results of the user's search for
 *  recipes.
 */
public class SearchResultActivity extends AppCompatActivity {

    // Instance variables.
    List<Recipe> mFilteredRecipes; // The filtered recipes.
    ArrayList<String> mFilteredRecipesNames; // List of recipe names to display.
    private ListView mListView; // ListView that will display the recipes.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect to layout.
        setContentView(R.layout.activity_search_result);

        // Connect UI widgets.
        mListView = findViewById(R.id.searchResultActivityListView);

        // Access bundled extras.
        mFilteredRecipes = (List<Recipe>) getIntent().getSerializableExtra("filteredRecipes");

        // Populate mListView.
        //      Gather the names of the filtered recipes.
        mFilteredRecipesNames = new ArrayList<>();
        for (int i = 0; i < mFilteredRecipes.size(); i++) {
            mFilteredRecipesNames.add(mFilteredRecipes.get(i).getName());
        }
        //      Create and apply the ArrayAdapter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, mFilteredRecipesNames
        );
        mListView.setAdapter(arrayAdapter);
    }
}