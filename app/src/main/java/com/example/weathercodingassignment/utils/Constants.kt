package com.example.weathercodingassignment.utils

object Constants {
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/weather/"
    // In prod  API_KEY should be fetch from CI/CD.no hardcoding. To Test Add your backupKey
    const val API_KEY = "******************"
    const val IMAGE_URL = "https://openweathermap.org/img/wn/%s@2x.png"
    const val NO_DATA_AVAILABLE = "No weather information"
    const val KELVIN_TO_CEL = 273.15
}
