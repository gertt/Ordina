package com.ordinefacile.root.ordinefacile.data.network;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesModel;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishes;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistory;
import com.ordinefacile.root.ordinefacile.data.network.model.PinModel;
import com.ordinefacile.root.ordinefacile.data.network.model.QrCodeModel;
import com.ordinefacile.root.ordinefacile.data.network.model.SendOrderModel;
import com.ordinefacile.root.ordinefacile.data.network.model.CallService;
import com.ordinefacile.root.ordinefacile.data.network.model.VauchePinModel;

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

    //<<<GET>>>

    // scan and qrcocde
    @GET("qr_scan/{qrcode}")
    Observable<QrCodeModel> getStoreDetails(@Path("qrcode") String qrCode);

    // scan and pin
    @GET("qr_scan/{pin}")
    Observable<PinModel> getStoreDetailsByPin(@Path("pin") String pin);

    // scan and voucher_code
    @GET("qr_scan/{voucher_code}")
    Observable<VauchePinModel> getStoreDetailsByVoucherCode(@Path("voucher_code") String voucher_code);

    //category product
    @GET("categories/store/{id}")
    Observable<CategoriesModel> getStoreCategories(@Path("id") String id);

    //category category
    @GET("products/category/{id}")
    Observable<MenuDishes> getMenuDishes(@Path("id") int id);

    //get my order history
    @GET("orders/list/{token_fcm}")
    Observable<OrderHistory> getOrderHistory(@Path("token_fcm") String token_fcm);

    //<<<POST>>>

    //send order
    @FormUrlEncoded
    @POST("orders/create")
    Observable<SendOrderModel> sendOrderJson(@Field("data") String data);

    //send
    @FormUrlEncoded
    @POST("call_service/{table_id}")
    Observable<CallService> sendSms(@Path("table_id") String table_id,@Field("data") String data);

}

