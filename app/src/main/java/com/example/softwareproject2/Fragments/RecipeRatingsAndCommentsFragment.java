package com.example.softwareproject2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.softwareproject2.R;

import java.util.ArrayList;
import java.util.HashSet;

public class RecipeRatingsAndCommentsFragment extends Fragment {

    // Instance variables.
    private TextView textViewRating;
    private ListView listViewComments;
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
        listViewComments = view.findViewById(R.id.singleRecipeActivityCommentsListview);

        // Get data.
        Bundle data = getArguments();

        // Display data.
        if (data != null) {
            rating = data.getInt("rating");
            comments = (HashSet<String>) data.getSerializable("comments");
            ArrayList<String> commentsArray = new ArrayList<>(comments);

            textViewRating.setText(Integer.toString(rating));

            ArrayList<String> test = new ArrayList<>();
            test.add("test");

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_list_item_1,
                    test);
            listViewComments.setAdapter(arrayAdapter);

            System.out.println(test.size());

        }

        return view;
    }
}