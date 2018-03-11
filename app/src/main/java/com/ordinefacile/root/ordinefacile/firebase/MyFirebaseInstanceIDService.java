package com.ordinefacile.root.ordinefacile.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by ADMIN on 10/6/2017.
 */

public class MyFirebaseInstanceIDService   extends FirebaseInstanceIdService {

   // private Savedata savedata;

    @Override
    public void onTokenRefresh() {


    //    savedata=new Savedata(getApplicationContext());
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("myfirebase", "New Token: " + refreshedToken);
     //   savedata.savetoken_fcm(refreshedToken);

        System.out.print("printotokenin"+refreshedToken);
        //You can save the token into third party server to do anything you want
    }
}