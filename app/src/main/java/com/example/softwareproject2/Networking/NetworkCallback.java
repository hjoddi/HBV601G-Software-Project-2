package com.example.softwareproject2.Networking;

public interface NetworkCallback <T> {
    void onSuccess(T result);
    void onFailure(String errorString);
}
