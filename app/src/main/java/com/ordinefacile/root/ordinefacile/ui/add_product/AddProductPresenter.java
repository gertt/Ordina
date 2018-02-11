package com.ordinefacile.root.ordinefacile.ui.add_product;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseHelper;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseOperationsImp;
import com.ordinefacile.root.ordinefacile.data.db.Orders;
import com.ordinefacile.root.ordinefacile.data.network.ApiHelper;

import java.sql.SQLException;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Eljo on 2/11/2018.
 */

public class AddProductPresenter {


    AddProductActivity addProductActivity;
    Context context;

    ApiHelper apiHelper;
    DatabaseOperationsImp dbOperations;
    Gson gson = new Gson();
    Orders orders;
    DatabaseHelper databaseHelper;

    public AddProductPresenter(AddProductActivity addProductActivity, Context context) {
        this.addProductActivity = addProductActivity;
        this.context = context;
        orders = new Orders();
        dbOperations = new DatabaseOperationsImp(context);
        databaseHelper = new DatabaseHelper(context);
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
