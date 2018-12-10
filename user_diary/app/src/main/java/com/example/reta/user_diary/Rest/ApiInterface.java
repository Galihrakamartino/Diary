package com.example.reta.user_diary.Rest;

import com.example.reta.user_diary.model.GetDiary;
import com.example.reta.user_diary.model.GetLogin;
import com.example.reta.user_diary.model.GetRegister;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

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


    @GET("diary/all/")
    Call<GetDiary> getDiary( );

    @FormUrlEncoded
    @POST("diary/all/")
    Call<GetDiary> getDiaryForId(
            @Field("id_user") String idUser
    );

    @Multipart
    @POST("diary/all")
    Call<GetDiary> postDiary(
            @Part MultipartBody.Part file,
            @Part("id_user") RequestBody id_user,
            @Part("mood") RequestBody mood,
            @Part("id_lokasi") RequestBody id_lokasi,
            @Part("judul") RequestBody judul,
            @Part("isi") RequestBody isi,
            @Part("tgl_diary") RequestBody tgl_diary,
            @Part("action") RequestBody action
    );


    @Multipart
    @POST("diary/all")
    Call<GetDiary> deleteDiary(
            @Part("id_diary") String id_diary,
            @Part("action") String action);


}
