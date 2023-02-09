package com.example.softwareproject2.Controllers;

import com.example.softwareproject2.Views.MainActivity;

/**
 * Controller for the Main View.
 */
public class MainActivityController {

    // Instance variables.
    private MainActivity mView; // The view.

    /**
     * Constructor for the controller.
     * @param mainActivity: The view for which this class is a controller.
     */
    public MainActivityController(MainActivity mainActivity) {
        this.mView = mainActivity;
    }
}
