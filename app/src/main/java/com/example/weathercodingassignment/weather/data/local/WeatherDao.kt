package com.example.weathercodingassignment.weather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weathercodingassignment.weather.data.local.entity.WeatherEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherData(products: List<WeatherEntity>)

    @Query("select * from WeatherEntity")
    suspend fun getWeatherData(): List<WeatherEntity>
}