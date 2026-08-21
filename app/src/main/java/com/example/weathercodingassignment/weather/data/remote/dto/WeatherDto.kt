package com.example.weathercodingassignment.weather.data.remote.dto

import com.example.weathercodingassignment.utils.Constants
import com.example.weathercodingassignment.weather.data.local.entity.WeatherEntity
import com.example.weathercodingassignment.weather.domain.model.WeatherInfo
import kotlinx.serialization.Serializable


@Serializable
data class WeatherDto(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int,
) {
    fun toWeatherInfo(): WeatherInfo {
        return WeatherInfo(
            name = name,
            description = weather.firstOrNull()?.description ?: "",
            temp = String.format(
                "%.1f°C",
                main.temp - Constants.KELVIN_TO_CEL
            ),
            feelsLike = String.format(
                "%.1f°C",
                main.feels_like - Constants.KELVIN_TO_CEL
            ),
            imageUrl = String.format(Constants.IMAGE_URL, weather.firstOrNull()?.icon ?: "")
        )
    }

    fun toWeatherEntity(): WeatherEntity {
        return WeatherEntity(
            name = name,
            description = weather.firstOrNull()?.description ?: "",
            temp = main.temp,
            feelsLike = main.feels_like,
            icon = weather.firstOrNull()?.icon ?: ""
        )

    }
}

@Serializable
data class Coord(val lon: Double, val lat: Double)

@Serializable
data class Weather(val id: Int, val main: String, val description: String, val icon: String)

@Serializable
data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int,
    val sea_level: Int = 0,
    val grnd_level: Int = 0
)

@Serializable
data class Wind(
    val speed: Double = 0.0,
    val deg: Int = 0,
    val gust: Double = 0.0
)

@Serializable
data class Clouds(val all: Int)

@Serializable
data class Sys(
    val type: Int = 0,
    val id: Int = 0,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)