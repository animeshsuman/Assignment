package com.example.codingtestassignment.weather.domain.use_cases

import com.example.weathercodingassignment.utils.Result
import com.example.weathercodingassignment.weather.domain.model.WeatherInfo
import com.example.codingtestassignment.weather.domain.repository.WeatherInfoRepository
import kotlinx.coroutines.flow.Flow

class GetWeatherInfoUseCase(private val repository: WeatherInfoRepository) {

    fun weatherData(query: String): Flow<Result<WeatherInfo>> {
        return repository.getWeatherInfo(query = query)
    }

    fun weatherData(lat: String, long: String): Flow<Result<WeatherInfo>> {
        return repository.getWeatherInfo(latitude = lat, longitude = long)
    }
}
