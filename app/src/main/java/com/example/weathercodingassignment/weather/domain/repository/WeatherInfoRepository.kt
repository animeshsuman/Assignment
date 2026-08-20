package com.example.codingtestassignment.weather.domain.repository

import com.example.weathercodingassignment.utils.Result
import com.example.weathercodingassignment.weather.domain.model.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface WeatherInfoRepository {
    fun getWeatherInfo(query: String): Flow<Result<WeatherInfo>>
    fun getWeatherInfo(latitude: String, longitude: String): Flow<Result<WeatherInfo>>
}