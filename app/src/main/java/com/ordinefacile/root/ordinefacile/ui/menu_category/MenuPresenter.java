package com.ordinefacile.root.ordinefacile.ui.menu_category;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.google.gson.Gson;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseHelper;
import com.ordinefacile.root.ordinefacile.data.db.order.OrdersOperationsImp;
import com.ordinefacile.root.ordinefacile.data.db.order.Orders;
import com.ordinefacile.root.ordinefacile.data.network.ApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.AppApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.model.CallService;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesModel;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesDataModel;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by user on 1/22/2018.
 */

public class MenuPresenter {

    MenuActivity menuActivity;
    ApiHelper apiHelper;
    OrdersOperationsImp dbOperations;
    Gson gson = new Gson();
    Context context;
    Orders orders;
    DatabaseHelper databaseHelper;
    List<CategoriesDataModel> feedItemList;
    SaveData saveData;

    public MenuPresenter(MenuActivity menuActivity,Context context) {
        this.menuActivity = menuActivity;
        this.context = context;
        saveData = new SaveData(context);
        apiHelper = new AppApiHelper();
        dbOperations = new OrdersOperationsImp(this.menuActivity);
        orders = new Orders();
        databaseHelper = new DatabaseHelper(context);

    }

    public void getStoreId() {
        menuActivity.getStoreId();

    }

    public void getStoreCategories(String id) {

        apiHelper.getStoreCategories(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategoriesModel>() {
                    @Override
                    public void onCompleted() {
                        //
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Problem : ", e.getMessage());
                        menuActivity.dissapearSwipeToRefresh();
                    }

                    @Override
                    public void onNext(CategoriesModel categoriesModel) {
                     //  Log.e("onNext : ", categoriesModel);
                        feedItemList = new ArrayList<>();
                        if(categoriesModel.getError() == false){
                            for(int i = 0;i<categoriesModel.getData().size();i++){
                                feedItemList.add(categoriesModel.getData().get(i));
                            }
                            Log.d("size : ",""+categoriesModel.getData().size());
                            menuActivity.getListStoreCategories(feedItemList);
                        }
                    }
                });
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
                                menuActivity.showErrorSending(e.toString());

                            }

                            @Override
                            public void onNext(CallService callService) {




                                if (callService.getError()==true){

                                    menuActivity.showSendingSmsWait();

                                }else  if (callService.getError()==false){

                                    menuActivity.showSendingSms();

                                }


                                String SJSJ = callService.getMessage().toString();
                                String SJuSJ = callService.getMessage().toString();

                            }

                        });

            } catch (JSONException e) {
                e.printStackTrace();
            }


    }
}
