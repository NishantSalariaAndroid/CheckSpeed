package com.example.newproject.model

data class SpeedData(
    val speed: Float,
    val timestamp: Long
)
data class SpeedAlert(
    val userId: String,
    val speed: Float,
    val deviceToken: String
)
