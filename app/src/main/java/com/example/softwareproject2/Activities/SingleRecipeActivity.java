package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.R;
import com.example.softwareproject2.Services.BackendSingleton;

/**
 * This class manages the view displaying information about a specific
 *  recipe.
 */
public class SingleRecipeActivity extends AppCompatActivity {

    // Instance variables.
    private ImageView mImageViewRecipeImage;
    private Recipe recipe;
    private Button mBtnBack;
    private TextView mTextViewRecipeName, mTextViewRecipeInstructions, getmTextViewRecipeIngredients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect to layout.
        setContentView(R.layout.activity_single_recipe);

        // Connect UI widgets.
        mBtnBack = findViewById(R.id.singleRecipeActivityBtnBack);
        mImageViewRecipeImage = findViewById(R.id.singleRecipeActivityImageViewRecipe);
        mTextViewRecipeName = findViewById(R.id.singleRecipeActivityTextVtiewRecipeName);
        mTextViewRecipeInstructions = findViewById(R.id.singleRecipeActivityTextViewRecipeInstructions);
        getmTextViewRecipeIngredients = findViewById(R.id.singleRecipeActivityTextViewRecipeIngredients);


        /*
        ////Old listview version. ////

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
         */



        // Establish widget functionalities.

        // Access bundled extras and (fake) backend.
        recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        BackendSingleton backend = BackendSingleton.getInstance();


        // Set image
        mImageViewRecipeImage.setImageResource(getResources().getIdentifier(recipe.getImageName(),"drawable",getPackageName()));

        // Set name
        mTextViewRecipeName.setText(recipe.getName());

        // Set ingredients
        String ingredientsString = "";
        for (String s: recipe.getIngredients()){
            ingredientsString = ingredientsString + "•" + s + "\n";
        }
        ingredientsString = ingredientsString.substring(0,ingredientsString.length()-1); // remove last newline
        getmTextViewRecipeIngredients.setText(ingredientsString);

        // Set instructions
        mTextViewRecipeInstructions.setText(recipe.getInstructions());

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