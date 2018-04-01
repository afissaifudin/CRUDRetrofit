package com.work.afis.crudretrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Asus on 3/31/2018.
 */

public interface RegisterAPI {

    @FormUrlEncoded
    @POST("/insert.php")
    Call<Value> daftar(@Field("npm") String npm,
                       @Field("nama") String nama,
                       @Field("kelas") String kelas,
                       @Field("sesi") String sesi);
}
