package com.ordinefacile.root.ordinefacile.ui.main_menu;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ordinefacile.root.ordinefacile.data.network.ApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.AppApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.model.CallService;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;
import com.ordinefacile.root.ordinefacile.service.StartStopJobs;
import com.ordinefacile.root.ordinefacile.ui.code_scan.CodeOrScanActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    StartStopJobs startStopJobs;


    public MainMenuPresenter(MainMenuActivity mainMenuActivity,  Context context) {
        this.mainMenuActivity = mainMenuActivity;
        this.context = context;
        saveData = new SaveData(context);
        apiHelper = new AppApiHelper();
        startStopJobs = new StartStopJobs(context);

    }

    public void getStoredId() {
        mainMenuActivity.getStoreId();
    }

    public void goToMenu(String id) {
        if (id!=null || !id.equalsIgnoreCase("")){
            mainMenuActivity.goToMenu();
        }
    }

    public void callService() {

        try {
            JSONObject jsonObj = new JSONObject();

            jsonObj.put("device_token", saveData.getTokenFcm());
            jsonObj.put("brand", Build.MANUFACTURER);
            jsonObj.put("model", Build.MODEL);

            String json_obj = jsonObj.toString();

                apiHelper.callService(saveData.getQrCode(),json_obj)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CallService>() {
                    @Override
                    public void onCompleted() {
                        Log.d("","");


                    }

                    @Override
                    public void onError(Throwable e) {
                       mainMenuActivity.showErrorSending(e.toString());

                    }

                    @Override
                    public void onNext(CallService callService) {



                        if (callService.getError()==true){

                            mainMenuActivity.showSendingSmsWait();

                        }else  if (callService.getError()==false){

                            mainMenuActivity.showSendingSms();

                        }


                        String SJSJ = callService.getMessage().toString();
                        String SJuSJ = callService.getMessage().toString();

                    }

                });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void checkCallservice() {
        if (saveData.getNumberCharacter().equalsIgnoreCase("5")){


            mainMenuActivity.callServiceGone();

        }
    }

    public void logout() {
        saveData.ClearAll();
        startStopJobs.cancelJob(StartStopJobs.JOB_TAG);

        mainMenuActivity.goToCodeOrScan();
    }
}
