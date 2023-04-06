package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.softwareproject2.R;
import com.example.softwareproject2.Services.BackendSingleton;

public class AdminActivity extends AppCompatActivity {

    // Instance variables.
    private BackendSingleton mBackend;
    private Button mBtnDeleteUsers, mBtnDeleteRecipes, mBtnDeleteUser, mBtnDeleteCommentsOnRecipe, mBtnDeleteRecipe, mBtnMainMenu;
    private TextView mFeedbackTextView;
    private EditText mInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Connect to layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Initialize variables.
        mBackend = BackendSingleton.getInstance();
        mBtnDeleteUsers = findViewById(R.id.adminActivityBtnDeleteUsers);
        mBtnDeleteRecipes = findViewById(R.id.adminActivityBtnDeleteRecipes);
        mBtnMainMenu = findViewById(R.id.adminActivityBtnMainMenu);
        mBtnDeleteUser = findViewById(R.id.adminActivityBtnDeleteUser);
        mBtnDeleteCommentsOnRecipe = findViewById(R.id.adminActivityBtnDeleteCommentsOnRecipe);
        mFeedbackTextView = findViewById(R.id.adminActivityFeedbackTextView);
        mInputEditText = findViewById(R.id.adminActivityEditText);
        mBtnDeleteRecipe = findViewById(R.id.adminActivityBtnDeleteRecipe);

        // Button functionalities.
        mBtnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtnDeleteRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBackend.deleteRecipes();
                mFeedbackTextView.setTextColor(Color.GREEN);
                mFeedbackTextView.setText(getResources().getString(R.string.admin_feedback_recipesDeletedSuccessfully));
            }
        });
        mBtnDeleteUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBackend.deleteUsers();
                mFeedbackTextView.setTextColor(Color.GREEN);
                mFeedbackTextView.setText(getResources().getString(R.string.admin_feedback_usersDeletedSuccessfully));
            }
        });
        mBtnDeleteCommentsOnRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInputEditText.getText().toString().equals("")) {
                    mFeedbackTextView.setTextColor(Color.RED);
                    mFeedbackTextView.setText(getResources().getString(
                            R.string.admin_feedback_noRecipeID));
                }
                else {
                    try {
                        long ID = Long.parseLong(mInputEditText.getText().toString());
                        int deleteCommentsOnRecipe = mBackend.deleteCommentsOnRecipeByID(ID);
                        if (deleteCommentsOnRecipe == 0) {
                            mFeedbackTextView.setText(getResources().getString(
                                    R.string.admin_feedback_noRecipeMatchingID));
                            mFeedbackTextView.setTextColor(Color.RED);
                        }
                        else if (deleteCommentsOnRecipe == 1) {
                            mFeedbackTextView.setTextColor(Color.GREEN);
                            mFeedbackTextView.setText(getResources().getString(
                                    R.string.admin_feedback_commentsOnRecipeDeletedSuccessfully));
                        }
                    } catch (NumberFormatException e) {
                        mFeedbackTextView.setText(getResources().getString(
                                R.string.admin_feedback_recipeIDFormatError));
                        mFeedbackTextView.setTextColor(Color.RED);
                    }
                }
            }
        });
        mBtnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInputEditText.getText().toString().equals("")) {
                    mFeedbackTextView.setTextColor(Color.RED);
                    mFeedbackTextView.setText(getResources().getString(
                            R.string.admin_feedback_noUsername));
                }
                else {
                    String username = mInputEditText.getText().toString();
                    int deleteUser = mBackend.deleteUserByUsername(username);

                    if (deleteUser == 0) {
                        mFeedbackTextView.setTextColor(Color.RED);
                        mFeedbackTextView.setText(getResources().getString(R.string.admin_feedback_noMatchingUsername));
                    }
                    else {
                        mFeedbackTextView.setTextColor(Color.GREEN);
                        mFeedbackTextView.setText(getResources().getString(R.string.admin_feedback_userDeletedSuccessfully));
                    }
                }
            }
        });
        mBtnDeleteRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInputEditText.getText().toString().equals("")) {
                    mFeedbackTextView.setTextColor(Color.RED);
                    mFeedbackTextView.setText(getResources().getString(
                            R.string.admin_feedback_noRecipeID));
                }
                else {
                    try {
                        long ID = Long.parseLong(mInputEditText.getText().toString());
                        int deleteRecipeByID = mBackend.deleteRecipeByID(ID);
                        if (deleteRecipeByID == 0) {
                            mFeedbackTextView.setText(getResources().getString(
                                    R.string.admin_feedback_noRecipeMatchingID));
                            mFeedbackTextView.setTextColor(Color.RED);
                        }
                        else if (deleteRecipeByID == 1) {
                            mFeedbackTextView.setTextColor(Color.GREEN);
                            mFeedbackTextView.setText(getResources().getString(
                                    R.string.admin_feedback_recipeDeletedSuccessfully));
                        }
                    } catch (NumberFormatException e) {
                        mFeedbackTextView.setText(getResources().getString(
                                R.string.admin_feedback_recipeIDFormatError));
                        mFeedbackTextView.setTextColor(Color.RED);
                    }
                }
            }
        });
    }
}