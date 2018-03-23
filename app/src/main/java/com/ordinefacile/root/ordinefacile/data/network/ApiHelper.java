package com.ordinefacile.root.ordinefacile.data.network;

import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishes;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistory;
import com.ordinefacile.root.ordinefacile.data.network.model.PinModel;
import com.ordinefacile.root.ordinefacile.data.network.model.QrCodeModel;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesModel;
import org.json.JSONObject;
import rx.Observable;

/**
 * Created by root on 1/20/18.
 */

public interface ApiHelper {

      Observable<QrCodeModel> getStoreDetails(String qrCode);
      Observable<PinModel> getStoreDetailsPin(String pin);
      Observable<CategoriesModel> getStoreCategories(int id);
      Observable<MenuDishes> getMenuDishes(int id);
      Observable<JSONObject> sendJson(JSONObject data);
      Observable<OrderHistory> getOrderHistory(String token_fcm);
}
