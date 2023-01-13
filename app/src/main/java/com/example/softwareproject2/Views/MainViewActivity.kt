package com.example.softwareproject2.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.softwareproject2.R

/**
 * View for the "front page" of the app; i.e. the first view when the app is opened.
 */
class MainViewActivity : AppCompatActivity() {
    /**
     * This method is called when the view is activated.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Connect to the layout.
        setContentView(R.layout.activity_main_view)
    }
}