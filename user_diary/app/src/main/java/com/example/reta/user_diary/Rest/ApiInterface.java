package com.example.reta.user_diary.Rest;

import com.example.reta.user_diary.model.GetLogin;
import com.example.reta.user_diary.model.GetRegister;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    /********* Login *********/
    // Ingat, tambahkan dulu fungsi login_post() pada controller Pembeli di REST server
    @Multipart
    @POST("pembeli/login")
    Call<GetLogin> postLogin(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password
    );

    @Multipart
    @POST("pembeli/register")
    Call<GetRegister> postRegister(
            @Part("id_login") RequestBody id_login,
            @Part("username") RequestBody username,
            @Part("password") RequestBody password
    );

}
