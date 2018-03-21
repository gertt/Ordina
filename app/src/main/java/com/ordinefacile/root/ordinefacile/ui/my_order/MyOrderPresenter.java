package com.ordinefacile.root.ordinefacile.ui.my_order;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseHelper;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseOperationsImp;
import com.ordinefacile.root.ordinefacile.data.db.Orders;
import com.ordinefacile.root.ordinefacile.data.network.ApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.AppApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishes;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishesDatum;
import com.ordinefacile.root.ordinefacile.data.network.model.MyOrderSendJson;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Eljo on 2/1/2018.
 */

public class MyOrderPresenter {

    Context context;
    MyOrderActivity myOrderActivity;
    ApiHelper apiHelper;
    DatabaseOperationsImp dbOperations;
    Orders orders;
    DatabaseHelper databaseHelper;
    RuntimeExceptionDao<Orders, Integer> userDao;
    List<Orders> feedItemList;
    String json_obj22;
    EventBus bus = EventBus.getDefault();
    SaveData saveData;

    public MyOrderPresenter(Context context, MyOrderActivity myOrderActivity) {
        this.context = context;
        this.myOrderActivity = myOrderActivity;
        apiHelper = new AppApiHelper();
        dbOperations = new DatabaseOperationsImp(context);
        orders = new Orders();
        databaseHelper = new DatabaseHelper(context);
        userDao = databaseHelper.getRuntimeExceptionDao(Orders.class);
        saveData = new SaveData(context);
    }

    public void getListProducts() {
        dbOperations.read().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Orders>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("", "");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "");
                    }

                    @Override
                    public void onNext(List<Orders> orders) {
                        Log.d("", "");

                        try {
                            JSONObject jsonObj = new JSONObject();
                            JSONArray jsonArr = new JSONArray();
                            jsonObj.put("table_id", saveData.getIdTable());
                            for (int i = 0; i < orders.size(); i++) {
                                JSONObject pnObj = new JSONObject();
                                pnObj.put("mDescriptions", orders.get(i).getmDescriptions());
                                pnObj.put("mFinalPrice", orders.get(i).getmFinalPrice());
                                pnObj.put("mId", orders.get(i).getmId());
                                pnObj.put("mIdProduct", orders.get(i).getmIdProduct());
                                pnObj.put("mIdTable", orders.get(i).getmIdProduct());
                                pnObj.put("mMetric", orders.get(i).getmMetric());
                                pnObj.put("mName", orders.get(i).getmName());
                                pnObj.put("mPrice", orders.get(i).getmPrice());
                                pnObj.put("mQuantity", orders.get(i).getmQuantity());
                                pnObj.put("mUrl_Image", orders.get(i).getmUrl_Image());
                                jsonArr.put(pnObj);
                                jsonObj.put("order_items", jsonArr);
                            }
                            String json_array = jsonArr.toString();
                            String json_obj = jsonObj.toString();
                            JSONObject jsonAdd = new JSONObject();
                            jsonAdd.put("device_token", saveData.getTokenFcm());
                            jsonAdd.put("brand", Build.MANUFACTURER);
                            jsonAdd.put("model", Build.MODEL);
                            jsonObj.put("device", jsonAdd);
                            String json_array2 = jsonArr.toString();
                            String json_obj2 = jsonObj.toString();
                             json_obj22 = jsonObj.toString();

                            //getMenuDishes(json_obj22);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        feedItemList = new ArrayList<Orders>();
                        for (int i = 0; i < orders.size(); i++) {

                            String ss = orders.get(i).getmName();
                            feedItemList.add(orders.get(i));

                        }

                        myOrderActivity.listAdapter(feedItemList);
                    }
                });
    }


    public void delete(int id) {


        dbOperations.delete2(id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DeleteBuilder<Orders, Integer>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("", "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "");
                    }

                    @Override
                    public void onNext(DeleteBuilder<Orders, Integer> deleteBuilder) {

                        getListProducts();

                        Log.d("", "");

                   //     myOrderActivity.listAdapter(feedItemList);
                    }

                });

    }


    public void sendJson() {

        apiHelper.sendJson(json_obj22)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyOrderSendJson>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Problem : ", e.getMessage());

                    }

                    @Override
                    public void onNext(MyOrderSendJson myorder) {
                            for (int i = 0; i < feedItemList.size(); i++) {

                                String dd = feedItemList.get(i).getmDescr();
                            }

                        myOrderActivity.deleteDatabase("ordinafacile.db");
                        feedItemList.clear();

                        myOrderActivity.listAdapter(feedItemList);

                        Eventlist event = new Eventlist();
                        event.setEmri("myorder_activity");
                        EventBus.getDefault().post(event);


                    }

                });
    }


}