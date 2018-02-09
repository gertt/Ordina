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


    public MenuDetailPresenter( Context context,MenuDetailActivity menuDetailActivity) {
        this.menuDetailActivity = menuDetailActivity;
        this.context = context ;
        apiHelper = new AppApiHelper();
        menuDetailAdapter =  new  MenuDetailAdapter(context,feedItemList,this);

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



    public void checkQuantisty(String quantity, View v) {



    }

 /*   public void checkQuantitys(String s, String s1, String s2, String s3, View v) {

        if (!quantity.equalsIgnoreCase("Add")){

            menuDetailAdapter.goToAddActivity();

            Intent intent = new Intent(context,MenuDetailActivity.class);
            intent.putExtra("categoryId", txt_add.getText().toString()+"");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }else {

            menuDetailAdapter.selectQuantity(v);
        }
    }

    public void checkQuantity(String s, String s1, String s2, String s3, String urlImg, View v) {
    }
*/
    public void checkQuantityOrGoActiviDty() {

        Intent intent = new Intent(context,AddProductActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);


    }

    public void checkQuantityOrGoActivity(String quantity,String name, String price, String metric, String description, String urlImg, View v) {

        if (!quantity.equalsIgnoreCase("Add")){

            Intent intent = new Intent(context,AddProductActivity.class);
            intent.putExtra("categoryDetailName", name);
            intent.putExtra("categoryDetailPrice",price);
            intent.putExtra("categoryDetailMetric", metric);
            intent.putExtra("categoryDetailDescription", description);
            intent.putExtra("categoryDetailUrlImg", urlImg);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }else {

            menuDetailAdapter.selectQuantity(v);
        }
    }
}