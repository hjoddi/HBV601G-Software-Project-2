package com.example.softwareproject2.Networking;

//TODO: Finish this class
public interface NetworkCallback <T> {
    void onSuccess(T result);
    void onFailure(String errorString);
}
