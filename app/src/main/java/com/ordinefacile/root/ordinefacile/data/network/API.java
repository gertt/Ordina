package com.ordinefacile.root.ordinefacile.data.network;
import com.ordinefacile.root.ordinefacile.data.network.model.Store;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by root on 1/20/18.
 */

public interface API {

  //  @FormUrlEncoded
  //  @GET("qr_scan/")
   // Observable<Store> getStoreDetails(@Field("qrCode") String qrCode);

    @GET("qr_scan/{qrcode}")
    Observable<Store> getStoreDetails(@Path("qrcode") String id);

}
