package com.sipmat.sipmat.model

import com.google.gson.annotations.SerializedName

data class ScheduleModel(

    @field:SerializedName("is_status")
    val isStatus: Int? = null,

    @field:SerializedName("tw")
    val tw: String? = null,

    @field:SerializedName("tahun")
    val tahun: String? = null,

    @field:SerializedName("lokasi")
    val lokasi: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("kode_apar")
    val kodeApar: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("tanggal_cek")
    val tanggalCek: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)