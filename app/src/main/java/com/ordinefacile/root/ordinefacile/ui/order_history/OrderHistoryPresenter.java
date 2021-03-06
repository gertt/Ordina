package com.ordinefacile.root.ordinefacile.ui.order_history;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.table.TableUtils;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseHelper;
import com.ordinefacile.root.ordinefacile.data.db.order.Orders;
import com.ordinefacile.root.ordinefacile.data.network.ApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.AppApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesDataModel;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistory;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistoryData;
import com.ordinefacile.root.ordinefacile.data.network.model.QrCodeModel;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by user on 3/12/2018.
 */

public class OrderHistoryPresenter {

    Context context;
    ApiHelper apiHelper;
    SaveData saveData;

    List<OrderHistoryData> feedItemList;
    OrderHistoryActivity orderHistoryActivity;


    public OrderHistoryPresenter(Context context, OrderHistoryActivity orderHistoryActivity) {
        this.context = context;
        this.orderHistoryActivity = orderHistoryActivity;
        apiHelper = new AppApiHelper();
        saveData = new SaveData(context);
    }


    public void getOrderHistory() {

        apiHelper.getOrderHistory(saveData.getTokenFcm())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderHistory>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Problem : ", e.getMessage());
                        orderHistoryActivity.errorLoading();

                    }

                    @Override
                    public void onNext(OrderHistory qrCodeModel) {
                        Log.d("Next  : ", qrCodeModel.getMessage());
                        feedItemList = new ArrayList<>();

                        for(int i = 0;i<qrCodeModel.getData().size();i++){

                            feedItemList.add(qrCodeModel.getData().get(i));

                            orderHistoryActivity.listHistoryOrder(feedItemList);
                        }

                    }
                });
    }

}
