package com.example.newproject

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.newproject.viewmodel.SpeedViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: SpeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val speedTextView: TextView = findViewById(R.id.speedTextView)
        val startButton: Button = findViewById(R.id.startButton)
        val userId = "userId" // Example userId

        viewModel.speedLiveData.observe(this, { speedData ->
            speedTextView.text = "Speed: ${speedData.speed} km/h"
        })

        viewModel.alertLiveData.observe(this, { alertTriggered ->
            if (alertTriggered) {
                Toast.makeText(this, "Speed limit exceeded!", Toast.LENGTH_SHORT).show()
            }
        })

        startButton.setOnClickListener {
            // Simulate speed tracking
            val speed = 25.0f
            viewModel.setSpeedData(speed, System.currentTimeMillis())
            viewModel.checkSpeed(speed, userId)
        }
    }
}

