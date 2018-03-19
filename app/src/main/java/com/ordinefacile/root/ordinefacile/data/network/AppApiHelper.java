package com.ordinefacile.root.ordinefacile.data.network;

import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesModel;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishes;
import com.ordinefacile.root.ordinefacile.data.network.model.MyOrderSendJson;
import com.ordinefacile.root.ordinefacile.data.network.model.PinModel;
import com.ordinefacile.root.ordinefacile.data.network.model.QrCodeModel;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by root on 1/20/18.
 */

public class AppApiHelper implements ApiHelper{

    final API apiService = APIClient.getClient().create(API.class);

    final API apiServiceSend = APIClient.createAPI().create(API.class);

    @Override
    public Observable<QrCodeModel> getStoreDetails(String qrCode) {
        return apiService.getStoreDetails(qrCode);
    }

    @Override
    public Observable<PinModel> getStoreDetailsPin(String pin) {
        return apiService.getStoreDetailsByPin(pin);
    }

    @Override
    public Observable<CategoriesModel> getStoreCategories(int id) {
        return apiService.getStoreCategories(id);
    }

    @Override
    public Observable<MenuDishes> getMenuDishes(int id) {
        return apiService.getMenuDishes(id);
    }

    @Override
    public Observable<MyOrderSendJson> sendJson(String data) {
        return apiServiceSend.sendOrderJson(data);
    }

}
