package com.ordinefacile.root.ordinefacile.network;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by procodev on 12/8/2017.
 */

public interface API {



    String BASE_URL2 = "http://lebbyt5q-site.gtempurl.com/api/";


    @FormUrlEncoded
    @POST("error.php")
    Call<LoginModel> sendErroLogs(@Field("error") String error, @Field("json")String json);
}
