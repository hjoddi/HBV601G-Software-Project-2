package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.softwareproject2.Fragments.RecipeRatingsAndCommentsFragment;
import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.Model.User;
import com.example.softwareproject2.R;
import com.example.softwareproject2.Services.BackendSingleton;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class manages the view displaying information about a specific
 *  recipe.
 */
public class SingleRecipeActivity extends FragmentActivity {

    // Instance variables.
    private ImageView mImageViewRecipeImage;
    private Recipe recipe;
    private Button mBtnBack;
    private TextView mTextViewRecipeName, mTextViewRecipeInstructions, getmTextViewRecipeIngredients;
    private CheckBox mCheckBoxFavourite;
    private User loggedInUser;

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
        mCheckBoxFavourite = findViewById(R.id.singleRecipeActivityCheckBoxFavourite);


        /** Establish widget functionalities. **/

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

        // Set favourite checkbox
        User loggedInUser = backend.getLoggedIn();
        if(loggedInUser==null){
            mCheckBoxFavourite.setVisibility(View.GONE);
        }else{
            mCheckBoxFavourite.setVisibility(View.VISIBLE);
            HashSet<String> loggedInFavourites = loggedInUser.getFavoriteRecipes();
            if(loggedInFavourites.contains(recipe.getName())){
                mCheckBoxFavourite.setChecked(true);
            }
        }

        mCheckBoxFavourite.setOnClickListener(v -> {
            if(mCheckBoxFavourite.isChecked()){
                loggedInUser.addToFavoriteRecipes(recipe.getName());
            }
            else{
                loggedInUser.removeFromFavouriteRecipes(recipe.getName());
            }
            //System.out.println(loggedInUser.getFavoriteRecipes().toString());

            //backend.updateUser();
        });


        // Connect Ratings and Comments fragment.
        Fragment fragment = new RecipeRatingsAndCommentsFragment();
        replaceFragment(fragment);




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

    @Override
    public void onResume(){
        super.onResume();
/*        BackendSingleton backend = BackendSingleton.getInstance();
        mCheckBoxFavourite = findViewById(R.id.singleRecipeActivityCheckBoxFavourite);*/
/*        System.out.println(backend.getLoggedIn().getFavoriteRecipes());
        System.out.println(backend.getLoggedIn().getFavoriteRecipes());
        System.out.println(backend.getLoggedIn().getFavoriteRecipes());*/
/*
        String loggedInUserName = loggedInUser.getUsername();
        ArrayList<User> currentUsersInBackend = backend.getUsers();
        for (User usr:currentUsersInBackend) {
            if(usr.getUsername().equals(loggedInUserName)){
                if(usr.getFavoriteRecipes().contains(recipe.getName())){
                    mCheckBoxFavourite.setChecked(true);
                }
            }
        }*/

    }

    /**
     * Replaces the fragment displaying the recipe's rating and comments.
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {

        // Get recipe data to be displayed.
        int rating = recipe.getRating();
        HashSet<String> comments = recipe.getComments();

        // Pass the recipe's rating and comments into the fragment.
        Bundle data = new Bundle();
        data.putInt("rating", rating);
        data.putSerializable("comments", comments);
        fragment.setArguments(data);

        // Display the fragment.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.singleRecipeActivityFragmentContainer, fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commit();
    }



}