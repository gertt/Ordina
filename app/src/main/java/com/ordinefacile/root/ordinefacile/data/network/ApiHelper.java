package com.ordinefacile.root.ordinefacile.data.network;

import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishes;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistory;
import com.ordinefacile.root.ordinefacile.data.network.model.PinModel;
import com.ordinefacile.root.ordinefacile.data.network.model.QrCodeModel;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesModel;
import com.ordinefacile.root.ordinefacile.data.network.model.SendOrderModel;
import com.ordinefacile.root.ordinefacile.data.network.model.VauchePinModel;
import rx.Observable;

/**
 * Created by root on 1/20/18.
 */

public interface ApiHelper {

      Observable<QrCodeModel> getStoreDetails(String qrCode);

      Observable<PinModel> getStoreDetailsPin(String pin);

      Observable<VauchePinModel> getStoreDetailsByVoucherCode(String voucher_code);

      Observable<CategoriesModel> getStoreCategories(String id);

      Observable<MenuDishes> getMenuDishes(int id);

      Observable<SendOrderModel> sendJson(String data);

      Observable<OrderHistory> getOrderHistory(String token_fcm);
}
