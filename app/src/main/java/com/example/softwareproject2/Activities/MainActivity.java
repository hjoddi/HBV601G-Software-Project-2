package com.example.softwareproject2.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.Model.User;
import com.example.softwareproject2.Networking.NetworkManager;
import com.example.softwareproject2.R;
import com.example.softwareproject2.Services.BackendSingleton;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the view for the "front page" of the app; i.e. the first
 *  view when the app is opened.
 */
public class MainActivity extends AppCompatActivity {

    private Button mBtnGetRecipes; // Temp button for dev purposes.
    private Button mBtnExit, mBtnSearchRecipes, mBtnLogin, mBtnlogout, mBtnFavourites, mBtnSubmitRecipe, mBtnREST;
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
        mBtnLogin = findViewById(R.id.mainActivityBtnLogin);
        mBtnlogout = findViewById(R.id.mainActivityBtnLogout);
        mTextViewGreeting = findViewById(R.id.mainActivityTextViewGreeting);
        mBtnFavourites = findViewById(R.id.mainActivityButtonFavourites);
        mBtnSubmitRecipe = findViewById(R.id.mainActivityBtnSubmitRecipe);
        mBtnREST = findViewById(R.id.mainActivityBtnREST);

        // Show login button if not logged in.
        if(backendInstance.getLoggedIn()==null){
            mBtnlogout.setVisibility(View.GONE);
            mBtnLogin.setVisibility(View.VISIBLE);
            mTextViewGreeting.setVisibility(View.GONE);
            mBtnFavourites.setVisibility(View.GONE);
            mBtnSubmitRecipe.setVisibility(View.GONE);

        }
        else{
            mBtnlogout.setVisibility(View.VISIBLE);
            mBtnLogin.setVisibility(View.GONE);
            mTextViewGreeting.setText("Greetings " + backendInstance.getLoggedIn().getUsername() + "!");
            mTextViewGreeting.setVisibility(View.VISIBLE);
            mBtnFavourites.setVisibility(View.VISIBLE);
            mBtnSubmitRecipe.setVisibility(View.VISIBLE);

            ArrayList<User> backendUsers = backendInstance.getUsers();
        }







        /***** REST CONTROLER FIKT *****/
        mBtnREST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restTest();
            }
        });




















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

        mBtnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backendInstance.logOut();
                openMainActivity();
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
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

        mBtnFavourites.setOnClickListener(v -> {
            openFavouritesActivity();
        });

        mBtnSubmitRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSubmitRecipeActivity();
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

    /***** REST CONTROLER FIKT *****/
    public void restTest() {
        String url = "http://10.0.2.2:8080/restRecipes";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String responseString = response.getString("");
                            System.out.println(responseString);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        requestQueue.add(request);
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

    public void openFavouritesActivity() {
        Intent intent = new Intent(this, FavouritesActivity.class);
        startActivity(intent);
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void openSubmitRecipeActivity() {
        Intent intent = new Intent(this, SubmitRecipeActivity.class);
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