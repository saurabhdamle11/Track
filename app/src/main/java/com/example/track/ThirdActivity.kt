package com.example.track

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ThirdActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val actualTimeTextView:TextView = findViewById(R.id.timeTextView)
        val titleTimeTextView:TextView = findViewById(R.id.actualTimeView)

        val backButton:Button = findViewById(R.id.backButton)
        val refreshButton:Button = findViewById(R.id.refreshButton)


        titleTimeTextView.text = "Time"
        updateTime(actualTimeTextView)

        refreshButton.setOnClickListener {
            updateTime(actualTimeTextView)
        }

        backButton.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }

    }

    private fun updateTime(actualTimeTextView: TextView){
        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        actualTimeTextView.text = currentTime
    }

}

