package com.ordinefacile.root.ordinefacile.data.network;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesModel;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishes;
import com.ordinefacile.root.ordinefacile.data.network.model.PinModel;
import com.ordinefacile.root.ordinefacile.data.network.model.QrCodeModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by root on 1/20/18.
 */

public interface API {

    @GET("qr_scan/{qrcode}")
    Observable<QrCodeModel> getStoreDetails(@Path("qrcode") String qrCode);

    @GET("qr_scan/{pin}")
    Observable<PinModel> getStoreDetailsByPin(@Path("pin") String pin);

    @GET("categories/store/{id}")
    Observable<CategoriesModel> getStoreCategories(@Path("id") int id);

    @GET("products/category/{id}")
    Observable<MenuDishes> getMenuDishes(@Path("id") int id);

}
