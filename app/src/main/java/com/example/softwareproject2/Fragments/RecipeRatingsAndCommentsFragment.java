package com.example.softwareproject2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.softwareproject2.R;

import java.util.HashSet;

public class RecipeRatingsAndCommentsFragment extends Fragment {

    // Instance variables.
    private TextView textViewRating;
    private int rating;
    private HashSet<String> comments;

    public RecipeRatingsAndCommentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_ratings_and_comments, container, false);

        // Connect UI widgets.
        textViewRating = view.findViewById(R.id.singleRecipeActivityRatingTextview);

        // Get data.
        Bundle data = getArguments();

        if (data != null) {
            rating = data.getInt("rating");
            comments = (HashSet<String>) data.getSerializable("comments");

            textViewRating.setText(Integer.toString(rating));
        }

        return view;
    }
}