package com.example.softwareproject2.Networking;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.softwareproject2.Model.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Klasi sem hefur einungis það hlutverk að sækja hluti frá networkinu og skila því
 *  til baka í gegnum callback.
 */
public class NetworkManager {

    // Instance variables.
    private static NetworkManager mInstance;
    private static RequestQueue mQueue;
    private Context mContext;
    private static final String BASE_URL = "10.0.2.2:8080/"; // Tutorial notaði þetta að því
                                                             //  að hann notaði localhost.

    /**
     * Returns the instance of the singleton NetworkManager, or creates the
     *  instance if it doesn't already exist.
     * @param context - The context.
     * @return - The instance of the singleton NetworkManager.
     */
    public static synchronized NetworkManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NetworkManager(context);
        }
        return mInstance;
    }

    /**
     * Constructor for the NetworkManager
     * @param context - The context.
     */
    private NetworkManager(Context context) {
        mContext = context;
        mQueue = getRequestQueue();
    }

    /**
     * Returns the instance of the RequestQueue, or creates it if it doesn't
     *  already exist.
     * @return - A RequestQueue.
     */
    public RequestQueue getRequestQueue() {
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mQueue;
    }

    /**
     * Gets a recipe from the backend. TODO: Fá til að virka.
     * VANDAMÁL: Keyrir aldrei onResponse né onErrorResponse.
     */
    public void getRecipes(NetworkCallback<List<Recipe>> callback) {
        StringRequest request = new StringRequest(
                Request.Method.GET,"10.0.2.2:8080/restRecipes", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Recipe>>() {
                }.getType();
                List<Recipe> recipeBank = gson.fromJson(response, listType);
                callback.onSuccess(recipeBank);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error.toString());
            }
        }
        );
    }

}
