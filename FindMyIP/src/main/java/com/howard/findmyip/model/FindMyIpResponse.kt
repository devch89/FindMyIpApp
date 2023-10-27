package com.howard.findmyip.model

data class FindMyIpResponse(
    val region: String,
    val city: String,
    val country_name: String)