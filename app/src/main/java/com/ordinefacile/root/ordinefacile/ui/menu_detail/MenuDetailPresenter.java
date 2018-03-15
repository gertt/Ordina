package com.ordinefacile.root.ordinefacile.ui.menu_detail;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.ordinefacile.root.ordinefacile.data.network.ApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.AppApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishes;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishesDatum;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;
import com.ordinefacile.root.ordinefacile.ui.add_product.AddProductActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by user on 1/22/2018.
 */

public class MenuDetailPresenter {

    MenuDetailActivity menuDetailActivity;
    ApiHelper apiHelper;
    Gson gson = new Gson();
    Context context;
    MenuDetailAdapter menuDetailAdapter;
    List<MenuDishesDatum> feedItemList;
    SaveData saveData;

    public MenuDetailPresenter( Context context,MenuDetailActivity menuDetailActivity) {
        this.menuDetailActivity = menuDetailActivity;
        this.context = context ;
        apiHelper = new AppApiHelper();
        menuDetailAdapter =  new  MenuDetailAdapter(context,feedItemList,this);
        saveData = new SaveData(context);

    }

    public void getCategoryId() {
        menuDetailActivity.getMenuDetails();
    }

    public void getMenuDishes(String categoryId) {
        apiHelper.getMenuDishes(Integer.parseInt(categoryId))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MenuDishes>() {
                    @Override
                    public void onCompleted() {
                        //
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Problem : ", e.getMessage());
                        menuDetailActivity.dissapearSwipeToRefresh();

                    }

                    @Override
                    public void onNext(MenuDishes menuDishes) {
                        feedItemList = new ArrayList<>();
                        Log.d("size : ", "" + menuDishes.getData().size());
                        if (menuDishes.getError() == false) {
                            for (int i = 0; i < menuDishes.getData().size(); i++) {
                                feedItemList.add(menuDishes.getData().get(i));
                            }
                            menuDetailActivity.getListDished(feedItemList);
                        }
                    }

                });
    }

    public void checkQuantityOrGoActivity(String quantity,String name,
                                          String price, String metric, String description,
                                          String urlImg,String id_product, View v) {

        System.out.print(id_product);
        if (!quantity.equalsIgnoreCase("Add")){

            Intent intent = new Intent(context,AddProductActivity.class);
            intent.putExtra("categoryDetailQuantity", quantity);
            intent.putExtra("categoryDetailName", name);
            intent.putExtra("categoryDetailPrice",price);
            intent.putExtra("categoryDetailMetric", metric);
            intent.putExtra("categoryDetailDescription", description);
            intent.putExtra("categoryDetailUrlImg", urlImg);
            intent.putExtra("categoryDetaiIdProduct", id_product);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }else {
            menuDetailActivity.checkQuantity();

        }
    }

    public void checknumber() {

        if (!saveData.getNumberCall().equalsIgnoreCase("")){

            menuDetailActivity.callNumber(saveData.getNumberCall());
        }else {
            menuDetailActivity.callNumberIncorrect();
        }
    }
}