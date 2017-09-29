package com.example.chatdemoproject.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.chatdemoproject.R;
import com.example.chatdemoproject.bean.SendMessageModel;
import com.example.chatdemoproject.fragment.ChatFragment;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by sarabjjeet on 9/28/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static String MESSAGE_INTENT = "com.example.chatdemoproject.message_received";
    public static String MESSAGE = "message";
    public static String TYPE_RECEIVED = "recieved";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // creating notification

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


        SendMessageModel sendMessageModel=new SendMessageModel();
        sendMessageModel.setMessageType(TYPE_RECEIVED);
        sendMessageModel.setTextMessage(remoteMessage.getNotification().getBody());

        Bundle bundle=new Bundle();
        bundle.putSerializable(MESSAGE, sendMessageModel);

        Log.e("message is",remoteMessage.getNotification().getBody());

        Intent intent=new Intent(MESSAGE_INTENT);
        intent.putExtra(MESSAGE,bundle);
        sendBroadcast(intent);
    }
}
