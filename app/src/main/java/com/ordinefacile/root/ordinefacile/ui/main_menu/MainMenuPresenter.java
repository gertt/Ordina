package com.ordinefacile.root.ordinefacile.ui.main_menu;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;
import com.ordinefacile.root.ordinefacile.data.network.ApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.AppApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.model.CallService;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;

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
    String json_obj1;

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


    public void callService() {


        try {
            JSONObject jsonObj = new JSONObject();

            jsonObj.put("device_token", saveData.getTokenFcm());
            jsonObj.put("brand", Build.MANUFACTURER);
            jsonObj.put("model", Build.MODEL);

            String json_obj = jsonObj.toString();

        apiHelper.callService("CN-562D",json_obj)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CallService>() {
                    @Override
                    public void onCompleted() {
                        Log.d("","");

                        mainMenuActivity.showSendingSms();

                    }

                    @Override
                    public void onError(Throwable e) {
                      //  mainMenuActivity.showErrorSending(e.toString());

                    }

                    @Override
                    public void onNext(CallService callService) {


                        String SJSJ = callService.getMessage().toString();
                        String SJuSJ = callService.getMessage().toString();

                        String SJgSJ = callService.toString();
                        String SJguhSJ = callService.toString();
                        JSONObject jsonObject = null;

                        Log.e("TAG", "response 33: "+new Gson().toJson(CallService.body()) );

                        try {
                            jsonObject = new JSONObject(SJSJ);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println("eraldii "+ jsonObject);
                        // JSONObject object1 = jsonObject.getJSONObject("status");

                        //JSONObject object2 = object1.getJSONObject("status");
                        try {
                            String status = jsonObject.getString("status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
