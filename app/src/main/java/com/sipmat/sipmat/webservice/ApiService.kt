package com.sipmat.sipmat.webservice

import com.sipmat.sipmat.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username : String,
        @Field("password") password: String,
        @Field("token_id") token_id: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name : String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("role") role: Int
    ): Call<RegisterResponse>



    @GET("getusers")
    fun getusers(
        @Query("role") role : Int
    ): Call<UsersResponse>

    //=========================APAR
    @FormUrlEncoded
    @POST("apar")
    fun tambahapar(
        @Field("kode") kode : String,
        @Field("jenis") jenis: String,
        @Field("lokasi") lokasi: String,
        @Field("tgl_pengisian") tgl_pengisian: String
    ): Call<PostDataResponse>

    @FormUrlEncoded
    @POST("updateapar")
    fun updateapar(
        @Field("id") id : Int,
        @Field("kode") kode : String,
        @Field("jenis") jenis: String,
        @Field("lokasi") lokasi: String,
        @Field("tgl_pengisian") tgl_pengisian: String
    ): Call<PostDataResponse>

    @FormUrlEncoded
    @POST("deleteapar")
    fun hapusapar(
        @Field("id") id : Int
    ): Call<PostDataResponse>

    @GET("apar")
    fun getapar(): Call<AparResponse>


}

