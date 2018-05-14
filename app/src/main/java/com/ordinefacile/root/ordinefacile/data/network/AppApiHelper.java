package com.ordinefacile.root.ordinefacile.data.network;

import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesModel;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishes;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistory;
import com.ordinefacile.root.ordinefacile.data.network.model.PinModel;
import com.ordinefacile.root.ordinefacile.data.network.model.QrCodeModel;
import com.ordinefacile.root.ordinefacile.data.network.model.SendOrderModel;
import com.ordinefacile.root.ordinefacile.data.network.model.SendSms;
import com.ordinefacile.root.ordinefacile.data.network.model.VauchePinModel;
import rx.Observable;

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
    public Observable<VauchePinModel> getStoreDetailsByVoucherCode(String voucher_code) {
        return apiService.getStoreDetailsByVoucherCode(voucher_code);
    }

    @Override
    public Observable<CategoriesModel> getStoreCategories(String id) {
        return apiService.getStoreCategories(id);
    }

    @Override
    public Observable<MenuDishes> getMenuDishes(int id) {
        return apiService.getMenuDishes(id);
    }

    @Override
    public Observable<SendOrderModel> sendJson(String data) {
        return apiServiceSend.sendOrderJson(data);
    }

    @Override
    public Observable<SendSms> sendSms(String sms) {
        return apiServiceSend.sendSms(sms);

    }

    @Override
    public Observable<OrderHistory> getOrderHistory(String fcm_token) {
        return apiService.getOrderHistory(fcm_token);
    }
}
