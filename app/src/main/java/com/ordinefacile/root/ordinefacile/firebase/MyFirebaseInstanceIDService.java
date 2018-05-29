package com.ordinefacile.root.ordinefacile.firebase;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;

/**
 * Created by user on 4/11/2018.
 */

public class MyFirebaseInstanceIDService   extends FirebaseInstanceIdService {

    private SaveData savedata;

    @Override
    public void onTokenRefresh() {

        //ekTRt0ye_Cs:APA91bFEaEujDUm0o-OyYGONSR-Iwq_tIiARxgK_Q3lYakAO4Dm9ePxbFyrD_zGXSP3_wY2FDm65-py6ReE6q5VqbQ_nD0u4MeKjPqR0qOex6NyiAjZssRHBKyA0LtbwS_MTh83F3Hau

        savedata=new SaveData(getApplicationContext());
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("myfirebase", "New Token: " + refreshedToken);
        savedata.saveTokenFcm(refreshedToken);

        System.out.print("printotokenin"+refreshedToken);
        //You can save the token into third party server to do anything you want
    }
}
