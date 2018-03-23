package com.ordinefacile.root.ordinefacile.data.network;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesModel;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishes;
import com.ordinefacile.root.ordinefacile.data.network.model.MyOrderSendJson;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistory;
import com.ordinefacile.root.ordinefacile.data.network.model.PinModel;
import com.ordinefacile.root.ordinefacile.data.network.model.QrCodeModel;

import org.json.JSONObject;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @POST("orders/create")
    Observable<JSONObject> sendOrderJson(@Body JSONObject req);

    @GET("orders/list/{token_fcm}")
    Observable<OrderHistory> getOrderHistory(@Path("token_fcm") String token_fcm);

}

