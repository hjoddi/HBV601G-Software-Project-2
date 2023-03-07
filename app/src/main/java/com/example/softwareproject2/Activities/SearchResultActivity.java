package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.softwareproject2.CustomBaseAdapter;
import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.R;
import com.example.softwareproject2.Services.RecipeService;

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
    private RecipeService mRecipeService;
    private ListView mListView; // ListView that will display the recipes.
    private Button mBtnBack;

    //FOR TESTING
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect to layout.
        setContentView(R.layout.activity_search_result);

        // Initialize RecipeService.
        mRecipeService = new RecipeService();

        // Connect UI widgets.
        mListView = findViewById(R.id.searchResultActivityListView);
        mBtnBack = findViewById(R.id.searchResultActivityBtnBack);

        // Access bundled extras.
        mFilteredRecipes = (List<Recipe>) getIntent().getSerializableExtra("filteredRecipes");

        // Populate mListView.

/*
        //// THE OLD VERSION - WORKS BUT ONLY SHOWS TEXT AND NO IMAGES ////

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
*/

        ArrayList<Integer> recipeImages = new ArrayList<>();
        ArrayList<String> recipeNameList = new ArrayList<>();
        for (Recipe rec:mFilteredRecipes) {
            recipeNameList.add(rec.getName());
            //System.out.println(rec.getName());
            recipeImages.add(getResources().getIdentifier(rec.getImageName(),"drawable",getPackageName()));
        }
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), recipeNameList, recipeImages);
        mListView.setAdapter(customBaseAdapter);


        // Establish widget functionalities.
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            /**
             * Calls the openSearchActivity() function.
             */
            @Override
            public void onClick(View v) {
                openSearchActivity();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Queries the RecipeService for the recipe selected by the user, and
             *  calls the openSingleRecipeActivity method to navigate to the view
             *  displaying the selected recipe.
             * @param parent The AdapterView where the click happened.
             * @param view The view within the AdapterView that was clicked (this
             *            will be a view provided by the adapter)
             * @param position The position of the view in the adapter.
             * @param id The row id of the item that was clicked.
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
                //Recipe r = mRecipeService.findByName(mFilteredRecipesNames.get(position));
                Recipe r = mRecipeService.findByName(recipeNameList.get(position));
                openSingleRecipeActivity(r);
            }
        });
    }

    /**
     * Navigates to the SearchActivity and finishes the current activity.
     */
    public void openSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Navigates to the SingleRecipeActivity.
     * @param recipe - The recipe to be displayed in the SingleRecipeActivity.
     */
    public void openSingleRecipeActivity(Recipe recipe) {
        Intent intent = new Intent(this, SingleRecipeActivity.class);
        intent.putExtra("recipe", recipe);
        startActivity(intent);
    }
}