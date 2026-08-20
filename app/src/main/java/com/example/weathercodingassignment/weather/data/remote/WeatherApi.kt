package com.example.weathercodingassignment.weather.data.remote

import com.example.weathercodingassignment.utils.Constants
import com.example.codingtestassignment.weather.data.remote.dto.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface WeatherApi {

    @GET
    suspend fun getWeather(
        @Url url: String = "",
        @Query("q") query: String,
        @Query("appid") appId: String = Constants.API_KEY
    ): Response<WeatherDto>

    @GET
    suspend fun getWeather(
        @Url url: String = "",
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") appId: String = Constants.API_KEY
    ): Response<WeatherDto>
}