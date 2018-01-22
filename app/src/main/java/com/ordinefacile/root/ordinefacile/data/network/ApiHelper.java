package com.ordinefacile.root.ordinefacile.data.network;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishes;
import com.ordinefacile.root.ordinefacile.data.network.model.Store;
import com.ordinefacile.root.ordinefacile.data.network.model.StoreCategories;

import rx.Observable;

/**
 * Created by root on 1/20/18.
 */

public interface ApiHelper {

      Observable<Store> getStoreDetails(String qrCode);
      Observable<StoreCategories> getStoreCategories(int id);
      Observable<MenuDishes> getMenuDishes(int id);

}
