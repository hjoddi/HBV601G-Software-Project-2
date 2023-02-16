package com.example.softwareproject2.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.softwareproject2.Controllers.MainActivityController;
import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.Networking.NetworkCallback;
import com.example.softwareproject2.Networking.NetworkManager;
import com.example.softwareproject2.R;

import java.util.List;

/**
 * View for the "front page" of the app; i.e. the first view when the app is opened.
 */
public class MainActivity extends AppCompatActivity {

    private MainActivityController mController;
    private Button mBtnGetRecipes; // Temp button for dev purposes.
    private Recipe[] recipeList;

    /**
     * This method is called when the view is activated.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect to layout.
        setContentView(R.layout.activity_main_view);

        // Connect to controller.
        mController = new MainActivityController(this);

        // Connect UI widgets.
        mBtnGetRecipes = findViewById(R.id.btnGetRecipes);

        // Connect NetworkManager.
        NetworkManager networkManager = NetworkManager.getInstance(this);

        // Use NetworkManager to get recipes. TODO
    }
}