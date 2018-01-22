package com.ordinefacile.root.ordinefacile.data.network;

import android.view.Menu;

import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishes;
import com.ordinefacile.root.ordinefacile.data.network.model.Store;
import com.ordinefacile.root.ordinefacile.data.network.model.StoreCategories;

import rx.Observable;

/**
 * Created by root on 1/20/18.
 */

public class AppApiHelper implements ApiHelper{

    final API apiService = APIClient.getClient().create(API.class);

    @Override
    public Observable<Store> getStoreDetails(String qrCode) {
        return apiService.getStoreDetails(qrCode);
    }

    @Override
    public Observable<StoreCategories> getStoreCategories(int id) {
        return apiService.getStoreCategories(id);
    }

    @Override
    public Observable<MenuDishes> getMenuDishes(int id) {
        return apiService.getMenuDishes(id);
    }


}
