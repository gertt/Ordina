package com.ordinefacile.root.ordinefacile.ui.scan;

import android.util.Log;

import com.ordinefacile.root.ordinefacile.data.network.ApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.AppApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.model.Store;

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

    public ScannerPresenter(ScannerActivity scannerActivity) {
        this.scannerActivity = scannerActivity;
        apiHelper = new AppApiHelper();
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

    public void goToMenuAtivity() {
        scannerActivity.goToMenuActivity();
    }

    public void getStoreDetail(String qrCode) {

        apiHelper.getStoreDetails("yak2kzin")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Store>() {
                    @Override
                    public void onCompleted() {
                        //
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Problem : ", e.getMessage());
                    }

                    @Override
                    public void onNext(Store store) {
                        Log.e("Next  : ", store.getData().getName());
                    }

                });
    }
}
