package com.example.softwareproject2.Fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.softwareproject2.Adapter;
import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.Model.User;
import com.example.softwareproject2.R;
import com.example.softwareproject2.Services.BackendSingleton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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
    private RatingBar mRatingBar;
    private TextInputLayout mCustomCommentArea;
    private Button mSubmitCommentButton;
    private TextInputEditText mCustomComment;

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
        mRatingBar = view.findViewById(R.id.recipeFragmentRatingBar);
        mCustomCommentArea = view.findViewById(R.id.singleRecipeFragmentCommentInputLayout);
        mSubmitCommentButton = view.findViewById(R.id.singleRecipeFragmentCommentButton);
        mCustomComment = view.findViewById(R.id.singleRecipeFragmentCustomComment);

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

            // Access backend.
            BackendSingleton backend = BackendSingleton.getInstance();
/*            backend.getAllUsersFromBackendAndSingleton(getContext());
            backend.getAllRecipesFromBackend(getContext());*/

            // Configure rating bar.
            mRatingBar.setRating(rating);

            // Set image
            if (!recipe.getImageName().equals("customImage")){
                mImageViewRecipeImage.setImageResource(getResources().getIdentifier(recipe.getImageName(),"drawable", getActivity().getPackageName()));
            }
            else {
                Uri imageUri = data.getParcelable("imageUri");
                mImageViewRecipeImage.setImageURI(imageUri);
            }

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

            // Set favourite checkbox and fix rating bar if not logged in.
            User loggedInUser = backend.getLoggedIn();
            if(loggedInUser==null){
                mCheckBoxFavourite.setVisibility(View.GONE);
                mRatingBar.setIsIndicator(true);
                mCustomCommentArea.setVisibility(View.GONE);
                mSubmitCommentButton.setVisibility(View.GONE);
            }else{
                mCheckBoxFavourite.setVisibility(View.VISIBLE);
                mSubmitCommentButton.setVisibility(View.VISIBLE);
                HashSet<Long> loggedInFavourites = loggedInUser.getFavoriteRecipes();
                if(loggedInFavourites.contains(recipe.getId())){
                    mCheckBoxFavourite.setChecked(true);
                }
                if (recipe.getRaters().contains(loggedInUser.getUsername())) {
                    mRatingBar.setIsIndicator(true);
                }
                else {
                    mRatingBar.setIsIndicator(false);
                }
            }

            mCheckBoxFavourite.setOnClickListener(v -> {
                System.out.println("CLICKED!");
                if(mCheckBoxFavourite.isChecked()){
                    loggedInUser.addToFavoriteRecipes(recipe.getId());
                    backend.updateUserOnTheBackendAndSingleton(getContext(),backend.getUser(loggedInUser.getUsername()));

                }
                else{
                    loggedInUser.removeFromFavouriteRecipes(recipe.getId());
                    backend.updateUserOnTheBackendAndSingleton(getContext(),backend.getUser(loggedInUser.getUsername()));
                }
                //System.out.println(loggedInUser.getFavoriteRecipes().toString());
                //backend.updateUserOnTheBackendAndSingleton(getContext(), loggedInUser);
            });

            mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float myRating, boolean fromUser) {
                    backend.rateRecipe(recipe, loggedInUser, (int) myRating);

                    refreshFragment();
                }
            });

            mSubmitCommentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCustomComment.getText().toString() != "") {
                        backend.addComment(recipe, mCustomComment.getText().toString());

                        refreshFragment();
                    }
                }
            });

            // TODO: Replace these test comments with the commentsArray when testing is finished.
            ArrayList<String> test = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                test.add("test" + Integer.toString(i));
            }

            recyclerViewComments.setLayoutManager(new LinearLayoutManager(this.getContext()));
            mAdapter = new Adapter(commentsArray);
            recyclerViewComments.setAdapter(mAdapter);
        }

        return view;
    }

    /**
     * Refreshes the fragment.
     */
    private void refreshFragment() {
        Bundle args = new Bundle();
        args.putSerializable("recipe", recipe);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SingleRecipeFragment newFragment = new SingleRecipeFragment();
        newFragment.setArguments(args);
        fragmentTransaction.replace(R.id.singleRecipeActivityFragmentContainer, newFragment);
        fragmentTransaction.commit();
    }
}