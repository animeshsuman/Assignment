package com.example.weathercodingassignment.weather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weathercodingassignment.weather.data.local.entity.WeatherEntity


@Database(entities = [WeatherEntity::class], version = 1)
abstract class WeatherDB : RoomDatabase() {

    abstract fun dao(): WeatherDao
}