package com.ordinefacile.root.ordinefacile.ui.scan;

import android.util.Log;

import com.google.gson.Gson;
import com.ordinefacile.root.ordinefacile.data.network.ApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.AppApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.model.QrCodeModel;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 1/20/18.
 */

public class ScannerPresenter {
    private String TAG = "ScannerPresenter ";

    ScannerActivity scannerActivity;
    ApiHelper apiHelper;
    SaveData saveData;
    Gson gson = new Gson();

    public ScannerPresenter(ScannerActivity scannerActivity) {
        this.scannerActivity = scannerActivity;
        apiHelper = new AppApiHelper();
        saveData = new SaveData(scannerActivity);
    }

    public void checkForPermission(){
        scannerActivity.checkIfPermissionNeeded();
    }

    public void requestPermission() {
        scannerActivity.requestPermission();
    }


    public boolean checkPermission() {
        return scannerActivity.checkPermission();
    }

    public void goToMenuAtivity(String id) {
        scannerActivity.goToMenuActivity(id);

    }

    public void getStoreDetail(final String qrCode) {
        System.out.println("qr code : "+qrCode);
        apiHelper.getStoreDetails(qrCode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<QrCodeModel>() {
                    @Override
                    public void onCompleted() {
                        //
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Problem : ", e.getMessage());
                        scannerActivity.qrCodeNotValid();

                    }

                    @Override
                    public void onNext(QrCodeModel qrCodeModel) {
                        Log.d("Next  : ", qrCodeModel.getData().getName());
                        if(qrCodeModel.getError() == false){
                            String id = gson.toJson(qrCodeModel.getData().getStore().getId());
                            if (qrCodeModel.getData().getStore().getPhone1()!=null){

                                saveData.saveNumberCall(qrCodeModel.getData().getStore().getPhone1().toString());
                            }else {
                                saveData.saveNumberCall("");
                            }

                            goToMenuAtivity(id);
                        }
                    }
                });
    }

    public void checkForLanguage() {
        if(saveData.getLanguage() != null){
            if(saveData.getLanguage().equalsIgnoreCase("it")){
                scannerActivity.getAppLanguageIt();
            }else if(saveData.getLanguage().equalsIgnoreCase("en")){
                scannerActivity.getAppLanguageEn();
            }
        }
    }
}
