package com.example.chatdemoproject.service;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by sarabjjeet on 9/28/17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";
    public static final String TOKEN = "token";
    public static String refreshedToken;


    public static String TOKEN_INTENT = "com.example.chatdemoproject.token_received";
    @Override
    public void onTokenRefresh() {
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, "Refreshed token: " + refreshedToken);

        Intent i = new Intent(TOKEN_INTENT);
        i.putExtra(TOKEN, refreshedToken);
       // sendBroadcast(i);
    }
}
