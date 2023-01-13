package com.example.softwareproject2.Controllers

import com.example.softwareproject2.Views.MainViewActivity

/**
 * Controller for the Main View.
 * @param mView: The view for which this class is a controller.
 */
class MainViewController {

    // View
    lateinit var mView: MainViewActivity

    /**
     * Constructor for the controller.
     * @param mView: The view for which this class is a controller.
     */
    constructor(mView: MainViewActivity) {
        this.mView = mView
    }
}