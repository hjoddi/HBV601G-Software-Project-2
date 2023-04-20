package com.example.softwareproject2.Activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.R;
import com.example.softwareproject2.Services.BackendSingleton;

import java.util.HashSet;

public class SubmitRecipeActivity extends AppCompatActivity {

    // Instance variables.
    private Button mBtnIngredientAdd, mBtnIngredientRemove, mBtnBack, mBtnSelectImage, mBtnSubmitRecipe;
    private LinearLayout mIngredientsLayout;
    private ImageView mImageView;
    private EditText mNameEditText, mInstructionsEditText;
    private int numEditTexts;   // Number of dynamically created EditTexts.
    //private int selectPicture;  // Constant for comparing activity result code.
    private Uri imageUri;
    private BackendSingleton mBackend;

/*    // Required code for submitting an image.
    private final ActivityResultLauncher<String> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null) {
                        imageUri = result;
                        mImageView.setImageURI(result);
                    }
                }
            }
    );*/

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
        //selectPicture = 200;
        mBtnBack = findViewById(R.id.submitRecipeActivityBtnBack);
        //mBtnSelectImage = findViewById(R.id.submitRecipeActivityBtnSelectImage);
        //mImageView = findViewById(R.id.submitRecipeActivityImageView);
        mBtnSubmitRecipe = findViewById(R.id.submitRecipeActivityBtnSubmit);
        mNameEditText = findViewById(R.id.submitRecipeActivityNameEditText);
        mInstructionsEditText = findViewById(R.id.submitRecipeActivityInstructionsEditText);
        mBackend = BackendSingleton.getInstance();


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
/*        mBtnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickerLauncher.launch("image/*");
            }
        });*/
        mBtnSubmitRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRecipe();
            }
        });
    }

    private void submitRecipe() {

        // Gather ingredients.
        HashSet<String> ingredients = new HashSet<>();
        for (int i = 0; i < mIngredientsLayout.getChildCount(); i++) {
            View view = mIngredientsLayout.getChildAt(i);
            if (view instanceof EditText) {
                EditText editText = (EditText) view;
                String text = editText.getText().toString();
                ingredients.add(text);
            }
        }

        // Get name.
        String name = mNameEditText.getText().toString();

        // Get instructions.
        String instructions = mInstructionsEditText.getText().toString();

        // Get image.
        //Uri imageUri = getImageUri();

        // Create Recipe object with gathered information.
        Recipe recipe = new Recipe(name, ingredients, instructions, "placeholder");

        // Add recipe to database.
        mBackend.updateRecipeInTheBackend(this, recipe);
        mBackend.getAllRecipesFromBackend(this);
        //mBackend.saveRecipe(recipe);

        // Create Inten to pass recipe to RecipeDetailActivity.
        Intent intent = new Intent(SubmitRecipeActivity.this, SingleRecipeActivity.class);
        intent.putExtra("recipe", recipe);
        intent.putExtra("imageUri", imageUri);
        startActivity(intent);
    }

    private Uri getImageUri() {
        if (imageUri != null) {
            return imageUri;
        } else {
            Drawable drawable = mImageView.getDrawable();
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Image Description", null);
            return Uri.parse(path);
        }
    }
}