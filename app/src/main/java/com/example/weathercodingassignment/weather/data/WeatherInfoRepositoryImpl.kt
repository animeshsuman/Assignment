package com.example.weathercodingassignment.weather.data

import com.example.weathercodingassignment.utils.Constants
import com.example.weathercodingassignment.utils.Result
import com.example.weathercodingassignment.weather.data.local.WeatherDao
import com.example.weathercodingassignment.weather.data.remote.WeatherApi
import com.example.weathercodingassignment.weather.domain.model.WeatherInfo
import com.example.codingtestassignment.weather.domain.repository.WeatherInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class WeatherInfoRepositoryImpl(
    private val api: WeatherApi,
    private val dao: WeatherDao
) : WeatherInfoRepository {
    /**
     * Fetches weather information based on the provided search text.
    If the query is not blank, it attempts to retrieve weatherdata from the API.
     * If the API call is successful, the weather data is inserted into the local database,
    and a Result.Success containing the WeatherInfo is emitted.
     * If the query is not blank , it retrieves weather data from the local database.
    If data is available, a Result.Success containing the WeatherInfo is emitted.
    Otherwise, a Result.Success with a default "No data available" message is emitted.

     */
    override fun getWeatherInfo(query: String): Flow<Result<WeatherInfo>> = flow {
        if (query.isNotBlank()) {
            try {
                val response = api.getWeather(query = query)
                if (response.isSuccessful) {
                    response.body()?.let { weather ->
                        dao.insertWeatherData(mutableListOf(weather.toWeatherEntity()))
                        val newWeatherInfos =
                            dao.getWeatherData().firstOrNull()?.toWeatherInfo() ?: WeatherInfo(
                                description = Constants.NO_DATA_AVAILABLE
                            )
                        emit(Result.Success(data = newWeatherInfos))
                    }
                } else {
                    emit(Result.Error(errorMsg = "Server Error"))
                }
            } catch (e: HttpException) {
                emit(Result.Error(errorMsg = "Server Error"))
            } catch (e: IOException) {
                emit(Result.Error(errorMsg = "Server Error"))
            }
        } else {
            val weatherInfos = dao.getWeatherData().firstOrNull()?.toWeatherInfo() ?: WeatherInfo(
                description = Constants.NO_DATA_AVAILABLE
            )
            emit(Result.Success(data = weatherInfos))

        }


    }

    override fun getWeatherInfo(latitude: String, longitude: String): Flow<Result<WeatherInfo>> =
        flow {
            try {
                val response = api.getWeather(latitude = latitude, longitude = longitude)
                if (response.isSuccessful) {
                    response.body()?.let { weather ->
                        dao.insertWeatherData(mutableListOf(weather.toWeatherEntity()))
                        val newWeatherInfos =
                            dao.getWeatherData().firstOrNull()?.toWeatherInfo() ?: WeatherInfo(
                                description = Constants.NO_DATA_AVAILABLE
                            )
                        emit(Result.Success(data = newWeatherInfos))
                    }
                } else {
                    emit(Result.Error(errorMsg = "Server Error"))
                }
            } catch (e: HttpException) {
                emit(Result.Error(errorMsg = "Server Error"))
            } catch (e: IOException) {
                emit(Result.Error(errorMsg = "Server Error"))
            }
        }
}