package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.Networking.NetworkCallback;
import com.example.softwareproject2.Networking.NetworkManager;
import com.example.softwareproject2.R;

import java.util.List;

/**
 * View for the "front page" of the app; i.e. the first view when the app is opened.
 */
public class MainActivity extends AppCompatActivity {

    private Button mBtnGetRecipes; // Temp button for dev purposes.
    private List<Recipe> recipeBank; // Temp?

    /**
     * This method is called when the view is activated.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);

        // Connect to layout.
        setContentView(R.layout.activity_main_view);

        // Connect UI widgets.
        mBtnGetRecipes = findViewById(R.id.btnGetRecipes);


        // Connect NetworkManager.
        NetworkManager networkManager = NetworkManager.getInstance(this);

        // Use NetworkManager to get recipes. TODO: Var svona í tutorial. Halda eða henda?
        /*
        networkManager.getRecipes(new NetworkCallback<List<Recipe>>() {
            @Override
            public void onSuccess(List<Recipe> result) {
                recipeBank = result;
                System.out.println("SUCCESS!");
            }

            @Override
            public void onFailure(String errorString) {
                System.out.println("FAILED TO GET RECIPES!");
            }
        });

         */

        // TODO: Fá þetta til að virka.
        // Keyrir aldrei onSuccess né onFailure.
        mBtnGetRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkManager.getRecipes(new NetworkCallback<List<Recipe>>() {
                    @Override
                    public void onSuccess(List<Recipe> result) {
                        recipeBank = result;
                        System.out.println("SUCCESS!");
                    }

                    @Override
                    public void onFailure(String errorString) {
                        System.out.println("FAILED TO GET RECIPES!");
                    }
                });
            }
        });
    }
}