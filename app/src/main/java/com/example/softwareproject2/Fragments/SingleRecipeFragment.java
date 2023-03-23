package com.example.softwareproject2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.softwareproject2.Adapter;
import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.Model.User;
import com.example.softwareproject2.R;
import com.example.softwareproject2.Services.BackendSingleton;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Fragment displaying a single recipe.
 */
public class SingleRecipeFragment extends Fragment {

    // Instance variables.
    private Recipe recipe;
    private User user;
    private TextView textViewRating;
    private RecyclerView recyclerViewComments;
    private int rating;
    private HashSet<String> comments;
    private ImageView mImageViewRecipeImage;
    private TextView mTextViewRecipeName, mTextViewRecipeInstructions, getmTextViewRecipeIngredients;
    private CheckBox mCheckBoxFavourite;
    private Adapter mAdapter;

    public SingleRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_ratings_and_comments, container, false);

        // Connect UI widgets.
        textViewRating = view.findViewById(R.id.recipeFragmentRating);
        mImageViewRecipeImage = view.findViewById(R.id.recipeFragmentImageView);
        mTextViewRecipeName = view.findViewById(R.id.recipeFragmentRecipeName);
        mTextViewRecipeInstructions = view.findViewById(R.id.recipeFragmentInstructions);
        getmTextViewRecipeIngredients = view.findViewById(R.id.recipeFragmentIngredients);
        mCheckBoxFavourite = view.findViewById(R.id.recipeFragmentFavCheckbox);
        recyclerViewComments = view.findViewById(R.id.recipeFragmentRecyclerView);

        // Get data bundle.
        Bundle data = getArguments();

        // Display data.
        if (data != null) {

            // Get data from bundle.
            recipe = (Recipe) data.getSerializable("recipe");
            rating = recipe.getRating();
            comments = recipe.getComments();
            ArrayList<String> commentsArray = new ArrayList<>(comments);

            // Connect data to UI.
            textViewRating.setText(Integer.toString(rating));

            BackendSingleton backend = BackendSingleton.getInstance();

            // Set image
            mImageViewRecipeImage.setImageResource(getResources().getIdentifier(recipe.getImageName(),"drawable", getActivity().getPackageName()));

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

            // TODO: Replace these test comments with the commentsArray when testing is finished.
            ArrayList<String> test = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                test.add("test" + Integer.toString(i));
            }

            recyclerViewComments.setLayoutManager(new LinearLayoutManager(this.getContext()));
            mAdapter = new Adapter(test);
            recyclerViewComments.setAdapter(mAdapter);
        }

        return view;
    }
}