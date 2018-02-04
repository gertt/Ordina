package com.ordinefacile.root.ordinefacile.ui.menu;

import android.util.Log;

import com.google.gson.Gson;
import com.ordinefacile.root.ordinefacile.data.network.ApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.AppApiHelper;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesModel;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesDataModel;

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

    List<CategoriesDataModel> feedItemList;

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

}
