package com.example.newproject.apicall

import com.example.newproject.model.SpeedLimitResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("speedLimit")
    fun getSpeedLimit(@Query("userId") userId: String): Call<SpeedLimitResponse>

    @POST("sendAlert")
    fun sendSpeedAlert(@Query("userId") userId: String, @Query("speed") speed: Float): Call<Void>
}