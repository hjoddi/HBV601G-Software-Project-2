package com.example.softwareproject2.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.softwareproject2.Controllers.MainActivityController;
import com.example.softwareproject2.R;

/**
 * View for the "front page" of the app; i.e. the first view when the app is opened.
 */
public class MainActivity extends AppCompatActivity {

    private MainActivityController mController;

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
    }
}