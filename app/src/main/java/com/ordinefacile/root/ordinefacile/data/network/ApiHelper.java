package com.ordinefacile.root.ordinefacile.data.network;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishes;
import com.ordinefacile.root.ordinefacile.data.network.model.QrCodeModel;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesModel;

import rx.Observable;

/**
 * Created by root on 1/20/18.
 */

public interface ApiHelper {

      Observable<QrCodeModel> getStoreDetails(String qrCode);
      Observable<CategoriesModel> getStoreCategories(int id);
      Observable<MenuDishes> getMenuDishes(int id);

}
