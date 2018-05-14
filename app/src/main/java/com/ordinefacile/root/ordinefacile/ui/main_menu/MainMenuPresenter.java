package com.ordinefacile.root.ordinefacile.ui.main_menu;

import android.content.Context;
import android.util.Log;

import com.ordinefacile.root.ordinefacile.data.network.ApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.AppApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.model.SendOrderModel;
import com.ordinefacile.root.ordinefacile.data.network.model.SendSms;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 1/22/18.
 */

public class MainMenuPresenter {

    MainMenuActivity mainMenuActivity;
    Context context;
    SaveData saveData;
    ApiHelper apiHelper;

    public MainMenuPresenter(MainMenuActivity mainMenuActivity,  Context context) {
        this.mainMenuActivity = mainMenuActivity;
        this.context = context;
        saveData = new SaveData(context);
        apiHelper = new AppApiHelper();

    }

    public void getStoredId() {
        mainMenuActivity.getStoreId();
    }

    public void goToMenu(String id) {
        if (id!=null || !id.equalsIgnoreCase("")){
            mainMenuActivity.goToMenu();
        }
    }


    public void sendSms(String sms) {

        apiHelper.sendSms(sms)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SendSms>() {
                    @Override
                    public void onCompleted() {
                        Log.d("","");

                        mainMenuActivity.showSendingSms();

                    }

                    @Override
                    public void onError(Throwable e) {
                        mainMenuActivity.showErrorSending(e.toString());

                    }

                    @Override
                    public void onNext(SendSms sendSms) {

                    }

                });
    }



}
