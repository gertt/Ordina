package com.ordinefacile.root.ordinefacile.ui.menu;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseHelper;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseOperations;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseOperationsImp;
import com.ordinefacile.root.ordinefacile.data.db.Orders;
import com.ordinefacile.root.ordinefacile.data.network.ApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.AppApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesModel;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesDataModel;

import java.sql.SQLException;
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
    DatabaseOperationsImp dbOperations;
    Gson gson = new Gson();
    Context context;

    Orders orders;
    DatabaseHelper databaseHelper;


    List<CategoriesDataModel> feedItemList;

    public MenuPresenter(MenuActivity menuActivity,Context context) {
        this.menuActivity = menuActivity;
        this.context = context;
        apiHelper = new AppApiHelper();
        dbOperations = new DatabaseOperationsImp(this.menuActivity);
        orders = new Orders();
        databaseHelper = new DatabaseHelper(context);
    }

    public void getStoreId() {
        menuActivity.getStoreId();
    }

    public void getStoreCategories(String id) {
        apiHelper.getStoreCategories(Integer.parseInt(id))
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

    public void getListProducts(){
        dbOperations.read().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Orders>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("","");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("","");
                    }

                    @Override
                    public void onNext(List<Orders> orders) {
                        Log.d("","");

                        for (int i=0;i<orders.size();i++){

                            String ss = orders.get(i).getmName();

                        }
                    }

                });
    }



    public void inserData(){



        orders.setmName("sss");
        orders.setmReferenceID("ss");
        orders.setmQuantity("sss");
        orders.setmUserOrder("sss");

        try {
            databaseHelper.getUserDao().createOrUpdate(orders);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        dbOperations.create(orders).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Orders>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("","");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("","");
                    }

                    @Override
                    public void onNext(List<Orders> orders) {
                        Log.d("","");

                        for (int i=0;i<orders.size();i++){

                            String ss = orders.get(i).getmName();

                        }
                    }

                });
    }

    public void insertData() {

        try {
            orders.setmName("222");
            orders.setmReferenceID("222");
            orders.setmQuantity("333");
            orders.setmUserOrder("wwww");
            databaseHelper.getUserDao().create(orders);
        } catch (java.sql.SQLException e) {

            Toast.makeText(context,"ssss"+e,Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
