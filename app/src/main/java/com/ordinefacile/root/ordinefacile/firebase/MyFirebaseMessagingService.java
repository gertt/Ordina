package com.ordinefacile.root.ordinefacile.firebase;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;

import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.ui.order_history.OrderHistoryActivity;
import com.ordinefacile.root.ordinefacile.ui.push_history.PushHistoryPresenter;



public class MyFirebaseMessagingService  extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    PushHistoryPresenter pushHistoryPresenter;


    private Context mContext;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


                    Log.d(TAG, "FROM:" + remoteMessage.getFrom());
                    //Check if the message contains data
                    if(remoteMessage.getData().size()>0 ) {
                        Log.d(TAG, "Message data: " + remoteMessage.getData().toString());
                        Log.d(TAG, "Message data: " + remoteMessage.getData().toString());


                     //   sendNotification2(remoteMessage.getData().get("title"),remoteMessage.getData().get("description"), Float.valueOf(remoteMessage.getData().get("price")));
                        createNotification(remoteMessage.getData().get("title"),remoteMessage.getData().get("description"), Float.valueOf(remoteMessage.getData().get("price")));


                    }
                    //Check if the message contains notification
                    if(remoteMessage.getNotification() != null) {
                        Log.d(TAG, "Mesage body:" + remoteMessage.getNotification().getBody());
                        Log.d(TAG, "Mesage body:" + remoteMessage.getNotification().getBody());
                        createNotification77(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

                      //  createNotification77(remoteMessage.getBody().get("title"),remoteMessage.getData().get("description"), Float.valueOf(remoteMessage.getData().get("price")));

                        //  sendNotification2("test","test","7788");
                        // sendNotification("order","ready");
                    }

    }

    private void sendNotification2(String title,String description,Float price) {

                                //L'ordine al tavolo : Tavolo 1, con prezzo totale : 45€, è stato approvato.
        //String currentString = "L'ordine al tavolo : Tavolo 1, con prezzo totale : 23.5€, è stato approvato.";
        String[] separated = description.split(",");

     //   String ZERO = separated[0];
     //  String ZERO1 = separated[1];
       // String ZERO2 = separated[2];
//
      //  String SUPER =  ZERO+ZERO2;
      //  String SUSPER =  ZERO+ZERO2;

        float b= 3.6f;

        pushHistoryPresenter = new PushHistoryPresenter(getApplicationContext());
        pushHistoryPresenter.inserData("tittle","test",b);


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
                .setContentTitle("tittle")
                .setContentText("Price: "+"646464")
                .setAutoCancel(true)
                .setSound(notificationSound)
                .setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /*ID of notification*/, notifiBuilder.build());
    }

    public void createNotification(String title, String description,Float price) {

        String[] separated = description.split(",");

        String ZERO = separated[0];
        String ZERO1 = separated[1];
        String ZERO2 = separated[2];


        String SUPER =  ZERO+ZERO2;
        String SUSPER =  ZERO+ZERO2;

        pushHistoryPresenter = new PushHistoryPresenter(getApplicationContext());
        pushHistoryPresenter.inserData(title,SUSPER,price);


        /**Creates an explicit intent for an Activity in your app**/
        Intent resultIntent = new Intent(this , OrderHistoryActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,
                0 /* Request code */, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_android);
        mBuilder.setContentTitle(title)
                .setContentText(SUSPER)
                .setAutoCancel(false)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPendingIntent);

        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(0 /* Request Code */, mBuilder.build());
    }


    public void createNotification77(String title, String description) {

      //  String[] separated = description.split(",");

      //  String ZERO = separated[0];
      //  String ZERO1 = separated[1];
    //   String ZERO2 = separated[2];


    //    String SUPER =  ZERO+ZERO2;
   //     String SUSPER =  ZERO+ZERO2;

   //     pushHistoryPresenter = new PushHistoryPresenter(getApplicationContext());
    //    pushHistoryPresenter.inserData(title,SUSPER,price);


        /**Creates an explicit intent for an Activity in your app**/
        Intent resultIntent = new Intent(this , OrderHistoryActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,
                0 /* Request code */, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_android);
        mBuilder.setContentTitle(title)
                .setContentText(description)
                .setAutoCancel(false)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPendingIntent);

        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(0 /* Request Code */, mBuilder.build());
    }
}



