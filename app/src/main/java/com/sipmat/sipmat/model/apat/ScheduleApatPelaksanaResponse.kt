package com.sipmat.sipmat.model.apat

import com.google.gson.annotations.SerializedName

data class ScheduleApatPelaksanaResponse(

	@field:SerializedName("data")
	val data: List<ScheduleApatPelaksanaModel>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ScheduleApatPelaksanaModel(

	@field:SerializedName("keterangan")
	val keterangan: Any? = null,

	@field:SerializedName("tw")
	val tw: String? = null,

	@field:SerializedName("tahun")
	val tahun: String? = null,

	@field:SerializedName("ember")
	val ember: String? = null,

	@field:SerializedName("gantungan")
	val gantungan: String? = null,

	@field:SerializedName("shift")
	val shift: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("tanggal_cek")
	val tanggalCek: String? = null,

	@field:SerializedName("bak")
	val bak: String? = null,

	@field:SerializedName("apar")
	val apar: Apar? = null,

	@field:SerializedName("kode_apat")
	val kodeApat: String? = null,

	@field:SerializedName("is_status")
	val isStatus: Int? = null,

	@field:SerializedName("karung")
	val karung: String? = null,

	@field:SerializedName("pasir")
	val pasir: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("sekop")
	val sekop: String? = null
)

data class Apar(

	@field:SerializedName("no_bak")
	val noBak: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("lokasi")
	val lokasi: String? = null,

	@field:SerializedName("kode")
	val kode: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
