package com.example.track

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.track.ui.theme.TrackTheme
import android.widget.TextView

class MainActivity : ComponentActivity() {
    private lateinit var temperatureTextView: TextView
    private lateinit var humidityTextView: TextView
    private lateinit var hvacStatusTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize TextViews
        temperatureTextView = findViewById(R.id.temperatureTextView)
        humidityTextView = findViewById(R.id.humidityTextView)
        hvacStatusTextView = findViewById(R.id.hvacStatusTextView)

        // Dummy data for now (replace this with real data fetching logic)
        val temperature = "23°C"
        val humidity = "45%"
        val hvacStatus = "Cooling"

        // Set the text on the TextViews
        temperatureTextView.text = "Temperature: $temperature"
        humidityTextView.text = "Humidity: $humidity"
        hvacStatusTextView.text = "HVAC Status: $hvacStatus"
    }

}

