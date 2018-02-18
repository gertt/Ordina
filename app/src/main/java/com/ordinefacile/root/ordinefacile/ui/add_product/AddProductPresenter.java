package com.ordinefacile.root.ordinefacile.ui.add_product;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseHelper;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseOperationsImp;
import com.ordinefacile.root.ordinefacile.data.db.Orders;
import com.ordinefacile.root.ordinefacile.data.network.ApiHelper;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;

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
    RuntimeExceptionDao<Orders, Integer> userDao;

    ApiHelper apiHelper;
    DatabaseOperationsImp dbOperations;
    Gson gson = new Gson();
    Orders orders;
    DatabaseHelper databaseHelper;
    SaveData saveData;

    public AddProductPresenter(AddProductActivity addProductActivity, Context context) {
        this.addProductActivity = addProductActivity;
        this.context = context;
        orders = new Orders();
        dbOperations = new DatabaseOperationsImp(context);
        databaseHelper = new DatabaseHelper(context);
        userDao = databaseHelper.getRuntimeExceptionDao(Orders.class);
        saveData = new SaveData(context);
    }

    public void inserData(Float final_price, Float quantity, String name, Float price,
                          String metric, String description, String urlImage) {

        orders.setmFinalPrice(final_price);
        orders.setmQuantity(quantity);
        orders.setmName(name);
        orders.setmPrice(price);
        orders.setmMetric(metric);
        orders.setmDescriptions(description);
        orders.setmUrl_Image(urlImage);
        orders.setmUserOrder("USER");


        dbOperations.create(orders).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Orders> () {
                    @Override
                    public void onCompleted() {
                        Log.d("", "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "");
                    }

                    @Override
                    public void onNext(Orders orders) {

                    }

                });

    }

    public boolean update(Float final_price, Float quantity, String name, Float price, String metric, String description, String urlimage) {
        if(checkIfExdist(name) == true){
            UpdateBuilder<Orders, Integer> updateBuilder = userDao.updateBuilder();
            try {
                updateBuilder.where().eq("name",name);
                updateBuilder.updateColumnValue("quantity" ,quantity);
                updateBuilder.updateColumnValue("final_price" ,final_price);
                updateBuilder.update();
                return true;
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
                return false;

            }
        }else if (checkIfExdist(name) == false){
            inserData(final_price ,quantity,name,price,metric,description,urlimage);

        }
        return false;
    }

    public boolean checkIfExdist(String p)
    {
        List<Orders> results = null;
        try {
            results = userDao.queryBuilder().where().eq("name",p).query();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return false;
        }
        if(results.size() == 0){
            return false;
        }else{
            return true;
        }
    }

    public void checknumber() {

        if (!saveData.getNumberCall().equalsIgnoreCase("")){

            addProductActivity.callNumber(saveData.getNumberCall());
        }else {
            addProductActivity.callNumberIncorrect();
        }

    }
}
