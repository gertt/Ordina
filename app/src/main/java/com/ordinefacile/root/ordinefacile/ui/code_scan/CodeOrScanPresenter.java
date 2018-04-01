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

        char[] cArray = pin_voucher.toCharArray();

        int count = 0;
        int number_character = 0;
        for (int i=0; i < cArray.length; i++){

            count++;
            number_character = count;
        }
        if (number_character==5){


            String str = Integer.toString(number_character);
            saveData.saveNumberCharacter(str);
            System.out.print("kjsjksjks");
            getStoreDetailsByVoucherCode(pin_voucher);

        }else  if(number_character>5){

            String str = Integer.toString(number_character);
            saveData.saveNumberCharacter(str);

            System.out.print("kjsjksjks");
            getStoreDetailByPin(pin_voucher);

        }else  if (number_character<5){

            codeOrScanActivity.pinInvalid();
        }


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
                            String id = gson.toJson(pinModel.getData().getId());

                            saveData.saveDeliveryStatus(pinModel.getData().getDelivery().toString());
                            saveData.saveEntity(pinModel.getData().getId().toString());

                            if (pinModel.getData().getPhone1()!=null){
                                saveData.saveNumberCall(pinModel.getData().getPhone1().toString());

                            }else {
                                saveData.saveNumberCall("");
                            }
                            codeOrScanActivity.goToMenuAtivity(id);
                            System.out.println("imazhi "+id);
                        }
                    }
                });
    }
    }

    public void getStoreDetailsByVoucherCode(String voucher_code) {

        if (voucher_code.equalsIgnoreCase("")||voucher_code==null){
            codeOrScanActivity.pinInvalid();
        }else {

            apiHelper.getStoreDetailsByVoucherCode(voucher_code)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<VauchePinModel>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("Problem : ", e.getMessage());
                            codeOrScanActivity.pinInvalid();

                        }

                        @Override
                        public void onNext(VauchePinModel pinModel) {
                            Log.d("Next  : ", pinModel.getMessage());
                            if(pinModel.getError() == false){
                                String id = gson.toJson(pinModel.getData().getId());

                                saveData.saveDeliveryStatus(pinModel.getData().getDelivery().toString());
                                saveData.saveEntity(pinModel.getData().getId().toString());

                                if (pinModel.getData().getPhone()!=null){
                                    saveData.saveNumberCall(pinModel.getData().getPhone().toString());

                                }else {
                                    saveData.saveNumberCall("");
                                }
                                codeOrScanActivity.goToMenuAtivity(pinModel.getData().getStoreId());

                                System.out.println("imazhi "+id);
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


}
