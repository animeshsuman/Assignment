package com.example.weathercodingassignment.weather.presentation

import com.example.weathercodingassignment.utils.Constants
import com.example.weathercodingassignment.weather.domain.model.WeatherInfo

data class WeatherInfoState(
    val weatherInfo: WeatherInfo = WeatherInfo(description = Constants.NO_DATA_AVAILABLE)
)
