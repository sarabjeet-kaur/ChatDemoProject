package com.example.chatdemoproject.service;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.chatdemoproject.R;
import com.example.chatdemoproject.fragment.ChatFragment;
import com.example.chatdemoproject.fragment.RegisterFragment;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;

/**
 * Created by sarabjjeet on 9/28/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static String MESSAGE_INTENT = "com.example.chatdemoproject.message_received";
    public static String MESSAGE = "message";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // creating notification

        if (applicationInForeground())
            updateChat(remoteMessage);
        else
            generateNotification(remoteMessage);
    }


    /**
     * shows notification.
     */
    private void generateNotification(RemoteMessage remoteMessage){
        Intent i= new Intent(this, ChatFragment.class);
        PendingIntent pi= PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder bn= new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("New Message")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel( true )
                .setContentIntent(pi);
        NotificationManager nm= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, bn.build());
    }

    /**
     * updates chat window
     */
    private void updateChat(RemoteMessage remoteMessage){
        Log.e("message is",remoteMessage.getNotification().getBody());

        if (RegisterFragment.chatListener != null && !ChatFragment.isChatOpen)
            RegisterFragment.chatListener.openChat();

        Intent intent=new Intent(MESSAGE_INTENT);
        intent.putExtra(MESSAGE,remoteMessage.getNotification().getBody());
        sendBroadcast(intent);
    }




    private boolean applicationInForeground() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> services = activityManager.getRunningAppProcesses();
        boolean isActivityFound = false;

        if (services.get(0).processName
                .equalsIgnoreCase(getPackageName())) {
            isActivityFound = true;
        }

        return isActivityFound;
    }
}
