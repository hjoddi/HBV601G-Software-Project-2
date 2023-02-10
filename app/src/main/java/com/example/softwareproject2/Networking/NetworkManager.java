package com.example.softwareproject2.Networking;

public class NetworkManager {

    private static NetworkManager mInstance;

    public static synchronized NetworkManager getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkManager();
        }
        return mInstance;
    }

    private NetworkManager() {
    }
}
