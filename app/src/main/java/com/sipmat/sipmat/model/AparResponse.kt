package com.sipmat.sipmat.model

import com.google.gson.annotations.SerializedName

data class AparResponse(

	@field:SerializedName("data")
	val data: List<AparModel>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

