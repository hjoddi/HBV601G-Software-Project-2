package com.example.softwareproject2.Networking;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

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
     * Gets a recipe from the backend. TODO
     */
    public void getRecipe() {

    }

}
