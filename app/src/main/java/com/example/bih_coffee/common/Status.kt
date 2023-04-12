package com.example.bih_coffee.common

sealed class Status<T>(val data: T? = null, val exception: Exception? = null) {
    class Success<T>(data: T) : Status<T>(data)
    class Error<T>(exception: Exception, data: T? = null) : Status<T>(data, exception)
    class Loading<T>(data: T? = null) : Status<T>(data)
}