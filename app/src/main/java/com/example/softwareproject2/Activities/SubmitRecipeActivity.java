package com.example.softwareproject2.Activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.softwareproject2.R;

public class SubmitRecipeActivity extends AppCompatActivity {

    // Instance variables.
    private Button mBtnIngredientAdd, mBtnIngredientRemove, mBtnBack, mBtnSelectImage;
    private LinearLayout mIngredientsLayout;
    private ImageView mImageView;
    private int numEditTexts;   // Number of dynamically created EditTexts.
    private int selectPicture;  // Constant for comparing activity result code.

    // Required code for submitting an image.
    private final ActivityResultLauncher<String> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null) {
                        mImageView.setImageURI(result);
                    }
                }
            }
    );

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
        selectPicture = 200;
        mBtnBack = findViewById(R.id.submitRecipeActivityBtnBack);
        mBtnSelectImage = findViewById(R.id.submitRecipeActivityBtnSelectImage);
        mImageView = findViewById(R.id.submitRecipeActivityImageView);


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
        mBtnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickerLauncher.launch("image/*");
            }
        });
    }


}