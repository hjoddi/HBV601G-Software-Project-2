package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.Model.User;
import com.example.softwareproject2.Networking.NetworkCallback;
import com.example.softwareproject2.Networking.NetworkManager;
import com.example.softwareproject2.R;
import com.example.softwareproject2.Services.BackendSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the view for the "front page" of the app; i.e. the first
 *  view when the app is opened.
 */
public class MainActivity extends AppCompatActivity {

    private Button mBtnGetRecipes; // Temp button for dev purposes.
    private Button mBtnExit, mBtnSearchRecipes, mbtnLogin, mbtnLogout;
    private TextView mTextViewGreeting;
    private List<Recipe> recipeBank; // Network kallið á að fylla þennan lista af Recipe hlutum.
                                     // TODO: Eyða þessu þegar networkign virkar - recipeBank er temp meðan ég prufa networking.

    /**
     * This method is called when the view is activated.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Connect to layout.
        setContentView(R.layout.activity_main_view);

        // Connect to singleton backend
        BackendSingleton backendInstance = BackendSingleton.getInstance();

        // Connect UI widgets.
        //mBtnGetRecipes = findViewById(R.id.btnGetRecipes);
        mBtnExit = findViewById(R.id.mainActivityBtnExit);
        mBtnSearchRecipes = findViewById(R.id.mainActivityBtnSearch);
        mbtnLogin = findViewById(R.id.mainActivityBtnLogin);
        mbtnLogout = findViewById(R.id.mainActivityBtnLogout);
        mTextViewGreeting = findViewById(R.id.mainActivityTextViewGreeting);

        // Show login button if not logged in.
        if(backendInstance.getLoggedIn()==null){
            mbtnLogout.setVisibility(View.GONE);
            mbtnLogin.setVisibility(View.VISIBLE);
            mTextViewGreeting.setVisibility(View.GONE);

        }
        else{
            mbtnLogout.setVisibility(View.VISIBLE);
            mbtnLogin.setVisibility(View.GONE);
            mTextViewGreeting.setText("Greetings " + backendInstance.getLoggedIn().getUsername() + "!");
            mTextViewGreeting.setVisibility(View.VISIBLE);

            ArrayList<User> backendUsers = backendInstance.getUsers();
            for(User usr:backendUsers){
                if(usr.getUsername().equals("Arnar")){
                    System.out.println(usr.getFavoriteRecipes());
                    System.out.println(usr.getFavoriteRecipes());
                    System.out.println(usr.getFavoriteRecipes());
                    System.out.println(usr.getFavoriteRecipes());

                }
            }

        }




        // Connect NetworkManager. TODO: Eyða þessu þegar networkign virkar
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

        // Establish widget functionalities.

        mbtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backendInstance.logOut();
                openMainActivity();
            }
        });

        mbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

        mBtnExit.setOnClickListener(new View.OnClickListener() {
            /**
             * Exits the application when button is pressed.
             * @param v - The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }
        });
        mBtnSearchRecipes.setOnClickListener(new View.OnClickListener() {
            /**
             * Calls the openSearchActivity() function.
             * @param v - The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                openSearchActivity();
            }
        });




/*        // Keyrist þegar ég ýti á 'GET RECIPES' takkann.
        // TODO: Fá þetta til að virka. Eyða þessu svo þegar networking virkar.
        // Vandamál: Keyrir aldrei onSuccess né onFailure.
        mBtnGetRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ÝTT Á TAKKA");
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
        });*/
    }

    /**
     * Navigates to SearchActivity.
     */
    public void openSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * Prompts the user whether he really wants to exit the app when he presses back button.
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Application")
                .setMessage("Are you sure you want to close the application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAndRemoveTask();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}