package com.howard.findmyip.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FindMyIpResponse(
    @Expose
    val region: String,
    @Expose
    val city: String,
    @Expose
    @SerializedName("country_name")
    val countryName: String
)