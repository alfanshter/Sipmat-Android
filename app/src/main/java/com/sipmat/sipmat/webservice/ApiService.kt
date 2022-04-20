package com.sipmat.sipmat.webservice

import com.sipmat.sipmat.model.*
import com.sipmat.sipmat.model.apar.CekAparModel
import com.sipmat.sipmat.model.apat.*
import com.sipmat.sipmat.model.hydrant.*
import com.sipmat.sipmat.model.postdata.PostScheduleApar
import com.sipmat.sipmat.model.postdata.UpdateScheduleApat
import com.sipmat.sipmat.model.postdata.UpdateScheduleHydrant
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("token_id") token_id: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("role") role: Int
    ): Call<RegisterResponse>


    @GET("getusers")
    fun getusers(
        @Query("role") role: Int
    ): Call<UsersResponse>

    //=========================APAR==============================
    @FormUrlEncoded
    @POST("apar")
    fun tambahapar(
        @Field("kode") kode: String,
        @Field("jenis") jenis: String,
        @Field("lokasi") lokasi: String,
        @Field("tgl_pengisian") tgl_pengisian: String
    ): Call<PostDataResponse>

    @FormUrlEncoded
    @POST("updateapar")
    fun updateapar(
        @Field("id") id: Int,
        @Field("kode") kode: String,
        @Field("jenis") jenis: String,
        @Field("lokasi") lokasi: String,
        @Field("tgl_pengisian") tgl_pengisian: String
    ): Call<PostDataResponse>

    @FormUrlEncoded
    @POST("deleteapar")
    fun hapusapar(
        @Field("id") id: Int
    ): Call<PostDataResponse>

    @GET("apar")
    fun getapar(): Call<AparResponse>

    @GET("apar_kadaluarsa")
    fun apar_kadaluarsa(): Call<AparResponse>

    @GET("apar")
    fun getapar_pick(): Call<AparPickResponse>

    //Schedule
    @FormUrlEncoded
    @POST("schedule_apar")
    fun schedule_apar(
        @Field("kode_apar") kode_apar: String,
        @Field("tw") tw: String,
        @Field("tahun") tahun: String,
        @Field("tanggal_cek") tanggal_cek: String
    ): Call<PostDataResponse>

    //Hapus Schedule
    @FormUrlEncoded
    @POST("hapus_schedule_apar")
    fun hapus_schedule_apar(
        @Field("id") id: Int
    ): Call<PostDataResponse>

    @GET("getschedule")
    fun getapar_pick(
        @Query("tw") tw: String,
        @Query("tahun") tahun: String
    ): Call<ScheduleResponse>

    @GET("gethasil")
    fun getapar_hasil(
        @Query("tw") tw: String,
        @Query("tahun") tahun: String
    ): Call<ScheduleResponse>

    @GET("getschedule_pelaksana")
    fun getschedule_pelaksana(): Call<ScheduleAparPelaksanaResponse>

    @GET("cekapar")
    fun cekapar(@Query("kode") kode: String): Call<CekAparModel>

    @Headers("Content-Type: application/json")
    @POST("update_schedule_apar")
    fun update_schedule_apar(@Body post: PostScheduleApar): Call<PostDataResponse>

    @FormUrlEncoded
    @POST("acc_apar")
    fun acc_apar(
        @Field("id") id: Int
    ): Call<PostDataResponse>

    @FormUrlEncoded
    @POST("return_apar")
    fun return_apar(
        @Field("id") id: Int
    ): Call<PostDataResponse>


    @Multipart
    @POST("apar_pdf")
    fun create_pdf(
        @Part image: MultipartBody.Part?,
        @Part("tw") tw: RequestBody,
        @Part("tahun") tahun: RequestBody,
        @Part("jabatan") jabatan: RequestBody,
        @Part("nama") nama: RequestBody,
        ): Call<PostDataResponse>


    //==========================APAAT =======================
    //update apat
    @FormUrlEncoded
    @POST("apat")
    fun apat(
        @Field("kode") kode: String,
        @Field("no_bak") no_bak: String,
        @Field("lokasi") lokasi: String,
    ): Call<PostDataResponse>

    //update apat
    @FormUrlEncoded
    @POST("updateapat")
    fun updateapat(
        @Field("id") id: Int,
        @Field("kode") kode: String,
        @Field("no_bak") no_bak: String,
        @Field("lokasi") lokasi: String,
    ): Call<PostDataResponse>

    @GET("getapat")
    fun getapat(): Call<ApatResponse>

    @GET("getapat")
    fun getapat_pick(): Call<ApatPickResponse>

    @FormUrlEncoded
    @POST("deleteapat")
    fun hapusapat(
        @Field("id") id: Int
    ): Call<PostDataResponse>
    //==========================Schedule APAAT =======================
    //Schedule
    @FormUrlEncoded
    @POST("schedule_apat")
    fun schedule_apat(
        @Field("kode_apat") kode_apat: String,
        @Field("tw") tw: String,
        @Field("tahun") tahun: String,
        @Field("tanggal_cek") tanggal_cek: String
    ): Call<PostDataResponse>

    @Headers("Content-Type: application/json")
    @POST("update_schedule_apat")
    fun update_schedule_apat(@Body post: UpdateScheduleApat): Call<PostDataResponse>

    @GET("cekapat")
    fun cekapat(@Query("kode") kode: String): Call<ApatModel>

    @GET("getschedule_apat")
    fun getschedule_apat(
        @Query("tw") tw: String,
        @Query("tahun") tahun: String
    ): Call<ScheduleApatResponse>

    @GET("getschedule_pelaksana_apat")
    fun getschedule_pelaksana_apat(): Call<ScheduleApatPelaksanaResponse>

    @GET("gethasil_apat")
    fun getapat_hasil(
        @Query("tw") tw: String,
        @Query("tahun") tahun: String
    ): Call<HasilApatResponse>

    //Hapus Schedule
    @FormUrlEncoded
    @POST("hapus_schedule_apat")
    fun hapus_schedule_apat(
        @Field("id") id: Int
    ): Call<PostDataResponse>

    @FormUrlEncoded
    @POST("acc_apat")
    fun acc_apat(
        @Field("id") id: Int
    ): Call<PostDataResponse>

    @FormUrlEncoded
    @POST("return_apat")
    fun return_apat(
        @Field("id") id: Int
    ): Call<PostDataResponse>

    @Multipart
    @POST("apat_pdf")
    fun apat_pdf(
        @Part image: MultipartBody.Part?,
        @Part("tw") tw: RequestBody,
        @Part("tahun") tahun: RequestBody,
        @Part("jabatan") jabatan: RequestBody,
        @Part("nama") nama: RequestBody,
    ): Call<PostDataResponse>

    //==========================Hydrant =======================
    @GET("gethydrant")
    fun itemhydrant(): Call<ItemHydrantResponse>

    @GET("gethydrant")
    fun gethydrant_pick(): Call<HydrantPickResponse>

    //Post Hydrant
    @FormUrlEncoded
    @POST("hydrant")
    fun hydrant(
        @Field("kode") kode: String,
        @Field("no_box") no_box: String,
        @Field("lokasi") lokasi: String,
    ): Call<PostDataResponse>
    //Update Hydrant
    @FormUrlEncoded
    @POST("updatehydrant")
    fun updatehydrant(
        @Field("kode") kode: String,
        @Field("no_box") no_box: String,
        @Field("lokasi") lokasi: String,
        @Field("id") id: Int
    ): Call<PostDataResponse>
    //delete
    @FormUrlEncoded
    @POST("deletehydrant")
    fun deletehydrant(
        @Field("id") id: Int
    ): Call<PostDataResponse>
    //==========================End Hydrant =======================
    //==========================Schedule Hydrant =======================
    @GET("getschedule_hydrant")
    fun getschedule_hydrant(
        @Query("tw") tw: String,
        @Query("tahun") tahun: String
    ): Call<ScheduleHydrantResponse>

    //Tambah Schedule
    @FormUrlEncoded
    @POST("schedule_hydrant")
    fun schedule_hydrant(
        @Field("kode_hydrant") kode_hydrant: String,
        @Field("tw") tw: String,
        @Field("tahun") tahun: String,
        @Field("tanggal_cek") tanggal_cek: String
    ): Call<PostDataResponse>

    //Hapus Schedule
    @FormUrlEncoded
    @POST("hapus_schedule_hydrant")
    fun hapus_schedule_hydrant(
        @Field("id") id: Int
    ): Call<PostDataResponse>

    @GET("getschedule_pelaksana_hydrant")
    fun getschedule_pelaksana_hydrant(): Call<ScheduleHydrantPelaksanaResponse>

    @Headers("Content-Type: application/json")
    @POST("update_schedule_hydrant")
    fun update_schedule_hydrant(@Body post: UpdateScheduleHydrant): Call<PostDataResponse>

    @GET("gethasil_hydrant")
    fun gethasil_hydrant(
        @Query("tw") tw: String,
        @Query("tahun") tahun: String
    ): Call<HasilHydrantResponse>

    @FormUrlEncoded
    @POST("acc_hydrant")
    fun acc_hydrant(
        @Field("id") id: Int
    ): Call<PostDataResponse>

    @FormUrlEncoded
    @POST("return_hydrant")
    fun return_hydrant(
        @Field("id") id: Int
    ): Call<PostDataResponse>

    @Multipart
    @POST("hydrant_pdf")
    fun hydrant_pdf(
        @Part image: MultipartBody.Part?,
        @Part("tw") tw: RequestBody,
        @Part("tahun") tahun: RequestBody,
        @Part("jabatan") jabatan: RequestBody,
        @Part("nama") nama: RequestBody,
    ): Call<PostDataResponse>
    //==========================End Schedule Hydrant =======================
}

