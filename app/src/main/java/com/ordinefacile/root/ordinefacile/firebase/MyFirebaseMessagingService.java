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



public class MyFirebaseMessagingService  extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    PushHistoryPresenter pushHistoryPresenter;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


                    Log.d(TAG, "FROM:" + remoteMessage.getFrom());
                    //Check if the message contains data
                    if(remoteMessage.getData() !=null ) {
                        Log.d(TAG, "Message data: " + remoteMessage.getData().toString());
                        Log.d(TAG, "Message data: " + remoteMessage.getData().toString());

                sendNotification2(remoteMessage.getData().get("title"),remoteMessage.getData().get("description"), Float.valueOf(remoteMessage.getData().get("price")));

                    }
                    //Check if the message contains notification
                    if(remoteMessage.getNotification() != null) {
                        Log.d(TAG, "Mesage body:" + remoteMessage.getNotification().getBody());
                        Log.d(TAG, "Mesage body:" + remoteMessage.getNotification().getBody());
                       //  sendNotification2(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle(),"7788");

                      //  sendNotification2("test","test","7788");
                        // sendNotification("order","ready");
                    }

    }

    private void sendNotification2(String title,String description,Float price) {


        //String currentString = "L'ordine al tavolo : Tavolo 1, con prezzo totale : 23.5€, è stato approvato.";
        String[] separated = description.split(",");

        String ZERO = separated[0];
        String ZERO1 = separated[1];
        String ZERO2 = separated[2];

        String SUPER =  ZERO+ZERO2;
        String SUSPER =  ZERO+ZERO2;

        pushHistoryPresenter = new PushHistoryPresenter(getApplicationContext());
        pushHistoryPresenter.inserData(title,SUSPER,price);


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
                .setContentText("Price: "+price.toString())
                .setAutoCancel(true)
                .setSound(notificationSound)
                .setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /*ID of notification*/, notifiBuilder.build());
    }

    /*
    {
 "to" : "fFAVZVh4-eo:APA91bGycWL9_35PjRsAynxQnzssbtwiurOa8uGrWIOBpTGWL9uMgmOuYbDBoO_bXrrpnuPJIF_B-seX0T9fsQti_WcFEjh2EBVaOgHrZpM9aRQp2SckwrLosoAkOMZOA5qzjJ0-14nC",
 "collapse_key" : "type_a",
 "data" : {
     "title" : "test77  test99 test",
     "description": "1234567890-0987654321234567890-",
     "price" : "2.66"
 }
}
     */

}

