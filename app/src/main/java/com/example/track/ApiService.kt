package com.example.track

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/api/hvac")  // Replace with your actual endpoint
    fun getHvacData(): Call<HvacData>
}