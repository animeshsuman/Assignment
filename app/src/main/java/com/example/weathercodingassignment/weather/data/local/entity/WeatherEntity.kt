package com.example.weathercodingassignment.weather.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weathercodingassignment.utils.Constants
import com.example.weathercodingassignment.weather.domain.model.WeatherInfo

@Entity
data class WeatherEntity(
    // primary key is always 1 , to insert or replace existing  item
    @PrimaryKey(autoGenerate = false)
    val rowId: Int = 1,
    val name: String,
    val description: String,
    val temp: Double,
    val feelsLike: Double,
    val icon: String = " ",

    ) {
    fun toWeatherInfo(): WeatherInfo {
        return WeatherInfo(
            name = name,
            description = description,
            temp = String.format(
                "%.1f°C",
                temp - Constants.KELVIN_TO_CEL
            ),
            feelsLike = String.format(
                "%.1f°C",
                feelsLike - Constants.KELVIN_TO_CEL
            ),
            imageUrl = String.format(Constants.IMAGE_URL, icon)
        )
    }
}