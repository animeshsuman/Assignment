package com.example.weathercodingassignment.utils


sealed class Result<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Result<T>(data)
    class Error<T>(errorMsg: String, data: T? = null) : Result<T>(data, errorMsg)
}


