package com.example.track

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast
import android.hardware.SensorManager
import android.hardware.SensorEventListener
import android.hardware.SensorEvent
import android.util.Log
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
    private lateinit var sensorManager:SensorManager
    private var gyroscope: Sensor?=null

    private lateinit var gyroscopeXtextView:TextView
    private lateinit var gyroscopeYtextView:TextView
    private lateinit var gyroscopeZtextView:TextView


    val gyroscopeListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // Not needed for this example
        }

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                // Update the UI
                gyroscopeXtextView.text = "X: %.2f".format(x)
                gyroscopeYtextView.text = "Y: %.2f".format(y)
                gyroscopeZtextView.text = "Z: %.2f".format(z)
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gyroscopeXtextView = findViewById(R.id.temperatureTextView)
        gyroscopeYtextView = findViewById(R.id.humidityTextView)
        gyroscopeZtextView = findViewById(R.id.hvacStatusTextView)


        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Get the gyroscope sensor
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        if (gyroscope == null) {
            // Log and handle if the gyroscope is not available
            Log.e("MainActivity", "Gyroscope not available on this device")
            gyroscopeXtextView.text = "Gyroscope not available"
            return
        }

        gyroscope?.let {
            sensorManager.registerListener(gyroscopeListener, it, SensorManager.SENSOR_DELAY_NORMAL)
        }


    }

    fun onSensorChanged(event: SensorEvent){
        if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
            // Extract the x, y, and z values from the event
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            // Update the TextViews with the new values
            gyroscopeXtextView.text = "X: %.2f".format(x)
            gyroscopeYtextView.text = "Y: %.2f".format(y)
            gyroscopeZtextView.text = "Z: %.2f".format(z)
        }
    }

//    fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
//
//    }

//    override fun onPause() {
//        super.onPause()
//
//        sensorManager.unregisterListener(this)
//    }
//
//    override fun onResume() {
//        super.onResume()
//
//        gyroscope?.let {
//            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
//        }
//    }


//    private fun fetchHvacData() {
//        val call = RetrofitClient.apiService.getHvacData()
//
//        call.enqueue(object : Callback<HvacData> {
//            override fun onResponse(call: Call<HvacData>, response: Response<HvacData>) {
//                if (response.isSuccessful) {
//                    val hvacData = response.body()
//                    if (hvacData != null) {
//                        // Update the UI with fetched data
//                        temperatureTextView.text = "Temperature: ${hvacData.temperature}"
//                        humidityTextView.text = "Humidity: ${hvacData.humidity}"
//                        hvacStatusTextView.text = "HVAC Status: ${hvacData.hvacStatus}"
//                    }
//                } else {
//                    Toast.makeText(this@MainActivity, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<HvacData>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }

}

