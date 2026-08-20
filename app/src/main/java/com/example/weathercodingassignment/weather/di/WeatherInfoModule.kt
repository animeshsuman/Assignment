package com.example.weathercodingassignment.weather.di

import android.content.Context
import androidx.room.Room
import com.example.weathercodingassignment.utils.Constants
import com.example.weathercodingassignment.weather.data.WeatherInfoRepositoryImpl
import com.example.weathercodingassignment.weather.data.local.WeatherDB
import com.example.weathercodingassignment.weather.data.remote.WeatherApi
import com.example.codingtestassignment.weather.domain.repository.WeatherInfoRepository
import com.example.codingtestassignment.weather.domain.use_cases.GetWeatherInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class WeatherInfoModule {

    val networkJson = Json { ignoreUnknownKeys = true }
    @Singleton
    @Provides
    fun provideGetWeatherInfoUseCase(repository: WeatherInfoRepository): GetWeatherInfoUseCase {
        return GetWeatherInfoUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideWeatherInfoRepository(api: WeatherApi, db: WeatherDB): WeatherInfoRepository {
        return WeatherInfoRepositoryImpl(api, db.dao())
    }

    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context): WeatherDB {
        return Room.databaseBuilder(context = context, WeatherDB::class.java, "WeatherDBData")
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }


}