package com.example.newproject.repository

import com.example.newproject.apicall.RetrofitInstance
import com.example.newproject.model.SpeedAlert
import com.example.newproject.model.SpeedLimitResponse
import com.google.firebase.functions.FirebaseFunctions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpeedRepository {

    private val functions = FirebaseFunctions.getInstance()
    private val api = RetrofitInstance.api

    // Fetch the speed limit from the API
    fun getSpeedLimit(userId: String, callback: (Float) -> Unit) {
        api.getSpeedLimit(userId).enqueue(object : Callback<SpeedLimitResponse> {
            override fun onResponse(call: Call<SpeedLimitResponse>, response: Response<SpeedLimitResponse>) {
                if (response.isSuccessful) {
                    val speedLimit = response.body()?.speedLimit ?: 20.0f // default speed limit
                    callback(speedLimit)
                } else {
                    // Handle API error
                }
            }

            override fun onFailure(call: Call<SpeedLimitResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }

    // Send speed alert via Firebase Functions
    fun sendSpeedAlert(alert: SpeedAlert) {
        val data = hashMapOf(
            "speed" to alert.speed,
            "deviceToken" to alert.deviceToken
        )

        functions.getHttpsCallable("sendSpeedAlert")
            .call(data)
            .addOnSuccessListener {
                // Handle success
            }
            .addOnFailureListener {
                // Handle failure
            }
    }
}


