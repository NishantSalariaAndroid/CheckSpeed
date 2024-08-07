package com.example.newproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newproject.model.SpeedAlert
import com.example.newproject.model.SpeedData
import com.example.newproject.repository.SpeedRepository

class SpeedViewModel : ViewModel() {

    private val repository = SpeedRepository()
    private val _speedLiveData = MutableLiveData<SpeedData>()
    val speedLiveData: LiveData<SpeedData> get() = _speedLiveData

    private val _alertLiveData = MutableLiveData<Boolean>()
    val alertLiveData: LiveData<Boolean> get() = _alertLiveData

    private val _speedLimitLiveData = MutableLiveData<Float>()
    val speedLimitLiveData: LiveData<Float> get() = _speedLimitLiveData

    fun checkSpeed(speed: Float, userId: String) {
        repository.getSpeedLimit(userId) { speedLimit ->
            if (speed > speedLimit) {
                // Send alert
                val alert = SpeedAlert(userId, speed, "deviceToken") // Replace with actual device token
                repository.sendSpeedAlert(alert)
                _alertLiveData.postValue(true)
            } else {
                _alertLiveData.postValue(false)
            }
        }
    }

    fun setSpeedData(speed: Float, timestamp: Long) {
        _speedLiveData.postValue(SpeedData(speed, timestamp))
    }
}

