package com.ordinefacile.root.ordinefacile.ui.code_scan;

import android.app.AlertDialog;
import android.content.Context;
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
 * Created by Eljo on 2/18/2018.
 */

public class CodeOrScanPresenter {

    Context context;
    CodeOrScanActivity codeOrScanActivity;
    ApiHelper apiHelper;
    SaveData saveData;
    Gson gson = new Gson();

    public CodeOrScanPresenter(Context context, CodeOrScanActivity codeOrScanActivity) {
        this.context = context;
        this.codeOrScanActivity = codeOrScanActivity;
        apiHelper = new AppApiHelper();
        saveData = new SaveData(context);
    }

    public void getStoreDetailByPin(String pin) {

        if (pin.equalsIgnoreCase("")||pin==null){
            codeOrScanActivity.pinInvalid();
        }else {

        apiHelper.getStoreDetails(pin)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<QrCodeModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Problem : ", e.getMessage());
                        codeOrScanActivity.pinInvalid();

                    }

                    @Override
                    public void onNext(QrCodeModel qrCodeModel) {
                        Log.d("Next  : ", qrCodeModel.getData().getName());
                        if(qrCodeModel.getError() == false){
                            String id = gson.toJson(qrCodeModel.getData().getId());
                            String id2 = gson.toJson(qrCodeModel.getData().getQrCode());
                            saveData.saveQr(id2);
                            if (qrCodeModel.getData().getPhone1()!=null){

                                saveData.saveNumberCall(qrCodeModel.getData().getPhone1().toString());
                            }else {
                                saveData.saveNumberCall("");
                            }
                            codeOrScanActivity.goToMenuAtivity(id);
                        }
                    }
                });
    }
    }
}
