package com.ordinefacile.root.ordinefacile.ui.add_product;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseHelper;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseOperations;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseOperationsImp;
import com.ordinefacile.root.ordinefacile.data.db.Orders;
import com.ordinefacile.root.ordinefacile.data.network.APIClient;
import com.ordinefacile.root.ordinefacile.data.network.ApiHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
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


    public void inserData(String quantity, String name, String price, String metric, String description, String urlImage) {

        orders.setmQuantity(quantity);
        orders.setmName(name);
        orders.setmPrice(price);
        orders.setmMetric(metric);
        orders.setmDescriptions(description);
        orders.setmUrl_Image(urlImage);
        orders.setmUserOrder("USER");

      String emri = orders.toString();

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
                      //  Log.d("", "");
                    //    for (int i = 0; i < orders.size(); i++) {
                     //       String ss = orders.get(i).getmName();
                     //   }
                    }

                });

    }

}
