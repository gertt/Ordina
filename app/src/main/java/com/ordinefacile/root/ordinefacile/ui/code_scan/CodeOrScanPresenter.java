package com.ordinefacile.root.ordinefacile.ui.code_scan;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.common.StringUtils;
import com.ordinefacile.root.ordinefacile.data.network.ApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.AppApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.model.PinModel;
import com.ordinefacile.root.ordinefacile.data.network.model.QrCodeModel;
import com.ordinefacile.root.ordinefacile.data.network.model.VauchePinModel;
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

  public void checkCharacter(String pin_voucher) {
    getStoreDetailByPin(pin_voucher);

  }

  public void getStoreDetailByPin(String pin) {


    if (pin.equalsIgnoreCase("")||pin==null){
      codeOrScanActivity.pinInvalid();
    }else {

      apiHelper.getStoreDetailsPin(pin)
              .subscribeOn(Schedulers.newThread())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Subscriber<PinModel>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                  Log.e("Problem : ", e.getMessage());
                  codeOrScanActivity.pinInvalid();

                }

                @Override
                public void onNext(PinModel pinModel) {
                  // Log.d("Next  : ", pinModel.getData().getName());
                  if(pinModel.getError() == false){
                    //  String id = gson.toJson(pinModel.getData().getStoreId());
                    String id = pinModel.getData().getStoreId().toString();
                    saveData.saveDeliveryStatus(pinModel.getData().getDelivery().toString());

                    saveData.saveEntity(pinModel.getData().getId().toString());

                    saveData.saveQrCode(pin);
                    if (pinModel.getData().getStore().getPhone1()!=null){
                      saveData.saveNumberCall(pinModel.getData().getStore().getPhone1());

                    }else {
                      saveData.saveNumberCall("");
                    }

                    saveData.saveStoreId(id);

                    codeOrScanActivity.goToMenuAtivity();



                    System.out.println("imazhi "+id);
                  }else if (pinModel.getError() ==true){

                    codeOrScanActivity.pinInvalid();
                  }
                }
              });
    }
  }

  public void checkForLanguage() {
    if(saveData.getLanguage() != null){
      if(saveData.getLanguage().equalsIgnoreCase("it")){
        codeOrScanActivity.getAppLanguageIt();
      }else if(saveData.getLanguage().equalsIgnoreCase("en")){
        codeOrScanActivity.getAppLanguageEn();
      }
    }
  }

    public void checkSharePreference() {
    if(!saveData.getQrCode().equalsIgnoreCase("")||saveData.get){

      codeOrScanActivity.goToMenuAtivitys();
    }

    }
}
