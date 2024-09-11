package com.example.track

import android.os.Bundle
import androidx.activity.ComponentActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast
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


    private fun fetchHvacData() {
        val call = RetrofitClient.apiService.getHvacData()

        call.enqueue(object : Callback<HvacData> {
            override fun onResponse(call: Call<HvacData>, response: Response<HvacData>) {
                if (response.isSuccessful) {
                    val hvacData = response.body()
                    if (hvacData != null) {
                        // Update the UI with fetched data
                        temperatureTextView.text = "Temperature: ${hvacData.temperature}"
                        humidityTextView.text = "Humidity: ${hvacData.humidity}"
                        hvacStatusTextView.text = "HVAC Status: ${hvacData.hvacStatus}"
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<HvacData>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}

