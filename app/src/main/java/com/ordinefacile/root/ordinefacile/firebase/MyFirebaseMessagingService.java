package com.ordinefacile.root.ordinefacile.firebase;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.ui.order_history.OrderHistoryActivity;
import com.ordinefacile.root.ordinefacile.ui.push_history.PushHistoryPresenter;

import java.util.Map;


public class MyFirebaseMessagingService  extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    PushHistoryPresenter pushHistoryPresenter;

    @Override
    public void handleIntent(Intent intent) {
        super.handleIntent(intent);

        // you can get ur data here
        //intent.getExtras().get("your_data_key")


      //  if(getIntent().getExtras() != null && getIntent().getExtras().get("your_data_key") != null) {
       //     String strNotificaiton = getIntent().getExtras().get("your_data_key").toString();
      //  }
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        Map<String, String> data = remoteMessage.getData();

        //you can get your text message here.
        String text= data.get("text");

        sendNotification2( text,"test","7788");

        Log.d(TAG, "FROM:" + remoteMessage.getFrom());
        //Check if the message contains data
        if(remoteMessage.getData() !=null ) {
            Log.d(TAG, "Message data: " + remoteMessage.getData().toString());
            Log.d(TAG, "Message data: " + remoteMessage.getData().toString());



            Object obj = remoteMessage.getData().get("title");
            if (obj != null) {


            }

        }
        //Check if the message contains notification
        if(remoteMessage.getNotification() != null) {
            Log.d(TAG, "Mesage body:" + remoteMessage.getNotification().getBody());
            Log.d(TAG, "Mesage body:" + remoteMessage.getNotification().getBody());
            //  sendNotification2(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle(),"7788");

            sendNotification2( remoteMessage.getNotification().getBody(),"test","7788");
            //  sendNotification2("test","test","7788");
            // sendNotification("order","ready");
        }

    }

    private void sendNotification2(String body,String title,String price) {


     //   pushHistoryPresenter = new PushHistoryPresenter(getApplicationContext());
        //  pushHistoryPresenter.inserData("");
     //   pushHistoryPresenter.inserData(body,title,price);

        //  inserData(body,title,price);

        //   pushHistoryPresenter.inserData("11","55","66");

        Intent notificationIntent = new Intent(this, OrderHistoryActivity.class);
        notificationIntent.setAction("android.intent.action.MAIN");
        notificationIntent.addCategory("android.intent.category.LAUNCHER");

        @SuppressLint("WrongConstant") PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | Notification.FLAG_AUTO_CANCEL);
        //Set sound of notification
        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notifiBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_android)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(notificationSound)
                .setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /*ID of notification*/, notifiBuilder.build());
    }


}

