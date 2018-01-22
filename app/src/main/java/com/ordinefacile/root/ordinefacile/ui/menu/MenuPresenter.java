package com.ordinefacile.root.ordinefacile.ui.menu;

import android.util.Log;

import com.google.gson.Gson;
import com.ordinefacile.root.ordinefacile.data.network.ApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.AppApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.model.StoreCategories;
import com.ordinefacile.root.ordinefacile.data.network.model.StoreCategoriesData;
import com.ordinefacile.root.ordinefacile.ui.mainmenu.MainMenuActivity;

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
    Gson gson = new Gson();

    List<StoreCategoriesData> feedItemList;

    public MenuPresenter(MenuActivity menuActivity) {
        this.menuActivity = menuActivity;
        apiHelper = new AppApiHelper();
    }




    public void getStoreId() {
        menuActivity.getStoreId();
    }

    public void getStoreCategories(String id) {
        apiHelper.getStoreCategories(Integer.parseInt(id))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StoreCategories>() {
                    @Override
                    public void onCompleted() {
                        //
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Problem : ", e.getMessage());
                    }

                    @Override
                    public void onNext(StoreCategories storeCategories) {
                      // Log.e("onNext : ", storeCategories);
                        feedItemList = new ArrayList<>();
                        Log.d("size : ",""+storeCategories.getData().size());
                        for(int i = 0;i<storeCategories.getData().size();i++){
                            feedItemList.add(storeCategories.getData().get(i));
                        }
                        menuActivity.getListStoreCategories(feedItemList);
                    }

                });
    }

}
