package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.softwareproject2.R;

public class SubmitRecipeActivity extends AppCompatActivity {

    // Instance variables.
    private Button mBtnIngredientAdd, mBtnIngredientRemove, mBtnBack;
    private LinearLayout mIngredientsLayout;
    private int numEditTexts;   // Number of dynamically created EditTexts.

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Connect to layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_recipe);

        // Initialize variables.
        mBtnIngredientAdd = findViewById(R.id.submitRecipeActivityBtnAdd);
        mBtnIngredientRemove = findViewById(R.id.submitRecipeActivityBtnRemove);
        mIngredientsLayout = findViewById(R.id.submitRecipeActivityIngredientLayout);
        numEditTexts = 0;
        mBtnBack = findViewById(R.id.submitRecipeActivityBtnBack);

        // Implement button functionalities.
        mBtnIngredientAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newTextField = new EditText(SubmitRecipeActivity.this);
                newTextField.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                mIngredientsLayout.addView(newTextField);
                numEditTexts++;
            }
        });
        mBtnIngredientRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numEditTexts > 0) {
                    mIngredientsLayout.removeViewAt(mIngredientsLayout.getChildCount() - 1);
                    numEditTexts--;
                }
            }
        });
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
}