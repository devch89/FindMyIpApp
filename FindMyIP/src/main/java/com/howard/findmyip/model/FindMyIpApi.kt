package com.howard.findmyip.model

import retrofit2.Response
import retrofit2.http.GET

interface FindMyIpApi {
    @GET(Constants.ENDPOINT)
    suspend fun getMyIp(): Response<FindMyIpResponse>
}